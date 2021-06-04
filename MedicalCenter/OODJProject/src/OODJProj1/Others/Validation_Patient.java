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
import java.util.regex.*;
public class Validation_Patient extends Validation {
    
      public String PatientValidation(String patientname,String patientnum){
        String errormessage = null;
        //required fields validation!
        
        boolean NameRequired = required(patientname);
        boolean NumRequired = required(patientnum);
        String FieldName = "";
        if(!NameRequired | !NumRequired ){
            if(!NameRequired){
                FieldName += "Name";                
            }
            if(!NumRequired && FieldName.length()>0){
              FieldName +=  " and phone number";
            }
            else if(!NumRequired && FieldName.length()==0){
             FieldName += "Phone Number ";
        }
        }
            else{ //check the validation.
            boolean NameOnlyChar = isName(patientname);
            boolean NumOnlyPhone = isNumeric(patientnum);
            boolean ValidPhone = isPhone(patientnum);
            if(!NameOnlyChar){
                 FieldName += "Name only accept character.";
            }
            if(!NumOnlyPhone && FieldName.length()>0){
                FieldName+=" \nPphone only accepts numbers";
            }
            else if(!NumOnlyPhone){
                FieldName+="Phone only accepts numbers";
            }
            if(!ValidPhone && FieldName.length()>0){
                FieldName+=" \n Invalid phone number";
            }
            else if(!ValidPhone){
                FieldName+="Invalid phone number";
            }
            if(FieldName.length()>0){
                FieldName += "\n";
            }
                  errormessage = errorMsg(FieldName, "");
        }

    
           System.out.println(errormessage);        
        return errormessage;
    }
}
