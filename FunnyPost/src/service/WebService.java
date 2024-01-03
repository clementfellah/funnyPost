package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import entite.Post;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class WebService {
	
	Post post;
	
	public ArrayList<Post> getPostAPI() throws IOException {
		URL url = new URL("https://jsonplaceholder.typicode.com/posts");
		HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
		con.setConnectTimeout(20000);
		con.setReadTimeout(20000);
		con.setUseCaches(true);
		con.setRequestMethod("GET");
		
		con.setRequestProperty("Accept", "application/xml");
		con.setRequestProperty("Content-Type", "application/xml");
		
		int responseCode = con.getResponseCode();
		if (responseCode == 400) {
			System.out.println("Client error !!");
		} else if (responseCode == 500) {
			System.out.println("Server error !!");
			
		} 
		else if (responseCode == 200) {
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuilder responseBody = new StringBuilder();
			while((inputLine = in.readLine()) != null) {
				responseBody.append(inputLine).append("\n");
			}
			in.close();
			return this.convertObjectToList(responseBody);
		}
		return null;
	}
	
	public ArrayList<Post> convertObjectToList(StringBuilder responseBody) {
	    ArrayList<Post> listPostAPI = new ArrayList<>();
	    JSONParser parser  = new JSONParser();
	    try {
	        JSONArray jsonArray = (JSONArray) parser.parse(responseBody.toString());

	        for (Object obj : jsonArray) {
	            JSONObject jsonObject = (JSONObject) obj;

	            Integer id = Integer.parseInt(jsonObject.get("id").toString());
	            String title = (String) jsonObject.get("title").toString();
	            String body = (String) jsonObject.get("body").toString();
	            this.post = new Post(id, title, body);
	            listPostAPI.add(this.post);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return listPostAPI;
	}
}
