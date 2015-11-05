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
import org.openqa.selenium.WebElement;

public class SemanticPage {
	private int DRIVER_WAIT = 15;
	private WebDriver _driver;
	
	@FindBy (className = "productTitle")
	private WebElement productTitleWM;   //Walmart product
	//@FindBy (xpath = "//div[@id='WM_Price']/div/div")
	@FindBy (className = "bigPriceText1")
	private WebElement productPriceWM;
	
	
	@FindBy (id = "sku-title")
	private WebElement productTitleBB;
	@FindBy (className = "item-price")
	private WebElement productPriceBB;
	@FindBy (id = "long-description")
	private WebElement productDescriptionBB;
	
	
	

	public SemanticPage (WebDriver driver) {
		_driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, DRIVER_WAIT);
		PageFactory.initElements(finder, this);
	}
	
	public String getProductTitle(String store) {
		String _title = "";
		switch(store) {
		case "BB" :
			_title = productTitleBB.getText();
			break;
		case "WM" :
			_title = productTitleWM.getText();
			break;
		}
		return _title;
	}
	public String getProductPrice(String store) {
		String _price = "";
		switch(store) {
		case "BB" :
			_price = productPriceBB.getText();
			break;
		case "WM" :
			_price = productPriceWM.getText();
			break;
		}
		return _price;
	}
	
	
	


}
