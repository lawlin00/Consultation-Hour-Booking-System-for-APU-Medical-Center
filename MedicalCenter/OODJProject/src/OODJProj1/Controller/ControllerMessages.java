/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OODJProj1.Controller;

import javax.swing.JOptionPane;
//class for generic error messages used by controllers

/**
 *
 * @author cheon
 */
public class ControllerMessages {
     public void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void displaySuccessMessage(String successMessage) {
        JOptionPane.showMessageDialog(null, successMessage, "Alert", JOptionPane.WARNING_MESSAGE);
    }
}
