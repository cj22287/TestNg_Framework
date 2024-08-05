package com.Framework;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.TestUrl.Url;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class Functions extends Set_Up {

	private static String ScenarioID;
	private static String TestCaseType;
	private static String testCaseID;
	private static String url = Url.url;

	// sendkeys
	public void sendkeys(WebElement ele, String value) {
		ele.sendKeys(value);
	}

	// action click
	public void actionClick(WebElement ele) {
		new Actions(driver).click(ele).build().perform();

	}

	// robot claer
	public void robotClear(int count) {
		Robot rb = null;
		try {
			rb = new Robot();
		} catch (AWTException e) {
		}

		for (int i = 0; i <= count; i++) {
			rb.keyPress(KeyEvent.VK_BACK_SPACE);
		}

	}

	// sendkeys using javascript
	public void js_Sendkeys(WebElement ele, String value) {
		JavascriptExecutor jse = ((JavascriptExecutor) driver);
		jse.executeScript("arguments[0].value='" + value + "';", ele);
	}

	// click and senkeys
	public void clickAndSendKeys(WebElement ele, String value) {
		Actions act = new Actions(driver);
		act.click(ele).pause(Duration.ofSeconds(3)).sendKeys(value).build().perform();
	}

	// single senkeys
	public void singleSendkyes(WebElement ele, String value) {
		final char[] ch = new char[value.length()];
		for (int k = 0; k < value.length(); ++k) {
			ch[k] = value.charAt(k);
			ele.sendKeys(new CharSequence[] { String.valueOf(ch[k]) });
			waitTime(1000);
		}
	}

	public void waitTime(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
		}

	}

	public void pressKeyboardBtn(String input) {
		waitTime(2000);
		try {
			Robot robot = new Robot();

			for (char c : input.toCharArray()) {
				if (Character.isLowerCase(c)) {
					int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
					robot.keyPress(keyCode);
					robot.keyRelease(keyCode);
				} else if (c == '\'' || c == '.' || c == ',' || c == '/' || c == ';' || c == '[' || c == ']' || c == '-'
						|| c == '=') {
					int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
					robot.keyPress(keyCode);
					robot.keyRelease(keyCode);
				} else if (!Character.isLetterOrDigit(c)) {
					int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(keyCode);
					robot.keyRelease(keyCode);
					robot.keyRelease(KeyEvent.VK_SHIFT);
				} else if (Character.isDigit(c)) {
					int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
					robot.keyPress(keyCode);
					robot.keyRelease(keyCode);
				} else if (Character.isUpperCase(c)) {
					int keyCode = KeyEvent.getExtendedKeyCodeForChar(Character.toUpperCase(c));
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(keyCode);
					robot.keyRelease(keyCode);
					robot.keyRelease(KeyEvent.VK_SHIFT);
				}
				waitTime(1000);
			}
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	// click
	public void click(WebElement ele) {
		ele.click();
	}

	// click
	public void browseUrl() {
		driver.get(url);
	}

	// click using javascript
	public void js_Click(WebElement ele) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", ele);
	}

	// double click using javascript
	public void js_doubleClick(WebElement ele) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].dblclick();", ele);

	}

	// clear
	public void clear(WebElement ele) {
		ele.clear();
	}

	// clear using javascript
	public void js_Clear(WebElement ele) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].value='';", ele);
	}

	// for taking screenshots
	public String getScreenshot() {
		String ScFileLocation = null;
//		try {
//			// Get the current time
//			Date date = new Date();
//			SimpleDateFormat f = new SimpleDateFormat("MMM_dd_yyyy_HH_mm_ss");
//			String timestamp = f.format(date);
//
//			// Take a screenshot
//			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//			Rectangle screenRectangle = new Rectangle(screenSize);
//			Robot robot = null;
//			try {
//				robot = new Robot();
//			} catch (AWTException e) {
//				e.printStackTrace();
//			}
//			BufferedImage image = robot.createScreenCapture(screenRectangle);
//
//			String scPath = System.getProperty("user.dir") + File.separator + "Results" + File.separator + "Images"
//					+ File.separator;
//			new File(scPath).mkdirs();
//
//			String scName = "Screenshot_" + timestamp + ".png";
//			ScFileLocation = scPath + scName;
//
//			ImageIO.write(image, "png", new File(ScFileLocation));
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		try {
			ScFileLocation = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ScFileLocation;

	}

	// gettext and save result
	public void gettext(WebElement ele, String expectedResult) {
		String actualResult = ele.getText();
		saveResult(actualResult, expectedResult);

	}

//  to drag and drop an element from x to y location 
	public void dragAndDrop(WebElement ele, int x, int y) {
		Actions dnd = new Actions(driver);
		dnd.clickAndHold(ele).moveByOffset(x, y).build().perform();
	}

	public void clickAndHold(By sliderElement, int x, int y, int value) {
		WebElement ele = functions.waitForElement(sliderElement);
		Actions dnd = new Actions(driver);

		while (true) {
			dnd.clickAndHold(ele).moveByOffset(x, y).build().perform();
			functions.scrollTillMiddle(functions.waitForElement(sliderElement));
			waitTime(1000);
			ele = functions.waitForElement(sliderElement);
			String attributeValue = ele.getAttribute("value");
			System.out.println("Updated value : " + attributeValue);
			if (Integer.parseInt(attributeValue) >= value) {
				break;
			}

		}
	}

	// Get test data from sheet
	public Map<String, String> getDataFromSheet(String testCaseNo) {

		Connection connect = null;
		Fillo fillo = null;

		Map<String, String> allData = new HashMap<>();

		try {
			fillo = new Fillo();
			String sheetPath = System.getProperty("user.dir") + File.separator + "Test Data" + File.separator
					+ "Data Sheet.xlsx";
			connect = fillo.getConnection(sheetPath);

			String Query = "Select * from Sheet1 where Test_case='" + testCaseNo + "' and Run_Status='Y'";
			Recordset recordset = connect.executeQuery(Query);
			ArrayList<String> columnNames = recordset.getFieldNames();

			while (recordset.next()) {

				testCaseID = recordset.getField("Test_case");
				ScenarioID = recordset.getField("ScenarioID");
				TestCaseType = recordset.getField("TestCaseType");

//
//				if (testCase.equalsIgnoreCase(testCaseNo)) {
				for (int i = 0; i < columnNames.size(); i++) {
					allData.put(columnNames.get(i), recordset.getField(columnNames.get(i)));
				}
//				}
			}
			recordset.close();
			connect.close();
		} catch (FilloException e) {
			e.printStackTrace();
		}
		return allData;
	}

	// save result
	public void saveResult(String actualResult, String expectedResult) {

		int status = getStatus(actualResult, expectedResult);

//		File file = new File(csvlogFilePath);
//		FileWriter fileWritter = null;
//		BufferedWriter bufferWritter = null;
//
//		if (!file.exists()) {
//			try {
//				file.createNewFile();
//				fileWritter = new FileWriter(file, true);
//				bufferWritter = new BufferedWriter(fileWritter);
//
//				bufferWritter
//						.write("ScenarioID,TestCaseID,TestCaseType,ExpectedResult,ActualResult,Status,DateAndTime\n");
//			} catch (Exception e) {
//				e.printStackTrace();
//			} finally {
//				try {
//					bufferWritter.close();
//					fileWritter.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}

		try {
//			fileWritter = new FileWriter(file, true);
//			bufferWritter = new BufferedWriter(fileWritter);
			if (status == 1) {
				Set_Up.test.pass("<br><font color=\"Green\"><b>Expected Result is - </b></font>" + expectedResult
						+ " <br> <font color=\"Green\"><b>Actual Result is   - </b></font>" + actualResult + "<br>",
						MediaEntityBuilder.createScreenCaptureFromBase64String(getScreenshot()).build());

//				bufferWritter.write(ScenarioID + "," + testCaseID + "," + TestCaseType + "," + expectedResult + ","
//						+ actualResult + ",PASS," + new Date().toString() + "," + "\n");

			} else {
				Set_Up.test.fail("<br><font color=\"Red\"><b>Expected Result is - </b></font>" + expectedResult
						+ " <br> <font color=\"Red\"><b>Actual Result is   - </b></font>" + actualResult + "<br>",
						MediaEntityBuilder.createScreenCaptureFromBase64String(getScreenshot()).build());

//				bufferWritter.write(ScenarioID + "," + testCaseID + "," + TestCaseType + "," + expectedResult + ","
//						+ actualResult + ",FAIL," + new Date().toString() + "," + "\n");
			}
			Assert.assertEquals(actualResult.toUpperCase(), expectedResult.toUpperCase());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			try {
//				bufferWritter.close();
//				fileWritter.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}

		}
	}

	// check element is visible or not
	public void checkVisibility(WebElement ele, String expectedResult) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='2px solid green'", ele);
		} catch (Exception e) {
		}

		String actualResult = String.valueOf(ele.isDisplayed());
		saveResult(actualResult, expectedResult);

		try {
			waitTime(1000);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border=''", ele);
		} catch (Exception e) {
		}

	}

	// check Text is present on screen or not
	public void checkVisibilityByText(WebElement ele, String expectedResult) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='2px solid green'", ele);
		} catch (Exception e) {
		}

		String actualResult = ele.getText();
		saveResult(actualResult, expectedResult);

		try {
			waitTime(1000);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border=''", ele);
		} catch (Exception e) {
		}

	}

	// Dialogbox
	public void diaLogBox(WebElement ele) {
		String input = JOptionPane.showInputDialog(" Give Input ...");
		ele.sendKeys(input);
	}

	// get status
	public int getStatus(String actualResult, String expectedResult) {
		System.out.println("Result     ------------------>  " + actualResult + "|" + expectedResult);
		int sts = 0;
		if (actualResult.equalsIgnoreCase(expectedResult)) {
			sts = 1;
		}
		return sts;
	}

	// scroll Down
	public void scrollIntoView(WebElement ele) {

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", ele);
	}

	// mouseover on the given element.
	public void mouseOver(WebElement ele) {
		String script = "var event = new MouseEvent('mouseover', {view: window,bubbles: true,cancelable: true});arguments[0].dispatchEvent(event);";
		((JavascriptExecutor) driver).executeScript(script, ele);
	}

	// scroll down up element in center
	public void scrollTillMiddle(WebElement ele) {

		try {
			Thread.sleep(1000);
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("arguments[0].scrollIntoView({ block: 'center' ,inline :'nearest' });", ele);

		} catch (Exception e) {
			System.out.println("Enable to Scroll");
		}
	}

	// explicitly wait
	public WebElement waitForElement(By ele) {

		WebElement element = null;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		try {
			element = wait.until(ExpectedConditions.presenceOfElementLocated(ele));
//			System.out.println("Element is clickable using : elementToBeClickable");

		} catch (Exception e) {
			try {
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(ele));
//				System.out.println("Element is visible using : visibilityOfElementLocated");

			} catch (Exception e1) {
				try {
					element = wait.until(ExpectedConditions.elementToBeClickable(ele));
//					System.out.println("Element is presence using : presenceOfElementLocated");

				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}

		return element;

	}

	// get element using xpath or other locators
	public WebElement getWebElement(String locator, String value) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement webElement = null;
		switch (locator) {
		case "ID":
			webElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(value)));
		case "CLASSNAME":

			webElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.className(value)));
		case "NAME":
			webElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.name(value)));

		case "XPATH":
			webElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(value)));

		case "CSS":
			webElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(value)));
		case "LINKTEXT":
			webElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(value)));
		case "TAGNAME":
			webElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName(value)));
		case "PARTIALLINKTEXT":
			webElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(value)));
		default:
			webElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(value)));

		}

		return webElement;
	}

}
