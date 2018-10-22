package com.att.testcases;

import org.testng.Assert;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.att.base.BaseTest;
import com.att.pom.HomePOM;
import com.att.pom.LoginPOM;
import com.att.pom.ReportsPOM;
import com.att.pom.TasksPOM;



public class HomeTest extends BaseTest{
	
	LoginPOM loginPOM;
	HomePOM homePOM;
	TasksPOM tasksPOM;
	ReportsPOM reportsPOM;
	
	public HomeTest() {
		super();
	}

	@BeforeMethod
	public void setUP() {
		initialization();
		loginPOM = new LoginPOM();
		homePOM = loginPOM.validateAttLogin(propv.getProperty("username"),propv.getProperty("password"));
		
	}
	
	//Basic Home page test methods of HomeTest
	@Test
	public void verifyProfileName() {
		String x = homePOM.validateProfileName();
		Assert.assertEquals(x, "user1 user1", "User name not found");
	}
	
	
	@Test
	public void verifyReportTab() {
		homePOM.validateReportsTab();
	}
	
	@Test
	public void verifyTaskTabClick() {
		homePOM.validateTaskTab();
	}
	
	//Setting Menu options Validation test cases:

	@Test
	public void verifySelectSettingsLeaveTypesMenu() {
		String x = homePOM.validateSelectSettingsMenuByValue("Leave Types");
		Assert.assertEquals(x, "Leave Types","Page Name Mismatch");
	}
	
	@Test
	public void verifySelectSettingsBillingTypesMenu() {
		String x = homePOM.validateSelectSettingsMenuByValue("Billing Types");
		Assert.assertEquals(x, "Billing Types","Page Name Mismatch");
	}
	
	@Test
	public void verifySelectSettingsLicensesMenu() {
		String x = homePOM.validateSelectSettingsMenuByValue("Licenses");
		Assert.assertEquals(x, "Licenses","Page Name Mismatch");
	}
	
	@Test
	public void verifySelectSettingsLogoAndColorSchemeMenu() {
		String x = homePOM.validateSelectSettingsMenuByValue("Logo & Color Scheme");
		Assert.assertEquals(x, "Logo & Color Scheme","Page Name Mismatch");
	}
	
	@Test
	public void verifyClickOnNewIcon() {
		String x = homePOM.validateClickOnNewIconGetTitleFromChildWindow();
		Assert.assertEquals(x, "actiTIME - Create New Tasks","ChildWindow Title Mismatch");
	}
	
	@Test
	public void verifyChickOnNewIconAndChildWindowTitle() {
		String x = homePOM.validateClickOnNewIconGetTitleTextAndCloseChildWindow();
		Assert.assertEquals(x, " Create New Tasks","ChildWindow Title Text Mismatch");
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
