/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OODJProj1;

import OODJProj1.Model.Users;

/**
 *
 * @author cheon
 */
public class ComboBoxItem {
    String displayName;
    Users hiddenID;

    public ComboBoxItem(Users hiddenID,String displayName ) {
        this.displayName = displayName;
        this.hiddenID = hiddenID;
    }
    
    public Users getHiddenID(){
    return hiddenID;
}
    public String toString()
    {
        return displayName;
    }
    
}
