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
import Services.IUserService;
import Services.Impl.UserSerive;

@WebServlet(urlPatterns = { "/user/account", "/user/wishlist", "/user/history", "/user/cart", 
		"/admin-list", "/admin/user/insert", "/admin/user/update", "/admin/user/delete" })
public class UserController extends HttpServlet{

	IUserService userService = new UserSerive();
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI().toString();
		if(url.contains("admin-list")) {
			findAll(req, resp);
		}else if(url.contains("insert")) {
			RequestDispatcher rd = req.getRequestDispatcher("/Views/admin/user/insert.jsp");
			rd.forward(req, resp);
		}else if(url.contains("update")) {
			int id = Integer.parseInt(req.getParameter("id"));
			User model = userService.findOne(id);
			req.setAttribute("user", model);
			RequestDispatcher rd = req.getRequestDispatcher("/Views/admin/user/update.jsp");
			rd.forward(req, resp);
		}else if(url.contains("delete")) {
			delete(req,resp);
		}else if(url.contains("/user/account")) {
			getAccount(req,resp);
		}else if(url.contains("/user/wishlist")) {
			getWishlist(req,resp);
		}else if(url.contains("/user/history")) {
			getHistory(req,resp);
		}else if(url.contains("/user/cart")) {
			getCart(req,resp);
		}
	}

	private void getCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/Views/user/cart.jsp").forward(req, resp);
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

	private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		try {
			userService.delete(Integer.parseInt(req.getParameter("id")));
			req.setAttribute("message", "Xoa thanh cong");
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Xoa that bai");
		}
		resp.sendRedirect(req.getContextPath()+"/admin-list");
		
	}

	private void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<User> list = userService.findAll();
		req.setAttribute("listuser", list);
		RequestDispatcher rd = req.getRequestDispatcher("Views/admin/user/list.jsp");
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI().toString();
		if(url.contains("insert")) {
			insert(req,resp);
		}else if(url.contains("update")) {
			update(req,resp);
		}
	}

	private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		User user = new User();
		try {
			BeanUtils.populate(user, req.getParameterMap());
			userService.update(user);
			req.setAttribute("message", "Chinh sua thanh cong");
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Chinh sua that bai");
		}
		resp.sendRedirect(req.getContextPath() + "/admin-list");
	}

	private void insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		User user = new User();
		try {
			BeanUtils.populate(user, req.getParameterMap());
			userService.insert(user);
			req.setAttribute("message", "Them thanh cong");
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Them that bai");
		}
		resp.sendRedirect(req.getContextPath() + "/admin-list");
		
	}
}
