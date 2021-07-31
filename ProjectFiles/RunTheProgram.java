import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.collections.*;
import java.util.*;
import javafx.util.converter.IntegerStringConverter;
import java.io.*;
import java.text.DateFormat;  
import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.util.Calendar;
import java.text.ParseException;


public class RunTheProgram extends Application{
  private static Stage window;
  
  public static ArrayList<Staff> staffArrayList;
  public static ArrayList<Appointment> appointmentArrayList;
  
  public static ObservableList<Staff> staffOBList = FXCollections.observableArrayList();
  public static ObservableList<Staff> allStaffOBList = FXCollections.observableArrayList();
  public static ObservableList<Staff> staffObservableListSorted = FXCollections.observableArrayList();
  public static ObservableList<Staff> topThreeList = FXCollections.observableArrayList();
  public static ObservableList<Appointment> appointmentOBList = FXCollections.observableArrayList();
  public static ObservableList<Appointment> workersAppointmentOBList = FXCollections.observableArrayList();
  
  
  public static String team;
  public static String userID;
  public static String role;
  public static String userName;
  
  public static void main(String[] args) {
    launch(args);
  }
  
  @Override
  public void start(Stage stage) throws FileNotFoundException, IOException, ParseException{
    window = stage;
    loadStaffData();
    loadAppointmentData();
    switchScene("LogInForm");
    window.show();
  }
  
  //For reading data
  public static void loadStaffData() throws FileNotFoundException{
    String[] line;
    File staffFile = new File("Staff.csv");
    staffArrayList=new ArrayList<>();
    
    Scanner scanner = new Scanner(staffFile);
    //Skip the first line
    line = scanner.nextLine().split(",");
    while (scanner.hasNextLine()){
      line = scanner.nextLine().split(",");
      Staff s = new Staff(line[0],line[1],line[2],line[3],line[4],line[5],line[6]);
      s.calSalary();   
      staffArrayList.add(s);
    }
    scanner.close();
  }
  
  public static void loadAppointmentData() throws FileNotFoundException, ParseException{
    String[] line;
    File appointmentFile = new File("Appointment.csv");
    appointmentArrayList=new ArrayList<>();
    
    Scanner scanner = new Scanner(appointmentFile);
    //Skip the first line
    line = scanner.nextLine().split(",");
    while (scanner.hasNextLine()){
      line = scanner.nextLine().split(",");
      Date tempDate = new SimpleDateFormat("dd/MM/yyyy").parse(line[6]);
      DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
      String strDate = dateFormat.format(tempDate);
      Appointment a = new Appointment(line[0],line[1],line[2],line[3],line[4],line[5],strDate,line[7],line[8],line[9],line[10],line[11]);
      appointmentArrayList.add(a);
    }
    scanner.close();
  
  }
  
  public static void changeDisplayTimeS(){
    int hour;
    int min;
    String time;
    IntegerStringConverter isc=new IntegerStringConverter();
    for (Appointment a: appointmentArrayList){
      hour = isc.fromString(a.getTimeStart())/60;
      min = isc.fromString(a.getTimeStart())%60;
      if (min<10){
        time = isc.toString(hour) + ":" + isc.toString(min) + "0";
      }
      else{
        time = isc.toString(hour) + ":" + isc.toString(min);
      }
      a.setDisplayTimeS(time);
    }
  }
  
  public static void changeDisplayTimeE(){
    int hourE;
    int minE;
    String timeE;
    IntegerStringConverter isc=new IntegerStringConverter();
    
    for (Appointment a: appointmentArrayList){
      if (a.getTimeEnd().equals("")){
        continue;
      }
      hourE = isc.fromString(a.getTimeEnd())/60;
      minE = isc.fromString(a.getTimeEnd())%60;
      if (minE<10){
        timeE = isc.toString(hourE) + ":" + isc.toString(minE) + "0";
      }
      else{
        timeE = isc.toString(hourE) + ":" + isc.toString(minE);
      }
      a.setDisplayTimeE(timeE);
    }
  }
  
  public static void changeDisplayAddress(){
    for (Appointment a: appointmentArrayList){
      a.setAddress(a.getAddress().replaceAll("/SEP/",","));
    }
  }
  
  public static void changeSaveAddress(){
    for (Appointment a: appointmentArrayList){
      a.setAddress(a.getAddress().replaceAll(",","/SEP/"));
    }
  }
 
  public static void loadWorkersAppointment(){
     workersAppointmentOBList.clear();
     try{
       DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
       Date currentDateTime= new Date();
       //Change Time to 0 so can compare Date only
       Calendar c = Calendar.getInstance();
       c.setTime(currentDateTime);
       c.set(Calendar.HOUR_OF_DAY, 0);
       c.set(Calendar.MINUTE, 0);
       c.set(Calendar.SECOND, 0);
       c.set(Calendar.MILLISECOND, 0);
       Date currentDate = c.getTime();
       
       Calendar c1 = Calendar.getInstance();
       c1.setTime(currentDate);
       c1.add(Calendar.DATE,7);
       Date currentDatePlus7 = c1.getTime();
      
       for (Appointment a: appointmentArrayList){
         Date jobDate = dateFormat.parse(a.getDate());
         //JobDate = today ||  nextweek> JobDate > today || JobDate=nextweek   => add to OB list
         if( currentDate.equals(jobDate)  || ( currentDatePlus7.after(jobDate) && currentDate.before(jobDate) ) ||  currentDatePlus7.equals(jobDate)){
           //staffID = loginID => add to OB list
           if( a.getStaffID().equals(userID)){          
             workersAppointmentOBList.add(a);
           }
         }         
       }
     }catch(ParseException ex){      
     }      
   }
  
  public static void addCleanerToTable(){
    staffOBList.clear();
    allStaffOBList.clear();
    staffObservableListSorted.clear();
    for(Staff s:staffArrayList){
      if (s.getRole().equals("C")){
        allStaffOBList.add(s);
        staffObservableListSorted.add(s);
      }
      if (s.getRole().equals("C")&& s.getTeam().equals(team)){
        // Manager is M, Cleaner is C
        staffOBList.add(s);
      }
    }
  }
  
  public static void addAppointmentToTable(){
    appointmentOBList.clear();
    changeDisplayAddress();
    for(Appointment a: appointmentArrayList){
      appointmentOBList.add(a);
    }
  }
  
  public static void addAmountToStaff(){
    double temp;
    for (Staff s:staffArrayList){
      s.setAmount("0");
    }
    for (Appointment a: appointmentArrayList){
      for (Staff s: staffArrayList){
        if(a.getStaffID().equals(s.getId()) && a.getStatus().equals("Completed")){
       //if(a.getStaffID().equals(s.getId())){
          temp = Double.parseDouble(s.getAmount());
          temp += Double.parseDouble(a.getEditAmount());
          s.setAmount(String.valueOf(temp));
          break;
        }
      }
    }
  }
  
  public static void addToTopThree(){
    topThreeList.clear();
    Collections.sort(staffObservableListSorted, Staff.StaffAmountComparator);
    
    for(Staff s:staffObservableListSorted){
        topThreeList.add(s);
      if (topThreeList.size()>3){
          topThreeList.remove(s);
      }
    }
  }
  
  public static void calMonthlyHour(){
    //get current month
    try{
    Calendar cal = Calendar.getInstance();
    Date currentTime = cal.getTime();
    cal.setTime(currentTime);
    int month = cal.get(Calendar.MONTH);
    for(Staff s: staffArrayList){
      s.setWorkHour("0");
    }
    Double tempHour;
      for(Staff s:staffArrayList){
        for(Appointment a:appointmentArrayList){
          Date tempDate = new SimpleDateFormat("dd/MM/yyyy").parse(a.getDate());
          cal.setTime(tempDate);
          int appointmentMonth = cal.get(Calendar.MONTH);
          if(a.getStatus().equals("Completed") && a.getStaffID().equals(s.getId()) && month == appointmentMonth){
            tempHour = Double.parseDouble(s.getWorkHour());
            tempHour += Double.parseDouble(a.getDuration());
            s.setWorkHour(String.valueOf(tempHour));
          }
        }
        s.calSalary();
      }
    }catch(ParseException ex){
    }
  }
  
  public static void switchScene(String name) throws IOException{
    Parent root = FXMLLoader.load(RunTheProgram.class.getResource(name+".fxml"));
    Scene scene = new Scene(root);
    window.setTitle(name);
    window.setScene(scene);
  }
  
  public static void updateStaffCSV() throws IOException {
    // write back staff data
    File file = new File("Staff.csv");
    // creates the file
    file.createNewFile();
    // creates a FileWriter Object
    try (FileWriter writer = new FileWriter(file)){
      // Writes the content to the file
      String staffHeader = "ID,Name,Password,Hourly Rate,Total Working Hour,Role,Team\n";
      writer.write(staffHeader);
      for(Staff s :staffArrayList){
        writer.write(s.getId()+","+
                     s.getName()+","+
                     s.getPassword()+","+
                     s.getRate()+","+
                     s.getWorkHour()+","+
                     s.getRole()+","+
                     s.getTeam()+"\n");
        
      }
      
      writer.flush();
      writer.close();
    }
  }
  
  public static void updateAppointmentCSV() throws IOException{
    // write back staff data
    File file = new File("Appointment.csv");
    // creates the file
    file.createNewFile();
    // creates a FileWriter Object
    try (FileWriter writer = new FileWriter(file)){
      // Writes the content to the file
      String appointmentHeader = "Appointment ID,Staff ID,Staff,Customer Name,Phone No.,Address,Date,Time start,Time end,Duration,Amount,Status\n";
      writer.write(appointmentHeader);
      for(Appointment a: appointmentArrayList){
        changeSaveAddress();
        writer.write(a.getId()+","+
                     a.getStaffID()+","+
                     a.getStaffName()+","+
                     a.getCustomerName()+","+
                     a.getPhoneNo()+","+
                     a.getAddress()+","+
                     a.getDate()+","+
                     a.getTimeStart()+","+
                     a.getTimeEnd()+","+
                     a.getDuration()+","+
                     a.getAmount()+","+
                     a.getStatus()+"\n");
        
      }
      
      writer.flush();
      writer.close();
    }
  }
}