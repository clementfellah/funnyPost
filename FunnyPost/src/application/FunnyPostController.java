package application;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import service.ConnectModel;
import service.WebService;
import entite.Post;

public class FunnyPostController implements Initializable{
	
	ConnectModel connModel;
	WebService ws = new WebService();	
	
	@FXML
	ListView<String> listViewPost = new ListView<>();
	
    @FXML
    private Button btnInit;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		try {
			connModel = new ConnectModel();
			initializePost();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void initializePost() throws SQLException, IOException {
		ArrayList<Post> listPostBDD = new ArrayList<>();
		listPostBDD = this.connModel.getPostBDD();
		try {
            this.listViewPost.getItems().clear();
            for (int i = 0; i < listPostBDD.size(); i++) {
                if (listPostBDD.get(i).getTitle() != null) {
                    this.listViewPost.getItems().add(listPostBDD.get(i).getTitle());
                }
            }
		} catch(Exception e) {
			
		}
    }
	
	@FXML
    void onClickInitialize(ActionEvent event) throws IOException, SQLException {
		connModel.getPostAPI();
		initializePost();
    }
}
