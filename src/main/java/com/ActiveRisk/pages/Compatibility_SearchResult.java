package com.ActiveRisk.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import com.ActiveRisk.utils.FileUtil;

public class Compatibility_SearchResult {
	
WebDriver driver;
	
	@FindBy(id="sub-header")
	private WebElement headerText;
	@FindBy(xpath="//div[@id='content']")
	private WebElement checkAvailability;	
	@FindBy(xpath="//h3/a")
	private List<WebElement> searchResultList;
	
	public Compatibility_SearchResult(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getTitle()	{
		return driver.getTitle();
	}
	
	public String getHeaderText()	{
		return headerText.getText();
	}
	
	public void getSearchResultList()
	{	
		if(checkAvailability.getText().contains("Sorry, no results found! Please try again."))
		{
			System.out.println("-----------NO RESULT TO DISPLAY----------");
		}
		else
		{
			SoftAssert as = new SoftAssert();
			ArrayList<String> listName = new ArrayList();
			listName = FileUtil.readToArrayList("LeverageResultList.txt");		
			int i=0;
			for(WebElement eachResult : searchResultList)
			{
				as.assertEquals(eachResult.getText(), listName.get(i));
				i++;
			}
			as.assertAll();
		}
	}
	
	public void getLeverageResultingSearchLinks()
	{	
		List<String> allTitles = new ArrayList<String>();
		List<String> allLinkText = new ArrayList<String>();		
		for(WebElement eachResultLink : searchResultList)
		{
			allLinkText.add(eachResultLink.getText());
		}
		for(String eachLinkText : allLinkText)
		{
			driver.findElement(By.linkText(eachLinkText)).click();
			driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
			allTitles.add(driver.getTitle());
			driver.navigate().back();			
		}		
		
		//Checking the correct page loaded.
		SoftAssert as = new SoftAssert();
		ArrayList<String> listName = new ArrayList();
		listName = FileUtil.readToArrayList("LeverageResultLinkTitle.txt");		
		int i=0;
		for(String eachTitle : allTitles)		{
			as.assertEquals(eachTitle, listName.get(i));
			i++;
		}		
		as.assertAll();	
	}	
}
