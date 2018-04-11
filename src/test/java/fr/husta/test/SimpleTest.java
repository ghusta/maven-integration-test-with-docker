package fr.husta.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.Serializable;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class SimpleTest {

    @Mock
    private Serializable myMockData;

    @Test
    public void shouldTestNothingInteresting() {
        // just a unit test for the example

        assertThat(1 + 2).isEqualTo(3).as("Simple operation test");
    }

    @Test
    public void shouldUseMocks() {
        assertThat(myMockData).isNotNull().as("myMockData should have been mocked");
    }

}
