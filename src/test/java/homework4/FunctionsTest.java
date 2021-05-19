package homework4;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


public class FunctionsTest {

    Functions functions = new Functions();
    @Test
//    @DisplayName("Проверить функцию isNumberPositive положительным числом") была такая аннотация в jupiter
//    void testIsNumberPositive() {
//        Assertions.assertTrue(functions.isNumberPositive(2));
//    } Assertions в jupiter

    void testIsNumberPositive() {
        Assertions.assertThat(functions.isNumberPositive(2));
    }
}
