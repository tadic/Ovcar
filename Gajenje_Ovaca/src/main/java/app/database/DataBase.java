/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.database;

import app.model.Aktivnost;
import app.model.Dan;
import app.model.NabavkaOvaca;
import app.model.Ovca;
import app.model.VrsteAktivnosti;
import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ivantadic
 */
public class DataBase {
    private EbeanServer server;
    
    public DataBase(boolean dropAndCreateDB){
        server = new DBServer(dropAndCreateDB).server();
    }
    
      public List<Dan> getAllDays() {
        List<Dan> listOfDays = server.find(Dan.class).findList();
        if (listOfDays==null){
            return new ArrayList<Dan>();
        }
        return listOfDays;
    }

    public List<VrsteAktivnosti> getAllTypesOfActivities() {
        List<VrsteAktivnosti> typesOfActivities = server.find(VrsteAktivnosti.class).findList();
        if (typesOfActivities==null){
            return new ArrayList<VrsteAktivnosti>();
        }
        return typesOfActivities;
    }

 /*   public void saveDay(Dan day) {
        Aktivnost akt = day.getAktivnosti().get(day.getAktivnosti().size()-1);
        Dan dDb = server.find(Dan.class).where().like("datum", day.getDatum().toString()).findUnique();
        if (dDb==null){
            server.save(day);
            return;
        }
        dDb.getAktivnosti().add(akt);
        server.save(dDb);
       
    }*/
    


    public List<Ovca> getAllSheep() {
         return  (List<Ovca>) server.find(Ovca.class).findList();
    }

    public Ovca getOvca(String oznaka) {
        return  server.find(Ovca.class).where().like("oznaka", oznaka).findUnique();      
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

    private void setNabavka(NabavkaOvaca nabavkaO, NabavkaOvaca no) {
        
    }

    public Dan getDayWithDate(Integer date) {
        return  server.find(Dan.class).where().like("datum", date.toString()).findUnique();    
    }

    public void saveActivity(Aktivnost aktivnost) {
        if (!aktivnost.getVrstaAktivnosti().getName().equals("")){
            new DBNabavkaOvaca(server).saveActivity(aktivnost);
        }
    }

    public void update(Ovca ovca) {
        Ovca o = server.find(Ovca.class, ovca.getId());
        o.setOpis(ovca.getOpis());
        o.setPracenje(ovca.getPracenje());
        o.setPol(ovca.getPol());
        o.setProcenatR(ovca.getProcenatR());
        server.save(o);
    }

    public void deleteActivity(Aktivnost aktivnost) {
        if (!aktivnost.getVrstaAktivnosti().getName().equals("")){
            new DBNabavkaOvaca(server).deleteActivity(aktivnost);
        }
    }




    
    
    
    
    
    
    
    
    
    
    
    
}
