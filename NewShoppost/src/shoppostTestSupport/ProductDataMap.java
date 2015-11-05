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


public class ProductDataMap {
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
  
  
  public ProductDataMap(WebDriver _driver) {
		driver = _driver;
		shopify = new Shopify(driver);
		landingPage = new LandingPage(driver);
	} 

 
  public HashMap buildShopifyMap(String n) throws Exception {  //collect analytics data from dashboard
	  shopify = new Shopify(driver);
	  _name = shopify.openProduct(n);
	  shopify = new Shopify(driver);  //instantiate the pageOject 
	  	_hm = new HashMap();
	  				                                         
	  	Thread.sleep(1000);
	  	
	  	
	  	
	  	_hm.put("productName", _name);  //String product name for nth product
	  	shopify = new Shopify(driver);  //re-instantiate class
	  	
	  	driver.switchTo().frame(driver.findElement(By.id("product-body-html_ifr")));
	  	_hm.put("productDescription", shopify.getProductDescription());  //String nth prod description
	  	driver.switchTo().defaultContent();
	  	
	  	_hm.put("variantCount", shopify.getVariantCount()); //int
	  	_hm.put("optionCount", shopify.getOptionCount());  //int
	  	_hm.put("option1Name", shopify.getFirstOptionName());  //String
	  	_hm.put("option2Name", shopify.getSecondOptionName());  //String
	  	//_hm.put("option3Name", shopify.getThirdOptionName());  //String
	  	
	  	
	  	for(int i=0; i<shopify.getOption1Count();i++) {
	  		_option1Values.add(shopify.getOption1value(i));
	  	}
	  	for(int k=0; k<shopify.getOption2Count();k++) {
	  		_option2Values.add(shopify.getOption2value(k));
	  	}
	  	_hm.put("option1Values", _option1Values);  //List<String>
	  	_hm.put("option2Values", _option2Values);  //List<String>
	  	
	  	_hm.put("variant1Price", shopify.getVariantOnePrice());
	  	_hm.put("variant2Price", shopify.getVariantTwoPrice());
	  	_hm.put("variant3Price", shopify.getVariantThreePrice());
	  	
	  	return _hm;
	
	}
  
  public HashMap buildSemanticMap(String n) throws Exception {  //collect analytics data from dashboard
	  shopify = new Shopify(driver);
	  _name = shopify.openProduct(n);
	  shopify = new Shopify(driver);  //instantiate the pageOject 
	  	_hm = new HashMap();
	  				                                         
	  	Thread.sleep(1000);
	  	
	  	
	  	
	  	_hm.put("productName", _name);  //String product name for nth product
	  	shopify = new Shopify(driver);  //re-instantiate class
	  	
	  	driver.switchTo().frame(driver.findElement(By.id("product-body-html_ifr")));
	  	_hm.put("productDescription", shopify.getProductDescription());  //String nth prod description
	  	driver.switchTo().defaultContent();
	  	
	  	_hm.put("variantCount", shopify.getVariantCount()); //int
	  	_hm.put("optionCount", shopify.getOptionCount());  //int
	  	_hm.put("option1Name", shopify.getFirstOptionName());  //String
	  	_hm.put("option2Name", shopify.getSecondOptionName());  //String
	  	//_hm.put("option3Name", shopify.getThirdOptionName());  //String
	  	
	  	
	  	for(int i=0; i<shopify.getOption1Count();i++) {
	  		_option1Values.add(shopify.getOption1value(i));
	  	}
	  	for(int k=0; k<shopify.getOption2Count();k++) {
	  		_option2Values.add(shopify.getOption2value(k));
	  	}
	  	_hm.put("option1Values", _option1Values);  //List<String>
	  	_hm.put("option2Values", _option2Values);  //List<String>
	  	
	  	_hm.put("variant1Price", shopify.getVariantOnePrice());
	  	_hm.put("variant2Price", shopify.getVariantTwoPrice());
	  	_hm.put("variant3Price", shopify.getVariantThreePrice());
	  	
	  	return _hm;
	
	}
  public HashMap buildLandingPageMap() throws Exception {  //collect analytics data from dashboard
	    landingPage = new LandingPage(driver);
	    _hm = new HashMap();
	  				                                         
	  	Thread.sleep(1000);
	  	
	  	landingPage.viewMerchantProfile();   //open full profile
	  	_hm.put("productName", landingPage.getProductName());  //String product name for nth product
	  	_hm.put("productDescription", landingPage.getProductDescription());  //String nth prod description
	  	//_hm.put("variantCount", shopify.getVariantCount()); //int
	  	_hm.put("optionCount", landingPage.getOptionCount());  //int
	  	System.out.println("optionCount: "+_hm.get("optionCount"));
	  	switch((int) _hm.get("optionCount")) {
	  	case 1:
	  		_hm.put("option1Name", landingPage.getFirstOptionName());  //String
	  		_hm.put("option1ValueCt", landingPage.getOption1ValueCt());
	  		for(int i=0; i<landingPage.getOption1ValueCt();i++) {  //
		  		_option1Values.add(landingPage.getOption1Value(i));
		  	}
	  		_hm.put("option1Values", _option1Values);   // list of option one values
	  		break;
	  	case 2:
	  		_hm.put("option1Name", landingPage.getFirstOptionName());  //String
		  	_hm.put("option2Name", landingPage.getSecondOptionName());  //String
		  	_hm.put("option1ValueCt", landingPage.getOption1ValueCt());
		  	_hm.put("option2ValueCt", landingPage.getOption2ValueCt());
		  	for(int i=0; i<landingPage.getOption1ValueCt();i++) {  //
		  		_option1Values.add(landingPage.getOption1Value(i));
		  	}
	  		_hm.put("option1Values", _option1Values);   // list of option one values
	  		for(int i=0; i<landingPage.getOption2ValueCt();i++) {  //
		  		_option2Values.add(landingPage.getOption2Value(i));
		  	}
	  		_hm.put("option2Values", _option2Values);   // list of option two values
	  		break;
	  	case 3:
	  		_hm.put("option1Name", landingPage.getFirstOptionName());  //String
	  		_hm.put("option2Name", landingPage.getSecondOptionName());  //String
		  	_hm.put("option3Name", landingPage.getThirdOptionName());  //String
		  	_hm.put("option1ValueCt", landingPage.getOption1ValueCt());
		  	_hm.put("option2ValueCt", landingPage.getOption2ValueCt());
		  	_hm.put("option3ValueCt", landingPage.getOption3ValueCt());
		  	landingPage.clickOption(1);
		  	Thread.sleep(1000);
		  	for(int i=0; i<landingPage.getOption1ValueCt();i++) {  //
		  		System.out.println("option1 values: "+landingPage.getOption1Value(i));
		  		_option1Values.add(landingPage.getOption1Value(i));
		  	}
	  		_hm.put("option1Values", _option1Values);   // list of option one values
	  		landingPage.clickOption(2);
	  		Thread.sleep(1000);
	  		System.out.println("first option, first value: "+_option1Values.get(0));
	  		for(int i=0; i<landingPage.getOption2ValueCt();i++) {  //
		  		_option2Values.add(landingPage.getOption2Value(i));
		  	}
	  		_hm.put("option2Values", _option2Values);   // list of option two values
	  		landingPage.clickOption(3);
	  		Thread.sleep(1000);
	  		for(int i=0; i<landingPage.getOption3ValueCt();i++) {  //
		  		_option3Values.add(landingPage.getOption3Value(i));
		  	}
	  		_hm.put("option3Values", _option3Values);   // list of option three values
	  		break;
	  	}
	  	//_hm.put("variant1Price", shopify.getVariantOnePrice());
	  	//_hm.put("variant2Price", shopify.getVariantTwoPrice());
	  	//_hm.put("variant3Price", shopify.getVariantThreePrice());
	  	
	  	_hm.put("merchantName", landingPage.getMerchantName());
	  	_hm.put("merchantWebsite", landingPage.getMerchantWebsite());
	  	_hm.put("merchantEmail", landingPage.getMerchantEmail());
	  	_hm.put("merchantPhone", landingPage.getMerchantPhone());
	  	
	  	return _hm;
	
	}


}