package org.ecommerce;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import junit.framework.Assert;


public class MobilePurchase {

static WebDriver driver;
	
	
	@BeforeClass
	public static void LaunchBrowser() {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\nandhu\\eclipse-workspace\\Flipkart-Junit\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.flipkart.com/");
		driver.manage().window().maximize();
	}
	
	 static long starttime;
	 @Before
	 public void starttime() {
		 System.out.println("Start time");
		 long starttime = System.currentTimeMillis();
	 }
	 @After
	 public void endtime() {
		 System.out.println("End Time");
		 long endtime = System.currentTimeMillis();
		System.out.println(endtime-starttime);
	 }
	 
	@Test
	public void closeBtn() {
		
		driver.findElement(By.xpath("//button[text()='✕']")).click();
	}
	
	@Test
	public void searchBtn() {
		driver.findElement(By.name("q")).sendKeys("Realme mobile");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	}
	
	static String name;
	@Test
	public void selectProduct() throws IOException {

		WebElement excelname = driver.findElement(By.xpath("(//div[contains(text(),'realme')])[1]"));
		String name = excelname.getText(); 
		
		File f = new File("C:\\Users\\nandhu\\eclipse-workspace\\Flipkart-Junit\\Task.xlsx");
		FileInputStream read =new FileInputStream(f);
		
		XSSFWorkbook XLbook =new XSSFWorkbook(read);
		XSSFSheet s = XLbook.getSheet("mobile");
		
		s.getRow(0).createCell(1).setCellValue(name);
		FileOutputStream write =new FileOutputStream(f);
		XLbook.write(write);
		write.close();	
	}
	@Test
	public void Wndwhandling() throws IOException {
		
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		WebElement realmeclick = driver.findElement(By.xpath("(//div[contains(text(),'realme')])[1]"));
		realmeclick.click();
		
		String name1 = realmeclick.getText();
		
		Set<String> allwin =((WebDriver) driver).getWindowHandles();
		
		List<String> lst = new ArrayList<String>();
		
		lst.addAll(allwin);
			driver.switchTo().window(lst.get(1));
		
		File f = new File("C:\\\\Users\\\\nandhu\\\\eclipse-workspace\\\\Flipkart-Junit\\\\Task.xlsx");
		FileInputStream f1 = new FileInputStream(f);
	
		XSSFWorkbook XLbook =new XSSFWorkbook(f1);
		XSSFSheet s = XLbook.getSheet("mobile");
		
			String data=s.getRow(0).getCell(1).getStringCellValue();
			f1.close();
			
			Assert.assertEquals(data,name1);
	}
	
	@Test
	public void method5() throws IOException {
		
		TakesScreenshot t=(TakesScreenshot)driver;
		
		File source = t.getScreenshotAs(OutputType.FILE);
		File target = new File("C:\\Users\\nandhu\\eclipse-workspace\\Flipkart-Junit\\Screenshot\\MobileImage.png");
		FileUtils.copyFile(source, target);
		
		WebElement down=driver.findElement(By.xpath("//div[text()='Highlights']"));
		JavascriptExecutor j=(JavascriptExecutor)driver;
		
		j.executeScript("arguments[0].scrollIntoView(true)",down);
		
		File source1=t.getScreenshotAs(OutputType.FILE);
		File target1=new File("C:\\Users\\nandhu\\eclipse-workspace\\Flipkart-Junit\\Screenshot\\Image2.png");
		FileUtils.copyFile(source1, target1);
	}
			
	
	
}
