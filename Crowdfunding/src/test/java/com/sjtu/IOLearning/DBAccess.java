package com.sjtu.IOLearning;

import java.sql.*;

public class DBAccess {
	public static void main(String[] argv){
		DBAccess db = new DBAccess ();
		db.getData("select * from crowd_busniess");
	}
	public Connection getConnection(){
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/crowdfunding","root","hackaming");	
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e1){
			e1.printStackTrace();
		} 
		return conn;
	}
	public void getData(String query){
		Statement stmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		if (null == conn){ //does not get connection;
			return;
		}
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			int j = rs.getMetaData().getColumnCount();
			for (int i=1;i<j;i++){ //column name
				System.out.print(rs.getMetaData().getColumnName(i)+"\t"); // in the same line
			}
			System.out.println(); // another line
			while (rs.next()){
				for (int i=1;i<j;i++){
					System.out.print(rs.getString(i)+"\t"); // in the same line
				}
				System.out.println(); //another line
			}
			
		} catch (SQLException e1){
			e1.printStackTrace();
		} finally{
			if (null != conn ){ try{conn.close();}catch (SQLException e1){e1.printStackTrace();}}
			if (null != stmt ){try {stmt.close();} catch (SQLException e2) {e2.printStackTrace();}}
			if (null != rs){try{rs.close();}catch(SQLException e3){e3.printStackTrace();}}
		}
	}

}
