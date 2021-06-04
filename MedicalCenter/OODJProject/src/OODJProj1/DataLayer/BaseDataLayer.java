/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OODJProj1.DataLayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.Boolean.parseBoolean;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public abstract class BaseDataLayer {
//Contains methods for read/write operations
    protected final String FilePath = "DataFiles/";

    public List<String> readFiles(String Filename) {
        String Filepath = FilePath + Filename;
        File file = new File(Filepath);
        List<String> lst = null;
        try {
            lst = Files.readAllLines(Paths.get(Filepath));
        } catch (IOException ex) {
            System.out.println("File Not Found! Technical Error!");
        }
        return lst;
    }

    public void create(String ListOfData, String Filename) {
        // boolean IsWritten = false;
        try {
            String Filepath = FilePath + Filename;
            Files.write(Paths.get(Filepath), ListOfData.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            System.out.println("Done!");
        } catch (IOException ex) {
            System.out.println(ex);
            System.out.println("Unexpected Error Occurs!");
        }
        // return IsWritten;
    }

    public void update(ArrayList<String> ListOfData, String Filename) {
        try {
            String Filepath = FilePath + Filename;
            Files.delete(Paths.get(Filepath));
            for(String current : ListOfData){
                Files.write(Paths.get(Filepath), current.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);                
            }
            System.out.println("Done Update!");            
        }catch (IOException ex) {
            System.out.println(ex);
            System.out.println("Unexpected Error Occurs!");
        }
        //
    }
    
    public abstract ArrayList<?> getAll();
}
