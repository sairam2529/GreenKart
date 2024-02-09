package com.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BeCognizant {
	
	WebDriver driver;
	
	
	public BeCognizant(WebDriver driver) {
		this.driver=driver;
	}
	
	
	@FindBy(xpath="//button[contains(@class,'commandBar')]")
	private WebElement companyElement;
	
	
	@FindBy(xpath="//span[text()='Bluebolt']")
	private WebElement blueBolt;
	
	
	@FindBy(xpath="//div[@data-automation-id='CanvasControl' and @id='078d482c-a3ef-47ca-9425-d13374dc14d1']")
	private WebElement faqSection;
	
	@FindBy(id="cswpAccessibleLabel_cbe7b0a9-3504-44dd-a3a3-0e5cacd07788")
	private WebElement blueBoltSection;
	
	@FindBy(xpath="//div[@id='078d482c-a3ef-47ca-9425-d13374dc14d1'] //div[@id='QuicklinksItemTitle']")
	private List<WebElement> blueBoltElements;
	
	@FindBy(id="id__12")
	private WebElement elementInPage;
	
	@FindBy(xpath = "//input[@role='combobox']")
	private WebElement searchButton;
	
	@FindBy(xpath = "//span[@class='template--listItem--title example-themePrimary']")
	private WebElement firstLink;
	
	public WebElement firstLink() {
		return firstLink;
	}
	
	public WebElement elementInPage() {
		return elementInPage;
	}
	
	public WebElement searchInSite() {
		return searchButton;
	}
	
	public List<WebElement> blueBoltFAQLinks(){
		return blueBoltElements;
	}
	
	
	public void navigatingToBlueBolt() {
		companyElement.click();
		blueBolt.click();
	}
	
	
	public WebElement faqSection() {
		return faqSection;
	}
	
	public WebElement blueBoltSection() {
		return blueBoltSection;
	}

}
