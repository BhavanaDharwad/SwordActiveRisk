package com.ActiveRisk.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BrowserUtil
{
	public static WebDriver driver=null;
	
	public static void openBrowser(String browsername)
	{
		switch(browsername)
		{
		case "chrome":
		{
			System.setProperty("webdriver.chrome.driver","src/main/resources/exe files/chromedriver.exe");
		    driver=new ChromeDriver();
		    driver.manage().window().maximize();
		    break;
		}
		
		case "firefox":
		{
			System.setProperty("webdriver.gecko.driver","src/main/resources/exe files/geckodriver.exe");
			driver=new FirefoxDriver();
			driver.manage().window().maximize();
			break;
		}
		
		case "IE":
		{
			System.setProperty("webdriver.ie.driver","src/main/resources/exe files/IEDriverServer.exe");
		    driver=new InternetExplorerDriver();
			driver.manage().window().maximize();
			break;
			
		}
		}
      }
	
	public static void closeBrowser()
	{
		if(driver!=null) {
			driver.close();
			driver.quit();
			driver = null;
		}
	}
	
}