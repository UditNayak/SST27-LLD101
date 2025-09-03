package exercise;

public class SimpleEmployeeFactory {

    public static Employee createEmployee(String source, Object data) {
        switch (source) {
            case "CSV":
                return new EmployeeCSVAdaptor((EmployeeCSV) data);
            case "DB":
                return new EmployeeDBAdaptor((EmployeeDB) data);
            case "LDAP":
                return new EmployeeLdapAdaptor((EmployeeLDAP) data);
            default:
                throw new IllegalArgumentException("Unknown source: " + source);
        }
    }
}
