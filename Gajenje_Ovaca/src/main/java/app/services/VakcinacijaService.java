/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.services;

import app.model.Aktivnost;
import app.model.Dan;
import app.model.Vakcinacija;
import com.avaje.ebean.EbeanServer;


public class VakcinacijaService extends ActivityService{

    public VakcinacijaService(EbeanServer server) {
        super(server);
    }
     

    // metod radi tako sto brise sve stare vakcinacije i samu staru aktivnost i snima novu...
    public void updateActivity(Aktivnost a){
        Aktivnost act = server.find(Aktivnost.class, a.getId()); 
        deleteActivity(act);
        Aktivnost nova = new Aktivnost();
        setActivity(nova, a);
//        nova.setDan(null);
        nova.setVakcinacije(a.getVakcinacije());
        saveDayAndActivity(a.getDan(), nova);

    }

    public void createActivity(Aktivnost a){
            saveDayAndActivity(a.getDan(), a);
    }
    
    public void deleteActivity(Aktivnost a) {
        server.delete(a);
    }

    
}
