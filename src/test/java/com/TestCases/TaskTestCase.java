package com.TestCases;

import org.testng.annotations.Test;

import com.Framework.Set_Up;

public class TaskTestCase extends Set_Up {

	@Test(priority = 1)
	public void TestCase_1() {
		test = extent.createTest("Verifying the revenue calculator slider is movable");
		try {
			test.info("Open the web browser and navigate to FitPeo Homepage.");
			functions.browseUrl();

			test.info("From the homepage, navigate to the Revenue Calculator Page.");
			homePage.clickOnRevenueBtn();

			test.info("Scroll down the page until the revenue calculator slider is visible.");
			revenueCalculatorPage.scrollUpToSlider();

			test.info("Adjusting the slider to set its value to around 820.");
			revenueCalculatorPage.dragTheSlider(14, 0, 820);

			test.info("check and verify the revenue calculator slider after drag action.");
			revenueCalculatorPage.visibilityOfSlider();

		} catch (Exception e) {
			e.printStackTrace();
			functions.saveResult("Pass", "Failed");
		}
	}

	@Test(priority = 2)
	public void TestCase_2() {
		test = extent.createTest(
				"Ensuring that when the value 560 is entered in the text field, the slider's position is updated");
		try {
			test.info("Open the web browser and navigate to FitPeo Homepage.");
			functions.browseUrl();

			test.info("From the homepage, navigate to the Revenue Calculator Page.");
			homePage.clickOnRevenueBtn();

			test.info("Scroll down the page until the revenue calculator slider is visible.");
			revenueCalculatorPage.scrollUpToSlider();

			test.info("Enter the value 560 in the text field");
			revenueCalculatorPage.sendValueInTextBox("560");

			test.info(
					"Ensuring that when the value 560 is entered in the text field, the slider's position is updated");
			revenueCalculatorPage.visibilityOfSlider();

		} catch (Exception e) {
			e.printStackTrace();
			functions.saveResult("Pass", "Failed");
		}
	}

	@Test(priority = 3)
	public void TestCase_3() {
		test = extent.createTest(
				"Verifying that the header displaying Total Recurring Reimbursement for all Patients value $110700.");
		try {
			test.info("Open the web browser and navigate to FitPeo Homepage.");
			functions.browseUrl();

			test.info("From the homepage, navigate to the Revenue Calculator Page.");
			homePage.clickOnRevenueBtn();

			test.info("Scroll down the page until the revenue calculator slider is visible.");
			revenueCalculatorPage.scrollUpToSlider();

			test.info("Enter the value 820 in the text field");
			revenueCalculatorPage.sendValueInTextBox("820");

			test.info("select the checkboxes for CPT-99091");
			revenueCalculatorPage.clickOncpt99091();

			test.info("select the checkboxes for CPT-99453");
			revenueCalculatorPage.clickOncpt99453();

			test.info("select the checkboxes for CPT-99454");
			revenueCalculatorPage.clickOncpt99454();

			test.info("select the checkboxes for CPT-99474");
			revenueCalculatorPage.clickOncpt99474();

			test.info(
					"Verify that the header displaying Total Recurring Reimbursement for all Patients Per Month: shows the value $110700.");
			revenueCalculatorPage.validateTotalRecurring("$110700");

		} catch (Exception e) {
			e.printStackTrace();
			functions.saveResult("Pass", "Failed");
		}
	}

}
