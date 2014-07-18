/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.database;

import app.model.Aktivnost;
import app.model.Dan;
import app.model.Jagnjenje;
import app.model.Ovca;
import app.model.Uginuce;
import com.avaje.ebean.EbeanServer;
import java.util.List;

/**
 *
 * @author ivantadic
 */
class DBUginuca {
    private EbeanServer server;
    public DBUginuca(EbeanServer server) {
        this.server = server;
    }
    
    
    public void saveActivity(Aktivnost a) {  
        if (a.getId()==null){
            System.out.print("Uginuce se snima....");
            createActivity(a);
        } else{
            updateActivity(a);
        }
    } 
    
    public void updateActivity(Aktivnost a){
        Aktivnost act = server.find(Aktivnost.class, a.getId());  
        setActivity(act, a);
        server.save(act);
        updateUginuce(a);
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
    
    private void updateUginuce(Aktivnost a){
        Uginuce u = server.find(Uginuce.class).where().like("a_id", a.getId().toString()).findUnique(); // uginuce iz baze (staro)  
        Ovca staraOvca = u.getO();
        if (!staraOvca.getOznaka().equals(a.getUginuce().getO().getOznaka())){ // ako ovca nije ista
            staraOvca.setStatus("na farmi");
            server.save(staraOvca);
            saveSheep(a.getUginuce().getO(), "uglinulo");
        }
        u.setO(a.getUginuce().getO());
        u.setRazlog(a.getUginuce().getRazlog());
        server.save(u);
    }

    private void createActivity(Aktivnost a){
        a.getDan().getAktivnosti().add(a);// unesi u panel
        saveSheep(a.getUginuce().getO(), "uglinulo");
        snimiAktivnost(a);
        a.getUginuce().setA(a);
        server.save(a.getUginuce());
        
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
    
    
        
    public void saveSheep(Ovca sheep, String status){
        Ovca ovca = server.find(Ovca.class).where().like("oznaka", sheep.getOznaka().toString()).findUnique();  
        ovca.setStatus(status);
        server.save(ovca);                 // ako nije postojala snimi je direktno
    }


    public void deleteActivity(Aktivnost a) {
        Uginuce u = server.find(Uginuce.class).where().like("a_id", a.getId().toString()).findUnique(); // uginuce iz baze (staro)  
        
        saveSheep(u.getO(), "na farmi");
        server.delete(a);
        server.delete(u);
    }
    
}
