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

public class MobileLandingPage extends LandingPage {
	private int DRIVER_WAIT = 15;
	
	@FindBy (id = "productMobileName")
	private WebElement productName;
	@FindBy (id = "merchantMobileLogo")
	private WebElement merchantLogo;
	@FindBy (id = "merchantMobileName")
	private WebElement merchantName;
	@FindBy (id = "merchantMobileWebsite")
	private WebElement merchantWebsite;
	@FindBy (id = "merchantMobileEmail")
	private WebElement merchantEmail;
	@FindBy (id = "merchantMobilePhone")
	private WebElement merchantPhone;
	@FindBy (id = "merchantMobileDescriptionShow")
	private WebElement descriptionShow;
	@FindBy (id = "merchantMobileDescriptionHide")
	private WebElement descriptionHide;
	@FindBy (id = "merchantMobileDescription")
	private WebElement merchantDescription;
	
	
	
	

	public MobileLandingPage (WebDriver driver) {
		super(driver);
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, DRIVER_WAIT);
		PageFactory.initElements(finder, this);
	}

	

}
