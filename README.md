## Opencart Automation Framework

This project is a **Java + Selenium + TestNG** automation framework for the Opencart (TutorialsNinja) demo site, organized for clean Page Object Model (POM) usage and easy interview explanation.

### Tech stack

- **Language**: Java
- **Test framework**: TestNG
- **Automation**: Selenium WebDriver
- **Build**: Maven
- **Reporting**: ExtentReports
- **Logging**: Log4j2
- **Data‑driven**: TestNG `@DataProvider` (simple, no Excel)

### Project structure

```text
src/test/java
│
├── base
│   └── BaseTest.java
│
├── factory
│   └── DriverFactory.java
│
├── pages
│   ├── BasePage.java
│   ├── HomePage.java
│   ├── LoginPage.java
│   ├── MyAccountPage.java
│   ├── RegistrationPage.java
│   └── SearchPage.java
│
├── tests
│   ├── RegistrationTest.java
│   ├── LoginTest.java
│   ├── SearchProductTest.java
│   ├── AddToCartTest.java
│   ├── WishListTest.java
│   ├── ShoppingCartTest.java
│   └── LoginDDTTest.java
│
├── utilities
│   ├── ConfigReader.java
│   ├── DataProviders.java
│   ├── ScreenshotUtil.java
│   └── ExtentReportManager.java
│
└── listeners
    └── TestListener.java
```

`src/test/resources`

- `config.properties` – application URL, credentials, search product, etc.
- `log4j2.xml` – logging configuration.

### Core concepts

- **DriverFactory**
  - Central place for creating and managing WebDriver instances.
  - Reads configuration from `config.properties` (browser, URL).
  - Applies timeouts, window settings, and opens the base URL.

- **BaseTest**
  - Parent class for all tests.
  - Initializes logger and gets the driver from `DriverFactory`.
  - Provides random data helpers for registration tests.

- **Page Object Model**
  - All UI interactions are encapsulated in `pages` classes.
  - Each page exposes clear methods (e.g., `clickLinkMyAccount()`, `setEmail()`, `clickLogin()`).
  - Tests stay readable and focused on business flows.

- **Utilities**
  - `ConfigReader`: loads key/value pairs from `config.properties`.
  - `DataProviders`: provides simple data‑driven inputs (no Excel).
  - `ScreenshotUtil`: captures screenshots on failures.
  - `ExtentReportManager`: configures ExtentReports and logs test status + screenshots.

- **Listeners**
  - `TestListener` plugs `ExtentReportManager` into TestNG via `testng.xml`.

### How to run tests

1. **Pre‑requisites**
   - JDK 8+ installed.
   - Maven installed and on `PATH`.
   - Browser drivers available on `PATH` (e.g., ChromeDriver, EdgeDriver) or managed by your WebDriver setup.

2. **Clone / open project**

```bash
cd Opencart
```

3. **Run via Maven**

```bash
mvn test
```

4. **Run via TestNG XML (IDE)**

- Open `testng.xml` and run it as a TestNG suite.
- The suite runs all classes under `tests`:
  - `RegistrationTest`, `LoginTest`, `SearchProductTest`, `AddToCartTest`, `WishListTest`, `ShoppingCartTest`, `LoginDDTTest`.

### Reports & logs

- **ExtentReports HTML report**: generated under the `reports` folder (file name includes timestamp).
- **Screenshots**: saved under the `screenshots` folder and linked from the Extent report on failures.
- **Logs**: written under the `logs` folder according to `log4j2.xml`.

### Good points to mention in interviews

- Separation of concerns: `base` (test lifecycle), `factory` (driver), `pages` (POM), `tests` (scenarios), `utilities` (cross‑cutting).
- Data‑driven login using TestNG `@DataProvider` + Excel.
- Data‑driven login using TestNG `@DataProvider` (simple, interview‑friendly).
- Centralized config and driver management for easy environment/browser changes.
- Automatic reporting and screenshots through TestNG listeners and ExtentReports.

