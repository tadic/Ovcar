package app.services;

import app.model.Aktivnost;
import app.model.Dan;
import app.model.NabavkaOvaca;
import app.model.VrsteAktivnosti;
import com.avaje.ebean.EbeanServer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public abstract class ActivityService {
    public EbeanServer server;

    public ActivityService() {
    }
    
    public ActivityService(EbeanServer server){
        this.server = server;
    }
     
    public void saveDayAndActivity(Dan dan, Aktivnost a){
            Dan d = server.find(Dan.class).where().like("datum", dan.getDatum().toString()).findUnique();  
 
             if (d!=null){
                 a.setDan(d);
                 server.save(a);
             } else {
                 server.save(dan);
                 a.setDan(dan);
                 server.save(a);
             }
             
    }
    
    public List<VrsteAktivnosti> getAllTypesOfActivities() {
        List<VrsteAktivnosti> typesOfActivities = server.find(VrsteAktivnosti.class).orderBy("name").findList();
        if (typesOfActivities==null){
            return new ArrayList<VrsteAktivnosti>();
        }
        return typesOfActivities;
    }    

    public Aktivnost getActivityWithId(Integer id) {
        Aktivnost a = server.find(Aktivnost.class, id); 
        Integer datum = a.getDan().getDatum();
      //  a.setDan(server.find(Dan.class, a.getDan().getId()));
        return a;

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
    
    public List<Aktivnost> getPoslednjaMerenja(int maxCount) {
        List<Aktivnost> list =  server.find(Aktivnost.class).where().like("vrsta_aktivnosti_id","7").findList();
        Collections.sort(list);
        if (list.size()>maxCount){
            return list.subList(list.size()-maxCount, list.size());
        } 
        return list;
    }

    public void setActivity(Aktivnost act, Aktivnost a){
        //act.setDan(a.getDan());
        act.setLokacija(a.getLokacija());
        act.setNapomena(a.getNapomena());
        act.setVremePocetka(a.getVremePocetka());
        act.setVremeZavrsetka(a.getVremeZavrsetka());
        act.setVrstaAktivnosti(a.getVrstaAktivnosti());
        act.setTroskovi(a.getTroskovi());
        act.setBilans(a.getBilans());
        
    }

    public void saveActivity(Aktivnost a) { 
        if (a.getId()==null){
            createActivity(a);
        } else{
            updateActivity(a);
        }
    } 
    
    public abstract void createActivity(Aktivnost a);
    public abstract void updateActivity(Aktivnost a);
    public abstract void deleteActivity(Aktivnost a);
   // public void setServer(EbeanServer server);
}
