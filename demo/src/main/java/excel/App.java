package excel;

class Employee {
	private String employeeName;
	private String employeeDesignation;
	private double salary;
	// All 3 fields getter, setter methods should be there
	public Employee() {}

	public String toString()
	{
		return String.format("%s - %s - %f", employeeName,
							employeeDesignation, salary);
	}

	public String getEmployeeName() { return employeeName; }

	public void setEmployeeName(String employeeName)
	{
		this.employeeName = employeeName;
	}

	public String getEmployeeDesignation()
	{
		return employeeDesignation;
	}

	public void
	setEmployeeDesignation(String employeeDesignation)
	{
		this.employeeDesignation = employeeDesignation;
	}

	public double getSalary() { return salary; }

	public void setSalary(double d) { this.salary = d; }
	// Main program
}

