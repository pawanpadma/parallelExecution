package com.selenium.seleniumgrid.test;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.selenium.seleniumgrid.CommonCode;
import com.selenium.seleniumgrid.Driverclass;
import com.selenium.seleniumgrid.Home;
import com.selenium.seleniumgrid.LoginPage;
import com.selenium.seleniumgrid.MyAccount;
import com.selenium.seleniumgrid.ReadExcel;
import com.selenium.seleniumgrid.Retry;


public class SanityTest extends Driverclass {
	public MyAccount account;
	public CommonCode commons;
	public Home home;
	public LoginPage login;
	public ReadExcel read;

	public SanityTest() {

	}

	public void initObject() {
		commons = new CommonCode();
		account = new MyAccount();
		home = new Home();
		login = new LoginPage();
		read = new ReadExcel();

	}

	
	@Parameters(value = { "browser" })
	@BeforeClass(alwaysRun = true)
	public void invokeURL(String browser) {
		System.out.println("browser is   "+browser);
		initBrowser(browser);
		initObject();

			}

	@BeforeMethod(alwaysRun = true)
	public void login() {
		home.login();
		login.emailaddress(read.readData("Email"));
		login.password(read.readData("password"));
		login.login();
		System.out.println("Before Method loggedin");

	}

	@AfterMethod(alwaysRun = true)
	public void HomePage() {
		try {
			//Thread.sleep(3000);

			home.navigate_to_Home();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		account.clickLogout();

	}

	@AfterClass(alwaysRun = true)
	public void closeBrowser() {

		getDriver().quit();

	}

	@Test(testName = "login_Positive", description = "login_Positive", timeOut = 190000, enabled = true, groups = {
			"sanity", "1" }, retryAnalyzer = Retry.class)
	public void alogin_Positive() {
		Assert.assertTrue(true);

	}

	@DataProvider(name = "DP1")
	public Object[][] createData1() throws Exception {
		Object[][] retObjArr = commons.getTableArray(
				System.getProperty("user.dir") + "/src/test/resources/DataDriven.xls", "DataArray",
				"InvalidCredentials");
		return (retObjArr);
	}

	@Test(testName = "login_Negative", description = "login_Negative", timeOut = 190000, enabled = true, groups = {
			"sanity", "2" }, dataProvider = "DP1")
	public void login_Negative(String userName, String Password) {

		account.ifLogoutExists();
		home.login();
		login.emailaddress(userName);
		login.password(Password);
		login.login();
		Assert.assertEquals(read.readData("Login_Error"), login.invalidEmailAddress(),
				"Invalid email address test case failed");
		home.login();
		login.emailaddress(read.readData("Email"));
		login.password(read.readData("password"));
		login.login();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

	}

	/*
	 * @Test(testName = "searchProduct", description = "searchProduct", timeOut =
	 * 190000, enabled = true, groups = { "sanity", "3" },retryAnalyzer =
	 * Retry.class) public void searchProduct() {
	 * account.verifyProduct(read.readData("Product_ipod"),
	 * read.readData("ResultsDisplayed"), read.readData("iPod"));
	 * 
	 * }
	 * 
	 * @Test(testName = "add2Cart", description = "add2Cart", timeOut = 190000,
	 * enabled = true, groups = { "sanity", "4" },retryAnalyzer = Retry.class)
	 * public void add2Cart() { account.cartData(read.readData("Product_ipod"),
	 * read.readData("ResultsDisplayed"), read.readData("iPod"),
	 * read.readData("Shoppingcartsummary"), read.readData("CartEmpty")); }
	 * 
	 * @Test(testName = "deleteFromCart", description = "deleteFromCart", timeOut =
	 * 190000, enabled = true, groups = { "sanity", "5" },retryAnalyzer =
	 * Retry.class) public void deleteFromCart() {
	 * account.deleteFromCart(read.readData("Product_ipod"),
	 * read.readData("ResultsDisplayed"), read.readData("iPod"),
	 * read.readData("Shoppingcartsummary"), read.readData("CartEmpty")); }
	 * 
	 * @Test(testName = "addtoAddress", description = "addtoAddress", timeOut =
	 * 190000, enabled = true, groups = { "sanity", "6" },retryAnalyzer =
	 * Retry.class) public void addtoAddress() {
	 * account.cartAddress(read.readData("Product_ipod"),
	 * read.readData("ResultsDisplayed"), read.readData("iPod"),
	 * read.readData("Shoppingcartsummary")); }
	 * 
	 * @Test(testName = "cartShipping", description = "cartShipping", timeOut =
	 * 190000, enabled = true, groups = { "sanity", "7" },retryAnalyzer =
	 * Retry.class) public void cartShipping() {
	 * account.cartShipping(read.readData("Product_ipod"),
	 * read.readData("ResultsDisplayed"), read.readData("iPod"),
	 * read.readData("Shoppingcartsummary")); }
	 * 
	 * @Test(testName = "cartPayMent", description = "cartPayMent", timeOut =
	 * 190000, enabled = true, groups = { "sanity", "8" },retryAnalyzer =
	 * Retry.class) public void cartPayMent() {
	 * account.cartPayMent(read.readData("Product_ipod"),
	 * read.readData("ResultsDisplayed"), read.readData("iPod"),
	 * read.readData("Shoppingcartsummary"), read.readData("iPod"),
	 * read.readData("ProductAmount")); }
	 * 
	 * @Test(testName = "cartSubmission", description = "cartSubmission", timeOut =
	 * 190000, enabled = true, groups = { "sanity", "9" },retryAnalyzer =
	 * Retry.class) public void cartSubmission() {
	 * account.cartSumbission(read.readData("Product_ipod"),
	 * read.readData("ResultsDisplayed"), read.readData("iPod"),
	 * read.readData("Shoppingcartsummary"), read.readData("iPod"),
	 * read.readData("ProductAmount")); }
	 */

}
