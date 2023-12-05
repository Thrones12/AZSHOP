package DAO.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Connection.DBConnection;
import DAO.IProductDAO;
import Models.Product;

public class ProductDAO implements IProductDAO{
	Connection conn;
	PreparedStatement ps;
	ResultSet rs;

	@Override
	public List<Product> findAll() {
		List<Product> list_pro = new ArrayList<Product>();
		String query = "SELECT * FROM products";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				Product pro = new Product();
				pro.setProduct_id(rs.getInt("product_id"));
				pro.setProduct_name(rs.getString("product_name"));
				pro.setDescription(rs.getString("description"));
				pro.setPrice(rs.getInt("price"));
				pro.setImage(rs.getString("image"));
				pro.setCategory_id(rs.getInt("category_id"));
				pro.setSupplier_id(rs.getInt("supplier_id"));
				pro.setStock_quantity(rs.getInt("stock_quantity"));
				pro.setSold_quantity(rs.getInt("sold_quantity"));
				list_pro.add(pro);
			}
			conn.close();
			return list_pro;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void Insert(Product product) {
		String query = "INSERT INTO products (product_name, description, price, image, category_id, supplier_id, stock_quantity, sold_quantity)\r\n"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, product.getProduct_name());
			ps.setString(2, product.getDescription());
			ps.setFloat(3, product.getPrice());
			ps.setString(4, product.getImage());
			ps.setInt(5, product.getCategory_id());
			ps.setInt(6, product.getSupplier_id());
			ps.setInt(7, product.getStock_quantity());
			ps.setInt(8, product.getSold_quantity());
			ps.executeUpdate();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void Update(Product product) {
		String query = "UPDATE products SET product_name=?, description=?, price=?, image=?, category_id=?, supplier_id=?, stock_quantity=?, sold_quantity=?"
				+ " WHERE product_id = ? ;";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, product.getProduct_name());
			ps.setString(2, product.getDescription());
			ps.setFloat(3, product.getPrice());
			ps.setString(4, product.getImage());
			ps.setInt(5, product.getCategory_id());
			ps.setInt(6, product.getSupplier_id());
			ps.setInt(7, product.getStock_quantity());
			ps.setInt(8, product.getSold_quantity());
			ps.setInt(9, product.getProduct_id());
			ps.executeUpdate();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void Delete(int product_id) {
		String query = "DELETE FROM products\r\n"
				+ "WHERE product_id = ?;";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, product_id);
			ps.executeUpdate();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Product findByID(int product_id) {
		Product pro = new Product();
		String query="SELECT * FROM products WHERE product_id=?";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, product_id);
			rs = ps.executeQuery();
			while(rs.next()) {
				pro.setProduct_id(rs.getInt("product_id"));
				pro.setProduct_name(rs.getString("product_name"));
				pro.setDescription(rs.getString("description"));
				pro.setPrice(rs.getFloat("price"));
				pro.setImage(rs.getString("image"));
				pro.setCategory_id(rs.getInt("category_id"));
				pro.setSupplier_id(rs.getInt("supplier_id"));
				pro.setStock_quantity(rs.getInt("stock_quantity"));
				pro.setSold_quantity(rs.getInt("sold_quantity"));
			}
			conn.close();
			return pro;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Product findByName(String product_name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> findByCategory(int category_id) {
		List<Product> list_pro = new ArrayList<Product>();
		String query="SELECT * FROM products WHERE category_id=?";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, category_id);
			rs = ps.executeQuery();
			while(rs.next()) {
				Product pro = new Product();
				pro.setProduct_id(rs.getInt("product_id"));
				pro.setProduct_name(rs.getString("product_name"));
				pro.setDescription(rs.getString("description"));
				pro.setPrice(rs.getFloat("price"));
				pro.setImage(rs.getString("image"));
				pro.setCategory_id(rs.getInt("category_id"));
				pro.setSupplier_id(rs.getInt("supplier_id"));
				pro.setStock_quantity(rs.getInt("stock_quantity"));
				pro.setSold_quantity(rs.getInt("sold_quantity"));
				list_pro.add(pro);
			}
			conn.close();
			return list_pro;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Product> findBySupplier(int supplier_id) {
		List<Product> list_pro = new ArrayList<Product>();
		String query="SELECT * FROM products WHERE supplier_id=?";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, supplier_id);
			rs = ps.executeQuery();
			while(rs.next()) {
				Product pro = new Product();
				pro.setProduct_id(rs.getInt("product_id"));
				pro.setProduct_name(rs.getString("product_name"));
				pro.setDescription(rs.getString("description"));
				pro.setPrice(rs.getFloat("price"));
				pro.setImage(rs.getString("image"));
				pro.setCategory_id(rs.getInt("category_id"));
				pro.setSupplier_id(rs.getInt("supplier_id"));
				pro.setStock_quantity(rs.getInt("stock_quantity"));
				pro.setSold_quantity(rs.getInt("sold_quantity"));
				list_pro.add(pro);
			}
			conn.close();
			return list_pro;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int countByCategory(int category_id) {
		int count = 0;
		String query="SELECT count(*) as count FROM\r\n"
				+ "(SELECT * FROM azshop.products WHERE category_id = ?) as product_of_cate";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, category_id);
			rs = ps.executeQuery();
			while(rs.next()) {
				count = rs.getInt("count");
			}
			conn.close();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
