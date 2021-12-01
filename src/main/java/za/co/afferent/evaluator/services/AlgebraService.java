package za.co.afferent.evaluator.services;


import com.fathzer.soft.javaluator.DoubleEvaluator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import za.co.afferent.evaluator.dtos.ResponseDTO;

@Service
public class AlgebraService {
    //This class will be responsible for handling all algebraic calculations
    private Logger logger = LoggerFactory.getLogger(AlgebraService.class);

    public ResponseEntity<ResponseDTO> evaluate(String expression){
        logger.info("ALG, attempting to evaluate: {}", expression);
        if (workExpression(expression) == null){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO("Something went wrong while trying to evaluate expression",expression));
        }else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO("Expression evaluated",workExpression(expression)));
        }
    }
    public Double workExpression(String expression){
        logger.info("ALG, Attempting to work out expression");
        try {
            //If an empty expression is given, return 0
            if (expression.isEmpty()){
                return 0.0;
            }
            DoubleEvaluator eval = new DoubleEvaluator();
            Double answer = eval.evaluate(expression);
            return answer;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
