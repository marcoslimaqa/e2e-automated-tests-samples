e2e-automated-tests-samples
-------------------

Web, API and Desktop Test Samples

This project uses
-------------------
* Java
* maven
* Cucumber
* Selenium
* RestAssured
* Sikuli
* ExtentReports
* PageObjects
* PageFactory
* SikuliFactory

Automated applications in this project
-------------------

* Web application: https://www.saucedemo.com
* API: https://restful-booker.herokuapp.com/apidoc
* Desktop application: app\ProcessExplorer\procexp.exe

Test Features
-------------------

[features](src/test/resources/features)

How to run
-------------------

Run all tests:
> mvn test

Run specific test suite:
> mvn test -Dtest=APITest

> mvn test -Dtest=UIDesktopTest

> mvn test -Dtest=UIWebTest