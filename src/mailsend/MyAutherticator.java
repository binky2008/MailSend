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
import javax.mail.Authenticator;  
import javax.mail.PasswordAuthentication;  
  
public class MyAutherticator extends Authenticator {  
    private String username;  
    private String password;  
  
    public MyAutherticator() {  
        super();  
    }  
  
    public MyAutherticator(String user, String pwd) {  
        super();  
        username = user;  
        password = pwd;  
    }  
  
    public PasswordAuthentication getPasswordAuthentication() {  
        return new PasswordAuthentication(username, password);  
    }  
}  
