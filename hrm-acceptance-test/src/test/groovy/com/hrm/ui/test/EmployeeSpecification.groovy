package com.hrm.ui.test

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import support.SeleniumAppDriver;
import support.TestNameListener;

@ContextConfiguration(locations = "/spring-selenium-test.xml")
@TestExecutionListeners([TestNameListener.class,DependencyInjectionTestExecutionListener.class])
class EmployeeSpecification extends spock.lang.Specification {
    
	@Autowired
	private SeleniumAppDriver appDriver;
	
	def "web application loads index page"(){
		when:
		appDriver.showHomePage()
		
		then:
		appDriver.findSearchBox() == "empId"
	}
	
	def "employee service should find employee by given id"(){
		when:
		appDriver.findEmployee "1"
		
		then:
		appDriver.resultPage() == true
	}
}
