/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keyatr;
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
import java.io.FileOutputStream;
/**
 *
 * @author Maciej
 */
public class KeyAtr {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        
        try
        {
          Security.addProvider(new com.safenetinc.luna.provider.LunaProvider());
          System.out.println("ABC1");
            
           LunaSlotManager slot_manager = null; 
           System.out.println("ABC2");
           
           
          // slot_manager.login(slot_manager.findSlotFromLabel("G5"), UserRole.CRYPTO_OFFICER, "alamakota");
           slot_manager = LunaSlotManager.getInstance();
           slot_manager.login("alamakota");
          
           if (slot_manager.isLoggedIn())
           {
                System.out.println("loged in");
           }
        
            System.out.println("ABC2");
                  
           LunaTokenObject lto = LunaTokenObject.LocateObjectByHandle(27);
           LunaTokenObject dest =  LunaTokenObject.LocateObjectByHandle(38);
           
      //     lto.SetSmallAttribute(LunaAPI.CKA_ID, 318865968);
           
           
           
        //   for (int i = 0 ; i < 10240 ; i++)
           {
               
            
           
       
                    
                   byte[] id =   lto.GetLargeAttribute(LunaAPI.CKA_ID);
                   
                   try { 
    FileOutputStream fileOuputStream = new FileOutputStream("filename"); 
    fileOuputStream.write(id);
 } catch (Exception e)
 {
     
 }
    
                   
                   dest.SetLargeAttribute(LunaAPI.CKA_ID, id);
                   
                   for (int i = 0 ; i < id.length ; i++)
                   {
                        
                        
                        
                    //     System.out.print(Character.toString((char)  (id[i] & 0xff) ));
                       System.out.print( Integer.toHexString((int) id[i] & 0xff));
                       
                   }
                  
                   
                   
                   
                   
                    //   System.out.println(  lto.GetLargeAttribute(LunaAPI.CKA_ID));
                   
               }
          //      catch (Exception w)
        {
           //     System.out.println("-");
        }

              
               
           
           
           
           
       //    System.out.println("wrap " +   lto.GetSmallAttribute(     LunaAPI.CKA_WRAP));
       //     System.out.println("unwrap " +   lto.GetSmallAttribute(     LunaAPI.CKA_UNWRAP));
       //    System.out.println("enc " +   lto.GetSmallAttribute(     LunaAPI.CKA_ENCRYPT));
     //       System.out.println("dec " +   lto.GetSmallAttribute(     LunaAPI.CKA_DECRYPT));
    //        System.out.println("token " +   lto.GetSmallAttribute(     LunaAPI.CKA_TOKEN));
      //      System.out.println("type " +   lto.GetSmallAttribute(     LunaAPI.CKA_KEY_TYPE));
       //     System.out.println("private " +   lto.GetSmallAttribute(     LunaAPI.CKA_PRIVATE));                     



        //    System.out.println("label " +   lto.GetSmallAttribute(     LunaAPI.CKA_LABEL));
       //     System.out.println("sing " +   lto.GetSmallAttribute(     LunaAPI.CKA_SIGN));
        //    System.out.println("ver " +   lto.GetSmallAttribute(     LunaAPI.CKA_VERIFY));
         //   System.out.println("derive " +   lto.GetSmallAttribute(     LunaAPI.CKA_DERIVE));
         //   System.out.println("extr " +   lto.GetSmallAttribute(     LunaAPI.CKA_EXTRACTABLE));    


         //   System.out.println("finger " +   lto.GetSmallAttribute(     LunaAPI.CKA_FINGERPRINT));
      //      System.out.println("id " +   lto.GetSmallAttribute(     LunaAPI.CKA_ID));
       //     System.out.println("type " +   lto.GetSmallAttribute(     LunaAPI.CKA_KEY_TYPE));    

        }catch (Exception e)
        {
            
            System.out.println(e.getMessage());
                    
            
        }
        
    }
    
}
