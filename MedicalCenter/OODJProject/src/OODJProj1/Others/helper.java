/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OODJProj1.Others;

import OODJProj1.Model.BaseModel;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class helper {
   public static <T extends BaseModel> ArrayList<String> ModelToString(ArrayList<T> modelList){
        ArrayList<String> stringList = new ArrayList<String>();
        for(T current : modelList){
            stringList.add(current.toFileString());
        }
        return stringList; 
    }
    
    public static LocalDateTime currentDateTime(){
        LocalDateTime now = LocalDateTime.now();
        return now;
    }
    
}
