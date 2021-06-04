/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OODJProj1.DataLayer;

import OODJProj1.Controller.C_User;
import OODJProj1.Controller.C_Patient;
import OODJProj1.Model.Users;
import OODJProj1.Model.Appointments;
import OODJProj1.Model.Patients;
import OODJProj1.Others.helper;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class D_Appointment extends BaseDataLayer {
private final String filename = "Appointments.txt";

@Override
    public ArrayList<Appointments> getAll() {
         List<String> appointment = readFiles(filename);
        ArrayList<Appointments> allAppointments = new ArrayList<Appointments>();
        C_Patient c_patient = new C_Patient();
        C_User c_user = new C_User();
        for (String line : appointment) {
            String[] split = line.split(",");
            Patients appPatient = c_patient.getModel(split[1]);
            Users appDoctor = c_user.getModel(split[2]);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            Appointments Appointment = new Appointments(Integer.parseInt(split[0]), appPatient, appDoctor, split[3], LocalDateTime.parse(split[4], formatter), split[5]);
            allAppointments.add(Appointment);
        }
        return allAppointments;
    }
    
    public void create(Appointments newData){
       create(newData.toFileString(), filename);       
    }
    
    public void update(ArrayList<Appointments> newData){
        ArrayList<String> DataList = helper.ModelToString(newData);
        update(DataList, filename);        
    }
    
}
