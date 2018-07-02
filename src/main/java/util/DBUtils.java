package util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

public class DBUtils {
	private static String driver;
	private static String url;
	private static String username;
	private static String password;
	private static int initSize;
	private static int maxActive;
	private static BasicDataSource ds;
	static{	
		ds=new BasicDataSource();
		Properties cfg=new Properties();
		try {			
			InputStream in=DBUtils.class.getClassLoader().
					getResourceAsStream("db.properties");
			cfg.load(in);
			System.out.println(cfg);
			driver=cfg.getProperty("jdbc.driver");
			url=cfg.getProperty("jdbc.url");
			username=cfg.getProperty("jdbc.username");
			password=cfg.getProperty("jdbc.password");
			initSize=Integer.parseInt(cfg.getProperty("initSize"));
			maxActive =Integer.parseInt(cfg.getProperty("maxActive"));		
			in.close();
			ds.setDriverClassName(driver);
			ds.setUrl(url);
			ds.setUsername(username);
			ds.setPassword(password);
			ds.setInitialSize(initSize);
			ds.setMaxActive(maxActive);
		} catch (IOException e) {
			e.printStackTrace();
		}				
	}
	public static Connection getConnection(){
		try{			
			Connection conn=ds.getConnection();
			return conn;
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}		
	}
	public static void close (Connection conn){
		if(conn!=null){
			try{
				conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	public static void rollback (Connection conn){
		if(conn!=null){
			try{
				conn.rollback();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		Connection conn=DBUtils.getConnection();
		System.out.println(conn);
		DBUtils.close(conn);
	}
}
