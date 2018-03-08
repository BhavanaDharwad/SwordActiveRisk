package com.ActiveRisk.searchTestCase;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.ActiveRisk.pages.HomePage;
import com.ActiveRisk.pages.Leverage_SearchResult;
import com.ActiveRisk.utils.BrowserUtil;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

public class LeverageSearchTestCase 
{
	public static WebDriver driver;
	
	/*@Given("^Go to website \"([^\"]*)\"$")
	public void go_to_website(String url) throws Throwable {
		BrowserUtil.openBrowser("chrome");
		BrowserUtil.driver.get(url);
		Thread.sleep(2000);
	}*/

	@When("^Enter the search text as \"([^\"]*)\"$")
	public void enter_the_search_text_as(String searchText) throws Throwable {
		HomePage hp = new HomePage(BrowserUtil.driver);
		hp.searchText=searchText;
		hp.typeInSearchFiled();
		Thread.sleep(2000);
	}

	@When("^Click on searchbutton$")
	public void click_on_searchbutton() throws Throwable {
		HomePage hp = new HomePage(BrowserUtil.driver);
		hp.clickOnsearchBtn();
	}

	@Then("^Get page title$")
	public void get_page_title() throws Throwable {
		BrowserUtil.driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		Leverage_SearchResult ls = new Leverage_SearchResult(BrowserUtil.driver);
		String title = ls.getTitle();
		Assert.assertEquals(title, "Leverage Search Results");
	}

	@Then("^get Header text$")
	public void get_Header_text() throws Throwable {
		Leverage_SearchResult ls = new Leverage_SearchResult(BrowserUtil.driver);
		String headerText = ls.getHeaderText();
		Assert.assertEquals(headerText, "SEARCH RESULTS");
	}

	@Then("^Validate Leverage search result$")
	public void validate_Leverage_search_result() throws Throwable {
		Leverage_SearchResult ls = new Leverage_SearchResult(BrowserUtil.driver);
		ls.getSearchResultList();
	}

	@Then("^Validate Leverage search links$")
	public void validate_Leverage_search_links() throws Throwable {
		Leverage_SearchResult ls = new Leverage_SearchResult(BrowserUtil.driver);
		ls.getLeverageResultingSearchLinks();
	}
}
