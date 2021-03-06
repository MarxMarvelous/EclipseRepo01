package theTests;

import static org.junit.Assert.*;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
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
import shoppostPages.ShareSetUp;
import shoppostPages.ProductCatalog;
import shoppostPages.ShareModal;
import shoppostPages.SignUpSignIn;
import shoppostPages.UserAgreement;
import shoppostPages.FBsignIn;
import shoppostPages.AnalyticsReporter;

import shoppostTestSupport.AnalyticsDataMap;
import shoppostTestSupport.GetDrivers;
import shoppostTestSupport.Global;
import shoppostTestSupport.SignOut;
import shoppostTestSupport.ReadTestJSON;
import shoppostTestSupport.ScreenShots;
import shoppostTestSupport.SignUpIn;
import shoppostTestSupport.Window;






@RunWith(Parameterized.class)   //this runs the tests serially one browser at a time
//@RunWith(Parallelized.class)    //this will run the tests in parallel and will open a new thread for each browser simultaneously
public class ShoppostShopifyAllAnalytics {
	
	private String browser;
	private WebDriver driver;
	private static TestData _td;
	private static SauceLabData _sld;
	//private static AcctSetData _asd;
	private static LPData _ltd;
	private String userName, _username, _password, _freshUser, _usernameFB, _shareTitle, _usernameTwitter, _tweet, _initialTweet;
	private String accessCode, _passkeyFB, _email, _currUrl, _mwh, _testCase, _emailAddress;
	private String _pinTitle, _googPlusPW, _googTitle;
	private DesiredCapabilities capabilities;
	private WebDriverWait wait, wait2;
	private java.awt.Dimension screenSize;
	private Dimension dim;
	private int counter, r, m, _productCount, preCt, postCt;
	private SignUpIn signup;
	private SignUpSignIn signupinPage;
	private UserAgreement userAgreementPage;
	private ProductCatalog catalog;
	private FBsignIn FBone, FBtwo;
	private AnalyticsReporter analyticsReporter;
	private ShareModal shareModal;
	private ShareSetUp shareSetUp;
	private Home home;
	private String _errorMsg;
	private Window win;
	private Set beforePopup;
	private HashMap _fadm;  //full analytics data map
	private String _redirectTotal, _redirectTotal_b, _folderTestCase, _testPlatform;
	private String[] _socialReactions,_geoLocations, _fbRedirects,_twitRedirects,_pinRedirects,_googRedirects,_otherRedirects;
	private String[] _socialReactions_b,_geoLocations_b,_fbRedirects_b,_twitRedirects_b,_pinRedirects_b,_googRedirects_b,_otherRedirects_b;
	
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
	
	public ShoppostShopifyAllAnalytics (String browser){
		
		this.browser = browser;
	}
	
	@Before
	public void setUp() throws Exception {  //sets up the driver
		counter = Global.sauceCounter;
		System.out.println("counter is: "+counter);
		
		GetDrivers getDriver = new GetDrivers(this.browser);   //instantiate GetDriver
		driver = getDriver.set();
		_testPlatform = getDriver.getPlatform();  //gets platform (must match qmetry platform in test cases)
		return;
		
	}
	
	@Test
	  public void test_Analytics() throws Exception {
		wait = new WebDriverWait(driver, 10);
		Random rand = new Random();
		Actions move = new Actions(driver);
		signupinPage = new SignUpSignIn(driver);  //this waits for page to load AND initializes the pageFactory proxies
		analyticsReporter = new AnalyticsReporter(driver); 
		catalog = new ProductCatalog(driver);
		shareModal = new ShareModal(driver);
		shareSetUp = new ShareSetUp(driver);
		home = new Home(driver);
		
		AnalyticsDataMap analyticsDataMap = new AnalyticsDataMap(driver);    //hashmap for data storage

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
		_passkeyFB = _td.getPasswordFB();
		_username = _td.getUsername();
		_password = _td.getPassword();
		_freshUser = "";
		
		//System.out.println("testLength is: "+TestRunner.getTests().length);
		for (int k=0; k<_td.getAnalyticsTests().getTests().size(); k++) {  //taking one testCase parameter at a time (this by-passes the need for TestRunner
		//for (int k=0; k<TestRunner.getTests().length; k++) {  //taking one testCase parameter at a time from cmd line
	    //for (int k=0; k<1; k++) {   //just a quick test
			//ss.takeTheShot(1, "platform");
  			//_testCase = TestRunner.getTests()[k];
			_testCase = _td.getAnalyticsTests().getTests().get(k);
			//_testCase = "signupValid";
			_folderTestCase = _td.getAnalyticsTests().getTcFolder()+_testCase;
  			switch (_testCase) {
  			
					
					
				case "buildFullAnalyticsDataMap": // 
					signup.helloPlatform(_td.getShopifyDashUrl());
					Thread.sleep(500);
					
					_fadm = analyticsDataMap.buildFullMap("7"); //pass in the date range  7, 14, or 30
					_socialReactions = (String[]) _fadm.get("socialReactions");
					_geoLocations = (String[]) _fadm.get("geoLocations");
					_redirectTotal = (String) _fadm.get("redirectTotal");
					_fbRedirects = (String[]) _fadm.get("facebookRedirects");
					_twitRedirects = (String[]) _fadm.get("twitterRedirects");
					_pinRedirects = (String[]) _fadm.get("pinterestRedirects");
					_googRedirects = (String[]) _fadm.get("googlePlusRedirects");
					_otherRedirects = (String[]) _fadm.get("otherRedirects");
					
					String[] _productMostGoogShares3 = (String[]) _fadm.get("productMostGoogSharesTop3");
					String[] _productMostOtherRefers3 = (String[]) _fadm.get("productMostOtherReferralsTop3");
					System.out.println("FB view count: "+_socialReactions[0]);
					System.out.println("FB share count: "+_socialReactions[1]);
					System.out.println("FB Like count: "+_socialReactions[2]);
					System.out.println("Twit view count: "+_socialReactions[3]);
					System.out.println("Twit tweet count: "+_socialReactions[4]);
					System.out.println("Pin view count: "+_socialReactions[5]);
					System.out.println("Pin Pin count: "+_socialReactions[6]);
					System.out.println("total Redirects: "+_redirectTotal);
					System.out.println("other redirects %: "+_otherRedirects[0]);
					System.out.println("other redirects redirects: "+_otherRedirects[1]);
					System.out.println("product 3rd most shared google: "+_productMostGoogShares3[4]);
					System.out.println("product 3rd most shared google ct: "+_productMostGoogShares3[5]);
					System.out.println("product 3rd most referred other: "+_productMostOtherRefers3[4]);
					System.out.println("product 3rd most referred other ct: "+_productMostOtherRefers3[5]);
					//String [][] optionValues = (String[][]) _hm.get("_optionValues");
					
					/**System.out.println("name = "+shareModal.getProductName());
					if(_shareTitle.equals(shareModal.getProductName())) {
						System.out.println("PASS Correct data to FB: "+_shareTitle);
					} else {
						fail("FAIL - invalid data to FB: "+_shareTitle);
					}**/
					
					
					
					Thread.sleep(1000);
					//logout.logoutFromCat();
					
					break;
					
				case "preAllDashSS":
					signup.helloPlatform(_td.getShopifyDashUrl());
					
					Thread.sleep(1500);
					ss.takeTheShot(1, "platform", _testPlatform, _folderTestCase);
					break;
					
				case "postAllDashSS":
					signup.helloPlatform(_td.getShopifyDashUrl());
					
					Thread.sleep(1500);
					ss.takeTheShot(1, "platform", _testPlatform, _folderTestCase);
					break;

					
					
				case "confirmTwitterShare": // twitter shares
					signup.helloPlatform(_td.getBaseUrl());
					//signup.signInTest(_email, _password, 0);
					analyticsReporter.toCatalog();
					
					_productCount = catalog.getProductCount();
					move.moveToElement(catalog.hoverRandomProduct());  //move to random product
					
					catalog.getShare(); //open share modal 
					//collect info about the windows
					beforePopup = driver.getWindowHandles();   //collect windows and then send them to the change window class
					_currUrl = driver.getCurrentUrl();  // get current url which is the share modal
					_mwh = driver.getWindowHandle();   //get current window name
					//now open new window
					shareModal.shareTwitter();   //this opens a new tab for twitter sharing now need to switch driver to that new window
					driver.switchTo().window(win.changeWindowForShare(beforePopup));  //this clicks the link and switches windows because win.changeWindowForShare() returns a URL to switch to
					
					_initialTweet = shareSetUp.getTweet();
					shareSetUp.loginTweetTwitter(_usernameTwitter, _passkeyFB);
					//driver.close(); //closes current window - maybe	

					driver.switchTo().window(_mwh);  //back to modal
					driver.get(_currUrl);   //run with modal
					move.moveToElement(catalog.hoverProductAgain());
					catalog.getShare();
					shareModal.goLink();
					System.out.println("tweet = "+_initialTweet);
					if(_initialTweet.equals(shareModal.getProductName()+" "+shareModal.getLPUrl())) {
						System.out.println("PASS Correct data to twitter: "+_initialTweet);
					} else {
						fail("FAIL - invalid data to tiwtter: "+_initialTweet);
					}
					
					
					
					Thread.sleep(1000);
					//logout.logoutFromCat();
					
					break;
					
				case "confirmPinterestShare": // see error message
					
					signup.helloPlatform(_td.getBaseUrl());
					//signup.signInTest(_email, _password, 0);
					analyticsReporter.toCatalog();
					
					_productCount = catalog.getProductCount();
					move.moveToElement(catalog.hoverRandomProduct());  //move to random product
					
					catalog.getShare(); //open share modal 
					//collect info about the windows
					beforePopup = driver.getWindowHandles();   //collect windows and then send them to the change window class
					_currUrl = driver.getCurrentUrl();  //get current url which is the share modal
					_mwh = driver.getWindowHandle();   //get current window name
					//now open new window
					shareModal.sharePinterest();   //this opens a new tab for pinterst sharing now need to switch driver to that new window
					driver.switchTo().window(win.changeWindowForShare(beforePopup));  //this clicks the link and switches windows because win.changeWindowForShare() returns a URL to switch to
					
					shareSetUp.havePinAcct();
					Thread.sleep(1000);
					shareSetUp.loginPinterest(_usernameFB, _passkeyFB);
					_pinTitle = shareSetUp.getPinTitle();  //
					shareSetUp.pinToPinterest();
					//driver.close(); //closes current window - maybe	

					driver.switchTo().window(_mwh);  //back to modal
					driver.get(_currUrl);   //run with modal
					move.moveToElement(catalog.hoverProductAgain());
					catalog.getShare();
					//shareModal.goLink();
					System.out.println("pinTitle = "+_pinTitle);
					System.out.println("productName = "+shareModal.getProductName());
					if(_pinTitle.equals(shareModal.getProductName())) {
						System.out.println("PASS Correct data to twitter: "+_pinTitle);
					} else {
						fail("FAIL - invalid data to tiwtter: "+_pinTitle);
					}
					
					
					
					Thread.sleep(1000);
					//logout.logoutFromCat();
					
					break;
				
				case "confirmGooglePlusShare": // see error message
					
					signup.helloPlatform(_td.getBaseUrl());
					//signup.signInTest(_email, _password, 0);
					
					analyticsReporter.toCatalog();
					
					_productCount = catalog.getProductCount();
					move.moveToElement(catalog.hoverRandomProduct());  //move to random product
					
					catalog.getShare(); //open share modal 
					//collect info about the windows
					beforePopup = driver.getWindowHandles();   //collect windows and then send them to the change window class
					_currUrl = driver.getCurrentUrl();  //get current url which is the share modal
					_mwh = driver.getWindowHandle();   //get current window name
					//now open new window
					shareModal.shareGooglePlus();   //this opens a new tab for googlePlus sharing now need to switch driver to that new window
					driver.switchTo().window(win.changeWindowForShare(beforePopup));  //this clicks the link and switches windows because win.changeWindowForShare() returns a URL to switch to
					
					//shareSetUp.signinGoogplus(_usernameFB, _td.getAnalyticsTests().getPasswordGoog());
					Thread.sleep(8000);
					_googTitle = shareSetUp.getGoogTitle();
					//shareSetUp.shareToGoog(_td.getAnalyticsTests().getGoogComment());  //
					//driver.close(); //closes current window - maybe	

					driver.switchTo().window(_mwh);  //back to modal
					driver.get(_currUrl);   //run with modal
					move.moveToElement(catalog.hoverProductAgain());
					catalog.getShare();
					//shareModal.goLink();
					System.out.println("googTitle = "+_googTitle);
					System.out.println("productName = "+shareModal.getProductName());
					if(_googTitle.equals(shareModal.getProductName())) {
						System.out.println("PASS Correct data to twitter: "+_googTitle);
					} else {
						fail("FAIL - invalid data to tiwtter: "+_googTitle);
					}
					
					
					
					Thread.sleep(1000);
					//logout.logoutFromCat();
					
					break;



				case "shareModalTest": //Opens share modal window for random product
					signup.helloPlatform(_td.getBaseUrl());
					Actions move2 = new Actions(driver);
					catalog = PageFactory.initElements(driver, ProductCatalog.class);  //instantiate the pageOject 
					shareModal = PageFactory.initElements(driver, ShareModal.class);  //instantiate the pageObject
					_productCount = catalog.getProductCount();
					move2.moveToElement(catalog.hoverRandomProduct());
					//catalog = PageFactory.initElements(driver, ProductCatalog.class);  //instantiate the pageOject 
					catalog.getShare();
					Thread.sleep(2000);
					shareModal.closeShareModal();
					Thread.sleep(2000);
					//logout.logoutFromCat();
					
					break;


				
				case "viewRandomStats": //View random product Stats from product hover
					signup.helloPlatform(_td.getBaseUrl());
					Actions movea = new Actions(driver);
					catalog = PageFactory.initElements(driver, ProductCatalog.class);  //instantiate the pageOject 
					shareModal = PageFactory.initElements(driver, ShareModal.class);  //instantiate the pageObject
					_productCount = catalog.getProductCount();
					movea.moveToElement(catalog.hoverRandomProduct());  //hover random product from Catalog
					//catalog = PageFactory.initElements(driver, ProductCatalog.class);  //instantiate the pageOject 
					catalog.getRandomStats();  //click stats button
					Thread.sleep(2000);
					shareModal.closeShareModal();
					Thread.sleep(2000);
					//logout.logoutFromCat();
					
					break;
					
				case "viewRandomStatsViaShare": //View random product Stats from share Modal
					signup.helloPlatform(_td.getBaseUrl());
					Actions move7 = new Actions(driver);
					catalog = PageFactory.initElements(driver, ProductCatalog.class);  //instantiate the pageOject 
					shareModal = PageFactory.initElements(driver, ShareModal.class);  //instantiate the pageObject
					_productCount = catalog.getProductCount();
					move7.moveToElement(catalog.hoverRandomProduct());  //hover random product from Catalog
					//catalog = PageFactory.initElements(driver, ProductCatalog.class);  //instantiate the pageOject 
					catalog.getShare();  //click share button
					Thread.sleep(1000);
					shareModal.goStats();
					Thread.sleep(2000);
					shareModal.closeShareModal();
					//logout.logoutFromCat();
					
					break;

				case "viewSingleStatsViaDashMS": //View product Stats from Dashboard most shared
					signup.helloPlatform(_td.getBaseUrl());
					analyticsReporter = PageFactory.initElements(driver, AnalyticsReporter.class);  //instantiate the pageOject 
					shareModal = PageFactory.initElements(driver, ShareModal.class);  //instantiate the pageObject
					//catalog = PageFactory.initElements(driver, ProductCatalog.class);  //instantiate the pageOject 
					catalog.getShare();  //click share button
					Thread.sleep(1000);
					shareModal.goStats();
					Thread.sleep(2000);
					shareModal.closeShareModal();
					//logout.logoutFromCat();
					
					break;

				case "viewSingleStatsViaDashMR": //View product Stats from Dashboard most referred
					signup.helloPlatform(_td.getBaseUrl());
					analyticsReporter = PageFactory.initElements(driver, AnalyticsReporter.class);  //instantiate the pageOject 
					shareModal = PageFactory.initElements(driver, ShareModal.class);  //instantiate the pageObject
					//catalog = PageFactory.initElements(driver, ProductCatalog.class);  //instantiate the pageOject 
					catalog.getShare();  //click share button
					Thread.sleep(1000);
					shareModal.goStats();
					Thread.sleep(2000);
					shareModal.closeShareModal();
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
