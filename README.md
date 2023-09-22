# Automation Tests Api

This is my project to tests automated the APIs of "desafio-qe".

### Requirements
- Java version 19+;
- Maven installed and configured in application;

### Steps necessary to run the project

1. Clone the repository;
2. In the terminal, run the command: `mvn clean install`

### Steps to run tests
Run the command: `mvn test`

### Generate report
After, you need to run the command `mvn allure:serve` to generate the reports. The report will open automatically in your browser.

### Important:
Some tests will be failure because it isn't following the business rules
