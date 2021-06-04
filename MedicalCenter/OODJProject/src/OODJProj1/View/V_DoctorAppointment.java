/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OODJProj1.View;

import OODJProj1.Controller.C_Appointment;
import OODJProj1.Controller.C_Payment;
import OODJProj1.Controller.C_User;
import OODJProj1.Model.Appointments;
import OODJProj1.Model.Users;
import OODJProj1.Model.Patients;
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
 * @author User
 */
public class V_DoctorAppointment extends JFrame implements ActionListener {

    DefaultTableModel model;
    JButton Back, btnSearch, refreshTbl, btnProceedPayment, btnViewPaymentInfo;
    JTable Table;
    JScrollPane scroll;
    JTextField txtSearch, txtAid, txtPhone, txtName, txtDesc, txtAppTime;
    static DatePicker beginPicker, endPicker;
    static DateTimePicker timePicker;
    final LocalDate today = LocalDate.now();
    private final C_Appointment c_appointment;
    JComboBox cmbFilter;
    JLabel lblFilter, lblAid, lblAppTime, lblPhone, lblPatient, lbldesc;
    JPanel tab1;
    final Users currentUser = C_User.login;

    public V_DoctorAppointment(C_Appointment AppointmentController) {
        c_appointment = AppointmentController;
        init();
        refreshTable(c_appointment.GenerateAppList(currentUser));
    }

    public void init() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200, 800);
        this.setTitle("My Appointments");
        this.setLayout(new MigLayout("insets 0 0 0 0"));
        //this.setResizable(false);
        JPanel container = new JPanel();
        container.setLayout(new MigLayout("insets 0 0 0 0, fill, width 1200px, height 800px"));
        container.setBackground(new Color(255, 225, 203));

        //JPanel TitlePanel = new JPanel();
        //TitlePanel.setLayout(new MigLayout());
        JLabel lblTitle = new JLabel("My Appointments");
        lblTitle.setFont(new Font("Imprint MT Shadow", Font.BOLD, 56));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setForeground(new Color(79, 77, 77));

        TimePickerSettings timeSettings;
        DatePickerSettings dateSettings, searchSettings, searchSettings2;

        dateSettings = new DatePickerSettings();
        searchSettings = new DatePickerSettings();
        searchSettings2 = new DatePickerSettings();
        timeSettings = new TimePickerSettings();
        dateSettings.setAllowEmptyDates(false);
        timeSettings.setAllowEmptyTimes(false);
        searchSettings.setAllowEmptyDates(false);
        searchSettings2.setAllowEmptyDates(false);
        timeSettings.generatePotentialMenuTimes(TimePickerSettings.TimeIncrement.OneHour, null, null);
        timePicker = new DateTimePicker(dateSettings, timeSettings);

        LocalDate now = LocalDate.now();
        LocalDate tomorrow = now.plusDays(1);
        LocalDateTime tomorrowvalid = tomorrow.atTime(10, 0);
        timePicker.setDateTimePermissive(tomorrowvalid);
        timeSettings.setVetoPolicy(new C_Appointment.AppointmentTimePolicy());
        dateSettings.setDateRangeLimits(today.minusDays(-1), today.plusDays(20));

        beginPicker = new DatePicker(searchSettings);
        endPicker = new DatePicker(searchSettings2);

        refreshTbl = new JButton("Refresh Table");
        refreshTbl.setFont(new Font("Stika Heading", Font.BOLD, 18));
        refreshTbl.setHorizontalAlignment(SwingConstants.CENTER);
        refreshTbl.setBackground(new Color(176, 255, 212));

        Back = new JButton("Back to Main Menu");
        Back.setFont(new Font("Stika Heading", Font.BOLD, 18));
        Back.setHorizontalAlignment(SwingConstants.CENTER);
        Back.setBackground(new Color(255, 214, 207));

        //PatientListPanel with table Design
        JPanel PatientListPanel = new JPanel();
        PatientListPanel.setBackground(new Color(255, 225, 203));
        PatientListPanel.setLayout(new MigLayout(" insets 2 2 2 2 , fillx,width 1000px, height 800px"));
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

        txtName = new JTextField(20);
        txtName.setEditable(false);
        txtName.setFont(new Font("Arial", 1, 14));
        txtDesc = new JTextField(20);
        txtDesc.setEditable(false);
        txtDesc.setFont(new Font("Arial", 1, 14));
        txtName.setFont(new Font("STXihei", 1, 14));
        txtAid = new JTextField(20);
        txtAid.setEditable(false);
        txtAid.setFont(new Font("Arial", 1, 14));
        txtPhone = new JTextField(20);
        txtPhone.setEditable(false);
        txtPhone.setFont(new Font("Arial", 1, 14));
        txtAppTime = new JTextField(20);
        txtAppTime.setEditable(false);
        txtAppTime.setFont(new Font("Arial", 1, 14));
        lblAppTime = new JLabel("Appointment Time");
        lblAppTime.setFont(new Font("Arial", 1, 14));
        lblPatient = new JLabel("Patient Name: ");
        lblPatient.setFont(new Font("Arial", 1, 14));
        lbldesc = new JLabel("Description: ");
        lbldesc.setFont(new Font("Arial", 1, 14));
        lblAid = new JLabel("Appointment ID: ");
        lblAid.setFont(new Font("Arial", 1, 14));
        lblPhone = new JLabel("Patient Phone: ");
        lblPhone.setFont(new Font("Arial", 1, 14));
        btnProceedPayment = new JButton("Proceed to Payment");
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

        // container.add(lblTitle, "dock north, pushx, growx,wrap,gapx 15 15, gapy 15 15");
        container.add(lblTitle, "dock north, pushx, growx,wrap,gapx 15 15, gapy 50 15");
        PatientListPanel.add(refreshTbl, "dock north,right, pushx, growx,wrap,gapx 25 25, gapy 15 5, width 50:70:200");
        PatientListPanel.add(beginPicker, "split 3, span, pushx, growx,gapx 25 25, gapy 15 5");
        PatientListPanel.add(endPicker, "split 3, span, pushx, growx,gapx 25 25, gapy 15 5");
        PatientListPanel.add(btnSearch, "split 3, span,wrap,gapx 25 25, gapy 15 5, width 150:70:200");
        PatientListPanel.add(scroll, "dock center, pushx,pushy, growx,growy, gapx 25 25, gapy 5 25");

        JPanel UserActionPanel = new JPanel(new MigLayout("align 50% 50%"));
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        UserActionPanel.setBackground(new Color(255, 225, 203));
        UserActionPanel.setLayout(new MigLayout("width 500px, height 800px"));

        tab1 = new JPanel(new MigLayout());
        tab1.add(lblAid, "span 2, gapy 5 5");
        tab1.add(txtAid, "span 2, wrap, pushx, growx, gapy 5 5");
        tab1.add(lblAppTime, "span 2, gapy 5 5");
        tab1.add(txtAppTime, "span 2,wrap,pushx,growx, gapy 5 5");
        tab1.add(lblPhone, "span 2, gapy 5 5");
        tab1.add(txtPhone, "span 2, wrap, pushx, growx, gapy 5 5");
        tab1.add(lblPatient, "span 2, gapy 5 5");
        tab1.add(txtName, "span 2, wrap, pushx, growx, gapy 5 5");
        tab1.add(lbldesc, "span 2, gapy 5 5");
        tab1.add(txtDesc, "span 2,wrap, pushx, growx, gapy 5 5");
        tab1.add(btnProceedPayment, "cell 3 6 ,right, pushx, growx, hidemode 3");
        tab1.add(btnViewPaymentInfo, "cell 3 6 ,right, pushx, growx, hidemode 3");
        tabbedPane.addTab("Appointment Details", tab1);
        tabbedPane.setFont(new Font("Arial", 1, 16));
        tab1.setBackground(new Color(255, 248, 242));

        UserActionPanel.add(tabbedPane, "dock center, pushx,pushy, growx,growy, gapy 0 28");
        UserActionPanel.add(Back, "dock north,right,wrap,gapx 15 15, gapy 15 15,width 40:70:200");
        UserActionPanel.add(FilterPanel, "north, right, wrap, gapx 15 15, gapy 5 15,width 220:70:260");
        container.add(PatientListPanel, "west, pushx, growx, gapx 15 0");
        container.add(UserActionPanel, "east, gapx 15 15");

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
                    FillAppDetail(row);
                    System.out.println(Table.getValueAt(row, 0).toString());
                }
            }
        });

        refreshTbl.addActionListener(this);
        Back.addActionListener(this);
        cmbFilter.addActionListener(this);
        btnSearch.addActionListener(this);
        btnProceedPayment.addActionListener(this);
        btnViewPaymentInfo.addActionListener(this);
        btnProceedPayment.setVisible(false);
        //container.add(SelectionPanel,"dock south, growx, pushx, growy, pushy, span, gapy 100 50");        
        this.add(container, "dock center");
        this.pack();
    }

    public void FillAppDetail(int row) {
        txtAid.setText(Table.getValueAt(row, 1).toString());
        txtAppTime.setText(Table.getValueAt(row, 5).toString());
        txtPhone.setText(Table.getValueAt(row, 3).toString());
        txtName.setText(Table.getValueAt(row, 2).toString());
        txtDesc.setText(Table.getValueAt(row, 4).toString());
        switch (Table.getValueAt(row, 6).toString()) {
            case "Complete":
                btnProceedPayment.setVisible(false);
                btnViewPaymentInfo.setVisible(true);
                break;
        //txtPhone.setText(Table.getValueAt(row,6).toString());
            case "Valid":
                btnViewPaymentInfo.setVisible(false);
                btnProceedPayment.setVisible(true);
                break;
            default:
                btnViewPaymentInfo.setVisible(false);
                btnProceedPayment.setVisible(false);
                break;
        }
    }

    public void refreshTable(ArrayList<Appointments> appList) {
        ArrayList<Appointments> allAppointments = appList;
        model = (DefaultTableModel) Table.getModel();
        model.setColumnCount(0);
        model.setRowCount(0);
        model.addColumn("No.");
        model.addColumn("Appointment ID");
        model.addColumn("Appointment Patient");
        model.addColumn("Patient Number");
        model.addColumn("Appointment reason");
        model.addColumn("Appointment time");
        model.addColumn("Appointment Status");
        if (allAppointments.size() > 0) {
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
                    appointment.getAppDescription(),
                    appointment.getSlot(),
                    appointment.getStatus()

                });
            }
            int lastRow = model.getRowCount() - 1;
            Object LastID = Table.getValueAt(lastRow, 1);
            setColor();
        }

        // NewID = Integer.valueOf((String) LastID) + 1;  
        // txtCuid.setText(String.valueOf(NewID));        
    }

    public void setColor() {
        Table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            //  @Override
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                Color c = new Color(204, 255, 255);
                Color t = new Color(102, 51, 0);
                //Color c = Color.BLACK;
                // Color t = Color.WHITE;
                Object AppStatus = (String) table.getValueAt(row, 6);
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

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == refreshTbl) {
            refreshTable(c_appointment.GenerateAppList(currentUser));
            cmbFilter.setSelectedIndex(0);
        } else if (e.getSource() == btnSearch) {
            ArrayList<Appointments> results = c_appointment.GenerateAppList(beginPicker.getDate(), endPicker.getDate());
            refreshTable(results);
        } else if (e.getSource() == cmbFilter) {
            String type = "All";
            String selected = cmbFilter.getItemAt(cmbFilter.getSelectedIndex()).toString();
            if (selected.contains(" ")) {
                selected = selected.substring(0, selected.indexOf(" "));
                type = selected;
            }            
            if (!type.equals("All")) {
                ArrayList<Appointments> Result = c_appointment.FilterAppointment(currentUser, type);
                refreshTable(Result);
            } else {
                ArrayList<Appointments> Result = c_appointment.GenerateAppList(currentUser);
                refreshTable(Result);
            }

        } else if (e.getSource() == btnProceedPayment) {
            if(c_appointment.getModel(txtAid.getText()).getSlot().isAfter(LocalDateTime.now())){
                JOptionPane.showMessageDialog(null,"Current time is before scheduled time ", "Payment details", JOptionPane.PLAIN_MESSAGE);
            }
            else{
                this.setVisible(false);
                new V_Payment_SetPrice(c_appointment.getModel(txtAid.getText())).setVisible(true);
            }
        } else if (e.getSource() == btnViewPaymentInfo) {
            C_Payment c_payment = new C_Payment();
            String message = c_payment.PaymentDetails(c_appointment.getModel(txtAid.getText()));
            JOptionPane.showMessageDialog(null, message, "Payment details", JOptionPane.PLAIN_MESSAGE);
        } else if (e.getSource() == Back) {
            OODJTest2.DoctorMainMenu.setVisible(true);
            this.setVisible(false);
        }
    }
}
