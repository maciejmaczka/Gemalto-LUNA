/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luna7test;




import com.safenetinc.luna.LunaSlotManager;
import com.safenetinc.luna.UserRole;
import java.io.ByteArrayInputStream;
import java.security.Key;
import java.security.KeyStore;
import java.util.Enumeration;
import javax.print.attribute.*;
import javax.crypto.SecretKey;
import java.math.BigInteger;
import com.safenetinc.luna.LunaAPI;
import com.safenetinc.luna.exception.LunaCryptokiException;
import com.safenetinc.luna.LunaSlotManager;
import com.safenetinc.luna.LunaTokenObject;
import com.safenetinc.luna.LunaUtils;
import com.safenetinc.luna.provider.key.LunaKey;
import com.safenetinc.luna.provider.key.LunaPrivateKeyRsa;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.AlgorithmParameters;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Random;
import com.safenetinc.luna.provider.LunaCertificateX509;
import java.io.FileOutputStream;
import java.security.NoSuchProviderException;
import java.security.Principal;
import java.security.SignatureException;
import java.util.Date;
import javax.security.cert.CertificateEncodingException;
import javax.security.cert.CertificateExpiredException;
import javax.security.cert.CertificateNotYetValidException;
import javax.security.cert.X509Certificate;
import com.safenetinc.luna.*;
import com.safenetinc.luna.provider.LunaProvider;
import java.security.*;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.*;
import java.lang.*;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
/**
 *
 * @author Maciej
 */
public class Luna7test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    
        
        try
        {
        
//   LunaSlotManager luna_slot_manager = null; 
 //  luna_slot_manager.
   
   System.out.println("1");
   
    String partition_password = "Pass1234";
    String partition_label = "MAC"; 
    KeyStore luna_keystore;
    int slot = 0;
        
    System.out.println("2");
    Security.addProvider(new com.safenetinc.luna.provider.LunaProvider());
    ByteArrayInputStream is1 = new ByteArrayInputStream(("slot:" + slot).getBytes());
    
     System.out.println("3");
    
    luna_keystore = KeyStore.getInstance("LUNA");
    luna_keystore.load(is1, partition_password.toCharArray());
     
    
    
     System.out.println("4");
    
    Enumeration<String> key_aliases =  luna_keystore.aliases();
    
    while(key_aliases.hasMoreElements())
    {
        
        
      System.out.println(key_aliases.nextElement().intern());
        
    }
        
        }
        catch   (Exception e)
        {
            
            System.out.println("error: " + e.getMessage());
            
        }
    }
    
}
