package homework4;


import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assumptions.*;
import static org.junit.jupiter.api.Assertions.*;

@Feature("Тестирование вычисления площади треугольника")
public class FunctionCalculateTriangleSquareTest {

    FunctionCalculateTriangleSquare functionCalculateTriangleSquare = new FunctionCalculateTriangleSquare();
    private static Logger logger = LoggerFactory.getLogger(FunctionCalculateTriangleSquareTest.class);


    @Test
    @Step("Тест позитивный с равными сторонами")
    void testIsTrianglePositive() throws NotTriangleException {
        logger.debug("Позитивная проверка верными значениями");
        assertThat(functionCalculateTriangleSquare.calculateTriangleSquare(2,2,2)).isNotNegative();
    }

    @Test
    void checkExceptionWhenNotTriangleZero() {
        logger.debug("Проверка выброса исключения при передаче нулевых значений");
        assertThatExceptionOfType(NotTriangleException.class).isThrownBy(() ->
                functionCalculateTriangleSquare.calculateTriangleSquare(0,0,0));
    }

    @Test
    void checkExceptionWhenNotTriangleNegativeSide(){
        logger.debug("Проверка выброса исключения при передаче отрицательного значения");
        assertThatExceptionOfType(NotTriangleException.class).isThrownBy(() ->
                functionCalculateTriangleSquare.calculateTriangleSquare(-2, 2, 2));
    }

    @Test
    void checkExceptionWhenNotTriangleTwoSidesLess() {
        logger.debug("Проверка выброса исключения в случае если две стороны меньше третьей");
        assertThatExceptionOfType(NotTriangleException.class).isThrownBy(() ->
                functionCalculateTriangleSquare.calculateTriangleSquare(2, 7, 10));
    }

}
