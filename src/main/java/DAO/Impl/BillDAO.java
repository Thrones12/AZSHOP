package DAO.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Connection.DBConnection;
import DAO.IBillDAO;

import Models.Bill;
public class BillDAO implements IBillDAO{
	
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
	@Override
	public List<Bill> findAll() {
		// TODO Auto-generated method stub
		List<Bill> listbill = new ArrayList<Bill>();
        String query = "SELECT * FROM bills";
        try {
        	Connection conn = new DBConnection().getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Bill bill = new Bill();
                bill.setBill_id(rs.getInt("bill_id"));
                bill.setUser_id(rs.getInt("user_id"));
                bill.setOrder_date(rs.getDate("order_date"));
                bill.setTotal_amount(rs.getBigDecimal("total_amount"));
                
                listbill.add(bill);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listbill;
	}

	@Override
	public void insert(Bill bill) {
		// TODO Auto-generated method stub
		String query = "INSERT INTO bills(bill_id, user_id, order_date, total_amount) VALUES (?, ?, ?, ?)";
        try {
        	Connection conn = new DBConnection().getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
            
            ps.setInt(1, bill.getBill_id());
            ps.setInt(2, bill.getBill_id());
            ps.setDate(3, bill.getOrder_date());
            ps.setBigDecimal(4, bill.getTotal_amount());
            ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	@Override
	public void update(Bill bill) {
		// TODO Auto-generated method stub
        String query = "UPDATE bills SET user_id = ?,order_date = ?,total_amount = ? WHERE bill_id = ? ";
        try {
        	Connection conn = new DBConnection().getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			
            ps.setInt(1,bill.getBill_id());
			ps.setDate(2,bill.getOrder_date());
            ps.setBigDecimal(3, bill.getTotal_amount());
            ps.setInt(4,bill.getBill_id());
            
            ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	@Override
	public void delete(int bill_id) {
		// TODO Auto-generated method stub
		 String query = "DELETE FROM bills WHERE bill_id = ? ";
	        try {
	        	Connection conn = new DBConnection().getConnection();
				PreparedStatement ps = conn.prepareStatement(query);
				
	            ps.setInt(1, bill_id);
	            ps.executeUpdate();
	            conn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}

	@Override
	public Bill findById(int bill_id) {
		// TODO Auto-generated method stub
		Bill bill = new Bill();
		String query = "SELECT * FROM bills WHERE bill_id = ? ";
		try {
        	Connection conn = new DBConnection().getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			
            ps.setInt(1, bill_id);
            rs = ps.executeQuery();
            while (rs.next()) {
            	bill.setBill_id(rs.getInt("bill_id"));
            	bill.setUser_id(rs.getInt("user_id"));
            	bill.setOrder_date(rs.getDate("order_date"));
            	bill.setTotal_amount(rs.getBigDecimal("total_amount"));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bill;
		
	}
	

}
