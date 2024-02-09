package com.pages;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Demo {
	
	
	@Test(dataProvider = "masterDataProvider")
	public void orderVegetables(HashMap<String, String> hashmap) {
		System.out.println(hashmap.get("Vegetables"));
		System.out.println(hashmap.get("VegQTY"));
		String[] veg=hashmap.get("Vegetables").split(",");
		System.out.println(veg[0]);
	}
	
	

	@DataProvider(name="masterDataProvider")
	public Object[][] excelHandling(Method method) throws Exception {
		File file=new File(System.getProperty("user.dir")+"/GreenKartAutomation.xlsx");
			FileInputStream fis=new FileInputStream(file);
			XSSFWorkbook workbook=new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheetAt(0);
			int rowCount = sheet.getPhysicalNumberOfRows();
			int colCount = sheet.getRow(0).getLastCellNum();
			HashMap<String,String> hashmap=new HashMap<String,String>();
			String[] headers= new String[colCount];
			Object[][] obj=new Object[colCount][1];
			
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
					obj[j][0]=hashmap;
				}
				
			}
			
			return obj;
		}
}
