package support;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

public class SeleniumAppDriver {

	private final static String BASE_URL = "http://localhost:9090";

	@Autowired
	private WebDriver webDriver;

	public void showHomePage() {
		goToHomePage();
	}
	
	public void showRegistrationPage() {
		goToHomePage();
		WebElement element = elementById("lnk_reg");
		element.click();
	}
	
	public void register(String firstName,String lastName){
		enterTextInField("firstName", firstName);
		enterTextInField("lastName", lastName);
		submit(elementById("btn_register"));
	}
	
	public String findElementOnPage(String id) {
		WebElement element = elementById(id);
		return element.getAttribute("name");
	}

	public void findEmployee(String id) {
		goToHomePage();
		enterTextInField("txt_find", id);
		submit(elementById("btn_find"));
	}

	public boolean resultPage() {
		try {
			elementById("result");
		} catch (NoSuchElementException ex) {
			return false;
		}
		return true;
	}

	WebElement elementById(String id) {
		return webDriver.findElement(By.id(id));
	}
	
	boolean hasElement(String id) {
		try{
			webDriver.findElement(By.id(id));
		}catch(NoSuchElementException e){
			return false;
		}
		return true;
	}
	
	void waitFor(long time){
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	void goToHomePage() {
		webDriver.get(BASE_URL + "/hrm-web-client/");
	}

	void enterTextInField(String fieldId, String text) {
		elementById(fieldId).clear();
		elementById(fieldId).sendKeys(text);
	}

	void submit(WebElement e) {
		e.submit();
	}

}
