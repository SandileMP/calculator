package za.co.afferent.evaluator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import za.co.afferent.evaluator.dtos.OperationDTO;
import za.co.afferent.evaluator.services.AlgebraService;

import java.util.*;

@SpringBootTest
class EvaluatorApplicationTests {
    @InjectMocks
    private AlgebraService algebraService;

    @Test
    public void givenExpression_Success() {
        String expression = "9.4*8/4";
        Double result = algebraService.workExpression(expression);
        Assertions.assertEquals(18.0, result);
    }

    @Test
    public void givenExpression2_Success() {
        String expression = "9-4*8/4";
        Double result = algebraService.workExpression(expression);
        Assertions.assertEquals(1.0, result);
    }

    @Test
    public void givenExpression3_Success() {
        String expression = "(3*3)+5-2*3";
        Double result = algebraService.workExpression(expression);
        Assertions.assertEquals(8.0, result);
    }



    @Test
    public void givenInvalidExpression() {
        String expression = "";
        Double result = algebraService.workExpression(expression);
        Assertions.assertEquals(0.0, result);
    }


}
