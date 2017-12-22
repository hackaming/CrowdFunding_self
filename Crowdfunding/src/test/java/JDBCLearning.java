import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCLearning {
	public Connection getConnection(){
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/crowdfunding","root","hackaming");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e2){
			e2.printStackTrace();
		}
		return conn;
	}
	public void selectFromMysql(){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		conn = getConnection();
		String sql = "select * from orders limit 100";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			for (int i=1,j=rs.getMetaData().getColumnCount();i<j;i++){ //column name
				System.out.print(rs.getMetaData().getColumnName(i)+"\t");
			}
			System.out.println();
			while(rs.next()){
				for (int i=1,j=rs.getMetaData().getColumnCount();i<j;i++){
					System.out.print(rs.getString(i)+"\t");
				}
				System.out.println();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if (null != conn){try{conn.close();} catch (SQLException e1){e1.printStackTrace();}}
			if (null != stmt){try{stmt.close();} catch (SQLException e2){e2.printStackTrace();}	}
			if (null != rs){try{rs.close();}catch (SQLException e3){e3.printStackTrace();}}
		}
	}
	public static void main(String[] argv){
		JDBCLearning jdbc = new JDBCLearning();
		jdbc.selectFromMysql();
	}
}
