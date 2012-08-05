package com.hrm.jms;

import com.hrm.ws.schema.bean.employee.Employee;

public interface RegistrationService {
	void sendMessage(Employee employee);
}
