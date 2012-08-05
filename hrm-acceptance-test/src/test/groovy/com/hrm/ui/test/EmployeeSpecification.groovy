package com.hrm.ui.test

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.hrm.ui.test.api.EmployeeTestApi;

import support.SeleniumAppDriver;
import support.TestNameListener;

@ContextConfiguration(locations = "/spring-selenium-test.xml")
@TestExecutionListeners([TestNameListener.class,DependencyInjectionTestExecutionListener.class])
class EmployeeSpecification extends spock.lang.Specification {
	
	@Autowired
	private SeleniumAppDriver appDriver;
	
	@Autowired
	EmployeeTestApi employeeTestApi;
	
	def "web application loads index page"(){
		when:
		appDriver.showHomePage()
		
		then:
		appDriver.findElementOnPage("txt_find") == "empId"
	}
	
	def "system should find employee by given id"(){
		when:
		appDriver.findEmployee "1"
		
		then:
		appDriver.resultPage() == true
	}
	
	def "web application loads registration page"(){
		when:
		appDriver.showRegistrationPage()
		
		then:
		appDriver.findElementOnPage("firstName") == "firstName"
		appDriver.findElementOnPage("lastName") == "lastName"
	}
	
	def "system should register new employee"(){
		when:
		appDriver.showRegistrationPage()
		appDriver.register("jone","perera")
		
		then:
		appDriver.hasElement("page_process")
		appDriver.waitFor(2000)
		employeeTestApi.employee.firstName == "jone"
		employeeTestApi.employee.lastName == "perera"
	}
}
