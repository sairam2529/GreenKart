package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GreenKartHomePage {
	

	WebDriver driver;
	
	public GreenKartHomePage(WebDriver driver) {
		this.driver=driver;
	}
	
	@FindBy(xpath="//img[@alt='Cart']")
	private WebElement cartButton;
	
	@FindBy(xpath="//button[text()='PROCEED TO CHECKOUT']")
	private WebElement checkOutButton;
	
	@FindBy(xpath="//div/button[text()='✔ ADDED']")
	private WebElement addedToCartButton;
	
	
	public WebElement cartButton() {
		return cartButton;
	}
	
	public WebElement addedToCartButton() {
		return addedToCartButton;
	}
	
	
	public WebElement checkOutButton() {
		return checkOutButton;
	}
	
	
	public WebElement itemQuantity(String itemName) {
		return driver.findElement(By.xpath("//h4[contains(text(),'"+itemName+"')]/parent::div/div[@class='stepper-input']/input"));
	}
	
	public void addToCart(String itemName) {
		driver.findElement(By.xpath("//h4[contains(text(),'"+itemName+"')]/parent::div/div/button")).click();
	}
	
	
	public int itemPrice(String itemName) {
		String itemPrice=driver.findElement(By.xpath("//h4[contains(text(),'"+itemName+"')]/parent::div/p")).getText();
		return Integer.parseInt(itemPrice);
	}
	
	
	
	
	

	
}
