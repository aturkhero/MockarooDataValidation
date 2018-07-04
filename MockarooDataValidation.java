package Projects;

import static org.testng.Assert.*;

import java.io.*;

import java.nio.charset.StandardCharsets;
import java.util.*;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;


import io.github.bonigarcia.wdm.WebDriverManager;
import net.bytebuddy.matcher.BooleanMatcher;

public class MockarooDataValidation {
	
	WebDriver driver;
	
	 List<String> list=new ArrayList<>();
	 List<String> cities=new ArrayList<>();
	 List<String> countries=new ArrayList<>();
	 
 	
	
	@BeforeClass
	public void setup() {
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
		driver.get("https://mockaroo.com/");
		
	}
	
	@Test
	public void step3() {
		
		String title="Mockaroo - Random Data Generator and API Mocking Tool | JSON / CSV / SQL / Excel";
		assertEquals(driver.getTitle(), title);
		
	}
	
	@Test
	public void step4() {
		
		String brand="mockaroo";
		String tagline="realistic data generator";
		
		assertEquals(brand, driver.findElement(By.xpath("//div[@class='brand']")).getText());
		assertEquals(tagline, driver.findElement(By.xpath("//div[@class='tagline']")).getText());
	}
	
	@Test(priority=1)
	public void step5() {
		
		List<WebElement> xB=new ArrayList<WebElement>(); 
		
		xB=driver.findElements(By.xpath("//a[@class='close remove-field remove_nested_fields']"));
		
		for (WebElement el:xB) {
			
		 el.click();
		 }
		
	}
	
	@Test
	public void step6() {
		
		
		WebElement fieldName = driver.findElement(By.xpath("//div[@class='column-fields']//div[.='Field Name']"));
		WebElement fieldType = driver.findElement(By.xpath("//div[@class='column-fields']//div[.='Type']"));
		WebElement fieldOptions = driver.findElement(By.xpath("//div[@class='column-fields']//div[.='Options']"));

		System.out.println(fieldName);
		
		assertTrue(fieldName.isDisplayed());
		assertTrue(fieldType.isDisplayed());
		assertTrue(fieldOptions.isDisplayed());
		
		
	}
	
	@Test 
	public void step7() {
		
		
		WebElement button=driver.findElement(By.xpath("//a[contains(@class,'add-column-btn')]"));
		
		assertTrue(button.isEnabled());
		
	}
	
	@Test
	public void step8() {
		
		String defaultnum="1000";
		
		assertEquals(defaultnum, driver.findElement(By.id("num_rows")).getAttribute("value"));
		
	}
	
	@Test
	public void step9() {
		
		Select select = new Select(driver.findElement(By.id("schema_file_format")));
		
		String defaultselection="csv";
		
		assertEquals(defaultselection, select.getFirstSelectedOption().getAttribute("value"));
		
	}
	
	@Test(priority=2)
	public void step10() {
		
		Select select = new Select(driver.findElement(By.id("schema_line_ending")));
		
		String defaultselection="unix";
		
		assertEquals(defaultselection,select.getFirstSelectedOption().getAttribute("value"));

		
	}
	
	@Test(priority=3)
	public void step11() throws InterruptedException {
		WebElement headerchk=driver.findElement(By.id("schema_include_header"));
		WebElement BOMchk= driver.findElement(By.id("schema_bom"));
	
		
		assertTrue(headerchk.isSelected());
		
		assertFalse(BOMchk.isSelected());
		
		//Thread.sleep(2000);
		
	}
	
	@Test(priority=4)
	public void step12() throws InterruptedException {
		
		driver.findElement(By.xpath("//a[@class='btn btn-default add-column-btn add_nested_fields']")).click();
		
		driver.findElement(By.xpath("(//div[@id='fields']//input[starts-with(@id, 'schema_columns_attributes_')][contains(@id,'name')])[last()]")).sendKeys("City");
	
		
	}
	//*[@id="schema_columns_attributes_1530566176965_name"]
	@Test(priority=5)
	public void step13() {
		
		
		
		driver.findElement(By.xpath("(//div[@class='fields']//input[@class='btn btn-default'])[last()]")).click();
		
		assertTrue(driver.findElement(By.xpath("//div[@id='type_dialog']//h3[@class='modal-title']")).isEnabled());
		
	}
	
	@Test(priority=6)
	public void step14() throws InterruptedException {
		
		Thread.sleep(2000);
		
		driver.findElement(By.id("type_search_field")).sendKeys("City");
		
		driver.findElement(By.xpath("//*[@id=\"type_list\"]/div[1]")).click();;
		
		
	}
	
	@Test(priority=7)
	public void step15() throws InterruptedException {
		
		Thread.sleep(2000);

		
		driver.findElement(By.xpath("//a[@class='btn btn-default add-column-btn add_nested_fields']")).click();
		
		driver.findElement(By.xpath("(//div[@id='fields']//input[starts-with(@id, 'schema_columns_attributes_')][contains(@id,'name')])[last()]")).sendKeys("Country");
	
		driver.findElement(By.xpath("(//div[@class='fields']//input[@class='btn btn-default'])[last()]")).click();
		
		assertTrue(driver.findElement(By.xpath("//div[@id='type_dialog']//h3[@class='modal-title']")).isEnabled());
		
		Thread.sleep(1000);

		driver.findElement(By.id("type_search_field")).sendKeys("Country");
		
		driver.findElement(By.xpath("//*[@id=\"type_list\"]/div[1]")).click();;
		
//		driver.findElement(By.id("download")).click();
		
		

		
	}
	
	@Test(priority=8)
	public void step16() throws InterruptedException {
		
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//button[.='Download Data']")).click();
		
	}
	
	//@Ignore
	@Test(priority=9)
	public void step17_20_21() throws IOException { 
		

		BufferedReader br = new BufferedReader(new FileReader("/Users/jackturkhero/Downloads/MOCK_DATA.csv"));
		
		String line;
		
       
        
        while((line = br.readLine()) != null) {
        	
        //	System.out.println(line);
        	list.add(line);
        	
        	String words[]=line.split(",");
        	
        	cities.add(words[0]);
        	countries.add(words[1]);
        }
		
		
	}
	@Test(priority=10)
	public void step18() {
		
        assertEquals(list.get(0), "City,Country");

		
	}
	
	@Test(priority=11)
	public void step19() {
		assertEquals(list.size()-1, 1000);
	}
	
	@Test(priority=12)
	public void step22() {
		
		Collections.sort(cities);
		
		String shortest=cities.get(0);
		String longest=cities.get(0);
		
		for (String string : cities) {
			
			if(string.length()>longest.length()) {
				longest=string;
			}
			
			if(string.length()<shortest.length()) {
				shortest=string;
			}
		}
		
		System.out.println("Longest name of the city: "+longest);
		System.out.println("Shortest name of the city: "+ shortest);
		
	}
	
	@Test(priority=13)
	public void step23(){
		
		SortedSet<String> sortedCountry = new TreeSet<>(countries);
		
		System.out.println("\n///////////////////////////////\n");
		
		for (String each : sortedCountry) {
			System.out.println(each + "-" + Collections.frequency(countries, each));
		}
		
	}
	
	@Test(priority=14)
	public void step24_25() {
		
		Set<String> citiesSet = new HashSet<>(cities);
				
		assertEquals(citiesSet.size(), 970);
		
		

	}
	
	@Test(priority=15)
	public void step26_27() { 
		
		Set<String> countriesSet = new HashSet<>(countries);
		
		assertEquals(countriesSet.size(), 134);
		
	}
	
	
	
	
	
	

}
