package za.co.afferent.evaluator.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import za.co.afferent.evaluator.dtos.OperationDTO;
import za.co.afferent.evaluator.dtos.ResponseDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

@Service
public class AlgebraService extends AuxUtility{
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
            String postFixForm = convertToPostFixForm(expression);
            return evalutate(postFixForm);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
    //Once in postfix form, we will then proceed to evaluate
    public Double evalutate(String postFixExpression) {
        Stack<Double> operands = new Stack<>();
        String[] expr = postFixExpression.split(" ");
        List<String> strings = new ArrayList<String>(Arrays.asList(expr));
        for (int i = 0; i < strings.size(); i++) {
            if (strings.get(i).equalsIgnoreCase(".")){
                String newString = strings.get(i-1)+strings.get(i)+strings.get(i+1);
                strings.add(i-1, newString);
                strings.remove(i);
                strings.remove(i+1);
                strings.remove(".");
            }
            if (isOperand(strings.get(i))) {
                operands.push(Double.parseDouble(strings.get(i)));
            } else {
                Double value1 = operands.pop();
                Double value2 = operands.pop();
                Double result = calculate(value2, value1, strings.get(i));
                operands.push(result);
            }
        }
        return operands.pop();
    }

    //This method will allow us to breakdown the equation to postfix format
    //Once in post fix format we can then be able to process the equation, with the correct precedence
    public String convertToPostFixForm(String expression) {
        try {
            logger.info("ALG, Converting expression to postFix Format");
            Stack<Character> tokens = new Stack<>();
            char[] expr = expression.toCharArray();
            StringBuilder postFixExpression = new StringBuilder();
            for (int i = 0; i < expr.length; i++) {
                if (Character.isDigit(expr[i]) || expr[i] == '.') {
                    postFixExpression.append(expr[i]);
                    postFixExpression.append(" ");
                } else if (isOperator(expr[i])) {
                    while (!tokens.empty() && isTopOfStackHigher(tokens.peek(), expr[i])) {
                        postFixExpression.append(tokens.peek());
                        postFixExpression.append(" ");
                        tokens.pop();
                    }
                    tokens.push(expr[i]);
                } else if (!isOpeningBracket(expr[i])) {
                    char x = tokens.pop();
                    while (!isOpeningBracket(x)) {
                        postFixExpression.append(x);
                        postFixExpression.append(" ");
                        x = tokens.pop();
                    }
                } else if (isOpeningBracket(expr[i])) {
                    tokens.push(expr[i]);
                } else {
                    postFixExpression.append(expr[i]);
                }
            }
            while (!tokens.empty()) {
                postFixExpression.append(tokens.peek());
                postFixExpression.append(" ");
                tokens.pop();
            }
            logger.info("ALG, Postfix Form: "+postFixExpression.toString());
            return postFixExpression.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


}
