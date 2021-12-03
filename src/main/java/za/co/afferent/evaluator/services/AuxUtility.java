package za.co.afferent.evaluator.services;

import za.co.afferent.evaluator.dtos.OperationDTO;

import java.util.ArrayList;
import java.util.List;

public  class AuxUtility {
    public boolean isOperator(char character) {
        if (character == '+' || character == '-' || character == '/' || character == '*') {
            return true;
        } else {
            return false;
        }
    }
    public Double calculate(Double val1, Double val2, String operator) {
        if (operator.equalsIgnoreCase("/"))
            return val1 / val2;
        if (operator.equalsIgnoreCase("*"))
            return val1 * val2;
        if (operator.equalsIgnoreCase("+"))
            return val1 + val2;
        if (operator.equalsIgnoreCase("-"))
            return val1 - val2;
        return 0.0;
    }
    public boolean isOperand(String text) {
        try {
            double value = Double.parseDouble(text);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean isOpeningBracket(char token) {
        if (token == '(')
            return true;
        else
            return false;
    }
    //Helper function to assist in prioritizing operations
    public boolean isTopOfStackHigher(char top, char operator) {
        try {
            List<OperationDTO> operatorWeights = new ArrayList<>();
            operatorWeights.add(new OperationDTO(4, '/'));
            operatorWeights.add(new OperationDTO(3, '*'));
            operatorWeights.add(new OperationDTO(2, '+'));
            operatorWeights.add(new OperationDTO(1, '-'));
            int topPriority = operatorWeights.stream().filter(op -> op.getOperator() == top).findFirst().get().getPriority();
            int incomingPriority = operatorWeights.stream().filter(op -> op.getOperator() == operator).findFirst().get().getPriority();
            if (topPriority > incomingPriority) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
