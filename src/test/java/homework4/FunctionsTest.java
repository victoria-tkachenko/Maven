package homework4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FunctionsTest {

    Functions functions = new Functions();
    @Test
    @DisplayName("Проверить функцию isNumberPositive положительным числом")
    void testIsNumberPositive() {
        Assertions.assertTrue(functions.isNumberPositive(2));
    }
}
