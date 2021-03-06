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

public class OptionMenuController {
    @FXML
  private Text managerName;
    
    @FXML
    private Button btnMC;

    @FXML
    private Button btnMA;

    @FXML
    private Button btnMSR;

    @FXML
    private Button btnT3C;

    @FXML
    void presslogout(ActionEvent event) throws IOException{
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
    void toManageAppointment(ActionEvent event) throws IOException{
      RunTheProgram.switchScene("ManageAppointmentMenu");
    }

    @FXML
    void toManageCleaner(ActionEvent event) throws IOException{
      RunTheProgram.switchScene("ManageCleanerMenu");
    }

    @FXML
    void toMonthlyReport(ActionEvent event) throws IOException{
      RunTheProgram.switchScene("GenerateSalaryReport");
    }

    @FXML
    void toTopThree(ActionEvent event) throws IOException{
      RunTheProgram.switchScene("TopThreeGenerator");
    }

    @FXML
    void initialize() {
      managerName.setText(RunTheProgram.userName);
    }
}
