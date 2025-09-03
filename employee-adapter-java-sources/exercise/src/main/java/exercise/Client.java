package exercise;

import java.util.*;

public class Client {
  public static void main(String[] args) {
    List<Employee> all = new EmployeeRepository().getEmployeesList();
    EmployeePrinter.print(all);
  }
}
