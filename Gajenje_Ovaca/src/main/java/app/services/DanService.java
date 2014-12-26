/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.services;

import app.model.Dan;
import com.avaje.ebean.EbeanServer;
import java.util.ArrayList;
import java.util.List;


public class DanService {
   private EbeanServer server; 
   
   public DanService(EbeanServer server){
       this.server = server;
   }
   
      
    public List<Dan> getAllDays() {
        List<Dan> listOfDays = server.find(Dan.class).order().asc("datum").findList();
        if (listOfDays==null){
            return new ArrayList<Dan>();
        }
        return listOfDays;
    }

    public Dan getDayWithDate(Integer date) {
        return  server.find(Dan.class).where().like("datum", date.toString()).findUnique();    
    }
    
    public Dan getDan(Integer id) {
        return server.find(Dan.class, id);
    } 
}
