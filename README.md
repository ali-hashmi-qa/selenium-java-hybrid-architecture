# 🎭 Production-Ready Enterprise Selenium Hybrid Automation Framework

[![Docker Selenium Grid CI Pipeline]
(https://github.com/ali-hashmi-qa/selenium-java-hybrid-architecture/actions/workflows/ci.yml/badge.svg)
(https://github.com/ali-hashmi-qa/selenium-java-hybrid-architecture/actions/workflows/ci.yml)

## 🛠️ Tech Stack & Libraries

| Category | Technology | Version |

| **Language** | Java | `17` |
| **Automation** | Selenium WebDriver | `4.45.0` |
| **Testing** | TestNG | `7.12.0` |
| **Build Tool** | Apache Maven (Surefire) | `3.5.6` |
| **Driver Manager** | WebDriverManager | `6.3.2` |
| **Data Engine** | Apache POI | `5.5.1` |
| **Infrastructure** | Docker & Docker Compose | Grid 4 |
| **CI/CD** | GitHub Actions | Runner |
| **Reporting** | Extent Reports | `5.1.2` |
| **Logging** | Log4j2 | `2.25.1` |

An enterprise-grade, thread-safe, cross-browser hybrid test automation framework engineered in **Java 17**, **Selenium WebDriver 4**, **TestNG**, and **Maven**. Built following **Page Object Model (POM)** and **Fluent Interface** design principles, fully data-driven via **Apache POI**, and containerized using **Docker Compose** for continuous execution on **GitHub Actions CI**.

---

## 🌟 Framework Highlights & Key Architecture

* 🧪 **Page Object Model (POM) + Fluent Interface:** Clean encapsulation of locators and actions with page chaining methods.
* ⚡ **Thread-Safe Parallel Execution:** Utilizes `ThreadLocal<WebDriver>` for isolated, thread-safe parallel test execution across multiple browsers without session collisions.
* 📊 **Data-Driven Engine (Apache POI):** Leverages TestNG `@DataProvider` mapped to external Excel sheets (`.xlsx`), enabling multi-dataset scenario testing without hardcoded values.
* 🐳 **Containerized Infrastructure (Docker Grid 4):** Integrated with Docker Compose to spin up distributed Selenium Grid 4 Hub and Chrome/Firefox browser nodes.
* 🚀 **GitHub Actions CI/CD Pipeline:** Fully automated cross-platform continuous integration runner running headlessly against containerized Selenium Grid nodes on every `push` and `pull_request`.
* 🖼️ **Self-Contained Portable Extent Reports:** Captures high-res failure screenshots embedded as **Base64 encoded strings** directly inside HTML reports, resolving broken image references in CI artifacts.
* 📝 **Robust Observability & Logging:** Features Apache **Log4j2** with `RollingFileAppender` and console appenders for complete execution step tracking.
* 💻 **Cross-Platform & OS Independent:** File paths are constructed dynamically using Java's `File.separator`, ensuring compatibility across Windows, Linux, and macOS.

---

## 🏗️ System Architecture & Workflow Diagram

```text
                               ┌─────────────────────────────────────────────────┐
                               │             GitHub Actions CI Pipeline          │
                               │                (ubuntu-latest)                  │
                               └───────────────────────┬─────────────────────────┘
                                                       │
                                                       ▼
                               ┌─────────────────────────────────────────────────┐
                               │           Docker Compose Infrastructure         │
                               │                                                 │
                               │   ┌──────────────────────────────────────────┐   │
                               │   │   Selenium Grid 4 Hub (Port 4444)        │   │
                               │   └────────────────────┬─────────────────────┘   │
                               │                        │                         │
                               │           ┌────────────┴────────────┐            │
                               │           ▼                         ▼            │
                               │   ┌───────────────┐         ┌───────────────┐    │
                               │   │  Chrome Node  │         │ Firefox Node  │    │
                               │   └───────────────┘         └───────────────┘    │
                               └───────────────────────▲─────────────────────────┘
                                                       │
                                   HTTP JSON Wire Protocol (RemoteWebDriver)
                                                       │
 ┌─────────────────────────────────────────────────────┴─────────────────────────────────────────────────────┐
 │                                           Java Test Automation Framework                                  │
 │                                                                                                           │
 │  ┌────────────────────────┐      ┌────────────────────────┐      ┌─────────────────────────────────────┐  │
 │  │      Test Layer        │      │   Page Object Layer    │      │             Core Utilities          │  │
 │  │  (LoginTest.java)      │ ───► │  (LoginPage, HomePage) │ ───► │  • DriverFactory (ThreadLocal)      │  │
 │  │  • TestNG Parallel     │      │  • BasePage (Fluent)   │      │  • ExcelUtil (Apache POI DataProvider)│  │
 │  │  • TestListener        │      │  • Private Encapsulation│     │  • ConfigReader (Properties & JVM)  │  │
 │  └───────────┬────────────┘      └────────────────────────┘      └──────────────────┬──────────────────┘  │
 └──────────────┼──────────────────────────────────────────────────────────────────────┼─────────────────────┘
                │                                                                      │
                ▼                                                                      ▼
 ┌─────────────────────────────┐                                      ┌─────────────────────────────────────┐
 │     Execution Artifacts     │                                      │          System Observability        │
 │  • Extent HTML Reports      │                                      │  • Base64 Fail Screenshots          │
 │  • Downloadable CI Zip      │                                      │  • Log4j2 Rolling File Logging      │
 └─────────────────────────────┘                                      └─────────────────────────────────────┘
 
 
 📁 Project Directory Structure
 selenium-hybrid-framework/
├── .github/
│   └── workflows/
│       └── ci.yml                 # GitHub Actions pipeline definition
├── docker-compose.yml             # Docker infrastructure for Selenium Grid 4 Hub & Nodes
├── pom.xml                        # Maven dependencies & Surefire configuration
├── testng.xml                     # Parallel cross-browser execution suite configuration
├── .dockerignore                  # Docker build exclusion file
├── .gitignore                     # Git exclusion file (target/, test-output/ ignored)
└── src/
    ├── main/
    │   └── java/
    │       ├── base/
    │       │   └── BasePage.java  # Reusable Web-action wrappers & explicit waits
    │       ├── constants/
    │       │   └── FrameworkConstants.java # Dynamic cross-platform file paths
    │       ├── factory/
    │       │   └── DriverFactory.java # ThreadLocal driver manager (Local & Remote)
    │       ├── listeners/
    │       │   └── TestListener.java  # TestNG lifecycle listener for extent reporting
    │       ├── pages/
    │       │   ├── LoginPage.java # POM encapsulated actions with Fluent interface
    │       │   └── HomePage.java  # Dashboard verification actions
    │       ├── reports/
    │       │   ├── ExtentManager.java     # Singleton ExtentReports manager
    │       │   └── ExtentTestManager.java # ThreadLocal ExtentTest manager
    │       └── utils/
    │           ├── ConfigReader.java   # Properties reader & JVM override parser
    │           ├── DateUtil.java       # Timestamp utility for report/log naming
    │           ├── ExcelUtil.java      # Apache POI Excel parser
    │           └── ScreenshotUtil.java # Base64 screenshot capturer
    └── test/
        ├── java/
        │   ├── base/
        │   │   └── BaseTest.java        # Test setup/teardown & browser injection
        │   ├── tests/
        │   │   └── LoginTest.java       # Parameterized data-driven test cases
        │   └── utils/
        │       └── TestDataProviders.java # TestNG @DataProvider definitions
        └── resources/
            ├── config.properties        # Framework execution defaults
            ├── log4j2.xml               # Logging configuration and rollover policies
            └── testdata/
                └── login_test_data.xlsx # External Excel test data source
                

🚀 Execution Instructions
Prerequisites
Java JDK 17+ installed & JAVA_HOME configured.
Apache Maven 3.8+ installed.
Docker Desktop (Optional, required only for local Selenium Grid execution).

1️⃣ Run Locally (Standard Browser Execution)
To run tests on your local machine using standard Chrome/Firefox GUI windows:
mvn clean test

2️⃣ Run Locally in Headless Mode
To run tests in the background without opening browser windows:
mvn clean test -Dheadless=true

3️⃣ Run via Local Docker Selenium Grid
Execute tests against containerized Chrome & Firefox browsers running on a local Docker Grid:

# Step 1: Spin up containerized Grid Hub & Nodes
docker compose up -d

# Step 2: Execute suite against Grid (listening on http://localhost:4444)
mvn clean test -Dexecution_env=grid -Dheadless=true

# Step 3: Tear down Grid containers after execution finishes
docker compose down

🌐 View the live Grid dashboard by navigating to http://localhost:4444 in your browser.

📊 Reports & Execution Artifacts
After execution, all test output artifacts are dynamically generated in the test-output/ folder:
test-output/
├── extent-reports/
│   └── extent-report-20260819_170000.html   # HTML Extent Report
├── logs/
│   └── automation.log                        # Log4j2 execution log
└── screenshots/
    └── loginTest_20260819_170015.png        # Physical PNG screenshots
    
    
☁️ Continuous Integration Artifacts (GitHub Actions)
On every push or pull_request to main, GitHub Actions executes the full test suite in headless mode on Linux runners.
Navigate to the Actions tab in GitHub -> Click on the latest workflow run -> Download the execution-test-output ZIP artifact to view the full HTML report, embedded failure screenshots, and execution logs generated in the cloud!

🧩 Design Patterns & Architecture Principles
Page Object Model (POM): Prevents code duplication by decoupling page UI locators from test logic.
Fluent Interface Pattern: Method calls in page objects return page instances (return this; or return new HomePage();), allowing method chaining (e.g., loginPage.enterUsername().enterPassword().clickLogin()).
Factory Design Pattern: Encapsulates browser initialization logic inside DriverFactory.
Singleton Design Pattern: ExtentManager enforces a single, thread-safe ExtentReports instance across the execution lifecycle.
ThreadLocal Driver Storage: Guarantees parallel thread isolation by giving every executing thread its own dedicated WebDriver instance.


👤 Author & Contact
Ali Hashmi|QA Automation Engineer
LinkedIn: www.linkedin.com/in/syedali-hashmi