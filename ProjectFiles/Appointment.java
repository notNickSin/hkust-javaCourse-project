public class Appointment{
  
  private String id;
  private String staffID;
  private String staffName;
  private String customerName;
  private String phoneNo;
  private String address;
  private String date;
  private String timeStart;
  private String timeEnd;
  private String duration;
  private String amount;
  private String status;
  private String editAmount = "";
  
  private static int idCount;
  
  private String displayTimeS;
  private String displayTimeE;
   
    public Appointment(String id,String staffID, String staffName, String customerName,String phoneNo,String address,String date,String timeStart, String timeEnd, String duration,String amount,String status){
      this.id = id;
      this.staffID = staffID;
      this.staffName=staffName;
      this.customerName=customerName;
      this.phoneNo=phoneNo;
      this.address=address;
      this.date=date;
      this.timeStart=timeStart;
      this.timeEnd = timeEnd;
      this.duration = duration;
      this.amount=amount;
      this.status=status;
      idCount++;
      
      for (int i = 1; i<amount.length();i++){
        editAmount += amount.charAt(i);
      }
    }
    
    public String getId(){
      return id;
    }
    
    public String getStaffID(){
      return staffID;
    }
    
    public String getStaffName(){
      return this.staffName;
    }
    public String getCustomerName(){
      return this.customerName;
    }
    public String getPhoneNo(){
      return this.phoneNo;
    }
    public String getAddress(){
      return this.address;
    }
    public String getDate(){
      return this.date;
    }
    public String getTimeStart(){
      return timeStart;
    }
    
    public String getTimeEnd(){
      return timeEnd;
    }
    
    public String getDuration(){
      return duration;
    }
    
    public String getAmount(){
      return this.amount;
    }
    public String getStatus(){
      return this.status;
    }
    
    public String getDisplayTimeS(){
      return displayTimeS;
    }
    
    public String getDisplayTimeE(){
      return displayTimeE;
    }
    
    public int getIDCount(){
      return idCount;
    }
    
    public String getEditAmount(){
      return editAmount;
    }
    
    public void setStaffID(String staffID){
      this.staffID = staffID;
    }
    
    public void setStaffName(String staffName){
      this.staffName = staffName;
    }
    
    public void setCustomerName(String customerName){
      this.customerName = customerName;
    }
    
    public void setPhoneNo(String phoneNo){
      this.phoneNo = phoneNo;
    }
    
    public void setAddress(String address){
      this.address = address;
    }
    
    public void setDate(String date){
      this.date = date;
    }
    
    public void setTimeStart(String timeStart){
      this.timeStart = timeStart;
    }
    
    public void setTimeEnd(String timeEnd){
      this.timeEnd = timeEnd;
    }
    
    public void setDuration(String duration){
      this.duration = duration;
    }
    
    public void setAmount(String amount){
      this.amount = amount;
    }
    
    public void setEditAmount(String editAmount){
      this.editAmount = editAmount;
    }
    
    public void setDisplayTimeS(String time){
      this.displayTimeS = time;
    }
    
    public void setDisplayTimeE(String time){
      this.displayTimeE = time;
    }
    
    public void setStatus(String status) {
      this.status = status;
    }
    
    public void editAmountToAmount(String editAmount){
      amount = "$"+editAmount;
    }
}
