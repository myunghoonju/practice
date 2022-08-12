package practice.mockito.bdd;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;

@ExtendWith(MockitoExtension.class)
public class PhoneBookServiceTest {

    @Mock
    PhoneBookRepository repository;
    @InjectMocks
    PhoneBookService service;

    @Test
    public void searchTest() {
        given(repository.contains("Mom")).willReturn(true);
        given(repository.getPhoneNumberByName("Mom"))
                .will((InvocationOnMock invocation) ->{
                    if(invocation.getArgument(0).equals("Mom")) {
                        return "01234";
                    } else {
                        return null;
                    }
                });

        String phoneNumber = service.search("Mom");

        then(repository).should().contains("Mom");
        then(repository).should().getPhoneNumberByName("Mom");

        assertThat(phoneNumber).isEqualTo("01234");
    }

    @Test
    public void invalidSearchTest() {
        given(repository.contains("x")).willReturn(false);

        String result = service.search("x");

        then(repository).should().contains("x");
        then(repository).should(never()).getPhoneNumberByName("x");

        assertThat(result).isEqualTo(null);
    }

}