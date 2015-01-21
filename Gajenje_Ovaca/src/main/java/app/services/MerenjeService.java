/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.services;

import app.model.Aktivnost;
import com.avaje.ebean.EbeanServer;

/**
 *
 * @author ivantadic
 */
class MerenjeService extends ActivityService {

    public MerenjeService(EbeanServer server) {
         super(server);
    }
      
   
    public void updateActivity(Aktivnost a){
        deleteActivity(a);
        Aktivnost nova = new Aktivnost();
        setActivity(nova, a);
        nova.setMerenja(a.getMerenja());
        createActivity(nova);
    }
    
    public void createActivity(Aktivnost a){
        saveDayAndActivity(a.getDan(), a);
    }

    
    public void deleteActivity(Aktivnost a) {
        Aktivnost act = server.find(Aktivnost.class, a.getId());
        if (act!=null){
            server.delete(act);
        }
    }
    
}
