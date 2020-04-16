package utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BroswerActions {

	public static WebDriver driver;
	 
	
	public void openBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}
	
	public void closeBrowser() {
		WebDriverManager.chromedriver().setup();
		driver.close();
	}
	
	public void ExplicitWaitTime(String locator, String Key) {
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator))).sendKeys(Key);
		
	}
	
	public void ExplicitWaitTime(String locator) {
		WebDriverWait wait = new WebDriverWait(driver,5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator))).click();
		
	}
	
	public void ImplicitWaitTime() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
}
