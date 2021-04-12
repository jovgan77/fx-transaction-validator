# FX transaction validation service

FX transaction validation service is the reactive service that provides flexible and extensible validation of any products.

All you need is just define set of checks and collect them into required validator.

Each check has to implement interface:
```java
public interface Check<T> {

    ConstraintViolation check(T object);

}
```

Check should contain an atomic piece of validation and could return ConstrainViolation object, 
that contains error message and invalid value.

Once all required checks are created, then time to place them into product specific validator. 
Each product specific validator has to extend the AbstractValidator and define a set of checks.

```java
@RequiredArgsConstructor
public abstract class AbstractValidator<T> {

    private final Set<Check<T>> checks;

    public CumulativeResult validate(T object) {
        final CumulativeResult result = new CumulativeResult(object);
        checks.forEach(check -> result.addConstrainViolation(check.check((object))));
        return result;
    }

}
```

Validator provides cumulative result with all violations for each product instance. 
The result contains product instance itself and set of constrain violations.

For scaling purposes we could use Docker and Kubernetes.

## Build

 * ./gradlew clean build

## Run
* ./gradlew bootRun

## Swagger UI

* http://localhost:8080/swagger-ui

## API documentation

* http://localhost:8080/api-docs

## cURL

```
curl -X POST \
  http://localhost:8080/transactions/validate \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: ceb46f9a-ad6d-f010-343c-8943a6be5dd2' \
  -d '[
  {
    "customer": "YODA5",
    "ccyPair": "ASDQWE",
    "type": "Spot",
    "direction": "BUY",
    "tradeDate": "2020-08-11",
    "amount1": 1000000.00,
    "amount2": 1120000.00,
    "rate": 1.12,
    "valueDate": "2020-08-14",
    "legalEntity": "UBS AG",
    "trader": "Josef Schoenberger"
  },
  {
    "customer": "YODA2",
    "ccyPair": "EURUSD",
    "type": "Forward",
    "direction": "SELL",
    "tradeDate": "2020-08-11",
    "amount1": 1000000.00,
    "amount2": 1120000.00,
    "rate": 1.12,
    "valueDate": "2020-08-22",
    "legalEntity": "UBS AG",
    "trader": "Josef Schoenberger"
  },
  {
    "customer": "YODA1",
    "ccyPair": "EURUSD",
    "type": "VanillaOption",
    "style": "NARNIA",
    "direction": "BUY",
    "strategy": "CALL",
    "tradeDate": "2020-08-11",
    "amount1": 1000000.00,
    "amount2": 1120000.00,
    "rate": 1.12,
    "deliveryDate": "2020-08-21",
    "expiryDate": "2020-08-19",
    "payCcy": "USD",
    "premium": 0.20,
    "premiumCcy": "USD",
    "premiumType": "%USD",
    "premiumDate": "2020-08-12",
    "legalEntity": "UBS AG",
    "trader": "Josef Schoenberger"
  },
  {
    "customer": "YODA1",
    "ccyPair": "EURUSD",
    "type": "VanillaOption",
    "style": "AMERICAN",
    "direction": "BUY",
    "strategy": "CALL",
    "tradeDate": "2020-08-11",
    "amount1": 1000000.00,
    "amount2": 1120000.00,
    "rate": 1.12,
    "deliveryDate": "2020-08-21",
    "expiryDate": "2020-08-19",
    "excerciseStartDate": "2020-08-25",
    "payCcy": "USD",
    "premium": 0.20,
    "premiumCcy": "USD",
    "premiumType": "%USD",
    "premiumDate": "2020-08-12",
    "legalEntity": "UBS AG",
    "trader": "Josef Schoenberger"
  }
]'
```

## Issues

* Since RESTful API is not very suitable for validation goals, so the endpoint is not very pretty.

* Because I don't have relevant experience in currency trading, so some validation points looks not very clear for me,
  and I skipped following rule.

```
Spot, Forward transactions:
- validate the value date against the product type
```

* There two options to check whether is any date is public holiday for currency.
  The first is to store public holidays information in the application and update  manually. 
  This is inefficient and unstable solution.
  The second one is to use API to obtain the public holidays information. 
  Unfortunately, all suitable APIs are paid.
  Therefore, I left in application only check whether a day is weekend.
  
* There no full test coverage provided, just unit and integration tests examples presented.

* Of course, implies that security also has to be, but, as I understand it is out of scope of this task.