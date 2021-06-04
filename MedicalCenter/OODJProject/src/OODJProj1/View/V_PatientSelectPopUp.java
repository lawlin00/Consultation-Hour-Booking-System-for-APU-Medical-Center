/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OODJProj1.View;


import OODJProj1.Controller.C_Patient;
import OODJProj1.Model.Patients;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;

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
public class V_PatientSelectPopUp extends JDialog implements ActionListener{
     DefaultTableModel model;
    JTable Table;    
    JScrollPane scroll;
    JLabel lblePhone,lblecuid,lbledesc,Title,lblPid,lblEname;
    JButton Switch,btnSearch,Back,refreshTbl,btnSelect;
    JTextField txtSearch,txtePhone,txtEName,txtEPId,txtEPDesc;          
    int NewID;  
    private final C_Patient c_patient;
    
    Patients thispatient;
    V_PatientSelectPopUp(C_Patient userController){
        c_patient = userController;
        initComponent();
        refreshTable(c_patient.getModelList());  
         this.setModalityType(Dialog.ModalityType.DOCUMENT_MODAL);
    }   
    public void initComponent(){
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setSize(1200,800);   
        this.setTitle("User Account Management");
        this.setLayout(new net.miginfocom.swing.MigLayout());
        JPanel container = new JPanel();
        container.setLayout(new MigLayout("insets 0 0 0 0, fill, width 1200px, height 800px", "3[]3[]3[]3", "5[]5[]5[]5"));               
        container.setBackground(new Color(255, 225, 203)); 
        //JLabel and JButton Design
        Title = new JLabel("Patient Selection");
        Title.setFont(new Font("Imprint MT Shadow",Font.BOLD,36));
        Title.setHorizontalAlignment(SwingConstants.CENTER);
        
        txtePhone = new JTextField(20);
        txtePhone.setFont(new Font("Arial", 1, 14));
        txtEName =  new JTextField(20);
        txtEName.setFont(new Font("Arial", 1, 14));
        txtEPId = new JTextField(20);
        txtEPId.setFont(new Font("Arial", 1, 14));
        txtEPDesc= new JTextField(20);
        txtEPDesc.setFont(new Font("Arial", 1, 14));
        lblPid = new JLabel("Patiend ID");
        lblPid.setFont(new Font("Arial", 1, 14));
        lblePhone = new JLabel("Phone num: ");
        lblePhone.setFont(new Font("Arial", 1, 14));
        lblEname = new JLabel("Name: ");
        lblEname.setFont(new Font("Arial", 1, 14));
        lblecuid = new JLabel("ID:");
        lblecuid.setFont(new Font("Arial", 1, 14));
        lbledesc = new JLabel("Description");
        lbledesc.setFont(new Font("Arial", 1, 14));
        btnSelect = new JButton("Confirm");
        refreshTbl = new JButton("Refresh ");        
        refreshTbl.setFont(new Font("Stika Heading", Font.BOLD, 18));
        refreshTbl.setHorizontalAlignment(SwingConstants.CENTER);
        refreshTbl.setBackground(new Color(176, 255, 212));
        Back = new JButton("Back");
        Back.setFont(new Font("Stika Heading", Font.BOLD, 18));
        Back.setHorizontalAlignment(SwingConstants.CENTER);
        Back.setBackground(new Color(255, 214, 207));
       
        
        
        //PatientListPanel with table Design
        JPanel PatientListPanel = new JPanel();      
        PatientListPanel .setBackground(new Color(255, 225, 203)); 
        PatientListPanel .setLayout(new MigLayout(" insets 2 2 2 2 , fillx,width 800px, height 800px"));          
        Table = new JTable(model);
        scroll = new JScrollPane(Table);        
        Table.setFocusable(false);
        Table.setRowSelectionAllowed(false);               
        
        txtSearch = new JTextField(20);
        txtSearch.setFont(new Font("STXihei", 1, 18));
        btnSearch = new JButton("Search");
        btnSearch.setFont(new Font("STXihei", 1, 18)); 
        
        JPanel UserActionPanel = new JPanel(new MigLayout("align 50% 50%"));  
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);               
        UserActionPanel.setBackground(new Color(255, 225, 203));
        UserActionPanel.setLayout(new MigLayout("width 500px, height 800px")); 
        
  
        JPanel tab2 = new JPanel(new MigLayout(""));
        tab2.add(lblPid,"span 2");
        tab2.add(txtEPId,"span 2, wrap, pushx,growx");
        tab2.add(lblEname,"span 2");
        tab2.add(txtEName, "span 2,wrap,pushx,growx ");
        tab2.add(lblePhone,"span 2");
        tab2.add(txtePhone, "span 2, wrap, pushx,growx");
        tab2.add(lbledesc,"span 2");
        tab2.add(txtEPDesc, "span 2,wrap, pushx, growx");
       // tab2.add(lblEDoctorId, "span 2");
       // tab2.add(txtEDoctorId, "span 2,wrap, pushx, growx");
        tab2.add(btnSelect,"cell 3 5 ,right, pushx, growx");
        tabbedPane.addTab("Patient Details", tab2);
        tabbedPane.setFont(new Font("Arial", 1, 16));     
        tab2.setBackground(new Color(255,248,242));
   
        //add componentanel
        container.add(Title, "dock north, pushx, growx,wrap,gapx 15 15, gapy 15 15");
        PatientListPanel .add(refreshTbl, "dock north,right, pushx, growx,wrap,gapx 25 25, gapy 15 5, width 50:70:200");
        PatientListPanel.add(txtSearch, "split 2, span, pushx, growx,gapx 25 25, gapy 15 5");
        PatientListPanel.add(btnSearch, "split 2, span,wrap,gapx 25 25, gapy 15 5, width 150:70:200");
        PatientListPanel .add(scroll, "dock center, pushx,pushy, growx,growy, gapx 25 25, gapy 5 25");        
        
        UserActionPanel.add(tabbedPane,"dock center, pushx,pushy, growx,growy, gapy 0 28");        
        UserActionPanel.add(Back, "dock north,right,wrap,gapx 15 15, gapy 15 15,width 40:70:200");
        container.add(PatientListPanel ,"west, pushx, growx");
        container.add(UserActionPanel,"east");

        btnSelect.addActionListener(this);
        btnSearch.addActionListener(this);
        refreshTbl.addActionListener(this);
        Back.addActionListener(this);
        Table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    JTable Table = (JTable) e.getSource();
                    int row = Table.getSelectedRow();
                    // do some action if appropriate column
                    fillEditPanel(row);
                    System.out.println(Table.getValueAt(row, 0).toString());
                }
            }
        });
        
        this.add(container,"dock center");       
        this.pack();
    }
        public void fillEditPanel(int row) {
        String pid = Table.getValueAt(row, 1).toString();
        Patients patient = c_patient.getModel(pid);
        txtEPId.setText(pid);
        txtEName.setText(patient.getPatientName());
        txtePhone.setText(Long.toString(patient.getPatientPhone()));
        txtEPDesc.setText(patient.getPatientDetails());
    }


    public void refreshTable(ArrayList<Patients> PatientList){                  
        ArrayList<Patients> allPatients = PatientList;
        model = (DefaultTableModel)Table.getModel(); 
        model.setColumnCount(0);
        model.setRowCount(0);        
        model.addColumn("No.");
        model.addColumn("PatientID");
        model.addColumn("Patient Phone Number");
        model.addColumn("Patient Name");        
        model.addColumn("Patient Description");     
        for (int i = 0; i < allPatients.size(); i++) {
            Patients patient = allPatients.get(i);
            model.addRow(new Object[]{
                Integer.toString(i+1),
                Integer.toString(patient.getPatientID()),
                Long.toString(patient.getPatientPhone()),
                patient.getPatientName(),
                patient.getPatientDetails(),                
                
            });
        }
        int lastRow = model.getRowCount()- 1;       
        Object LastID = Table.getValueAt(lastRow,1);
        NewID = Integer.valueOf((String) LastID) + 1;  

    }
    
    
    public void displayErrorMessage(String errorMessage){
        JOptionPane.showMessageDialog(this, errorMessage,"Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public void displaySuccessMessage(String successMessage){
        JOptionPane.showMessageDialog(this, successMessage, "Alert",JOptionPane.WARNING_MESSAGE);
    }
    
    public Patients getPatient(){
        return thispatient;
    }

    public void setPatient(Patients patient){
        thispatient = patient;
    }
    
    public Patients showDialog(){
        this.setVisible(true);
        Patients patient = c_patient.getModel(txtEPId.getText());
        setPatient(patient);
        return patient;
    }
        
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == refreshTbl){            
            refreshTable(c_patient.getModelList());
        }
        else if (e.getSource() == btnSelect) {
                setVisible(false);
                dispose();
//                V_AppointmentList.txtName.setText(patient.getPatientName());
        } //delete btn
        else if (e.getSource() == btnSearch){
           if (txtSearch.getText().isEmpty()) {
                displayErrorMessage("Please enter search key!");
            } else {
                ArrayList<Patients> Result = c_patient.getModelList(txtSearch.getText());
                refreshTable(Result);
            }
        }else if(e.getSource() == Back){
            dispose();
        }
    
}

}

