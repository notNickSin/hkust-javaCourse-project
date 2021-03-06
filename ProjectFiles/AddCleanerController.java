import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.collections.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import java.io.IOException;
import java.util.Optional;

public class AddCleanerController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button back_Btn;

    @FXML
    private TextField cleanerName;

    @FXML
    private TextField cleanerPW;

    @FXML
    private TextField cleanerRate;


    @FXML
    private Button addCleaner_btn;

    @FXML
    void addCleaner(ActionEvent event) throws IOException {
      boolean accept = true;
      // no empty string
      if (cleanerName.getText().isEmpty() || cleanerPW.getText().isEmpty() || cleanerRate.getText().isEmpty()){
        accept = false;
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText("Ooops, there was an error!");
        alert.setContentText("There is/are empty field(s)");
        
        alert.showAndWait();
      }
      // rate must be numbers
      if (!cleanerRate.getText().isEmpty()){
        try{
          double d = Double.parseDouble(cleanerRate.getText());
        } catch (NumberFormatException nfe){
          accept = false;
          Alert alert = new Alert(AlertType.ERROR);
          alert.setTitle("Error Dialog");
          alert.setHeaderText("Ooops, there was an error!");
          alert.setContentText("Hourly Rate must be in numbers");
          
          alert.showAndWait();
        }
      }
      if (accept){
        String temp = "";
        int tempInt = 0;
        for (int i = 0; i<RunTheProgram.staffArrayList.size();i++){
        String num = "";
          for (int j = 1; j< RunTheProgram.staffArrayList.get(i).getId().length();j++){
            num += RunTheProgram.staffArrayList.get(i).getId().charAt(j);
            if (Integer.parseInt(num)>tempInt){
              tempInt = Integer.parseInt(num);
            }
          }
        }
        temp = String.valueOf(tempInt+1);
        String id = "C" + temp;
        Staff s = new Staff(id,cleanerName.getText(),cleanerPW.getText(),cleanerRate.getText(),"0","C",RunTheProgram.team); // 0 working hour and must be cleaner added
        s.calSalary();
        RunTheProgram.staffArrayList.add(s);
        RunTheProgram.staffOBList.add(s);
        RunTheProgram.allStaffOBList.add(s);
        RunTheProgram.staffObservableListSorted.add(s);
        RunTheProgram.updateStaffCSV();
        
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Successfully Added a Cleaner");
        alert.showAndWait();
        cleanerName.setText("");
        cleanerPW.setText("");
        cleanerRate.setText("");
      }
    }

    @FXML
    void backCleanerMenu(ActionEvent event) throws IOException{
      if(!cleanerName.getText().isEmpty()|| !cleanerPW.getText().isEmpty() || !cleanerRate.getText().isEmpty()){
        AlertType type = AlertType.CONFIRMATION;
        Alert alert = new Alert(type,"");
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("You have type something in the text field");
        alert.setContentText("Are you sure you want to leave?");
        Optional <ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
          RunTheProgram.switchScene("ManageCleanerMenu");
        }  
      }else{
        RunTheProgram.switchScene("ManageCleanerMenu");
      }
    }

    @FXML
    void initialize() {

    }
}
