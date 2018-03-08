package com.ActiveRisk.pages;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.ActiveRisk.constants.Constants;
import com.ActiveRisk.utils.FileUtil;

public class RiskManagement_SearchResult {
	
	WebDriver driver;
	
	@FindBy(id="sub-header")
	private WebElement headerText;
	@FindBy(xpath="//h3/a")
	private List<WebElement> searchResultList;
	
	public RiskManagement_SearchResult(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getTitle()
	{
		return driver.getTitle();
	}
	
	public String getHeaderText()
	{
		return headerText.getText();
	}
	
	public void getSearchResultList()
	{	
		SoftAssert as = new SoftAssert();
		ArrayList<String> listName = new ArrayList();
		listName = FileUtil.readToArrayList("riskResultList.txt");		
		int i=0;
		for(WebElement eachResult : searchResultList)
		{
			as.assertEquals(eachResult.getText(), listName.get(i));
			i++;
		}
		as.assertAll();
	}
	
	public void getResultingSearchLinks()
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
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader("src/test/txtFiles/RiskResultLinkTitle.txt");
			br = new BufferedReader(fr);
			String data;
			while ((data = br.readLine()) != null) 
			{
				listName.add(data);
			}
			System.out.println(listName);
		} catch (IOException e)		{
			e.printStackTrace();
		}
		int i=0;
		for(String eachTitle : allTitles)		{
			as.assertEquals(eachTitle, listName.get(i));
			i++;
		}		
		as.assertAll();		
	}
	
	public void checkSearchResultContent()
	{
		List<WebElement> allHighlightedResult = driver.findElements(By.xpath("//div[@id='content']//span[@class='search-everything-highlight-color']"));
		for(WebElement result : allHighlightedResult)
		{
			if((result.getText()).equalsIgnoreCase("risk") || (result.getText()).equalsIgnoreCase("management") )
			{
				continue;
			}
			else
			{
				System.out.println("checkSearchResult - FAIL");
			}
		}
	}
}
