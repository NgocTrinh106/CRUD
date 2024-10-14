package service;

import model.Employee;
import repository.EmployeeRepository;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class EmployeeServiceImpl implements EmployeeService {
    EmployeeRepository employeeRepository = new EmployeeRepository();

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }
    @Override
    public Employee findEmployeeById(int id) {
        return employeeRepository.findEmployeeById(id);
    }

    @Override
    public void addNewEmployee(Employee employee) {
        employeeRepository.addNewEmployee(employee);
    }


    @Override
    public boolean updateEmployee(Employee employee) {
        return employeeRepository.updateEmployee(employee);
    }


    @Override
    public boolean deleteEmployeeById(int id) {
        return employeeRepository.deleteEmployeeById(id);
    }


    public Employee writeInfoEmployee() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập vào id:");
        int id = Integer.parseInt(sc.nextLine());
        System.out.println("Nhập vào name:");
        String name = sc.nextLine();
        System.out.println("Nhập vào age:");
        int age = Integer.parseInt(sc.nextLine());
        System.out.println("Nhập vào address:");
        String address = sc.nextLine();
        System.out.println("Nhập vào salary:");
        int salary = Integer.parseInt(sc.nextLine());
        System.out.println("Nhập vào startDate:");
        Date startDate = Date.valueOf(sc.nextLine());
        Employee employee = new Employee(id, name, age, address, salary, startDate);
        return employee;
    }
    public int writeIdEmployee() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập vào id:");
        int id = Integer.parseInt(sc.nextLine());
        return id;
    }
    public void showAllEmployees(List<Employee> employeeList){

        for (int i = 0; i < employeeList.size(); i++) {
            System.out.println(employeeList.get(i).toString());
        }
    }
}
