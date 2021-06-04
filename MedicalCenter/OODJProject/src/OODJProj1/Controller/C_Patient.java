
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OODJProj1.Controller;

import OODJProj1.DataLayer.D_Patient;

import OODJProj1.Model.Patients;
import java.util.ArrayList;

/**
 *
 * @author cheon
 */
public class C_Patient extends ControllerMessages implements CRUDReadable<Patients>{

    private final D_Patient PatientData = new D_Patient();   

    public void create(Patients patient) {                
        Boolean isExist = false;                                            
        try {
            for (int i = 0; i < PatientData.getAll().size(); i++) {  
                Patients patients = PatientData.getAll().get(i);   
                Long checknum = patient.getPatientPhone();
                if (checknum.equals(patients.getPatientPhone())) {
                    isExist = true;
                    break;
                }
            }  

            if (isExist) {                
                displayErrorMessage("This phone number is already in use");                
            } else { 
              
                System.out.println(patient.toFileString());
                PatientData.create(patient);
                displaySuccessMessage("Created Successfully!");    
                                              
            }
            
        } catch (Exception ex) {
            System.out.println(ex);
        }       
    }
   public Patients getModel(String PatientID) {
        Patients patientInfo = new Patients();
        for (int i = 0; i < PatientData.getAll().size(); i++) {
            Patients patient = PatientData.getAll().get(i);
            if (PatientID.equals(String.valueOf(patient.getPatientID()))) {
            patientInfo = new Patients(patient.getPatientID(), patient.getPatientPhone(), patient.getPatientName(),patient.getPatientDetails());
            }
        }
        return patientInfo;
    }  
  
 public ArrayList<Patients> getModelList(String searchkey) {
        ArrayList<Patients> patientList = PatientData.getAll();
        ArrayList<Patients> SearchPatientResult = new ArrayList<Patients>();
        for(Patients patient : patientList){
            if(patient.getPatientName().contains(searchkey)){
                SearchPatientResult.add(patient);
            }
        }
        return SearchPatientResult;
    }
 
    public void update( Patients EditedPatient) {
        Boolean isExist = false;
        try {
            for (int i = 0; i < PatientData.getAll().size(); i++) {
                Patients patient = PatientData.getAll().get(i);
                if (EditedPatient.getPatientPhone()==(patient.getPatientPhone()) && EditedPatient.getPatientID() != patient.getPatientID()) {
                    isExist = true;
                    break;
                }
            }

            if (isExist) {
                displayErrorMessage("A patient with this phone number already exists.");
            } else {
                ArrayList<Patients> newList = new ArrayList<Patients>();
                for (int i = 0; i < PatientData.getAll().size(); i++) {
                   Patients allpatients = PatientData.getAll().get(i);
                    if (EditedPatient.getPatientID() == allpatients.getPatientID()) {
                        newList.add(EditedPatient);
                    } else {
                        newList.add(allpatients);
                    }
                }

                if (newList.size() > 0) {
                    PatientData.update(newList);
//                    for (int i = 0; i < newList.size(); i++) {
//                        PatientData.writeTo(newList.get(i));
//                    }
                }
                displaySuccessMessage("Edited Successfully!");

            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void delete( Patients DeletedPatient) {
        Boolean isExist = false;
        try {
            for (int i = 0; i < PatientData.getAll().size(); i++) {
                Patients patient = PatientData.getAll().get(i);
                if (DeletedPatient.getPatientID() == patient.getPatientID()) {
                    isExist = true;
                    break;
                }
            }            
            if (isExist) {
                ArrayList<Patients> newList = new ArrayList<Patients>();
                for (int i = 0; i < PatientData.getAll().size(); i++) {
                   Patients patient = PatientData.getAll().get(i);
                    if (DeletedPatient.getPatientID() != patient.getPatientID()) {
                        newList.add(patient);
                    } else {
                        newList.remove(patient);
                    }
                }

                if (newList.size() > 0) {
                    PatientData.update(newList);
//                    for (int i = 0; i < newList.size(); i++) {
//                        PatientData.writeTo(newList.get(i));
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


    @Override
    public ArrayList<Patients> getModelList() {
        ArrayList<Patients> patientList = PatientData.getAll();
        return patientList;
    }


}

