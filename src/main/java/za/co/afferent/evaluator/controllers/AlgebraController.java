package za.co.afferent.evaluator.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import za.co.afferent.evaluator.dtos.ExpressionDTO;
import za.co.afferent.evaluator.dtos.ResponseDTO;
import za.co.afferent.evaluator.services.AlgebraService;


@RestController
public class AlgebraController {
    private Logger logger = LoggerFactory.getLogger(AlgebraController.class);
    @Autowired
    private AlgebraService algebraService;
    @GetMapping("/test")
    public String testServices(){
        return "Services Up";
    }
    @PostMapping("/evaluate")
    public ResponseEntity<ResponseDTO> evaluate(@RequestBody ExpressionDTO expressionDTO){
        logger.info("ALG, evaluate service reached");
        return algebraService.evaluate(expressionDTO.getExpression());
    }
}
