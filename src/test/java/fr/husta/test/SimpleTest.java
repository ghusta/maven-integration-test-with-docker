package fr.husta.test;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleTest {

    @Test
    public void shouldTestNothingInteresting() {
        // just a unit test for the example

        assertThat(1 + 2).isEqualTo(3).as("Simple operation test");
    }

}
