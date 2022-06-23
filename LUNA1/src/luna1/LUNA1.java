/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luna1;
import com.safenetinc.luna.*;
import com.safenetinc.luna.provider.LunaProvider;
import java.security.*;
    





public class LUNA1 {

    
    
    
    public  static LunaSlotManager slot_manager = null;
    
    public  static String partition_password = "alamakota";

    public static void initialize_slots()
    {
          
        Security.addProvider(new com.safenetinc.luna.provider.LunaProvider());
        System.out.print("Initializing ... ");
        
        slot_manager = LunaSlotManager.getInstance();
        String tokenlabel = "";
        
        int[] activeSlots = slot_manager.getSlotList();
               
        System.out.println(" slots found: " + activeSlots.length);

        for (int slot : activeSlots)
        {
            try
            {

                if (slot_manager.isTokenPresent(slot))
                {
                    tokenlabel = slot_manager.getTokenLabel(slot);

                    
                    
                   System.out.println("Slot: " + slot + " token label: " + tokenlabel);
                }
     
           
           
             // this command does not work till log in ?
             slot_manager.setDefaultSlot(0);      
             System.out.println("Default slot: " + slot_manager.getDefaultSlot());

               
             
             LunaSessionManager sesion_manager = new LunaSessionManager();
             
             LunaSession session = sesion_manager.getSession(0);
             
              System.out.println("Seesion handeler:" + session.GetSessionHandle()  + "Valid:" +session.StillValid() +  "Session slot: " + session.getSlot());
             
              
             
            
              // luna provider 
                 
              LunaProvider lp = new LunaProvider();    
              System.out.println("LP Info " + lp.getInfo());
              System.out.println("LP Version " + lp.getVersion());

          //   System.out.println(lp.getServices());
              
            
              Long[] abc = slot_manager.getTokenFirmwareVersion(slot);
           //   
           
           
           System.out.print("Token Firmware: ");
           for (Long ver : abc)
        {
          
            System.out.print(ver);
            
        }
              System.out.println("");
              
              
              slot_manager.login(slot_manager.findSlotFromLabel("G5"), UserRole.CRYPTO_OFFICER, "alamakota");
     
            
              System.out.println("Logged in: " + slot_manager.isLoggedIn());
            
            }
            catch(Exception e)
            {
               
                System.out.println("Exception - " + e.getLocalizedMessage());
                
            }
        }
        
  
        
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
      
        
        initialize_slots();
      
        
    }
    
}
