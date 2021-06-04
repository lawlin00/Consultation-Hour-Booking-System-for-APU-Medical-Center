/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OODJProj1.View;

import OODJProj1.Controller.C_Payment;
import OODJProj1.Model.Appointments;
import OODJProj1.Model.Patients;
import OODJProj1.Others.Validation;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author User
 */
public class V_Payment_EnterAmount extends JFrame implements ActionListener{
    JLabel lblTitle,lblIcon,lblTotalAmount,lblReturn,lblReceived,lblCurrency;
    JTextField txtReturn;
    JFormattedTextField txtAmountReceived;
    JButton btnProceed,btnCancel;
    private double totalAmt;
    Appointments currentApp;
    public V_Payment_EnterAmount(Appointments App, double price){
        currentApp = App;
        init();
        DecimalFormat df = new DecimalFormat("####0.00");
        Patients patientInfo = App.getAppPatient();   
        totalAmt = price;
        lblTotalAmount.setText("Total amount is MYR " + df.format(totalAmt));
        System.out.println(totalAmt + " app " + App.getAppID());        
        //txtPatientName.setText(patientInfo.getPatientName());        
    }
    
    public void init() {        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200, 800);
        this.setTitle("Payment");
        this.setLayout(new MigLayout("insets 0 0 0 0"));
        //this.setResizable(false);
        JPanel container = new JPanel();
        container.setLayout(new MigLayout("insets 0 0 0 0, fill, width 1200px, height 800px"));
        container.setBackground(new Color(230, 255, 251));
        
        //JPanel TitlePanel = new JPanel();
        //TitlePanel.setLayout(new MigLayout());
        lblTitle = new JLabel("Please enter the amount received from the patient");
        lblTitle.setFont(new Font("STXihei", Font.BOLD, 32));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        //lblTitle.setForeground(new Color(255, 176, 102));        
        //TitlePanel.add(lblTitle, "span");        
        lblTotalAmount = new JLabel("Total Amount: ");
        lblTotalAmount.setFont(new Font("STXihei", 1, 24));
        
        lblReceived = new JLabel("Amount Received: ");
        lblReceived.setFont(new Font("STXihei", 1, 24));
        
        lblCurrency = new JLabel("MYR ");
        lblCurrency.setFont(new Font("STXihei", 1, 24));       
                
        txtAmountReceived = new JFormattedTextField();
        txtAmountReceived.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("#0.00"))));  
        txtAmountReceived.setFont(new Font("STXihei", 1, 24));                
        
//        txtPatientName = new JTextField(20);
//        txtPatientName.setFont(new Font("STXihei", 1, 24));
//        txtPatientName.setEditable(false);
//        
//        txtPrice = new JFormattedTextField();
//        txtPrice.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("#0.00"))));        
//        txtPrice.setFont(new Font("STXihei", 1, 24));
        
        btnProceed = new JButton("Proceed");
        btnProceed.setFont(new Font("Stika Heading", Font.BOLD, 28));
        btnProceed.setHorizontalAlignment(SwingConstants.CENTER);
        btnProceed.addActionListener(this);
        btnProceed.setBackground(new Color(181, 255, 166));
        
        btnCancel = new JButton("Cancel");
        btnCancel.setFont(new Font("Stika Heading", Font.BOLD, 28));
        btnCancel.setHorizontalAlignment(SwingConstants.CENTER);
        btnCancel.addActionListener(this);
        btnCancel.setBackground(new Color(255, 153, 153));
        
        JPanel PaymentDetails = new JPanel();
        PaymentDetails.setLayout(new MigLayout(" insets 2 2 2 2, fillx,width 1200px, height 800px"));
        PaymentDetails.setBackground(new Color(230, 255, 251));
        
        lblIcon = new JLabel();
        ImageIcon PaymentIcon = new ImageIcon("Images/Payment.png");      
        Image image = PaymentIcon.getImage();
        image = image.getScaledInstance(300,300,Image.SCALE_SMOOTH);
        PaymentIcon = new ImageIcon(image); 
        lblIcon.setIcon(PaymentIcon);
        
        PaymentDetails.add(lblIcon, "center, wrap,span");        
        PaymentDetails.add(lblTotalAmount, "split 2, span, center,wrap, gapy 10 10, width 400:70:400");
        //PaymentDetails.add(txtPatientName, "split 2, span,wrap, center, gapy 10 10, width 400:70:400");      
        PaymentDetails.add(lblReceived, "split 3, span, center, gapy 10 0, width 250:70:250");
        PaymentDetails.add(lblCurrency,"split 3, center, gapy 10 0, width 70:70:70");
        PaymentDetails.add(txtAmountReceived, "split 3, span,wrap, center, gapy 10 0, width 330:70:330");
        //PaymentDetails.add(lblPriceNote, "cell 2 5, span, wrap, center, gapy 0 10, width 400:70:400");
        PaymentDetails.add(btnCancel, "split 2, span, center, gapy 10 10, width 400:70:400");   
        PaymentDetails.add(btnProceed, "split 2, span, center, gapy 10 10, width 400:70:400");        
                 
        
        container.add(lblTitle, "dock north, growx, pushx, growy, pushy, span, gapy 100 25");
        container.add(PaymentDetails,"dock south, growx, pushx, growy, pushy, span, gapy 25 50");        
        this.add(container,"dock center");
        this.pack();
    }
    
    public void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void displaySuccessMessage(String successMessage) {
        JOptionPane.showMessageDialog(this, successMessage, "Alert", JOptionPane.WARNING_MESSAGE);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnCancel){
            this.setVisible(false);
            new V_DoctorAppointment(OODJTest2.AppController).setVisible(true);
        }else if(e.getSource() == btnProceed){            
            Validation validation = new Validation();
            boolean isPrice = validation.isPrice(txtAmountReceived.getText());
            if (isPrice){
                C_Payment c_payment = new C_Payment();
                double AmountReceived = Double.parseDouble(txtAmountReceived.getText());                
                if(c_payment.CheckPayment(totalAmt, AmountReceived, currentApp)){
                    new V_DoctorAppointment(OODJTest2.AppController).setVisible(true);
                     dispose();
                }               
            }else{
                JOptionPane.showMessageDialog(this, "Please enter a valid price.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
