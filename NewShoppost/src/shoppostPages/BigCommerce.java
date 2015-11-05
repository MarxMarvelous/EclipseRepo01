package shoppostPages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;

public class BigCommerce {
	private int DRIVER_WAIT = 20;
	private WebDriver _driver;
	
	//@FindBy (xpath = "//a[@class ='iac-app-logo']/img[@alt='shoppost']")
	@FindBy (xpath = "//ul[@class ='iac-installed-apps']/li[3]/a")
	private WebElement appIcon;
	
	@FindBy (linkText = "My Apps")
	private WebElement myApps;
	
	@FindBy (id = "sidebar")
	private WebElement sidebarMenu;
	
	@FindBy (xpath = "//div[@class='app-filters']/div[3]/button")   //[contains(@class,'apps-search-button')]
	private WebElement appSearchButton;
	
	@FindBy (id = "tabNavLink")
	private WebElement linkTab;
	
	@FindBy (id = "tabNavEmbed")
	private WebElement modalEmbedTab;
	
	@FindBy (id = "shareFacebook")
	private WebElement shareFacebook;
	
	@FindBy (id = "shareTwitter")
	private WebElement shareTwitter;
	
	@FindBy (id = "sharePinterest")
	private WebElement sharePinterest;
	
	

	@FindBy (xpath = "//div[@id='sidebar']/nav[1]/ol[1]/li/a[contains(@class,'products')]")
	private WebElement sidebarProducts;
	
	@FindBy (xpath = "//div[@id='sidebar']/nav[1]/ol[1]/li/a[contains(@class,'dashboard')]")
	private WebElement sidebarDashboard;
	
	@FindBy (id = "iac-home")
	private WebElement sidebarApps;
	
	@FindBy (xpath = "//div[@class='header-right']/a[contains(@class,'btn-primary')]")
	private WebElement addProductBtn;
	
	@FindBy (xpath = "//table[@id='all-products']/tbody/tr[1]/td[@class='name']/a")
	private WebElement firstProductName;
	@FindBy (xpath = "//table[@id='all-products']/tbody/tr[2]/td[@class='name']/a")
	private WebElement secondProductName;
	@FindBy (xpath = "//table[@id='all-products']/tbody/tr[3]/td[@class='name']/a")
	private WebElement thirdProductName;
	@FindBy (xpath = "//table[@id='all-products']/tbody/tr[4]/td[@class='name']/a")
	private WebElement fourthProductName;
	
	@FindBy (linkText = "App Store")
	private WebElement appStore;
	
	@FindBy (xpath = "//div[@class='iac-app-cards']/div/div/div[3]")
	private WebElement shoppostSingleClick;
	@FindBy (xpath = "//h3[contains(.,'shoppost')]/following-sibling::div/div")
	private WebElement appCardGear;
	@FindBy (linkText = "uninstall")
	private WebElement uninstallApp;
	
	@FindBy (linkText = "Uninstall")
	private WebElement uninstall;
	
	@FindBy (xpath = "//input[contains(@class,'btn-destroy-no-hover')]")
	private WebElement finalDelete;
	@FindBy (xpath = "//div[contains(@class,'apps-search')]/input[contains(@class,'ng-pristine')]")
	private WebElement searchField;
	
	@FindBy (xpath = "//a[contains(@class,'btn-install')]")
	private WebElement getApp;
	@FindBy (linkText = "Install")
	private WebElement installButton;
	@FindBy (id = "confirm-button")
	private WebElement confirm;
	
	@FindBy (name = "app-store-iframe")
	private WebElement appStoreFrame;
	
	@FindBy (xpath = "//body[@id='tinymce']")    //inside an iFrame
	private WebElement shopifyProductDescription;
	@FindBy (id = "productName")
	private WebElement productName;
	@FindBy (id = "productUrl")
	private WebElement productUrl;
	
	@FindBy (xpath = "//table[@id='product-inner-variants']/tbody/tr")
	private List<WebElement> productVariants;
	
	
	
	@FindBy (className = "account-overview-user-details")
	private WebElement userPullDown;
	@FindBy (linkText = "Log out")
	private WebElement logout;
	

	public BigCommerce (WebDriver driver) {
		_driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, DRIVER_WAIT);
		PageFactory.initElements(finder, this);
	}
	public void appStoreFrame() {
		(new WebDriverWait(_driver,60)).until(ExpectedConditions.presenceOfElementLocated(By.id("app-store-iframe")));
	}

	
	public void toLink() {
		linkTab.click();
	}
	public void toProductList() {
		sidebarProducts.click();
	}
	public void addProduct() {
		addProductBtn.click();
	}
	public void shareFacebook() {
		shareFacebook.click();
	}
	public void toApps() {
		sidebarApps.click();
	}
	public void toMyApps() throws Exception {
		myApps.click();
	}	
	
	public void toDashboard() {
		sidebarDashboard.click();
	}
	public boolean appPresent() {
		return appIcon.isDisplayed();
	}
	public void visitAppStore() {
		appStore.click();
	}
	public void openApp() throws Exception {
		Thread.sleep(10000);
		appIcon.click();
	}
	public void findAppInStore() throws Exception {
		//System.out.println("a");
		appSearchButton.click();
		//System.out.println("b");
		Thread.sleep(3000);
		//searchField.click();
		searchField.sendKeys("shoppost");
		Thread.sleep(1500);
		searchField.sendKeys(Keys.RETURN);
		Thread.sleep(1000);
		searchField.sendKeys(Keys.RETURN);
		System.out.println("c");
		
	
		
		Thread.sleep(5000);
		(new WebDriverWait(_driver,60)).until(ExpectedConditions.elementToBeClickable(shoppostSingleClick));  //wait for shoppost card
		
	}
	public void getApp() {
		getApp.click();
	}
	public void getMyApps() {
		myApps.click();
	}
	public void installApp() throws Exception {
		_driver.switchTo().defaultContent();
		_driver.switchTo().frame(_driver.findElement(By.id("app-store-iframe"))); //look in iframe
		shoppostSingleClick.click();
		Thread.sleep(3000);
		System.out.println("X");
	}
	public void install() {
		(new WebDriverWait(_driver,60)).until(ExpectedConditions.presenceOfElementLocated(By.linkText("Install")));
		installButton.click();
		(new WebDriverWait(_driver,60)).until(ExpectedConditions.presenceOfElementLocated(By.id("confirm-button")));
		confirm.click();
	}
	public void uninstall() {
		uninstall.click();
		System.out.println("E");
		(new WebDriverWait(_driver,60)).until(ExpectedConditions.presenceOfElementLocated(By.linkText("Install")));
	}
	public void appCardMenuRemove() throws Exception {
		appCardGear.click();
		Thread.sleep(1000);
		uninstallApp.click();
		Thread.sleep(1000);
		
	}
	public void finalDelete() {
		finalDelete.click();
	}
	public boolean appGone() {
		return appIcon.isDisplayed();
	}
	
	public String openProduct(String n) {
		String _name = "";
		switch(n) {
		case "1":
			_name = firstProductName.getText();
			firstProductName.click();
			break;
		case "2":
			_name = secondProductName.getText();
			secondProductName.click();
			break;
		case "3":
			_name = thirdProductName.getText();
			thirdProductName.click();
			break;
		case "4":
			_name = fourthProductName.getText();
			fourthProductName.click();
			break;
		}
		return _name;
	}
	
	public String getProductDescription() {
		return shopifyProductDescription.getText();
	}
	public String getProductName() {
		return productName.getText();
	}
	public String getProductUrl() {
		return productUrl.getText();
	}
	public void clickProductUrl() {
		productUrl.click();
	}
	public void userPullDown() {
		userPullDown.click();
		
	}
	public void logout() {
		logout.click();
	}
	

}
