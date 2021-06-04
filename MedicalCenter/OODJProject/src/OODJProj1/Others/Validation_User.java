/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OODJProj1.Others;

/**
 *
 * @author cheon
 */
public class Validation_User extends Validation{
  public String UserValidation(String username, String name, String pwd, String desc){
        String errormessage = null;
        //required fields validation!
        boolean NameRequired = required(name);
        boolean UsernameRequired = required(username);
        boolean PwdRequired = required(pwd);
        String FieldName = "";
        if(!NameRequired | !UsernameRequired | !PwdRequired){
            if(!NameRequired){
                FieldName += "Name";                
            }
            if(!UsernameRequired && FieldName.length()>0 && PwdRequired){
                FieldName += " and Username"; 
            }else if(!UsernameRequired && FieldName.length()>0 && !PwdRequired){
                FieldName += ", Username"; 
            }else if(!UsernameRequired && FieldName.length()==0){
                FieldName += "Username"; 
            }
            if(!PwdRequired && FieldName.length()>0){
                FieldName += " and Password";
            }else if(!PwdRequired){
                FieldName += "Password";
            }
            errormessage = errorMsg(FieldName, " is required!");
        }else{ //check the validation.
            boolean NameOnlyChar = isName(name);
            boolean UsernameNoSpecialChar = NoSpecialChar(username);
            boolean PasswordNoSpecialChar = NoSpecialChar(pwd);
            if(!NameOnlyChar){
                 FieldName += "Name only accept character.";
            }
            if(!UsernameNoSpecialChar && FieldName.length()>0){
                 FieldName += "\nUsername only accept character and number.";
            }else if(!UsernameNoSpecialChar){
                FieldName += "Username only accept character and number.";
            }
            if(FieldName.length()>0){
                FieldName += "\n";
            }
            if(!PasswordNoSpecialChar){
                FieldName += "Password only accept character and number. ";
            }
            errormessage = errorMsg(FieldName, "");
        }
        System.out.println(errormessage);        
        
        
        return errormessage;
    }
    
}
