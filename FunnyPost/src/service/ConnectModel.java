package service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import entite.Post;

public class ConnectModel {
	
	Connect conn;
	WebService ws;
	
	ArrayList<Post> listPostAPI = new ArrayList<>();

	public ConnectModel() {
		try {
			conn = new Connect();
			ws = new WebService();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<Post> getPostBDD() throws SQLException {
		return conn.getPostBDD();
	}
	
	public void getPostAPI() throws IOException, SQLException {
		this.listPostAPI = this.ws.getPostAPI();
		for (Post post : listPostAPI) {
			conn.insertAPIPostToBDD(post);
		}	
	}
}
