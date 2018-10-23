package com.att.pom;




import java.util.List;


import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.att.base.BaseTest;


public class CreateNewTaskPOM extends BaseTest {
	
	@FindBy(xpath="//td[@class='pagetitle']")
	WebElement AttNewTaskPageName;
	
	@FindBy(xpath="//select[@name='customerId']")
	WebElement AttSelectCustomerName;
	
	@FindBy(xpath="//select[@name='customerId']")
	WebElement AttSelectCustListBox;
	
	@FindBy(xpath="//select[@name='projectId']")
	WebElement AttSelectProjectListBox;
	
	@FindBy(xpath="//select[@name='customerId']/option")
	WebElement AttSelectCustListOption;
	
	
	@FindBy(xpath="//select/option[@value='-2']")
	WebElement AttSelectNewCustomerOptionFromListBox;
	
	@FindBy(xpath="//span[@class='errormsg']")
	WebElement AttTaskCreateErrorMsgWhenNothing;
	
	@FindBy(xpath="//div[@class='errormsg']")
	WebElement AttTaskCreateErrorMsgOnlyWhenGivenTaskName;
	
	@FindBy(xpath="//input[@name='customerName']")
	WebElement AttCustomerNameTextBox;
	
	@FindBy(xpath="//input[@name='projectName']")
	WebElement AttProjectNameTextBox;
	
	@FindBy(xpath="//input[@id='task[0].name']")
	WebElement AttTaskNameTextBox;
	
	@FindBy(xpath="//input[@id='task[0].budgetedTimeStr']")
	WebElement AttBudgetTimeTextBox;
	
	@FindBy(xpath="//input[@id='task[0].deadline']")
	WebElement AttEnterDateInDateField;
	
	
	@FindBy(xpath="//select[@name='task[0].billingType']/option[@value='1']")
	WebElement AttSelectBillableBillingType;
	
	@FindBy(xpath="//select[@name='task[0].billingType']/option[@value='2']")
	WebElement AttSelectNonBillableBillingType;
	
	@FindBy(xpath="//input[@id='task[0].markedToBeAddedToUserTasks']")
	WebElement AttSelectCheckBoxMarkToBeAdd;
	
	@FindBy(xpath="//input[@value='Create Tasks']")
	WebElement AttCreateTaskBtn;
	
	@FindBy(xpath="//select[@id='task[0].billingType']")
	WebElement AttSelectBillingType;
	
	@FindBy(xpath="(//label[contains(text(),'Show')])[1]")
	WebElement AttShowRadioBtn1;
	
	@FindBy(xpath="(//label[contains(text(),'Show')])[2]")
	WebElement AttShowRadioBtn2;
	
	@FindBy(xpath="(//label[contains(text(),'Show')])[3]")
	WebElement AttShowRadioBtn3;
	
	@FindBy(xpath="//label[contains(text(),'Show list of open tasks')]")
	WebElement AttDefaultShowRadioBtn;
	
	@FindBy(xpath="//a[contains(text(),'rows')]")
	List<WebElement> AttRowLinkTextList;
	
	@FindBy(xpath="//td[@id='task[0].cell']/parent::tr/child::td/child::a")
	WebElement AttClearTaskIcon;
	
	//Initialization
	public CreateNewTaskPOM() {
		PageFactory.initElements(driver, this);
	}
	
	//Actions
	public String validateCreateNewTaskPageName() {
		String x = AttNewTaskPageName.getText();
		return x;
	}
	
	public void validateSelectCustomerName(String custName) {
		AttSelectCustomerName.sendKeys(custName);
	}
	
	//create customer and task
	public void validateCreateCustomerAndTask() {
		AttSelectNewCustomerOptionFromListBox.click();
		AttCustomerNameTextBox.sendKeys("TestCust");
		AttProjectNameTextBox.sendKeys("TestProject");
		AttTaskNameTextBox.sendKeys("Tast1WCNP");
		AttBudgetTimeTextBox.sendKeys("3:00");
		AttEnterDateInDateField.sendKeys(Keys.ENTER,"Jan 27, 2019");
		AttSelectBillableBillingType.click();
		AttSelectCheckBoxMarkToBeAdd.click();
		AttCreateTaskBtn.click();
	}
	
	//New Customer and New Project
	public void validate_Billable_Task_Using_New_Customer_And_New_Project(String custListOption,String custListName, String projectName,String taskName,String taskBudgetTime,String billingType) {
		selectSingleDropDownItem(driver, AttSelectCustListBox, custListOption);
		AttCustomerNameTextBox.sendKeys(custListName);
		AttProjectNameTextBox.sendKeys(projectName);
		AttTaskNameTextBox.sendKeys(taskName);
		AttBudgetTimeTextBox.sendKeys(taskBudgetTime);
		AttEnterDateInDateField.sendKeys(getTodaysDate());
		selectSingleDropDownItem(driver, AttSelectBillingType, billingType);
		AttSelectCheckBoxMarkToBeAdd.click();
		AttCreateTaskBtn.click();
		
	}
	//Existing Project and Existing Customer
	public void validate_Billable_Task_Using_Existing_Customer_And_Existing_Project(String custListOption,String projectName,String taskName,String taskBudgetTime,String billingType) {
		selectSingleDropDownItem(driver, AttSelectCustListBox, custListOption);
		selectSingleDropDownItem(driver, AttSelectProjectListBox, projectName);

		AttTaskNameTextBox.sendKeys(taskName);
		AttBudgetTimeTextBox.sendKeys(taskBudgetTime);
		AttEnterDateInDateField.sendKeys(getTodaysDate());
		selectSingleDropDownItem(driver, AttSelectBillingType, billingType);
		AttSelectCheckBoxMarkToBeAdd.click();
		AttCreateTaskBtn.click();
		
	}
	
	//Negative Test Create task with Nothing.
	public String validate_Task_Create_By_Nothing() throws InterruptedException {
		AttCreateTaskBtn.click();
		Thread.sleep(400);
		return AttTaskCreateErrorMsgWhenNothing.getText();
		
	}
	
	//Negative Test Create task without Customer / Project.
	public boolean validate_Task_Create_Without_Customer_or_Project(String custListOption,String projectName, String taskName) throws InterruptedException {
		
		if(!custListOption.equals("")) {
		selectSingleDropDownItem(driver, AttSelectCustListBox, custListOption);
		}
		if(!projectName.equals("")) {
			selectSingleDropDownItem(driver, AttSelectProjectListBox, projectName);
		}
		AttTaskNameTextBox.sendKeys(taskName);
		AttCreateTaskBtn.click();
		Thread.sleep(400);
		String x = AttTaskCreateErrorMsgOnlyWhenGivenTaskName.getText();
		if(x.contains("highlighted in red")) {
			return true;
		}else {
			return false;
		}
		
	}
	
	//All Project and All Customer
	public void validate_Billable_Task_Using_All_Customer_And_All_Project(String custListOption,String projectName,String taskName,String taskBudgetTime,String billingType) {
		selectSingleDropDownItem(driver, AttSelectCustListBox, custListOption);
		selectSingleDropDownItem(driver, AttSelectProjectListBox, projectName);
		AttTaskNameTextBox.sendKeys(taskName);
		AttBudgetTimeTextBox.sendKeys(taskBudgetTime);
		AttCreateTaskBtn.click();
		
	}
	
	//Validate Row Links Text
	public boolean validateRowLinkText() {
		boolean flag=false;
		for(int i=0;i<AttRowLinkTextList.size();i++)
		{
			String linkName = AttRowLinkTextList.get(i).getText();
			if(linkName.equals("5 rows")||linkName.equals("10 rows")||linkName.equals("15 rows")) {
				flag =true;
			}else {
				flag = false;
				break;
			}
		}
		return flag;
	}
		
	//Validate Clear with Existing Project and Existing Customer
		public boolean validate_Clear_Task_With_Task_Using_Existing_Customer_And_Existing_Project(String custListOption,String projectName,String taskName) {
			selectSingleDropDownItem(driver, AttSelectCustListBox, custListOption);
			selectSingleDropDownItem(driver, AttSelectProjectListBox, projectName);
			AttTaskNameTextBox.sendKeys(taskName);
			AttClearTaskIcon.click();
			switchToAlertAndAccept();
			clearClipboardText();
			AttTaskNameTextBox.sendKeys(Keys.CONTROL,"ac");
			String x = getClipboardText();
			if(!x.equals(taskName)) {
				return true;
			}else {
				return false;
			}
			
		}
		
	
	//Validate default Radio button selection.??????NEED TO WORK ON...
	public boolean validateDefaultRadioButtonSelection() {

		boolean b = AttDefaultShowRadioBtn.isSelected();
		System.out.println("VISHNU its getting value as:..........."+b);
		if(b){
			return true;
		}else {
			System.out.println("Invalid Default redio button selection");
			return false;
		}
	}
		
		
}



