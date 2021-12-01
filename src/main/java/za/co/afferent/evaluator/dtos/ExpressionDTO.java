package za.co.afferent.evaluator.dtos;

import org.springframework.lang.NonNull;

//Class to handle complex expression strings
public class ExpressionDTO {
    @NonNull
    private String expression;

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }
}
