/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OODJProj1;

import OODJProj1.Model.BaseModel;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class helper {
    static <T extends BaseModel> ArrayList<String> ModelToString(ArrayList<T> modelList){
        ArrayList<String> stringList = new ArrayList<String>();
        for(T current : modelList){
            stringList.add(current.toFileString());
        }
        return stringList; 
    }
    

    
//     public static ArrayList<String> UserModelToString(ArrayList<Users> modelList){
//        ArrayList<String> stringList = new ArrayList<String>();
//        for(Users current : modelList){
//            stringList.add(current.toFileString());
//        }
//        return stringList; 
//    }
//     
//     public static ArrayList<String> AppointmentsModelToString(ArrayList<Appointments> modelList){
//        ArrayList<String> stringList = new ArrayList<String>();
//        for(Appointments current : modelList){
//            stringList.add(current.toFileString());
//        }
//        return stringList; 
//    }
//     
//     public static ArrayList<String> PatientsModelToString(ArrayList<Patient> modelList){
//        ArrayList<String> stringList = new ArrayList<String>();
//        for(Patient current : modelList){
//            stringList.add(current.toFileString());
//        }
//        return stringList; 
//    }
//     
//     public static ArrayList<String> PaymentModelToString(ArrayList<Payment> modelList){
//        ArrayList<String> stringList = new ArrayList<String>();
//        for(Payment current : modelList){
//            stringList.add(current.toFileString());
//        }
//        return stringList; 
//    }
}
