package shoppostTestSupport;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import shoppostPages.LandingPage;
import shoppostPages.Shopify;


public class ProductOptions {
	  private WebDriver driver;
	  private String _browser, _name;
	  private WebDriverWait wait;
	  private List<String> _option1Values = new ArrayList<String>();
	  private List<String> _option2Values = new ArrayList<String>();
	  private List<String> _option3Values = new ArrayList<String>();
	  private String _optionValues[] = new String[3];
	  

	  
	  private HashMap _hm;
	   
	  private Shopify shopify;
	  private LandingPage landingPage;
	  
	  
	  public ProductOptions(WebDriver _driver) {
			driver = _driver;
			shopify = new Shopify(driver);
			landingPage = new LandingPage(driver);
		} 
	

}
