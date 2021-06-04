/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OODJProj1.Controller;

import java.util.ArrayList;

/**
 *
 * @author cheon
 */
public interface CRUDReadable <T>{ //Groups countrollers for Views/functions with CRUD and Table retrieval capabilities
    //T represents the model used
    //Only suitable for controllers that do not need more than the object itself for CRUD operations
    //Not used for Payment and reports
    public void create(T object); //Used to create, validate, then write model objects into the textfile
    public void delete(T object); //Deletes model objects from text file
    public void update(T object); //Edits model objects in the text file
    public T getModel(String id); //Gets a specific object/model  from the text files
    public ArrayList<T> getModelList(); //Retrieves all model objects without filter, controllers will have specific filtered List retrieval if needed
    
}
