/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OODJProj1.Controller;

import OODJProj1.DataLayer.D_Appointment;
import OODJProj1.DataLayer.D_Payment;

import OODJProj1.Model.Appointments;
import OODJProj1.Model.Payment;
import OODJProj1.Others.helper;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class C_Payment extends ControllerMessages {

    D_Payment PaymentData = new D_Payment();
    D_Appointment AppointmentData = new D_Appointment();
    
    public void delete(Payment DeletedRec) {
        Boolean isExist = false;
        try {
            for (int i = 0; i < PaymentData.getAll().size(); i++) {
                Payment compare = PaymentData.getAll().get(i);
                if (DeletedRec.getPaymentID()== compare.getPaymentID()) {
                    isExist = true;
                    break;
                }
            }
            if (isExist) {
                ArrayList<Payment> newPaymentData = new ArrayList<Payment>();
                for (int i = 0; i < PaymentData.getAll().size(); i++) {
                    Payment payment = PaymentData.getAll().get(i);
                    if (payment.getPaymentID() != DeletedRec.getPaymentID()) {
                        newPaymentData.add(payment);
                        System.out.println("added to new list");
                    } else {
                        newPaymentData.remove(payment);
                        System.out.println("removed");
                    }
                }
                if (newPaymentData.size() > 0) {
                    PaymentData.update(newPaymentData);
//                    for (int i = 0; i < newList.size(); i++) {
//                        AppointmentData.writeTo(newList.get(i));
//                        System.out.println("writing");
//                    }
                }
            } 
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public boolean CheckPayment(double Total, double Received, Appointments App) {
        boolean PaymentDone = false;
        if (Received == Total) { //calculate is there enough
            //JOptionPane.showMessageDialog(rootPane, "Successfully Paid." + "\nPlease Enjoy Your Drinks. ♪(･ω･)ﾉ");             
            write(App, Total);
            PaymentDone = true;
            displaySuccessMessage("Successfully Paid. \nThe payment record is created!");
        } else if (Received > Total) {
            double Change = Received - Total;
            write(App, Total);
            PaymentDone = true;
            displaySuccessMessage("Successfully Paid. \nChange: MYR " + Change + "\nThe payment record is created!");
            //JOptionPane.showMessageDialog(rootPane, "Return Change: MYR " + Change);              
        } else if (Received < Total) {
            double left = Total - Received;
            //JOptionPane.showMessageDialog(null, "Not Enough of money, please try again!", "Error", JOptionPane.ERROR_MESSAGE);
            displayErrorMessage("Not Enough of money, please try again! \nLeft out: MYR " + left);            
        }
        return PaymentDone;
    }
 
    
    public void RemoveInvalidatedPayments() {
        //Used to remove invalidated payments, considered not used as appointments automatically delete themselves upon the
        //absence of a patient or doctor, used as a backup just such an invalidated appointment was not deleted
        //before entering the reports page.
        try {
            for (int i = 0; i < AppointmentData.getAll().size(); i++) {
                Appointments appointment = AppointmentData.getAll().get(i);
                if (appointment.getAppDoctor().getName() == null || appointment.getAppPatient().getPatientName() == null) {
                    delete(GetCurrentPayment(appointment));
                    System.out.println("Missing deleted");
                } 
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    } 
    
    public void write(Appointments App, double price) {
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");                        
            Payment paymentRecord = new Payment(NewPID(), LocalDateTime.now(), price, App);
            PaymentData.create(paymentRecord);
            C_Appointment c_appointment = new C_Appointment();
            App.setStatus("Complete");
            c_appointment.update(App);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Unexpected Error Occurs while create payment record!");
        }
    }

    public ArrayList<Payment> GetAllPayment() {
        ArrayList<Payment> payment = PaymentData.getAll();
        return payment;
    }

    public String NewPID() {
        File tempFile = new File("DataFiles/Payment.txt");
        String NewPID = "P" + String.format("%03d", 1);
        if (tempFile.exists()) {
            ArrayList<Payment> payment = PaymentData.getAll();
            if (payment.size() > 0) {
                Payment LastPayment = payment.get(payment.size() - 1);
                String LastID = LastPayment.getPaymentID().substring(1);
                int pid = Integer.parseInt(LastID) + 1;
                NewPID = "P" + String.format("%03d", pid);
            }
        }

        return NewPID;
    }

    public Payment GetCurrentPayment(Appointments App) {
        ArrayList<Payment> allpayment = GetAllPayment();
        Payment payment = new Payment();
        for (int i = 0; i < allpayment.size(); i++) {
            Payment x = allpayment.get(i);
            if (x.getApp().getAppID() == App.getAppID()) {
               payment = new Payment(x.getPaymentID(), x.getPaymentDate(), x.getPrice(), x.getApp());
               break;
            }
        }
        return payment;
    }
    
    public String PaymentDetails(Appointments App) {        
        Payment payment = GetCurrentPayment(App);
        String msg = "Payment ID: " + payment.getPaymentID() + "\nAmount: MYR " + payment.getPrice() + "\nPaymentDate: " + payment.getPaymentDate();
        return msg;
    }
}
