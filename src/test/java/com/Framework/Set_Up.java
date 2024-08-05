package com.Framework;

import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.Pages.HomePage;
import com.Pages.RevenueCalculatorPage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Set_Up {
	public static WebDriver driver;
	public static ExtentReports extent;
	public static ExtentSparkReporter spark;
	public static String extentReportPath, csvlogFilePath;
	public static ExtentTest test;
	private static long start;
	private static long end;

	public static Functions functions;
	public static HomePage homePage;
	public static RevenueCalculatorPage revenueCalculatorPage;

	// Browser open
	@BeforeMethod
	@Parameters(value = { "browser" })
	public static void setup(String browser) {
		launchBrowser(browser);
		pagesInistilition();
	}

	// Browser close
	@AfterMethod
	public static void closeBrowser() {
		try {
			driver.close();
		} catch (Exception e) {
			driver.quit();
		}

	}

	// Extent report start
	@BeforeSuite
	public static void extentReportStart() {
		start = Calendar.getInstance().getTimeInMillis();
		try {
			String day = new SimpleDateFormat("dd_MMMM_yyyy").format(new Date());
			String time = new SimpleDateFormat("_HH_mm_ss").format(new Date());

			String resultPath = System.getProperty("user.dir") + File.separator + "Results" + File.separator;

			new File(resultPath + "HTML_Report" + File.separator).mkdirs();
//			new File(resultPath + "CSV_Report" + File.separator).mkdirs();

			extentReportPath = resultPath + "HTML_Report" + File.separator + "Automation_Test_Report_" + day + time
					+ ".html";
//			csvlogFilePath = resultPath + "CSV_Report" + File.separator + "Automation_Test_Report_" + day + time
//					+ ".csv";
			extent = new ExtentReports();
			spark = new ExtentSparkReporter(extentReportPath);
			extent.attachReporter(spark);
			extent.setSystemInfo("Company Name", "Apmosys");
			extent.setSystemInfo("Enviroment", "Automation Testing");
			extent.setSystemInfo("Tester Name", "C.J.");
			extent.attachReporter(spark);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Extent report close
	@AfterSuite
	public static void extentReportEnd() {
		end = Calendar.getInstance().getTimeInMillis();
		System.out.println("time is : " + (end - start) / 1000 + " sec");
		try {
			extent.flush();
//			 opening extent report
			File extentReport = new File(extentReportPath);
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void launchBrowser(String browser) {
		try {
			if (browser.equalsIgnoreCase("Chrome")) {

				ChromeOptions options = new ChromeOptions();
//				options.setPageLoadStrategy(PageLoadStrategy.EAGER);
				options.addArguments(new String[] { "--disable-notifications" });
				options.addArguments(new String[] { "--remote-allow-origins=*" });
				options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));

//				options.addArguments("--enable-fast-renderer");
//				options.addArguments("--disable-background-timer-throttling");
//				options.addArguments("--disable-backgrounding-occluded-windows");
//				options.addArguments("--enable-prefetch");
//				options.addArguments("--disable-gpu");
//				options.addArguments("--no-sandbox");
//				options.addArguments("--disable-dev-shm-usage");
//				options.addArguments("--window-size=1024,768");

				driver = new ChromeDriver(options);
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			} else if (browser.equalsIgnoreCase("FF")) {

				FirefoxOptions options = new FirefoxOptions();
				options.addArguments(new String[] { "--disable-notifications" });
//				options.addArguments(new String[] { "--remote-allow-origins=*" });

				driver = new FirefoxDriver(options);
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			} else {

				ChromeOptions options = new ChromeOptions();
				options.addArguments(new String[] { "--disable-notifications" });
				options.addArguments(new String[] { "--remote-allow-origins=*" });

				driver = new ChromeDriver(options);
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void pagesInistilition() {
		functions = new Functions();
		revenueCalculatorPage = new RevenueCalculatorPage(driver);
		homePage = new HomePage(driver);
	}
}
