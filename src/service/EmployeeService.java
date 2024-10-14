package service;

import model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    Employee findEmployeeById(int id);
    void addNewEmployee(Employee employee);
    boolean updateEmployee(Employee employee);
    boolean deleteEmployeeById(int id);
}
