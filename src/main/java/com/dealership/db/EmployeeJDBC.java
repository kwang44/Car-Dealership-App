package com.dealership.db;

import com.dealership.models.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeJDBC implements GenericDao<Employee, String> {

    private static EmployeeJDBC instance;

    static EmployeeJDBC getInstance() {
        if (instance == null)
            instance = new EmployeeJDBC();
        return instance;
    }

    @Override
    public int save(Employee employee) {
        try {
            Connection conn = JDBCConnection.getInstance().getConnnection();
            PreparedStatement ps = conn.prepareStatement("insert into employee (username, password) values (?,?)");
            ps.setString(1, employee.getUsername());
            ps.setString(2, employee.getPassword());
            int i = ps.executeUpdate();
//            System.out.println("The number of updated rows were " + i);
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public Employee get(String pk) {
        try {
            Connection conn = JDBCConnection.getInstance().getConnnection();
            PreparedStatement ps = conn.prepareStatement("select username, password from employee c where c.username = ?");
            ps.setString(1, pk);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String username = rs.getString(1);
                String password = rs.getString(2);
                return new Employee(username, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Employee[] getAll() {
        return new Employee[0];
    }

    @Override
    public Employee[] getAll(String pk) {
        return new Employee[0];
    }

    @Override
    public int remove(String pk) {
        return 0;
    }

    @Override
    public int update(Employee employee) {
        return 0;
    }

    @Override
    public int insertOrUpdate(Employee employee) {
        return 0;
    }
}
