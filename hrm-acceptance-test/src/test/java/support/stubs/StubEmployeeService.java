package support.stubs;

import com.hrm.ws.api.service.EmployeeService;
import com.hrm.ws.schema.bean.employee.Employee;

public class StubEmployeeService implements EmployeeService{

	@Override
	public Employee getEmployee(Long id) {
		Employee emp = new Employee();
		emp.setId(id);
		emp.setFirstName("dummy first");
		emp.setLastName("dummy last");
		return emp;
	}
	
	
}
