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
import java.io.FileInputStream;
import java.nio.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
/**
 *
 * @author Maciej
 */




public class luna_worker 
{
    
    
    public  String partition_label = "label"; 
    public  String partition_password = "haslo";
   
    public  int partition_slot = -1;
    public  int key_number = -1;
    
    public LunaSlotManager slot_manager;
    
    public String key_type = "none";
    
    public void set_attr_pub()
    {
        
        try
        {
            
            
            LunaTokenObject key_to_adapt = LunaTokenObject.LocateObjectByHandle(key_number);
            System.out.println("Transforming key");
            
            

            key_to_adapt.SetSmallAttribute(LunaAPI.CKA_ENCRYPT, 1);
            key_to_adapt.SetSmallAttribute(LunaAPI.CKA_WRAP, 1);            
            key_to_adapt.SetSmallAttribute(LunaAPI.CKA_VERIFY, 1);                
            key_to_adapt.SetSmallAttribute(LunaAPI.CKA_DERIVE, 0);  


         //   FileInputStream fis = new FileInputStream("key_att");
            
            Path path = Paths.get("key_att");
            byte[] id = Files.readAllBytes(path);

            key_to_adapt.SetLargeAttribute(LunaAPI.CKA_ID, id);
            
            
        }
        catch (Exception e)
        {
            
            System.out.println("LW_SAPU " + e.getMessage() );
            
            
            
        }
        
        
        
        
        
    }
    
    public void set_attr_priv()
    {
        
        
                try
        {
            
            
            LunaTokenObject key_to_adapt = LunaTokenObject.LocateObjectByHandle(key_number);
            System.out.println("Transforming key");
            
            

            key_to_adapt.SetSmallAttribute(LunaAPI.CKA_DECRYPT, 1);
            key_to_adapt.SetSmallAttribute(LunaAPI.CKA_UNWRAP, 1);            
            key_to_adapt.SetSmallAttribute(LunaAPI.CKA_SIGN , 1);                
            key_to_adapt.SetSmallAttribute(LunaAPI.CKA_DERIVE, 0);  

            key_to_adapt.SetSmallAttribute(LunaAPI.CKA_EXTRACTABLE, 1);  

           
         //   FileInputStream fis = new FileInputStream("key_att");
            
            Path path = Paths.get("key_att");
            byte[] id = Files.readAllBytes(path);

            key_to_adapt.SetLargeAttribute(LunaAPI.CKA_ID, id);
            
            
        }
        catch (Exception e)
        {
            
            System.out.println("LW_SAPU " + e.getMessage() );
            
            
            
        }
        
        
        
    }
    
    
    
    public void adopt_key()
    {
        
        try
        {
            Security.addProvider(new com.safenetinc.luna.provider.LunaProvider());
            
            slot_manager = LunaSlotManager.getInstance();
            
            String check_par_label = slot_manager.getTokenLabel(partition_slot);
            
            if (check_par_label.equals( partition_label) )
            {
                
                slot_manager.login(partition_slot, UserRole.CRYPTO_OFFICER, partition_password);
                
                
                if (slot_manager.isLoggedIn())
                {
                  
                    System.out.println("Logged in");
                    
                    if (key_type.equalsIgnoreCase("priv"))
                    {
                        
                        set_attr_priv();
                        
                    }
                    
                    if (key_type.equalsIgnoreCase("pub"))
                    {
                        
                        set_attr_pub();
                        
                    }
                    
                    
                    
                    
                    
                    
                    
                }
                else
                {
                    
                    
                    System.out.println("Problem with logging in. Please verify partition details");
                    System.out.println("");
                    System.exit(0);                   
                    
                    
                }
                
                
                
                
                
                
            }
            else
            {
                
                
                System.out.println("Problem with slot/label. Please verify partition details");
                System.out.println("");
                System.exit(0);
                
            }
            
            
        }
        catch (Exception e)
        {
            
            System.out.println("LW_AK " + e.getMessage());
            
        }
        
        
        
        
        
        
        
        
    }
    
    
    
}
