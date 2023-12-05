package DAO.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Connection.DBConnection;
import DAO.IPermissionDAO;
import Models.Permission;

public class PermissionDAO implements IPermissionDAO{
	Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    
	@Override
	public List<Permission> findAll() {
		List<Permission> listpermission = new ArrayList<Permission>();
        String query = "SELECT * FROM permissions";
        try {
        	Connection conn = new DBConnection().getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
            	Permission permission = new Permission();
            	permission.setPermission_id(rs.getInt("permission_id"));
            	permission.setPermission_name(rs.getString("permission_name"));
                listpermission.add(permission);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listpermission;
	}

	@Override
	public void insert(Permission permission) {
        String query = "INSERT INTO permissions(permission_name) VALUES (?)";
        try {
        	Connection conn = new DBConnection().getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
            
            ps.setString(1, permission.getPermission_name());
            ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	@Override
	public void update(Permission permission) {
		String query = "UPDATE permissions SET permission_name = ? WHERE permission_id = ? ";
        try {
        	Connection conn = new DBConnection().getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			
            ps.setString(1, permission.getPermission_name());
            ps.setInt(2, permission.getPermission_id());
            ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	@Override
	public void delete(int permission_id) {
		String query = "DELETE FROM permissions WHERE permission_id = ? ";
        try {
        	Connection conn = new DBConnection().getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			
            ps.setInt(1, permission_id);
            ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	@Override
	public Permission findById(int permission_id) {
		Permission permission = new Permission();
        String query = "SELECT * FROM permissions WHERE permission_id = ? ";
        try {
        	Connection conn = new DBConnection().getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			
            ps.setInt(1, permission_id);
            rs = ps.executeQuery();
            while (rs.next()) {
            	permission.setPermission_id(rs.getInt("permission_id"));
            	permission.setPermission_name(rs.getString("permission_name"));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return permission;
	}
	
}
