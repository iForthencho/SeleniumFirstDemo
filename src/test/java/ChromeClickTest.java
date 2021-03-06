import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeClickTest {

	public static void main(String[] args) throws InterruptedException {		
		String projectPath = System.getProperty("user.dir");
		System.out.println("projectPath: " + projectPath);
		
		System.setProperty("webdriver.chrome.driver", projectPath + "\\Drivers\\chromedriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.get("http://localhost:3000/FAQ");
		
		// get all the links
		java.util.List<WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println(links.size());
				
		// print all the links
		for (int i = 0; i < links.size(); i=i+1) {
			System.out.println(i + " " + links.get(i).getText());
			System.out.println(i + " " + links.get(i).getAttribute("href"));
		}
		
		// maximize the browser window
		//driver.manage().window().maximize();
		
		// click all links in a web page
		for(int i = 0; i < links.size(); i++)
		{
			System.out.println("*** Navigating to" + " " + links.get(i).getAttribute("href"));
			if (links.get(i).getAttribute("href") == null)
				continue;
			boolean staleElementLoaded = true;
			//the loop checks whether the elements is properly loaded
			while(staleElementLoaded) {
				try {
					//navigate to the link
					driver.navigate().to(links.get(i).getAttribute("href"));
					Thread.sleep(3000);
					//click the back button in browser
					driver.navigate().back();
					links = driver.findElements(By.tagName("a"));
					System.out.println("*** Navigated to" + " " + links.get(i).getAttribute("href"));
					staleElementLoaded = false;
				} catch (StaleElementReferenceException e) {
					staleElementLoaded = true;
				}
			}
		}
		driver.close();
	}
}
