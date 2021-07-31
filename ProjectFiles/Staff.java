import java.util.Comparator;

public class Staff{
  private String id;
  private String name;
  private String password;
  private String rate;
  private String workHour;
  private String role;
  private String team;
  private Double salary;
  private String amount = "0";
  
  public Staff(String id, String name, String password, String rate,  String workHour, String role, String team){
    this.id = id;
    this.name = name;
    this.password = password;
    this.rate = rate;
    this.workHour = workHour;
    this.role = role;
    this.team = team;
  }
  
  public String getId(){
    return id;
  }
  
  public String getName(){
    return name;
  }
  
  public String getPassword(){
    return password;
  }
  
  public String getRate(){
    return rate;
  }
  
  public String getWorkHour(){
    return workHour;
  }
  
  public String getRole(){
    return role;
  }
  
  public String getTeam(){
    return team;
  }
  
  
  
  public Double getSalary(){
    return salary;
  }
  
  public String getAmount(){
    return amount;
  }
  
  public void calSalary(){
    salary = Double.parseDouble(workHour)*Double.parseDouble(rate);
  }
  
  public void setName(String name){
    this.name = name;
  }
  
  public void setPassword(String password){
    this.password = password;
  }
  
  public void setRate(String rate){
    this.rate = rate;
  }
  
  public void setAmount(String amount){
    this.amount = amount;
  }
  
  public void setWorkHour(String workHour){
    this.workHour = workHour;
  }
  
  public void minusWorkHour(){
    this.workHour = String.valueOf(Double.parseDouble(workHour)-1);
  }
  
  public void plusWorkHour(){
    this.workHour = String.valueOf(Double.parseDouble(workHour)+1);
  }
  
  public static boolean login(String textfield, String passwordfield){ 
    for(Staff s: RunTheProgram.staffArrayList){  
      if (s.id.equals(textfield) && s.password.equals(passwordfield)){ 
        RunTheProgram.userID = s.id;
        RunTheProgram.role = s.role;
        RunTheProgram.team = s.team;
        RunTheProgram.userName = s.name;
        return true; 
      } 
    } 
    return false;
  }
  
  public static Comparator<Staff> StaffAmountComparator = new Comparator<Staff>() {
               
    public int compare(Staff s1, Staff s2)
    {
      Double StaffAmount1
        = Double.parseDouble(s1.getAmount());
      Double StaffAmount2
        = Double.parseDouble(s2.getAmount());
      
      return StaffAmount2.compareTo(StaffAmount1);
    }
  };
  
  
}