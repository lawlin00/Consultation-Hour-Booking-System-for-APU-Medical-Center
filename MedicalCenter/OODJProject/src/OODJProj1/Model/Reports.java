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
public class Reports {
    private String DocName;
    private int DocID, count;
    private double TotalEarned;
    
    public String getDocName() {
        return DocName;
    }

    public void setDocName(String DocName) {
        this.DocName = DocName;
    }

    public int getDocID() {
        return DocID;
    }

    public void setDocID(int DocID) {
        this.DocID = DocID;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getTotalEarned() {
        return TotalEarned;
    }

    public void setTotalEarned(double TotalEarned) {
        this.TotalEarned = TotalEarned;
    }

    public Reports(String DocName, int DocID, int count, double TotalEarned) {
        this.DocName = DocName;
        this.DocID = DocID;
        this.count = count;
        this.TotalEarned = TotalEarned;
    }


}
