package com.dealership.db;

import com.dealership.models.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerJDBC implements GenericDao<Customer, String>{

    private static CustomerJDBC instance;

    private CustomerJDBC(){}

    static CustomerJDBC getInstance() {
        if (instance == null)
            instance = new CustomerJDBC();
        return instance;
    }


    @Override
    public int save(Customer customer) {
        try {
            Connection conn = JDBCConnection.getInstance().getConnnection();
            PreparedStatement ps = conn.prepareStatement("insert into customer (username, password) values (?,?)");
            ps.setString(1, customer.getUsername());
            ps.setString(2, customer.getPassword());
            int i = ps.executeUpdate();
//            System.out.println("The number of updated rows were " + i);
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public Customer get(String pk) {
        try {
            Connection conn = JDBCConnection.getInstance().getConnnection();
            PreparedStatement ps = conn.prepareStatement("select username, password from customer c where c.username = ?");
            ps.setString(1, pk);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String username = rs.getString(1);
                String password = rs.getString(2);
                return new Customer(username, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Customer[] getAll() {
        return new Customer[0];
    }

    @Override
    public Customer[] getAll(String pk) {
        return new Customer[0];
    }

    @Override
    public int remove(String pk) {
        return 0;
    }

    @Override
    public int update(Customer customer) {
        return 0;
    }

    @Override
    public int insertOrUpdate(Customer customer) {
        return 0;
    }

}
