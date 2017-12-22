import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCDemo {
	public static void main(String[] argv){
		JDBCDemo jdbc = new JDBCDemo();
		jdbc.getConnection();
		jdbc.selectData("select * from journaltractional limit 10");
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
	public void selectData(String query){
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		conn = getConnection();
		try {
			stmt = conn.createStatement();
			pstmt = conn.prepareStatement(query);
			rs = stmt.executeQuery(query);
			rs1 = pstmt.executeQuery();
			for (int i=1,j=rs.getMetaData().getColumnCount();i<j;i++){  // column name
				System.out.print(rs.getMetaData().getColumnName(i)+"\t");
			}
			System.out.println();
			while (rs.next()){  
				for (int i=1,j=rs.getMetaData().getColumnCount();i<j;i++){  // column name
					System.out.print(rs.getString(i)+"\t");
				}
				System.out.println();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if (null != conn){try{conn.close();} catch (SQLException e1){e1.printStackTrace();}}
			if (null != rs){try{rs.close();} catch (SQLException e2){e2.printStackTrace();}}
			if (null != stmt){try{stmt.close();} catch (SQLException e3){e3.printStackTrace();}}
			if (null != rs1){try{rs1.close();} catch (SQLException e4){e4.printStackTrace();}}
		}
	}
}
