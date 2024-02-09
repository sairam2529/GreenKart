package com.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckOutPage {
	
	
	WebDriver driver;
	
	public CheckOutPage(WebDriver driver) {
		this.driver=driver;
	}
	
	
	@FindBy(xpath="//td/p[@class='product-name']")
	private List<WebElement> itemsList;
	
	@FindBy(xpath="//span[@class='totAmt']")
	private WebElement totalAmt;
	
	@FindBy(xpath="//td/p[@class='quantity']")
	private List<WebElement> quantityList;
	
	
	@FindBy(xpath="//button[text()='Place Order']")
	private WebElement placeOrder;
	
	public WebElement totalAmt(){
		return totalAmt;
	}
	
	public WebElement placeOrder(){
		return placeOrder;
	}
	
	public List<WebElement> itemsList(){
		return itemsList;
	}
	
	public List<WebElement> quantityList(){
		return quantityList;
	}

}
