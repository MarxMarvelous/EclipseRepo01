package shoppostPages;

import java.util.List;

import org.openqa.selenium.By;
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



public class Shopify {
	
	private int DRIVER_WAIT = 60;
	private WebDriver _driver;
	
	@FindBy (id = "sidebar")
	private WebElement sidebarMenu;
	
	@FindBy (linkText = "Back To Product")
	private WebElement backToProductBtn;
	
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
	
	

	@FindBy (xpath = "//div[@id='NavDrawer']/nav[1]/div[1]/ol[1]/li[10]/a[contains(@class,'next-nav__link')]")
	private WebElement sidebarProducts;
	
	@FindBy (xpath = "//div[@id='sidebar']/nav[1]/ol[1]/li/a[contains(@class,'dashboard')]")
	private WebElement sidebarDashboard;
	
	@FindBy (xpath = "//div[@id='sidebar']/nav[1]/ol[3]/li[1]/a[contains(@class,'apps')]")
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
	
	@FindBy (xpath = "//li[@class='apps-dropdown']/a[@class='btn']")
	private WebElement ellipsisButton;
	@FindBy (xpath = "//div[contains(@class,'dropdown')]/ul/li[2]/div[@class='app-icon-list']/a")
	private WebElement shoppostIt;
	@FindBy (xpath = "//div[contains(@class,'first-section')]/div/div/p/a[contains(@class,'btn-primary')]")
	private WebElement visitAppStore;
	@FindBy (xpath = "//div[@class='app-card']/a")
	private WebElement shoppostAppCard;
	@FindBy (xpath = "//div[@class='app-details']/ul[@class='segmented']/li[1]")
	private WebElement appCardMenu;
	@FindBy (xpath = "//div[@class='app-details']/ul[@class='segmented']/li/div/ul/li[4]")
	private WebElement removeApp;
	@FindBy (xpath = "//input[contains(@class,'btn-destroy-no-hover')]")
	private WebElement finalDelete;
	@FindBy (id = "q")
	private WebElement searchField;
	@FindBy (xpath = "//div[@class='search-widget']/div/ul")
	private WebElement selectShoppost;
	@FindBy (xpath = "//a[contains(@class,'btn-install')]")
	private WebElement getApp;
	@FindBy (xpath = "//form/input[contains(@class,'btn-primary')]")
	private WebElement installApp;
	@FindBy (name = "app-iframe")
	private WebElement frame;
	
	@FindBy (xpath = "//body[@id='tinymce']")    //inside an iFrame
	private WebElement shopifyProductDescription;
	@FindBy (id = "productName")
	private WebElement productName;
	@FindBy (id = "productUrl")
	private WebElement productUrl;
	
	@FindBy (xpath = "//table[@id='product-inner-variants']/tbody/tr")
	private List<WebElement> productVariants;
	
	@FindBy (xpath = "//div[@class='variant-selector']/ul")
	private List<WebElement> productOptions;  //option count = size -1
	@FindBy (xpath = "//div[@class='variant-selector']/ul[2]/li")
	private List<WebElement> option1values;
	@FindBy (xpath = "//div[@class='variant-selector']/ul[3]/li")
	private List<WebElement> option2values;
	
	@FindBy (id = "edit-option-name-1")
	private WebElement firstOptionName;
	@FindBy (id = "edit-option-name-2")
	private WebElement secondOptionName;
	
	@FindBy (xpath = "//table[@id='product-inner-variants']/tbody/tr[1]/td[contains(@class,'option-1')]/input")
	private WebElement variantOneOptionOneName;
	@FindBy (xpath = "//table[@id='product-inner-variants']/tbody/tr[1]/td[contains(@class,'option-2')]/input")
	private WebElement variantOneOptionTwoName;
	

	@FindBy (xpath = "//table[@id='product-inner-variants']/tbody/tr[1]/td[contains(@class,'price')]/input[1]")
	private WebElement variantOnePrice;
	@FindBy (xpath = "//table[@id='product-inner-variants']/tbody/tr[1]/td[contains(@class,'price')]/input[2]")
	private WebElement variantOnePriceInput;
	@FindBy (xpath = "//table[@id='product-inner-variants']/tbody/tr[2]/td[contains(@class,'price')]/input[1]")
	private WebElement variantTwoPrice;
	@FindBy (xpath = "//table[@id='product-inner-variants']/tbody/tr[2]/td[contains(@class,'price')]/input[2]")
	private WebElement variantTwoPriceInput;
	@FindBy (xpath = "//table[@id='product-inner-variants']/tbody/tr[3]/td[contains(@class,'price')]/input[1]")
	private WebElement variantThreePrice;
	@FindBy (xpath = "//table[@id='product-inner-variants']/tbody/tr[3]/td[contains(@class,'price')]/input[2]")
	private WebElement variantThreePriceInput;
	
	@FindBy (xpath = "//table[@id='product-inner-variants']/tbody/tr[2]/td[contains(@class,'option-1')]")
	private WebElement variantTwoOptionOneName;
	@FindBy (xpath = "//table[@id='product-inner-variants']/tbody/tr[2]/td[contains(@class,'option-2')]")
	private WebElement variantTwoOptionTwoName;
	
	
	
	
	@FindBy (className = "account-overview-user-details")
	private WebElement userPullDown;
	@FindBy (linkText = "Log out")
	private WebElement logout;
	

	public Shopify (WebDriver driver ) {
		_driver = driver;
		//_appsURL = appsURL;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, DRIVER_WAIT);
		PageFactory.initElements(finder, this);
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
		_driver.switchTo().defaultContent();
		_driver.switchTo().frame(_driver.findElement(By.className("app-iframe")));
		shareFacebook.click();
	}
	public void toApps(String appurl) {
		String _appsURL = appurl;
		//sidebarApps.click();
		_driver.get(_appsURL);
	}
	public void toDashboard() {
		sidebarDashboard.click();
	}
	public boolean appPresent() {
		return shoppostAppCard.isDisplayed();
	}
	public void visitAppStore() {
		visitAppStore.click();
	}
	public void goToApp() throws Exception {
		searchField.click();
		searchField.sendKeys("shoppost");
		Thread.sleep(2000);
		selectShoppost.click();
	}
	public void getApp() {
		getApp.click();
	}
	public void installApp() {
		installApp.click();
	}
	public void frame() {
		(new WebDriverWait(_driver,60)).until(ExpectedConditions.presenceOfElementLocated(By.className("app-iframe")));
	}
	
	public void appCardMenuRemove() throws Exception {
		appCardMenu.click();
		Thread.sleep(1000);
		removeApp.click();
	}
	public void finalDelete() {
		finalDelete.click();
	}
	public boolean appGone() {
		return visitAppStore.isDisplayed();
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
	public void clickEllipsis() {
		ellipsisButton.click();
	}
	public void shoppostProduct() {
		shoppostIt.click();
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
	public int getOptionCount() {
		return (productOptions.size() - 1);
	}
	public String getFirstOptionName() {
		return firstOptionName.getText();
	}
	public String getSecondOptionName() {
		return secondOptionName.getText();
	}
	public int getVariantCount() {
		return productVariants.size();
	}
	public String getVariantOneOptionOneName() {
		return variantOneOptionOneName.getText();
	}
	public String getVariantOneOptionTwoName() {
		return variantOneOptionTwoName.getText();
	}
	public String getVariantOnePrice() {
		String price;
		price = "10.00";
		variantOnePrice.click();
		variantOnePriceInput.sendKeys(price);
		return price;
	}
	public String getVariantTwoPrice() {
		String price;
		price = "10.00";
		variantTwoPrice.click();
		variantTwoPriceInput.sendKeys(price);
		return price;
	}
	public String getVariantThreePrice() {
		String price;
		price = "10.00";
		variantThreePrice.click();
		variantThreePriceInput.sendKeys(price);
		return price;
	}
	public String getVariantTwoOptionOneName() {
		return variantTwoOptionOneName.getText();
	}
	public String getVariantTwoOptionTwoName() {
		return variantTwoOptionTwoName.getText();
	}
	public int getOption1Count() {
		return option1values.size();
	}
	public int getOption2Count() {
	return option2values.size();
	}
	public String getOption1value(int m) {
		return option1values.get(m).getText();
	}
	public String getOption2value(int n) {
		return option2values.get(n).getText();
	}
	public void userPullDown() {
		userPullDown.click();
		
	}
	public void logout() {
		logout.click();
	}
	public void goBackToProduct() {
		backToProductBtn.click();
	}

}
