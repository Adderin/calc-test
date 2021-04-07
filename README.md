### Used: SpringBoot, JDK11, OpenApi, Lombok

### Endpoint to call: 
`localhost:8088/calculate`

### Sample request:
`{
    "formula": "(12+2)/2 - 8"
}`

### Sample response:
`{
    "result": "-1"
}`

> Assignment Topic: Design and implement an service evaluating algebraic expressions in JAVA.
> Deliverables: source codes, build script creating an war file deployable on an common servlet container (e.g. Tomcat)
> Assignment:  Design an input format suitable for representation of algebraic expressions. (e.g. XML, JSON, plaintext prefix notation.. whatever).

> The expression can contain:
>   * integer constant
>   * string constant
>   * binary operators +,-,*,/
>   * unary operator sizeof (string) – length of the string argument
>   * unary operator abs(int)  - absolute value of the integer argument
> Actual syntax does not matter ( element name "plus" suits well XML format while symbol "+" can be convenient for plaintext notations).
> Then implement an service evaluating the input expression in Java using the technologies of your choice (REST, SOAP, plain network sockets, Spring, JAX-WS).
> Then invoked with a valid request the service will produce a response using the same format (result is either string or integer constant)

> When evaluating the solution we will be interested in particular:
> * object oriented design
> * design patterns
> * modular and extensible design
> * maintainability of the delivered artefacts
> * correct usage of the selected technologies
> * justification of the selected technologies
