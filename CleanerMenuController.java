import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.paint.*;
import javafx.event.ActionEvent;
import java.io.IOException;
import javafx.scene.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import java.util.Optional;
public class CleanerMenuController  {
   @FXML
    private Text staffName; 
   
    @FXML
    private Button viewApp;

    @FXML
    private Button viewTop3;

    @FXML
    private Button logout;

    @FXML
    void pressLogout(ActionEvent event) throws IOException {
      AlertType type = AlertType.CONFIRMATION;
        Alert alert = new Alert(type,"");
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("You are about to Log Out");
        alert.setContentText("Are you sure?");
        Optional <ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
          RunTheProgram.switchScene("LoginForm");
        }

    }

    @FXML
    void pressViewTop3(ActionEvent event) throws IOException{
      RunTheProgram.switchScene("CleanerTopThreeGenerator");
    }

    @FXML
    void pressViewApp(ActionEvent event) throws IOException{
      
      RunTheProgram.switchScene("CleanerViewAppointment");
    }
    
    @FXML
    void initialize() { 
      staffName.setText(RunTheProgram.userName);
    }
}
    
