package cf.croot.database;

import java.sql.*;

import javax.swing.JOptionPane;

public class Users {
//	public static void main(String[] args) {
//		Connection c = DataConnect.ConnectDb();
//		if(c!=null){
//			try {
//				c.setAutoCommit(false);
//				Statement stmt = c.createStatement();
//				ResultSet rs = stmt.executeQuery("SELECT * FROM Student where UserName ='student1' and Password ='123456'");
//				while(rs.next()){
//					System.out.println(rs.getString("UserName"));
//					System.out.println(rs.getString("PassWord"));
//				}
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}
	public static boolean CheckPassWordAndUser(String UserName,String Password){
		Connection c = DataConnect.ConnectDb();
		if(c!=null){
			try {
				c.setAutoCommit(false);
				String sql = "SELECT * FROM Student where UserName =? and Password =?";
				PreparedStatement pstmt = c.prepareStatement(sql);
				pstmt.setString(1,UserName);
				pstmt.setString(2,Password);
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()){
					pstmt.close();
					rs.close();
					return true;
				}
				else 
					pstmt.close();
					rs.close();
					return false;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, e.toString(), "用户连接信息", JOptionPane.INFORMATION_MESSAGE);
				return false;
			}
		}
//		return true;
		return false;
	}
}
