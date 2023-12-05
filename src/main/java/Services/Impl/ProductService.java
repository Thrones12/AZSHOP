package Services.Impl;

import java.util.List;

import DAO.IProductDAO;
import DAO.Impl.ProductDAO;
import Models.Product;
import Services.IProductService;

public class ProductService implements IProductService{
	IProductDAO proDAO = new ProductDAO();
	@Override
	public List<Product> findAll() {
		return proDAO.findAll();
	}

	@Override
	public void Insert(Product product) {
		proDAO.Insert(product);
	}

	@Override
	public void Update(Product product) {
		proDAO.Update(product);
	}

	@Override
	public void Delete(int product_id) {
		proDAO.Delete(product_id);
	}

	@Override
	public Product findByID(int product_id) {
		return proDAO.findByID(product_id);
	}

	@Override
	public Product findByName(String product_name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> findByCategory(int category_id) {
		return proDAO.findByCategory(category_id);
	}

	@Override
	public List<Product> findBySupplier(int supplier_id) {
		return proDAO.findBySupplier(supplier_id);
	}

	@Override
	public int countByCategory(int category_id) {
		return proDAO.countByCategory(category_id);
	}

}
