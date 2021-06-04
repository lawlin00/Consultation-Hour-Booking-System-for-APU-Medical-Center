/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OODJProj1.View;


import OODJProj1.Controller.C_User;
import OODJProj1.Model.Users;
import OODJProj1.Others.Validation_User;
import com.github.lgooddatepicker.components.DatePicker;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author User
 */
public class V_UserList extends JFrame implements ActionListener {

    DefaultTableModel model;
    JTable Table;
    JScrollPane scroll;

    JLabel Title, lblCname, lblCusername, lblCpwd, lblCrole, lblCuid, lblCDoctorDesc, lblEname, lblEusername, lblEpwd, lblErole, lblEuid, lblEDoctorDesc, lblFilter;
    JButton Back, refreshTbl, btnCreate, btnEdit, btnDelete, btnSearch;
    JTextField txtName, txtUsername, txtPassword, txtEName, txtEUsername, txtEPassword, txtCuid, txtCDoctorDesc, txtEuid, txtEDoctorDesc, txtSearch;
    JComboBox cmbIsAdmin, cmbEIsAdmin, cmbFilter;
    JTabbedPane tabbedPane;
    int NewID;
    private final C_User c_user;

    static DatePicker beginPicker, endPicker;
    public V_UserList(C_User userController) {
        c_user = userController;
        
        initComponent();
        refreshTable(c_user.getModelList());
    }

    private void initComponent() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200, 800);
        this.setTitle("User Account Management");
        this.setLayout(new MigLayout("insets 0 0 0 0"));
        JPanel container = new JPanel();
        container.setLayout(new MigLayout("insets 0 0 0 0, fill, width 1200px, height 800px", "3[]3[]3[]3", "5[]5[]5[]5"));
        container.setBackground(new Color(255, 225, 203));
        //JLabel and JButton Design
        Title = new JLabel("User Account Management");
        Title.setFont(new Font("Imprint MT Shadow", Font.BOLD, 36));
        Title.setHorizontalAlignment(SwingConstants.CENTER);

        Back = new JButton("Back to Main Menu");
        Back.setFont(new Font("Stika Heading", Font.BOLD, 18));
        Back.setHorizontalAlignment(SwingConstants.CENTER);
        Back.setBackground(new Color(255, 214, 207));

        refreshTbl = new JButton("Refresh Table");
        refreshTbl.setFont(new Font("Stika Heading", Font.BOLD, 18));
        refreshTbl.setHorizontalAlignment(SwingConstants.CENTER);
        refreshTbl.setBackground(new Color(176, 255, 212));

        txtName = new JTextField(20);
        txtName.setFont(new Font("Arial", 1, 14));

        txtUsername = new JTextField(20);
        txtUsername.setFont(new Font("Arial", 1, 14));

        txtPassword = new JTextField(20);
        txtPassword.setFont(new Font("Arial", 1, 14));

        txtCuid = new JTextField(20);
        txtCuid.setEnabled(false);
        txtCuid.setFont(new Font("Arial", 1, 14));

        txtCDoctorDesc = new JTextField(20);
        txtCDoctorDesc.setFont(new Font("Arial", 1, 14));

        cmbIsAdmin = new JComboBox();
        cmbIsAdmin.addItem("Admin");
        cmbIsAdmin.addItem("Doctor");
        cmbIsAdmin.setFont(new Font("Arial", 1, 14));

        lblCname = new JLabel("Name: ");
        lblCname.setFont(new Font("Arial", 1, 14));

        lblCusername = new JLabel("Username: ");
        lblCusername.setFont(new Font("Arial", 1, 14));

        lblCpwd = new JLabel("Password: ");
        lblCpwd.setFont(new Font("Arial", 1, 14));

        lblCrole = new JLabel("Role: ");
        lblCrole.setFont(new Font("Arial", 1, 14));

        lblCuid = new JLabel("User ID: ");
        lblCuid.setFont(new Font("Arial", 1, 14));

        lblCDoctorDesc = new JLabel("User Description: ");
        lblCDoctorDesc.setFont(new Font("Arial", 1, 14));

        btnCreate = new JButton("Create");
//===================================================================//
        txtEName = new JTextField(20);
        txtEName.setFont(new Font("Arial", 1, 14));

        txtEUsername = new JTextField(20);
        txtEUsername.setFont(new Font("Arial", 1, 14));

        txtEPassword = new JTextField(20);
        txtEPassword.setFont(new Font("Arial", 1, 14));

        txtEuid = new JTextField(20);
        txtEuid.setFont(new Font("Arial", 1, 14));
        txtEuid.setEnabled(false);

        txtEDoctorDesc = new JTextField(20);
        txtEDoctorDesc.setFont(new Font("Arial", 1, 14));

        cmbEIsAdmin = new JComboBox();
        cmbEIsAdmin.addItem("Admin");
        cmbEIsAdmin.addItem("Doctor");
        cmbEIsAdmin.setFont(new Font("Arial", 1, 14));

        lblEuid = new JLabel("User ID: ");
        lblEuid.setFont(new Font("Arial", 1, 14));

        lblEname = new JLabel("Name: ");
        lblEname.setFont(new Font("Arial", 1, 14));

        lblEusername = new JLabel("Username: ");
        lblEusername.setFont(new Font("Arial", 1, 14));

        lblEpwd = new JLabel("Password: ");
        lblEpwd.setFont(new Font("Arial", 1, 14));

        lblErole = new JLabel("Role: ");
        lblErole.setFont(new Font("Arial", 1, 14));

        lblEDoctorDesc = new JLabel("Doctor Description");
        lblEDoctorDesc.setFont(new Font("Arial", 1, 14));

        btnEdit = new JButton("Edit");
        btnDelete = new JButton("Delete");
//====================================================================//
        JPanel FilterPanel = new JPanel(new MigLayout("inset 0 0 0 0"));
        lblFilter = new JLabel("Filter: ");
        lblFilter.setFont(new Font("Times New Roman", Font.BOLD, 16));
        cmbFilter = new JComboBox();
        cmbFilter.setFont(new Font("Times New Roman", 1, 16));
        cmbFilter.addItem("Admin and Doctor");
        cmbFilter.addItem("Admin");
        cmbFilter.addItem("Doctor");
        FilterPanel.add(lblFilter, "gapx 15 15, split 2, span");
        FilterPanel.add(cmbFilter, "gapx 5 0,split 2, span, growx, pushx, span");
        FilterPanel.setBackground(new Color(255, 225, 203));

        //UserListPanel with table Design
        JPanel UserListPanel = new JPanel();
        UserListPanel.setBackground(new Color(255, 225, 203));
        UserListPanel.setLayout(new MigLayout(" insets 2 2 2 2 , fillx, ,width 800px, height 800px"));
        Table = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        scroll = new JScrollPane(Table);
        Table.setFocusable(false);
        Table.setRowSelectionAllowed(true);
        Table.setAutoCreateRowSorter(true);
        Table.setRowHeight(24);
        Table.setFont(new Font("Arial", Font.BOLD, 14));

        txtSearch = new JTextField(20);
        txtSearch.setFont(new Font("STXihei", 1, 18));
        btnSearch = new JButton("Search");
        btnSearch.setFont(new Font("STXihei", 1, 18));

        //UserActionPanel
        JPanel UserActionPanel = new JPanel(new MigLayout("align 50% 50%"));
        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        UserActionPanel.setBackground(new Color(255, 225, 203));
        UserActionPanel.setLayout(new MigLayout("width 500px, height 800px"));

        //Tab Panel 1
        JPanel tab1 = new JPanel(new MigLayout(""));
        tab1.add(lblCuid, "span 2");
        tab1.add(txtCuid, "span 2, wrap, pushx, growx");
        tab1.add(lblCname, "span 2");
        tab1.add(txtName, "span 2, wrap, pushx, growx");
        tab1.add(lblCusername, "span 2");
        tab1.add(txtUsername, "span 2,wrap, pushx, growx");
        tab1.add(lblCpwd, "span 2");
        tab1.add(txtPassword, "span 2,wrap, pushx, growx");
        tab1.add(lblCrole, "span 2");
        tab1.add(cmbIsAdmin, "span 2,wrap, pushx, growx");
        //  tab1.add(lblCDoctorId, "span 2");
        //  tab1.add(txtCDoctorId, "span 2,wrap, pushx, growx");
        tab1.add(lblCDoctorDesc, "span 2");
        tab1.add(txtCDoctorDesc, "span 2,wrap, pushx, growx");
        tab1.add(btnCreate, "cell 3 6 ,right, pushx, growx");

        JPanel tab2 = new JPanel(new MigLayout(""));
        tab2.add(lblEuid, "span 2");
        tab2.add(txtEuid, "span 2, wrap, pushx, growx");
        tab2.add(lblEname, "span 2");
        tab2.add(txtEName, "span 2, wrap, pushx, growx");
        tab2.add(lblEusername, "span 2");
        tab2.add(txtEUsername, "span 2,wrap, pushx, growx");
        tab2.add(lblEpwd, "span 2");
        tab2.add(txtEPassword, "span 2,wrap, pushx, growx");
        tab2.add(lblErole, "span 2");
        tab2.add(cmbEIsAdmin, "span 2,wrap, pushx, growx");
        // tab2.add(lblEDoctorId, "span 2");
        // tab2.add(txtEDoctorId, "span 2,wrap, pushx, growx");
        tab2.add(lblEDoctorDesc, "span 2");
        tab2.add(txtEDoctorDesc, "span 2,wrap, pushx, growx");
        tab2.add(btnEdit, "cell 3 6 ,right, pushx, growx");
        tab2.add(btnDelete, "cell 3 6 ,right, pushx, growx");
        tabbedPane.addTab("Create User", tab1);
        tabbedPane.addTab("Edit User", tab2);
        //add component to panel
        tabbedPane.setFont(new Font("Arial", 1, 16));
        tab1.setBackground(new Color(255, 248, 242));
        tab2.setBackground(new Color(255, 248, 242));

        container.add(Title, "dock north, pushx, growx,wrap,gapx 15 15, gapy 50 15");
        UserListPanel.add(refreshTbl, "dock north,right, pushx, growx,wrap,gapx 25 25, gapy 15 5, width 50:70:200");
        UserListPanel.add(txtSearch, "split 2, span, pushx, growx,gapx 25 25, gapy 15 5");
        UserListPanel.add(btnSearch, "split 2, span,wrap,gapx 25 25, gapy 15 5, width 150:70:200");
        UserListPanel.add(scroll, "dock center, pushx,pushy, growx,growy, gapx 25 25, gapy 5 25");

        UserActionPanel.add(Back, "dock north,right,wrap,gapx 15 15, gapy 15 5,width 50:70:200");
        UserActionPanel.add(FilterPanel, "north, right, wrap, gapx 15 15, gapy 5 15,width 220:70:260");
//        UserActionPanel.add(lblFilter, "north,right,gapx 15 15, gapy 5 15");
//        UserActionPanel.add(cmbFilter, "dock north,right,wrap,gapx 15 15, gapy 5 15,width 50:70:200");
        UserActionPanel.add(tabbedPane, "dock center, pushx,pushy, growx,growy, gapy 0 28");

        container.add(UserListPanel, "west, pushx, growx , gapx 15 0");
        container.add(UserActionPanel, "east, gapx 15 15");

        btnCreate.addActionListener(this);
        refreshTbl.addActionListener(this);
        Back.addActionListener(this);
        btnEdit.addActionListener(this);
        btnDelete.addActionListener(this);
        cmbFilter.addActionListener(this);
        btnSearch.addActionListener(this);
        Table.setBackground(new Color(204, 255, 255));
        Table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    JTable Table = (JTable) e.getSource();
                    int row = Table.getSelectedRow();
                    int column = Table.getSelectedColumn();
                    // do some action if appropriate column
                    fillEditPanel(row);
                    System.out.println(Table.getValueAt(row, 0).toString());
                }
            }
        });

        this.add(container, "dock center");
        this.pack();
    }

    public void fillEditPanel(int row) {
        tabbedPane.setSelectedIndex(1);
        String uid = Table.getValueAt(row, 1).toString();
        Users user = c_user.getModel(uid);
        txtEuid.setText(uid);
        txtEName.setText(user.getName());
        txtEUsername.setText(user.getUsername());
        txtEPassword.setText(user.getPassword());
        txtEDoctorDesc.setText(user.getSelfdescription());
        if (user.getIsadmin()) {
            cmbEIsAdmin.setSelectedItem("Admin");
        } else {
            cmbEIsAdmin.setSelectedItem("Doctor");
        }
    }

    private void refreshTable(ArrayList<Users> dataList) {
        model = (DefaultTableModel) Table.getModel();
        model.setColumnCount(0);
        model.setRowCount(0);
        model.addColumn("No.");
        model.addColumn("UID");
        model.addColumn("Username");
        model.addColumn("Name");
        model.addColumn("Password");
        model.addColumn("Role");
        model.addColumn("Doctor Description");
        String Role;
      //  ArrayList<Users> userList = dataList;
        if (dataList.size() > 0) {
            for (int i = 0; i < dataList.size(); i++) {
                Users user = dataList.get(i);
                if (user.getIsadmin() == true) {
                    Role = "Admin";
                } else {
                    Role = "Doctor";
                }
                model.addRow(new Object[]{
                    Integer.toString(i + 1),
                    Integer.toString(user.getUid()),
                    user.getUsername(),
                    user.getName(),
                    user.getPassword(),
                    Role,
                    user.getSelfdescription()

                });
            }
            int lastRow = model.getRowCount() - 1;
            Object LastID = Table.getValueAt(lastRow, 1);
            NewID = Integer.valueOf((String) LastID) + 1;
            txtCuid.setText(String.valueOf(NewID));
        }
    }

    public boolean convertIsAdmin(JComboBox cmb) {
        Boolean IsAdmin;
        IsAdmin = cmb.getItemAt(cmb.getSelectedIndex()).toString().equals("Admin");
        System.out.println(IsAdmin);
        return IsAdmin;

    }

    public void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void displaySuccessMessage(String successMessage) {
        JOptionPane.showMessageDialog(this, successMessage, "Alert", JOptionPane.WARNING_MESSAGE);
    }

    public boolean UserPanelValidation(String username, String name, String pwd, String desc) {
        boolean valid = false;
        boolean required = false;
        Validation_User validation = new Validation_User();
        String errormessage = validation.UserValidation(username, name, pwd, desc);
        if (errormessage.length() == 0) {
            valid = true;
        } else {
            displayErrorMessage(errormessage);
        }
        return valid;
    }

    public void clearText() {
        txtUsername.setText("");
        txtName.setText("");
        txtPassword.setText("");
        txtCDoctorDesc.setText("");        
        txtSearch.setText("");
        txtEuid.setText("");
        txtEDoctorDesc.setText("");
        txtEUsername.setText("");
        txtEName.setText("");
        txtEPassword.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == refreshTbl) {
            refreshTable(c_user.getModelList());
            clearText();
        } //create user btn
        else if (e.getSource() == btnCreate) {
            if (UserPanelValidation(txtUsername.getText(), txtName.getText(), txtPassword.getText(), txtCDoctorDesc.getText())) {
                Users newUserInfo = new Users(Integer.parseInt(txtCuid.getText()), txtName.getText(), txtUsername.getText(), txtPassword.getText(),
                        convertIsAdmin(cmbIsAdmin), txtCDoctorDesc.getText());
                c_user.create(newUserInfo);
            }
            refreshTable(c_user.getModelList());
        } //edit btn
        else if (e.getSource() == btnEdit) {
            if (UserPanelValidation(txtEUsername.getText(), txtEName.getText(), txtEPassword.getText(), txtEDoctorDesc.getText())) {
                Users editedUserInfo = new Users(Integer.parseInt(txtEuid.getText()), txtEName.getText(), txtEUsername.getText(), txtEPassword.getText(),
                        convertIsAdmin(cmbEIsAdmin), txtEDoctorDesc.getText());
                 c_user.update(editedUserInfo);
            }
            refreshTable(c_user.getModelList());
        } //delete btn
        else if (e.getSource() == btnDelete) {
            System.out.println("delete btn is clicked");
            if (UserPanelValidation(txtEUsername.getText(), txtEName.getText(), txtEPassword.getText(), txtEDoctorDesc.getText())) {
                Users userTodelete = new Users(Integer.parseInt(txtEuid.getText()), txtEName.getText(), txtEUsername.getText(), txtEPassword.getText(),
                        convertIsAdmin(cmbEIsAdmin), txtEDoctorDesc.getText());
                c_user.delete(userTodelete);
            }
            refreshTable(c_user.getModelList());
        } //filter
        else if (e.getSource() == cmbFilter) {
            String role = cmbFilter.getItemAt(cmbFilter.getSelectedIndex()).toString();
            if (role.equals("Admin and Doctor")) {
                refreshTable(c_user.getModelList());
            } else{
                refreshTable(c_user.FilterUser(role));
            }
        } //Search
        else if (e.getSource() == btnSearch) {
            if (txtSearch.getText().isEmpty()) {
                displayErrorMessage("Please enter search key!");
            } else {
                ArrayList<Users> Result = c_user.SearchUser(txtSearch.getText());
                refreshTable(Result);
            }
        } //BackToMenu
        else if (e.getSource() == Back) {
            this.setVisible(false);
            OODJTest2.AdminMainMenu.setVisible(true);

        }
    }

}
