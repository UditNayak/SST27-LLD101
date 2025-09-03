package exercise;

public class EmployeeCSVAdaptor implements Employee {
    private final EmployeeCSV employeeCSV;

    public EmployeeCSVAdaptor(EmployeeCSV employeeCSV) {
        this.employeeCSV = employeeCSV;
    }

    @Override
    public String getId() {
        return employeeCSV.tokens()[0];
    }

    @Override
    public String getFirstName() {
        return employeeCSV.tokens()[1];
    }

    @Override
    public String getLastName() {
        return employeeCSV.tokens()[2];
    }

    @Override
    public String getEmail() {
        return employeeCSV.tokens()[3];
    }
}
