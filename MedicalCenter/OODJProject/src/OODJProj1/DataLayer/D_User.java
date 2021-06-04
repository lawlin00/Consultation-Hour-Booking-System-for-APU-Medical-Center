/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OODJProj1.DataLayer;

import OODJProj1.Model.Users;
import OODJProj1.Others.helper;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class D_User extends BaseDataLayer {

    private final String filename = "Users.txt";
    Users user = new Users();

    @Override
    public ArrayList<Users> getAll() {
        Boolean IsAdmin = false;
        List<String> user = readFiles(filename);
        ArrayList<Users> allUser = new ArrayList<Users>();
        for (String line : user) {
            String[] split = line.split(",");
            if (split[4].equals("1")) {
                IsAdmin = true;
            } else {
                IsAdmin = false;
            }
            Users User = new Users(Integer.parseInt(split[0]), split[1], split[2], split[3], IsAdmin, split[5]);
            allUser.add(User);
        }
        return allUser;
    }
    
    public void create(Users newData){
       create(newData.toFileString(), filename);       
    }
    
    public void update(ArrayList<Users> newData){
        ArrayList<String> DataList = helper.ModelToString(newData);
        update(DataList, filename);        
    }


}
