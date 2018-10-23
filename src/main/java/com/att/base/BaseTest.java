package com.att.base;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.att.util.TestUtil;



public class BaseTest {
	public static WebDriver driver;
	public static Properties propv;


	//Constructor: to initialize and load properties in prop.
	public BaseTest() {
		try {
			propv = new Properties();
			FileInputStream fi = new FileInputStream("C:\\GCVSEL\\MYMAVEN\\src\\main\\java\\com\\att\\config\\config.properties");
			propv.load(fi);
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}	
	
	//Method: Initialization of given browser and setting prerequisites of the test browser.
	public static void initialization() {
	String browserName = propv.getProperty("browser");
	if(browserName.equals("chrome")) {
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
	}else if(browserName.equals("FF")) {
		System.setProperty("webdriver.gecko.driver", "C:\\geckodriver\\geckodriver.exe");
		driver = new FirefoxDriver();
	}else if(browserName.equals("IE")) {
		System.setProperty("webdriver.ie.driver", "C:\\IEDriverServer_x64_3.14.0\\IEDriverServer.exe");
		driver = new InternetExplorerDriver();
	}
	driver.manage().window().maximize();
	driver.manage().timeouts().pageLoadTimeout(TestUtil.PageLoadTimeOut,TimeUnit.SECONDS);
	driver.manage().timeouts().implicitlyWait(TestUtil.ImplicitTimeOut, TimeUnit.SECONDS);
	driver.manage().deleteAllCookies();
	driver.get(propv.getProperty("url"));
	

	}
	
	//Method: to click on main menu and sub menu by using mouse actions
	public void clickOnSubMenu(WebDriver driver, String mainMenuXpath, String subMenuXpath) {
		WebElement tu_mainMenu = driver.findElement(By.xpath(mainMenuXpath));
		WebElement tu_subMenu = driver.findElement(By.xpath(subMenuXpath));
		
		Actions act = new Actions(driver);
		act.moveToElement(tu_mainMenu).click().build().perform();
		act.click(tu_subMenu).build().perform();
	}
	
	//Method: select list item as per given list item text
	public void selectSingleDropDownItem(WebDriver driver, WebElement attSelectCustListBox, String custListOption) {
		Select dropDownElement = new Select(attSelectCustListBox);
		dropDownElement.selectByVisibleText(custListOption);
	}
	
	//Method: to get today's date in MMM dd, yyyy format.
	public String getTodaysDate() {
		  Date myDate = new Date();
	      System.out.println(myDate);
	      SimpleDateFormat sm = new SimpleDateFormat("MMM dd,yyyy");
	      String strDate = sm.format(myDate);
	      return strDate;
	}
	
	//Method: to generate Random string with 6 chars.
	protected String getSaltString() {
	        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	        StringBuilder salt = new StringBuilder();
	        Random rnd = new Random();
	        while (salt.length() < 6) { // length of the random string.
	            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
	            salt.append(SALTCHARS.charAt(index));
	        }
	        String saltStr = salt.toString();
	        return saltStr;

	    }
	
	//Method: Click on WebElement in the list of WebElements.
	public static void selectElementByListByTextOfAnElement(List<WebElement> AttAllReportTabNameList, String tabName) {
		int listSize = AttAllReportTabNameList.size();
		for(int i=0;i<listSize;i++)
		{
			if(AttAllReportTabNameList.get(i).getText().contains(tabName)) {
				AttAllReportTabNameList.get(i).click();
			}
		}
	}
	
	//Method: to switchTo - Child Window, getText of given WebElement and close.
	public String getTextAndCloseChildWindow(WebElement webElement) {
		String x = null;
		String mainWindow = driver.getWindowHandle();
		Set<String> allWindows = driver.getWindowHandles();
	
		for(String childWindow: allWindows) {
			if(!childWindow.equals(mainWindow)) {
				driver.switchTo().window(childWindow);
				x = webElement.getText();
				driver.close();
				driver.switchTo().window(mainWindow);
				return x;
		}
	}
	return x;

	}
	
	//Method: to switchTo - Child Window and close.
	public void closeChildWindow() {
		String mainWindow = driver.getWindowHandle();
		Set<String> allWindows = driver.getWindowHandles();
	
		for(String childWindow: allWindows) {
			if(!childWindow.equals(mainWindow)) {
				driver.switchTo().window(childWindow);
				driver.close();
				driver.switchTo().window(mainWindow);
			}
		}

	}
	
	
	//Method: to switchTo - Child Window get Window title and close.
	public String getTitleAndCloseChildWindow() {
		String x = null;
		String mainWindow = driver.getWindowHandle();
		Set<String> allWindows = driver.getWindowHandles();
	
		for(String childWindow: allWindows) {
			if(!childWindow.equals(mainWindow)) {
				driver.switchTo().window(childWindow);
				x = driver.getTitle();
				driver.close();
				driver.switchTo().window(mainWindow);
				return x;
			}
		}
		return x;
	}
	
	
	//Method: to switchTo - Child Window.
	public void switchToChildWindow() {
		String mainWindow = driver.getWindowHandle();
		Set<String> allWindows = driver.getWindowHandles();
	
		for(String childWindow: allWindows) {
			if(!childWindow.equals(mainWindow)) {
				driver.switchTo().window(childWindow);
			}
		}

	}
	
	//Method: to switchTo - Main Window. [use after using switch to switchToChildWindow()]
	public void switchToMainWindow() {
		String childWindow = driver.getWindowHandle();
		Set<String> allWindows = driver.getWindowHandles();
	
		for(String mainWindow: allWindows) {
			if(!mainWindow.equals(childWindow)) {
				driver.switchTo().window(mainWindow);
			}
		}

	}

	//Method: to switchTo - ALert()
	public static void switchToAlert() {
					try {
						driver.switchTo().alert();
					}catch(NoAlertPresentException e) {
						e.printStackTrace();
					}
	}
	
	//Method: to switchTo - ALert() and Accept.
	public static void switchToAlertAndAccept() {
					try {
						Alert a = driver.switchTo().alert();
						a.accept();
					}catch(NoAlertPresentException e) {
						e.printStackTrace();
					}
	}
	
	//Method: to switchTo - Frame()
		public static void switchToFrame() {
						try {
							driver.switchTo().frame(0);
						}catch(NoSuchFrameException e) {
							e.printStackTrace();
						}
		}
	
	//getClipboard string (it gives cntr+c data/string in return)
		public String getClipboardText(){
			String x = null;
			try {
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				Transferable contents = clipboard.getContents(null);
				x = (String) contents.getTransferData(DataFlavor.stringFlavor);
				return x;
				
			}catch(UnsupportedFlavorException e) {
				e.printStackTrace();
			}catch(IOException e) {
				e.printStackTrace();
			}
			return x;
		}
		
	//clearClipboard string (it set Clipboard as null)
		public void clearClipboardText() {
			StringSelection stringSelection = new StringSelection("");
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		}
}
