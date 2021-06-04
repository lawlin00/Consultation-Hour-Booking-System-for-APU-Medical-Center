/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OODJProj1.Controller;

import OODJProj1.DataLayer.D_User;
import OODJProj1.Model.Users;
import OODJProj1.Others.Validation_User;
import com.github.lgooddatepicker.components.TimePicker;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class C_User extends ControllerMessages implements CRUDReadable<Users>{

    public static Users login = null;
    D_User userData = new D_User();
    static TimePicker timePicker;

    public static Users getLogin() {
        return login;
    }

    public static void setLogin(Users login) {
        C_User.login = login;
    }        

    public void create(Users newUser) {
        Boolean isExist = false;
        try {
            for (int i = 0; i < userData.getAll().size(); i++) {
                Users User = userData.getAll().get(i);
                if (newUser.getName().equals(User.getUsername())) {
                    isExist = true;
                    break;
                }
            }

            if (isExist) {
                displayErrorMessage("The Username is exist! Please try another username. ");
            } else {                
                userData.create(newUser);
                displaySuccessMessage("Created Successfully!");
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }


//    public void EditUser(Users EditedUser) {
//        Boolean isExist = false;
//        V_UserList UserAccCMS = new V_UserList(this);
//        try {
//            for (int i = 0; i < data.getUsers().size(); i++) {
//                Users User = data.getUsers().get(i);
//                if (EditedUser.getUsername().equals(User.getUsername()) && EditedUser.getUid() != User.getUid()) {
//                    isExist = true;
//                    break;
//                }
//            }
//
//            if (isExist) {
//                UserAccCMS.displayErrorMessage("The Username is exist! Please try another username. ");
//            } else {
//                ArrayList<String> newList = new ArrayList<String>();
//                for (int i = 0; i < data.getUsers().size(); i++) {
//                    Users allUser = data.getUsers().get(i);
//                    if (EditedUser.getUid() == allUser.getUid()) {
//                        newList.add(EditedUser.newUser());
//                    } else {
//                        newList.add(allUser.newUser());
//                    }
//                }
//
//                if (newList.size() > 0) {
//                    data.delete("Users.txt");
//                    for (int i = 0; i < newList.size(); i++) {
//                        data.writeTo(newList.get(i), "Users.txt");
//                    }
//                }
//                UserAccCMS.displaySuccessMessage("Edited Successfully!");
//            }
//
//        } catch (Exception ex) {
//            System.out.println(ex);
//        }
//    }
    
    @Override
    public void update(Users EditedUser) {

        Boolean isExist = false;
        boolean edited = false;
        //V_UserList UserAccCMS = new V_UserList(this);
        try {
            for (int i = 0; i < userData.getAll().size(); i++) {
                Users User = userData.getAll().get(i);
                if (EditedUser.getUsername().equals(User.getUsername()) && EditedUser.getUid() != User.getUid()) {
                    isExist = true;
                    break;
                }
            }

            if (isExist) {
                //UserAccCMS.displayErrorMessage("The Username is exist! Please try another username. ");
                edited = false;
                displayErrorMessage("Username already exists");
            } else {
                ArrayList<String> newList = new ArrayList<String>();
                ArrayList<Users> newUsersData = new ArrayList<Users>();
                for (int i = 0; i < userData.getAll().size(); i++) {
                    Users allUser = userData.getAll().get(i);
                    if (EditedUser.getUid() == allUser.getUid()) {
                       // newList.add(EditedUser.getLine());
                       newUsersData.add(EditedUser);
                    } else {
                        newUsersData.add(allUser);
                    }
                }

                if (newUsersData.size() > 0) {
                    userData.update(newUsersData);
//                    for (int i = 0; i < newList.size(); i++) {
//                        userData.writeTo(newList.get(i));
//                    }
                }
                //UserAccCMS.displaySuccessMessage("Edited Successfully!");
                edited = true;
                displaySuccessMessage("Updated ");

            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
        public boolean edit(Users EditedUser) {

        Boolean isExist = false;
        boolean edited = false;
        //V_UserList UserAccCMS = new V_UserList(this);
        try {
            for (int i = 0; i < userData.getAll().size(); i++) {
                Users User = userData.getAll().get(i);
                if (EditedUser.getUsername().equals(User.getUsername()) && EditedUser.getUid() != User.getUid()) {
                    isExist = true;
                    break;
                }
            }

            if (isExist) {
                //UserAccCMS.displayErrorMessage("The Username is exist! Please try another username. ");
                edited = false;
            } else {
                ArrayList<String> newList = new ArrayList<String>();
                ArrayList<Users> newUsersData = new ArrayList<Users>();
                for (int i = 0; i < userData.getAll().size(); i++) {
                    Users allUser = userData.getAll().get(i);
                    if (EditedUser.getUid() == allUser.getUid()) {
                       // newList.add(EditedUser.getLine());
                       newUsersData.add(EditedUser);
                    } else {
                        newUsersData.add(allUser);
                    }
                }

                if (newUsersData.size() > 0) {
                    userData.update(newUsersData);
//                    for (int i = 0; i < newList.size(); i++) {
//                        userData.writeTo(newList.get(i));
//                    }
                }
                //UserAccCMS.displaySuccessMessage("Edited Successfully!");
                edited = true;

            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return edited;
    }

    public void delete(Users DeletedUser) {
        Boolean isExist = false;
        try {
            for (int i = 0; i < userData.getAll().size(); i++) {
                Users User = userData.getAll().get(i);
                if (DeletedUser.getUid() == User.getUid()) {
                    isExist = true;
                    break;
                }
            }
            if (isExist) {
                ArrayList<Users> newUserData = new ArrayList<Users>();
                for (int i = 0; i < userData.getAll().size(); i++) {
                    Users allUser = userData.getAll().get(i);
                    if (DeletedUser.getUid() != allUser.getUid()) {
                        newUserData.add(allUser);
                    } else {
                        newUserData.remove(allUser);
                    }
                }

                if (newUserData.size() > 0) {
                    userData.update(newUserData);
//                    for (int i = 0; i < newList.size(); i++) {
//                        userData.writeTo(newList.get(i));
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

    public Users getModel(String uid) {
        Users user = new Users();
        for (int i = 0; i < userData.getAll().size(); i++) {
            Users allUser = userData.getAll().get(i);
            if (uid.equals(String.valueOf(allUser.getUid()))) {
                user = new Users(allUser.getUid(), allUser.getName(), allUser.getUsername(), allUser.getPassword(), allUser.getIsadmin(), allUser.getSelfdescription());
            }
        }
        return user;
    }

    public ArrayList<Users> getModelList() {
        ArrayList<Users> userList = userData.getAll();
        return userList;
    }
    
    public ArrayList<Users> FilterUser(String role){
        boolean isAdmin = false;
        if(role.toLowerCase().equals("Admin")){
            isAdmin = true;
        }
            
        ArrayList<Users> userList = userData.getAll();
        ArrayList<Users> RoleList = new ArrayList<Users>();
        for (Users user : userList) {
            if (user.getIsadmin() == isAdmin) { 
                RoleList.add(user);
            }
        }
        return RoleList;
    }
   
    public ArrayList<Users> SearchUser(String searchkey) {
        ArrayList<Users> userList = userData.getAll();
        ArrayList<Users> SearchUserResult = new ArrayList<Users>();
        for (Users user : userList) {
            if (user.getName().contains(searchkey) | user.getUsername().contains(searchkey)) {
                SearchUserResult.add(user);
            }
        }
        return SearchUserResult;
    }

    public boolean Login(String username, String pwd) {
        boolean LoginSuccess = false;
        for (int i = 0; i < userData.getAll().size(); i++) {
            Users allUser = userData.getAll().get(i);
            if (username.trim().equals(allUser.getUsername().trim()) && pwd.equals(allUser.getPassword())) {
                login = allUser;
                LoginSuccess = true;
                break;
            }
        }
        return LoginSuccess;
    }

    public boolean Logout() {
        login = null;
        Boolean Logout = true;
        return Logout;
    }
    
    public String ChangePwd(String oldpwd, String newpwd, String retypepwd){
        boolean success = false;
        Validation_User validation = new Validation_User();
        boolean notEmpty = validation.required(oldpwd,newpwd,retypepwd);
        String errormsg = "";
        if(notEmpty){
            Users userInfo = getModel(String.valueOf(login.getUid()));
            if(!oldpwd.equals(userInfo.getPassword())){
                System.out.println(userInfo.getPassword());
               errormsg = "Old Password is incorrect! Please try again!";
            }else{
                if(!newpwd.equals(retypepwd)){
                    errormsg = "New password is not same with retype password! Please try again!";
                }
            }
        }else{
            errormsg = "Old Password, New Password, Retype Password fields is Required! ";
        }
        return errormsg;
        
    }

}
