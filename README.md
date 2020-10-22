e2e-automated-tests-samples
-------------------

Web, API and Desktop Automated Tests Samples

Automated applications in this project
-------------------

* Web application: https://www.saucedemo.com
* API: https://restful-booker.herokuapp.com/apidoc
* Desktop application: app\ProcessExplorer\procexp.exe

Automated Features
-------------------

[features](src/test/resources/features)

How to run the tests
-------------------

Run all tests:
> mvn test

Run specific test suite:
> mvn test -Dtest=APITest

> mvn test -Dtest=UIDesktopTest

> mvn test -Dtest=UIWebTest

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

Author
-------------------

* **Marcos Lima** - [marcoslimaqa@gmail.com](mailto:marcoslimaqa@gmail.com)