package com.dealership.db;

import com.dealership.models.Offer;

import java.sql.*;

public class OfferJDBC implements GenericDao<Offer, Offer>{

    private static OfferJDBC instance;

    private OfferJDBC(){}

    static OfferJDBC getInstance() {
        if (instance == null)
            instance = new OfferJDBC();
        return instance;
    }

    @Override
    public int save(Offer offer) {
        return 0;
    }

    @Override
    public Offer get(Offer pk) {
        return null;
    }

    @Override
    public Offer[] getAll() {
        try {
            Connection conn = JDBCConnection.getInstance().getConnnection();
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("select username, vin, offer_amount, payments from offer");
            rs.last();
            int rowCount = rs.getRow();
            if (rowCount == 0) {
                return null;
            }
            rs.beforeFirst();
            Offer[] offers = new Offer[rowCount];
            rowCount = 0;

            while (rs.next()) {
                offers[rowCount] = new Offer(rs.getString("username"), rs.getString("vin"), rs.getBigDecimal("offer_amount"), rs.getInt("payments"));
                rowCount++;
            }
            return offers;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Offer[] getAll(Offer pk) {
        return null;
    }

    @Override
    public int remove(Offer pk) {

        if (pk.getUsername() != null) {
            try {
                Connection conn = JDBCConnection.getInstance().getConnnection();
                PreparedStatement ps = conn.prepareStatement("delete from offer where (vin = ? and username = ?)");
                ps.setString(1, pk.getVin());
                ps.setString(2, pk.getUsername());
                int i = ps.executeUpdate();
                return i;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                Connection conn = JDBCConnection.getInstance().getConnnection();
                PreparedStatement ps = conn.prepareStatement("delete from offer where vin = ?");
                ps.setString(1, pk.getVin());
                int i = ps.executeUpdate();
                return i;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return -1;
    }

    @Override
    public int update(Offer offer) {
        return 0;
    }

    @Override
    public int insertOrUpdate(Offer offer) {
        try {
            Connection conn = JDBCConnection.getInstance().getConnnection();
            PreparedStatement ps = conn.prepareStatement("insert into offer (username, vin, offer_amount, payments) values (?,?,?,?) " +
                    "on conflict (username, vin) do update set offer_amount = ?, payments = ?");
            ps.setString(1, offer.getUsername());
            ps.setString(2, offer.getVin());
            ps.setBigDecimal(3, offer.getOfferAmount());
            ps.setInt(4, offer.getPayments());
            ps.setBigDecimal(5, offer.getOfferAmount());
            ps.setInt(6, offer.getPayments());
            int i = ps.executeUpdate();
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
