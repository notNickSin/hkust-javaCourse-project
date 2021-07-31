import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.io.IOException;

public class ManageCleanerMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addCleaner_Btn;

    @FXML
    private Button viewCleaner_Btn;

    @FXML
    private Button back_Btn;

    @FXML
    void backMainMenu(ActionEvent event) throws IOException {
      RunTheProgram.switchScene("OptionMenuScene");
    }

    @FXML
    void toAddCleaner(ActionEvent event) throws IOException{
      RunTheProgram.switchScene("AddCleaner");
    }

    @FXML
    void toViewCleaner(ActionEvent event) throws IOException {
      RunTheProgram.switchScene("ViewCleaner");
    }

    @FXML
    void initialize() {


    }
}
