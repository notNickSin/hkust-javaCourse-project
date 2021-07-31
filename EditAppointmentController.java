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
import javafx.scene.control.DatePicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import java.util.Optional;
import javafx.util.StringConverter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.DateFormat;  
import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.util.Calendar;


public class EditAppointmentController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField customerName_field;

    @FXML
    private TextField phoneNumber_field;

    @FXML
    private Button back_btn;

    @FXML
    private DatePicker date_DatePicker;

    @FXML
    private TextField amountPaid_field;

    @FXML
    private Button editAppointment_btn;

    @FXML
    private ComboBox<String> hour_CBox;
    ObservableList<String> hourLs = FXCollections.observableArrayList();
    @FXML
    private ComboBox<String> min_CBox;
    ObservableList<String> minLs = FXCollections.observableArrayList();
    @FXML
    private ComboBox<String> staff_CBox;
    ObservableList<String> staffName = FXCollections.observableArrayList();
    @FXML
    private TextField staffID_field;

    @FXML
    private TextArea address_box;

    @FXML
    private TextField duration_field;

    @FXML
    void backViewAppointment(ActionEvent event) throws IOException {
      RunTheProgram.switchScene("ViewAppointment");
    }

    @FXML
    void editAppointment(ActionEvent event) throws IOException{
      boolean accept = true;
      boolean empty = false;
      //empty value
      if(!staffName.contains(staff_CBox.getValue())|| customerName_field.getText().isEmpty() || address_box.getText().isEmpty() || duration_field.getText().isEmpty() || amountPaid_field.getText().isEmpty() || phoneNumber_field.getText().isEmpty()|| hour_CBox.getSelectionModel().isEmpty() || min_CBox.getSelectionModel().isEmpty() || date_DatePicker.getValue() == null){
        accept = false;
        empty = true;
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText("Ooops, there was an error!");
        alert.setContentText("Cannot Edit As an Empty Field");
        alert.showAndWait();
        return;
      }
      // Same value, no action
      for (Appointment a: RunTheProgram.appointmentArrayList){
        if (a.getId().equals(ViewAppointmentController.selectedAppointment.getId())){
           String displayHour;
           String displayMin;
           LocalDate displayDate;
           DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("dd/MM/yyyy");
           displayDate = LocalDate.parse(a.getDate(),formatter);
           
           
           displayHour = String.valueOf(Integer.parseInt(a.getTimeStart())/60);
           displayMin = String.valueOf(Integer.parseInt(a.getTimeStart())%60);
           for (int i = 0; i < displayHour.length();i++){
             if(displayHour.length() == 1){
               displayHour = "0"+displayHour; 
             }
           }
           for (int i = 0; i < displayMin.length();i++){
             if(displayMin.length() == 1){
               displayMin = "0"+displayMin; 
             }
           }
          if(staffID_field.getText().equals(a.getStaffID())&& customerName_field.getText().equals(a.getCustomerName()) && address_box.getText().equals(a.getAddress()) && duration_field.getText().equals(a.getDuration()) && amountPaid_field.getText().equals(a.getEditAmount()) && phoneNumber_field.getText().equals(a.getPhoneNo())&& hour_CBox.getValue().equals(displayHour) && min_CBox.getValue().equals(displayMin) && date_DatePicker.getValue().equals(displayDate)){
            accept = false;
            return;
          }
        }
      }
      //validation
      /*if(!hourLs.contains(hour_CBox.getValue())){ //add back if want to change time by user (Since time start at 09(String), need to think how to deal without error)
        accept = false;
      }
      if(!minLs.contains(min_CBox.getValue())){
        accept = false;
      }*/
      
      // If Numbers?
      
      if (!amountPaid_field.getText().isEmpty()){
        try{
          double d = Double.parseDouble(amountPaid_field.getText());
        } catch (NumberFormatException nfe){
          accept = false;
          Alert alert = new Alert(AlertType.ERROR);
          alert.setTitle("Error Dialog");
          alert.setHeaderText("Ooops, there was an error!");
          alert.setContentText("Amount Paid must be in numbers");
          
          alert.showAndWait();
        }
      }
      
      if (!duration_field.getText().isEmpty()){
        try{
          double d = Double.parseDouble(duration_field.getText());
        } catch (NumberFormatException nfe){
          accept = false;
          Alert alert = new Alert(AlertType.ERROR);
          alert.setTitle("Error Dialog");
          alert.setHeaderText("Ooops, there was an error!");
          alert.setContentText("Duration must be in numbers");
          
          alert.showAndWait();
        }
      }
      
      boolean passNumTest = false;
      if (!phoneNumber_field.getText().isEmpty()){
        try{
          int i = Integer.parseInt(phoneNumber_field.getText());
          passNumTest = true;
        } catch (NumberFormatException nfe){
          accept = false;
          Alert alert = new Alert(AlertType.ERROR);
          alert.setTitle("Error Dialog");
          alert.setHeaderText("Ooops, there was an error!");
          alert.setContentText("Phone Number must be in numbers");
          alert.showAndWait();
        }
      }
      // If Phonenumber = 8 digits??
      if(passNumTest){
        int count = 0;
        for (int i = 0; i<phoneNumber_field.getText().length();i++){
          count++;
        }
        if(count!=8){
          accept = false;
          Alert alert = new Alert(AlertType.ERROR);
          alert.setTitle("Error Dialog");
          alert.setHeaderText("Ooops, there was an error!");
          alert.setContentText("Phone Number must be a 8-digit Number");
          alert.showAndWait();
        }
      }
      // Within Rest time??
      // Find the int for time, is string and have some leading 0

        int appointmentTime;
        int bannedTime;
        int appointmentEnd;
        String duration;
        String tempHour;
        String tempMin;
        int hour;
        int min;
        LocalDate appointmentDate = date_DatePicker.getValue();
        String formattedDate = appointmentDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Appointment aClash;
        String startHourClash;
        String startMinClash;
        String endHourClash;
        String endMinClash;
        boolean clash = false;
        

        tempHour = hour_CBox.getValue();
        hour = Integer.parseInt(tempHour);
        tempMin = min_CBox.getValue();
        min = Integer.parseInt(tempMin);
        appointmentTime = (hour*60)+min;
        bannedTime = appointmentTime-30;
        duration = duration_field.getText();
        appointmentEnd = appointmentTime + (int) Math.round(Double.parseDouble(duration)*60); // Since duration can hv decimals, change to double can calculate 
        
        if(!empty){
          //Check for time duration over 7 pm
          if (appointmentEnd > 19*60){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Ooops, there was an error!");
            alert.setContentText("The Appoitment End Time Cannot Be Over 19:00");
            alert.showAndWait();
            clash = true;
            accept = false;
          }
        for (Appointment a: RunTheProgram.appointmentArrayList){
          // Equal Id, Equal Date
          if(staffID_field.getText().equals(a.getStaffID()) && a.getDate().equals(formattedDate) && !a.getId().equals(ViewAppointmentController.selectedAppointment.getId())){

            
            //Check for Time Clashes
            //Case: New Appointment is between an appointment
            if (appointmentTime >= Integer.parseInt(a.getTimeStart()) && appointmentTime <= Integer.parseInt(a.getTimeEnd()) && appointmentEnd >= Integer.parseInt(a.getTimeStart()) && appointmentEnd <= Integer.parseInt(a.getTimeEnd())){
              
              aClash = a;
              startHourClash = String.valueOf(Integer.parseInt(a.getTimeStart())/60);
              startMinClash = String.valueOf(Integer.parseInt(a.getTimeStart())%60);
              endHourClash = String.valueOf(Integer.parseInt(a.getTimeEnd())/60);
              endMinClash = String.valueOf(Integer.parseInt(a.getTimeEnd())%60);
              
              for (int i = 0; i < startHourClash.length();i++){
                if(startHourClash.length() == 1){
                  startHourClash = "0"+startHourClash; 
                }
              }
              for (int i = 0; i < startMinClash.length();i++){
                if(startMinClash.length() == 1){
                  startMinClash = "0"+startMinClash; 
                }
              }
              
              for (int i = 0; i < endHourClash.length();i++){
                if(endHourClash.length() == 1){
                  endHourClash = "0"+endHourClash; 
                }
              }
              for (int i = 0; i < endMinClash.length();i++){
                if(endMinClash.length() == 1){
                  endMinClash = "0"+endMinClash; 
                }
              }
              
              Alert alert = new Alert(AlertType.ERROR);
              alert.setTitle("Error Dialog");
              alert.setHeaderText("Ooops, there was an error!");
              alert.setContentText("It Clashes with Appointment " + a.getId() +", The New Appointment Time Cannot be in Between " + startHourClash +":"+ startMinClash + " and " + endHourClash +":"+ endMinClash);
              alert.showAndWait();
              //System.out.println("It Clashes on Appointment " + a.getId() +", The New Appointment Time Cannot be in Between " + startHourClash +":"+ startMinClash + " and " + endHourClash +":"+ endMinClash);
              clash = true;
              accept = false;
            } else if(appointmentTime >= Integer.parseInt(a.getTimeStart()) && appointmentTime <= Integer.parseInt(a.getTimeEnd())){//Case:New Start Time in Between An appointment
              
              aClash = a;
              startHourClash = String.valueOf(Integer.parseInt(a.getTimeStart())/60);
              startMinClash = String.valueOf(Integer.parseInt(a.getTimeStart())%60);
              endHourClash = String.valueOf(Integer.parseInt(a.getTimeEnd())/60);
              endMinClash = String.valueOf(Integer.parseInt(a.getTimeEnd())%60);
              
              for (int i = 0; i < startHourClash.length();i++){
                if(startHourClash.length() == 1){
                  startHourClash = "0"+startHourClash; 
                }
              }
              for (int i = 0; i < startMinClash.length();i++){
                if(startMinClash.length() == 1){
                  startMinClash = "0"+startMinClash; 
                }
              }
              
              for (int i = 0; i < endHourClash.length();i++){
                if(endHourClash.length() == 1){
                  endHourClash = "0"+endHourClash; 
                }
              }
              for (int i = 0; i < endMinClash.length();i++){
                if(endMinClash.length() == 1){
                  endMinClash = "0"+endMinClash; 
                }
              }
              Alert alert = new Alert(AlertType.ERROR);
              alert.setTitle("Error Dialog");
              alert.setHeaderText("Ooops, there was an error!");
              alert.setContentText("It Clashes with Appointment " + a.getId() +", The New Appointment Time Cannot Start In Between " + startHourClash +":"+ startMinClash + " and " + endHourClash +":"+ endMinClash);
              alert.showAndWait();
              //System.out.println("It Clashes on Appointment " + a.getId() +", The New Appointment Time Cannot Start In Between " + startHourClash +":"+ startMinClash + " and " + endHourClash +":"+ endMinClash);
              clash = true;
              accept = false;
            } else if(appointmentEnd >= Integer.parseInt(a.getTimeStart()) && appointmentEnd <= Integer.parseInt(a.getTimeEnd())){  //Case:New End Time in between an appointment
              
              aClash = a;
              startHourClash = String.valueOf(Integer.parseInt(a.getTimeStart())/60);
              startMinClash = String.valueOf(Integer.parseInt(a.getTimeStart())%60);
              endHourClash = String.valueOf(Integer.parseInt(a.getTimeEnd())/60);
              endMinClash = String.valueOf(Integer.parseInt(a.getTimeEnd())%60);
              
              for (int i = 0; i < startHourClash.length();i++){
                if(startHourClash.length() == 1){
                  startHourClash = "0"+startHourClash; 
                }
              }
              for (int i = 0; i < startMinClash.length();i++){
                if(startMinClash.length() == 1){
                  startMinClash = "0"+startMinClash; 
                }
              }
              
              for (int i = 0; i < endHourClash.length();i++){
                if(endHourClash.length() == 1){
                  endHourClash = "0"+endHourClash; 
                }
              }
              for (int i = 0; i < endMinClash.length();i++){
                if(endMinClash.length() == 1){
                  endMinClash = "0"+endMinClash; 
                }
              }
              Alert alert = new Alert(AlertType.ERROR);
              alert.setTitle("Error Dialog");
              alert.setHeaderText("Ooops, there was an error!");
              alert.setContentText("It Clashes with Appointment " + a.getId() +", The New Appointment Time Cannot End in Between " + startHourClash +":"+ startMinClash + " and " + endHourClash +":"+ endMinClash);
              alert.showAndWait();
              //System.out.println("It Clashes on Appointment " + a.getId() +", The New Appointment Time Cannot End in Between " + startHourClash +":"+ startMinClash + " and " + endHourClash +":"+ endMinClash);
              clash = true;
              accept = false;
            }     
            //Check Within Rest Time / banned time
            if (!clash){
            if(Integer.parseInt(a.getTimeEnd())>= bannedTime && Integer.parseInt(a.getTimeEnd()) <= appointmentTime){       
              aClash = a;
              startHourClash = String.valueOf(Integer.parseInt(a.getTimeStart())/60);
              startMinClash = String.valueOf(Integer.parseInt(a.getTimeStart())%60);
              endHourClash = String.valueOf(Integer.parseInt(a.getTimeEnd())/60);
              endMinClash = String.valueOf(Integer.parseInt(a.getTimeEnd())%60);
              
              for (int i = 0; i < startHourClash.length();i++){
                if(startHourClash.length() == 1){
                  startHourClash = "0"+startHourClash; 
                }
              }
              for (int i = 0; i < startMinClash.length();i++){
                if(startMinClash.length() == 1){
                  startMinClash = "0"+startMinClash; 
                }
              }
              
              for (int i = 0; i < endHourClash.length();i++){
                if(endHourClash.length() == 1){
                  endHourClash = "0"+endHourClash; 
                }
              }
              for (int i = 0; i < endMinClash.length();i++){
                if(endMinClash.length() == 1){
                  endMinClash = "0"+endMinClash; 
                }
              }
              accept = false;
              Alert alert = new Alert(AlertType.ERROR);
              alert.setTitle("Error Dialog");
              alert.setHeaderText("Ooops, there was an error!");
              alert.setContentText("The Last appointment ends at " + endHourClash +":" + endMinClash + ", the cleaner still has " + (Integer.parseInt(a.getTimeEnd()) - bannedTime) + " mins rest time left");
              alert.showAndWait();
              //System.out.println("The New Appointment Cannot start in the rest time of cleaner, the last appointment ends at " + endHourClash +":" + endMinClash + ", the cleaner still has " + (Integer.parseInt(a.getTimeEnd()) - bannedTime) + " mins left");
            }
            }
          }
        }
      }
        if(accept){
          AlertType type = AlertType.CONFIRMATION;
          Alert alert = new Alert(type,"");
          alert.setTitle("Confirmation Dialog");
          alert.setHeaderText("You are about to Edit an Record in Your Team");
          alert.setContentText("Are you sure?");
          Optional <ButtonType> result = alert.showAndWait();
          if (result.get() == ButtonType.OK){
            for (int i = 0; i< RunTheProgram.appointmentArrayList.size();i++){
              if(RunTheProgram.appointmentArrayList.get(i).getId().equals(ViewAppointmentController.selectedAppointment.getId())){
                RunTheProgram.appointmentArrayList.get(i).setStaffID(staffID_field.getText());
                RunTheProgram.appointmentArrayList.get(i).setStaffName(staff_CBox.getValue());
                RunTheProgram.appointmentArrayList.get(i).setCustomerName(customerName_field.getText());
                RunTheProgram.appointmentArrayList.get(i).setPhoneNo(phoneNumber_field.getText());
                RunTheProgram.appointmentArrayList.get(i).setAddress(address_box.getText());
                RunTheProgram.appointmentArrayList.get(i).setDate(formattedDate);
                RunTheProgram.appointmentArrayList.get(i).setTimeStart(String.valueOf(appointmentTime));
                RunTheProgram.appointmentArrayList.get(i).setTimeEnd(String.valueOf(appointmentEnd));
                RunTheProgram.appointmentArrayList.get(i).setDuration(String.valueOf(duration));
                RunTheProgram.appointmentArrayList.get(i).setEditAmount(amountPaid_field.getText());
                RunTheProgram.appointmentArrayList.get(i).editAmountToAmount(RunTheProgram.appointmentArrayList.get(i).getEditAmount());
              }
              RunTheProgram.changeDisplayTimeS();
              RunTheProgram.changeDisplayTimeE();
              RunTheProgram.changeDisplayAddress();
              RunTheProgram.updateAppointmentCSV();
              RunTheProgram.addAppointmentToTable();
              RunTheProgram.addAmountToStaff();
              RunTheProgram.addCleanerToTable();
              RunTheProgram.addToTopThree();
              RunTheProgram.switchScene("ViewAppointment");
            }
          }
        }
    }
    
    @FXML
    void staffName_Change(ActionEvent event) {
      for (Staff s: RunTheProgram.staffOBList){
        if (staff_CBox.getValue().equals(s.getName())){
          staffID_field.setText(s.getId());
        }
      }
    }
    

    
    @FXML
    void initialize() {
      staffID_field.setText(ViewAppointmentController.selectedAppointment.getStaffID());
      customerName_field.setText(ViewAppointmentController.selectedAppointment.getCustomerName());
      address_box.setText(ViewAppointmentController.selectedAppointment.getAddress());
      duration_field.setText(ViewAppointmentController.selectedAppointment.getDuration());
      amountPaid_field.setText(ViewAppointmentController.selectedAppointment.getEditAmount());
      phoneNumber_field.setText(ViewAppointmentController.selectedAppointment.getPhoneNo());
      String displayHour;
      String displayMin;
      LocalDate displayDate;
      DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("dd/MM/yyyy");
      displayDate = LocalDate.parse(ViewAppointmentController.selectedAppointment.getDate(),formatter);
      date_DatePicker.setValue(displayDate);

      staff_CBox.valueProperty().set(ViewAppointmentController.selectedAppointment.getStaffName());
      displayHour = String.valueOf(Integer.parseInt(ViewAppointmentController.selectedAppointment.getTimeStart())/60);
      displayMin = String.valueOf(Integer.parseInt(ViewAppointmentController.selectedAppointment.getTimeStart())%60);
      for (int i = 0; i < displayHour.length();i++){
        if(displayHour.length() == 1){
          displayHour = "0"+displayHour; 
        }
      }
      for (int i = 0; i < displayMin.length();i++){
        if(displayMin.length() == 1){
          displayMin = "0"+displayMin; 
        }
      }
      
      hour_CBox.valueProperty().set(displayHour);
      min_CBox.valueProperty().set(displayMin);
      
      //set date, from offical document
      date_DatePicker.setConverter(new StringConverter<LocalDate>() {
        String pattern = "dd/MM/yyyy";
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
        
        {
          date_DatePicker.setPromptText(pattern.toLowerCase());
        }
        
        @Override public String toString(LocalDate date) {
          if (date != null) {
            return dateFormatter.format(date);
          } else {
            return "";
          }
        }
        
        @Override public LocalDate fromString(String string) {
          if (string != null && !string.isEmpty()) {
            return LocalDate.parse(string, dateFormatter);
          } else {
            return null;
          }
        }
      });
      
  
      //add hours to combobox
      for (int i = 9; i < 19;i++){
        String temp = "";
        if (i<10){
          temp = "0" + String.valueOf(i);
          hourLs.add(temp);
        }else{
          hourLs.add(String.valueOf(i));
        }
      }
      hour_CBox.setItems(hourLs);
      //add minutes to combobox
      for (int i = 0; i<60;i++){
        String temp = "";
        if (i<10){
          temp = "0"+String.valueOf(i);
          minLs.add(temp);
        }
        else{
          minLs.add(String.valueOf(i));
        }
      }
      min_CBox.setItems(minLs);
      //add staff name to combobox
      for (Staff s: RunTheProgram.staffOBList){
        staffName.add(s.getName());
      }
      staff_CBox.setItems(staffName);
    
    }
}










