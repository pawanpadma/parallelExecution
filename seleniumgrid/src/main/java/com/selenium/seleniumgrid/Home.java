package com.selenium.seleniumgrid;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;


public class Home {

	public WebDriver driver=Driverclass.getDriver();
	public ReadExcel read;
	public CommonCode commons;

	@FindBy(xpath = Elements.Login)
	private WebElement Login;

	@FindBy(className = Elements.Image_Logo)
	private WebElement Image_Logo;

	public Home() {

		PageFactory.initElements(driver, this);
		read = new ReadExcel();
		commons = new CommonCode();
	}

	public void login() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertTrue(Login.isDisplayed(), "Login Button Not Found");
		Login.click();

	}

	public void navigate_to_Home() {
		Image_Logo.click();

	}

}
