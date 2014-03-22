/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mailsend;

/**
 *
 * @author mathide
 */
import java.util.Properties;  
  
public class MyProperties {  
    private static Properties properties = null;  
      
    public static Properties getPro(String host){  
        properties  = System.getProperties();// 获取系统环境  
        properties.setProperty("mail.smtp.host", host);  
        properties.setProperty("mail.transport.protocol", "smtp");  
        properties.setProperty("mail.smtp.auth", "true");  
        properties.setProperty("mail.smtp.port", "25");
        //properties.setProperty("mail.smtp.starttls.enable", "true");
          
        return properties;  
    }  
}  