/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OODJProj1.Others;

import java.util.regex.*;

/**
 *
 * @author User
 */
public class Validation {
    Pattern p;

    
    public boolean isNumeric(String txt){
        boolean isNumeric = false;
        if(Pattern.matches("[0-9]+",txt)){
            isNumeric = true;
        }
        return isNumeric;
    }
       public boolean isPhone(String txt){
        boolean isPhone = false;
        if(Pattern.matches("\\d{8,15}",txt)){
            isPhone = true;
        }
        return isPhone;
    }
    public boolean onlyChar(String txt){        
        boolean isString = false;        
        System.out.println(txt);        
        if(Pattern.matches("[a-zA-Z]+",txt)){
            isString = true;
        }
        return isString;        
    }
    
    public boolean isName(String txt){
        boolean isString = false;                       
        if(Pattern.matches("[a-zA-Z\\s]+",txt)){
            isString = true;
        }
        return isString;
    }
    
    public boolean NoSpecialChar(String txt){
        boolean SpecChar = false;
        boolean testing = Pattern.matches("[a-zA-Z0-9\\s]+", txt);
        System.out.println(testing + "testing");
        if(Pattern.matches("[a-zA-Z0-9\\s]+", txt)){
            SpecChar = true;
        }
        return SpecChar;
    }
    
    public boolean isPrice(String txt){        
        boolean isPrice = Pattern.matches("[0-9]+([,.][0-9]{1,2})?", txt);
        return isPrice;
    }
    
    public boolean required(String ...args){
        boolean fill = true;
        for (String arg : args) {
            if ( arg.equals("") ) {
                fill = false;
            }
        }
        return fill;
    }
        
        
        
    
      public boolean required(String txt){
        boolean notEmpty = false;
        if(!txt.isEmpty()){
            notEmpty = true;
        }
        return notEmpty;
    }
    
    public String errorMsg(String FieldName, String error){
        String errorMsg = FieldName + error;
        return errorMsg;
    }
    
   
  
}