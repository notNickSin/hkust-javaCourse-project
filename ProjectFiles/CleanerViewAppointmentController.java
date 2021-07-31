import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import java.util.Optional;
import javafx.scene.text.Text;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.collections.*;
import javafx.event.*;
import javafx.event.ActionEvent;
import java.io.*;


public class CleanerViewAppointmentController {

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
    private TextField staff_field;

    @FXML
    private TextField customerName_field;

    @FXML
    private TextField phoneNo_field;

    @FXML
    private TextField date_field;

    @FXML
    private TextField amount_field;    

    @FXML
    private Button back_Btn;
    
    @FXML
    private Button update_Btn;
    
    @FXML
    private MenuButton statusMenu;
    
    @FXML
    private MenuItem notCompleted;

    @FXML
    private MenuItem completed;

   
    @FXML
    void clicked_completed(ActionEvent event) {
      statusMenu.setText("Completed");
    }

    @FXML
    void clicked_notCompleted(ActionEvent event) {
      statusMenu.setText("Not Completed");
    }
    
    
    
   @FXML
    void backPressed(ActionEvent event) throws IOException{
      RunTheProgram.switchScene("CleanerMenu");
    }
    
    @FXML
    void clicked_Appointment_Table(MouseEvent event) { // when mouse click on the table
      if (event.getClickCount() > 0) {
         goToEdit();
      }
    }
    
     public void goToEdit(){
      if (appointment_table.getSelectionModel().getSelectedItem() != null){
        Appointment selectedJob = appointment_table.getSelectionModel().getSelectedItem();
        staff_field.setText(selectedJob.getStaffName());
        customerName_field.setText(selectedJob.getCustomerName());
        phoneNo_field.setText(selectedJob.getPhoneNo());
        date_field.setText(selectedJob.getDate());
        amount_field.setText(selectedJob.getAmount());
        statusMenu.setText(selectedJob.getStatus());
      }
    }
     
     public void resetEdit(){     
        staff_field.setText("");
        customerName_field.setText("");
        phoneNo_field.setText("");
        date_field.setText("");
        amount_field.setText("");
        statusMenu.setText("");
      }
    
     
     @FXML
    void updatePressed(ActionEvent event) throws IOException{
        Appointment appointmentSelected = appointment_table.getSelectionModel().getSelectedItem();
        AlertType type = AlertType.CONFIRMATION;
        Alert alert = new Alert(type,"");
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Please Confirm You Mark The Right Appointment");
        alert.setContentText("Are you sure?");
        Optional <ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
        //Edit the Status  
       for(int i = 0 ; i< RunTheProgram.appointmentArrayList.size();i++){  
            if (appointmentSelected.getId().equals(RunTheProgram.appointmentArrayList.get(i).getId())){            
              if(statusMenu.getText().equals("Completed")){
                RunTheProgram.appointmentArrayList.get(i).setStatus(statusMenu.getText());
                for(int j = 0 ; j< RunTheProgram.staffArrayList.size();j++){
                  if(appointmentSelected.getStaffID().equals(RunTheProgram.staffArrayList.get(j).getId()))
                    RunTheProgram.staffArrayList.get(j).plusWorkHour();
                }
                  
              }else if(statusMenu.getText().equals("Not Completed")){
                RunTheProgram.appointmentArrayList.get(i).setStatus(statusMenu.getText());
                for(int k = 0 ; k< RunTheProgram.staffArrayList.size();k++){
                  if(appointmentSelected.getStaffID().equals(RunTheProgram.staffArrayList.get(k).getId()))
                    RunTheProgram.staffArrayList.get(k).minusWorkHour(); 
                }
              }
            }else if(staff_field.getText().equals("")){
              AlertType type2 = AlertType.CONFIRMATION;
              Alert alert2 = new Alert(type2,"");
              alert2.setTitle("Confirmation Dialog");
              alert2.setHeaderText("Your Did Not Select Any Appointment");
              alert2.setContentText("Are you sure?");
              alert2.showAndWait();
            }
       }
       RunTheProgram.updateAppointmentCSV();              
       RunTheProgram.changeDisplayAddress();
       RunTheProgram.updateStaffCSV();     
       //Refresh the TableView
       RunTheProgram.addAppointmentToTable();
       RunTheProgram.loadWorkersAppointment();       
       resetEdit();
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
      
      appointment_table.setItems(RunTheProgram.workersAppointmentOBList);
     
    }
    
}
