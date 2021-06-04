/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OODJProj1.View;

import OODJProj1.Model.Appointments;
import OODJProj1.Model.Patients;
import OODJProj1.Others.Validation;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
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
public class V_Payment_SetPrice extends JFrame implements ActionListener {
    JLabel lblTitle, lblPatientName, lblAppointmentID, lblPrice, lblPriceNote,lblCurrency; 
    JTextField txtPatientName, txtAppointmentID;
    JFormattedTextField txtPrice;
    JButton btnProceed,btnCancel;
    private Appointments App;
    
    public V_Payment_SetPrice(Appointments AppID){
       // c_appointment = AppointmentController;
        App = AppID;
        init();
        Patients patientInfo = App.getAppPatient();        
        txtAppointmentID.setText(String.valueOf(AppID.getAppID()));
        txtPatientName.setText(patientInfo.getPatientName());
        
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
        lblTitle = new JLabel("Payment Details");
        lblTitle.setFont(new Font("Jokerman", Font.BOLD, 56));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setForeground(new Color(255, 176, 102));        
        //TitlePanel.add(lblTitle, "span");        
        lblAppointmentID = new JLabel("Appointment ID: ");
        lblAppointmentID.setFont(new Font("STXihei", 1, 24));
        
        lblPatientName = new JLabel("Patient Name: ");
        lblPatientName.setFont(new Font("STXihei", 1, 24));
        
        lblPrice = new JLabel("Price: ");
        lblPrice.setFont(new Font("STXihei", 1, 24));
        
        lblCurrency = new JLabel("MYR ");
        lblCurrency.setFont(new Font("STXihei", 1, 24));
        
        lblPriceNote = new JLabel("*Please enter the price of this appointment.");
        lblPriceNote.setFont(new Font("STXihei", 1, 12));
        lblPriceNote.setForeground(new Color(255, 59, 59));
                
        txtAppointmentID = new JTextField(20);
        txtAppointmentID.setFont(new Font("STXihei", 1, 24));
        txtAppointmentID.setEditable(false);        
        
        txtPatientName = new JTextField(20);
        txtPatientName.setFont(new Font("STXihei", 1, 24));
        txtPatientName.setEditable(false);
        
        txtPrice = new JFormattedTextField();
        txtPrice.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("#0.00"))));        
        txtPrice.setFont(new Font("STXihei", 1, 24));
        
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
        PaymentDetails.setLayout(new MigLayout(" insets 2 2 2 2 , fillx,width 1200px, height 800px"));
        PaymentDetails.setBackground(new Color(230, 255, 251));
        
        PaymentDetails.add(lblAppointmentID, "split 2, span, center, gapy 10 10, width 220:70:220");
        PaymentDetails.add(txtAppointmentID, "split 2, span,wrap, center, gapy 10 10, width 400:70:400");
        PaymentDetails.add(lblPatientName, "split 2, span, center, gapy 10 10, width 220:70:220");
        PaymentDetails.add(txtPatientName, "split 2, span,wrap, center, gapy 10 10, width 400:70:400");      
        PaymentDetails.add(lblPrice, "split 3, span, center, gapy 10 0, width 220:70:220");
        PaymentDetails.add(lblCurrency,"split 3, center, gapy 10 0, width 70:70:70");
        PaymentDetails.add(txtPrice, "split 3, span,wrap, center, gapy 10 0, width 330:70:330");
        PaymentDetails.add(lblPriceNote, "cell 2 5, span, wrap, center, gapy 0 10, width 400:70:400");
        PaymentDetails.add(btnCancel, "split 2, span, center, gapy 10 10, width 400:70:400"); 
        PaymentDetails.add(btnProceed, "split 2, span, center, gapy 10 10, width 400:70:400");        
                   
        
        container.add(lblTitle, "dock north, growx, pushx, growy, pushy, span, gapy 100");
        container.add(PaymentDetails,"dock south, growx, pushx, growy, pushy, span, gapy 100 50");        
        this.add(container,"dock center");
        this.pack();
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnCancel){
            dispose();
            new V_DoctorAppointment(OODJTest2.AppController).setVisible(true);
        }else if(e.getSource() == btnProceed){            
            Validation validation = new Validation();
            boolean isPrice = validation.isPrice(txtPrice.getText());
            if (isPrice){
                dispose();
                new V_Payment_EnterAmount(App, Double.parseDouble(txtPrice.getText())).setVisible(true);
            }else{
                JOptionPane.showMessageDialog(this, "Please enter a valid price.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
