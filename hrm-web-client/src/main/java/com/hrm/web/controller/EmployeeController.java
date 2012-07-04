package com.hrm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hrm.ws.schema.bean.employee.Employee;

@Controller
@RequestMapping(value="*")
public class EmployeeController {
	
	@RequestMapping(value = "/find", method = RequestMethod.GET)
	public String find(ModelMap model){
		Employee e = new Employee();//TODO This should get form web service
		e.setId(1);
		e.setFirstName("Hasitha");//TODO Test
		e.setLastName("Wijesekara");
		model.addAttribute("employee", e);
		return "findEmployee";
	}
}
