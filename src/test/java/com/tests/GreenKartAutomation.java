package com.tests;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
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
	
	
	
	@Test(dataProvider = "masterDataProvider")
	public void orderFruits(HashMap<String, String> hashmap) throws Exception {
		utils.navigatingToBaseURL(System.getenv("GreenkartURL"), driver);
		int expPrice=0;
		String name = new Object() {}.getClass().getEnclosingMethod().getName();
		String[] itemsList=hashmap.get("Fruits").split(",");
		String[] itemQty=hashmap.get("FruitsQTY").split(",");
		for(int i=0;i<itemsList.length;i++) {
				String itemName = itemsList[i];
				int quantity=Integer.parseInt(itemQty[i]);
				greenKartHomePage.itemQuantity(itemName).clear();
				greenKartHomePage.itemQuantity(itemName).sendKeys(itemQty[i]);
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
	
	@Test(dataProvider = "masterDataProvider")
	public void orderVegetables(HashMap<String, String> hashmap) throws Exception {
		utils.navigatingToBaseURL(System.getenv("GreenkartURL"), driver);
		int expPrice=0;
		String name = new Object() {}.getClass().getEnclosingMethod().getName();
		String[] itemsList=hashmap.get("Vegetables").split(",");
		String[] itemQty=hashmap.get("VegQTY").split(",");
		for(int i=0;i<itemsList.length;i++) {
				String itemName = itemsList[i];
				int quantity=Integer.parseInt(itemQty[i]);
				greenKartHomePage.itemQuantity(itemName).clear();
				greenKartHomePage.itemQuantity(itemName).sendKeys(itemQty[i]);
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

	@DataProvider(name="masterDataProvider")
	public Object[][] excelHandling(Method method) throws Exception {
		File file=new File(System.getProperty("user.dir")+"/"+this.getClass().getSimpleName()+".xlsx");
		FileInputStream fis=new FileInputStream(file);
		XSSFWorkbook workbook=new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);
		int rowCount = sheet.getPhysicalNumberOfRows();
		int colCount = sheet.getRow(0).getLastCellNum();
		HashMap<String,String> hashmap=new HashMap<String,String>();
		String[] headers= new String[colCount];
		Object[][] obj=new Object[1][1];
		
		System.out.println(method.getName());
		DataFormatter df=new DataFormatter();
		for(int i=0;i<colCount;i++) {
			headers[i]=sheet.getRow(0).getCell(i).getStringCellValue();
		}
		for(int j=1;j<rowCount;j++) {
			String testCaseName = sheet.getRow(j).getCell(0).getStringCellValue();
			if(testCaseName.equals(method.getName())) {
				for(int i=1;i<colCount;i++) {
				hashmap.put(headers[i], sheet.getRow(j).getCell(i).getStringCellValue());
				}
				obj[0][0]=hashmap;
			}
			
		}
		
		return obj;
	}
}
