/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OODJProj1.View;

import OODJProj1.Controller.C_User;
import OODJProj1.Controller.C_User;
import OODJProj1.Model.Users;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author User
 */
public class V_IndividualProfile extends JFrame implements ActionListener {

    JTextField txtUsername, txtName;
    JTextArea txtDesc;
    JLabel lblUsername, lblDesc, lblName, lblTitle;
    JButton btnEditProfile, btnBack, btnChangePassword;
    private final C_User c_user;

    public V_IndividualProfile(C_User userController) {
        c_user = userController;
        initComponent();
        fillTextField();
        //int uid = C_User.login.getUid();
        //fillTextField(c_user.getModel(String.valueOf(uid)));
    }

    private void initComponent() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200, 800);
        this.setTitle("My Profile");
        this.setLayout(new MigLayout("insets 0 0 0 0"));
        //this.setResizable(false);
        JPanel container = new JPanel();
        container.setLayout(new MigLayout("insets 0 0 0 0, fill, width 1200px, height 800px"));
        container.setBackground(new Color(230, 255, 251));

        lblTitle = new JLabel("My Profile");
        lblTitle.setFont(new Font("Jokerman", Font.BOLD, 56));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setForeground(new Color(255, 176, 102));

        JPanel ProfileDetailPanel = new JPanel();
        ProfileDetailPanel.setLayout(new MigLayout(" insets 2 2 2 2 , fillx,width 1200px, height 800px"));
        ProfileDetailPanel.setBackground(new Color(230, 255, 251));
        btnEditProfile = new JButton("Edit my profile");
        btnBack = new JButton("Back to menu");
        btnChangePassword = new JButton("Change Password");

        btnEditProfile.setFont(new Font("Stika Heading", Font.BOLD, 28));
        btnEditProfile.setHorizontalAlignment(SwingConstants.CENTER);
        btnEditProfile.addActionListener(this);        

        btnBack.setFont(new Font("Stika Heading", Font.BOLD, 28));
        btnBack.setHorizontalAlignment(SwingConstants.CENTER);
        btnBack.addActionListener(this);        

        btnChangePassword.setFont(new Font("Stika Heading", Font.BOLD, 28));
        btnChangePassword.setHorizontalAlignment(SwingConstants.CENTER);
        btnChangePassword.addActionListener(this);        

        txtUsername = new JTextField(20);
        txtUsername.setFont(new Font("STXihei", 1, 24));
        txtUsername.setEditable(false);

        txtName = new JTextField(20);
        txtName.setFont(new Font("STXihei", 1, 24));
        txtName.setEditable(false);

        txtDesc = new JTextArea(5, 20);
        JScrollPane scrollPane = new JScrollPane(txtDesc);
        txtDesc.setFont(new Font("STXihei", 1, 24));
        txtDesc.setEditable(false);
        txtDesc.setBackground(new Color(238, 238, 238));

        lblUsername = new JLabel("Username: ");
        lblUsername.setFont(new Font("STXihei", 1, 24));

        lblDesc = new JLabel("Description: ");
        lblDesc.setFont(new Font("STXihei", 1, 24));

        lblName = new JLabel("Name: ");
        lblName.setFont(new Font("STXihei", 1, 24));

        ProfileDetailPanel.add(lblUsername, "split 2, span, center, gapy 10 10, width 180:70:180");
        ProfileDetailPanel.add(txtUsername, "split 2, span,wrap, center, gapy 10 10, width 400:70:400");
        ProfileDetailPanel.add(lblName, "split 2, span, center, gapy 10 10, width 180:70:180");
        ProfileDetailPanel.add(txtName, "split 2, span,wrap, center, gapy 10 10, width 400:70:400");
        ProfileDetailPanel.add(lblDesc, "split 2, span, center, gapy 10 10, width 180:70:180");
        ProfileDetailPanel.add(txtDesc, "split 2, span,wrap, center, gapy 10 10, width 400:70:400");
        ProfileDetailPanel.add(btnEditProfile, "split 2, span, center, gapy 10 10, width 400:70:400");
        ProfileDetailPanel.add(btnChangePassword, "split 2, span,wrap, center, gapy 10 10, width 400:70:400");
        ProfileDetailPanel.add(btnBack, "split 2, span, center, gapy 10 10, width 400:70:400");

        container.add(lblTitle, "dock north, growx, pushx, growy, pushy, span, gapy 100");
        container.add(ProfileDetailPanel, "dock south, growx, pushx, growy, pushy, span, gapy 100 50");

        this.add(container, "dock center");
        this.pack();
    }

    private void fillTextField() {
        Users loginUser = C_User.login;
        if (!Objects.isNull(loginUser)) {
            Users userInfo = c_user.getModel(String.valueOf(loginUser.getUid()));
            txtUsername.setText(userInfo.getUsername());
            txtName.setText(userInfo.getName());
            txtDesc.setText(userInfo.getSelfdescription());
            System.out.println("this method is called " + userInfo.getName());
        } else {
            JOptionPane.showMessageDialog(this, "Unexpected Error Occurs!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnEditProfile) {                                      
            this.setVisible(false);             
            V_EditProfile EditProfile = new V_EditProfile(c_user);
            EditProfile.setVisible(true);             
        }else if(e.getSource() == btnBack){
            this.setVisible(false);
            OODJTest2.DoctorMainMenu.setVisible(true);
        }else if(e.getSource() == btnChangePassword){
            this.setVisible(false);
            V_ChangePassword changepwd = new V_ChangePassword(c_user);
            changepwd.setVisible(true);            
        }
    }

}
