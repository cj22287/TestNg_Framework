package com.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.Framework.Set_Up;

public class HomePage extends Set_Up {
	static WebDriver driver;

	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// web elements
	private static By revenueBtn = By.xpath("//div[contains(text(),'Revenue Calculator')]");

	public void clickOnRevenueBtn() {

		functions.waitForElement(revenueBtn).click();

	}

}
