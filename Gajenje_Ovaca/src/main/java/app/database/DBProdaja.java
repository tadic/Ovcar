/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.database;

import app.model.Aktivnost;
import app.model.Dan;
import app.model.Ovca;
import app.model.Prodaja;
import com.avaje.ebean.EbeanServer;

/**
 *
 * @author ivantadic
 */
public class DBProdaja {
    EbeanServer server;
    
    public DBProdaja(EbeanServer server){
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
        Aktivnost act = server.find(Aktivnost.class).where().like("id", a.getId().toString()).findUnique();  
        setActivity(act, a);
        updateProdaje(a);
        server.save(act);
    }
    
    private void createActivity(Aktivnost a){
            a.getDan().getAktivnosti().add(a);// unesi u panel
            for (Prodaja prodaja: a.getProdaje()){
                saveSheep(prodaja.getOvca(), "prodato");
            }
            if (a.getDan().getId()==null){ // ako ga nema u bazi, napravi ga
                server.save(a.getDan());
            } else{
               Dan d = server.find(Dan.class).where().like("datum", a.getDan().getDatum().toString()).findUnique();  
               d.getAktivnosti().add(a);
               server.save(d);
            }
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
        act.setProdaje(a.getProdaje());
}
    
    
    public void saveSheep(Ovca sheep, String status){
            Ovca o = server.find(Ovca.class, sheep.getId());  
            o.setStatus(status);
            server.save(o);
    }
    
  
    private void updateProdaje(Aktivnost a){
        Aktivnost staraAktivnost = server.find(Aktivnost.class, a.getId());
        for (Prodaja p:staraAktivnost.getProdaje()){
            saveSheep(p.getOvca(), "na farmi");
            server.delete(p);
        }
        for (Prodaja p: a.getProdaje()){
            saveSheep(p.getOvca(), "prodato");
        }
    }
    
    void deleteActivity(Aktivnost a) {
        Aktivnost act = server.find(Aktivnost.class, a.getId());
        for (Prodaja p: a.getProdaje()){
            Ovca o = p.getOvca();
            o.setStatus("na farmi");
            server.save(o);
        }
        server.delete(a);
    }
    
}
