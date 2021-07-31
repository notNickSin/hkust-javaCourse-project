import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.*;
import java.io.IOException;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import java.io.IOException;
import java.util.Optional;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;


public class LogInController {
    @FXML
    private Button logIn;

    @FXML
    private TextField textfield;

    @FXML
    private PasswordField passwordfield;

    @FXML
    private Text pressed;
    
  
    @FXML
    void pressed_enter(KeyEvent e) throws IOException{
        if (e.getCode() == KeyCode.ENTER) { if(Staff.login(textfield.getText(),passwordfield.getText())){
          if (RunTheProgram.role.equals("M")){
            RunTheProgram.userID = textfield.getText();
            RunTheProgram.addCleanerToTable();
            RunTheProgram.changeDisplayTimeS();
            RunTheProgram.changeDisplayTimeE();
            RunTheProgram.changeDisplayAddress();
            RunTheProgram.addAppointmentToTable();
            RunTheProgram.calMonthlyHour();
            RunTheProgram.addAmountToStaff();
            RunTheProgram.addToTopThree();
            RunTheProgram.switchScene("OptionMenuScene");
          }else if (RunTheProgram.role.equals("C")){
            RunTheProgram.userID = textfield.getText();
            RunTheProgram.loadWorkersAppointment();
            RunTheProgram.addCleanerToTable();
            RunTheProgram.changeDisplayTimeS();
            RunTheProgram.changeDisplayTimeE();
            RunTheProgram.changeDisplayAddress();
            RunTheProgram.addAppointmentToTable();
            RunTheProgram.calMonthlyHour();
            RunTheProgram.addAmountToStaff();
            RunTheProgram.addToTopThree();
            RunTheProgram.switchScene("CleanerMenu");
          }
      }else{
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText("Ooops, there was an error!");
        alert.setContentText("Log-In failed, Incorrect ID or Password");
        
        alert.showAndWait();
      }
      
      }
      
    }

    @FXML
    void pressbutton(ActionEvent event) throws IOException{
      if(Staff.login(textfield.getText(),passwordfield.getText())){
        if (RunTheProgram.role.equals("M")){
          RunTheProgram.userID = textfield.getText();
          RunTheProgram.switchScene("OptionMenuScene");
          RunTheProgram.calMonthlyHour();
          RunTheProgram.addCleanerToTable();
          RunTheProgram.changeDisplayTimeS();
          RunTheProgram.changeDisplayTimeE();
          RunTheProgram.changeDisplayAddress();
          RunTheProgram.addAppointmentToTable();
          RunTheProgram.addAmountToStaff();
          RunTheProgram.addToTopThree();
        }else if (RunTheProgram.role.equals("C")){
          RunTheProgram.userID = textfield.getText();
          RunTheProgram.switchScene("CleanerMenu");
          RunTheProgram.loadWorkersAppointment();
          RunTheProgram.calMonthlyHour();
          RunTheProgram.addCleanerToTable();
          RunTheProgram.changeDisplayTimeS();
          RunTheProgram.changeDisplayTimeE();
          RunTheProgram.changeDisplayAddress();
          RunTheProgram.addAppointmentToTable();
          RunTheProgram.addAmountToStaff();
          RunTheProgram.addToTopThree();
        }
      }else{
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText("Ooops, there was an error!");
        alert.setContentText("Log-In failed, Incorrect ID or Password");
        
        alert.showAndWait();
      }
    }

    @FXML
    void initialize() {
    }
}
