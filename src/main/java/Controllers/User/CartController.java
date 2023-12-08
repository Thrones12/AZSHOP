package Controllers.User;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Models.Cart;
import Services.ICartSerive;
import Services.Impl.CartService;

@WebServlet(urlPatterns = { "/user/cart", "/user/update-cart", "/user/delete-cart"})
public class CartController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	ICartSerive cartService = new CartService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI().toString();
		if(url.contains("/user/cart")) {
			getCart(req,resp);
		} else if(url.contains("/user/update-cart")) {
			getUpdate(req,resp);
		} else if(url.contains("/user/delete-cart")) {
			getDelete(req,resp);
		}
	}

	private void getDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		cartService.delete(Integer.parseInt(req.getParameter("cart_id")));
		resp.sendRedirect(req.getContextPath() + "/user/cart");
	}

	private void getUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cart cart = cartService.findById(Integer.parseInt(req.getParameter("cart_id")));
		int new_quantity = cart.getQuantity() + Integer.parseInt(req.getParameter("quantity"));
		cart.setQuantity(new_quantity);
		cartService.update(cart);
		resp.sendRedirect(req.getContextPath() + "/user/cart");
	}

	private void getCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int user_id = 4;
		List<Cart> carts = cartService.findByUserID(user_id);
		req.setAttribute("carts", carts);
		
		float total = 0;
		for (Cart cart : carts) {
			total += cart.getTotal_price();
		}
		
		req.setAttribute("total", total);;
		
		req.getRequestDispatcher("/Views/user/cart.jsp").forward(req, resp);
	}
}
