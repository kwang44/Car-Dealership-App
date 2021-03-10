package com.dealership.db;

import com.dealership.models.Payment;

import java.sql.*;

public class PaymentJDBC implements GenericDao<Payment, Payment> {
    private static PaymentJDBC instance;

    private PaymentJDBC(){}

    static PaymentJDBC getInstance() {
        if (instance == null)
            instance = new PaymentJDBC();
        return instance;
    }

    @Override
    public int save(Payment payment) {
        try {
            Connection conn = JDBCConnection.getInstance().getConnnection();
            PreparedStatement ps = conn.prepareStatement("insert into car_payment values (?,?,?,?,?,?)");
            ps.setString(1, payment.getUsername());
            ps.setString(2, payment.getVin());
            ps.setBigDecimal(3, payment.getTotalAmount());
            ps.setBigDecimal(4, payment.getPaymentMade());
            ps.setBigDecimal(5, payment.getMonthlyPaymentAmount());
            ps.setInt(6, payment.getMonthlyPaymentLeft());
            int i = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public Payment get(Payment pk) {
        return null;
    }

    @Override
    public Payment[] getAll() {
        try {
            Connection conn = JDBCConnection.getInstance().getConnnection();
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("select username, vin, total_amount, payment_made, monthly_payment_amount, monthly_payment_left from car_payment");
            rs.last();
            int rowCount = rs.getRow();
            if (rowCount == 0) {
                return null;
            }
            rs.beforeFirst();
            Payment[] payments = new Payment[rowCount];
            rowCount = 0;

            while (rs.next()) {
                payments[rowCount] = new Payment(rs.getString("username"),
                        rs.getString("vin"),
                        rs.getBigDecimal("total_amount"),
                        rs.getBigDecimal("payment_made"),
                        rs.getBigDecimal("monthly_payment_amount"),
                        rs.getInt("monthly_payment_left")
                        );
                rowCount++;
            }
            return payments;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Payment[] getAll(Payment pk) {
        try {
            Connection conn = JDBCConnection.getInstance().getConnnection();
            PreparedStatement ps = conn.prepareStatement("select username, vin, total_amount, payment_made, monthly_payment_amount, monthly_payment_left from car_payment where (username = ?)", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, pk.getUsername());
            ResultSet rs = ps.executeQuery();
            rs.last();
            int rowCount = rs.getRow();
            if (rowCount == 0) {
                return null;
            }
            rs.beforeFirst();
            Payment[] payments = new Payment[rowCount];
            rowCount = 0;

            while (rs.next()) {
                payments[rowCount] = new Payment(rs.getString("username"),
                        rs.getString("vin"),
                        rs.getBigDecimal("total_amount"),
                        rs.getBigDecimal("payment_made"),
                        rs.getBigDecimal("monthly_payment_amount"),
                        rs.getInt("monthly_payment_left")
                );
                rowCount++;
            }
            return payments;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int remove(Payment pk) {
        try {
            Connection conn = JDBCConnection.getInstance().getConnnection();
            PreparedStatement ps = conn.prepareStatement("delete from car_payment where (username = ? and vin = ?)");
            ps.setString(1, pk.getUsername());
            ps.setString(2, pk.getVin());
            int i = ps.executeUpdate();
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int update(Payment payment) {
        return 0;
    }

    @Override
    public int insertOrUpdate(Payment payment) {
        try {
            Connection conn = JDBCConnection.getInstance().getConnnection();
            PreparedStatement ps = conn.prepareStatement("insert into car_payment (username, vin, total_amount, payment_made, monthly_payment_amount, monthly_payment_left) values (?,?,?,?,?,?) " +
                    "on conflict (username, vin) do update set total_amount = ?, payment_made = ?, monthly_payment_amount = ?, monthly_payment_left = ?");
            ps.setString(1, payment.getUsername());
            ps.setString(2, payment.getVin());
            ps.setBigDecimal(3, payment.getTotalAmount());
            ps.setBigDecimal(4, payment.getPaymentMade());
            ps.setBigDecimal(5, payment.getMonthlyPaymentAmount());
            ps.setInt(6, payment.getMonthlyPaymentLeft());
            ps.setBigDecimal(7, payment.getTotalAmount());
            ps.setBigDecimal(8, payment.getPaymentMade());
            ps.setBigDecimal(9, payment.getMonthlyPaymentAmount());
            ps.setInt(10, payment.getMonthlyPaymentLeft());
            int i = ps.executeUpdate();
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
