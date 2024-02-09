package com.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.pages.BeCognizant;
import com.utility.BaseTest;
import com.utility.CommonMethods;

public class SearchAutomation extends BaseTest{
	
	CommonMethods utils=new CommonMethods();
	private BeCognizant beCognizant;
	
	
	@BeforeClass
	public void init() {
		beCognizant= PageFactory.initElements(driver, BeCognizant.class);
	}
	
	@Test(dataProvider = "excelHandling")
	public void DataDriven(String name) {
		utils.navigatingToBaseURL(System.getenv("URL"), driver);
		beCognizant.searchInSite().sendKeys(name);
		beCognizant.searchInSite().sendKeys(Keys.ENTER);
		utils.waitForElementToAppear(driver,beCognizant.firstLink());
		String link= beCognizant.firstLink().getText();
		System.out.println("LINK : "+link);
		
	}
	
	
	
	@DataProvider
	public String[] excelHandling() throws IOException {
		String className = this.getClass().getSimpleName();
		File file=new File(System.getProperty("user.dir")+"/"+className+".xlsx");
		FileInputStream fis=new FileInputStream(file);
		XSSFWorkbook workbook=new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);
		int rowCount = sheet.getPhysicalNumberOfRows();
		String[] data=new String[rowCount];
		for(int i=0;i<rowCount;i++) {
			String cell= sheet.getRow(i).getCell(0).getStringCellValue();
			data[i]=cell;
//			System.out.println(data[i]);
		}
		
		workbook.close();
		fis.close();
		return data;
	}
	
	
	
	
	
	

}
