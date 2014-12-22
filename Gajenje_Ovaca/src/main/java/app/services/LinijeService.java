/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.services;

import app.model.Linija;
import com.avaje.ebean.EbeanServer;
import java.util.List;


public class LinijeService {
    private EbeanServer server;

    public LinijeService(EbeanServer server) {
        this.server = server;
    }
    
    public List<Linija> getLinije() {
       return  server.find(Linija.class).findList();
    } 
}
