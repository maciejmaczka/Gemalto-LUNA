/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luna_list_all;
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
public class LUNA_LIST_ALL {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Security.addProvider(new com.safenetinc.luna.provider.LunaProvider());
        System.out.println("1223");
        
        HSMHandler hsm = new  HSMHandler();
        
        System.out.println("1223");
        
        hsm.list_slots();
        hsm.list_keys();
      //  hsm.gen_key();
        
        
        
    }
    
}
