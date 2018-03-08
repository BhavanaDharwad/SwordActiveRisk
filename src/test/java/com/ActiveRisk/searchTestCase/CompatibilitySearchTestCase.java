package com.ActiveRisk.searchTestCase;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import com.ActiveRisk.pages.Compatibility_SearchResult;
import com.ActiveRisk.pages.HomePage;
import com.ActiveRisk.utils.BrowserUtil;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

public class CompatibilitySearchTestCase {
	public static WebDriver driver; 
	
	@When("^Search for text \"([^\"]*)\"$")
	public void search_for_text(String searchText) throws Throwable {
		HomePage hp = new HomePage(BrowserUtil.driver);
		hp.searchText=searchText;
		hp.typeInSearchFiled();
		Thread.sleep(2000);
	}

	@When("^Click on Search button$")
	public void click_on_Search_button(){
		HomePage hp = new HomePage(BrowserUtil.driver);
		hp.clickOnsearchBtn();
	}

	@Then("^Collect result page title$")
	public void collect_result_page_title() {
		Compatibility_SearchResult cs = new Compatibility_SearchResult(BrowserUtil.driver);
		BrowserUtil.driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);		
		System.out.println("Title is = "+cs.getTitle());
		String title = cs.getTitle();
		Assert.assertEquals(title, "Compatibility Search Results");
	}

	@Then("^Validate search content$")
	public void validate_search_content() {
		Compatibility_SearchResult cs = new Compatibility_SearchResult(BrowserUtil.driver);
		cs.getSearchResultList();
		BrowserUtil.closeBrowser();
	}
	
}
