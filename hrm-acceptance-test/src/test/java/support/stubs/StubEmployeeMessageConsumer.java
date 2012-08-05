package support.stubs;

import org.springframework.beans.factory.annotation.Autowired;

import com.hrm.jms.api.consumer.EmployeeMessageConsumer;
import com.hrm.ws.api.service.EmployeeService;
import com.hrm.ws.schema.bean.employee.Employee;

public class StubEmployeeMessageConsumer implements EmployeeMessageConsumer{

	@Autowired
	private EmployeeService employeeService;
	
	@Override
	public void consume(Employee employee) {
		employeeService.saveEmployee(employee);
	}
	
}
