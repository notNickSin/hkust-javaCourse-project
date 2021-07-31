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

public class ViewAppointmentController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    

    @FXML
    private TableView<Appointment> appointment_table;
    
    @FXML
    private TableColumn<Appointment, String> id_Col;

    @FXML
    private TableColumn<Appointment, String> staffID_Col;

    @FXML
    private TableColumn<Appointment, String> staffName_Col;

    @FXML
    private TableColumn<Appointment, String> customer_Col;

    @FXML
    private TableColumn<Appointment, String> phoneNum_Col;

    @FXML
    private TableColumn<Appointment, String> address_Col;

    @FXML
    private TableColumn<Appointment, String> date_Col;

    @FXML
    private TableColumn<Appointment, String> timeStart_Col;

    @FXML
    private TableColumn<Appointment, String> timeEnd_Col;

    @FXML
    private TableColumn<Appointment, String> duration_Col;

    @FXML
    private TableColumn<Appointment, String> amount_Col;

    @FXML
    private TableColumn<Appointment, String> status_Col;

    @FXML
    private Button delete_Btn;

    @FXML
    private Button edit_Btn;

    @FXML
    private Button back_Btn;
    
    

    @FXML
    void backManageAppointment(ActionEvent event) throws IOException{
      RunTheProgram.switchScene("ManageAppointmentMenu");
    }

    @FXML
    void deleteAppointment(ActionEvent event) throws IOException{
      //get selection
      ObservableList<Appointment> allAppointments;
      allAppointments  = appointment_table.getItems();
      Appointment appointmentSelected = appointment_table.getSelectionModel().getSelectedItem();
      AlertType type = AlertType.CONFIRMATION;
      Alert alert = new Alert(type,"");
      alert.setTitle("Confirmation Dialog");
      alert.setHeaderText("You are about to Delete an Appointment");
      alert.setContentText("Are you sure?");
      Optional <ButtonType> result = alert.showAndWait();
      if (result.get() == ButtonType.OK){
        //remove from csv
        for (Appointment a : RunTheProgram.appointmentOBList){
          if (appointmentSelected.getId().equals(a.getId())){
            RunTheProgram.appointmentArrayList.remove(a);
            RunTheProgram.updateAppointmentCSV();
            RunTheProgram.changeDisplayAddress();
            RunTheProgram.changeDisplayTimeS();
            RunTheProgram.changeDisplayTimeE();
            RunTheProgram.addAppointmentToTable();
            RunTheProgram.addAmountToStaff();
            RunTheProgram.addCleanerToTable();
            RunTheProgram.addToTopThree();
          }
        }
        //remove from table
        appointment_table.getItems().removeAll(appointmentSelected);
      }
      
    }

    public static Appointment selectedAppointment;
    @FXML
    void goEditAppointment(ActionEvent event) throws IOException{
      if (appointment_table.getSelectionModel().getSelectedItem() != null){
        selectedAppointment = appointment_table.getSelectionModel().getSelectedItem();
        if (selectedAppointment.getStatus().equals("Completed")){
          Alert alert = new Alert(AlertType.ERROR);
          alert.setTitle("Error Dialog");
          alert.setHeaderText("Ooops, there was an error!");
          alert.setContentText("Completed Appointments cannot be edited");
          
          alert.showAndWait();
        }else{
          RunTheProgram.switchScene("EditAppointment");
        }
      }
    }
    

    @FXML
    void clicked_Appointment_Table(MouseEvent event) { // when mouse click on the table
      if (event.getClickCount() > 0) {

      }
    }
    
    @FXML
    void initialize() {
      id_Col.setCellValueFactory(new PropertyValueFactory<Appointment,String>("id"));
      staffID_Col.setCellValueFactory(new PropertyValueFactory<Appointment,String>("staffID"));
      staffName_Col.setCellValueFactory(new PropertyValueFactory<Appointment,String>("staffName"));
      customer_Col.setCellValueFactory(new PropertyValueFactory<Appointment,String>("customerName"));
      phoneNum_Col.setCellValueFactory(new PropertyValueFactory<Appointment,String>("phoneNo"));
      address_Col.setCellValueFactory(new PropertyValueFactory<Appointment,String>("address"));
      date_Col.setCellValueFactory(new PropertyValueFactory<Appointment,String>("date"));
      timeStart_Col.setCellValueFactory(new PropertyValueFactory<Appointment,String>("displayTimeS"));
      timeEnd_Col.setCellValueFactory(new PropertyValueFactory<Appointment,String>("displayTimeE"));
      duration_Col.setCellValueFactory(new PropertyValueFactory<Appointment,String>("duration"));
      amount_Col.setCellValueFactory(new PropertyValueFactory<Appointment,String>("amount"));
      status_Col.setCellValueFactory(new PropertyValueFactory<Appointment,String>("status"));
      appointment_table.setItems(RunTheProgram.appointmentOBList);
    }
    
}
