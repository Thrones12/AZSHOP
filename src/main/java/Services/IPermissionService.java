package Services;

import java.util.List;

import Models.Permission;

public interface IPermissionService {

	 List<Permission> findAll();
	    
	 void insert(Permission permission);
	    
	 void update(Permission permission);
	    
	 void delete(int permission_id);
	    
	 Permission findById(int permission_id);
}
