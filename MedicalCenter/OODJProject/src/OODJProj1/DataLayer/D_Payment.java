/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OODJProj1.DataLayer;

import OODJProj1.Controller.C_Appointment;
import OODJProj1.Model.Appointments;
import OODJProj1.Model.Payment;
import OODJProj1.Others.helper;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class D_Payment extends BaseDataLayer {

    private final String filename = "Payment.txt";

    @Override
    public ArrayList<Payment> getAll() {
        List<String> Patients = readFiles(filename);
        ArrayList<Payment> allPayment = new ArrayList<Payment>();
        C_Appointment c_appointment = new C_Appointment();

        for (String line : Patients) {
            String[] split = line.split(",");
            Appointments appointment = c_appointment.getModel(split[1]);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            Payment record = new Payment(split[0], LocalDateTime.parse(split[3], formatter), Double.parseDouble(split[2]), appointment);
            allPayment.add(record);
        }
        return allPayment;
    }

    public void create(Payment newData) {
        create(newData.toFileString(), filename);
    }

    public void update(ArrayList<Payment> newData) {
        ArrayList<String> DataList = helper.ModelToString(newData);
        update(DataList, filename);
    }

}
