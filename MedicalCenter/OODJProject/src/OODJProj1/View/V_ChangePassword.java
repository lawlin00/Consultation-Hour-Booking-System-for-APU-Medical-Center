/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OODJProj1.View;

import OODJProj1.Controller.C_User;
import OODJProj1.Model.Users;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author User
 */
public class V_ChangePassword extends JFrame implements ActionListener {

    JPasswordField txtOldPwd, txtNewPwd, txtRetypePwd;
    JLabel lblOldPwd, lblNewPwd, lblRetypePwd, lblTitle;
    JButton btnChange, btnCancel;
    private final C_User c_user;
    Users loginUser = C_User.login;

    public V_ChangePassword(C_User userController) {
        c_user = userController;
        initComponent();
    }

    private void initComponent() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200, 800);
        this.setTitle("Edit Profile");
        this.setLayout(new MigLayout("insets 0 0 0 0"));
        JPanel container = new JPanel();
        container.setLayout(new MigLayout("insets 0 0 0 0, fill, width 1200px, height 800px"));
        container.setBackground(new Color(230, 255, 251));

        lblTitle = new JLabel("Edit Profile");
        lblTitle.setFont(new Font("Jokerman", Font.BOLD, 56));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setForeground(new Color(169, 71, 255));

        JPanel PasswordPanel = new JPanel();
        PasswordPanel.setLayout(new MigLayout(" insets 2 2 2 2, fillx,width 1200px, height 800px"));
        PasswordPanel.setBackground(new Color(230, 255, 251));
        btnChange = new JButton("Change Password");
        btnCancel = new JButton("Cancel");

        btnChange.setFont(new Font("Stika Heading", Font.BOLD, 28));
        btnChange.setHorizontalAlignment(SwingConstants.CENTER);
        btnChange.addActionListener(this);
        btnChange.setBackground(new Color(181, 255, 166));

        btnCancel.setFont(new Font("Stika Heading", Font.BOLD, 28));
        btnCancel.setHorizontalAlignment(SwingConstants.CENTER);
        btnCancel.addActionListener(this);
        btnCancel.setBackground(new Color(255, 153, 153));

        txtOldPwd = new JPasswordField(20);
        txtOldPwd.setFont(new Font("STXihei", 1, 24));

        txtNewPwd = new JPasswordField(20);
        txtNewPwd.setFont(new Font("STXihei", 1, 24));

        txtRetypePwd = new JPasswordField(20);
        txtRetypePwd.setFont(new Font("STXihei", 1, 24));

        lblOldPwd = new JLabel("Old Password: ");
        lblOldPwd.setFont(new Font("STXihei", 1, 24));

        lblNewPwd = new JLabel("New Password: ");
        lblNewPwd.setFont(new Font("STXihei", 1, 24));

        lblRetypePwd = new JLabel("Retype Password: ");
        lblRetypePwd.setFont(new Font("STXihei", 1, 24));

        PasswordPanel.add(lblOldPwd, "split 2, span, center, gapy 10 10, width 250:70:250");
        PasswordPanel.add(txtOldPwd, "split 2, span,wrap, center, gapy 10 10, width 400:70:400");
        PasswordPanel.add(lblNewPwd, "split 2, span, center, gapy 10 10, width 250:70:250");
        PasswordPanel.add(txtNewPwd, "split 2, span,wrap, center, gapy 10 10, width 400:70:400");
        PasswordPanel.add(lblRetypePwd, "split 2, span, center, gapy 10 10, width 250:70:250");
        PasswordPanel.add(txtRetypePwd, "split 2, span,wrap, center, gapy 10 10, width 400:70:400");
        PasswordPanel.add(btnChange, "split 2, span, center, gapy 10 10, width 400:70:400");
        PasswordPanel.add(btnCancel, "split 2, span, center, gapy 10 10, width 400:70:400");

        container.add(lblTitle, "dock north, growx, pushx, growy, pushy, span, gapy 100");
        container.add(PasswordPanel, "dock south, growx, pushx, growy, pushy, span, gapy 100 50");

        this.add(container, "dock center");
        this.pack();
    }      

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnCancel) {
            int dialogResult = JOptionPane.showConfirmDialog(this, "Do you want to exit without change password?", "Cancel?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (dialogResult == JOptionPane.YES_OPTION) {
                this.setVisible(false);
               OODJTest2.DoctorMainMenu.setVisible(true);
            }                        
        }
        else if (e.getSource() == btnChange) {
            int dialogResult = JOptionPane.showConfirmDialog(this, "Do you want to proceed?", "Change Password?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (dialogResult == JOptionPane.YES_OPTION) {
                boolean PwdValid = false;
                String msg = c_user.ChangePwd(String.valueOf(txtOldPwd.getPassword()), String.valueOf(txtNewPwd.getPassword()), String.valueOf(txtRetypePwd.getPassword()));
                if(msg == ""){
                    PwdValid = true;
                }                
                if (PwdValid) {
                    Users editedUserInfo = new Users(loginUser.getUid(), loginUser.getName(), loginUser.getUsername(), String.valueOf(txtNewPwd.getPassword()),
                            loginUser.getIsadmin(), loginUser.getSelfdescription());
                    boolean edited = c_user.edit(editedUserInfo);
                    if (edited) {
                        JOptionPane.showMessageDialog(this, "Password Changed Successfully", "Error", JOptionPane.WARNING_MESSAGE);
                        this.setVisible(false);
                        V_IndividualProfile IndividualProfile = new V_IndividualProfile(c_user);
                        IndividualProfile.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(this, "Unexpected Error Occurs!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }else{                    
                    JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
                }

            }           

        }
    }
}
