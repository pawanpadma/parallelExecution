package com.selenium.seleniumgrid;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;



public class MyAccount {

	public WebDriver driver=Driverclass.getDriver();
	public ReadExcel read;
	public CommonCode commons;
	public Home home;

	@FindBy(className = Elements.Logout)
	private WebElement Logout;

	@FindBy(name = Elements.Search_Btn)
	private WebElement Search_Btn;

	@FindBy(id = Elements.Login_Btn)
	private WebElement Login_Btn;

	@FindBy(id = Elements.SearchBox)
	private WebElement SearchBox;

	@FindBy(xpath = Elements.ResultsDisplayed)
	private WebElement ResultsDisplayed;

	@FindBy(xpath = Elements.Ipodtouch)
	private WebElement Ipodtouch;

	@FindBy(xpath = Elements.IpodShuffle_amound)
	private WebElement IpodShuffle_amound;

	@FindBy(xpath = Elements.AddtoCart)
	private WebElement AddtoCart;

	@FindBy(xpath = Elements.CartDetails)
	private WebElement CartDetails;

	@FindBy(xpath = Elements.ShoppingCartSummary)
	private WebElement ShoppingCartSummary;

	@FindBy(xpath = Elements.productDescription)
	private WebElement productDescription;

	@FindBy(xpath = Elements.SummaryAmount)
	private WebElement SummaryAmount;

	@FindBy(xpath = Elements.DeleteBtn)
	private WebElement DeleteBtn;

	@FindBy(xpath = Elements.ContinueShopping)
	private WebElement ContinueShopping;

	@FindBy(xpath = Elements.AddressCheckBox)
	private WebElement AddressCheckBox;

	@FindBy(xpath = Elements.NextBtn)
	private WebElement NextBtn;

	@FindBy(name = Elements.AddressNext)
	private WebElement AddressNext;

	@FindBy(xpath = Elements.NewAddress)
	private WebElement NewAddress;

	@FindBy(id = Elements.TermsAndConditionsChkBox)
	private WebElement TermsAndConditionsChkBox;

	@FindBy(xpath = Elements.ProductDescription)
	private WebElement ProductDescription;

	@FindBy(xpath = Elements.ProductAmount)
	private WebElement ProductAmount;

	@FindBy(xpath = Elements.payByCheque)
	private WebElement payByCheque;

	@FindBy(xpath = Elements.payByCash)
	private WebElement payByCash;

	@FindBy(xpath = Elements.SubmitPayMentBtn)
	private WebElement SubmitPayMentBtn;

	@FindBy(name = Elements.ShippingNext)
	private WebElement ShippingNext;

	@FindBy(xpath = Elements.OrderSummary)
	private WebElement OrderSummary;

	@FindBy(xpath = Elements.ChequePayment)
	private WebElement ChequePayment;

	@FindBy(xpath = Elements.OtherPayMentModes)
	private WebElement OtherPayMentModes;

	@FindBy(xpath = Elements.ConfirmOrder)
	private WebElement ConfirmOrder;

	@FindBy(xpath = Elements.ErrorMessage)
	private WebElement ErrorMessage;

	@FindBy(xpath = Elements.SuccessMessage)
	private WebElement SuccessMessage;

	public MyAccount() {

		PageFactory.initElements(driver, this);
		read = new ReadExcel();
		commons = new CommonCode();
		home = new Home();
	}

	public void assertAccount() {
		Assert.assertEquals(driver.getTitle(), read.readData("MyAccount_Title"));
		Assert.assertTrue(Logout.isDisplayed(), "LogoutButton is not displayed");
		Assert.assertTrue(Search_Btn.isDisplayed(),
				"Search_Btn is not displayed");
	}

	public void ifLogoutExists() {
		if (Logout == null) {
			Login_Btn.click();
		} else {
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			clickLogout();
		}
	}

	public void clickLogout() {
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertTrue(Logout.isDisplayed());
		Logout.click();
		// Assert.assertFalse(Logout.isDisplayed());
		ExpectedCondition<Alert> s = ExpectedConditions.alertIsPresent();
		if (s.toString()!="alert to be present") {
			Alert alert = driver.switchTo().alert();
			// update is executed
			alert.accept();
			// Assert.assertTrue(false, "AlertBox available");
		}

	}

	public void searchProduct(String product) {
		Assert.assertTrue(SearchBox.isDisplayed());
		SearchBox.sendKeys(product);
		Search_Btn.click();
	}

	public void verifyProduct(String product, String ResultsDisplayd,
			String Ipod) {

		Assert.assertTrue(SearchBox.isDisplayed());
		SearchBox.sendKeys(product);
		Search_Btn.click();
		commons.fluentWait(By.xpath(Elements.ResultsDisplayed));
		Assert.assertEquals(ResultsDisplayed.getText(), ResultsDisplayd);
		Assert.assertEquals(Ipodtouch.getText(), Ipod);
		Assert.assertTrue(IpodShuffle_amound.isDisplayed());
		Assert.assertTrue(AddtoCart.isDisplayed());
		AddtoCart.click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ExpectedCondition<Alert> s = ExpectedConditions.alertIsPresent();
		if (s.toString()!="alert to be present") {
			Alert alert = driver.switchTo().alert();
			// update is executed
			alert.accept();
			// Assert.assertTrue(false, "AlertBox available");
		}
	}

	/*
	 * public void verifyProductLogut(String product, String ResultsDisplayd,
	 * String Ipod) { verifyProduct(product, ResultsDisplayd, Ipod);
	 * 
	 * try { Thread.sleep(3000); } catch (InterruptedException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); } Logout.click();
	 * Assert.assertFalse(Logout.isDisplayed()); }
	 */

	public void summaryPage(String ShoppingSummary) {

		Assert.assertTrue(CartDetails.isEnabled());
		Assert.assertTrue(CartDetails.isDisplayed());
		CartDetails.click();
		commons.fluentWait(By.xpath(Elements.CartDetails));
		Assert.assertEquals(ShoppingCartSummary.getText(), ShoppingSummary);
		Assert.assertTrue(productDescription.isDisplayed());
		Assert.assertTrue(SummaryAmount.isDisplayed());
		Assert.assertTrue(DeleteBtn.isDisplayed());
		Assert.assertTrue(DeleteBtn.isEnabled());
		Assert.assertTrue(ContinueShopping.isDisplayed());
		Assert.assertTrue(ContinueShopping.isEnabled());
	}

	public void cartData(String product, String ResultsDisplayd, String Ipod,
			String ShoppingSummary, String CartEmpty) {
		verifyProduct(product, ResultsDisplayd, Ipod);

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		summaryPage(ShoppingSummary);

	}

	public void deleteFromCart(String product, String ResultsDisplayd,
			String Ipod, String ShoppingSummary, String CartEmpty) {
		verifyProduct(product, ResultsDisplayd, Ipod);

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		summaryPage(ShoppingSummary);

		DeleteBtn.click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		commons.fluentWait(By.xpath(Elements.CartDetails));
		Assert.assertEquals(CartDetails.getText(), CartEmpty);
		home.navigate_to_Home();
		Assert.assertTrue(Logout.isDisplayed());

	}

	public void cartAddress(String product, String ResultsDisplayd,
			String Ipod, String ShoppingSummary) {
		verifyProduct(product, ResultsDisplayd, Ipod);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		summaryPage(ShoppingSummary);

		NextBtn.click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertTrue(AddressCheckBox.isSelected());
		AddressCheckBox.click();

		Assert.assertTrue(AddressNext.isDisplayed());

	}

	public void cartShipping(String product, String ResultsDisplayd,
			String Ipod, String ShoppingSummary) {
		verifyProduct(product, ResultsDisplayd, Ipod);

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		summaryPage(ShoppingSummary);

		NextBtn.click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertTrue(AddressCheckBox.isSelected());
		Assert.assertTrue(NewAddress.isDisplayed());
		Assert.assertTrue(NewAddress.isEnabled());
		Assert.assertTrue(AddressNext.isDisplayed());
		AddressNext.click();

		Assert.assertFalse(TermsAndConditionsChkBox.isSelected());

	}

	public void cartPayMent(String product, String ResultsDisplayd,
			String Ipod, String ShoppingSummary, String SummaryProduct,
			String ProductAmt) {
		verifyProduct(product, ResultsDisplayd, Ipod);

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		summaryPage(ShoppingSummary);

		NextBtn.click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertTrue(AddressCheckBox.isSelected());
		Assert.assertTrue(NewAddress.isDisplayed());
		Assert.assertTrue(NewAddress.isEnabled());
		Assert.assertTrue(AddressNext.isDisplayed());
		AddressNext.click();

		Assert.assertFalse(TermsAndConditionsChkBox.isSelected());
		TermsAndConditionsChkBox.click();

		Assert.assertTrue(ShippingNext.isDisplayed());
		ShippingNext.click();
		commons.fluentWait(By.xpath(Elements.ProductDescription));
		Assert.assertTrue(ProductDescription.isDisplayed());
		Assert.assertEquals(ProductDescription.getText(), SummaryProduct);
		Assert.assertTrue(ProductAmount.isDisplayed());
		Assert.assertEquals(ProductAmount.getText(), ProductAmt);
		Assert.assertTrue(payByCheque.isDisplayed());
		Assert.assertTrue(payByCash.isDisplayed());
		Assert.assertTrue(SubmitPayMentBtn.isDisplayed());
		Assert.assertTrue(SubmitPayMentBtn.isEnabled());

	}

	public void cartSumbission(String product, String ResultsDisplayd,
			String Ipod, String ShoppingSummary, String SummaryProduct,
			String ProductAmt) {
		verifyProduct(product, ResultsDisplayd, Ipod);

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		summaryPage(ShoppingSummary);
		NextBtn.click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertTrue(AddressCheckBox.isSelected());
		Assert.assertTrue(NewAddress.isDisplayed());
		Assert.assertTrue(NewAddress.isEnabled());
		Assert.assertTrue(AddressNext.isDisplayed());
		AddressNext.click();
		Assert.assertFalse(TermsAndConditionsChkBox.isSelected());
		TermsAndConditionsChkBox.click();
		Assert.assertTrue(ShippingNext.isDisplayed());
		ShippingNext.click();
		commons.fluentWait(By.xpath(Elements.ProductDescription));
		Assert.assertTrue(ProductDescription.isDisplayed());
		Assert.assertEquals(ProductDescription.getText(), SummaryProduct);
		Assert.assertTrue(ProductAmount.isDisplayed());
		Assert.assertEquals(ProductAmount.getText(), ProductAmt);
		Assert.assertTrue(payByCheque.isDisplayed());
		Assert.assertTrue(payByCash.isDisplayed());
		Assert.assertTrue(SubmitPayMentBtn.isDisplayed());
		Assert.assertTrue(SubmitPayMentBtn.isEnabled());
		payByCheque.click();
		commons.fluentWait(By.xpath(Elements.OrderSummary));
		Assert.assertTrue(OrderSummary.isDisplayed());
		Assert.assertTrue(ChequePayment.isDisplayed());
		Assert.assertTrue(OtherPayMentModes.isDisplayed());
		Assert.assertTrue(ConfirmOrder.isDisplayed());
		ConfirmOrder.click();
		commons.fluentWait(By.xpath(Elements.ErrorMessage));
		if (SuccessMessage.getText().contains("Sorry, unfortunately")) {
			Assert.assertTrue(true, "Failed to submit" + ErrorMessage.getText());
		} else {
			Assert.assertTrue(false,
					"Failed to submit" + ErrorMessage.getText());
		}

	}

}
