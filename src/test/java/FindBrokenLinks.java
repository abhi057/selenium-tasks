import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FindBrokenLinks {
	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver", "D:\\eclipse-ws\\SeleniumUITests\\newDriver\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		driver.get("http://amazon.com");

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
