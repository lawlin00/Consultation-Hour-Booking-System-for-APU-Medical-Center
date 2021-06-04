/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OODJProj1.DataLayer;

import OODJProj1.Model.Patients;
import OODJProj1.Others.helper;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class D_Patient extends BaseDataLayer {
private final String filename = "Patients.txt";
    @Override
    public ArrayList<Patients> getAll() {
        List<String> Patients = readFiles(filename);
        ArrayList<Patients> allPatients = new ArrayList<Patients>();
        for (String line : Patients) {
            String[] split = line.split(",");
            Patients x = new Patients(Integer.parseInt(split[0]), Long.parseLong(split[1]), split[2], split[3]);
            allPatients.add(x);
        }
        return allPatients;
    }
    
     public void create(Patients newData) {
        create(newData.toFileString(), filename);
    }

    public void update(ArrayList<Patients> newData) {
        ArrayList<String> DataList = helper.ModelToString(newData);
        update(DataList, filename);
    }
}
