/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OODJProj1.Model;

/**
 *
 * @author cheon
 */
public class Patients extends BaseModel{
    private int PatientID;
    private long PatientPhone;
    private String PatientName, PatientDetails;
    private String line;

    public Patients(int PatientID,long PatientPhone, String PatientName, String PatientDetails) {
        this.PatientID = PatientID;
        this.PatientName = PatientName;
        this.PatientDetails = PatientDetails;
        this.PatientPhone = PatientPhone;
     
    }

    public Patients() {
    }

    public long getPatientPhone() {
        return PatientPhone;
    }

    public void setPatientPhone(long PatientPhone) {
        this.PatientPhone = PatientPhone;
    }

    public int getPatientID() {
        return PatientID;
    }

    public void setPatientID(int PatientID) {
        this.PatientID = PatientID;
    }

    public String getPatientName() {
        return PatientName;
    }

    public void setPatientName(String PatientName) {
        this.PatientName = PatientName;
    }

    public String getPatientDetails() {
        return PatientDetails;
    }

    public void setPatientDetails(String PatientDetails) {
        this.PatientDetails = PatientDetails;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }
    
    public String toFileString() {        
        String joinLine = String.join(",", String.valueOf(PatientID), String.valueOf(PatientPhone),PatientName, PatientDetails);
        joinLine += System.lineSeparator();//breakline
        //System.out.println(role);
        this.line = joinLine;        
        return joinLine;
    }
  

}
