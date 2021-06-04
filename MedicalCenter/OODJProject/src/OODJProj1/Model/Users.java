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
public class Users extends BaseModel{

    private String username, password, name, selfdescription, line;
    boolean isadmin;    
    
    private int uid;

    public Users(int uid, String name, String username, String password, boolean isadmin, String selfdescription) {
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.name = name;
        this.isadmin = isadmin;
        this.selfdescription = selfdescription;   
        toFileString();
    }

    public Users() {

    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsadmin() {
        return isadmin;
    }

    public void setIsadmin(boolean isadmin) {
        this.isadmin = isadmin;
    }

    public String getSelfdescription() {
        return selfdescription;
    }

    public void setSelfdescription(String selfdescription) {
        this.selfdescription = selfdescription;
    }

    public String getLine() {
        return line;
    }

    @Override
    public String toFileString() {
        String role;
        if (isadmin) {
            role = "1";
        } else {
            role = "0";
        }
        String joinLine = String.join(",", String.valueOf(uid), name, username, password, role, selfdescription);
        joinLine += System.lineSeparator();//breakline        
        this.line = joinLine;
        return joinLine;
    }


}
