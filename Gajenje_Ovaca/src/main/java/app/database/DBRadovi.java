/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.database;

import app.model.Aktivnost;
import app.model.Dan;
import app.model.Ovca;
import app.model.Radovi;
import app.model.Uginuce;
import com.avaje.ebean.EbeanServer;

/**
 *
 * @author ivantadic
 */
public class DBRadovi {
    EbeanServer server;
    public DBRadovi(EbeanServer server) {
        this.server = server;
    }
    
    
        
    public void saveActivity(Aktivnost a) {  
        if (a.getId()==null){
            createActivity(a);
        } else{
            updateActivity(a);
        }
    } 
    
    public void updateActivity(Aktivnost a){
        Aktivnost act = server.find(Aktivnost.class, a.getId());  
        setActivity(act, a);
        updateRadovi(a);
        server.save(act);
    }
    
    
    private void setActivity(Aktivnost act, Aktivnost a){
        act.setDan(a.getDan());
        act.setLokacija(a.getLokacija());
        act.setNapomena(a.getNapomena());
        act.setVremePocetka(a.getVremePocetka());
        act.setVremeZavrsetka(a.getVremeZavrsetka());
        act.setVrstaAktivnosti(a.getVrstaAktivnosti());
        act.setTroskovi(a.getTroskovi());
        act.setBilans(a.getBilans());
    }
    
    private void updateRadovi(Aktivnost a){
        Radovi r = server.find(Radovi.class).where().like("aktivnost_id", a.getId().toString()).findUnique(); // uginuce iz baze (staro)  
        r.setKolicina(a.getRadovi().getKolicina());
        r.setRazlog(a.getRadovi().getRazlog());
        r.setStaSeRadilo_Nabavilo(a.getRadovi().getStaSeRadilo_Nabavilo());

        server.save(r);
    }

    private void createActivity(Aktivnost a){
        a.getDan().getAktivnosti().add(a);// unesi u panel
        snimiAktivnost(a);
        a.getRadovi().setAktivnost(a);
        server.save(a.getRadovi());
    }
    
    private void snimiAktivnost(Aktivnost a){
        if (a.getDan().getId()==null){ // ako ga nema u bazi, napravi ga
            server.save(a.getDan());
        } else{
           Dan d = server.find(Dan.class).where().like("datum", a.getDan().getDatum().toString()).findUnique();  
           d.getAktivnosti().add(a);
           server.save(d);
        }
    }



    public void deleteActivity(Aktivnost a) {
        server.delete(a);
    }
    


    
}
