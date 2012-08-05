package support.stubs;

import org.springframework.beans.factory.annotation.Autowired;

import com.hrm.ui.test.api.EmployeeTestApi;
import com.hrm.ws.api.service.EmployeeService;
import com.hrm.ws.schema.bean.employee.Employee;

public class StubEmployeeService implements EmployeeService{

	@Autowired
	EmployeeTestApi employeeTestApi;
	
	@Override
	public Employee getEmployee(Long id) {
		Employee emp = new Employee();
		emp.setId(id);
		emp.setFirstName("dummy first");
		emp.setLastName("dummy last");
		return emp;
	}

	@Override
	public void saveEmployee(Employee employee) {
		employeeTestApi.setEmployee(employee);
	}
	
	
}
