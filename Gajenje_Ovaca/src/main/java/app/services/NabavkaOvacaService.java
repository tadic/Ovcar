/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.services;

import app.model.Aktivnost;
import app.model.NabavkaOvaca;
import com.avaje.ebean.EbeanServer;

/**
 *
 * @author ivantadic
 */
public class NabavkaOvacaService extends ActivityService {
    
    private OvcaService ovcaService;
    
    public NabavkaOvacaService(EbeanServer server){
        super(server);
        ovcaService = new OvcaService(server);
    }
    
    
    public void updateActivity(Aktivnost a){
        Aktivnost act = server.find(Aktivnost.class, a.getId());  
        setActivity(act, a);
        saveDayAndActivity(a.getDan(), act);
        saveNabavkeOvaca(a);
    }
     
    public void createActivity(Aktivnost a){
        saveDayAndActivity(a.getDan(), a);
        saveNabavkeOvaca(a);
    }
    
    
    private void saveNabavkeOvaca(Aktivnost a){
        for (NabavkaOvaca no: a.getNabavljenaGrla()){
            no.setAktivnost(a); 
            
            ovcaService.saveSheep(no.getSheep());
            saveNabavku(no);
        }
    }
    
    private void saveNabavku(NabavkaOvaca no){
        if (no.getId()!=null){
            NabavkaOvaca nabavkaO = server.find(NabavkaOvaca.class, no.getId());  
            nabavkaO.setCena(no.getCena());
            nabavkaO.setAktivnost(no.getAktivnost());
            nabavkaO.setNapomena(no.getNapomena());
            server.save(nabavkaO);
            
        } else {
            //ovcaService.saveSheep(no.getSheep());
            server.save(no);
        }
        
    }
    
    public void deleteActivity(Aktivnost a) {
        for (NabavkaOvaca no: a.getNabavljenaGrla()){
            ovcaService.deleteSheep(no.getSheep());
          //  server.delete(no);
        }
        server.delete(a);
    }
    
}
