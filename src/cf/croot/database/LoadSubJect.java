package cf.croot.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class LoadSubJect{
	private String ExamCategory = null;
	public LoadSubJect(String ExamCategory){
		this.ExamCategory = ExamCategory;
	}
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}
	public HashMap<String, String> getSubjet(int Id) {
		// TODO Auto-generated method stub
		HashMap<String,String> map = new HashMap<String , String>();
		try {
			Connection c =DataConnect.ConnectDb();
			String sql = "select * from Subjects where Category = ? and Id =?";
			PreparedStatement pstmt = c.prepareStatement(sql);
			pstmt.setString(1, ExamCategory);
			pstmt.setInt(2, Id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				map.put("Subject", rs.getString("Subject"));
				map.put("Answer", rs.getString("Answer"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	public int getSubjectCount(){
		try {
			Connection c =DataConnect.ConnectDb();
			String sql = "select count(*) from Subjects where Category = ?";
			PreparedStatement pstmt = c.prepareStatement(sql);
			pstmt.setString(1, ExamCategory);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				System.out.println("数据库中当前类型题目数："+rs.getInt(1));
				return rs.getInt(1);
			}
			else
				return 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	public int getTexttime(){
		try {
			Connection c =DataConnect.ConnectDb();
			String sql = "select InfoText from OtherInfo where InfoName = ?";
			PreparedStatement pstmt = c.prepareStatement(sql);
			pstmt.setString(1, "TestTime");
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				System.out.println("数据库中考试时间："+rs.getInt(1));
				return Integer.parseInt(rs.getString(1));
			}
			else
				return 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	public int CompareAnswer(ArrayList<Integer> answer,String Category){
		int score = 0;
		try {
			Connection c =DataConnect.ConnectDb();
			String sql = "select Answer from Subjects where Category = ?";
			PreparedStatement pstmt = c.prepareStatement(sql);
			pstmt.setString(1, Category);
			ResultSet rs = pstmt.executeQuery();
			Iterator<Integer> it = answer.iterator();
			while(rs.next()&&it.hasNext()){
				if(rs.getString(1).equals(getStringAnswer(it.next()))){
					score+=1;
				}
			}
			return score;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return score;
	}
	public static String getStringAnswer(int answer){
		switch(answer){
		case 1:return "A";
		case 2:return "B";
		case 3:return "C";
		case 4:return "D";
		default:return null;
		}
	}
}
