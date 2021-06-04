/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OODJProj1.Controller;

import OODJProj1.DataLayer.D_Appointment;
import OODJProj1.Model.Reports;
import OODJProj1.Model.Appointments;
import OODJProj1.Model.Users;
import OODJProj1.Model.Payment;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author cheon
 */
public class C_Reports  {
    C_User user = new C_User();
    C_Payment pay = new C_Payment();
    D_Appointment AppointmentData = new D_Appointment();
    private int count;
    
    public ArrayList<Reports> GenerateEarnings(LocalDate begin, LocalDate end){
        //Generates a list of doctors and their earnnings as well as missed apppointments within a timeframe
        ArrayList<Payment> docpayments = pay.GetAllPayment();
        ArrayList<Users> doctors = user.FilterUser("doctor");
        ArrayList<Reports> reportlist = new ArrayList<Reports>();
        for (Users doctor : doctors){ //loops trough the doctors
            System.out.println("Test doc ");
            double doctotal = 0;
            int count = 0;
            for(Payment payments:docpayments){ //for each doctor, loops trough the payments~
                        System.out.println("Test pay ");
                //checks if the payment matches the chosen datespan
                if(payments.getPaymentDate().toLocalDate().isBefore(end.plusDays(1)) &&
                    payments.getPaymentDate().toLocalDate().isAfter(begin.minusDays(1))){
                        Appointments docpointment = payments.getApp(); //retrieves the appointments which the payment was made for

                        if(docpointment.getAppDoctor() != null &&docpointment.getAppPatient()!=null ){
                        if(docpointment.getAppDoctor().getName().equals(doctor.getName())){ //~and then checks that the appointment belongs to the doctor
                            doctotal += payments.getPrice();  //adds to the total earned
                            count++; 
                             System.out.println("Test adding");//add to the number of appointments the doctor had
                    }
                    }
                }
            }
        Reports report = new Reports(doctor.getName(),doctor.getUid(),count,doctotal); //create the report based on each doctor
        reportlist.add(report);
        }
        return reportlist;
    } 
    
    
 public int CountMissed(LocalDate begin, LocalDate end) {
     //Counts a specific doctor's missed appointments
        count = 0;
        ArrayList<Appointments> appointmentlist = AppointmentData.getAll();
        for (Appointments appointment : appointmentlist) {
            if (appointment.getStatus().equals("Missed") && appointment.getSlot().toLocalDate().isAfter(begin.minusDays(1))
                    && appointment.getSlot().toLocalDate().isBefore(end.plusDays(1))) {
                count++;
            }
        }
        return count;
    }

    public int CountMissed(int doctor, LocalDate begin, LocalDate end) {
        //Counts total missed appointments in a date range
        count = 0;
        ArrayList<Appointments> appointmentlist = AppointmentData.getAll();
        for (Appointments appointment : appointmentlist) {
            if (appointment.getStatus().equals("Missed") && appointment.getAppDoctor().getUid() == (doctor)
                    && appointment.getSlot().toLocalDate().isAfter(begin.minusDays(1))
                    && appointment.getSlot().toLocalDate().isBefore(end.plusDays(1))) {
                System.out.println("counting");
                count++;
            }
        }
        return count;
    }    
 
    
        public boolean isDateAllowed(LocalDate date,LocalDate before) {
        if ((date.getDayOfMonth() >= 7) && (date.getDayOfMonth() <= 11)) {
            return false;
        }
        // Disallow odd numbered saturdays.
        // Allow all other days.
        return true;
        }
}