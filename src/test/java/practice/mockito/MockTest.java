package practice.mockito;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    @Mock
    List<Object> mockList = new ArrayList<>();
    @Captor
    ArgumentCaptor captor;

    @Mock
    Map<String, String> words;
    Dictionary dic;

    @Before
    public void initDic() {
        dic = spy(new Dictionary(words));
    }

    @Test
    public void realObjectTest() {
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

    @Test
    public void captorTest() {
        mockList.add("one");
        verify(mockList).add(captor.capture());

        assertThat(captor.getValue()).isEqualTo("one");
    }

    @Test
    public void injectMockIntoSpyTest() {
        when(words.get("aWord")).thenReturn("aMeaning");
        assertThat(dic.getMeaning("aWord")).isEqualTo("aMeaning");
    }

}