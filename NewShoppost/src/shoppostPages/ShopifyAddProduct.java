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

public class ShopifyAddProduct {
	private int DRIVER_WAIT = 10;
	private WebDriver _driver;
	
	@FindBy (id = "sidebar")
	private WebElement sidebarMenu;
	
	@FindBy (linkText = "Back To Product")
	private WebElement backToProductBtn;
	
	@FindBy (id = "tabNavLink")
	private WebElement linkTab;
	
	

	@FindBy (xpath = "//div[@id='sidebar']/nav[1]/ol[1]/li/a[contains(@class,'products')]")
	private WebElement sidebarProducts;
	
	@FindBy (xpath = "//div[@id='sidebar']/nav[1]/ol[1]/li/a[contains(@class,'dashboard')]")
	private WebElement sidebarDashboard;
	
	@FindBy (xpath = "//div[@id='sidebar']/nav[1]/ol[2]/li/a[contains(@class,'apps')]")
	private WebElement sidebarApps;
	
	@FindBy (xpath = "//div[@class='header-right']/input[contains(@class,'btn-primary')]")
	private WebElement saveProductBtn;
	
	@FindBy (id = "product-name")
	private WebElement productTitle;
	@FindBy (id = "product-body-html_iframecontainer")
	private WebElement productDescriptionFrame;
	@FindBy (xpath = "//body[@id='tinymce']")    //inside an iFrame
	private WebElement shopifyProductDescription;
	@FindBy (id = "product-price")
	private WebElement priceField;
	@FindBy (id = "product-weight")
	private WebElement weightField;
	@FindBy (id = "product-upload-images")
	private WebElement imageUploadBtn;
	@FindBy (className = "app-flow")
	private WebElement doneSave;
	
	
	
	@FindBy (xpath = "//li[@class='apps-dropdown']/a[@class='btn']")
	private WebElement ellipsisButton;
	//@FindBy (xpath = "//div[@class='app-icon-list']/a")
	@FindBy (linkText = "Shoppost")
	private WebElement shoppostIt;
	
	@FindBy (name = "app-iframe")
	private WebElement outerFrame;
	
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
	

	public ShopifyAddProduct (WebDriver driver) {
		_driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, DRIVER_WAIT);
		PageFactory.initElements(finder, this);
	}
	

	
	public void toLink() {
		linkTab.click();
	}
	public void toProductList() {
		sidebarProducts.click();
	}
	public void inputTitle(String title) throws Exception {
		System.out.println("title: "+title);
		productTitle.click();
		productTitle.sendKeys(title);
		Thread.sleep(1000);
	}
	public void inputDescription(String descrip) {
		_driver.switchTo().defaultContent();
		_driver.switchTo().frame(_driver.findElement(By.id("product-body-html_ifr")));
		shopifyProductDescription.click();
		shopifyProductDescription.sendKeys(descrip);
		_driver.switchTo().defaultContent();
	}
	public void inputPrice(String price) {
		priceField.click();
		priceField.sendKeys(price);
	}
	public void inputWeight(String weight) {
		weightField.click();
		weightField.sendKeys(weight);
	}
	public void uploadImage(String url) throws Exception {
		imageUploadBtn.sendKeys(url);
		Thread.sleep(3000);
	}
	
	public void saveProduct() throws Exception {
		saveProductBtn.click();
		Thread.sleep(1000);
		(new WebDriverWait(_driver,10)).until(ExpectedConditions.visibilityOf(doneSave));   //wait for 'done save' message
		//(new WebDriverWait(_driver,10)).until(ExpectedConditions.elementToBeClickable(ellipsisButton));   //wait for ellipsis button
	}
	public void toApps() {
		sidebarApps.click();
	}
	public void toDashboard() {
		sidebarDashboard.click();
	}
	
	public void outerFrame() {
		(new WebDriverWait(_driver,10)).until(ExpectedConditions.presenceOfElementLocated(By.className("app-iframe")));
	}
	public void innerFrame() {
		_driver.switchTo().defaultContent();
		_driver.switchTo().frame(_driver.findElement(By.className("app-iframe")));
		(new WebDriverWait(_driver,10)).until(ExpectedConditions.presenceOfElementLocated(By.id("shoppostframe")));
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
