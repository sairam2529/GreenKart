package com.tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.pages.BeCognizant;
import com.utility.BaseTest;
import com.utility.CommonMethods;

public class BlueBoltAutomation extends BaseTest{
	
	private BeCognizant beCognizant;
	CommonMethods utils=new CommonMethods();
	List<String> li=Arrays.asList(
			"Bluebolt FAQs",
		"Bluebolt Central Governance",
		"Bluebolt Central Workflow - CSI - Adding opportunities",
		"Bluebolt Central Workflow - Automation - Adding opportunities",
		"Bluebolt Central Workflow - Managed Innovation - Adding opportunities");
	
	@BeforeClass
	public void initElements() {
		beCognizant = PageFactory.initElements(driver, BeCognizant.class);
	}
	
	@Test
	public void automatingWebSite() throws Exception {
		
		utils.navigatingToBaseURL(System.getenv("URL"),driver);
		beCognizant.navigatingToBlueBolt();
		utils.movingToElement(beCognizant.blueBoltSection(),driver);
		utils.scrollingToElement(beCognizant.faqSection(),driver);
		int count=beCognizant.blueBoltFAQLinks().size();
		
		for(int i=0;i<count;i++) {
			String links = beCognizant.blueBoltFAQLinks().get(i).getText();
			Assert.assertEquals(links, li.get(i));
			System.out.println(links);
		}
		
		
	}
	
	

}
