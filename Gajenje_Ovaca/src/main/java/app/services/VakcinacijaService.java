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
     
    public void saveActivity(Aktivnost a) {  
        if (a.getId()==null){
            createActivity(a);
        } else{
            updateActivity(a);
        }
    } 
    
    public void updateActivity(Aktivnost a){
        Aktivnost staraAct = server.find(Aktivnost.class, a.getId());         
        izbrisiStareVakcinacije(staraAct);
        setActivity(staraAct, a);
System.out.println("Baza: jel redovno: jel vakcinacija: " + staraAct.getVakcinacije().get(0).getJelRedovno());
        server.save(staraAct);
    }
    
    private void createActivity(Aktivnost a){
            a.getDan().getAktivnosti().add(a);// unesi u panel
            if (a.getDan().getId()==null){ // ako ga nema u bazi, napravi ga
               System.out.println("dan: "+ a.getDan().toString());
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
        act.setVakcinacije(a.getVakcinacije());
}

  
    private void izbrisiStareVakcinacije(Aktivnost staraAktivnost){
        for (Vakcinacija v:staraAktivnost.getVakcinacije()){
            server.delete(v);
        }
    }


    public void deleteActivity(Aktivnost a) {
        server.delete(a);
    }
    
}
