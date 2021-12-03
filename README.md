# AffeeCalc
## _Algebra Made Easy_

AffeeCalc is a cloud based API that helps with solving complex algebraic expressions.


## How to get the source code running locally

- This is a Java based application. So ensure that you have the necessary runtimes and JDK installed as well as maven
- Clone the application
- Open the application on your favorite IDE and press play
- #### Building and Running via MVN 
```sh
mvn clean install
mvn spring-boot:run
```
- Alternatively, you can access the live API at       https://afferent-calculator.herokuapp.com/evaluate

## Using the API
Endpoint:      https://afferent-calculator.herokuapp.com/evaluate
Method: POST
Sample Request
```json
{
    "expression":"9-4*8/4"
}
```

Sample response
```json

{
    "message": "Expression evaluated",
    "response": 1.0
}
```

