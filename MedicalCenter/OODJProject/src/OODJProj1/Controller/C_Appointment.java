/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OODJProj1.Controller;

import OODJProj1.DataLayer.D_Appointment;
import OODJProj1.Model.Users;
import OODJProj1.Model.Appointments;
import OODJProj1.Model.Patients;
import com.github.lgooddatepicker.optionalusertools.PickerUtilities;
import com.github.lgooddatepicker.optionalusertools.TimeVetoPolicy;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author cheon
 */
public class C_Appointment extends ControllerMessages implements CRUDReadable<Appointments> {

    D_Appointment AppointmentData = new D_Appointment();
    private int count;


    public static class AppointmentTimePolicy implements TimeVetoPolicy { //Used to limit doctor's available times in GUI
         //lgoodDateTimepicker 
        @Override
        public boolean isTimeAllowed(LocalTime time) {
            // Only allow times from 9a to 5p, inclusive.
            if (PickerUtilities.isLocalTimeInRange(
                    time, LocalTime.of(9, 00), LocalTime.of(12, 00), true)) {
                return true;
            }
            if (PickerUtilities.isLocalTimeInRange(
                    time, LocalTime.of(14, 00), LocalTime.of(18, 00), true)) {
                return true;
            }
            return false;
        }
    }

    @Override
    public void create( Appointments appointment) { //Create/write appointment
        Boolean isPatientExist = false;
        Boolean isDoctorExist = false;
        try {
            for (int i = 0; i < AppointmentData.getAll().size(); i++) {
                Appointments Appointments = AppointmentData.getAll().get(i);
                Patients appPatient = Appointments.getAppPatient();
                Users appDoctor = Appointments.getAppDoctor();
                int checkp = appointment.getAppPatient().getPatientID();
                int checkd = appointment.getAppDoctor().getUid();
                if (appointment.getSlot().equals(Appointments.getSlot()) && (checkp == (appPatient.getPatientID()))) {
                    isPatientExist = true;
                    break;
                }
                if (appointment.getSlot().equals(Appointments.getSlot()) && (checkd == (appDoctor.getUid()))) {
                    isDoctorExist = true;
                    break;
                }
            }
            if (isPatientExist) {
                displayErrorMessage("Patient has already booked identical timeslot  ");
            } else if (isDoctorExist) {
                displayErrorMessage("Doctor is already occupied at this time ");
            } else {
                System.out.println(appointment.toFileString());
                AppointmentData.create(appointment);
                displaySuccessMessage("Appointment scheduled");

            }

        }catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public ArrayList<Appointments> getModelList() { 
        ArrayList<Appointments> appList = AppointmentData.getAll();
        return appList;  
    }

    public ArrayList<Appointments> GenerateAppList(LocalDate begin, LocalDate end) {
        //Retreives all appointment objects within specified date range
        ArrayList<Appointments> appointmentlist = AppointmentData.getAll();
        ArrayList<Appointments> searchresult = new ArrayList<Appointments>();
        for (Appointments appointment : appointmentlist) {
            if (appointment.getSlot().toLocalDate().isBefore(end.plusDays(1)) && appointment.getSlot().toLocalDate().isAfter(begin.minusDays(1))) {
                searchresult.add(appointment);
            }
        }
        return searchresult;
    }

    public ArrayList<Appointments> GenerateAppList(Users doctor) {
        //Retreives all appointment objects for specific user/doctor
        ArrayList<Appointments> appointmentlist = AppointmentData.getAll();
        ArrayList<Appointments> DoctorAppointment = new ArrayList<Appointments>();
        for (Appointments appointment : appointmentlist) {
            if (appointment.getAppDoctor().getUid() == doctor.getUid()) {
                DoctorAppointment.add(appointment);
            }
        }
        return DoctorAppointment;
    }

    public void delete(Appointments DeletedApp) {
        Boolean isExist = false;
        try {
            for (int i = 0; i < AppointmentData.getAll().size(); i++) {
                Appointments appointment = AppointmentData.getAll().get(i);
                if (DeletedApp.getAppID() == appointment.getAppID()) {
                    isExist = true;
                    break;
                }
            }
            if (isExist) {
                ArrayList<Appointments> newAppointmentsData = new ArrayList<Appointments>();
                for (int i = 0; i < AppointmentData.getAll().size(); i++) {
                    Appointments appointment = AppointmentData.getAll().get(i);
                    if (DeletedApp.getAppID() != appointment.getAppID()) {
                        newAppointmentsData.add(appointment);
                        System.out.println("added to new list");
                    } else {
                        newAppointmentsData.remove(appointment);
                        System.out.println("removed");
                    }
                }
                if (newAppointmentsData.size() > 0) {
                    AppointmentData.update(newAppointmentsData);
//                    for (int i = 0; i < newList.size(); i++) {
//                        AppointmentData.writeTo(newList.get(i));
//                        System.out.println("writing");
//                    }
                }
                displaySuccessMessage("Deleted Successfully!");
            } else {
                displayErrorMessage("Unexpected error occurs!");
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    
    public void deleteMessageless(Appointments DeletedApp) {
        Boolean isExist = false;
        try {
            for (int i = 0; i < AppointmentData.getAll().size(); i++) {
                Appointments appointment = AppointmentData.getAll().get(i);
                if (DeletedApp.getAppID() == appointment.getAppID()) {
                    isExist = true;
                    break;
                }
            }
            if (isExist) {
                ArrayList<Appointments> newAppointmentsData = new ArrayList<Appointments>();
                for (int i = 0; i < AppointmentData.getAll().size(); i++) {
                    Appointments appointment = AppointmentData.getAll().get(i);
                    if (DeletedApp.getAppID() != appointment.getAppID()) {
                        newAppointmentsData.add(appointment);
                        System.out.println("added to new list");
                    } else {
                        newAppointmentsData.remove(appointment);
                        System.out.println("removed");
                    }
                }
                if (newAppointmentsData.size() > 0) {
                    AppointmentData.update(newAppointmentsData);

                }
            } 
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public Appointments getModel(String AppID) {
        Appointments appInfo = new Appointments();
        for (int i = 0; i < getModelList().size(); i++) {
            Appointments appointment = getModelList().get(i);
            if (AppID.equals(String.valueOf(appointment.getAppID()))) {
                System.out.println(appointment.getAppID());
                appInfo = appointment;
                break;
            }
        }
        System.out.println(appInfo.getAppDescription() + "fetching...");
        return appInfo;
    }

    public void update(Appointments App) {
        ArrayList<Appointments> newAppointmentsData = new ArrayList<Appointments>();
        for (int i = 0; i < getModelList().size(); i++) {
            Appointments allAppointments = getModelList().get(i);
            if (App.getAppID() != allAppointments.getAppID()) {
                newAppointmentsData.add(allAppointments);
            } else {
                newAppointmentsData.add(App);
            }
        }

        if (newAppointmentsData.size() > 0) {
            AppointmentData.update(newAppointmentsData);
//            for (int i = 0; i < newList.size(); i++) {
//                AppointmentData.writeTo(newList.get(i));
//            }
        }
    }


    public ArrayList<Appointments> FilterAppointment(String type) {
        //Appointment filter used to filter by appointment statuses
        if (type.equals("Pending")) {
            type = "Valid";
        }
        ArrayList<Appointments> appointmentlist = AppointmentData.getAll();
        ArrayList<Appointments> ResultList = new ArrayList<Appointments>();
        for (Appointments appointment : appointmentlist) {
            if (appointment.getStatus().equals(type)) {
                ResultList.add(appointment);
            }
        }
        return ResultList;
    }

    public ArrayList<Appointments> FilterAppointment(Users doctor, String type){
        //Appointment filter used to filter appointment by users and status
        if (type.equals("Pending")) {
            type = "Valid";
        }
        ArrayList<Appointments> appointmentlist = AppointmentData.getAll();
        ArrayList<Appointments> ResultList = new ArrayList<Appointments>();
        for (Appointments appointment : appointmentlist) {
            if (appointment.getStatus().equals(type) && 
                appointment.getAppDoctor().getUid() == doctor.getUid()) {
                    ResultList.add(appointment);
               }
            }
        return ResultList;
    }

    public void SetMissedAppointments() {
        //Sets all appointments that have missed their scheduled date and time to missed.
        try {
            ArrayList<Appointments> newAppointmentsData = new ArrayList<Appointments>();
            for (int i = 0; i < AppointmentData.getAll().size(); i++) {
                Appointments appointment = AppointmentData.getAll().get(i);
                if (appointment.getSlot().isBefore(LocalDateTime.now().minusHours(1)) && !appointment.getStatus().equals("Complete")) {
                    appointment.setStatus("Missed");
                    newAppointmentsData.add(appointment);
                    System.out.println("added to new list");
                } else {
                    newAppointmentsData.add(appointment);
                }
            }
            if (newAppointmentsData.size() > 0) {
                AppointmentData.update(newAppointmentsData);
//                    for (int i = 0; i < newList.size(); i++) {
//                        AppointmentData.writeTo(newList.get(i));
//                        System.out.println("writing");
//                    }
            } else {
                displayErrorMessage("Unexpected error occurs!");
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void RemoveInvalidatedApps() {
        //Removes all appointment records for which their patients or doctors have been deleted.
        try {
            ArrayList<Appointments> newAppointmentsData = new ArrayList<Appointments>();
            for (int i = 0; i < AppointmentData.getAll().size(); i++) {
                Appointments appointment = AppointmentData.getAll().get(i);
                if (appointment.getAppDoctor().getName() == null || appointment.getAppPatient().getPatientName() == null) {
                    deleteMessageless(appointment);
                    System.out.println("Invalid app deleted");
                } else {
                    newAppointmentsData.add(appointment);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

}
