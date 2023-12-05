package DAO;

import java.util.List;

import Models.Product;

public interface IProductDAO {
	List<Product> findAll();
	void Insert(Product product);
	void Update(Product product);
	void Delete(int product_id);
	Product findByID(int product_id);
	Product findByName(String product_name);
	List<Product> findByCategory(int category_id);
	List<Product> findBySupplier(int supplier_id);
	int countByCategory(int category_id);
}
