package theTests;

import static org.junit.Assert.*;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;

//import org.apache.commons.io.FileUtils;
//import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
//import org.junit.BeforeClass;
import org.junit.ComparisonFailure;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
//import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
//import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.internal.selenesedriver.TakeScreenshot;
//import org.openqa.selenium.remote.Augmenter;
//import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import shoppostBeans.LPData;
import shoppostBeans.TestData;
import shoppostBeans.SauceLabData;



import shoppostPages.Home;
import shoppostPages.LandingPage;
import shoppostPages.MobileLandingPage;
import shoppostPages.ProductReporter;
import shoppostPages.ShareSetUp;
import shoppostPages.ProductCatalog;
import shoppostPages.ShareModal;
import shoppostPages.Shopify;
import shoppostPages.ShopifyAddProduct;
import shoppostPages.ShopifyLogin;
import shoppostPages.SignUpSignIn;
import shoppostPages.UserAgreement;
import shoppostPages.FBsignIn;
import shoppostPages.AnalyticsReporter;

import shoppostTestSupport.GetDrivers;
import shoppostTestSupport.Global;
import shoppostTestSupport.ProductDataMap;
import shoppostTestSupport.SignOut;
import shoppostTestSupport.ReadTestJSON;
import shoppostTestSupport.ScreenShots;
import shoppostTestSupport.SignUpIn;
import shoppostTestSupport.SocialNetworks;
import shoppostTestSupport.Window;






@RunWith(Parameterized.class)   //this runs the tests serially one browser at a time
//@RunWith(Parallelized.class)    //this will run the tests in parallel and will open a new thread for each browser simultaneously
public class ShoppostShopifySOU {
	
	private String browser;
	private WebDriver driver;
	private static TestData _td;
	private static SauceLabData _sld;
	//private static AcctSetData _asd;
	private static LPData _ltd;
	private String userName, _username, _password, _freshUser, _usernameFB, _shareTitle, _usernameTwitter, _tweet, _initialTweet;
	private String accessCode, _passkey, _passkeyFB, _email, _currUrl, _currUrl_b, mwh_b, _mwh, _testCase, _emailAddress;
	private String _pinTitle, _googPlusPW, _googTitle, _landingPageURL, _appsurl;
	private String _shopifyStorename, _shopifyEmail, _shopifyPassword, _shopifyProductName, _shopifyProductDescription="";
	private DesiredCapabilities capabilities;
	private WebDriverWait wait, wait2;
	private java.awt.Dimension screenSize;
	private Dimension dim;
	private int counter, r, m, _productCount, preCt, postCt, _optionCount, _variantCount,_option1Count,_option2Count;
	private SignUpIn signup;
	private SignUpSignIn signupinPage;
	private UserAgreement userAgreementPage;
	private ProductCatalog catalog;
	private FBsignIn FBone, FBtwo, fbSignIn;
	private AnalyticsReporter analyticsReporter;
	private ShareModal shareModal;
	private ShareSetUp shareSetUp;
	private Home home;
	private LandingPage landingPage;
	private MobileLandingPage mobileLandingPage;
	private ShopifyLogin shopifyLogin;
	private Shopify shopify;
	private SocialNetworks socialNetwork;
	private ProductDataMap productDataMap;
	private ShopifyAddProduct addProduct;
	private String _errorMsg,_option1Name,_option2Name, _usernameShopify, _passwordShopify, _shopifyShopUrl;
	private Window win;
	private Set beforePopup;
	private HashMap _shopify_Hm, _landingPage_Hm;  //full data map
	private List<String> _option1Values = new ArrayList<String>();
	private List<String> _option2Values = new ArrayList<String>();
	private String _variant1Option1Name, _variant1Option2Name, _variant2Option1Name, _variant2Option2Name, _variant1Price, _variant2Price;
	private String _name, _fbPostTitle;
	//private ScreenShot ss;
	
	
	
	
	//public String getBrowser() { return browser; }
	
	@Parameters
	public static Collection<Object[]> browsersStrings(){
		Object[][] browsersStrings = new Object[][]{
				//{"*firefox"}
				{"*chrome"}
				//{"*internetexplorer"}
				//{"*safari"}
				//{"*sauceLabsRemote1"}
				//{"*sauceLabsRemote2"},
				//{"*sauceLabsRemote3"},
				//{"*sauceLabsRemote4"}
				};
		return Arrays.asList(browsersStrings);
	} 
	
	public ShoppostShopifySOU (String browser){
		
		this.browser = browser;
	}
	
	@Before
	public void setUp() throws Exception {  //sets up the driver
		counter = Global.sauceCounter;
		System.out.println("counter is: "+counter);
		
		GetDrivers getDriver = new GetDrivers(this.browser);   //instantiate GetDriver
		driver = getDriver.set();
		
		return;
		
	}
	
	@Test
	  public void test_Verification() throws Exception {
		wait = new WebDriverWait(driver, 10);
		Random rand = new Random();
		Actions move = new Actions(driver);
		signupinPage = new SignUpSignIn(driver);  //this waits for page to load AND initializes the pageFactory proxies
		analyticsReporter = new AnalyticsReporter(driver); 
		catalog = new ProductCatalog(driver);
		shareModal = new ShareModal(driver);
		analyticsReporter = new AnalyticsReporter(driver);
		shareSetUp = new ShareSetUp(driver);
		home = new Home(driver);
		landingPage = new LandingPage(driver);
		mobileLandingPage = new MobileLandingPage(driver);
		fbSignIn = new FBsignIn(driver);
		shopifyLogin = new ShopifyLogin(driver);
		shopify = new Shopify(driver );
		productDataMap = new ProductDataMap(driver);
		addProduct = new ShopifyAddProduct(driver);
		socialNetwork = new SocialNetworks(driver, _td);

		ReadTestJSON.read();
		_td = ReadTestJSON.get_td();
		socialNetwork = new SocialNetworks(driver, _td);
		String _browsName = this.browser;
		_browsName = _browsName.substring(1);
		ScreenShots ss = new ScreenShots(driver, _td, _sld, _ltd, _browsName, m);   //screenshot of platform signup
		
		signup = new SignUpIn(driver, _td);   //signup methods
		SignOut logout = new SignOut(driver);
		Window win = new Window(driver);
		
		_usernameFB = _td.getUsernameFB();
		_appsurl = _td.getVerifyData().getShopifyAcctApps();
		
		//System.out.println(_usernameFB);
		System.out.println("A");
		_landingPageURL = "";
		
		//System.out.println("testLength is: "+TestRunner.getTests().length);
		for (int k=0; k<_td.getVerifyData().getTests().size(); k++) {  //taking one testCase parameter at a time (this by-passes the need for TestRunner
		//for (int k=0; k<TestRunner.getTests().length; k++) {  //taking one testCase parameter at a time from cmd line
	    //for (int k=0; k<1; k++) {   //just a quick test
			//ss.takeTheShot(1, "platform");
  			//_testCase = TestRunner.getTests()[k];
			_testCase = _td.getVerifyData().getTests().get(k);
			//_testCase = "signupValid";
  			switch (_testCase) {
  			
  				case "installShopifyProdApp" :   //
  					signup.helloPlatform(_td.getVerifyData().getInstallShopifyAcct().get(0));
  					Thread.sleep(1000);
  					try {
  						shopifyLogin.shopSignin(_td.getVerifyData().getInstallShopifyAcct().get(1), _td.getVerifyData().getInstallShopifyAcct().get(2));
  					} catch (NoSuchElementException e) {
  						System.out.println("already loggin in");
  					}
  					shopify.toApps(_td.getVerifyData().getShopifyAcctApps());
  					shopify = new Shopify(driver);
  					Thread.sleep(1500);
  					beforePopup = driver.getWindowHandles();   //collect windows and then send them to the change window class
					_currUrl = driver.getCurrentUrl();  //(FACEBOOK test) get current url which is the share modal
					_mwh = driver.getWindowHandle();   //get current window name
  					try {    // if an app is present, delete it
  						shopify.visitAppStore();
  					} catch (ElementNotVisibleException e) {
  						shopify.appCardMenuRemove();
  						Thread.sleep(1000);
  						shopify.finalDelete();
  						Thread.sleep(1000);
  						shopify.visitAppStore();
  					}
					//collect info about the windows
					
  					driver.switchTo().window(win.changeWindowForShare(beforePopup));  //this clicks the link and switches windows because win.changeWindowForShare() returns a URL to switch to
  					shopify.goToApp();
  					Thread.sleep(1500);
  					shopify.getApp();
  					Thread.sleep(1000);
  					shopify.installApp();
  					shopify.frame();  //waits for outframe to load
  					driver.switchTo().defaultContent();
  					driver.switchTo().frame(driver.findElement(By.className("app-iframe")));  //switches to iframe
  				  	
  					if (analyticsReporter.loaded().equals("Dashboard")) {
  						System.out.println("PASS Shoppost app properly installed.");
  					} else {
  						fail("FAIL - no Welcome screen, installation failure.");
  					}
  					driver.switchTo().defaultContent();
  					
 					Thread.sleep(2000);
  					shopify.userPullDown();  //shopify log out steps
  					Thread.sleep(500);
  					shopify.logout();   //shopify log out steps
  					
  					break;
  					
  				case "uninstallShopifyProdApp" :
  					signup.helloPlatform(_td.getVerifyData().getInstallShopifyAcct().get(0));
  					Thread.sleep(1000);
  					try {
  						shopifyLogin.shopSignin(_td.getVerifyData().getInstallShopifyAcct().get(1), _td.getVerifyData().getInstallShopifyAcct().get(2));
  					} catch (NoSuchElementException e) {
  						System.out.println("already loggin in");
  					}
  					shopify.toApps(_td.getVerifyData().getShopifyAcctApps());
  					shopify = new Shopify(driver);
  					Thread.sleep(1500);
  					try {    // if an app is present, delete it
  						shopify.appCardMenuRemove();
  						Thread.sleep(1000);
  						shopify.finalDelete();
  						Thread.sleep(2000);
  						
  					} catch (NoSuchElementException e) {
  						System.out.println("App not present.");
  					}
					
					if (shopify.appGone()) {
  						System.out.println("PASS Shoppost app properly removed.");
  					} else {
  						fail("FAIL - App Not Removed.");
  					}
  					
  					Thread.sleep(1000);
  					shopify.userPullDown();  //shopify log out steps
  					Thread.sleep(500);
  					shopify.logout();   //shopify log out steps
  					
  					break;
  					

				
  				case "newProductShoppost": // add product to shopify 
  					signup.helloPlatform(_td.getVerifyData().getStoreShopifyAcct().get(0));
  					Thread.sleep(1000);
  					try {
  						shopifyLogin.shopSignin(_td.getVerifyData().getStoreShopifyAcct().get(1), _td.getVerifyData().getStoreShopifyAcct().get(2));
  					} catch (NoSuchElementException e) {
  						System.out.println("already loggin in");
  					}
  					shopify.toProductList();
  					shopify = new Shopify(driver);
  					Thread.sleep(1500);
  					// add new product to shopify
  					shopify.addProduct();
  					addProduct.inputTitle(_td.getVerifyData().getProductData().get(0));
  					addProduct.inputDescription(_td.getVerifyData().getProductData().get(1));
  					addProduct.inputPrice(_td.getVerifyData().getProductData().get(2));
  					addProduct.inputWeight(_td.getVerifyData().getProductData().get(3));
  					addProduct.uploadImage(_td.getVerifyData().getProductData().get(4));
  					addProduct.saveProduct();
  					Thread.sleep(5000);
  					//addProduct.clickEllipsis();
  					Thread.sleep(1000);
  					addProduct.shoppostProduct();
  					shopify.frame(); //wait for iframe
  					
  					
  					driver.switchTo().defaultContent();
  					driver.switchTo().frame(driver.findElement(By.className("app-iframe")));
  					
  					
  					if((_td.getVerifyData().getProductData().get(0)).equals(shopify.getProductName())) {
						System.out.println("PASS Shoppost properly created: "+_td.getVerifyData().getProductData().get(0));
					} else {
						fail("FAIL - invalid shoppost product name: "+shopify.getProductName());
					}
  					
  					System.out.println("URL: "+shopify.getProductUrl());
  					//_fbPostTitle = socialNetwork.shareOut("facebook", "shopify");   //share to FB
  					Thread.sleep(6000);
  					shopify.shareFacebook();
  					Thread.sleep(6000);
  					System.out.println("postTitle = "+_fbPostTitle);
					if(_td.getVerifyData().getProductData().get(0).equals(_fbPostTitle)) {
						System.out.println("PASS Correct data to FB: "+_td.getVerifyData().getProductData().get(0));
					} else {
						fail("FAIL - invalid data to FB: "+_fbPostTitle);
					}
					
			
					
					Thread.sleep(1000);
					//logout.logoutFromCat();
					
					break;
					
  				case "addShopifyProductRepeat": // add product(s) to shopify - productRepeat in JSON determines number of products
  					signup.helloPlatform(_td.getVerifyData().getStoreShopifyAcct().get(0));
  					Thread.sleep(1000);
  					try {
  						shopifyLogin.shopSignin(_td.getVerifyData().getStoreShopifyAcct().get(1), _td.getVerifyData().getStoreShopifyAcct().get(2));
  					} catch (NoSuchElementException e) {
  						System.out.println("already loggin in");
  					}
  					shopify = new Shopify(driver);
  					for (int j=0; j<_td.getVerifyData().getProductRepeat(); j++) {
  						shopify.toProductList();
  						Thread.sleep(1500);
  						// add new product to shopify
  						shopify.addProduct();
  						System.out.println("loop count: "+j);
  						addProduct.inputTitle(_td.getVerifyData().getProductData().get(0)+" "+Integer.toString(j));
  						addProduct.inputDescription(_td.getVerifyData().getProductData().get(1));
  						addProduct.inputPrice(_td.getVerifyData().getProductData().get(2));
  						addProduct.inputWeight(_td.getVerifyData().getProductData().get(3));
  						addProduct.uploadImage(_td.getVerifyData().getProductData().get(4));
  						addProduct.saveProduct();
  						Thread.sleep(2000);
  						//addProduct.clickEllipsis();
  						//addProduct.shoppostProduct();
					}
					
			
					
					Thread.sleep(1000);
					//logout.logoutFromCat();
					
					break;


					
					
  				}
		}
		
		Thread.sleep(1000);
		//ss.takeTheShot(2, "platform", _testCase);  //screenshot of outro
		Thread.sleep(1000);  
		
	}
	

	
	
	
	@After
	public void tearDown() throws Exception {
		//Global.increment();           //increment the global variable 'counter' by 1
        driver.quit();
    }  
	@AfterClass
	public static void done() throws Exception {
		Global.increment();
	}

}