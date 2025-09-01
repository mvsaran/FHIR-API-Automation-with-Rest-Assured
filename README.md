# 🚀 FHIR API Automation with Allure Reports

This project demonstrates automated testing of **HL7 FHIR APIs** using **Java, RestAssured, TestNG, and Allure Reporting**.  
It dynamically generates FHIR resources (like `Patient`) using **FakerUtils** and validates responses from the FHIR server.

---

## 📌 Tech Stack
- **Java 11+**
- **TestNG**
- **RestAssured**
- **Maven**
- **Allure Reports**
- **Faker (for dynamic test data generation)**

---

## ⚙️ Project Features
- ✅ Create and validate FHIR resources (`Patient`, extendable to `Observation`, `Condition`, etc.)
- ✅ Utility-based framework (`ApiUtils`, `FakerUtils`) for reusability
- ✅ Allure Reporting integration for rich test results
- ✅ TestNG XML support for flexible test execution
- ✅ Designed for scalability to support multiple FHIR resources

---

## 🏗 Project Structure
FHIR-API-TESTING
│── src
│ ├── main/java/utils
│ │ ├── ApiUtils.java # Handles API calls
│ │ ├── FakerUtils.java # Generates dynamic FHIR test data
│ └── test/java/tests
│ ├── PatientTest.java # Test case for Patient resource
│
│── testng.xml # TestNG suite file
│── pom.xml # Maven dependencies + plugins
│── allure-results/ # JSON results (auto-generated after tests)

---

## 🚀 Running Tests

### Run via Maven
```bash
mvn clean test
📊 Generate Allure Report

After execution, results are stored in allure-results.

Serve the Allure report:

allure serve allure-results


Or generate a static report:

allure generate allure-results -o allure-report --clean


Open the report:

allure open allure-report

📈 Sample Output (Allure Report)

Rich graphical interface for test results

Metadata like Epic, Feature, Story, and Description are mapped in reports

Easy debugging with request/response logs

🤝 Contributions

Extend with new FHIR resources (Observation, Condition, MedicationRequest)

Add negative test cases and security testing

Improve test data strategy

👨‍💻 Author

Saran Kumar
