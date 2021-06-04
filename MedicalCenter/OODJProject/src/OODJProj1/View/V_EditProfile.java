/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OODJProj1.View;

import OODJProj1.Controller.C_User;
import OODJProj1.Model.Users;
import OODJProj1.Others.Validation_User;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class V_EditProfile extends JFrame implements ActionListener {

    JTextField txtUsername, txtName;
    JTextArea txtDesc;
    JLabel lblUsername, lblDesc, lblName, lblTitle, lbldescnote;
    JButton btnModify, btnBack;
    private final C_User c_user;
    Users loginUser = C_User.login;

    public V_EditProfile(C_User userController) {
        c_user = userController;
        initComponent();
        fill();
    }

    private void initComponent() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200, 800);
        this.setTitle("Edit Profile");
        this.setLayout(new MigLayout("insets 0 0 0 0"));
        //this.setResizable(false);
        JPanel container = new JPanel();
        container.setLayout(new MigLayout("insets 0 0 0 0, fill, width 1200px, height 800px"));
        container.setBackground(new Color(230, 255, 251));

        lblTitle = new JLabel("Edit Profile");
        lblTitle.setFont(new Font("Jokerman", Font.BOLD, 56));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setForeground(new Color(169, 71, 255));

        JPanel ProfileDetailPanel = new JPanel();
        ProfileDetailPanel.setLayout(new MigLayout(" insets 2 2 2 2 , fillx,width 1200px, height 800px"));
        ProfileDetailPanel.setBackground(new Color(230, 255, 251));
        btnModify = new JButton("Save");
        btnBack = new JButton("Cancel");        

        btnModify.setFont(new Font("Stika Heading", Font.BOLD, 28));
        btnModify.setHorizontalAlignment(SwingConstants.CENTER);
        btnModify.addActionListener(this);
        btnModify.setBackground(new Color(181, 255, 166));

        btnBack.setFont(new Font("Stika Heading", Font.BOLD, 28));
        btnBack.setHorizontalAlignment(SwingConstants.CENTER);
        btnBack.addActionListener(this);
        btnBack.setBackground(new Color(255, 153, 153));

        txtUsername = new JTextField(20);
        txtUsername.setFont(new Font("STXihei", 1, 24));

        txtName = new JTextField(20);
        txtName.setFont(new Font("STXihei", 1, 24));

        txtDesc = new JTextArea(5, 20);
        JScrollPane scrollPane = new JScrollPane(txtDesc);
        txtDesc.setFont(new Font("STXihei", 1, 24));

        lblUsername = new JLabel("Username: ");
        lblUsername.setFont(new Font("STXihei", 1, 24));

        lblDesc = new JLabel("Description: ");
        lblDesc.setFont(new Font("STXihei", 1, 24));

        lblName = new JLabel("Name: ");
        lblName.setFont(new Font("STXihei", 1, 24));

        lbldescnote = new JLabel("*Please enter your bio informaiton such as specialist and awards!");
        lbldescnote.setFont(new Font("STXihei", 1, 12));
        lbldescnote.setForeground(new Color(255, 59, 59));

        ProfileDetailPanel.add(lblUsername, "split 2, span, center, gapy 10 10, width 180:70:180");
        ProfileDetailPanel.add(txtUsername, "split 2, span,wrap, center, gapy 10 10, width 400:70:400");
        ProfileDetailPanel.add(lblName, "split 2, span, center, gapy 10 10, width 180:70:180");
        ProfileDetailPanel.add(txtName, "split 2, span,wrap, center, gapy 10 10, width 400:70:400");
        ProfileDetailPanel.add(lbldescnote, "split, span, wrap, center, gapy 10 10, width 580:70:580");
        ProfileDetailPanel.add(lblDesc, "split 2, span, center, gapy 10 10, width 180:70:180");
        ProfileDetailPanel.add(txtDesc, "split 2, span,wrap, center, gapy 10 10, width 400:70:400");
        ProfileDetailPanel.add(btnModify, "split 2, span, center, gapy 10 10, width 400:70:400");        
        ProfileDetailPanel.add(btnBack, "split 2, span, center, gapy 10 10, width 400:70:400");

        container.add(lblTitle, "dock north, growx, pushx, growy, pushy, span, gapy 100");
        container.add(ProfileDetailPanel, "dock south, growx, pushx, growy, pushy, span, gapy 100 50");

        this.add(container, "dock center");
        this.pack();
    }

    private void fill() {

        String uid = String.valueOf(loginUser.getUid());
        if (uid != "") {
            Users userInfo = c_user.getModel(uid);
            txtUsername.setText(userInfo.getUsername());
            txtName.setText(userInfo.getName());
            txtDesc.setText(userInfo.getSelfdescription());            
        } else {
            JOptionPane.showMessageDialog(this, "Unexpected Error Occurs!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public boolean UserValidation(String username, String name, String desc) {
        boolean valid = false;
        boolean required = false;
        Validation_User validation = new Validation_User();
        String errormessage = validation.UserValidation(username, name, loginUser.getPassword(), desc);
        if (errormessage.length() == 0) {
            valid = true;
        } else {                        
            JOptionPane.showMessageDialog(this, errormessage, "Error", JOptionPane.ERROR_MESSAGE);
        }
        return valid;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnModify) {
            boolean edited = false;
            int dialogResult = JOptionPane.showConfirmDialog(this, "Do you want to proceed?", "Proceed?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (dialogResult == JOptionPane.YES_OPTION) {                
                boolean valid = UserValidation( txtUsername.getText(), txtName.getText(),txtDesc.getText());
                if(valid){
                    Users editedUserInfo = new Users(loginUser.getUid(), txtName.getText(), txtUsername.getText(), loginUser.getPassword(),
                        loginUser.getIsadmin(), txtDesc.getText());
                    edited = c_user.edit(editedUserInfo);
                    if (edited) {
                    JOptionPane.showMessageDialog(this, "Edited Successfully", "Alert", JOptionPane.WARNING_MESSAGE);
                    this.setVisible(false);
                    V_IndividualProfile IndividualProfile = new V_IndividualProfile(c_user);
                    IndividualProfile.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "The Username is exist! Please try another username. ", "Error", JOptionPane.WARNING_MESSAGE);
                }
                }                
                
            }
        } //Cancel
        else if (e.getSource() == btnBack) {
            int dialogResult = JOptionPane.showConfirmDialog(this, "Do you want to exit without save?", "Cancel?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (dialogResult == JOptionPane.YES_OPTION) {
                this.setVisible(false);
               OODJTest2.DoctorMainMenu.setVisible(true);
            }
        }
    }

}
