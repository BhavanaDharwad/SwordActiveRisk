package com.ActiveRisk.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;
	public static String searchText;
	
	@FindBy(id=("s"))
	private WebElement searchField;
	@FindBy(id=("searchsubmit"))
	private WebElement searchBtn;
	
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void typeInSearchFiled()
	{		
		searchField.clear();
		searchField.sendKeys(searchText);
	}
	
	public void clickOnsearchBtn()
	{		
		searchBtn.click();
	}
}
