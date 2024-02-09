package com.utility;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonMethods{
	
	private Actions a;
	private WebDriverWait wait;
	
	public void navigatingToBaseURL(String URL,WebDriver driver) {
		driver.get(URL);
	}
	
	
	public String getTitle(WebDriver driver) {
		return driver.getTitle();
	}
	
	
	public void movingToElement(WebElement target,WebDriver driver) {
		a=new Actions(driver);
		a.moveToElement(target).perform();
	}
	
	public void scrollingToElement(WebElement target,WebDriver driver) {
		a.scrollToElement(target).perform();
	}
	
	public void dropdownValue(WebElement element,String value) {
		Select select=new Select(element);
		select.selectByValue(value);
	}
	
	
	public void waitForElementToAppear(WebDriver driver,WebElement element) {
		wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForElementToDisappear(WebDriver driver,WebElement element) {
		wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	
	
	
	
	

}
