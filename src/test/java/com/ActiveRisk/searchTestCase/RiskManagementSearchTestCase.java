package com.ActiveRisk.searchTestCase;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.ActiveRisk.pages.HomePage;
import com.ActiveRisk.pages.RiskManagement_SearchResult;
import com.ActiveRisk.utils.BrowserUtil;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

public class RiskManagementSearchTestCase{
	public static WebDriver driver;
	
	@Given("^Go to website\"([^\"]*)\"$")
	public void go_to_website(String url) {
		BrowserUtil.openBrowser("chrome");
		BrowserUtil.driver.get(url);
		BrowserUtil.driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	}

	@When("^Enter search text \"([^\"]*)\"$")
	public void enter_search_text(String searchText)  throws Throwable
	{
		HomePage hp = new HomePage(BrowserUtil.driver);
		hp.searchText=searchText;
		hp.typeInSearchFiled();
		Thread.sleep(2000);
	}

	@When("^Click on search button$")
	public void click_on_search_button()  {
		HomePage hp = new HomePage(BrowserUtil.driver);
		hp.clickOnsearchBtn();
	}

	@Then("^Get result page title$")
	public void get_result_page_title() 
	{
		RiskManagement_SearchResult rms = new RiskManagement_SearchResult(BrowserUtil.driver);
		BrowserUtil.driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);		
		System.out.println("Title is = "+rms.getTitle());
		String title = rms.getTitle();
		Assert.assertEquals(title, "Risk management Search Results");
	}

	@Then("^Get header text$")
	public void get_header_text() 
	{
		RiskManagement_SearchResult rms = new RiskManagement_SearchResult(BrowserUtil.driver);
		System.out.println("Header Text is = "+rms.getHeaderText());
		String headerText = rms.getHeaderText();
		Assert.assertEquals(headerText, "SEARCH RESULTS");
	}
	
	@Then("^Validate the search content$")
	public void validate_the_search_content()  {
		RiskManagement_SearchResult rms = new RiskManagement_SearchResult(BrowserUtil.driver);
		rms.checkSearchResultContent();
	}

	@Then("^Validate search result$")
	public void validate_search_result()  
	{
		RiskManagement_SearchResult rms = new RiskManagement_SearchResult(BrowserUtil.driver);
		rms.getSearchResultList();
	}

	@Then("^Validate search links$")
	public void validate_search_links()  
	{
		RiskManagement_SearchResult rms = new RiskManagement_SearchResult(BrowserUtil.driver);
		rms.getResultingSearchLinks();
	}
}
