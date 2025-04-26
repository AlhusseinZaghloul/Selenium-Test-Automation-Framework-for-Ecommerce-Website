
# Selenium Test Automation Framework for Swag Labs Website

This is a robust Selenium-based automation framework for testing web applications. It's built with Java, TestNG, Maven, Log4J2, and Allure Reporting, and leverages the Page Object Model (POM) for efficient test design. The framework's WebDriver management system is designed for flexibility and maintainability, utilizing the Abstract Factory, Factory Method, and Thread Local patterns to handle different browser types and ensure thread safety.

---

## Table of Contents
- [Prerequisites](#prerequisites)
- [Setup](#setup)
- [Project Structure](#project-structure)
- [Configuration](#configuration)
- [Running Tests](#running-tests)
- [Reporting](#reporting)
- [WebDriver Management Approach](#webdriver-management-approach)

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
Selenium-Test-Automation-Framework-for-Ecommerce-Website/
├── .idea/                  # IntelliJ configuration
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── drivers/    # WebDriver management
│   │   │   │   ├── AbstractDriver.java
│   │   │   │   ├── ChromeDriver.java
│   │   │   │   ├── EdgeDriver.java
│   │   │   │   ├── FirefoxDriver.java
│   │   │   │   ├── GUIDriver.java
│   │   │   │   └── WebDriverOptionsAbstract.java
│   │   │   ├── listeners/  # TestNG event listeners
│   │   │   │   └── TestNGListeners.java
│   │   │   ├── pages/      # Page Object Model (POM)
│   │   │   │   ├──  CartPage.java
│   │   │   │   ├──  CheckoutComplete.java
│   │   │   │   ├──  CheckoutInfoPage.java
│   │   │   │   ├──  CheckoutOverviewPage.java
│   │   │   │   ├──  ConfirmationPage.java
│   │   │   │   ├──  HomePage.java
│   │   │   │   └──  LoginPage.java
│   │   │   ├── utils/      # Utility classes
│   │   │   │   ├── AllureUtils.java
│   │   │   │   ├── BrowserActions.java
│   │   │   │   ├── ElementsActions.java
│   │   │   │   ├── FilesUtils.java
│   │   │   │   ├── CustomSoftAssertion.java
│   │   │   │   ├── JsonUtils.java
│   │   │   │   ├── LogsUtils.java
│   │   │   │   ├── PropertiesUtils.java
│   │   │   │   ├── ScreenshotUtils.java
│   │   │   │   ├── Scrolling.java
│   │   │   │   ├── TImeStampUtils.java
│   │   │   │   ├── Validations.java
│   │   │   │   └── Waits.java
│   │   │   └── resources/  # Configuration files
│   │   │       ├── allure.properties
│   │   │       ├── environment.properties
│   │   │       ├── log4j2.properties
│   │   │       ├── waits.properties
│   │   │       └── web.properties
│   └── test/
│       ├── java/
│       │   └── tests/      # Test cases
│       │       ├── CartTest.java
│       │       ├── EndToEndScenariosTest.java
│       │       ├── LoginTest.java
│       │       └── HomePageTest.java
│       && resources/      # Test data
│           └── testData.json
└── test-outputs/           # Test artifacts
    ├── allure-results/     # Allure report data
    ├── Logs/               # Log files
    ├── screenshots/        # Screenshot captures
    └── target/             # Build output
├── .gitignore              # Git ignore rules
├── pom.xml                 # Maven configuration
└── testng.xml              # TestNG suite file
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
explicit.wait=10
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

## WebDriver Management Approach
This framework employs a thread-safe mechanism to manage Selenium WebDriver instances using `ThreadLocal`, ensuring each test thread has its own isolated WebDriver instance for parallel execution. The design leverages the **factory design pattern** and adheres to the **single responsibility principle** for better maintainability and scalability.

### Key Components
1. **GUIDriver Class**:
   - Manages WebDriver instances in a thread-safe manner using `ThreadLocal<WebDriver>`.
   - Initializes drivers based on browser type, retrieves them for use, and quits them to free resources.
   - Uses a factory method to instantiate the appropriate browser-specific driver factory.

2. **AbstractDriver Class**:
   - Defines the contract for starting a WebDriver instance.
   - Extended by browser-specific factories (e.g., `ChromeFactory`, `FirefoxFactory`).

3. **WebDriverOptionsAbstract Interface**:
   - Provides a generic method to configure browser-specific options (e.g., `ChromeOptions`).
   - Implemented by each browser factory for customized settings.

4. **Browser-Specific Factories**:
   - Handle the creation and configuration of their respective WebDriver instances.
   - Each factory is responsible for its browser's driver creation and option configuration.

### How It Works
- **Factory Design Pattern**: The `GUIDriver` class uses a factory method to determine and instantiate the correct browser factory based on the browser name, decoupling driver creation from the main management class.
- **Single Responsibility Principle**: Each class has a clearly defined responsibility:
   - `GUIDriver`: Manages the lifecycle of WebDriver instances per thread.
   - `AbstractDriver`: Defines the interface for starting a driver.
   - `WebDriverOptionsAbstract`: Defines the interface for configuring browser options.
   - Browser factories: Handle the creation and configuration of specific WebDriver instances.
- **Initialization**: The `GUIDriver` constructor takes a browser name, uses the factory method to get the appropriate driver factory, starts the driver, and stores it in `ThreadLocal`.
- **Access**: `getDriver()` and `get()` retrieve the current thread’s driver.
- **Cleanup**: `quitDriver()` closes the driver and removes it from `ThreadLocal`.
- **Options Configuration**: Factories configure settings like headless mode based on execution type.

### Benefits
- **Thread Safety**: Isolated drivers per thread for safe parallel execution.
- **Extensibility**: Add new browsers by creating new factory classes without modifying existing code.
- **Maintainability**: Clear separation of concerns and well-defined responsibilities.
- **Resource Management**: Ensures proper cleanup of WebDriver instances.

---

### Key Features Highlighted:
1. **Modular Design**: Separation of concerns via POM, utilities, and listeners.
2. **Data-Driven Testing**: JSON-based test data in `testData.json`.
3. **Logging & Screenshots**: Automated logs and screenshot captures.
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
