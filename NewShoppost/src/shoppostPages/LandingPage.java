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

public class LandingPage {
	private int DRIVER_WAIT = 15;
	
	@FindBy (xpath = "//div[contains(@class, 'media-gallery')]")
	private WebElement mediaGallery;
	@FindBy (id ="prevMedia")
	private WebElement prevMedia;
	@FindBy (id ="nextMedia")
	private WebElement nextMedia;
	@FindBy (id ="previewMedia")
	private WebElement previewMedia;
	@FindBy (id ="closeMedia")
	private WebElement closeMedia;
	@FindBy (xpath = "//div[@id='store']/div/div[2]/h1")
	private WebElement productName;
	@FindBy (id = "buyButton")
	private WebElement buyButton;
	@FindBy (id = "shareButton")
	private WebElement shareButton;
	
	@FindBy (xpath ="//ul[@class='options']/li")
	private List<WebElement> options;
	@FindBy (id = "option0Button")
	private WebElement firstOptionBtn;
	@FindBy (id = "option1Button")
	private WebElement secondOptionBtn;
	@FindBy (id = "option2Button")
	private WebElement thirdOptionBtn;
	@FindBy (xpath = "//div[@id='option0']/button/div/span[1]")
	private WebElement option1Name;
	@FindBy (xpath = "//div[@id='option1']/button/div/span[1]")
	private WebElement option2Name;
	@FindBy (xpath = "//div[@id='option2']/button/div/span[1]")
	private WebElement option3Name;
	
	@FindBy (xpath = "//div[@id='option0']/ul/li")
	private List<WebElement> option1Values;
	@FindBy (xpath = "//div[@id='option1']/ul/li")
	private List<WebElement> option2Values;
	@FindBy (xpath = "//div[@id='option2']/ul/li")
	private List<WebElement> option3Values;
	
	@FindBy (id = "productDescription")
	private WebElement prodDescription;
	@FindBy (id = "merchantDesktopLogo")
	private WebElement merchantLogo;
	@FindBy (id = "merchantDesktopName")
	private WebElement merchantName;
	@FindBy (id = "merchantDesktopWebsite")
	private WebElement merchantWebsite;
	@FindBy (id = "merchantDesktopEmail")
	private WebElement merchantEmail;
	@FindBy (id = "merchantDesktopPhone")
	private WebElement merchantPhone;
	@FindBy (id = "merchantDesktopDescriptionShow")
	private WebElement viewProfile;
	@FindBy (id = "merchantDesktopDescriptionHide")
	private WebElement descriptionHide;
	@FindBy (id = "merchantDesktopDescription")
	private WebElement merchantDescription;
	@FindBy (id = "shareFacebook")
	private WebElement facebookIcon;
	@FindBy (id = "shareTwitter")
	private WebElement twitterIcon;
	@FindBy (id = "sharePinterest")
	private WebElement pinterestIcon;
	@FindBy (id = "shareGooglePlus")
	private WebElement googleIcon;
	@FindBy (id = "embedCode")
	private WebElement embedCode;
	@FindBy (id = "shareUrl")
	private WebElement shareUrl;
	@FindBy (id = "closeShare")
	private WebElement closeShare;
	
	public LandingPage (WebDriver driver) {
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, DRIVER_WAIT);
		PageFactory.initElements(finder, this);
	}

	public boolean isMediaGalleryVisible() {
		return mediaGallery.isDisplayed();
	}
	public void viewMerchantProfile() {
		viewProfile.click();
	}
	public String getProductName() {
		return productName.getText();
	}
	public String getProductDescription() {
		return prodDescription.getText();
	}
	public void openSharing() {
		shareButton.click();
	}
	public void shareFacebook() {
		facebookIcon.click();
	}
	public void shareTwitter () {
		twitterIcon.click();
	}
	public String getLPUrl() {
		return shareUrl.getAttribute("value");
	}
	public void sharePinterest() {
		pinterestIcon.click();
	}
	public void shareGooglePlus() {
		googleIcon.click();
	}
	public String getMerchantName() {
		return merchantName.getText();
	}
	public String getMerchantWebsite() {
		return merchantWebsite.getText();
	}
	public String getMerchantEmail() {
		return merchantEmail.getText();
	}
	public String getMerchantPhone() {
		return merchantPhone.getText();
	}
	public int getOptionCount() {
		return options.size();
	}
	
	public String getFirstOptionName() {
		return option1Name.getText();
	}
	public int getOption1ValueCt() {
		return option1Values.size();
	}
	public void clickOption(int k) {
		switch(k) {
		case 1:
			firstOptionBtn.click();
			break;
		case 2:
			secondOptionBtn.click();
			break;
		case 3:
			thirdOptionBtn.click();
			break;
		}
		
	}
	public String getOption1Value(int m) {
		return option1Values.get(m).getText();
	}
	public String getSecondOptionName() {
		return option2Name.getText();
	}
	public int getOption2ValueCt() {
		return option2Values.size();
	}
	public String getOption2Value(int m) {
		return option2Values.get(m).getText();
	}
	public String getThirdOptionName() {
		return option3Name.getText();
	}
	public int getOption3ValueCt() {
		return option3Values.size();
	}
	public String getOption3Value(int m) {
		return option3Values.get(m).getText();
	}
	
	

}
