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

	public String findSearchBox() {
		WebElement element = elementById("txt_find");
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
