/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OODJProj1.View;


import OODJProj1.Controller.C_Reports;
import OODJProj1.Model.Reports;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
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
 * @author cheon
 */
public class V_Reports extends JFrame implements ActionListener {

    DefaultTableModel model;
    JTable Table;
    JScrollPane scroll;
    JLabel lblSearch, lblStartTime, lblEndTime, lblTimeFrame, lblMissed, lblIncome, lblAppointment, Title, lblbegin, lblend, lblFilter, lblBest;
    JTextField txtTimeFrame, txtIncome, txtAppointment, txtBest, txtMissed;
    JButton Switch, btnSearch, Back, refreshTbl, btnGenerate, btnPatient;
    JComboBox cmbReport;    
    private final  C_Reports c_report ;
    DatePickerSettings searchSettings, searchSettings2;
    int appointments, missed;
    double income, best;
    String Bestdoc;
    static DatePicker beginPicker, endPicker;

    public V_Reports(C_Reports reportController) {
        c_report = reportController;
        initComponent();

//        String date1 = "2021-02-01";
//        String date2 = "2021-02-28";
//        LocalDate localDate = LocalDate.parse(date1);
//        LocalDate localDate2 = LocalDate.parse(date2);
//        GenerateEarnings( c_report.GenerateEarnings(localDate,localDate2));
    }

    public void dateChanged(DateChangeEvent event) {
        searchSettings2.setDateRangeLimits(beginPicker.getDate().minusDays(0), beginPicker.getDate().plusDays(30));
    }

    public void initComponent() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200, 800);
        this.setTitle("Income report ");
        this.setLayout(new MigLayout("insets 0 0 0 0"));
        JPanel container = new JPanel();
        container.setLayout(new MigLayout("insets 0 0 0 0, fill, width 1200px, height 800px", "3[]3[]3[]3", "5[]5[]5[]5"));
        container.setBackground(new Color(255, 225, 203));
        //JLabel and JButton Design

        lblTimeFrame = new JLabel("Timeframe : ");
        lblIncome = new JLabel("Total income: ");
        lblAppointment = new JLabel("Total attended appointments: ");
        lblBest = new JLabel("Highest earning Doctor: ");
        lblMissed = new JLabel("Total missed appointments: ");
        txtTimeFrame = new JTextField(20);
        txtIncome = new JTextField(20);
        txtAppointment = new JTextField(20);
        txtBest = new JTextField(100);
        txtMissed = new JTextField(20);

        searchSettings = new DatePickerSettings();
        searchSettings2 = new DatePickerSettings();
        searchSettings.setAllowEmptyDates(false);
        searchSettings2.setAllowEmptyDates(false);
        beginPicker = new DatePicker(searchSettings);

        endPicker = new DatePicker(searchSettings2);

        Title = new JLabel("Income report ");
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

        btnGenerate = new JButton("Generate Report");
        cmbReport = new JComboBox();

        lblStartTime = new JLabel("From: ");
        lblStartTime.setFont(new Font("Arial", 1, 14));

        lblEndTime = new JLabel("To: ");
        lblEndTime.setFont(new Font("Arial", 1, 14));

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

        btnSearch = new JButton("Generate ");
        btnSearch.setFont(new Font("STXihei", 1, 18));

        JPanel UserActionPanel = new JPanel(new MigLayout("align 50% 50%"));
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        UserActionPanel.setBackground(new Color(255, 225, 203));
        UserActionPanel.setLayout(new MigLayout("width 500px, height 800px"));

        //Tab Panel 1
        JPanel tab1 = new JPanel(new MigLayout(""));

        tab1.add(lblTimeFrame, "span 2");
        tab1.add(txtTimeFrame, "span 2,wrap,pushx,growx");
        tab1.add(lblIncome, "span 2");
        tab1.add(txtIncome, "span 2,wrap,pushx,growx");
        tab1.add(lblAppointment, "span 2");
        tab1.add(txtAppointment, "span 2,wrap,pushx,growx");
        tab1.add(lblMissed, "span 2");
        tab1.add(txtMissed, "span 2,wrap,pushx,growx");
        tab1.add(lblBest, "span 2");
        tab1.add(txtBest, "span 2,wrap,pushx,growx");
        tabbedPane.addTab("Report Statistics ", tab1);

        //add componentanel
        container.add(Title, "dock north, pushx, growx,wrap,gapx 15 15, gapy 50 15");
        PatientListPanel.add(lblStartTime, "split 5,  pushx, growx, gapy 15 5");
        PatientListPanel.add(beginPicker, "split 5, span, pushx, growx,gapx 25 25, gapy 15 5");
        PatientListPanel.add(lblEndTime, "split 5,  pushx, growx, gapy 15 5");
        PatientListPanel.add(endPicker, "split 5, span, pushx, growx,gapx 25 25, gapy 15 5");
        PatientListPanel.add(btnSearch, "split 5, span,wrap,gapx 25 25, gapy 15 5, width 150:70:200");
        PatientListPanel.add(scroll, "dock center, pushx,pushy, growx,growy, gapx 25 25, gapy 5 25");
        PatientListPanel.add(Back, "dock north,right,wrap,gapx 15 15, gapy 15 15,width 40:70:200");

        UserActionPanel.add(tabbedPane, "dock center, pushx,pushy, growx,growy, gapy 0 28");
        UserActionPanel.add(Back, "dock north,right,wrap,gapx 15 15, gapy 15 15,width 40:70:200");
        container.add(PatientListPanel, "west, pushx, growx, gapx 15 0");
        container.add(UserActionPanel, "east, gapx 15 15");

        btnSearch.addActionListener(this);
        Back.addActionListener(this);
        this.add(container, "dock center");
        this.pack();
    }
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");

    public void GenerateEarnings(ArrayList<Reports> ReportList) {
        best = 0;
        Bestdoc = "not applicable";
        income = 0;
        appointments = 0;
        missed = 0;
        System.out.println("Generating");
        ArrayList<Reports> reports = ReportList;
        model = (DefaultTableModel) Table.getModel();
        model.setColumnCount(0);
        model.setRowCount(0);
        model.addColumn("num ");
        model.addColumn("Doctor ID ");
        model.addColumn("Doctor Name ");
        model.addColumn("Total attended ");
        model.addColumn("Total missed ");
        model.addColumn("Total Income ");
        for (int i = 0; i < reports.size(); i++) {
            Reports report = reports.get(i);
            int docid = report.getDocID();
            int appnum = report.getCount();
            appointments += appnum;
//            Users user = c_users.getModel(Integer.toString(docid));
            int missnum = c_report.CountMissed(docid, beginPicker.getDate(), endPicker.getDate());
//            missed += missnum;
            double amount = report.getTotalEarned();
            if (best < amount && amount > 0) {
                best = amount;
                Bestdoc = report.getDocName();
            }
            income += amount;
            String docname = report.getDocName();
            model.addRow(new Object[]{
                Integer.toString(i + 1),
                Integer.toString(docid),
                docname,
                Integer.toString(appnum),
                Integer.toString(missnum),
                Double.toString(amount),});
            int lastRow = model.getRowCount() - 1;
            Object LastID = Table.getValueAt(lastRow, 1);
        }
        txtIncome.setText(Double.toString(income));
        missed = c_report.CountMissed(beginPicker.getDate(), endPicker.getDate());
        txtMissed.setText(Integer.toString(missed));
        txtAppointment.setText(Integer.toString(appointments));
        String date1 = beginPicker.getDate().format(formatter);
        String date2 = endPicker.getDate().format(formatter);
        txtTimeFrame.setText(date1 + " to " + date2);
        txtBest.setText(Bestdoc);
    }
////        btnPatient.addActionListener(this);

////        refreshTbl.addActionListener(this);
//        cmbFilter.addActionListener(this);
//        Back.addActionListener(this);
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSearch) {
            if (endPicker.getDate().isBefore(beginPicker.getDate())) {
                JOptionPane.showMessageDialog(this, "The end date must be after the start date ", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                System.out.println(beginPicker.getDate());
                GenerateEarnings(c_report.GenerateEarnings(beginPicker.getDate(), endPicker.getDate()));
            }
        } else if (e.getSource() == Back) {
            OODJTest2.AdminMainMenu.setVisible(true);
            this.setVisible(false);
        }
    }
}
