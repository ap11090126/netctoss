package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.DBUtils;

import entity.Cost;

public class CostDaoImpl implements Serializable, CostDao {

	public List<Cost> findAll() {
		Connection conn=null;
		try {
			conn=DBUtils.getConnection();
			String sql="select * from cost order by id";
			Statement smt=conn.createStatement();
			ResultSet rs=smt.executeQuery(sql);
			List<Cost> list=new ArrayList<Cost>();
			while(rs.next()){
				Cost c = creatCost(rs);
				list.add(c);
			}
			return list;
		}catch(Exception e){
			//记录日记
			e.printStackTrace();
			throw new RuntimeException(
					"查询资费失败",e);
		}
		finally {
			DBUtils.close(conn);
		}
		
	}
	private Cost creatCost(ResultSet rs) throws SQLException {
		Cost c=new Cost();
		c.setCostId(rs.getInt("id"));
		c.setName(rs.getString("name"));
		c.setBaseDuration(rs.getInt("base_duration"));
		c.setUnitCost(rs.getDouble("unit_cost"));
		c.setBaseCost(rs.getDouble("base_cost"));
		c.setStatus(rs.getString("status"));
		c.setDescr(rs.getString("descr"));
		c.setCreatime(rs.getTimestamp("creatime"));
		c.setStartime(rs.getTimestamp("startime"));
		c.setCostType(rs.getString("costtype"));
		return c;
	}
	public void save(Cost c) {
		Connection conn=null;
		try {
			conn=DBUtils.getConnection();
			String sql="insert into cost values("
					+ "last_insert_id(),"
					+ "?,?,?,?,1,?,sysdate(),null,?)";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1,c.getName());
			ps.setObject(2,c.getBaseDuration());
			ps.setObject(3,c.getBaseCost());
			ps.setObject(4,c.getUnitCost());
			ps.setString(5,c.getDescr());
			ps.setString(6,c.getCostType());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("增加员工失败",e);
		}finally{
			DBUtils.close(conn);
		}
		
	}
	
	public Cost findById(int id) {
		Connection conn=null;
		try {
			conn=DBUtils.getConnection();
			String sql="select * from cost where id=?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				return creatCost(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("查询资费失败",e);
		}finally{
			DBUtils.close(conn);
		}
		return null;
	}
	public static void main(String[] args) {
		CostDao dao=new CostDaoImpl();
		/*List<Cost> list=dao.findAll();
		for(Cost c:list){
			System.out.println(c.getCostId()+":"+
		c.getName()+"、"+c.getBaseCost());
			
		}*/
		/*Cost c=new Cost();
		c.setName("包月");
		//c.setBaseDuration(600);
		c.setBaseCost(60.0);
		//c.setUnitCost(0.6);
		c.setDescr("包月很爽");
		c.setCostType("1");
		dao.save(c);*/
		Cost c=dao.findById(1);
		System.out.println(c.getName());
		System.out.println(c.getDescr());
	}




}
