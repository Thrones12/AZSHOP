package Controllers.Web;

import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet(urlPatterns = { "/home", "/product-detail", "/search", "/loadAjax" })
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
		} else if (url.contains("loadAjax")) {
			getHomeAjax(req, resp);
		}
	}

	private void getHomeAjax(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int existingCount = 0;
		String existingCountParam = req.getParameter("exists");
		if (existingCountParam != null) {
			existingCount = Integer.parseInt(existingCountParam);
		}
		List<Product> products;
		if (Integer.parseInt(req.getParameter("category_id")) != 0) {
			products = proService.findTop3ByCategory(Integer.parseInt(req.getParameter("category_id")), existingCount);
		} else if (Integer.parseInt(req.getParameter("supplier_id")) != 0) {
			products = proService.findTop3BySupplier(Integer.parseInt(req.getParameter("supplier_id")), existingCount);
		} else if (Integer.parseInt(req.getParameter("end_range")) != 0) {
			products = proService.findTop3ByPrice(Integer.parseInt(req.getParameter("start_range")),
					Integer.parseInt(req.getParameter("end_range")), existingCount);
		} else {
			products = proService.findTop3(existingCount);
			System.out.println(existingCount);
		}
		System.out.println(req.getParameter("category_id") != "0");
		System.out.println(req.getParameter("category_id"));

		PrintWriter out = resp.getWriter();
		for (Product product : products) {
			out.println("<div class=\"productAjax col-sm-4\">\r\n"
					+ "						<div class=\"product-image-wrapper\">\r\n"
					+ "							<div class=\"single-products\">\r\n"
					+ "								<div class=\"productinfo text-center\">\r\n"
					+ "									<a\r\n"
					+ "										href=\"<c:url value='/product-detail?product_id="
					+ product.getProduct_id() + "'></c:url>\">\r\n"
					+ "										<img style=\"width: 270px; height: 270px\"\r\n"
					+ "										src=\"templates/images/product/" + product.getImage()
					+ "\" alt=\"\" />\r\n" + "									</a>\r\n"
					+ "									<h2>$" + product.getPrice() + "</h2>\r\n"
					+ "									<h4>" + product.getProduct_name() + "</h4>\r\n"
					+ "								</div>\r\n" + "							</div>\r\n"
					+ "						</div>\r\n" + "					</div>");
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
		int category_id = 0;
		int supplier_id = 0;
		float start_range = 0, end_range = 0;
		if (req.getParameter("category_id") != null) {
			category_id = Integer.parseInt(req.getParameter("category_id"));
			list_pro = proService.findTop3ByCategory(category_id, 0);
		} else if (req.getParameter("supplier_id") != null) {
			supplier_id = Integer.parseInt(req.getParameter("supplier_id"));
			list_pro = proService.findTop3BySupplier(supplier_id, 0);
		} else if (req.getParameter("price_range") != null) {
			int price_range = Integer.parseInt(req.getParameter("price_range"));
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
			list_pro = proService.findTop3ByPrice(start_range, end_range, 0);
		} else {
			list_pro = proService.findTop3(0);
		}
		if (list_pro.size() == 0) {
			req.setAttribute("message", "Không có sản phẩm");
		}
		req.setAttribute("listProduct", list_pro);
		req.setAttribute("category_id", category_id);
		req.setAttribute("supplier_id", supplier_id);
		req.setAttribute("start_range", start_range);
		req.setAttribute("end_range", end_range);
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
