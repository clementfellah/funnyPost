package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entite.Post;

public class Connect {
	
	Connection conn;
	
	ArrayList<Post> listPostBDD = new ArrayList<>();
	
	public Connect() throws Exception {
		this.conn = getConnection();
	}
	
	
	public static Connection getConnection() throws Exception {
		try {
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://127.0.0.1:3306/funnypost";
			String username = "root";
			String password = "";
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, username, password);
			return conn;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	public ArrayList<Post> getPostBDD() throws SQLException {
		Statement stPBdd = this.conn.createStatement(); 
		ResultSet rs = stPBdd.executeQuery("SELECT * FROM post");
		while(rs.next()) {
			int id = rs.getInt("id");
			String title = rs.getString("title");
			String body = rs.getString("body");
			Post post = new Post(id, title, body);
			this.listPostBDD.add(post);
		}
		return this.listPostBDD;
	}
	
	public void insertAPIPostToBDD(Post post) throws SQLException {
		PreparedStatement insert = this.conn.prepareStatement("INSERT INTO post VALUES(?, ?, ?)");
		insert.setInt(1, post.getId());
		insert.setString(2, post.getTitle());
		insert.setString(3, post.getBody());
		insert.executeUpdate();
	}
	
}
