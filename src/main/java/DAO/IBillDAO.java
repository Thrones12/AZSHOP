package DAO;

import java.util.List;

import Models.Bill;

public interface IBillDAO {

	 List<Bill> findAll();
	    
	 void insert(Bill bill);
	    
	 void update(Bill bill);
	    
	 void delete(int bill_id);
	    
	 Bill findById(int bill_id);
}
