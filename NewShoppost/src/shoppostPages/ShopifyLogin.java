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

public class ShopifyLogin {
	private int DRIVER_WAIT = 15;
	
	@FindBy (id = "subdomain")
	private WebElement storeNameField;

	@FindBy (id = "login")
	private WebElement emailField;
	
	@FindBy (id = "password")
	private WebElement passwordField;
	
	@FindBy (id = "remember_checkbox")
	private WebElement rememberCheck;
	
	@FindBy (xpath = "//input[@class='dialog-btn']")
	private WebElement loginButton;
	
	@FindBy (id = "login-input")
	private WebElement shopUsername;
	@FindBy (id = "password")
	private WebElement shopPassword;
	@FindBy (xpath = "//div[@class='actions']/input")
	private WebElement shopLoginBtn;
	

	public ShopifyLogin (WebDriver driver) {
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, DRIVER_WAIT);
		PageFactory.initElements(finder, this);
	}

	
	
	public void login(String storeName, String email, String password) {
		storeNameField.sendKeys(storeName);
		emailField.sendKeys(email);
		passwordField.sendKeys(password);
		loginButton.click();
	}
	public void shopSignin(String username, String password) {
		shopUsername.sendKeys(username);
		shopPassword.sendKeys(password);
		loginButton.click();
	}

}
