
package Main;


public class HotelManagementSystem {

    
    public static void main(String[] args) {
        
        
        UseAsGuestOrReceptionist();
        
        
    }
    
    private static void UseAsGuestOrReceptionist(){
        
        System.out.println("Use as Guest (1) or Receptionist (2)");
        int choice = Input.getUserInputInt();
        
        switch(choice){
            case 1: GuestUser.guestUserMenu();
                break;
            case 2: ReceptionUser.receptionUserMenu();
                break;
                default:
                
        }
    }
}
