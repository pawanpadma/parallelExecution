package com.selenium.seleniumgrid;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Driverclass {

	public static ThreadLocal<RemoteWebDriver> driver = null;

	public static WebDriver getDriver() {
		return driver.get();
	}

	public void initBrowser(String browser) {
		if (browser.equals("firefox")) {
			driver = new ThreadLocal<RemoteWebDriver>();
			DesiredCapabilities dc = new DesiredCapabilities();
			FirefoxProfile fp = new FirefoxProfile();
			dc.setCapability(FirefoxDriver.PROFILE, fp);
			dc.setBrowserName(DesiredCapabilities.firefox().getBrowserName());
			try {
				driver.set(new RemoteWebDriver(new URL("http://localhost:4444"), dc));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			getDriver().manage().window().maximize();
			getDriver().get("http://prestacoder.com/demo_module/");
			// driver.manage().window().maximize();
			getDriver().manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

		}

		if (browser.equals("chrome")) {
			driver = new ThreadLocal<RemoteWebDriver>();
			 ChromeOptions chromeOptions = new ChromeOptions();
			    DesiredCapabilities caps = new DesiredCapabilities();
			    caps.setCapability(CapabilityType.BROWSER_NAME,"chrome");
			 
			    caps.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
			try {
				driver.set(new RemoteWebDriver(new URL("http://localhost:4444"), caps));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			getDriver().get("http://prestacoder.com/demo_module/");
			getDriver().manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

		}

		if (browser.equals("internet explorer")) {
			driver = new ThreadLocal<RemoteWebDriver>();
			DesiredCapabilities dc = new DesiredCapabilities();
			dc.setBrowserName(DesiredCapabilities.internetExplorer().getBrowserName());
			// dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
			// true); //-> Not needed if the all the preconditions are done.
			// I’ve left this anyway
			// dc.setCapability(InternetExplorerDriver.BROWSER_ATTACH_TIMEOUT,
			// 30000);
			// dc.setCapability(InternetExplorerDriver.FORCE_CREATE_PROCESS,
			// true);
			// dc.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION,
			// true);
			// dc.setCapability(InternetExplorerDriver.IE_SWITCHES, "-private");
			// dc.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING,
			// true);

			try {
				driver.set(new RemoteWebDriver(new URL("http://localhost:4444"), dc));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			getDriver().get("http://prestacoder.com/demo_module/");
			// driver.manage().window().maximize();
			getDriver().manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
			getDriver().navigate().refresh();

		}

	}

}
