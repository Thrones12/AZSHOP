package Controllers.User;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Models.Bill;
import Models.BillDetail;
import Models.Cart;
import Models.User;
import Services.IBillDetailService;
import Services.IBillService;
import Services.ICartSerive;
import Services.Impl.BillDetailService;
import Services.Impl.BillService;
import Services.Impl.CartService;

@WebServlet(urlPatterns = { "/user/checkout", "/user/thanks", "/user/addbill" })
public class CheckoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ICartSerive cartService = new CartService();
	IBillService billService = new BillService();
	IBillDetailService billDetailService = new BillDetailService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI().toString();
		if (url.contains("checkout")) {
			getCheckout(req, resp);
		} else if (url.contains("thanks")) {
			getThanks(req, resp);
		} else if (url.contains("addbill")) {
			getBill(req, resp);
		}

	}

	private void getBill(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		User u = (User) session.getAttribute("account");
		int user_id = u.getUserID();
		java.util.Date order_date = new java.util.Date();
		String total_amount = req.getParameter("total_amount");
		Bill bill = new Bill();
		bill.setUser_id(user_id);
		bill.setOrder_date(new java.sql.Date(order_date.getTime()));
		bill.setTotal_amount(new BigDecimal(total_amount));
		bill.setReceiver(req.getParameter("receiver"));
		bill.setPhone(req.getParameter("phone"));
		bill.setAddress(req.getParameter("address"));
		
		billService.insert(bill);
		
		List<Cart> carts = cartService.findByUserID(user_id);
		for (Cart cart : carts) {
			BillDetail billdetail = new BillDetail();
			billdetail.setBillID(billService.findNewestBillByUserID(user_id).getBill_id());
			billdetail.setProductID(cart.getProduct_id());
			billdetail.setQuantity(cart.getQuantity());
			billdetail.setPrice(new BigDecimal(cart.getTotal_price()));
			billDetailService.insert(billdetail);
			cartService.delete(cart.getCart_id());
		}
	}

	private void getThanks(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/Views/user/thanks.jsp").forward(req, resp);
	}

	private void getCheckout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User u = (User) session.getAttribute("account");
		int user_id = u.getUserID();
		List<Cart> carts = cartService.findByUserID(user_id);
		req.setAttribute("carts", carts);

		float total = 0;
		for (Cart cart : carts) {
			total += cart.getTotal_price();
		}

		req.setAttribute("total", total);
		int shipCost = 10;
		req.setAttribute("total_amount", total + shipCost);

		req.getRequestDispatcher("/Views/user/checkout.jsp").forward(req, resp);
	}
}
