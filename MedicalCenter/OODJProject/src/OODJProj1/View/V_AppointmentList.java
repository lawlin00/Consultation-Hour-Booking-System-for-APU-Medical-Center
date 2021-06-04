/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OODJProj1.View;

//import static OODJProj1.V_PatientList.timePicker;
import OODJProj1.Model.Appointments;
import OODJProj1.Model.Patients;
import OODJProj1.Controller.C_Appointment;
import OODJProj1.Controller.C_Patient;
import OODJProj1.Controller.C_Payment;
import OODJProj1.Controller.C_User;
import OODJProj1.Others.UserComboBoxItem;
import OODJProj1.Model.*;
import OODJProj1.Others.Validation;
import OODJProj1.Others.Validation_Patient;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.DateTimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author cheon
 */
public class V_AppointmentList extends JFrame implements ActionListener {

    /**
     *
     * @author cheon
     */
    DefaultTableModel model;
    JTable Table;
    JScrollPane scroll;
    JLabel lblSearch, lblAppTime, lbleAppID, lbledesc, lbldesc, Title, lblPatient, lblDoctor, lblCrole, lblCuid, lblEdoc, lblEpat, lblEPhone,
            lblEid, lblEtime, lblEdesc, lblbegin, lblFilter;
    JButton Switch, btnSearch, Back, refreshTbl, btnCreate, btnEdit, btnDelete, btnPatient, btnViewPaymentInfo;
    JTextField txtDesc, txtCuid, txtSearch, txtPhone, txtePhone, txtName, txtUsername, txtPassword, txtEdoc, txtEpat, txtEphone, txtEid, txtEtime, txtEdesc, txtdesc;
    JComboBox comboDoctor, cmbFilter;
    int NewID;
    static DateTimePicker timePicker;
    static DatePicker beginPicker, endPicker;
    final LocalDate today = LocalDate.now();
    private C_Appointment c_appointment;
    private final C_User c_user;
    private final C_Patient c_patient;
    private Patients thisPatient, selectPatient;
    private Users thisDoctor, selectDoctor;
    private Appointments selectedAppointment;

   public V_AppointmentList(C_Appointment appController, C_User userController, C_Patient patientController) {
        c_appointment = appController;
        c_user = userController;
        c_patient = patientController;
        c_appointment.SetMissedAppointments();
        initComponent();
        c_appointment.RemoveInvalidatedApps();
        refreshTable(c_appointment.getModelList());
        populateCombo(c_user.FilterUser("doctor"));
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
        
        LocalDate now = LocalDate.now();
        LocalDate tomorrow = now.plusDays(1);
        LocalDateTime tomorrowvalid = tomorrow.atTime(10, 0);

        TimePickerSettings timeSettings;
        DatePickerSettings dateSettings, searchSettings, searchSettings2;

        dateSettings = new DatePickerSettings();
        timeSettings = new TimePickerSettings();
        searchSettings = new DatePickerSettings();
        searchSettings2 = new DatePickerSettings();
        
        
        dateSettings.setAllowEmptyDates(false);
        timeSettings.setAllowEmptyTimes(false);
        searchSettings.setAllowEmptyDates(false);
        searchSettings2.setAllowEmptyDates(false);
        
        timeSettings.generatePotentialMenuTimes(TimePickerSettings.TimeIncrement.OneHour, null, null);
        timePicker = new DateTimePicker(dateSettings, timeSettings);


        timePicker.setDateTimePermissive(tomorrowvalid);
        timeSettings.setVetoPolicy(new C_Appointment.AppointmentTimePolicy());
        dateSettings.setDateRangeLimits(today.minusDays(-1), today.plusDays(20));

        beginPicker = new DatePicker(searchSettings);
        endPicker = new DatePicker(searchSettings2);

        Title = new JLabel("Appointment management ");
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
        btnPatient = new JButton("Select");
        comboDoctor = new JComboBox();
        txtName = new JTextField(20);
        txtName.setEnabled(false);
        txtName.setFont(new Font("Arial", 1, 14));

        txtDesc = new JTextField(20);
        txtDesc.setFont(new Font("Arial", 1, 14));

        txtEdesc = new JTextField(20);
        txtEdesc.setFont(new Font("Arial", 1, 14));

        txtPhone = new JTextField(20);
        txtPhone.setFont(new Font("Arial", 1, 14));

        txtCuid = new JTextField(20);
        txtCuid.setEnabled(false);
        txtCuid.setFont(new Font("Arial", 1, 14));

        txtEid = new JTextField(20);
        txtEid.setEnabled(false);
        txtEid.setFont(new Font("Arial", 1, 14));

        txtEphone = new JTextField(20);
        txtEphone.setFont(new Font("Arial", 1, 14));

        lblAppTime = new JLabel("Appointment Time");
        lblAppTime.setFont(new Font("Arial", 1, 14));

        lblPatient = new JLabel("Patient Name: ");
        lblPatient.setFont(new Font("Arial", 1, 14));

        lbldesc = new JLabel("Description: ");
        lbldesc.setFont(new Font("Arial", 1, 14));

        lblCuid = new JLabel("Appointment ID: ");
        lblCuid.setFont(new Font("Arial", 1, 14));

        lblDoctor = new JLabel("Doctor: ");
        lblDoctor.setFont(new Font("Arial", 1, 14));

        btnCreate = new JButton("Create");
        //-------------------------------------------------------------//      
        txtePhone = new JTextField(20);
        txtePhone.setEditable(false);
        txtePhone.setFont(new Font("Arial", 1, 14));

        txtEpat = new JTextField(20);
        txtEpat.setEditable(false);
        txtePhone.setFont(new Font("Arial", 1, 14));

        txtEid = new JTextField(20);
        txtEid.setEditable(false);
        txtEid.setFont(new Font("Arial", 1, 14));

        txtEdoc = new JTextField(20);
        txtEdoc.setEditable(false);
        txtEdoc.setFont(new Font("Arial", 1, 14));

        txtEdesc = new JTextField(20);
        txtEdesc.setEditable(false);
        txtEdesc.setFont(new Font("Arial", 1, 14));

        txtEtime = new JTextField(40);
        txtEtime.setEditable(false);
        txtEtime.setFont(new Font("Arial", 1, 14));

        txtEphone = new JTextField(20);
        txtEphone.setEditable(false);
        txtEphone.setFont(new Font("Arial", 1, 14));

        lblEid = new JLabel("Appointment ID: ");
        lblEid.setFont(new Font("Arial", 1, 14));

        lblEpat = new JLabel("Patient Name: ");
        lblEpat.setFont(new Font("Arial", 1, 14));

        lbledesc = new JLabel("Description");
        lbledesc.setFont(new Font("Arial", 1, 14));

        lblEdoc = new JLabel("Doc Name: ");
        lblEdoc.setFont(new Font("Arial", 1, 14));

        lblEtime = new JLabel("Appointment Time: ");
        lblEtime.setFont(new Font("Arial", 1, 14));

        lblEPhone = new JLabel("Patient phone: ");
        lblEPhone.setFont(new Font("Arial", 1, 14));

        btnEdit = new JButton("Edit");
        btnDelete = new JButton("Delete");

        btnViewPaymentInfo = new JButton("View Payment Detail");

        JPanel FilterPanel = new JPanel(new MigLayout("inset 0 0 0 0"));
        lblFilter = new JLabel("Filter: ");
        lblFilter.setFont(new Font("Times New Roman", Font.BOLD, 16));
        cmbFilter = new JComboBox();
        cmbFilter.setFont(new Font("Times New Roman", 1, 16));
        cmbFilter.addItem("All");
        cmbFilter.addItem("Pending Appointment");
        cmbFilter.addItem("Complete Appointment");
        cmbFilter.addItem("Today Appointment");
        cmbFilter.addItem("Missed Appointment");
        FilterPanel.add(lblFilter, "gapx 15 15, split 2, span");
        FilterPanel.add(cmbFilter, "gapx 5 0,split 2, span, growx, pushx, span");
        FilterPanel.setBackground(new Color(255, 225, 203));

        //PatientListPanel with table Design
        JPanel PatientListPanel = new JPanel();
        PatientListPanel.setBackground(new Color(255, 225, 203));
        PatientListPanel.setLayout(new MigLayout(" insets 2 2 2 2 , fillx,width 800px, height 800px"));
        Table = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        scroll = new JScrollPane(Table);
        Table.setFocusable(false);
        Table.setRowSelectionAllowed(false);
        Table.setRowHeight(24);
        Table.setFont(new Font("Arial", Font.BOLD, 12));

        txtSearch = new JTextField(20);
        txtSearch.setFont(new Font("STXihei", 1, 18));
        btnSearch = new JButton("Search");
        btnSearch.setFont(new Font("STXihei", 1, 18));

        JPanel UserActionPanel = new JPanel(new MigLayout("align 50% 50%"));
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        UserActionPanel.setBackground(new Color(255, 225, 203));
        UserActionPanel.setLayout(new MigLayout("width 500px, height 800px"));

        //Tab Panel 1
        JPanel tab1 = new JPanel(new MigLayout(""));
        tab1.add(lblCuid, "span 2");
        tab1.add(txtCuid, "span 2, wrap, pushx, growx");
        tab1.add(lblAppTime, "span 2");
        tab1.add(timePicker, "span 2,wrap,pushx,growx");
        tab1.add(lblDoctor, "span 2");
        tab1.add(comboDoctor, "span 2, wrap, pushx, growx");
        tab1.add(lblPatient, "span 2");
        tab1.add(txtName, "span 2, wrap, pushx, growx");
        tab1.add(btnPatient, "cell 3 4, span, wrap, right");
        //  tab1.add(lblCDoctorId, "span 2");
        //  tab1.add(txtCDoctorId, "span 2,wrap, pushx, growx");
        tab1.add(lbldesc, "span 2");
        tab1.add(txtDesc, "span 2,wrap, pushx, growx");
        tab1.add(btnCreate, "cell 3 6 ,right, pushx, growx");

        JPanel tab2 = new JPanel(new MigLayout(""));
        tab2.add(lblEid, "span 2");
        tab2.add(txtEid, "span 2, wrap, pushx, growx");
        tab2.add(lblEdoc, "span 2");
        tab2.add(txtEdoc, "span 2, wrap, pushx, growx");
        tab2.add(lblEpat, "span 2");
        tab2.add(txtEpat, "span 2, wrap, pushx, growx");
        tab2.add(lblEPhone, "span 2");
        tab2.add(txtEphone, "span 2, wrap, pushx, growx");
        tab2.add(lblEtime, "span 2");
        tab2.add(txtEtime, "span 2, wrap, pushx, growx");
        tab2.add(lbledesc, "span 2");
        tab2.add(txtEdesc, "span 2,wrap, pushx, growx");
        tab2.add(btnViewPaymentInfo, "cell 2 6 ,right, pushx, growx, hidemode 3");
        // tab2.add(lblEDoctorId, "span 2");
        // tab2.add(txtEDoctorId, "span 2,wrap, pushx, growx");
        tab2.add(btnDelete, "span ,wrap, pushx, growx");
        tabbedPane.addTab("Create Appointment", tab1);
        tabbedPane.addTab("Deletion and payment details", tab2);
        tabbedPane.setFont(new Font("Arial", 1, 16));
        tab1.setBackground(new Color(255, 248, 242));
        tab2.setBackground(new Color(255, 248, 242));

        //add componentanel
        container.add(Title, "dock north, pushx, growx,wrap,gapx 15 15, gapy 50 15");
        PatientListPanel.add(refreshTbl, "dock north,right, pushx, growx,wrap,gapx 25 25, gapy 15 5, width 50:70:200");
        PatientListPanel.add(beginPicker, "split 3, span, pushx, growx,gapx 25 25, gapy 15 5");
        PatientListPanel.add(endPicker, "split 3, span, pushx, growx,gapx 25 25, gapy 15 5");
        PatientListPanel.add(btnSearch, "split 3, span,wrap,gapx 25 25, gapy 15 5, width 150:70:200");
        PatientListPanel.add(scroll, "dock center, pushx,pushy, growx,growy, gapx 25 25, gapy 5 25");

        UserActionPanel.add(tabbedPane, "dock center, pushx,pushy, growx,growy, gapy 0 28");
        UserActionPanel.add(Back, "dock north,right,wrap,gapx 15 15, gapy 15 15,width 40:70:200");
        UserActionPanel.add(FilterPanel, "north, right, wrap, gapx 15 15, gapy 5 15,width 220:70:260");
        container.add(PatientListPanel, "west, pushx, growx, gapx 15 0");
        container.add(UserActionPanel, "east, gapx 15 15");

        btnViewPaymentInfo.addActionListener(this);
        btnCreate.addActionListener(this);
        btnPatient.addActionListener(this);
        btnDelete.addActionListener(this);
        btnEdit.addActionListener(this);
        btnSearch.addActionListener(this);
        refreshTbl.addActionListener(this);
        comboDoctor.addActionListener(this);
        cmbFilter.addActionListener(this);
        Back.addActionListener(this);
        Table.addMouseListener(new MouseAdapter() {
            //Gets table row value
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    JTable Table = (JTable) e.getSource();
                    int row = Table.getSelectedRow();
                    // do some action if appropriate column
                    fillEditPanel(row);
                    tabbedPane.setSelectedIndex(1);
                    System.out.println(Table.getValueAt(row, 0).toString());
                }
            }
        });

        this.add(container, "dock center");
        this.pack();
    }

    public void fillEditPanel(int row) {
        //Fills edit panel with information from table row
        txtEid.setText(Table.getValueAt(row, 1).toString());
        txtEpat.setText(Table.getValueAt(row, 2).toString());
        txtEdoc.setText(Table.getValueAt(row, 4).toString());
        txtEphone.setText(Table.getValueAt(row, 3).toString());
        txtEdesc.setText(Table.getValueAt(row, 5).toString());
        txtEtime.setText(Table.getValueAt(row, 6).toString());
        if (Table.getValueAt(row, 7).toString().equals("Complete")) {
            btnViewPaymentInfo.setVisible(true);
        } else {
            btnViewPaymentInfo.setVisible(false);
        }
    }

    public void populateCombo(ArrayList<Users> DList) {
        //Populate the combo box with UserComboBoxItem objects
        ArrayList<Users> allUsers = DList;
        for (int i = 0; i < allUsers.size(); i++) {
            Users user = allUsers.get(i);
            String docname = user.getName();
            comboDoctor.addItem(new UserComboBoxItem(user, docname));
        }
    }

    public void refreshTable(ArrayList<Appointments> appList) {
        
        c_appointment.RemoveInvalidatedApps();
        ArrayList<Appointments> allAppointments = appList;
        model = (DefaultTableModel) Table.getModel();
        model.setColumnCount(0);
        model.setRowCount(0);
        model.addColumn("No.");
        model.addColumn("Appointment ID");
        model.addColumn("Appointment Patient");
        model.addColumn("Patient Number");
        model.addColumn("Appointment Doctor");
        model.addColumn("Appointment reason");
        model.addColumn("Appointment time");
        model.addColumn("Appointment Status");
        for (int i = 0; i < allAppointments.size(); i++) {
            Appointments appointment = allAppointments.get(i);
            Patients appPatient = appointment.getAppPatient();
            Users appDoctor = appointment.getAppDoctor();
            String appPatientName = appPatient.getPatientName();
            String appPatientNumber = Long.toString(appPatient.getPatientPhone());
            String appDoctorName = appDoctor.getName();
            model.addRow(new Object[]{
                Integer.toString(i + 1),
                Integer.toString(appointment.getAppID()),
                appPatientName,
                appPatientNumber,
                appDoctorName,
                appointment.getAppDescription(),
                appointment.getSlot(),
                appointment.getStatus()

            });
            int lastRow = model.getRowCount() - 1;
            Object LastID = Table.getValueAt(lastRow, 1);
            NewID = Integer.valueOf((String) LastID) + 1;
            txtCuid.setText(String.valueOf(NewID));
        }

        setColor();

    }

    public void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void displaySuccessMessage(String successMessage) {
        JOptionPane.showMessageDialog(this, successMessage, "Alert", JOptionPane.WARNING_MESSAGE);
    }

    public boolean PatientPanelValidation(String name, String num, String desc) {
        //Validates the patient using the Validation_patient class , such as patient name or phone number
        boolean valid = false;
        Validation_Patient validation = new Validation_Patient();
        String errormessage = validation.PatientValidation(name, num);
        if (errormessage.length() == 0) {
            valid = true;
        } else {
            displayErrorMessage(errormessage);
        }
        return valid;
    }


    public void setColor() {
        //Sets table colour
        Table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            //  @Override
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                Color c = new Color(204, 255, 255);
                Color t = new Color(102, 51, 0);
                //Color c = Color.BLACK;
                // Color t = Color.WHITE;
                Object AppStatus = (String) table.getValueAt(row, 7);
                String status = (String) AppStatus;

                //System.out.println(stock);
                //int q = ((Integer) stock).intValue();
                switch (status) {
                    case "Missed":
                        c = new Color(255, 51, 51);
                        t = Color.WHITE;
                        break;
                    case "Pending":
                        c = new Color(255, 204, 51);
                        t = Color.BLACK;
                        break;
                    case "Complete":
                        c = new Color(102, 255, 102);
                        t = Color.BLACK;
                        break;
                    default:
                        break;
                }
                label.setBackground(c);
                label.setForeground(t);
                return label;

            }

        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == refreshTbl) {
            refreshTable(c_appointment.getModelList());
        } else if (e.getSource() == btnPatient) {
            V_PatientSelectPopUp PatientPopUp = new V_PatientSelectPopUp(c_patient);
            Patients pop = PatientPopUp.showDialog();
            txtName.setText(pop.getPatientName());
            thisPatient = pop;
        } else if (e.getSource() == comboDoctor) {
            UserComboBoxItem item = (UserComboBoxItem) comboDoctor.getSelectedItem();
            thisDoctor = item.getUser();
        } else if (e.getSource() == btnCreate) {
            Validation validator = new Validation();
            if (txtDesc.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Description not provided");
            } else if (txtName.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "No name provided");
            } 
            else {
                Appointments appointment = new Appointments(Integer.parseInt(txtCuid.getText()), thisPatient, thisDoctor,
                        txtDesc.getText(), timePicker.getDateTimeStrict(), "Valid");
                appointment.toFileString();
                c_appointment.create(appointment);
                refreshTable(c_appointment.getModelList());
            }
        } else if (e.getSource() == btnDelete) {

            if (txtEid.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "No appointments selected");
            } else {
                String aID = txtEid.getText();
                Appointments appToDelete = c_appointment.getModel(aID);
                appToDelete.toFileString();
                c_appointment.delete(appToDelete);
            }
            refreshTable(c_appointment.getModelList());
        } //filter
        else if (e.getSource() == btnSearch) {
            ArrayList<Appointments> results = c_appointment.GenerateAppList(beginPicker.getDate(), endPicker.getDate());
            refreshTable(results);
        } else if (e.getSource() == Back) {
            OODJTest2.AdminMainMenu.setVisible(true);
            this.setVisible(false);
        } else if (e.getSource() == cmbFilter) {
            String type = "All";
            String selected = cmbFilter.getItemAt(cmbFilter.getSelectedIndex()).toString();
            if (selected.contains(" ")) {
                selected = selected.substring(0, selected.indexOf(" "));
                type = selected;
            }            
            if (type.equals("All")) {
                ArrayList<Appointments> Result = c_appointment.getModelList();
                refreshTable(Result);
            } else {
                ArrayList<Appointments> Result = c_appointment.FilterAppointment(type);
                refreshTable(Result);
            }
        } else if (e.getSource() == btnViewPaymentInfo) {
            C_Payment c_payment = new C_Payment();
            String message = c_payment.PaymentDetails(c_appointment.getModel(txtEid.getText()));
            JOptionPane.showMessageDialog(null, message, "Payment details", JOptionPane.PLAIN_MESSAGE);
        }

    }
}
