# M2 Automated Tests

## Description
This repository contains automated tests for the M2 platform using Selenium WebDriver and TestNG. The tests cover navigation and interactions with the Markets and Wallets sections of the M2 website.

## Setup and Dependencies
- Maven is used as the build tool.
- Dependencies include Selenium for web automation, TestNG for testing, Log4j for logging, ExtentReports for reporting, and SLF4J for logging abstraction.
- Ensure Java 17 is installed.

## Test Class: M2Test.java
- **TC1: Check navigation to Markets link and fetch network requests**
  - Description: Verifies navigation to the Markets link and captures network requests.
  - Assertions: Validates URL and allows for additional request validations.

- **TC2: Check navigation to Wallets tab and sort by Price Desc**
  - Description: Verifies navigation to the Wallets tab, sorts by price descending, and captures network requests.
  - Assertions: Validates URL and allows for additional request validations.

## NetworkHandler Class Overview
The `NetworkHandler` class manages network interactions for the automation framework. It utilizes Selenium's DevTools to capture network requests and responses.

#### `NetworkHandler` Class Overview:
- **Purpose**: Manages network interactions for the automation framework.
- **Key Methods**:
  - `initialize(WebDriver driver)`: Initializes the DevTools session for capturing network information.
  - `captureNetwork(String targetUrl)`: Captures network requests and responses, matching them against a target URL.

#### Functionality:
- Utilizes Selenium's DevTools for network interception and monitoring.
- Captures request and response details, including method, URL, request body, status, and response body.
- Matches requests to corresponding responses based on URL.

This class plays a crucial role in monitoring network activities during automated tests, facilitating the validation of requests and responses.

## Test Execution
- Run the tests using TestNG or your preferred test runner.
- The tests use listeners and extend the BaseTest class.

## Notes
- Customize additional assertions or validations within the test methods.
- Ensure proper configuration of the environment, including setting the `baseUrl` appropriately in the BaseTest class.

Feel free to adjust configurations and add more test scenarios as needed!
