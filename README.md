
# Selenium Test Automation Framework for Ecommerce


A robust Selenium-based automation framework designed for end-to-end testing of ecommerce applications. Built using Java, TestNG, Maven, and Allure Reporting.

---

## Table of Contents
- [Prerequisites](#prerequisites)
- [Setup](#setup)
- [Project Structure](#project-structure)
- [Configuration](#configuration)
- [Running Tests](#running-tests)
- [Reporting](#reporting)

---

## Prerequisites
- Java 17+
- Maven 3.8+
- Chrome/Firefox/Edge WebDriver binaries (managed via SeleniumManager)
- IDE (IntelliJ recommended)

---

## Setup
1. Clone the repository:
   ```bash
   git clone https://github.com/alhusseinzaghloul/Selenium-Test-Automation-Framework-for-Ecommerce.git
   ```
2. Install dependencies:
   ```bash
   mvn clean install
   ```

---

## Project Structure
```
Selenium-Test-Automation-Framework-for-Ecommerce/
├── .idea/                  # IntelliJ configuration
├── .gitignore              # Git ignore rules
├── pom.xml                 # Maven configuration
├── testng.xml              # TestNG suite file
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── drivers/    # WebDriver management
│   │   │   │   └── BrowserFactory.java
│   │   │   │   └── DriverManager.java
│   │   │   ├── listeners/  # TestNG event listeners
│   │   │   │   └── TestNGListeners.java
│   │   │   ├── pages/      # Page Object Model (POM)
│   │   │   │   └── LoginPage.java
│   │   │   ├── utils/      # Utility classes
│   │   │   │   └── AllureUtils.java
│   │   │   │   └── BrowserActions.java
│   │   │   │   └── ElementsActions.java
│   │   │   │   └── FilesUtils.java
│   │   │   │   └── JsonUtils.java
│   │   │   │   └── LogsUtils.java
│   │   │   │   └── PropertiesUtils.java
│   │   │   │   └── ScreenshotUtils.java
│   │   │   │   └── Scrolling.java
│   │   │   │   └── Waits.java
│   │   │   └── resources/  # Configuration files
│   │   │       └── allure.properties
│   │   │       └── environment.properties
│   │   │       └── log4j2.properties
│   │   │       └── waits.properties
│   │   │       └── web.properties
│   └── test/
│       ├── java/
│       │   └── tests/      # Test cases
│       │       └── LoginTest.java
│       └── resources/      # Test data
│           └── testData.json
└── test-outputs/           # Test artifacts
    ├── allure-results/     # Allure report data
    ├── Logs/               # Log files
    ├── screenshots/        # Screenshot captures
    └── target/             # Build output
```

---

## Configuration
### Environment-Specific Settings
Modify `environment.properties`:
```properties
loginPageURL=https://www.saucedemo.com/
homePageURL=https://www.saucedemo.com/inventory.html
appTitle=Swag Labs
errorMSG=Epic sadface: Username and password do not match any user in this service
```

### Logging
Configure logging in `log4j2.properties`:
```properties
appender.console.type=Console
appender.console.name=STDOUT
appender.console.layout.type=PatternLayout
appender.console.layout.pattern=%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n
```

### Wait Strategies
Adjust implicit/explicit wait timeouts in `waits.properties`:
```properties
explicit.wait=30
```

---

## Running Tests
1. Run all tests:
   ```bash
   mvn clean test
   ```
2. Generate Allure report:
   ```bash
   allure serve test-outputs/allure-results
   ```

---

## Reporting
The framework uses **Allure Reports** for detailed test results. After execution:
1. Open `test-outputs/allure-results` in your browser.
2. View aggregated reports with timelines, steps, and screenshots.

---

### Key Features Highlighted:
1. **Modular Design**: Separation of concerns via POM, utilities, and listeners.
2. **Data-Driven Testing**: JSON-based test data in `testData.json`.
3. **Logging & Screenshots**: Automated logs and screenshot captures on failure.
4. **CI/CD Ready**: Maven + TestNG setup for seamless integration.
5. **Single Responsibility Principle**:
    - Each class has a single responsibility (e.g., page classes handle element interactions, utility classes handle specific operations).
    - Test classes focus solely on test logic, while configuration and reporting are handled separately.
    - Improves maintainability and reduces side effects during updates.
6. **Java Best Practices**:
    - Follows Java naming conventions (`camelCase` for variables, `PascalCase` for classes).
    - Javadoc comments for all public methods and classes.
    - Clear method names (e.g., `performLogin()`, `validateCartContents()`).
    - Consistent code formatting for readability.
