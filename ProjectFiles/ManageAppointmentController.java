import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.io.IOException;

public class ManageAppointmentController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button toAddAppointment_Btn;

    @FXML
    private Button toViewAppointment_Btn;

    @FXML
    private Button back_Btn;

    @FXML
    void backMainMenu(ActionEvent event) throws IOException{
      RunTheProgram.switchScene("OptionMenuScene");
    }

    @FXML
    void toAddAppoint(ActionEvent event) throws IOException{
      RunTheProgram.switchScene("AddAppointment");
    }

    @FXML
    void toViewAppoint(ActionEvent event) throws IOException{
      RunTheProgram.switchScene("ViewAppointment");
    }

    @FXML
    void initialize() {

    }
}
