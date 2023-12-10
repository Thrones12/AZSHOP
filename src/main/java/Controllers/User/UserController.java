package Controllers.User;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import Models.User;
import Services.ICartSerive;
import Services.IUserService;
import Services.Impl.CartService;
import Services.Impl.UserSerive;

@WebServlet(urlPatterns = { "/user/account", "/user/wishlist", "/user/history",})
public class UserController extends HttpServlet{

	IUserService userService = new UserSerive();
	ICartSerive cartService = new CartService();
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI().toString();
		if(url.contains("/user/account")) {
			getAccount(req,resp);
		}else if(url.contains("/user/wishlist")) {
			getWishlist(req,resp);
		}else if(url.contains("/user/history")) {
			getHistory(req,resp);
		}
	}

	private void getHistory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/Views/user/history.jsp").forward(req, resp);
	}
	
	private void getWishlist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/Views/user/wishlist.jsp").forward(req, resp);
	}

	private void getAccount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/Views/user/account.jsp").forward(req, resp);
	}
}
