package Controllers.Web;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Models.Product;
import Models.Supplier;
import Services.ICategoryService;
import Services.IProductService;
import Services.ISupplierService;
import Services.Impl.CategoryService;
import Services.Impl.ProductService;
import Services.Impl.SupplierService;

@WebServlet(urlPatterns = { "/home", "/product-detail", "/addToCart", "/search" })
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ICategoryService cateService = new CategoryService();
	private IProductService proService = new ProductService();
	private ISupplierService supplierService = new SupplierService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI().toString();
		req.setAttribute("listCategory", cateService.findAll());
		List<Supplier> suppliers = supplierService.findAll();
		for (Supplier i : suppliers) {
			i.setCount(supplierService.countProduct(i.getSupplier_id()));
		}
		req.setAttribute("suppliers", suppliers);
		if (url.contains("home")) {
			getHome(req, resp);
		} else if (url.contains("product-detail")) {
			getProductDetail(req, resp);
		} else if (url.contains("add-to-cart")) {
			getAddCart(req, resp);
		}
	}

	private void getAddCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int product_id = Integer.parseInt(req.getParameter("product_id"));
		int quantity = Integer.parseInt(req.getParameter("quantity"));

		resp.sendRedirect(req.getContextPath() + "/product-detail?product_id=" + product_id);
	}

	private void getProductDetail(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Product product;
		if (req.getParameter("product_id") == null || req.getParameter("product_id").isEmpty()) {
			product = proService.findByID(1);
		} else {
			int product_id = Integer.parseInt(req.getParameter("product_id"));
			product = proService.findByID(product_id);
		}
		req.setAttribute("product", product);

		Supplier supplier = supplierService.findById(product.getSupplier_id());
		req.setAttribute("supplier", supplier);

		// Handle product same category
		List<Product> products_same_category = proService.findByCategory(product.getCategory_id());
		List<List<Product>> matrix_same_cate = new ArrayList<>();

		for (int i = 0; i < products_same_category.size(); i += 3) {
			int endIndex = Math.min(i + 3, products_same_category.size());
			List<Product> row = new ArrayList<>(products_same_category.subList(i, endIndex));
			matrix_same_cate.add(row);
		}

		req.setAttribute("same_cate_active", matrix_same_cate.get(0));
		req.setAttribute("same_cate", matrix_same_cate.subList(1, matrix_same_cate.size()));

		// Handle product same supplier
		List<Product> products_same_supplier = proService.findBySupplier(product.getSupplier_id());
		List<List<Product>> matrix_same_supplier = new ArrayList<>();

		for (int i = 0; i < products_same_supplier.size(); i += 3) {
			int endIndex = Math.min(i + 3, products_same_supplier.size());
			List<Product> row = new ArrayList<>(products_same_supplier.subList(i, endIndex));
			matrix_same_supplier.add(row);
		}

		req.setAttribute("same_supplier_active", matrix_same_supplier.get(0));
		req.setAttribute("same_supplier", matrix_same_supplier.subList(1, matrix_same_supplier.size()));

		req.getRequestDispatcher("Views/web/product-detail.jsp").forward(req, resp);
	}

	private void getHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Product> list_pro;
		if (req.getParameter("category_id") != null) {
			int category_id = Integer.parseInt(req.getParameter("category_id"));
			list_pro = proService.findByCategory(category_id);
		} else if (req.getParameter("supplier_id") != null) {
			int supplier_id = Integer.parseInt(req.getParameter("supplier_id"));
			list_pro = proService.findBySupplier(supplier_id);
		} else if (req.getParameter("price_range") != null) {
			int price_range = Integer.parseInt(req.getParameter("price_range"));
			float start_range = 0, end_range = 0;
			if (price_range == 1) {
				start_range = 0;
				end_range = 100;
			} else if (price_range == 2) {
				start_range = 100;
				end_range = 500;
			} else if (price_range == 3) {
				start_range = 500;
				end_range = 1000;
			} else if (price_range == 4) {
				start_range = 1000;
				end_range = 2000;
			} else if (price_range == 5) {
				start_range = 2000;
				end_range = 999999;
			}
			list_pro = proService.findByPrice(start_range, end_range);
		} else {
			list_pro = proService.findAll();
		}
		if (list_pro.size() == 0) {
			req.setAttribute("message", "Không có sản phẩm");
		}
		req.setAttribute("listProduct", list_pro);
		req.getRequestDispatcher("Views/web/home.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    String url = req.getRequestURI().toString();
	    if (url.contains("search")) {
	        postSearch(req, resp);
	    }
	}

	private void postSearch(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
	    String searchValue = req.getParameter("searchInput");
	    List<Product> products = proService.findByName(searchValue);
	    if (products.size() == 0) {
	        req.setAttribute("message", "Không có sản phẩm");
	    }
	    req.setAttribute("listProduct", products);
	    req.setAttribute("listCategory", cateService.findAll());
		List<Supplier> suppliers = supplierService.findAll();
		for (Supplier i : suppliers) {
			i.setCount(supplierService.countProduct(i.getSupplier_id()));
		}
		req.setAttribute("suppliers", suppliers);
	    // Chuyển hướng sau khi xử lý tìm kiếm
		req.getRequestDispatcher("Views/web/home.jsp").forward(req, resp);
	}

}
