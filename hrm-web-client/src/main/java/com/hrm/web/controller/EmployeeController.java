package com.hrm.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hrm.ws.api.client.EmployeeServiceGateway;
import com.hrm.ws.schema.bean.employee.Employee;
import com.hrm.ws.schema.bean.employee.EmployeeResponse;
import com.hrm.ws.schema.bean.employee.GetEmployeeRequest;

@Controller
@RequestMapping(value="*")
public class EmployeeController {
	
	@Autowired
	EmployeeServiceGateway empService;
	
	@Autowired
	HttpServletRequest request;
	
	@RequestMapping(value = "/find", method = RequestMethod.GET)
	public String find(ModelMap model){
		String strId = request.getParameter("empId");
		Long id = 0L;
	
		if(strId != null && !strId.equals("")){
			id = Long.parseLong(strId);
		}
		GetEmployeeRequest empReq = new GetEmployeeRequest();
		empReq.setId(id);
		
		EmployeeResponse empRes = empService.getEmployee(empReq);
		Employee e = empRes.getEmployee().get(0);
		model.addAttribute("employee", e);
		return "findEmployee";
	}
}
