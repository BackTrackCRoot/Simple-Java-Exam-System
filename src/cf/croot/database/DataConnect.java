package cf.croot.database;

import java.sql.*;

import javax.swing.JOptionPane;

public class DataConnect {
	public static Connection ConnectDb() {
		// TODO Auto-generated method stub
		Connection c = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:/E://exam.db");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.toString());
			JOptionPane.showMessageDialog(null, e.toString(), "数据库连接信息", JOptionPane.INFORMATION_MESSAGE);
		}
		return c;
	}

}
