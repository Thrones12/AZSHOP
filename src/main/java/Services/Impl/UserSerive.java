package Services.Impl;

import java.util.List;

import DAO.IUserDAO;
import DAO.Impl.UserDAO;
import Models.User;
import Services.IUserService;

public class UserSerive implements IUserService {
	
	IUserDAO userDao = new UserDAO();

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public void insert(User model) {
		userDao.insert(model);
		
	}

	@Override
	public User findOne(int id) {
		return userDao.findOne(id);
	}

	@Override
	public void update(User model) {
		User olduser = userDao.findOne(model.getUserID());
		olduser.setUserID(model.getUserID());
		olduser.setUserName(model.getUserName());
		olduser.setPassword(model.getPassword());
		olduser.setEmail(model.getEmail());
		olduser.setRole(model.getRole());
		olduser.setStatus(model.getStatus());
		
		userDao.update(olduser);
	}

	@Override
	public void delete(int id) {
		userDao.delete(id);
		
	}

}
