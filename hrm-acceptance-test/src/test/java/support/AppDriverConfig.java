package support;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppDriverConfig {
	
	@Value(value="#{'${selenuim.sauce}'}")
    private Boolean sauceTest;
	
	@Value(value="#{'${selenuim.sauce.version}'}")
    private String version;
	
	@Value(value="#{'${selenuim.sauce.platform}'}")
    private String platform;
	
	@Value(value="#{'${selenuim.sauce.address}'}")
    private String address;

	
	@Bean(name="webDriver",destroyMethod="quit")
	public WebDriver configure(){
		WebDriver webDriver=null;
		if(isSauceTest()){
			try{
				final DesiredCapabilities capabillities = getDesiredCapabilities();
		        capabillities.setCapability("version", version);
		        capabillities.setCapability("platform", resolvePlatform());
		        capabillities.setCapability("name", getTestName());
				webDriver = new RemoteWebDriver(new URL(address), capabillities);
			}catch(MalformedURLException ex){
				throw new RuntimeException(ex);
			}
		}else{
			webDriver = new FirefoxDriver();
		}
		webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return webDriver;
	}
	
	private Platform resolvePlatform(){
		if(platform.equalsIgnoreCase("linux")){
			return Platform.LINUX;
		}else if(platform.equalsIgnoreCase("windows")){
			return Platform.WINDOWS;
		}else{
			return Platform.getCurrent();
		}
	}
	
	private String getTestName(){
		if(isNotEmpty(TestNameListener.testName)){
			return TestNameListener.testName;
		}
		return "hrm web client test";
	}
	
	private boolean isSauceTest(){
		final String env = System.getProperty("sauceconn");
		//TODO In CI server doesn't SAUCE_CONN env variable yet, need to fix that 
		boolean envSauceConn = false;
		if(isNotEmpty(env)){
			final Boolean b = new Boolean(env.trim());
			envSauceConn = b.booleanValue();
		}
		if(!envSauceConn){
			return sauceTest;
		}
		return envSauceConn;
	}
	
	private DesiredCapabilities getDesiredCapabilities(){
		final String browser = System.getProperty("browser");
		DesiredCapabilities capabillities = null;
		
		if(!isNotEmpty(browser)){
			return DesiredCapabilities.firefox();
		}
		if("firefox".equalsIgnoreCase(browser)){
			capabillities = DesiredCapabilities.firefox();
		}else if("chrome".equalsIgnoreCase(browser)){
			capabillities = DesiredCapabilities.chrome();
		}else if("ie".equalsIgnoreCase(browser)){
			capabillities = DesiredCapabilities.internetExplorer();
		}else{
			capabillities = DesiredCapabilities.firefox();
		}
		
		return capabillities;
	}
	
	private boolean isNotEmpty(String value){
		if(value != null && !value.isEmpty()){
			return true;
		}
		return false;
	}

}
