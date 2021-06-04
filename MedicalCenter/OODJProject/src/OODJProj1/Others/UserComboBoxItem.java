/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OODJProj1.Others;

import OODJProj1.Model.Users;

/**
 *
 * @author cheon
 */
public class UserComboBoxItem {
    String displayName;
    Users hiddenID;

    public UserComboBoxItem(Users HiddenUser,String displayName ) {
        this.displayName = displayName;
        this.hiddenID = HiddenUser;
    }
    
    public Users getUser(){
    return hiddenID;
}
    public String toString()
    {
        return displayName;
    }
    
}
