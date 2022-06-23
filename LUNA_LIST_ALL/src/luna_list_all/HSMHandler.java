/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luna_list_all;

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
/**
 *
 * @author Maciej
 */
public class HSMHandler 
{
    
    
    LunaSlotManager luna_slot_manager = null; 
    
    public String partition_password = "Pass12345";
    public String partition_label = "MAC"; 
    public KeyStore luna_keystore;
    public int slot = 0;
    
    
        public void write_to_file(String file, byte[] byte_to_save)
    {
        
        try
        {
            
            
        FileOutputStream fos = new FileOutputStream(file);
        int abc = 0;
        for (int i = 0 ; i < byte_to_save.length ; i++)
        {
            
            abc = byte_to_save[i];
            System.out.print(abc); 
            fos.write( byte_to_save[i]);
            
        }
        
        System.out.println("");
        fos.close();
        
        
        
        
        }
        catch (Exception e)
       {
            
            System.out.println("Error: " + e.getMessage());
           
            
      }
    }
    
    
    public void gen_key()
    {
        
        try
        {
            
            Random randomGenerator = new Random();
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA", "LunaProvider");
            kpg.initialize(2048 , SecureRandom.getInstanceStrong() );
            
          
            
            KeyPair kp;
            
            kp = kpg.genKeyPair();
            
            
            PrivateKey priv = kp.getPrivate();
            PublicKey pub = kp.getPublic();
        
            
            byte [] pubbyte = pub.getEncoded();
            
            
            
            System.out.print("\nPublic key: \n");
            for ( int i = 0; i < pubbyte.length ; i++)
            {
                
                System.out.print(pubbyte[i] & 0xff);
                
                
            }
            
            System.out.print("\n\n\n");
            System.out.print("\nPrivate key: \n");
            byte [] privbyte = priv.getEncoded();
            for ( int i = 0; i < privbyte.length ; i++)
            {
                
                System.out.print(privbyte[i] & 0xff);
                
                
            }
             System.out.print("\n\n\n");
            
            
            LunaCertificateX509[] certChain  = null;
            
            certChain = new LunaCertificateX509[1];
            
            String subjectname = "CN=some guy1, L=around, C=US";
            BigInteger serialNumber = new BigInteger("12345");
            Date notBefore = new Date();
            Date notAfter = new Date(notBefore.getTime() + 1000000000);
            
            certChain[0] = LunaCertificateX509.SelfSign(kp, subjectname, serialNumber, notBefore, notAfter);
      
            
            luna_keystore.setKeyEntry("PRIV ABC1", priv,  null,  certChain);
            
          
            byte[] certChainbyte = certChain[0].getEncoded();
                
            write_to_file("cert.der",  certChainbyte);
            
            
            LunaCertificateX509 aaa = new LunaCertificateX509(certChainbyte);
            X509Certificate abc = X509Certificate.getInstance(certChainbyte);
            
            
            
            
        }
        catch  (Exception e)
        {
            
            System.out.println("Error" + e.getMessage());
            
            
        }
                
        
        
    }
            


    public void list_keys()
    {
        try
        {
        
        ByteArrayInputStream is1 = new ByteArrayInputStream(("slot:" + slot).getBytes());
        luna_keystore = KeyStore.getInstance("LUNA");
        luna_keystore.load(is1, partition_password.toCharArray());
        
        
        Enumeration <String> key_aliases = luna_keystore.aliases();
        String key_alias = null;
        
       KeyStore.Entry key_entry = null;
        Key luna_current_key = null;
        
        
            while ( key_aliases.hasMoreElements())
            {

                key_alias = key_aliases.nextElement();
                luna_current_key =  luna_keystore.getKey(key_alias, null)
                        ;
                if (luna_current_key == null)
                {
                 
                    continue;
                }
                       
                
           
           
             LunaTokenObject lto = LunaTokenObject.LocateKeyByAlias(key_alias);
             lto.SetBooleanAttribute(LunaAPI.CKA_EXTRACTABLE, true);
 
             
             
             LunaKey lk = LunaKey.LocateKeyByAlias(key_alias);
             
             
                
                          System.out.println(lk.GetKeyHandle() + " " + key_alias + " " + luna_keystore.getCreationDate(key_alias)  + " - " +  lk.getKeySize() + " - " + lk.getFormat());
            } 
            
            
     
            
            
     
        }
        catch (Exception e)
        {
            
            
            System.out.print(e.getMessage());
            
        }
                
        
        
        
        
        
    
    
    }
    
    public void list_slots()
    {
        
         
        System.out.print("Initializing ... ");
        luna_slot_manager = LunaSlotManager.getInstance();
        String tokenlabel = "";
       
       
        // lista slotow
        
        try
        {
        
        int[] activeSlots = luna_slot_manager.getSlotList();
               
        System.out.println(" Slots found: " + activeSlots.length);
        
        
        for (int slot : activeSlots)
        {

                if (luna_slot_manager.isTokenPresent(slot))
                {
                    tokenlabel = luna_slot_manager.getTokenLabel(slot);

                    
                    
                   System.out.println("Slot: " + slot + " token label: " + tokenlabel);
                }
                
        }
        
        
        // logowanie
        
        luna_slot_manager.login(luna_slot_manager.findSlotFromLabel(partition_label), UserRole.CRYPTO_OFFICER, partition_password);
        
      
        
        luna_slot_manager.setSecretKeysExtractable(true);
        
        luna_slot_manager.setDefaultSlot(0);
        System.out.println("Using slot: " + luna_slot_manager.getDefaultSlot() + " Objects: " + luna_slot_manager.getCurrentObjectCount());      
        
        
        
        if (luna_slot_manager.isLoggedIn())
        {
            
            System.out.println("Logged in");
            System.out.println("-------------------\n");
           
            
        }
        else
        {
            
             System.out.println("Not Logged in");
           
             
        }
        
        
        
        
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
          
            
            
        }
        
        
    }
            
    
    
    
    
}
