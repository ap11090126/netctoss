package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import entity.Admin;
import util.DBUtils;

public class AdminDaoImpl implements Serializable, AdminDao {

	public Admin findByCode(String code) {
		Connection conn=null;
		try {
			conn=DBUtils.getConnection();
			String sql="select * from admin_info "
					+ "where admincode=?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, code);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				Admin a=new Admin();
				a.setAdminId(rs.getInt("id"));
				a.setAdminCode(rs.getString("admincode"));
				a.setPassword(rs.getString("password"));
				a.setName(rs.getString("name"));
				a.setTelephone(rs.getString("telephone"));
				a.setEmail(rs.getString("email"));
				a.setEnrolldate(rs.getTimestamp("enrolldate"));
				return a;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(
					"查询管理员失败",e);
		}finally{
			DBUtils.close(conn);
		}
		
		return null;
	}
	public static void main(String[] args) {
		AdminDao dao=new AdminDaoImpl();
		Admin a=dao.findByCode("jeffrey");
		System.out.println(a.getAdminId());
		System.out.println(a.getTelephone());
	}
	
	

}
