package com.selenium.seleniumgrid;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginPage {
	public WebDriver driver=Driverclass.getDriver();
	public ReadExcel read;
	public CommonCode commons;

	@FindBy(id = Elements.Email)
	private WebElement Email;

	@FindBy(id = Elements.Password)
	private WebElement Password;

	@FindBy(id = Elements.Login_Btn)
	private WebElement Login_Btn;

	@FindBy(xpath = Elements.InvalidEmailAddress)
	private WebElement InvalidEmailAddress;

	public LoginPage() {

		PageFactory.initElements(driver, this);
		read = new ReadExcel();
		commons = new CommonCode();
	}

	public void emailaddress(String email) {
		commons.fluentWait(By.id(Elements.Email));
		Assert.assertEquals(driver.getTitle(), read.readData("Login_Title"));
		Email.isDisplayed();
		Email.clear();
		Email.sendKeys(email);

	}

	public void password(String password) {
		Password.isDisplayed();
		Password.clear();
		Password.sendKeys(password);

	}

	public void login() {

		Assert.assertTrue(Login_Btn.isEnabled());
		Login_Btn.click();
	}

	public String invalidEmailAddress() {
		return InvalidEmailAddress.getText();

	}

}
