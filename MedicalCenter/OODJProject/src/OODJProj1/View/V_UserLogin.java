/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OODJProj1.View;

import OODJProj1.Controller.C_User;
import java.awt.Color;
import net.miginfocom.swing.MigLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

/**
 *
 * @author User
 */
public class V_UserLogin extends JFrame implements ActionListener{    
    private JLabel lblUsername = new JLabel("Username: ");
    private JTextField txtUsername = new JTextField(20);
    private JLabel lblPassword = new JLabel("Password: ");
    private JLabel lblLoginTitle = new JLabel("Login");
    private JLabel IconTitle = new JLabel("Staff Login");
    private JPasswordField txtPassword = new JPasswordField(20);
    private JButton btnLogin = new JButton("Login");
    private JButton btnForgetPwd = new JButton("Forget Password");
    private JLabel lblIcon = new JLabel("");
    private C_User c_user;
    
    public V_UserLogin(C_User userController){  
       c_user = userController;
       init();
       
       
    }
    
    public void init(){
        JPanel LoginPanel = new JPanel();
        JPanel LoginInputPanel = new JPanel();
        JPanel container = new JPanel();   
        JPanel IconPanel = new JPanel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200, 800);
        this.setTitle("Login");
        this.setLayout(new MigLayout("insets 0 0 0 0,"));
        container.setLayout(new MigLayout("insets 0 0 0 0, fill, width 1200px, height 800px", "2[]2[]2[]2", "5[]5[]5[]5"));
        IconPanel.setLayout(new MigLayout("insets 80 20 20 20 , fillx, width 600px, height 800px" ));
        LoginPanel.setLayout(new MigLayout("insets 150 20 20 20 , fillx,width 600px, height 800px","[:500, grow, center][grow, left]", "[center]"));                
        LoginPanel.setBackground(new Color(204,255,255));        
        //LoginInputPanel.setLayout(new MigLayout("insets 80 20 20 20, fillx,width 600px, height 600px"));        
        //LoginInputPanel.setBackground(new Color(204,255,255)); 
        ImageIcon iconProfile = new ImageIcon("Images/Login.png");      
        Image image = iconProfile.getImage();
        image = image.getScaledInstance(600,600,Image.SCALE_SMOOTH);
        iconProfile = new ImageIcon(image); 
        lblIcon.setIcon(iconProfile);
        lblUsername.setFont(new Font("STXihei", 1, 32));
        lblPassword.setFont(new Font("STXihei", 1, 32));
        lblLoginTitle.setFont(new Font("Sylfaen", Font.BOLD, 48));
        lblLoginTitle.setHorizontalAlignment(SwingConstants.CENTER);
        IconTitle.setFont(new Font("Sylfaen", 1, 48));
        IconTitle.setForeground(new Color(255, 255, 255));
        txtUsername.setFont(new Font ("Sylfaen", 1, 34));
        txtPassword.setFont(new Font ("Sylfaen", 1, 34));
        //Border border = BorderFactory.createLineBorder(Color.BLUE, 5);
        btnLogin.setFont(new Font("Stika Heading", Font.BOLD, 30));
        btnLogin.setHorizontalAlignment(SwingConstants.CENTER);
        //btnLogin.setBackground(new Color(255, 204, 204));
        btnForgetPwd.setFont(new Font("Stika Heading", Font.BOLD, 30));
        btnForgetPwd.setHorizontalAlignment(SwingConstants.CENTER);
        //btnForgetPwd.setBackground(new Color(255, 204, 204));
        //lblIcon.setBorder(border);
        container.setBackground(new Color(204,204,255));   
        IconPanel.setBackground(new Color(204,204,255));   
        IconPanel.add(lblIcon, "center, wrap");
        IconPanel.add(IconTitle, "center");
        
        //LoginPanel.setBorder(border);
        LoginPanel.add(lblLoginTitle,"wrap, center, span, pushx, growx, gapy 15 15");
        LoginPanel.add(lblUsername,"wrap, span, pushx, growx, gapy 8 8");
        LoginPanel.add(txtUsername, "wrap, span, pushx, growx, gapy 8 8");
        LoginPanel.add(lblPassword,"wrap, span, pushx, growx, gapy 8 8");
        LoginPanel.add(txtPassword,"wrap, span, pushx, growx, gapy 8 8");
        LoginPanel.add(btnLogin, "span, split 2, gapy 8 8,gapx 25 25, width 300:70:300");
        //LoginPanel.add(btnForgetPwd, "span, split 2, gapy 8 8,gapx 25 25, width 300:70:300");
        //LoginPanel.add(LoginInputPanel, "center,pushx, pushy,growx, growy");
        container.add(IconPanel," dock west, pushx, pushy,growx, growy, align 50% 50%");
        container.add(LoginPanel,"dock east, pushx, pushy,growx, growy");
        
        
        btnLogin.addActionListener(this);
        
        this.add(container,"dock center");
        this.pack();
        
    }
    
    public String getUsername(){
        return txtUsername.getText();
    }
    
    public String getPassword(){
        return String.valueOf(txtPassword.getPassword());
    }       
    
    public void displayErrorMessage(String errorMessage){
        JOptionPane.showMessageDialog(this, errorMessage);
    }
    
    public void displaySuccessMessage(String SuccessMessage){
         JOptionPane.showMessageDialog(this, SuccessMessage);
    }
    
     public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLogin) {
            String username = getUsername();
            String pwd = getPassword();             
            boolean valid = c_user.Login(username, pwd);
            if(valid){
                displaySuccessMessage("Login Successfully. ");
                txtUsername.setText("");
                txtPassword.setText("");
                boolean isAdmin = C_User.login.getIsadmin();
                if(isAdmin){
                    OODJTest2.AdminMainMenu.setVisible(true);
                }else{
                    OODJTest2.DoctorMainMenu.setVisible(true);
                }        
                this.setVisible(false);
            }else{
                displayErrorMessage("Wrong username or password, please try again!");
            }
        }
    }
        
}
