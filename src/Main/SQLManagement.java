
package Main;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;


public class SQLManagement {
    
    private static final String url = "jdbc:mysql://localhost:3306/hotel?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "";
    private static Statement sqlStatement=null;
    private static Connection sqlConnection;
    
    public static void testConnection(){
        try {
            sqlConnection = (Connection) DriverManager.getConnection(url, user, password);
            System.out.println("Anslutningen lyckades!");

            sqlStatement = sqlConnection.createStatement();
            
      

    }catch(Exception e){
            System.out.println(e);
    }
  }
    public static void guesstDataToDb(int guestId,String first,String last,int numberOfNights){
        testConnection();
        try  {
            sqlStatement = sqlConnection.createStatement();
        //   int a = 0; 
         
    
          //  for (Room room : HotelManagementSystem.allRoomsList) {
                
            //       if(room.guest.getFirstName().equalsIgnoreCase(first)&& room.guest.getLastName().equalsIgnoreCase(last)){
             //           a = room.guest.getGuestId();
               //    }
           // }
            
            sqlStatement.executeUpdate("INSERT INTO guests(GuestId,FirstName,LastName,NumberOfNights) VALUES("+guestId+",'" +first+ "','"+last+"',"+numberOfNights+");");
          
    }catch(Exception e){
            System.out.println("This is guestDataToDB"+e);
            
    }
    
   }
    public static void getGuestData(){
        testConnection();
        ArrayList<Guest>guestData = new ArrayList<Guest>();
        try{
        sqlStatement = sqlConnection.createStatement();
        
        ResultSet rs = sqlStatement.executeQuery("SELECT*FROM guests");
        
        while(rs.next()){
            guestData.add(new Guest(rs.getString("FirstName"),rs.getString("LastName"),rs.getInt("Numberofnights")));
        }
        
        }catch(Exception e){
            System.out.println(e);
        }
        System.out.println(guestData.toString());
    }
    public static void searchSpecifikGuestData(){
        Scanner scan = new Scanner(System.in);//TODO Use input method
        ArrayList<Guest>guestData = new ArrayList<>();
        
        testConnection();
        try{
            System.out.println("Search for customer name");
            System.out.println("Name");
            String firstName = scan.nextLine();
           // System.out.println("Last name");
            //String lastName = scan.nextLine();
            
            sqlStatement = sqlConnection.createStatement();
            ResultSet rs = sqlStatement.executeQuery("SELECT*FROM guests WHERE FirstName = '%"+firstName+"%'OR LastName ='%"+firstName+"%'");
             while(rs.next()){
            guestData.add(new Guest(rs.getString("FirstName"),rs.getString("LastName"),rs.getInt("numberOfNights")));
        }
         if(guestData.isEmpty()){
            System.out.println("Sorry did not find any match");
        }else{
            System.out.println(guestData.toString());
        }
            
            
            
        }catch(Exception e){
            System.out.println(e);
        }
        
    }
    public static void updateGuestData(){
        testConnection();
        Scanner scan = new Scanner(System.in);//TODO change to input method
        try{
            System.out.println("Update name");
            System.out.println("Who's name would you like to update?");
            System.out.println("First name:");
            String firstName = scan.nextLine();
            System.out.println("Last name:");
            String lastName = scan.nextLine();
            
            System.out.println("What would you like to change it to?");
            System.out.println("First name");
            String firstNameNew = scan.nextLine();
            System.out.println("Last name");
            String lastNameNew = scan.nextLine();
            
            sqlStatement = sqlConnection.createStatement();
            
            sqlStatement.executeUpdate("UPDATE Guests SET FirstName ='"+firstNameNew+"',LastName ='"+lastNameNew+"' WHERE FirstName ='"+firstName+"'AND LastName = '"+lastName+"");
            
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public static void deleteGuestData(){
        Scanner scan = new Scanner(System.in);
        System.out.println("who would you like to remove from the system?");
        System.out.println("First name:");
        String firstName = scan.nextLine();
        System.out.println("Last name");
        String lastName = scan.nextLine();
        
        System.out.println("Are you sure you would like to remove "+firstName+" "+lastName+" from the database? Y/N");
        String yeyOrNay = scan.nextLine();
        if(yeyOrNay.equalsIgnoreCase("y")){
        testConnection();
        try{
            sqlStatement = sqlConnection.createStatement();
            
            sqlStatement.executeUpdate("DELETE FROM uests WHERE FistName ='"+firstName+"' AND LastName ='"+lastName+"';");
            
        }catch(Exception e){
            System.out.println(e);
        }
        }else{
            System.out.println("Nothing was done to the data");
        }
     
    }
    public static void guestRoomchoice(int roomNumber){
        
    }
    public static int getLagestIdFromDb(){
        testConnection();
        int a=0;
        try{
           
            sqlStatement = sqlConnection.createStatement();
            
            ResultSet rs = sqlStatement.executeQuery("SELECT MAX(GuestId) AS largestId FROM Guests;");
            
            while(rs.next()){
                a = rs.getInt(1);
                a++;
               
            }
            
        }catch(Exception e){
            System.out.println(e);
        }
        return a;
       
        
    }
    public static void addSomePeopleToDB(ArrayList<Guest>guestList){
        
        
    }
    public static void testMethod(){
        Room room1 = new StandardDoubleRoom();
        testConnection();
        try{
            
            PreparedStatement pa = sqlConnection.prepareStatement("INSERT INTO rooms (RoomNumber,RoomName,Beds,BedName,AcEquipped,BreakFastIncluded,ChargePerDay,Guest) VALUES (?,?,?,?,?,?,?,?)");
            pa.setInt(1,room1.getRoomNr());
            pa.setString(2, room1.getName());
            pa.setInt(3,room1.getBeds());
            pa.setString(4,room1.bedName);
            pa.setBoolean(5,room1.acEquipped);
            pa.setBoolean(6,room1.breakfastIncluded);
            pa.setInt(7,room1.getChargePerDay());
            pa.setBoolean(8,true);
            pa.executeUpdate();
            
            pa.close();
  
        }catch(Exception e){
            System.out.println(e);
        }
        
    }
    public static void updateRoomStatus(){
        try{
            
            
        }catch(Exception e){
            
        }
    }
   
    
}
