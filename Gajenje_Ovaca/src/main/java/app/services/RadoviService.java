package app.services;

import app.model.Aktivnost;
import app.model.Dan;
import app.model.Radovi;
import com.avaje.ebean.EbeanServer;

/**
 *
 * @author ivantadic
 */
public class RadoviService extends ActivityService{

    public RadoviService(EbeanServer server) {
        super(server);
    }
    
    
    public void updateActivity(Aktivnost a){
        Aktivnost act = server.find(Aktivnost.class, a.getId());  
        setActivity(act, a);
        updateRadovi(a);
        saveDayAndActivity(a.getDan(), act);
    }    
    
    private void updateRadovi(Aktivnost a){
        Radovi r = server.find(Radovi.class).where().like("aktivnost_id", a.getId().toString()).findUnique(); // uginuce iz baze (staro)  
        r.setKolicina(a.getRadovi().getKolicina());
        r.setRazlog(a.getRadovi().getRazlog());
        r.setStaSeRadilo_Nabavilo(a.getRadovi().getStaSeRadilo_Nabavilo());

        server.save(r);
    }

    public void createActivity(Aktivnost a){
        saveDayAndActivity(a.getDan(), a);
        a.getRadovi().setAktivnost(a);
        server.save(a.getRadovi());
    }    
    
    public void deleteActivity(Aktivnost a) {
        Radovi r = server.find(Radovi.class).where().like("aktivnost_id", a.getId().toString()).findUnique(); // uginuce iz baze (staro)  
        server.delete(a);
        server.delete(r);
        
    }
    
}
