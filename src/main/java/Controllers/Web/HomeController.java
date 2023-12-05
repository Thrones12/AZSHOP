package Controllers.Web;

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

@WebServlet(urlPatterns = { "/home", "/product-detail" })
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ICategoryService cateService = new CategoryService();
	private IProductService proService = new ProductService();
	private ISupplierService supplierServcie = new SupplierService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI().toString();
		req.setAttribute("listCategory", cateService.findAll());
		if (url.contains("home")) {
			getHome(req, resp);
		} else if (url.contains("product-detail")) {
			getProductDetail(req, resp);
		}
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

		Supplier supplier = supplierServcie.findById(product.getSupplier_id());
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
		if (req.getParameter("category_id") == null || req.getParameter("category_id").isEmpty()) {
			list_pro = proService.findAll();
		} else {
			int category_id = Integer.parseInt(req.getParameter("category_id"));
			list_pro = proService.findByCategory(category_id);
		}
		req.setAttribute("listProduct", list_pro);

		req.getRequestDispatcher("Views/web/home.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
}
