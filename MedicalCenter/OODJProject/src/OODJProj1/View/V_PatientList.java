
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OODJProj1.View;

import OODJProj1.Controller.C_Patient;
import OODJProj1.Controller.C_User;
import OODJProj1.Model.Patients;
import OODJProj1.Others.Validation_Patient;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JButton;
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
 * @author cheon
 */
public class V_PatientList extends JFrame implements ActionListener {

    DefaultTableModel model;
    JTable Table;
    JScrollPane scroll;
    JLabel lblSearch, lblPhone, lblePhone, lblecuid, lbledesc, lbldesc, Title, lblPname, lblCuid, lblEname, lblEusername, lblEPDesc;
    JButton Switch, btnSearch, Back, refreshTbl, btnCreate, btnEdit, btnDelete;
    JTextField txtSearch, txtPhone, txtePhone, txtName, txtEName, txtCuid,txtEPId, txtEPDesc, txtdesc;

    JTabbedPane tabbedPane;
    //DataLayer data = new DataLayer();   
    int NewID;
    private final C_Patient c_patient;


   public V_PatientList(C_Patient userController) {
        c_patient = userController;
        initComponent();
        refreshTable(c_patient.getModelList());
    }

    public void initComponent() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200, 800);
        this.setTitle("User Account Management");
        this.setLayout(new MigLayout("insets 0 0 0 0"));
        JPanel container = new JPanel();
        container.setLayout(new MigLayout("insets 0 0 0 0, fill, width 1200px, height 800px", "3[]3[]3[]3", "5[]5[]5[]5"));
        container.setBackground(new Color(255, 225, 203));
        //JLabel and JButton Design
        Title = new JLabel("Patient management");
        Title.setFont(new Font("Imprint MT Shadow", Font.BOLD, 36));
        Title.setHorizontalAlignment(SwingConstants.CENTER);

        Back = new JButton("Back to Main Menu");
        Back.setFont(new Font("Stika Heading", Font.BOLD, 18));
        Back.setHorizontalAlignment(SwingConstants.CENTER);
        Back.setBackground(new Color(255, 214, 207));

        Switch = new JButton("Switch to Account Management");
        Switch.setFont(new Font("Stika Heading", Font.BOLD, 18));
        Switch.setHorizontalAlignment(SwingConstants.CENTER);
        Switch.setBackground(new Color(255, 225, 156));

        refreshTbl = new JButton("Refresh Table");
        refreshTbl.setFont(new Font("Stika Heading", Font.BOLD, 18));
        refreshTbl.setHorizontalAlignment(SwingConstants.CENTER);
        refreshTbl.setBackground(new Color(176, 255, 212));

        txtName = new JTextField(20);
        txtName.setFont(new Font("Arial", 1, 14));

        txtdesc = new JTextField(20);
        txtdesc.setFont(new Font("Arial", 1, 14));

        txtPhone = new JTextField(20);
        txtPhone.setFont(new Font("Arial", 1, 14));

        txtCuid = new JTextField(20);
        txtCuid.setEnabled(false);
        txtCuid.setFont(new Font("Arial", 1, 14));

        lblPname = new JLabel("Name: ");
        lblPname.setFont(new Font("Arial", 1, 14));

        lbldesc = new JLabel("Description: ");
        lbldesc.setFont(new Font("Arial", 1, 14));

        lblCuid = new JLabel("Patient ID: ");
        lblCuid.setFont(new Font("Arial", 1, 14));

        lblPhone = new JLabel("Phone num: ");
        lblPhone.setFont(new Font("Arial", 1, 14));

        btnCreate = new JButton("Create");
//-----------------------------------------------------------//

        txtePhone = new JTextField(20);
        txtePhone.setFont(new Font("Arial", 1, 14));

        txtEName = new JTextField(20);
        txtEName.setFont(new Font("Arial", 1, 14));

        txtEPId = new JTextField(20);
        txtEPId.setFont(new Font("Arial", 1, 14));

        txtEPDesc = new JTextField(20);
        txtEPDesc.setFont(new Font("Arial", 1, 14));

        lblePhone = new JLabel("Phone num: ");
        lblePhone.setFont(new Font("Arial", 1, 14));

        lblEname = new JLabel("Name: ");
        lblEname.setFont(new Font("Arial", 1, 14));

        lblecuid = new JLabel("ID:");
        lblecuid.setFont(new Font("Arial", 1, 14));

        lbledesc = new JLabel("Description");
        lbledesc.setFont(new Font("Arial", 1, 14));

        btnEdit = new JButton("Edit");
        btnDelete = new JButton("Delete");

        //PatientListPanel with table Design
        JPanel PatientListPanel = new JPanel();
        PatientListPanel.setBackground(new Color(255, 225, 203));
        PatientListPanel.setLayout(new MigLayout(" insets 0 0 0 0 , fillx,width 800px, height 800px"));
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

        JPanel UserActionPanel = new JPanel(new MigLayout("align 50% 50%"));
        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        UserActionPanel.setBackground(new Color(255, 225, 203));
        UserActionPanel.setLayout(new MigLayout("width 500px, height 800px"));

        //Tab Panel 1
        JPanel tab1 = new JPanel(new MigLayout());
        tab1.add(lblCuid, "span 2");
        tab1.add(txtCuid, "span 2, wrap, pushx, growx");
        tab1.add(lblPhone, "span 2");
        tab1.add(txtPhone, "span 2, wrap, pushx, growx");
        tab1.add(lblPname, "span 2");
        tab1.add(txtName, "span 2, wrap, pushx, growx");
        //  tab1.add(lblCDoctorId, "span 2");
        //  tab1.add(txtCDoctorId, "span 2,wrap, pushx, growx");
        tab1.add(lbldesc, "span 2");
        tab1.add(txtdesc, "span 2,wrap, pushx, growx");
        tab1.add(btnCreate, "cell 3 6 ,right, pushx, growx");

        JPanel tab2 = new JPanel(new MigLayout());
        tab2.add(lblEname, "span 2");
        tab2.add(txtEName, "span 2, wrap, pushx, growx");
        tab2.add(lblePhone, "span 2");
        tab2.add(txtePhone, "span 2, wrap, pushx,growx");
        tab2.add(lbledesc, "span 2");
        tab2.add(txtEPDesc, "span 2,wrap, pushx, growx");
        // tab2.add(lblEDoctorId, "span 2");
        // tab2.add(txtEDoctorId, "span 2,wrap, pushx, growx");
        tab2.add(btnEdit, "cell 3 5 ,right, pushx, growx");
        tab2.add(btnDelete, "cell 3 5 ,right, pushx, growx");
        tabbedPane.addTab("Create Patient", tab1);
        tabbedPane.addTab("Edit Patient", tab2);
        tabbedPane.setFont(new Font("Arial", 1, 16));
        tab1.setBackground(new Color(255, 248, 242));
        tab2.setBackground(new Color(255, 248, 242));

        //add componentanel
        container.add(Title, "dock north, pushx, growx,wrap,gapx 15 15, gapy 15 15");
        PatientListPanel.add(refreshTbl, "dock north,right, pushx, growx,wrap,gapx 25 25, gapy 15 5, width 50:70:200");
        PatientListPanel.add(txtSearch, "split 2, span, pushx, growx,gapx 25 25, gapy 15 5");
        PatientListPanel.add(btnSearch, "split 2, span,wrap,gapx 25 25, gapy 15 5, width 150:70:200");
        PatientListPanel.add(scroll, "dock center, pushx,pushy, growx,growy, gapx 25 25, gapy 5 25");

        UserActionPanel.add(tabbedPane, "dock center, pushx,pushy, growx,growy, gapy 0 28");
        UserActionPanel.add(Back, "dock north,right,wrap,gapx 50 15, gapy 15 15,width 40:70:200");
        container.add(PatientListPanel, "west, pushx, growx");
        container.add(UserActionPanel, "east, gapx 15 15");

        btnCreate.addActionListener(this);
        btnDelete.addActionListener(this);
        btnEdit.addActionListener(this);
        btnSearch.addActionListener(this);
        refreshTbl.addActionListener(this);
        Back.addActionListener(this);
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
        Table.setBackground(new Color(204, 255, 255));

        this.add(container, "dock center");
        this.pack();
    }

    public void fillEditPanel(int row) {
        tabbedPane.setSelectedIndex(1);
        String pid = Table.getValueAt(row, 1).toString();
        Patients patient = c_patient.getModel(pid);
        txtEPId.setText(pid);
        txtEName.setText(patient.getPatientName());
        txtePhone.setText(Long.toString(patient.getPatientPhone()));
        txtEPDesc.setText(patient.getPatientDetails());
    }

    public void refreshTable(ArrayList<Patients> PatientList) {
        ArrayList<Patients> allPatients = PatientList;
        model = (DefaultTableModel) Table.getModel();
        model.setColumnCount(0);
        model.setRowCount(0);
        model.addColumn("No.");
        model.addColumn("PatientID");
        model.addColumn("Patient Phone Number");
        model.addColumn("Patient Name");
        model.addColumn("Patient Description");
        if (allPatients.size() > 0) {
            for (int i = 0; i < allPatients.size(); i++) {
                Patients patient = allPatients.get(i);
                model.addRow(new Object[]{
                    Integer.toString(i + 1),
                    Integer.toString(patient.getPatientID()),
                    Long.toString(patient.getPatientPhone()),
                    patient.getPatientName(),
                    patient.getPatientDetails(),});
            }
        }
        int lastRow = model.getRowCount() - 1;
        Object LastID = Table.getValueAt(lastRow, 1);
        NewID = Integer.valueOf((String) LastID) + 1;
        txtCuid.setText(String.valueOf(NewID));

    }

    public void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void displaySuccessMessage(String successMessage) {
        JOptionPane.showMessageDialog(this, successMessage, "Alert", JOptionPane.WARNING_MESSAGE);
    }

    public boolean PatientPanelValidation(String name, String num, String desc) {
        boolean valid = false;
        boolean required = false;
        Validation_Patient validation = new Validation_Patient();
        String errormessage = validation.PatientValidation(name, num);
        if (errormessage.length() == 0) {
            valid = true;
        } else {
            displayErrorMessage(errormessage);
        }
        return valid;
    }

    public void ButtonTest(ActionEvent e) {
        if (e.getSource() == btnDelete) {
            System.out.println("Delete button pressed");
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == refreshTbl) {
            refreshTable(c_patient.getModelList());
        } else if (e.getSource() == btnCreate) {
            //  cUser.createUser(getName(),getUsername(),getPassword(),getIsAdmin());
            if (PatientPanelValidation(txtName.getText(), txtPhone.getText(), txtdesc.getText())) {
                Patients patient = new Patients(Integer.parseInt(txtCuid.getText()), Long.parseLong(txtPhone.getText()), txtName.getText(), txtdesc.getText());                ;
                c_patient.create( patient);

            }
            refreshTable(c_patient.getModelList());
        } else if (e.getSource() == btnEdit) {
            if (PatientPanelValidation(txtEName.getText(), txtePhone.getText(), txtEPDesc.getText())) {
                Patients editedPatientInfo = new Patients(Integer.parseInt(txtEPId.getText()), Long.parseLong(txtePhone.getText()), txtEName.getText(), txtEPDesc.getText());                
                c_patient.update(editedPatientInfo);
            }
            refreshTable(c_patient.getModelList());
        } //delete btn
        else if (e.getSource() == btnDelete) {
            if (PatientPanelValidation(txtEName.getText(), txtePhone.getText(), txtEPDesc.getText())) {
                Patients patientToDelete = new Patients(Integer.parseInt(txtEPId.getText()), Long.parseLong(txtePhone.getText()), txtEName.getText(), txtEPDesc.getText());                
                c_patient.delete(patientToDelete);

            }
            refreshTable(c_patient.getModelList());
        } //filter
        else if (e.getSource() == btnSearch) {
            if (txtSearch.getText().isEmpty()) {
                displayErrorMessage("Please enter search key!");
            } else {
                ArrayList<Patients> Result = c_patient.getModelList(txtSearch.getText());
                refreshTable(Result);
            }
        } else if (e.getSource() == Back) {
            this.setVisible(false);
            if (C_User.login.getIsadmin()) {
                OODJTest2.AdminMainMenu.setVisible(true);

            } else {
                OODJTest2.DoctorMainMenu.setVisible(true);

            }

        }

    }

}
