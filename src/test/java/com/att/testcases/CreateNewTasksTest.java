package com.att.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.att.base.BaseTest;
import com.att.pom.CreateNewTaskPOM;
import com.att.pom.HomePOM;
import com.att.pom.LoginPOM;
import com.att.pom.ProjectAndCustomerPOM;
import com.att.pom.ReportsPOM;
import com.att.pom.TasksPOM;
import com.att.util.TestUtil;

public class CreateNewTasksTest extends BaseTest {
	
	public CreateNewTasksTest() {
		super();
	}
	
	LoginPOM loginPOM;
	HomePOM homePOM;
	TasksPOM tasksPOM;
	TestUtil testUtil;
	CreateNewTaskPOM createNewTasksPOM;
	ProjectAndCustomerPOM projectAndCustomerPOM;
	ReportsPOM reportsPOM;
	
	@BeforeMethod
	public void setUp() throws InterruptedException{
		initialization();
		testUtil = new TestUtil();
		loginPOM = new LoginPOM();
		tasksPOM = new TasksPOM();
		projectAndCustomerPOM = new ProjectAndCustomerPOM();
		createNewTasksPOM = new CreateNewTaskPOM();
		homePOM = loginPOM.validateAttLogin(propv.getProperty("username"),propv.getProperty("password"));
		homePOM.validateTaskClick();
		tasksPOM.validateCreateNewTasksBtnClick();
		Thread.sleep(2000);
	}
	
	@Test
	public void verifyCreateNewTasksPageName() {
		Assert.assertEquals(createNewTasksPOM.validateCreateNewTaskPageName(), " Create New Tasks", "Create New Tasks Page Name Mismatch");
		
	}
	
	@Test
	public void verifySelectCustomerName() {
		createNewTasksPOM.validateSelectCustomerName("Vishnu");
	}

	@Test
	public void verifyCustomerAndTaskCreateAndDelete() {
		createNewTasksPOM.validateCreateCustomerAndTask();
		String x = projectAndCustomerPOM.validateSelectedAndDeleteCustomer();
		Assert.assertEquals(x, "Selected customers and projects have been successfully deleted.", "Delete Unsuccessful");
	}
	

	@Test
	public void verify_Two_Records_Creation_Billable_Task_Using_New_Customer_And_New_Project() throws InterruptedException {
		createNewTasksPOM.validate_Billable_Task_Using_New_Customer_And_New_Project("-- new customer --","Sanatan","Jaana","Jodichhedi","2:00","Billable");
		Thread.sleep(200);
		projectAndCustomerPOM.validateDeleteCustomer("Sanatan");

		homePOM.validateTaskClickOnOpenTask();
		tasksPOM.validateCreateNewTasksBtnClick();
		createNewTasksPOM.validate_Billable_Task_Using_New_Customer_And_New_Project("-- new customer --","Vaishnavi","Jaani","Jodichhedi","2:00","Billable");
		Thread.sleep(200);
		projectAndCustomerPOM.validateDeleteCustomer("Vaishnavi");
	
	}

	//TEST CASE: I. Create Billable task using new customer and new project.
	@Test
	public void verify_Billable_Task_Using_New_Customer_And_New_Project() throws InterruptedException {
		createNewTasksPOM.validate_Billable_Task_Using_New_Customer_And_New_Project("-- new customer --","CustNameSanatan","PrjNameJaana","TaskNameJodichhedi","2:00","Billable");
		Thread.sleep(400);
		projectAndCustomerPOM.validateDeleteCustomer("CustNameSanatan");

	}
	
	//TEST CASE: II. Create Non-billable task using Existing Customer and Project.
	@Test
	public void verify_Create_Non_billable_task_using_Existing_Customer() throws InterruptedException {
		createNewTasksPOM.validate_Billable_Task_Using_Existing_Customer_And_Existing_Project("Vishnu","ROCKON","TaskJodichhedi","2:00","Non-Billable");
		Thread.sleep(200);
		tasksPOM.validateDeleteSelectedTask("TaskJodichhedi");
	}
	
	//TEST CASE: III. Create billable task in Existing Customer and Project.
	@Test
	public void verify_Create_Billable_task_using_Existing_Customer() throws InterruptedException {
		createNewTasksPOM.validate_Billable_Task_Using_Existing_Customer_And_Existing_Project("Vishnu","ROCKON","TaskJodichhedi","2:00","Billable");
		Thread.sleep(200);
		tasksPOM.validateDeleteSelectedTask("TaskJodichhedi");
	}
	
	//TEST CASE: IV. Create non-billable task with New customer and Project
	@Test
	public void verify_Non_Billable_Task_Using_New_Customer_And_New_Project() throws InterruptedException {
		createNewTasksPOM.validate_Billable_Task_Using_New_Customer_And_New_Project("-- new customer --","CustNameSanatan","PrjNameJaana","TaskNameJodichhedi","2:00","Non-Billable");
		Thread.sleep(400);
		projectAndCustomerPOM.validateDeleteCustomer("CustNameSanatan");

	}


	//TEST CASE: V. Verify Create new task by not entering Nothing.
	@Test
	public void verify_Task_Create_By_Nothing() throws InterruptedException {
		String x = createNewTasksPOM.validate_Task_Create_By_Nothing();
		Assert.assertEquals(x,"There are no tasks to create. Please fill in at least one row.","Error Mgs MisMatch");
	}
	
	
	//TEST CASE: VI. Verify Create new task by selecting all customers and all projects with Non-Billing.
	@Test
	public void verify_Billable_Task_Using_All_Customer_And_All_Project() throws InterruptedException {
		createNewTasksPOM.validate_Billable_Task_Using_All_Customer_And_All_Project("-- ALL ACTIVE CUSTOMERS --","-- ALL ACTIVE PROJECTS --","TaskJodichhedi","1:00","Non-Billable");
		Thread.sleep(200);
		tasksPOM.validateDeleteSelectedTask("TaskJodichhedi");
	}
	

	//TEST CASE: VII. Verify Create new task by selecting all customers and all projects with Billable.
	@Test
	public void verify_Non_Billable_Task_Using_All_Customer_And_All_Project() throws InterruptedException {
		createNewTasksPOM.validate_Billable_Task_Using_All_Customer_And_All_Project("-- ALL ACTIVE CUSTOMERS --","-- ALL ACTIVE PROJECTS --","TaskJodichhedi","1:00","Billable");
		Thread.sleep(200);
		tasksPOM.validateDeleteSelectedTask("TaskJodichhedi");
	}
	
	//Validate Default Radio button selection in Create New Task page.
	@Test
	public void validateDefaultRadioButtonSelection() throws InterruptedException {
		createNewTasksPOM.validateDefaultRadioButtonSelection();
		Thread.sleep(200);
	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
