package com.att.pom;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.att.base.BaseTest;


public class HomePOM extends BaseTest{
	
	LeaveTypePOM leaveTypePOM = new LeaveTypePOM();
	BillingTypePOM billingTypePOM = new BillingTypePOM();
	LicensesPOM licensesPOM = new LicensesPOM();
	LogoAndColorSchemePOM logoAndColorSchemePOM = new LogoAndColorSchemePOM();
	
	@FindBy(xpath="//a[@id='profile-link']")
	WebElement AttProfileName;
	
	@FindBy(xpath="//a[@class='content tasks']")
	WebElement AttTasksTab;
	
	@FindBy(xpath="//a[text()='Open Tasks']")
	WebElement AttTasksTabOpenTask;
	
	@FindBy(xpath="//div[text()='Reports']")
	WebElement AttReportTab;
	
	@FindBy(xpath="//a[@class='content reports']")
	WebElement AttReportsTab;
	
	@FindBy(xpath="//a[@class='dashedLink']")
	WebElement AttNewIcon;
	
	@FindBy(xpath="//td[contains(text(),'Create New Tasks')]")
	WebElement AttChildWindowTitle;
	
	String settingsMainMenuXpath ="//div[@class='popup_menu_icon settings_icon']";
	String settingsSubLeaveType ="//a[text()='Leave Types']";
	String settingsSubBillingType ="//a[text()='Billing Types']";
	
	
	String addOnMainXpath="//div[@class='popup_menu_icon add_ons_icon']";
	String addOnSubXpath="//a[@class='item_link has_icon mobile_icon']";
	
	
	String helpMainXpath ="//div[@class='popup_menu_icon support_icon']";
	String helpSubXpath="//a[text()='About actiTIME']";
	
	
	
	//Initialization
	public HomePOM() {
		PageFactory.initElements(driver, this);
	}
	
	//Actions
	public String validateProfileName() {
		return AttProfileName.getText();
	}
	
	public ReportsPOM validateReportsTab() {
		AttReportTab.click();
		return new ReportsPOM();
	}
	
	public TasksPOM validateTaskTab() {
		AttTasksTab.click();
		return new TasksPOM();
	}
	
	public TasksPOM validateTaskClickOnOpenTask() {
		AttTasksTabOpenTask.click();
		return new TasksPOM();
	}
	
	//Methods to select Menu Pages
	public String selectLeaveTypeMenu() {
		clickOnSubMenu(driver, settingsMainMenuXpath,settingsSubLeaveType);
		String x = leaveTypePOM.validateLeaveTypePageName();
		return x;
	}
	
	public String selectBillingTypeMenu() {
		clickOnSubMenu(driver, settingsMainMenuXpath,settingsSubBillingType);
		String x = billingTypePOM.validateBillingTypePageName();
		return x;
	}
	
	//This methods is used to access Settings Menu's Sub Menu options.
	public String validateSelectSettingsMenuByValue(String subMenu) {
		
		String subMenuXpath ="//a[text()='"+subMenu+"']";
		clickOnSubMenu(driver, settingsMainMenuXpath, subMenuXpath);

		if(subMenu.equals("Leave Types"))
		{
			return leaveTypePOM.validateLeaveTypePageName();
		}else if(subMenu.equals("Billing Types")){
			return billingTypePOM.validateBillingTypePageName();
		}else if(subMenu.equals("Licenses")) {
			return licensesPOM.validateLicensesPageName();
		}else if(subMenu.equals("Logo & Color Scheme")) {
			return logoAndColorSchemePOM.validateLogoAndColorSchemePageName();
		}else {
			System.out.println("Invalide SubMenu Option");
			return "Invalid Submenu";
		}
	}
	
	public String validateClickOnNewIconGetTitleFromChildWindow() {
		AttNewIcon.click();
		String x = getTitleAndCloseChildWindow();
		return x;
	}

	public String validateClickOnNewIconGetTitleTextAndCloseChildWindow() {
		AttNewIcon.click();
		String x = getTextAndCloseChildWindow(AttChildWindowTitle);
		return x;
	}
	
}
