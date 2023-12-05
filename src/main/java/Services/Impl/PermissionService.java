package Services.Impl;

import java.util.List;

import DAO.IPermissionDAO;
import DAO.Impl.PermissionDAO;
import Models.Permission;
import Services.IPermissionService;

public class PermissionService implements IPermissionService{
	IPermissionDAO perDAO = new PermissionDAO();
	@Override
	public List<Permission> findAll() {
		return perDAO.findAll();
	}

	@Override
	public void insert(Permission permission) {
		perDAO.insert(permission);
	}

	@Override
	public void update(Permission permission) {
		perDAO.update(permission);
	}

	@Override
	public void delete(int permission_id) {
		perDAO.delete(permission_id);
	}

	@Override
	public Permission findById(int permission_id) {
		return perDAO.findById(permission_id);
	}

}
