package example.entity;

public class EmployeeDelegate {

	private final Employee employee;

	public EmployeeDelegate(Employee employee) {
		if (employee == null) {
			throw new NullPointerException("employee");
		}
		this.employee = employee;
	}

	public String getSalaryDescription() {
		if (employee.salary().isNull()) {
			return "unknown";
		}
		return employee.salary().ge(2000) ? "high" : "low";
	}
}
