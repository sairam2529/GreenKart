package com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.utility.CommonMethods;

public class OrderPage extends CommonMethods{
	
	
	@FindBy(xpath="//select")
	private WebElement country;
	
	
	@FindBy(xpath="//input[@type='checkbox']")
	private WebElement checkBox;
	
	@FindBy(xpath="//button")
	private WebElement finalSubmitButton;
	
	
	@FindBy(xpath="//div[@class='wrapperTwo']/span")
	private WebElement thankYou;
	
	public WebElement countryDropdown() {
		return country;
	}
	
	public WebElement thankYou() {
		return thankYou;
	}
	
	public WebElement finalSubmitButton() {
		return finalSubmitButton;
	}
	
	public WebElement checkBox() {
		return checkBox;
	}
}
