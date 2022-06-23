/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aes_key_dump;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.SecureRandom;
import javax.crypto.*;


/**
 *
 * @author Maciej
 */
public class AES_Key_Dump {
    
    
    public static  byte[]  encryption_key;
    public static  byte[]  user_seed;
    public static  String last_error_code = "";
  
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
           
    try
    {
        
    MessageDigest md5 = MessageDigest.getInstance("MD5");
    user_seed = md5.digest("alamakota".getBytes());       
            
    SecretKeySpec  spec = new SecretKeySpec ( user_seed,  "AES");
 
    SecretKey key = SecretKeyFactory.getInstance("AES").generateSecret(spec);
        
    }
    catch (Exception e)
    {
        
        System.out.println("Error: " + e.getLocalizedMessage());
        
    }
        
    }
    
}
