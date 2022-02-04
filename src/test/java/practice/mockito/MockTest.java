package practice.mockito;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MockTest {

    @InjectMocks
    RecordService service;
    @Mock
    DatabaseDAO databaseDAO;
    @Mock
    NetworkDAO networkDAO;
    @Spy
    List<String> spyList = new ArrayList<>();

    @Test
    public void saveTest() {
        databaseDAO.save();
        boolean result = service.save();

        assertThat(result).isTrue();

        verify(databaseDAO, Mockito.times(1)).save();
        verify(networkDAO, Mockito.times(1)).save();
    }


    @Test
    public void spyTest() {
        spyList.add("one");
        spyList.add("two");

        verify(spyList).add("one");
        verify(spyList).add("two");

        assertThat(spyList.size()).isEqualTo(2);

        doReturn(100).when(spyList).size();

        assertThat(spyList.size()).isEqualTo(100);
    }

}