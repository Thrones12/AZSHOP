package Services;

import java.util.List;

import Models.User;

public interface IUserService {
List<User> findAll();
	
	void insert(User model);
	
	User findOne(int id);
	
	void update(User model);
	
	void delete(int id);
}
