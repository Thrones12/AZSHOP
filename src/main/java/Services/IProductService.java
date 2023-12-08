package Services;

import java.util.List;

import Models.Product;

public interface IProductService {
	List<Product> findAll();
	void Insert(Product product);
	void Update(Product product);
	void Delete(int product_id);
	Product findByID(int product_id);
	Product findByName(String product_name);
	List<Product> findByCategory(int category_id);
	List<Product> findBySupplier(int supplier_id);
	List<Product> findByPrice(float start_range, float end_range);
	int countByCategory(int category_id);
}
