/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OODJProj1.Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author cheon
 */
public class Appointments extends BaseModel{

    private Patients appPatient;
    private Users appDoctor;
    private String appDescription;
    private LocalDateTime slot;
    private String status;
    private int AppID;

    public Appointments(int AppID, Patients appPatient, Users appDoctor, String appDescription, LocalDateTime slot, String status) {
        this.appPatient = appPatient;
        this.appDoctor = appDoctor;
        this.appDescription = appDescription;
        this.slot = slot;
        this.status = status;
        this.AppID = AppID;
    }

    public Appointments() {
    }

    public int getAppID() {
        return AppID;
    }

    public void setAppID(int AppID) {
        this.AppID = AppID;
    }

    public Patients getAppPatient() {
        return appPatient;
    }

    public void setAppPatient(Patients appPatient) {
        this.appPatient = appPatient;
    }

    public Users getAppDoctor() {
        return appDoctor;
    }

    public void setAppDoctor(Users appDoctor) {
        this.appDoctor = appDoctor;
    }

    public String getAppDescription() {
        return appDescription;
    }

    public void setAppDescription(String appDescription) {
        this.appDescription = appDescription;
    }

    public LocalDateTime getSlot() {
        return slot;
    }

    public void setSlot(LocalDateTime slot) {
        this.slot = slot;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String outstanding) {
        this.status = outstanding;
    }

    @Override
    public String toFileString() {
        int pid = appPatient.getPatientID();
        int did = appDoctor.getUid();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String joinLine = String.join(",", String.valueOf(AppID), String.valueOf(pid), String.valueOf(did), appDescription, slot.format(formatter), String.valueOf(status));
        joinLine += System.lineSeparator();//breakline                
        return joinLine;
    }
}
