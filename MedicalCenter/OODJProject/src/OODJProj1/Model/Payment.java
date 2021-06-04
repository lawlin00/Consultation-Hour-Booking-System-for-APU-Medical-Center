/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OODJProj1.Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author User
 */
public class Payment extends BaseModel{
    String PaymentID, line; 
    LocalDateTime PaymentDate;
    double Price; 
    Appointments App;
    
    public Payment(String PaymentID, LocalDateTime PaymentDate, double Price, Appointments App) {
        this.PaymentID = PaymentID;
        this.PaymentDate = PaymentDate;
        this.Price = Price;
        this.App = App;
    }
    
    public Payment() {
        
    }
    
    public String getPaymentID() {
        return PaymentID;
    }

    public LocalDateTime getPaymentDate() {
        return PaymentDate;
    }

    public Appointments getApp() {
        return App;
    }
        
    public double getPrice() {
        return Price;
    }
    
    public String toFileString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String joinLine = String.join(",", PaymentID, String.valueOf(App.getAppID()), String.valueOf(Price), PaymentDate.format(formatter));
        joinLine += System.lineSeparator();//breakline
        //System.out.println(role);
        this.line = joinLine;        
        return joinLine;
    }

}
