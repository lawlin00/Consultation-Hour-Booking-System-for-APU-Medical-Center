/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OODJProj1.View;

import OODJProj1.Controller.C_Appointment;
import OODJProj1.Controller.C_Patient;
import OODJProj1.Controller.C_Payment;
import OODJProj1.Controller.C_Reports;
import OODJProj1.Controller.C_User;
import OODJProj1.View.*;
import java.time.LocalDate;

/**
 *
 * @author cheon
 */
public class OODJTest2 {

    /**
     * @param args the command line arguments
     */
    public static C_User UserController = new C_User();
    public static V_UserLogin Login = new V_UserLogin(UserController);
    public static V_UserList UserManagement = new V_UserList(UserController);
    public static V_AdminMainMenu AdminMainMenu = new V_AdminMainMenu();
    public static C_Patient PatientController = new C_Patient();
    public static C_Reports ReportController = new C_Reports();
    public static C_Payment PaymentController = new C_Payment();
    public static V_Reports ReportList= new V_Reports(ReportController);
    public static V_PatientList PatientManagement = new V_PatientList(PatientController);  
    public static V_DoctorMainMenu DoctorMainMenu = new V_DoctorMainMenu();  
    public static C_Appointment AppController = new C_Appointment();    
    public static V_AppointmentList AppManagement = new V_AppointmentList(AppController,UserController,PatientController);
    public static V_DoctorPatientView DoctorPatientView = new V_DoctorPatientView(PatientController);  
    public static void main(String[] args) {
        AppController.RemoveInvalidatedApps();
        // TODO code application logic here

//        Users user = new Users(2, "James123", "123", "james", true, "a");
//        //DataLayer data = new DataLayer();
//        //data.writeto(user.newUser(), "Users.txt");
//        System.out.println(user.getIsadmin());
//        Validation validdation = new Validation();
//        boolean test = validdation.NoSpecialChar("is");
//        System.out.println("Validation result: " + test);
//        Login.setVisible(true);
        Login.setVisible(true);
        
        //IndividualProfile.setVisible(true);
      //-------------------
//        Administrator James = new Administrator(1,"James123","123","james",true,"");
//        System.out.println(James.getIsadmin());
//        C_User UserController = new C_User();
//        C_Patient PatientController = new C_Patient();
//        V_PatientList PatientManagement = new V_PatientList(PatientController);  
//        V_UserList UserManagement = new V_UserList(UserController);
       // Login.setVisible(true);

       
//       Patient P = new Patient(1,1234567,"jack","jacky");
//       Appointments appointment = new Appointments(1,P,user,"test",LocalDateTime.now(),100);
//           Patient appPatient = appointment.getAppPatient();
//       System.out.println(appPatient.getPatientName());
//       
//       C_Appointment AppController = new C_Appointment();
        String date1 = "2021-02-01";
        String date2 = "2021-02-28";
        LocalDate localDate = LocalDate.parse(date1);
        LocalDate localDate2 = LocalDate.parse(date2);
        C_Reports c_report = new C_Reports();
//      c_report.GenerateEarnings(localDate,localDate2);
       //V_AppointmentList AppManagement = new V_AppointmentList(AppController,UserController,PatientController);
       
       
//       AppManagement.setVisible(true);
        //DoctorAppointment.setVisible(true);
//       
//       V_PatientSelectPopUp PatientPopUp = new V_PatientSelectPopUp(PatientController);
//       PatientPopUp.setVisible(true);
    }

}
