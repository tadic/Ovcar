/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.database;

import app.model.Aktivnost;
import app.model.Dan;
import app.model.Linija;
import app.model.NabavkaOvaca;
import app.model.Ovca;
import app.model.VrsteAktivnosti;
import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
        List<Dan> listOfDays = server.find(Dan.class).order().asc("datum").findList();
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
         List<Ovca> list = server.find(Ovca.class).findList();
         System.out.println("hei ima li koga lista je ovde "+list.size());
         return list;
    }

    public Ovca getOvca(String oznaka) {
        return server.find(Ovca.class).where().like("oznaka", oznaka).findUnique();
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



    public Dan getDayWithDate(Integer date) {
        return  server.find(Dan.class).where().like("datum", date.toString()).findUnique();    
    }

    public void saveActivity(Aktivnost aktivnost) {
        if (aktivnost.getVrstaAktivnosti().getName().equals("Nabavka ovaca")){
            new DBNabavkaOvaca(server).saveActivity(aktivnost);
        } else if (aktivnost.getVrstaAktivnosti().getName().equals("Jagnjenje")) {
            new DBJagnjenja(server).saveActivity(aktivnost);
        } else if (aktivnost.getVrstaAktivnosti().getName().equals("Uginuce")) {
            new DBUginuca(server).saveActivity(aktivnost);
        } else if (aktivnost.getVrstaAktivnosti().getName().equals("Prodaja")) {
            new DBProdaja(server).saveActivity(aktivnost);
        } else if (aktivnost.getVrstaAktivnosti().getName().equals("Vakcinacija/Lečenje")) {
            new DBVakcinacija(server).saveActivity(aktivnost);
        } else {
            new DBRadovi(server).saveActivity(aktivnost);
        }
    }

    public void update(Ovca ovca) {
        Ovca o = server.find(Ovca.class, ovca.getId());
        o.setOpis(ovca.getOpis());
        o.setOznaka(ovca.getOznaka());
        o.setPracenje(ovca.getPracenje());
        o.setPol(ovca.getPol());
        o.setProcenatR(ovca.getProcenatR());
        o.setLinija(ovca.getLinija());
        o.setNadimak(ovca.getNadimak());
        o.setAktuelno(ovca.getAktuelno());
        o.setLeglo(ovca.getLeglo());
        server.save(o);
    }

    public void deleteActivity(Aktivnost aktivnost) {
        if (aktivnost.getVrstaAktivnosti().getName().equals("Nabavka ovaca")){
            new DBNabavkaOvaca(server).deleteActivity(aktivnost);
        } else if (aktivnost.getVrstaAktivnosti().getName().equals("Jagnjenje")) {
            new DBJagnjenja(server).deleteActivity(aktivnost);
        } else if (aktivnost.getVrstaAktivnosti().getName().equals("Uginuce")) {
            new DBUginuca(server).deleteActivity(aktivnost);
        } else  if (aktivnost.getVrstaAktivnosti().getName().equals("Prodaja")) {
            new DBProdaja(server).deleteActivity(aktivnost);
        } else if (aktivnost.getVrstaAktivnosti().getName().equals("Vakcinacija/Lečenje")) {
            new DBVakcinacija(server).deleteActivity(aktivnost);
        } else {
            new DBRadovi(server).deleteActivity(aktivnost);
        }
    }

    public List<Linija> getLinije() {
       return  server.find(Linija.class).findList();
    }

    public Ovca getOvca(int id) {
        Ovca o =  server.find(Ovca.class, id);
        return o;
    }

    public List<Ovca> getOvceZaJagnjenje() {
         List<Ovca> list = server.find(Ovca.class).where().like("status", "na farmi").like("pol", "ž").findList();
        if (list!=null){
            return list;
        }
        return new ArrayList<Ovca>();

    }

    public List<Ovca> listaOvnova() {
        List<Ovca> list = server.find(Ovca.class).where().like("pol", "m").findList();
        if (list!=null){
            return list;
        }
        return new ArrayList<Ovca>();
    }

    public List<Ovca> getSvaZivaGrla() {
       return  server.find(Ovca.class).where().like("status", "na farmi").findList();
    }






    
    
    
    
    
    
    
    
    
    
    
    
}
