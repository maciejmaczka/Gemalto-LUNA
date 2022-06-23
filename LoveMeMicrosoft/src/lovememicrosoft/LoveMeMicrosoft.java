/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lovememicrosoft;
import java.util.*;
import com.safenetinc.luna.*;
import com.safenetinc.luna.provider.LunaProvider;
import java.security.*;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec; 
import com.safenetinc.luna.LunaSlotManager;
import com.safenetinc.luna.provider.param.LunaGmacParameterSpec;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import java.io.ByteArrayInputStream;
import javax.crypto.Cipher;
import java.util.Random;
import javax.crypto.Mac;
/**
 * 
 */
/**
 *
 * @author Maciej
 */
public class LoveMeMicrosoft {

    /**
     * @param args the command line arguments
     */
    static general_handler gm;
    
    
    public static String partition_label = "label"; 
    public static String partition_password = "haslo";
   
    public static int partition_slot = -1;
    public static int key_number = -1;
    
    public static String key_type = "none";
    
    public static void main(String[] args) 
    {
    
        
        gm = new general_handler();
        
        gm.start_read_args(args);
        
        partition_label = gm.partition_label;
        partition_password = gm.partition_password;
        partition_slot = gm.partition_slot;
        key_number = gm.key_number;
        key_type = gm.key_type;
        
       
        System.out.println("");
        System.out.println("(Label) " + partition_label + " (Slot) " + partition_slot + " (Key) " + key_number + " (Type) " + key_type);
        System.out.println("Procceed? [Y/N]");
     
        Scanner reader = new Scanner(System.in);
        String s = reader.nextLine();
       
  
        
        if ( s.equalsIgnoreCase("y"))
        {
            
            luna_worker lw = new luna_worker();
            
            lw.partition_label = gm.partition_label;
            lw.partition_password = gm.partition_password;
            lw.partition_slot = gm.partition_slot;
            lw.key_number = gm.key_number;
            lw.key_type = gm.key_type;
            
            lw.adopt_key();
            
            
            
        }
        else
        {
            
            System.out.println("Abort");
            System.exit(0);
            
        }
                
        
       
        
    }
    

    
}
