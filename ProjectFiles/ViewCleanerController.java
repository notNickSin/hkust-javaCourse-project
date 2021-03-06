import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.cell.*;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.collections.*;
import javafx.scene.input.MouseEvent;
import javafx.event.*;
import java.io.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import java.util.Optional;

public class ViewCleanerController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private Label title_lbl;

    @FXML
    private TableView<Staff> cleanerTable;

    @FXML
    private TableColumn<Staff, String> id_Col;

    @FXML
    private TableColumn<Staff, String> name_Col;

    @FXML
    private TableColumn<Staff, String> pw_Col;

    @FXML
    private TableColumn<Staff, String> rate_Col;

    @FXML
    private TableColumn<Staff, String> wH_Col;

    @FXML
    private TableColumn<Staff, Double> salary_Col;

    @FXML
    private TextField id_field;

    @FXML
    private TextField name_field;

    @FXML
    private TextField pw_field;

    @FXML
    private TextField rate_field;

    @FXML
    private TextField wH_field;

    @FXML
    private Button edit_Btn;

    @FXML
    private Button delete_Btn;

    @FXML
    private Button back_Btn;

    @FXML
    void backCleanerMenu(ActionEvent event) throws IOException{
      boolean accept = true;
      // is not edit if the records are the same
      for (int i = 0; i < RunTheProgram.staffArrayList.size();i++){
        if (id_field.getText().equals(RunTheProgram.staffArrayList.get(i).getId())){ // same id first
          if (!name_field.getText().equals(RunTheProgram.staffArrayList.get(i).getName()) || !pw_field.getText().equals(RunTheProgram.staffArrayList.get(i).getPassword()) || !rate_field.getText().equals(RunTheProgram.staffArrayList.get(i).getRate())){
            AlertType type = AlertType.CONFIRMATION;
            Alert alert = new Alert(type,"");
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("You have make some changes in the staff's data");
            alert.setContentText("Are you sure you want to leave?");
            Optional <ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.CANCEL){
              accept = false;
            }
          }
        }
      }
      
      if (accept){
        RunTheProgram.switchScene("ManageCleanerMenu");
      }
    }
    
    @FXML
    void clicked_CleanerTable(MouseEvent event) { // when mouse click on the table
      if (event.getClickCount() > 0) {
        goToEdit();
      }
    }
    
    @FXML
    void deleteCleaner(ActionEvent event) throws IOException{
      // check if cells are empty
      if (!id_field.getText().isEmpty() || !name_field.getText().isEmpty() || !pw_field.getText().isEmpty() || !rate_field.getText().isEmpty() || !wH_field.getText().isEmpty()){
        AlertType type = AlertType.CONFIRMATION;
        Alert alert = new Alert(type,"");
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("You are about to Delete an Record in Your Team");
        alert.setContentText("Are you sure?");
        Optional <ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
          for(Staff s : RunTheProgram.staffOBList){
            //select the unique id, which is not editable
            if (id_field.getText().equals(s.getId())){
              RunTheProgram.staffArrayList.remove(s);
              RunTheProgram.updateStaffCSV();
            }
          }
          cleanerTable.getItems().removeAll(cleanerTable.getSelectionModel().getSelectedItem());
          id_field.setText("");
          name_field.setText("");
          pw_field.setText("");
          rate_field.setText("");
          wH_field.setText("");
        } 
      }
    }

    @FXML
    void editCleaner(ActionEvent event) throws IOException{
      boolean accept = true;
      // if all fields are empty, cannot change
      if (id_field.getText().isEmpty() || name_field.getText().isEmpty() || pw_field.getText().isEmpty() || rate_field.getText().isEmpty() || wH_field.getText().isEmpty()){
        accept = false;
      }
      
      // is not edit when fields are equal to the data
      for (int i = 0; i < RunTheProgram.staffArrayList.size();i++){
        if (id_field.getText().equals(RunTheProgram.staffArrayList.get(i).getId())){ // same id first
          if (name_field.getText().equals(RunTheProgram.staffArrayList.get(i).getName()) && pw_field.getText().equals(RunTheProgram.staffArrayList.get(i).getPassword()) && rate_field.getText().equals(RunTheProgram.staffArrayList.get(i).getRate())){
            accept = false;
          }
        }
      }
      
      
      // data validation
      if (!rate_field.getText().isEmpty()){
        try{
          double d = Double.parseDouble(rate_field.getText());
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
        AlertType type = AlertType.CONFIRMATION;
        Alert alert = new Alert(type,"");
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("You are about to Edit an Record in Your Team");
        alert.setContentText("Are you sure?");
        Optional <ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
          for (int i = 0; i < RunTheProgram.staffArrayList.size();i++){
            //select the unique id
            if (id_field.getText().equals(RunTheProgram.staffArrayList.get(i).getId())){
              RunTheProgram.staffArrayList.get(i).setName(name_field.getText());
              RunTheProgram.staffArrayList.get(i).setPassword(pw_field.getText());
              RunTheProgram.staffArrayList.get(i).setRate(rate_field.getText());
              RunTheProgram.staffArrayList.get(i).calSalary();
              for(Appointment a: RunTheProgram.appointmentArrayList){
                if(id_field.getText().equals(a.getStaffID())){
                  a.setStaffName(name_field.getText());
                }
              }
            }
            RunTheProgram.changeDisplayTimeS();
            RunTheProgram.changeDisplayTimeE();
            RunTheProgram.changeDisplayAddress();
            RunTheProgram.updateAppointmentCSV();
            RunTheProgram.addAppointmentToTable();
            RunTheProgram.addAmountToStaff();
            RunTheProgram.addCleanerToTable();
            RunTheProgram.addToTopThree();
            RunTheProgram.updateStaffCSV();
            RunTheProgram.addCleanerToTable();
          }
        }
      }
    }

    @FXML
    void initialize() throws FileNotFoundException{
      //title
      title_lbl.setText("Team " + RunTheProgram.team + " Cleaner Table");
      //table
      id_Col.setCellValueFactory(new PropertyValueFactory<Staff,String>("id"));
      name_Col.setCellValueFactory(new PropertyValueFactory<Staff,String>("name"));
      pw_Col.setCellValueFactory(new PropertyValueFactory<Staff,String>("password"));
      rate_Col.setCellValueFactory(new PropertyValueFactory<Staff,String>("rate"));
      wH_Col.setCellValueFactory(new PropertyValueFactory<Staff,String>("workHour"));
      salary_Col.setCellValueFactory(new PropertyValueFactory<Staff,Double>("salary"));
      cleanerTable.setItems(RunTheProgram.staffOBList);
      
    }
    
    public void goToEdit(){
      if (cleanerTable.getSelectionModel().getSelectedItem() != null){
        Staff selectedStaff = cleanerTable.getSelectionModel().getSelectedItem();
        id_field.setText(selectedStaff.getId());
        name_field.setText(selectedStaff.getName());
        pw_field.setText(selectedStaff.getPassword());
        rate_field.setText(selectedStaff.getRate());
        wH_field.setText(selectedStaff.getWorkHour());
      }
    }
}

