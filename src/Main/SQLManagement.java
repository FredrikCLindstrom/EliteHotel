
package Main;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;


public class SQLManagement {
    
    private static final String url = "jdbc:mysql://localhost:3306/hotel?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "";//enter password here
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
    public static void guesstDataToDb(int guestId,String first,String last,int numberOfNights,String phone){
        testConnection();
        try  {
            sqlStatement = sqlConnection.createStatement();
        //   int a = 0; 
         
    
          //  for (Room room : HotelManagementSystem.allRoomsList) {
                
            //       if(room.guest.getFirstName().equalsIgnoreCase(first)&& room.guest.getLastName().equalsIgnoreCase(last)){
             //           a = room.guest.getGuestId();
               //    }
           // }
            
            sqlStatement.executeUpdate("INSERT INTO guests(GuestId,FirstName,LastName,NumberOfNights,phone) VALUES("+guestId+",'" +first+ "','"+last+"',"+numberOfNights+",'"+phone+"');");
          
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
            guestData.add(new Guest(rs.getString("FirstName"),rs.getString("LastName"),rs.getInt("Numberofnights"),rs.getString("phone")));
        }
        
        }catch(Exception e){
            System.out.println(e);
        }
        System.out.println(guestData.toString());
    }
    public static void searchSpecifikGuestData(){
        try {
            sqlStatement = sqlConnection.createStatement();
                    ResultSet rs = sqlStatement.executeQuery("SELECT*FROM guests;");
                    ListFunction(rs);
                    
                    System.out.println("1. search guest by name");
                    System.out.println("2. go back");
                    int choice = Input.getUserInputInt();
                    
                    switch(choice){
                        case 1:
                            System.out.println("Type in name to search");
                            String searchName=Input.getUserInputString();
                        sqlStatement = sqlConnection.createStatement();
                    ResultSet rs2 = sqlStatement.executeQuery("SELECT*FROM guests WHERE FirstName LIKE '%" +searchName +"%' OR LastName LIKE '%" + searchName +"%';");
                            ListFunction(rs2);
                    break;
                    
                        case 2:
                           
                           default:
                               break;
                    }
                    
        } catch (SQLException e) {
            System.err.println("SQL search error"+e);
        }
        
        
    }
    public static void updateGuestData() {
        boolean foundMatch = false;
        testConnection();
        ArrayList<Guest> guestData1 = new ArrayList<>();
        Scanner scan = new Scanner(System.in);//TODO change to input method
        String firstName="";
        String lastName ="";
        try {

            System.out.println("Update Guest");

            System.out.println("1.Update phone number");
            System.out.println("2. update name");
            System.out.println("3. Go Back");
            int choice = Input.getUserInputInt();

            switch (choice) {

                case 1:
                     System.out.println("Who's phone number would you like to update?");
                    System.out.println("First name:");
                    firstName = Input.getUserInputString();
                    System.out.println("Last name:");
                    lastName = Input.getUserInputString();
                    
                    sqlStatement = sqlConnection.createStatement();
                    ResultSet rs = sqlStatement.executeQuery("SELECT*FROM guests WHERE FirstName LIKE '%" + firstName + "%' OR LastName LIKE '%" + lastName + "%';");
                    
                    foundMatch=ListFunction(rs);
                    
                    
                    
                   // while (rs.next()) {
                   //     System.out.println(rs.getInt("GuestId") + " " + rs.getString("firstName") + " " + rs.getString("lastName") + " " + rs.getString("phone"));
                    //    //guestData1.add(new Guest(rs.getString("FirstName"), rs.getString("LastName"), rs.getInt("numberOfNights"), rs.getString("phone")));
                   // }
                    
                    if (foundMatch == true) {
                        System.out.println("What Guest id would you like to change phone number of");
                        int guestIdSQL=Input.getUserInputInt();
                        
                        System.out.println("What would you like to change the phone number to?");
                        String newPhoneNr=Input.getUserInputString();

                        sqlStatement = sqlConnection.createStatement();
                        sqlStatement.executeUpdate("UPDATE Guests SET phone ='" + newPhoneNr + "' WHERE guestId ="+guestIdSQL+";");
                    }else{
                        System.out.println("No match on search");
                    }
                    
                    break;
                case 2:
                    System.out.println("Search name you would you like to update?");
                    System.out.println("First name:");
                    firstName = Input.getUserInputString();
                    System.out.println("Last name:");
                    lastName = Input.getUserInputString();
                    
                    sqlStatement = sqlConnection.createStatement();
                    ResultSet rs2 = sqlStatement.executeQuery("SELECT*FROM guests WHERE FirstName LIKE '%" + firstName + "%' OR LastName LIKE '%" + lastName + "%';");
                    
                    
                    
                    foundMatch=ListFunction(rs2);
                    
                    if (foundMatch == true) {
                        System.out.println("What Guest id would you like to change name of");
                        int guestIdSQL=Input.getUserInputInt();
                        
                        System.out.println("What would you like to change first name to?");
                        String newFirstName=Input.getUserInputString();
                        
                        System.out.println("What would you like to change last name to?");
                        String newLastName=Input.getUserInputString();
                        
                        sqlStatement = sqlConnection.createStatement();
                        sqlStatement.executeUpdate("UPDATE Guests SET FirstName ='" + newFirstName + "',LastName ='" + newLastName + "' WHERE GuestId = "+guestIdSQL+ ";");
                        
                    }else{
                        System.out.println("No match on search");
                    }
                    
                    
                    break;
                default:
                    break;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void deleteGuestData(){
        Scanner scan = new Scanner(System.in);
        try {
            sqlStatement = sqlConnection.createStatement();
                    ResultSet rs = sqlStatement.executeQuery("SELECT*FROM guests;");
                    ListFunction(rs);
        
        
        System.out.println("who would you like to remove from the system?");
        System.out.println("GuestID:");
        int GuestID = Input.getUserInputInt();
        
        sqlStatement.executeUpdate("DELETE FROM guests WHERE GuestID ="+GuestID+";");
         } catch (SQLException e) {
            System.err.println("SQL exception delete");
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
   
    private static Boolean ListFunction(ResultSet result) throws SQLException {
        // hämta antal kolumner
        boolean match = true;
        int count=0;
        int columnCount = result.getMetaData().getColumnCount();
        // hämta alla kolmnnamn
        String[] columnNames = new String[columnCount];
        for (int i = 0; i < columnCount; i++) {
            columnNames[i] = result.getMetaData().getColumnName(i + 1);
        }
        
        // lägg kolumnnamn i string array
        for (String columnName : columnNames) {
            System.out.print(PadRight(columnName));
        }

        while (result.next()) {
            System.out.println();
            // hämta data för alla kolumner för den nuvarande raden
            for (String columnName : columnNames) {
                String value = result.getString(columnName);
                count++;
                if (value == null) {
                    value = "null";
                }

                System.out.print(PadRight(value));
            }
        }
        System.out.println("");
        if(count<1){
            match=false;
        }
        //System.out.println(count);
        return match;
    }

    private static String PadRight(String string) {
        int totalStringLength = 25;
        int charsToPadd = totalStringLength - string.length();

        // incase the string is the same length or longer than our maximum lenght
        if (string.length() >= totalStringLength) {
            return string;
        }

        StringBuilder stringBuilder = new StringBuilder(string);
        for (int i = 0; i < charsToPadd; i++) {
            stringBuilder.append(" ");
        }

        return stringBuilder.toString();
    }
     private static String getSingleMetadata(ResultSet result) throws SQLException {

        // hämta antal kolumner
        String value1 = "";
        int columnCount = result.getMetaData().getColumnCount();
        // hämta alla kolmnnamn
        String[] columnNames = new String[columnCount];
        for (int i = 0; i < columnCount; i++) {
            columnNames[i] = result.getMetaData().getColumnName(i + 1);
        }

        // lägg kolumnnamn i string array
        for (String columnName : columnNames) {
            System.out.print(PadRight(columnName));
        }

        while (result.next()) {
            System.out.println();
            // hämta data för alla kolumner för den nuvarande raden
            for (String columnName : columnNames) {
                String value = result.getString(columnName);

                if (value == null) {
                    value = "null";
                }

                System.out.print(PadRight(value));

            }
            value1 = result.getString(columnNames[2]);

        }
        System.out.println("");

        return value1;
    }
     public static void showStats(){
         System.out.println(Misc.GREEN+"--What would you like to see--"+Misc.RESET);
         System.out.println("1. see total nights stayed in hotel");
         System.out.println("2. see guest who stayed the most nights");
         System.out.println("3. total number of guests that have stayed");
         System.out.println("4. go back");
         
         int choice = Input.getUserInputInt();
         
         switch(choice){
             case 1: 
                 try {
                     sqlStatement = sqlConnection.createStatement();
                    ResultSet rs = sqlStatement.executeQuery("SELECT SUM(NumberOfNights) as TotalNights FROM guests;");
                    ListFunction(rs);
                    
                 } catch (SQLException e) {
                     System.err.println(e);
                 }

                 break;
             case 2:

                 try {
                     sqlStatement = sqlConnection.createStatement();
                     ResultSet rs = sqlStatement.executeQuery("SELECT FirstName, LastName, sum(NumberOfNights) as MostNigthsStayed FROM guests \n"
                             + "group by phone\n"
                             + "order by MostNigthsStayed desc\n"
                             + "limit 5");
                     ListFunction(rs);
                 } catch (SQLException e) {
                     System.err.println(e);
                 }
                 break;
             case 3:
                 try {
                     sqlStatement = sqlConnection.createStatement();
                     ResultSet rs = sqlStatement.executeQuery("SELECT count(Distinct phone) as TotalGuests FROM guests");
                     ListFunction(rs);
                 } catch (SQLException e) {
                     System.err.println(e);
                 }
                 break;
             default:;
                 break;
         }
     }
}
