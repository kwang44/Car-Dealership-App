package com.dealership.db;

import com.dealership.models.Customer;
import com.dealership.models.CustomerCar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerCarJDBC implements GenericDao<CustomerCar, Customer>{

    private static CustomerCarJDBC instance;

    private CustomerCarJDBC(){}

    static CustomerCarJDBC getInstance() {
        if (instance == null)
            instance = new CustomerCarJDBC();
        return instance;
    }

    @Override
    public int save(CustomerCar customerCar) {
        try {
            Connection conn = JDBCConnection.getInstance().getConnnection();
            PreparedStatement ps = conn.prepareStatement("insert into customer_car (username, vin) values (?,?)");
            ps.setString(1, customerCar.getCustomerUsername());
            ps.setString(2, customerCar.getVin());
            int i = ps.executeUpdate();
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public CustomerCar get(Customer pk) {
        return null;
    }

    @Override
    public CustomerCar[] getAll() {
        return new CustomerCar[0];
    }

    @Override
    public CustomerCar[] getAll(Customer pk) {
        Connection conn = null;
        try {
            conn = JDBCConnection.getInstance().getConnnection();
            PreparedStatement ps = conn.prepareStatement("select username, vin, make, model, year from customer_car_view where username = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, pk.getUsername());
            ResultSet rs = ps.executeQuery();
            rs.last();
            int rowCount = rs.getRow();
            if (rowCount == 0) {
                return null;
            }
            rs.beforeFirst();

            CustomerCar[] cc = new CustomerCar[rowCount];
            rowCount = 0;

            while (rs.next()) {
                cc[rowCount] = new CustomerCar(rs.getString("year"),
                        rs.getString("make"),
                        rs.getString("model"),
                        rs.getString("vin"),
                        rs.getString("username")
                );
                rowCount++;
            }
            return cc;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int remove(Customer pk) {
        return 0;
    }

    @Override
    public int update(CustomerCar customerCar) {
        return 0;
    }

    @Override
    public int insertOrUpdate(CustomerCar customerCar) {
        return 0;
    }
}
