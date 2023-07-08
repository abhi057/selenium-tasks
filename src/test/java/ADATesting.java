import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.deque.axe.AXE;

public class ADATesting {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        // Set the path to the chromedriver executable
        System.setProperty("webdriver.chrome.driver", "D:\\eclipse-ws\\SeleniumUITests\\Driver\\chromedriver.exe");

        // Create a new instance of the ChromeDriver
        driver = new ChromeDriver();
    }

    @Test
    public void testAccessibility() {
        try {
            // Load the axe script
            final URL scriptURL = ADATesting.class.getResource("/axe.min.js");

            // Launch the URL to be tested
            driver.get("https://www.amazon.com/");

            // Analyze the page using axe
            JSONObject responseJson = new AXE.Builder(driver, scriptURL).analyze();
            JSONArray violations = responseJson.getJSONArray("violations");

            // If no violations are found, print a message indicating that the page is accessible
            if (violations.length() == 0) {
                System.out.println("No ADA issues found!");
            } else {
                // If violations are found, write the results to a file and fail the test with a message indicating the violations
                System.out.println("ADA issues found as metioned below: ");
                AXE.writeResults("ADATestingResults", responseJson);
                Assert.fail("Accessibility issues found: " + AXE.report(violations));
            }
        } catch (Exception e) {
            // If an exception occurs, fail the test with a message indicating the exception
            Assert.fail("Exception occurred: " + e.getMessage());
        }
    }

    @AfterClass
    public void tearDown() {
        // Close the browser
        driver.quit();
    }
}
