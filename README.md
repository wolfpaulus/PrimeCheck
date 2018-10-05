#PrimeCheck 

PrimeCheck is a VERY simple AWS Lambda Function implemented in Java. 
It has two classes for representing the request and response objects. 
A third class that implements [RequestHandler](https://github.com/aws/aws-lambda-java-libs/blob/master/aws-lambda-java-core/src/main/java/com/amazonaws/services/lambda/runtime/RequestHandler.java), determines if a provided number is a prime number or not 
and responds with a message.

AWS Lambda for Java, is currently using a very old Jackson Lib on Amazons service side. 
It does not yet support annotation like: JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE) 
Therefore, JsonProperty must be declared and if private, getter methods needs to be implemented as well.

The project also has some unit tests implemented and uses Log4J2 locally and also when deployed, logging into AWS CloudWatch.


##Interacting with AWS
For convenience, the PrimeCheck projects includes gradle tasks for 
* Creating a AWS LambdaBasicExecutionRole, which needs to be created even before the function can be deployed
* Deploying of the Lambda Function
* Invoking of the Lambda Function

This can be done from a commandline in Terminal or Inside the Gradle Tool-Window inside of IntelliJ IDEA
