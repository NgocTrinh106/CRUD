package repository;

import model.Employee;
import utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class EmployeeRepository {
    private static final String SELECT_ALL_EMPLOYEES = "SELECT * FROM employee";
    private static final String FIND_EMPLOYEE_BY_ID = "SELECT * FROM employee WHERE id = ?";
    private static final String INSERT_NEW_EMPLOYEE = "INSERT INTO employee(id, name, age, address, salary, startdate) VALUES (?,?,?,?,?,?)";
    private static final String UPDATE_EMPLOYEE_BY_ID = "UPDATE employee SET name = ?, age = ?, address = ?, salary = ?, startdate = ? WHERE id = ?";
    private static final String DELETE_EMPLOYEE_BY_ID = "DELETE FROM employee WHERE id = ?";

    public List<Employee> getAllEmployees() {
        List<Employee> listEmp = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_EMPLOYEES)) {
            ResultSet rs = preparedStatement.executeQuery(); //Thực thi câu lệnh SQL và lấy kết quả trả về bởi executeQuery().
            while (rs.next()) {

                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String address = rs.getString("address");
                int salary = rs.getInt("salary");
                Date startDate = rs.getDate("startdate");
                Employee employee = new Employee(id, name, age, address, salary, startDate);
                listEmp.add(employee);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return listEmp;
    }

    public Employee findEmployeeById(int id) {
        Employee employee = null;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_EMPLOYEE_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String address = rs.getString("address");
                int salary = rs.getInt("salary");
                Date startDate = rs.getDate("startdate");
                employee = new Employee(id, name, age, address, salary, startDate);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return employee;
    }

    public void addNewEmployee(Employee employee) {

        Employee checkEmp = findEmployeeById(employee.getId());
        if (checkEmp != null) {
            System.out.println("Đã tồn tại nhân viên với mã nhân viên: " + employee.getId());
            System.out.println("Nhân viên: " + checkEmp.toString()); //in ra thông tin của nhân viên đó bằng cách gọi checkEmp.toString().
        } else {
            try (Connection connection = DBConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW_EMPLOYEE))
            {
                preparedStatement.setInt(1, employee.getId());
                preparedStatement.setString(2, employee.getName());
                preparedStatement.setInt(3, employee.getAge());
                preparedStatement.setString(4, employee.getAddress());
                preparedStatement.setInt(5, employee.getSalary());
                preparedStatement.setDate(6, employee.getStartDate());
                preparedStatement.executeUpdate();
            }
            catch (SQLException e) {
                printSQLException(e);
            }
        }

    }


    public boolean updateEmployee(Employee employee) {
        Employee checkEmp = findEmployeeById(employee.getId());
        boolean rowUpdated = false;
        if (checkEmp == null) {
            System.out.println("Không tồn tại nhân viên với mã nhân viên: " + employee.getId());
        } else {
            try (Connection connection = DBConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EMPLOYEE_BY_ID))
            {
                preparedStatement.setString(1, employee.getName());
                preparedStatement.setInt(2, employee.getAge());
                preparedStatement.setString(3, employee.getAddress());
                preparedStatement.setInt(4, employee.getSalary());
                preparedStatement.setDate(5, employee.getStartDate());
                preparedStatement.setInt(6, employee.getId());
                preparedStatement.executeUpdate();
                rowUpdated = preparedStatement.executeUpdate() > 0;
            } catch (SQLException e) {
                printSQLException(e);
            }
        }
        return rowUpdated;
    }


    public boolean deleteEmployeeById(int id) {
        Employee checkEmp = findEmployeeById(id);
        boolean rowDeleted = false;
        if (checkEmp == null) {
            System.out.println("Không tồn tại nhân viên với mã nhân viên: " + id);
        } else {
            try (Connection connection = DBConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EMPLOYEE_BY_ID);) {
                preparedStatement.setInt(1, id);
                rowDeleted = preparedStatement.executeUpdate() > 0;
            } catch (SQLException e) {
                printSQLException(e);
            }
        }
        return rowDeleted;
    }


    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

//    public static void main(String[] args) {
//        EmployeeRepository employeeRepository = new EmployeeRepository();
//        List<Employee> employeeList = employeeRepository.getAllEmployees();
////        for (int i = 0; i < employeeList.size(); i++) {
////            System.out.println(employeeList.get(i).getName());
////        }
//
//
//        for (int i = 0; i < employeeList.size(); i++) {
//            if(employeeList.get(i).getName().equals("Trinh")){
//                System.out.print(employeeList.get(i).toString() + " , ");
//            }
//        }
//

        //System.out.println(employeeList.get(0));



//        if (employeeList.isEmpty()) {
//            System.out.println("không có nhân viên nào trong csdl");
//        } else {
//            for (int i = 0; i < employeeList.size(); i++) {
//                System.out.println(employeeList.get(i).toString());
//            }
//        }

//        Employee employee = new Employee(3, "tuan", 22, "đà nẵng", 4000, Date.valueOf("2020-08-28"));
//        employeeRepository.updateEmployee(employee);
//        Employee employee = employeeRepository.findEmployeeById(1);
//        System.out.println(employee.toString());
//    }
}
