package com.hrm.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import com.hrm.jms.RegistrationService;
import com.hrm.web.validator.EmployeeValidator;
import com.hrm.ws.api.client.EmployeeServiceGateway;
import com.hrm.ws.schema.bean.employee.Employee;
import com.hrm.ws.schema.bean.employee.EmployeeResponse;
import com.hrm.ws.schema.bean.employee.GetEmployeeRequest;

@Controller
@RequestMapping(value = "*")
public class EmployeeController {

	@Autowired
	EmployeeValidator employeeValidator;

	@Autowired
	EmployeeServiceGateway empService;
	
	@Autowired
	RegistrationService registrationService;

	@Autowired
	HttpServletRequest request;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(@ModelAttribute("employee") Employee employee) {
		employee = new Employee();
		return "registerEmployee";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String processRegister(
			@ModelAttribute("employee") Employee employee,
			BindingResult result, SessionStatus status, ModelMap model) {
		
		employeeValidator.validate(employee, result);
		
		if (result.hasErrors()) {
			return "registerEmployee";
		} else {
			status.setComplete();
			registrationService.sendMessage(employee);
			return "process";
		}
	}

	@RequestMapping(value = "/find", method = RequestMethod.GET)
	public String find(ModelMap model) {
		final String strId = request.getParameter("empId");
		Long id = 0L;

		if (strId != null && !strId.equals("")) {
			id = Long.parseLong(strId);
		}
		final GetEmployeeRequest empReq = new GetEmployeeRequest();
		empReq.setId(id);

		final EmployeeResponse empRes = empService.getEmployee(empReq);
		final Employee e = empRes.getEmployee().get(0);
		model.addAttribute("employee", e);
		return "viewEmployee";
	}
}
