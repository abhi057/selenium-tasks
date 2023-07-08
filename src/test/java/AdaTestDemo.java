import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import com.deque.axe.AXE;

public class AdaTestDemo {
    public static void main(String[] args) {
        // Set the path to the chromedriver executable
        System.setProperty("webdriver.chrome.driver", "D:\\eclipse-ws\\chromedriver.exe");

        // Create a new instance of the ChromeDriver
        WebDriver driver = new ChromeDriver();
        
        final URL scriptURL = AdaTestDemo.class.getResource("/axe.min.js");

        // Launch the URL
        driver.get("https://www.yahoo.com");
        
        JSONObject responseJson = new AXE.Builder(driver, scriptURL).analyze();
        JSONArray violations = responseJson.getJSONArray("violations");
        
        if(violations.length() == 0) {
        	System.out.println("No ADA issues found!");
        }
        else {
        	System.out.println("ADA issues found...");
        	AXE.writeResults("LaunchURLTest", responseJson);
        	Assert.assertTrue(false, AXE.report(violations));
        }

        // Close the browser
        driver.quit();
    }
}