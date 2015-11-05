package shoppostTestSupport;

import static org.junit.Assert.fail;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import shoppostBeans.TestData;
import shoppostPages.Home;
import shoppostPages.LandingPage;
import shoppostPages.ProductCatalog;
import shoppostPages.ShareModal;
import shoppostPages.ShareSetUp;
import shoppostPages.Shopify;


public class SocialNetworks {
	
	private WebDriver _driver;
	private TestData _td;
	private String _network, _eComm, _currUrl, _mwh, _shareTitle, _name, _info;
	private Window win;
	private Set beforePopup;
	
	private Home homePage;
	private LandingPage landingPage;
	private ShareSetUp shareSetUp;
	private ProductCatalog catalog;
	private ShareModal shareModal;
	private Shopify shopify;
	
	public SocialNetworks(WebDriver driver, TestData td) {
		
		_driver = driver;
		_td = td;
		
		homePage = new Home(driver);
		landingPage = new LandingPage(driver);
	}
	public String shareOut(String network, String eComm) throws Exception {
		_network = network;
		_eComm = eComm;   //currently 'web' or 'shopify'
		
		
		switch(_network) {
		case "facebook" : 
			
			beforePopup = _driver.getWindowHandles();   //collect windows and then send them to the change window class
			_currUrl = _driver.getCurrentUrl();  //(FACEBOOK test) get current url which is the share modal
			_mwh = _driver.getWindowHandle();   //get current window name
			System.out.println("a");
			//now open new window
			switch(_eComm) {
			case "shopify" :
				System.out.println("b");
				_driver.switchTo().defaultContent();
				_driver.switchTo().frame(_driver.findElement(By.className("app-iframe")));
				_driver.switchTo().frame(_driver.findElement(By.id("shoppostframe")));  //switches to second iframe
				System.out.println("c");
				_name = shopify.getProductName();
				System.out.println("d");
				shopify.shareFacebook();  //this opens a new tab for FB sharing now need to switch driver to that new window
				System.out.println("e");
				break;
			case "web" :
				_driver.switchTo().defaultContent();
				_name = shareModal.getProductName();
				shareModal.shareFB();   //this opens a new tab for FB sharing now need to switch driver to that new window
				break;
			}
			
			_driver.switchTo().window(win.changeWindowForShare(beforePopup));  //this clicks the link and switches windows because win.changeWindowForShare() returns a URL to switch to
			shareSetUp.loginFacebook(_td.getUsernameFB(), _td.getPasswordFB());
			_shareTitle = shareSetUp.getShareTitleFB();
			shareSetUp.shareToTimeline(_td.getShareData().getShareMessage());
			
			_driver.switchTo().window(_mwh);  //back to original window
			_driver.get(_currUrl);   //run with original window
			Thread.sleep(1000);
			/**System.out.println("name = "+_name);
			if(_shareTitle.equals(_name)) {
				System.out.println("PASS Correct data to FB: "+_shareTitle);
			} else {
				fail("FAIL - invalid data to FB: "+_shareTitle);
			}**/
			_info = _shareTitle;  //pass back facebook post title
			break;
		case "twitter" :
			 
			break;
		case "pinterest" :
			
			break;
		case "googlePlus" :
			
			break;
		}
		
		return _info;
	}

}
