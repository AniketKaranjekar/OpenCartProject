# Opencart Automation Framework

## Overview

This is a Java-based Selenium automation framework built using TestNG and Page Object Model for the OpenCart (TutorialsNinja) demo application.

It is designed to handle real-world UI challenges like dynamic elements, synchronization issues, and CI execution.

---

## Tech Stack

* Language: Java
* Automation: Selenium WebDriver (4.x)
* Test Framework: TestNG
* Build Tool: Maven
* Reporting: ExtentReports
* Logging: Log4j2
* CI: GitHub Actions
* Driver Management: WebDriverManager

---

## Project Structure

```
src/test/java

base
 └── BaseTest.java

factory
 └── DriverFactory.java

pages
 ├── BasePage.java
 ├── HomePage.java
 ├── LoginPage.java
 ├── MyAccountPage.java
 ├── RegistrationPage.java
 ├── SearchPage.java
 ├── ShoppingCartPage.java
 └── WishListPage.java

tests
 ├── RegistrationTest.java
 ├── LoginTest.java
 ├── SearchProductTest.java
 ├── AddToCartTest.java
 ├── WishListTest.java
 ├── ShoppingCartTest.java

utilities
 ├── ConfigReader.java
 ├── DataProviders.java
 ├── ScreenshotUtil.java
 └── ExtentReportManager.java

listeners
 └── TestListener.java
```

## Core Design

### DriverFactory

* Centralized WebDriver creation
* Supports headless execution for CI
* Uses ChromeOptions for Linux compatibility
* Handles sandbox and memory issues
* Reads configuration from config.properties

### BaseTest

* Manages test lifecycle using TestNG annotations
* Supports browser parameterization
* Provides logging and random data generation

### Page Object Model

* Each page contains locators and actions
* Synchronization handled inside page methods
* Improves maintainability and readability

### Synchronization Strategy

* Uses explicit waits instead of Thread.sleep
* Handles dynamic UI and async behavior
* Improves test stability in CI

### Test Design

* Tests follow business flows
* Focus on system behavior instead of UI text
* Validate navigation using URL
* Validate data presence instead of static UI

---

## Utilities

### ConfigReader

* Reads data from config.properties
* Avoids hardcoding

### DataProviders

* Provides data-driven testing using TestNG

### ScreenshotUtil

* Captures screenshots on failure

### ExtentReportManager

* Generates HTML reports
* Logs execution details

---

## TestNG Listener

* Integrates ExtentReports
* Captures screenshots on failure
* Logs test execution results

---

## Execution

### Run via Maven

```
mvn clean test -DsuiteXmlFile=testng.xml -Dbrowser=chrome
```

### Run in CI (GitHub Actions)

* Runs on Ubuntu
* Uses headless Chrome
* Uploads reports as artifacts

---

## Reporting

* Extent Reports in /reports
* Screenshots in /screenshots
* Logs via Log4j2

---

## Challenges Solved

* Chrome crash in CI fixed using headless configuration
* Session creation issues resolved with proper ChromeOptions
* TestNG parameter issues fixed using optional parameters
* Element interaction issues solved with explicit waits
* Navigation issues fixed using URL validation
* Flaky tests stabilized with better synchronization
