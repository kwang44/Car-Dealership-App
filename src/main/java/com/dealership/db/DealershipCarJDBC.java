package com.dealership.db;

import com.dealership.models.DealershipCar;

import java.sql.*;

public class DealershipCarJDBC implements GenericDao<DealershipCar, String>{

    private static DealershipCarJDBC instance;

    private DealershipCarJDBC(){}

    static DealershipCarJDBC getInstance() {
        if (instance == null)
            instance = new DealershipCarJDBC();
        return instance;
    }

    @Override
    public int save(DealershipCar dealershipCar) {
        try {
            Connection conn = JDBCConnection.getInstance().getConnnection();
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement("insert into car (vin, year, make, model) values (?,?,?,?)");
            ps.setString(1, dealershipCar.getVin());
            ps.setString(2, dealershipCar.getYear());
            ps.setString(3, dealershipCar.getMake());
            ps.setString(4, dealershipCar.getModel());
            int i = ps.executeUpdate();
            if (i != 1) {
                conn.rollback();
                return -1;
            }

            ps = conn.prepareStatement("insert into car_for_sale (vin, msrp) values (?,?)");
            ps.setString(1, dealershipCar.getVin());
            ps.setBigDecimal(2, dealershipCar.getMsrp());
            i = ps.executeUpdate();
            if (i != 1) {
                conn.rollback();
                return -1;
            }

            conn.commit();
            return i;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    @Override
    public DealershipCar get(String pk) {
        return null;
    }

    @Override
    public DealershipCar[] getAll() {
        try {
            Connection conn = JDBCConnection.getInstance().getConnnection();
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("select vin, msrp, year, make, model from dealership_car_view");
            rs.last();
            int rowCount = rs.getRow();
            if (rowCount == 0) {
                return null;
            }
            rs.beforeFirst();

            DealershipCar[] dc = new DealershipCar[rowCount];
            rowCount = 0;

            while (rs.next()) {
                dc[rowCount] = new DealershipCar(rs.getString("year"), rs.getString("make"), rs.getString("model"), rs.getString("vin"), rs.getBigDecimal("msrp"));
                rowCount++;
            }

            return dc;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public DealershipCar[] getAll(String pk) {
        return new DealershipCar[0];
    }

    @Override
    public int remove(String pk) {
        try {
            Connection conn = JDBCConnection.getInstance().getConnnection();
            PreparedStatement ps = conn.prepareStatement("delete from car_for_sale where vin = ?");
            ps.setString(1, pk);
            int i = ps.executeUpdate();
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    @Override
    public int update(DealershipCar dealershipCar) {
        return 0;
    }

    @Override
    public int insertOrUpdate(DealershipCar dealershipCar) {
        return 0;
    }
}
