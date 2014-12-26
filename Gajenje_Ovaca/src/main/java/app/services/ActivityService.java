package app.services;

import app.model.Aktivnost;
import app.model.Dan;
import app.model.NabavkaOvaca;
import app.model.VrsteAktivnosti;
import com.avaje.ebean.EbeanServer;
import java.util.ArrayList;
import java.util.List;


public abstract class ActivityService {
    public EbeanServer server;

    public ActivityService() {
    }
    
    public ActivityService(EbeanServer server){
        this.server = server;
    }
    
    
    public List<VrsteAktivnosti> getAllTypesOfActivities() {
        List<VrsteAktivnosti> typesOfActivities = server.find(VrsteAktivnosti.class).findList();
        if (typesOfActivities==null){
            return new ArrayList<VrsteAktivnosti>();
        }
        return typesOfActivities;
    }    

    public Aktivnost getActivityWithId(Integer id) {
        return server.find(Aktivnost.class).where().like("id", id.toString()).findUnique();  

    }

    public List<Aktivnost> getActivitiesFrom(Dan d) {
        List<Aktivnost> list = server.find(Aktivnost.class).where().like("dan_id", d.getId().toString()).findList();  
        if (list != null){
            return list;
        }
        return new ArrayList<Aktivnost>();
    }

    public List<NabavkaOvaca> getListOfNabavkaFor(Aktivnost aktivnost) {
        return  server.find(NabavkaOvaca.class).where().like("aktivnost_id", aktivnost.getId().toString()).findList();  
    }

    public abstract void saveActivity(Aktivnost a);
    public abstract void deleteActivity(Aktivnost a);
   // public void setServer(EbeanServer server);
}
