package login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utils.BroswerActions;

public class Login extends BroswerActions {

	static Login login = new Login();
	final String USERNAME = "swapnil@dev.squirrelcloudtest.com";
	final String PASSWORD = "optimus@123";
	final String INVALID_USERNAME = "idontexist@dev.squirrelcloudtest.com";
	final String INVALID_PASSWORD = "optimus123";
	final String NAVIGATE_TO_URL = "http://squirrelcloudtest.com";

	@BeforeMethod
	public void openChrome() {

		login.openBrowser();
	}

	@AfterMethod
	public void closeChrome() {
		login.closeBrowser();
	}

	@Test(priority = 1)
	public void loginWithValidUser() {
		Reporter.log("Logging to squirrelcloudtest.com");
		driver.get(NAVIGATE_TO_URL);

		login.ImplicitWaitTime();

		driver.findElement(By.xpath("//input[@id='i0116']")).sendKeys(USERNAME);
		login.ExplicitWaitTime("//input[@id='idSIButton9']");

		login.ImplicitWaitTime();

		driver.findElement(By.xpath("//input[@id='i0118']")).sendKeys(PASSWORD);
		login.ExplicitWaitTime("//input[@id='idSIButton9' and @value='Sign in']");
//		driver.findElement(By.xpath("//input[@id='idSIButton9' and @value='Sign in']")).click();

		login.ImplicitWaitTime();
		driver.findElement(By.xpath("//input[@id='KmsiCheckboxField']")).click();
		driver.findElement(By.xpath("//input[@id='idSIButton9' and @value='Yes']")).click();
		Reporter.log("User logged in");

	}

	@Test(priority = 2, dependsOnMethods = "loginWithValidUser")
	public void logout() {
		Reporter.log("Logging to squirrelcloudtest.com");
		loginWithValidUser();
		driver.findElement(By.cssSelector("a.pointer.logout-button")).click();
		Reporter.log("User logged out");
	}

	@Test(priority = 3)
	public void loginWithInvalidUsername() {
		Reporter.log("Logging to squirrelcloudtest.com");
		driver.get(NAVIGATE_TO_URL);

		login.ImplicitWaitTime();

		driver.findElement(By.xpath("//input[@id='i0116']")).sendKeys(INVALID_USERNAME);
		login.ExplicitWaitTime("//input[@id='idSIButton9']");
		String errorText = driver.findElement(By.xpath("//div[@id='usernameError']")).getText();
		String expectedErrorText = "This username may be incorrect. Make sure you typed it correctly. Otherwise, contact your admin.";
		Assert.assertEquals(errorText, expectedErrorText);
	}
	
	@Test(priority = 4)
	public void loginWithInvalidPassword() {
		Reporter.log("Logging to "+NAVIGATE_TO_URL);
		driver.get(NAVIGATE_TO_URL);
		login.ImplicitWaitTime();

		driver.findElement(By.xpath("//input[@id='i0116']")).sendKeys(USERNAME);
		login.ExplicitWaitTime("//input[@id='idSIButton9']");

		login.ImplicitWaitTime();
	
		driver.findElement(By.xpath("//input[@id='i0118']")).sendKeys(INVALID_PASSWORD);
		login.ExplicitWaitTime("//input[@id='idSIButton9' and @value='Sign in']");
		
		String errorText = driver.findElement(By.cssSelector("div#passwordError")).getText();
		String expectedErrorText = "Your account or password is incorrect. If you don't remember your password, reset it now.";
		Assert.assertEquals(errorText, expectedErrorText);
	}

}
