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
     

    
    public void updateActivity(Aktivnost a){
        Aktivnost staraAct = server.find(Aktivnost.class, a.getId());         
        izbrisiVisakPodaktivnosti(staraAct, a);
        setActivity(staraAct, a);
        updateSubActivities();
        saveDayAndActivity(a.getDan(), a);
    }
    
    public void createActivity(Aktivnost a){
            saveDayAndActivity(a.getDan(), a);
    }

  
    private void izbrisiVisakPodaktivnosti(Aktivnost staraAktivnost, Aktivnost novaAktivnost){// ovaj slucaj je specifican i samo brise stare a ne updejtuje
        for (Vakcinacija v:staraAktivnost.getVakcinacije()){
            server.delete(v);
        }
    }


    public void deleteActivity(Aktivnost a) {
        server.delete(a);
    }

    private void updateSubActivities() {
        return;
    }
    
}
