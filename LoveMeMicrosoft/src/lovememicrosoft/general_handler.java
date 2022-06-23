package lovememicrosoft;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Maciej
 */
public class general_handler {
    
        
    public String partition_label = "label"; 
    public String partition_password = "haslo";
   
    public int partition_slot = -1;
    public int key_number = -1;
    
    
    public String key_type = "none";
    
        
    public void start_read_args(String[] args)
    {
        try
        {
        
        if (args.length != 5)
        {
            
            print_help();
            
            
        }
        
        partition_label = args[0];
        partition_slot = Integer.parseInt(args[1]);
        partition_password = args[2];
        key_number = Integer.parseInt(args[3]);
        key_type = args[4];
        
        
        if (key_type.equalsIgnoreCase("priv") || (key_type.equalsIgnoreCase("pub") ) )
        {
            
            
        }
        else
        {
            
            System.out.println("Wrong key type");
            System.exit(0);
            
        }
        
        
        }
        catch (Exception e)
        {
            
            System.out.println("(GH_STA)" + e.getMessage());
            print_help();
            
            
        }
                 
        
        
    }
    
    
    
    public void print_help()
    {
        System.out.println("");
        System.out.println("Thales -> Gemalto migration kit stage 3  ver 1.0 ");
        System.out.println("");
        System.out.println("Usage:   java -jar lovememicrosoft   <partition label>  <slot number> <partition password> <key number> <priv/pub>");
        System.out.println("");
        System.out.println("");
        System.exit(0);
        
    }
    
    
    
}
