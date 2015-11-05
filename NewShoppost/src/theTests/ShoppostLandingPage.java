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
import shoppostTestSupport.Window;






@RunWith(Parameterized.class)   //this runs the tests serially one browser at a time
//@RunWith(Parallelized.class)    //this will run the tests in parallel and will open a new thread for each browser simultaneously
public class ShoppostLandingPage {
	
	private String browser;
	private WebDriver driver;
	private static TestData _td;
	private static SauceLabData _sld;
	//private static AcctSetData _asd;
	private static LPData _ltd;
	private String userName, _username, _password, _freshUser, _usernameFB, _shareTitle, _usernameTwitter, _tweet, _initialTweet;
	private String accessCode, _passkey, _passkeyFB, _email, _currUrl, _currUrl_b, mwh_b, _mwh, _testCase, _emailAddress;
	private String _pinTitle, _googPlusPW, _googTitle, _landingPageURL;
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
	private ProductDataMap productDataMap;
	private String _errorMsg,_option1Name,_option2Name, _usernameShopify, _passwordShopify, _shopifyShopUrl;
	private Window win;
	private Set beforePopup;
	private HashMap _shopify_Hm, _landingPage_Hm;  //full data map
	private List<String> _option1Values = new ArrayList<String>();
	private List<String> _option2Values = new ArrayList<String>();
	private String _variant1Option1Name, _variant1Option2Name, _variant2Option1Name, _variant2Option2Name, _variant1Price, _variant2Price;
	//private ScreenShot ss;
	
	
	
	
	//public String getBrowser() { return browser; }
	
	@Parameters
	public static Collection<Object[]> browsersStrings(){
		Object[][] browsersStrings = new Object[][]{
				{"*firefox"}
				//{"*chrome"}
				//{"*internetexplorer"}
				//{"*safari"}
				//{"*sauceLabsRemote1"}
				//{"*sauceLabsRemote2"},
				//{"*sauceLabsRemote3"},
				//{"*sauceLabsRemote4"}
				};
		return Arrays.asList(browsersStrings);
	} 
	
	public ShoppostLandingPage (String browser){
		
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
	  public void test_landingPage() throws Exception {
		wait = new WebDriverWait(driver, 10);
		Random rand = new Random();
		Actions move = new Actions(driver);
		signupinPage = new SignUpSignIn(driver);  //this waits for page to load AND initializes the pageFactory proxies
		analyticsReporter = new AnalyticsReporter(driver); 
		catalog = new ProductCatalog(driver);
		shareModal = new ShareModal(driver);
		shareSetUp = new ShareSetUp(driver);
		home = new Home(driver);
		landingPage = new LandingPage(driver);
		mobileLandingPage = new MobileLandingPage(driver);
		fbSignIn = new FBsignIn(driver);
		shopifyLogin = new ShopifyLogin(driver);
		shopify = new Shopify(driver);
		productDataMap = new ProductDataMap(driver);

		ReadTestJSON.read();
		_td = ReadTestJSON.get_td();
		
		String _browsName = this.browser;
		_browsName = _browsName.substring(1);
		ScreenShots ss = new ScreenShots(driver, _td, _sld, _ltd, _browsName, m);   //screenshot of platform signup
		
		signup = new SignUpIn(driver, _td);   //signup methods
		SignOut logout = new SignOut(driver);
		Window win = new Window(driver);
		
		_usernameFB = _td.getUsernameFB();
		//System.out.println(_usernameFB);
		//System.out.println("A");
		_usernameTwitter = _td.getUsernameTwitter();
		_passkeyFB = _td.getPasswordFB();
		_username = _td.getUsername();
		_password = _td.getPassword();
		_usernameShopify = _td.getUsernameShopify();
		_passwordShopify = _td.getPasswordShopify();
		_shopifyShopUrl = _td.getShopifyShopUrl();
		_shopifyEmail = _td.getShareData().getShopifyEmail();
		_shopifyStorename = _td.getShareData().getShopifyStorename();
		_shopifyPassword = _td.getShareData().getShopifyPassword();
		_landingPageURL = "";
		
		//System.out.println("testLength is: "+TestRunner.getTests().length);
		for (int k=0; k<_td.getLandingPageData().getTests().size(); k++) {  //taking one testCase parameter at a time (this by-passes the need for TestRunner
		//for (int k=0; k<TestRunner.getTests().length; k++) {  //taking one testCase parameter at a time from cmd line
	    //for (int k=0; k<1; k++) {   //just a quick test
			//ss.takeTheShot(1, "platform");
  			//_testCase = TestRunner.getTests()[k];
			_testCase = _td.getLandingPageData().getTests().get(k);
			//_testCase = "signupValid";
  			switch (_testCase) {
  			
  				case "shopifyProductData" :   //make shoppost from 2nd product in shopify store and get LP URL, prod name, description, and first two variants
  					String _temp, _prodName;
  					signup.helloPlatform(_td.getShareData().getShopifyLoginUrl());
  					shopifyLogin.login(_shopifyStorename, _shopifyEmail, _shopifyPassword);
  					shopify.toProductList();
  					Thread.sleep(1500);
  					shopify = new Shopify(driver);
  					_shopify_Hm = productDataMap.buildShopifyMap(_td.getLandingPageData().getProductNumber());  //product data for nth product
  					
  					System.out.println((String) _shopify_Hm.get("productName"));
  					System.out.println((String) _shopify_Hm.get("productDescription"));
  					System.out.println((String) _shopify_Hm.get("variant1Price"));
  					System.out.println((String) _shopify_Hm.get("variant2Price"));
  					/** if (_variantCount > 1) {
	  					switch(_optionCount) {  //collect data on first two variants
	  					case 1 :  //single option
	  						_variant1Option1Name = shopify.getVariantOneOptionOneName();
	  						_variant2Option1Name = shopify.getVariantTwoOptionOneName();
	  						_variant1Price = shopify.getVariantOnePrice();
	  						_variant2Price = shopify.getVariantTwoPrice();
	  						break;
	  					case 2 :  //two options
	  						_variant1Option1Name = shopify.getVariantOneOptionOneName();
	  						_variant1Option2Name = shopify.getVariantOneOptionTwoName();
	  						_variant2Option1Name = shopify.getVariantTwoOptionOneName();
	  						_variant2Option2Name = shopify.getVariantTwoOptionTwoName();
	  						_variant1Price = shopify.getVariantOnePrice();
	  						_variant2Price = shopify.getVariantTwoPrice();
	  						break;
	  					}
  					}else{  //collect data on first (and only) variant
  						_variant1Option1Name = shopify.getVariantOneOptionOneName();
  						_variant1Price = shopify.getVariantOnePrice();
  					} **/
  					shopify.clickEllipsis();  //need to open modal to get landing page URL
  					Thread.sleep(1500);
  					shopify.shoppostProduct();
  					Thread.sleep(10000);
  					//shareModal = new ShareModal(driver);
  					//driver.switchTo().defaultContent();
  					//driver.switchTo().frame(driver.findElement(By.className("app-iframe")));
  					driver.switchTo().frame("app-iframe");
  					//driver.switchTo().frame(1);
  					Thread.sleep(500);
  					driver.switchTo().frame(driver.findElement(By.id("shoppostframe")));
  					//driver.switchTo().frame(1);
  					//System.out.println("b");
  					//shareModal = new ShareModal(driver);
  					Thread.sleep(4000);
  					
  					shareModal.goLink();
  					Thread.sleep(3000);
  					/**try {
  						shareModal.goLink();
  						Thread.sleep(2000);
  						//driver.switchTo().defaultContent();  
  					} catch (NoSuchElementException ex) {
  						System.out.println("ERROR! Shoppost App did NOT load. go to next test");
  						//break;
  					} **/
  					_landingPageURL = shareModal.getLPUrl();
  					System.out.println("LP URL: "+_landingPageURL);
  					driver.switchTo().defaultContent();
  					shopify.goBackToProduct();
  					Thread.sleep(2000);
  					shopify.userPullDown();  //shopify log out steps
  					Thread.sleep(500);
  					shopify.logout();   //shopify log out steps
  					
  					break;
  					
  				case "landingPageDataMap" :
  					List<String> _option3Values;
  					signup.helloPlatform(_td.getLpUrlPre1());
  					_landingPage_Hm = productDataMap.buildLandingPageMap();
  					
  					System.out.println((String) _landingPage_Hm.get("productName"));
  					System.out.println((String) _landingPage_Hm.get("productDescription"));
  					System.out.println((int) _landingPage_Hm.get("optionCount"));
  					_option3Values = (List<String>) _landingPage_Hm.get("option3Values");
  					for (int i=0; i<(int) _landingPage_Hm.get("option3ValueCt"); i++) {
  						System.out.println(_option3Values.get(i));
  					}
  					
  					break;
  				case "pricing" :
  	  				
  					break;
  				case "buyButtonRedirect" :
  	  				
  					break;
  				case "readMore" :
  	  				
  					break;
  				case "soldBy" :
  	  				
  					break;
  				case "productDetailsInCart" :
  	  				
  					break;
  				case "youTubeVid" :
  	  				
  					break;
  				case "links" :
  	  				
  					break;
  				case "lpPostDelete" :
  					
  					break;
  				case "shoppostLink" :
  					
  					break;
				
  				case "fbShare": // FB shares from shopify shopposts
					signup.helloPlatform(_td.getLpUrlPre3());  //open shopify login
					/**shopifyLogin.shopSignin(_td.getUsernameShopify(), _td.getPasswordShopify());  //log in shopify shop
					shopify.toApps();
					if (shopify.appPresent()) {
						shopify.toDashboard();
					}**/
					/**signup.signInTest(_username, _password, 0);
					analyticsReporter.toCatalog();
					_productCount = catalog.getProductCount();
					//System.out.println(_productCount);
					move.moveToElement(catalog.hoverRandomProduct());  //move to random product
					
					catalog.getShare(); **/ //open share modal 
					//collect info about the windows
					beforePopup = driver.getWindowHandles();   //collect windows and then send them to the change window class
					_currUrl = driver.getCurrentUrl();  //(FACEBOOK test) get current url which is the share modal
					_mwh = driver.getWindowHandle();   //get current window name
					//now open new window
					landingPage.isMediaGalleryVisible();
					landingPage.openSharing();
					landingPage.shareFacebook();
					//shareModal.shareFB();   //this opens a new tab for FB sharing now need to switch driver to that new window
					driver.switchTo().window(win.changeWindowForShare(beforePopup));  //this clicks the link and switches windows because win.changeWindowForShare() returns a URL to switch to
					shareSetUp.loginFacebook(_usernameFB, _passkeyFB);
					_shareTitle = shareSetUp.getShareTitleFB();
					shareSetUp.shareToTimeline(_td.getShareData().getShareMessage());
					
					
					driver.switchTo().window(_mwh);  //back to catalog - LP
					driver.get(_currUrl);   //run with catalogLP
					//move.moveToElement(catalog.hoverProductAgain());  //hover same product again
					Thread.sleep(1000);
					//catalog.getShare();
					System.out.println("name = "+landingPage.getProductName());
					if(_shareTitle.equals(landingPage.getProductName())) {
						System.out.println("PASS Correct data to FB: "+_shareTitle);
					} else {
						fail("FAIL - invalid data to FB: "+_shareTitle);
					}
					
					
					
					Thread.sleep(1000);
					//logout.logoutFromCat();
					
					break;
					
				case "twitterShare": // twitter shares
					//signup.helloPlatform(_td.getBaseUrl());
					signup.helloPlatform(_td.getLpUrlPre1());  //open shopify login
					
					/**signup.signInTest(_username, _password, 0);
					analyticsReporter.toCatalog();
					_productCount = catalog.getProductCount();
					//System.out.println(_productCount);
					move.moveToElement(catalog.hoverRandomProduct());  //move to random product
					
					catalog.getShare(); **/ //open share modal 
					//collect info about the windows
					beforePopup = driver.getWindowHandles();   //collect windows and then send them to the change window class
					_currUrl = driver.getCurrentUrl();  //(FACEBOOK test) get current url which is the share modal
					_mwh = driver.getWindowHandle();   //get current window name
					//now open new window
					landingPage.isMediaGalleryVisible();
					landingPage.openSharing();
					landingPage.shareTwitter();
					//now open new window
					//shareModal.shareTwitter();   //this opens a new tab for twitter sharing now need to switch driver to that new window
					driver.switchTo().window(win.changeWindowForShare(beforePopup));  //this clicks the link and switches windows because win.changeWindowForShare() returns a URL to switch to
					r = rand.nextInt(10000);
					_initialTweet = shareSetUp.getTweet();
					shareSetUp.addMoreTweet(_td.getShareData().getMoreTweet()+r);
					shareSetUp.loginTweetTwitter(_usernameTwitter, _passkeyFB);
					//driver.close(); //closes current window - maybe	

					driver.switchTo().window(_mwh);  //back to modalLP
					driver.get(_currUrl);   //run with modalLP
					//move.moveToElement(catalog.hoverProductAgain());
					//catalog.getShare();
					Thread.sleep(1000);
					//shareModal.goLink();
					System.out.println("tweet = "+_initialTweet);
					if(_initialTweet.equals(landingPage.getProductName()+" "+landingPage.getLPUrl())) {
						System.out.println("PASS Correct data to twitter: "+_initialTweet);
					} else {
						fail("FAIL - invalid data to tiwtter: "+_initialTweet);
					}
					
					
					
					Thread.sleep(1000);
					//logout.logoutFromCat();
					
					break;
					
				case "pinterestShare": // see error message
					
					/**signup.helloPlatform(_td.getBaseUrl());
					signup.signInTest(_username, _password, 0);
					analyticsReporter.toCatalog();
					//Thread.sleep(1500);
					
					_productCount = catalog.getProductCount();
					move.moveToElement(catalog.hoverRandomProduct());  //move to random product
					
					catalog.getShare(); //open share modal 
					//collect info about the windows
					beforePopup = driver.getWindowHandles();   //collect windows and then send them to the change window class
					_currUrl = driver.getCurrentUrl();  //get current url which is the share modal
					_mwh = driver.getWindowHandle();   //get current window name
					//now open new window
					shareModal.sharePinterest(); **/  //this opens a new tab for pinterst sharing now need to switch driver to that new window
					
					signup.helloPlatform(_td.getLpUrlPre1());  //open landing page
					//collect info about the windows
					beforePopup = driver.getWindowHandles();   //collect windows and then send them to the change window class
					_currUrl = driver.getCurrentUrl();  //(FACEBOOK test) get current url which is the share modal
					_mwh = driver.getWindowHandle();   //get current window name
					//now open new window
					landingPage.isMediaGalleryVisible();
					landingPage.openSharing();
					landingPage.sharePinterest();

					
					driver.switchTo().window(win.changeWindowForShare(beforePopup));  //this clicks the link and switches windows because win.changeWindowForShare() returns a URL to switch to
					
					shareSetUp.havePinAcct();
					Thread.sleep(3000);
					shareSetUp.loginPinterest(_usernameFB, _passkeyFB);
					_pinTitle = shareSetUp.getPinTitle();  //
					Thread.sleep(2000);
					shareSetUp.pinToPinterest();
					//driver.close(); //closes current window - maybe	

					driver.switchTo().window(_mwh);  //back to modalLP
					driver.get(_currUrl);   //run with modalLP
					//move.moveToElement(catalog.hoverProductAgain());
					//catalog.getShare();
					Thread.sleep(1000);
					//shareModal.goLink();
					System.out.println("pinTitle = "+_pinTitle);
					System.out.println("productName = "+landingPage.getProductName());
					if(_pinTitle.equals(landingPage.getProductName())) {
						System.out.println("PASS Correct data to twitter: "+_pinTitle);
					} else {
						fail("FAIL - invalid data to twitter: "+_pinTitle);
					}
					
					
					
					Thread.sleep(1000);
					//logout.logoutFromCat();
					
					break;
				
				case "googlePlusShare": // see error message
					
					/**signup.helloPlatform(_td.getBaseUrl());
					signup.signInTest(_email, _password, 0);
					
					analyticsReporter.toCatalog();
					//Thread.sleep(1500);
					
					_productCount = catalog.getProductCount();
					move.moveToElement(catalog.hoverRandomProduct());  //move to random product
					
					catalog.getShare();  //open share modal 
					//collect info about the windows
					beforePopup = driver.getWindowHandles();   //collect windows and then send them to the change window class
					_currUrl = driver.getCurrentUrl();  //get current url which is the share modal
					_mwh = driver.getWindowHandle();   //get current window name
					//now open new window
					shareModal.shareGooglePlus();   //this opens a new tab for googlePlus sharing now need to switch driver to that new window
					**/
					
					signup.helloPlatform(_td.getLpUrlPre2());  //open landing page
					//collect info about the windows
					beforePopup = driver.getWindowHandles();   //collect windows and then send them to the change window class
					_currUrl = driver.getCurrentUrl();  //(FACEBOOK test) get current url which is the share modal
					_mwh = driver.getWindowHandle();   //get current window name
					//now open new window
					landingPage.isMediaGalleryVisible();
					landingPage.openSharing();
					landingPage.shareGooglePlus();

					
					
					driver.switchTo().window(win.changeWindowForShare(beforePopup));  //this clicks the link and switches windows because win.changeWindowForShare() returns a URL to switch to
					
					shareSetUp.signinGoogplus(_usernameFB, _td.getPasswordGoog());
					Thread.sleep(3000);
					_googTitle = shareSetUp.getGoogTitle();
					Thread.sleep(2000);
					shareSetUp.shareToGoog(_td.getShareData().getGoogComment());  //
					//driver.close(); //closes current window - maybe	

					driver.switchTo().window(_mwh);  //back to modal
					driver.get(_currUrl);   //run with modal
					//move.moveToElement(catalog.hoverProductAgain());
					//catalog.getShare();
					Thread.sleep(1000);
					//shareModal.goLink();
					System.out.println("googTitle = "+_googTitle);
					System.out.println("productName = "+landingPage.getProductName());
					if(_googTitle.equals(landingPage.getProductName())) {
						System.out.println("PASS Correct data to twitter: "+_googTitle);
					} else {
						fail("FAIL - invalid data to tiwtter: "+_googTitle);
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
	

	
	public void checkSignupError(String em) throws Exception {
		String _errorMessage = em;
		wait2 = new WebDriverWait(driver, 5);
		
		if (driver.findElement(By.id("errorMsg")).getText().equals(_errorMessage)) {
			System.out.println(_errorMessage+" error displayed.");
			
		} else if (driver.findElement(By.xpath("//div[@id='emailField']/span")).isDisplayed()||driver.findElement(By.xpath("//div[@id='emailField']/span")).getText().equals(_errorMessage)) {
			System.out.println(_errorMessage+" error displayed.");
		} else if (driver.findElement(By.xpath("//div[@id='passwordField']/span")).isDisplayed()||driver.findElement(By.xpath("//div[@id='passwordField']/span")).getText().equals(_errorMessage)) {
			System.out.println(_errorMessage+" error displayed.");
		} else if (driver.findElement(By.xpath("//div[@id='repeatPasswordField']/span")).isDisplayed()||driver.findElement(By.xpath("//div[@id='repeatPasswordField']/span")).getText().equals(_errorMessage)) {
			System.out.println(_errorMessage+" error displayed.");
		} else if (driver.findElement(By.xpath("//div[@id='agreementField']/span")).isDisplayed()||driver.findElement(By.xpath("//div[@id='agreementField']/span")).getText().equals(_errorMessage)) {
			System.out.println(_errorMessage+" error displayed.");
		} else {
			System.out.println("No message was found.");
		}
		
		
	}
	
	public void checkSigninError (String em) throws Exception {
		String _error = em;
		wait2 = new WebDriverWait(driver, 5);
		
		try {
			driver.findElement(By.xpath("//div[@id='main-login']/div[contains(@class,'errorMessage')]"));
			if (driver.findElement(By.xpath("//div[@id='main-login']/div[contains(@class,'errorMessage')]")).getText().equals(_error)) {
				System.out.println("'"+_error+"' error displayed.");
			}
		} catch (NoSuchElementException e) {
			if (driver.findElement(By.xpath("//div[@id='emailWrapper']/span")).isDisplayed()||driver.findElement(By.xpath("//div[@id='emailWrapper']/span")).getText().equals(_error)) {
				System.out.println("'"+_error+"' error displayed.");
			} else if (driver.findElement(By.xpath("//div[@id='passwordWrapper']/span")).isDisplayed()||driver.findElement(By.xpath("//div[@id='passwordWrapper']/span")).getText().equals(_error)) {
				System.out.println("'"+_error+"' error displayed.");
			} else {
				System.out.println("No message was found.");
			}

		}
	
		
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
