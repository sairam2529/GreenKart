package com.tests;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.pages.CheckOutPage;
import com.pages.GreenKartHomePage;
import com.pages.OrderPage;
import com.utility.BaseTest;
import com.utility.CommonMethods;

public class GreenKartAutomation extends BaseTest{
	
	CommonMethods utils=new CommonMethods();
	private GreenKartHomePage greenKartHomePage;
	private CheckOutPage checkoutPage;
	private OrderPage orderPage;
	
	@BeforeClass
	public void driverParsing() {
		greenKartHomePage = PageFactory.initElements(driver, GreenKartHomePage.class);
		checkoutPage= PageFactory.initElements(driver, CheckOutPage.class);
		orderPage= PageFactory.initElements(driver, OrderPage.class);
	}
	
	@Test
	public void GreenkartPage() throws Exception {
		utils.navigatingToBaseURL(System.getenv("GreenkartURL"), driver);
		int expPrice=0;
		String name = new Object() {}.getClass().getEnclosingMethod().getName();
		String itemsList[][] = excelHandling(name);
		for(int i=0;i<itemsList.length;i++) {
				String itemName = itemsList[i][0];
				int quantity=Integer.parseInt(itemsList[i][1]);
				greenKartHomePage.itemQuantity(itemName).clear();
				greenKartHomePage.itemQuantity(itemName).sendKeys(itemsList[i][1]);
				expPrice+=greenKartHomePage.itemPrice(itemName)*quantity;
				greenKartHomePage.addToCart(itemName);
				utils.waitForElementToDisappear(driver, greenKartHomePage.addedToCartButton());
		}
		greenKartHomePage.cartButton().click();
		greenKartHomePage.checkOutButton().click();
		List<WebElement> itemlist = checkoutPage.itemsList();
		List<WebElement> qtylist = checkoutPage.quantityList();
		for(int i=0;i<itemlist.size();i++) {
			System.out.println(itemlist.get(i).getText()+" *"+qtylist.get(i).getText());
		}
		int actualPrice = Integer.parseInt(checkoutPage.totalAmt().getText());
		System.out.println("Total Amount :"+actualPrice);
		Assert.assertEquals(actualPrice, expPrice);
		checkoutPage.placeOrder().click();
		utils.dropdownValue(orderPage.countryDropdown(), "India");
		orderPage.checkBox().click();
		orderPage.finalSubmitButton().click();
		System.out.println(orderPage.thankYou().getText());
	}

	@DataProvider
	public String[][] excelHandling(String methodName) throws Exception {
		File file=new File(System.getProperty("user.dir")+"/"+this.getClass().getSimpleName()+".xlsx");
		FileInputStream fis=new FileInputStream(file);
		XSSFWorkbook workbook=new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);
		int rowCount = sheet.getPhysicalNumberOfRows();
		int colCount = sheet.getRow(0).getLastCellNum();
		HashMap<String,String> hashmap=new HashMap<String,String>();
		
		System.out.println(methodName);
		String[][] data=new String[rowCount-1][colCount];
		DataFormatter df=new DataFormatter();
		for(int i=1;i<rowCount;i++) {
			for(int j=1;j<colCount;j++) {
				if(sheet.getRow(i).getCell(0)!=null) {
			if(sheet.getRow(i).getCell(0).getStringCellValue().equals(methodName)) {
				String key = sheet.getRow(i-1).getCell(j).getStringCellValue();
				String value = sheet.getRow(i).getCell(j).getStringCellValue();
				hashmap.put(key, value);
			}
			}
			}
		}
		return data;
	}
}
