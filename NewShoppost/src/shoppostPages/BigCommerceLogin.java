package shoppostPages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.WebElement;

public class BigCommerceLogin {
	private int DRIVER_WAIT = 15;
	
	@FindBy (id = "subdomain")
	private WebElement storeNameField;

	@FindBy (id = "user_email")
	private WebElement emailField;
	
	@FindBy (id = "user_password")
	private WebElement passwordField;
	
	@FindBy (id = "remember_email")
	private WebElement rememberEmailCheck;
	
	@FindBy (xpath = "//input[contains(@class,'login-form-button')]")
	private WebElement loginButton;
	
	@FindBy (xpath = "//div[@id='stores']/a[1]")
	private WebElement prodStore;
	
	
	

	public BigCommerceLogin (WebDriver driver) {
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, DRIVER_WAIT);
		PageFactory.initElements(finder, this);
	}

	
	
	
	public void shopSignin(String username, String password) throws Exception {
		emailField.sendKeys(username);
		passwordField.sendKeys(password);
		loginButton.click();
		Thread.sleep(3000);
		prodStore.click();
	}

}
