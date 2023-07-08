import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class HeadlessTesting {

    public static void main(String[] args) {
        // set chrome driver path
        System.setProperty("webdriver.chrome.driver", "D:\\eclipse-ws\\SeleniumUITests\\Driver\\chromedriver.exe");

        // set options for headless mode
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");

        // create a new ChromeDriver with options
        WebDriver driver = new ChromeDriver(options);

        // navigate to Yahoo.com
        driver.get("https://www.yahoo.com/");
	//	driver.get("http://www.google.com");

		List<WebElement> links = driver.findElements(By.tagName("a"));

		for (WebElement link : links) {
			String url = link.getAttribute("href");
			try {
				HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
				connection.connect();
				int responseCode = connection.getResponseCode();
				if (responseCode >= 400) {
					System.out.println(url + " is a broken link");
				}
			} catch (Exception e) {
				System.out.println(url + " is a broken link");
			}
		}

		driver.quit();
	}
}