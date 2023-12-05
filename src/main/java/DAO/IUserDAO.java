package DAO;

import java.util.List;

import Models.User;

public interface IUserDAO {

	List<User> findAll();
	
	void insert(User model);
	
	User findOne(int id);
	
	void update(User model);
	
	void delete(int id);
}
