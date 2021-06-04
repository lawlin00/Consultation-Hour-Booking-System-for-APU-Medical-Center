/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OODJProj1.View;

import OODJProj1.Controller.C_User;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author User
 */
public class V_DoctorMainMenu extends JFrame implements ActionListener {

    JButton btnIndividualProfile, btnIndividualAppointment, btnPatientManagement, btnGenerateReport,btnLogout;
    JLabel lblTitle, lblIcon,jLabel2;

    public V_DoctorMainMenu() {
        init();
    }

    public void init() {        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200, 800);
        this.setTitle("Home");
        this.setLayout(new MigLayout("insets 0 0 0 0"));
        //this.setResizable(false);
        JPanel container = new JPanel();
        container.setLayout(new MigLayout("insets 0 0 0 0, fill, width 1200px, height 800px"));
        container.setBackground(new Color(230, 255, 251));
        
        //JPanel TitlePanel = new JPanel();
        //TitlePanel.setLayout(new MigLayout());
        lblTitle = new JLabel("APU - Medical Centre");
        lblTitle.setFont(new Font("Jokerman", Font.BOLD, 56));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setForeground(new Color(255, 176, 102));        
        //TitlePanel.add(lblTitle, "span");        
        
        JPanel SelectionPanel = new JPanel();
        SelectionPanel.setLayout(new MigLayout(" insets 2 2 2 2 , fillx,width 1200px, height 800px"));
        SelectionPanel.setBackground(new Color(230, 255, 251));
        btnIndividualProfile = new JButton("My Profile");
        btnIndividualAppointment = new JButton("My Appointment");                
        btnPatientManagement = new JButton("Patient Management");
        btnLogout = new JButton("Logout");     
        
        btnIndividualProfile.setFont(new Font("Stika Heading", Font.BOLD, 28));
        btnIndividualProfile.setHorizontalAlignment(SwingConstants.CENTER);
        btnIndividualProfile.addActionListener(this);
        
        btnPatientManagement.setFont(new Font("Stika Heading", Font.BOLD, 28));
        btnPatientManagement.setHorizontalAlignment(SwingConstants.CENTER);
        btnPatientManagement.addActionListener(this);
        
        btnIndividualAppointment.setFont(new Font("Stika Heading", Font.BOLD, 28));
        btnIndividualAppointment.setHorizontalAlignment(SwingConstants.CENTER);
        btnIndividualAppointment.addActionListener(this);        
        
        btnLogout.setFont(new Font("Stika Heading", Font.BOLD, 28));
        btnLogout.setHorizontalAlignment(SwingConstants.CENTER);
        btnLogout.addActionListener(this);
        
        SelectionPanel.add(btnIndividualProfile, "wrap, span, center, gapy 10 10, width 400:70:400");
        SelectionPanel.add(btnPatientManagement, "wrap, span, center,gapy 10 10, width 400:70:400");
        SelectionPanel.add(btnIndividualAppointment, "wrap, span, center,gapy 10 10, width 400:70:400");        
        SelectionPanel.add(btnLogout, "wrap, span, center,gapy 10 10, width 400:70:400");
        

        container.add(lblTitle, "dock north, growx, pushx, growy, pushy, span, gapy 100");
        container.add(SelectionPanel,"dock south, growx, pushx, growy, pushy, span, gapy 100 50");        
        this.add(container,"dock center");
        this.pack();
    }

    public void actionPerformed(ActionEvent e) {
        //Logout
        if (e.getSource() == btnLogout){
            C_User c_user = new C_User();
            if(c_user.Logout()){
                JOptionPane.showMessageDialog(this, "Logout Successfully!");
                this.setVisible(false);
                OODJTest2.Login.setVisible(true);
            }
        }//User Account Management page
        else if(e.getSource() == btnIndividualProfile){
            C_User c_user = new C_User();
            this.setVisible(false);             
            V_IndividualProfile IndividualProfile = new V_IndividualProfile(c_user);
            IndividualProfile.setVisible(true);         
            
        }else if(e.getSource() == btnPatientManagement){
            this.setVisible(false);
            OODJTest2.DoctorPatientView.setVisible(true);
        }else if(e.getSource() == btnIndividualAppointment){
            V_DoctorAppointment DoctorAppointment = new V_DoctorAppointment(OODJTest2.AppController);
            DoctorAppointment.setVisible(true);
            this.setVisible(false);
        }
    }
    
}
