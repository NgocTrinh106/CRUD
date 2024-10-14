package controller;

import model.Employee;
import service.EmployeeServiceImpl;
import java.util.List;
import java.util.Scanner;

public class Controller {

    public static void main(String[] args) {
        EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Vui lòng nhập vào 1 trong các lựa chọn sau: " + "\n 1.Hiển thị tất cả nhân viên" + "\n 2.Thêm mới nhân viên" + "\n 3.Chỉnh sửa nhân viên" + "\n 4.Xóa nhân viên" + "\n 5.Tìm kiếm nhân viên" + "\n Số nhập:");
            int n = Integer.parseInt(sc.nextLine());
            switch (n) {
                case 1:
                    List<Employee> employeeList = employeeService.getAllEmployees();
                    employeeService.showAllEmployees(employeeList);
                    break;
                case 2:
                    Employee insertEmployee = employeeService.writeInfoEmployee();
                    employeeService.addNewEmployee(insertEmployee);
                    break;
                case 3:
                    Employee updateEmployee = employeeService.writeInfoEmployee();
                    employeeService.updateEmployee(updateEmployee);
                    break;
                case 4:
                    employeeService.deleteEmployeeById(employeeService.writeIdEmployee());
                    break;
                case 5:
                    Employee employee = employeeService.findEmployeeById(employeeService.writeIdEmployee());
                    System.out.println(employee.toString());
                    break;
                case 6:
                    System.out.println("Chương trình đã dừng.");
                    sc.close();
                    return;
                default:
                    System.out.println("Hành động nào hợp lệ!");
            }
        } while (true);
    }

}
