**Web Scraper for Hockey Teams and Oscar-Winning Films - Sanjay Chauhan**

## Project Name and Description:
This project automates the process of scraping data from websites related to hockey teams and Oscar-winning films using Selenium WebDriver. The data is stored in JSON files for further analysis and verification. The project involves iterating through web tables, capturing specific data points, and validating the output using TestNG.

## Installation Instructions:
Follow these steps to set up and run the project locally:

Clone the Repository:

bash
Copy code
git clone https://github.com/YOUR_USERNAME/YOUR_REPOSITORY.git
cd YOUR_REPOSITORY
Ensure Java is Installed:

Make sure you have Java 17 installed. You can verify your Java version using the following command:
bash
Copy code
java --version
Expected output:
bash
Copy code
openjdk 17.0.x
Install Gradle:

If Gradle is not installed, you can install it using:
bash
Copy code
brew install gradle
Verify the installation:
bash
Copy code
gradle --version
Set Up Dependencies:

Ensure that all project dependencies are set up by running:
bash
Copy code
./gradlew build
Set Up TestNG:

TestNG is already integrated, but ensure testng.xml and build.gradle files are correctly configured.
Run the Tests:

To execute the tests, run:
bash
Copy code
./gradlew test


## Usage and Examples:
Here’s how you can run the project and see it in action:

Running the Project:

Use the following command to run the project:
bash
Copy code
./gradlew test
Sample Usage - Scraping Hockey Teams Data:

The project automatically navigates to the "Hockey Teams" section of the website and scrapes data based on the specified criteria (e.g., teams with a win percentage less than 40%). The data is then stored in a JSON file.
Example:
java
Copy code
ArrayList<HashMap<String, Object>> hockeyData = scraper.scrapeHockeyTeamData();
// Output JSON file: hockey-team-data.json
Sample Usage - Scraping Oscar-Winning Films Data:

The project navigates to the "Oscar Winning Films" section, scrapes data for the top 5 movies for each year, and checks whether the movie won the best picture award. The data is stored in a JSON file.
Example:
java
Copy code
ArrayList<HashMap<String, Object>> oscarData = scraper.scrapeOscarWinningFilms();
// Output JSON file: oscar-winner-data.json
Verifying the Output:

The project includes TestNG assertions to ensure the JSON files are created and not empty.

Example:

java
Copy code
@Test
public void validateHockeyTeamDataFile() {
    File jsonFile = new File("output/hockey-team-data.json");
    Assert.assertTrue(jsonFile.exists(), "File should exist");
    Assert.assertTrue(jsonFile.length() > 0, "File should not be empty");
}
Running the test:

bash
Copy code
./gradlew test --tests "com.example.ValidateOutputFiles"

```

## Important Links:
Selenium WebDriver Documentation: https://www.selenium.dev/documentation/
TestNG Documentation: https://testng.org/doc/
Jackson JSON Processor: https://github.com/FasterXML/jackson
Gradle Build Tool: https://gradle.org/
 
