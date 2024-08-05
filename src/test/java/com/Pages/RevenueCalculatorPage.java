package com.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.Framework.Set_Up;

public class RevenueCalculatorPage extends Set_Up {
	static WebDriver driver;

	public RevenueCalculatorPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// web elements
	private By inputBox = By.xpath(
			"//input[@class='MuiInputBase-input MuiOutlinedInput-input MuiInputBase-inputSizeSmall css-1o6z5ng' and @type='number']");

	private By sliderElement = By.xpath("//input[@type='range']");

	private By totalRecurring = By
			.xpath("(//p[@class='MuiTypography-root MuiTypography-body1 inter css-hocx5c'])[last()]");

	private By cpt99091 = By.xpath("//p[text()='CPT-99091']/parent::div/label/span/input");

	private By cpt99453 = By.xpath("//p[text()='CPT-99453']/parent::div/label/span/input");

	private By cpt99454 = By.xpath("//p[text()='CPT-99454']/parent::div/label/span/input");

	private By cpt99474 = By.xpath("//p[text()='CPT-99474']/parent::div/label/span/input");

//	 All actions -----------------------------------------------
	public void scrollUpToSlider() {
		functions.scrollTillMiddle(functions.waitForElement(inputBox));
	}

	public void sendValueInTextBox(String val) {
		functions.waitTime(1000);
		functions.actionClick(functions.waitForElement(inputBox));
		functions.waitTime(1000);
		functions.robotClear(5);
		functions.pressKeyboardBtn(val);
	}

	public void dragTheSlider(int x, int y, int value) {
		functions.clickAndHold(sliderElement, x, y, value);
	}

	public void visibilityOfSlider() {
		functions.checkVisibility(functions.waitForElement(sliderElement), "True");
	}

	public void clickOncpt99091() {
		WebElement ele = functions.waitForElement(cpt99091);
		functions.scrollTillMiddle(ele);
		functions.click(ele);
	}

	public void clickOncpt99453() {
		WebElement ele = functions.waitForElement(cpt99453);
		functions.scrollTillMiddle(ele);
		functions.click(ele);
	}

	public void clickOncpt99454() {
		WebElement ele = functions.waitForElement(cpt99454);
		functions.scrollTillMiddle(ele);
		functions.click(ele);
	}

	public void clickOncpt99474() {
		WebElement ele = functions.waitForElement(cpt99474);
		functions.scrollTillMiddle(ele);
		functions.click(ele);
	}

	public void validateTotalRecurring(String amount) {
		String value = functions.waitForElement(totalRecurring).getText();
		System.out.println("Total Recurring Reimbursement : " + value);
		functions.saveResult(value, amount);
		;
	}

}
