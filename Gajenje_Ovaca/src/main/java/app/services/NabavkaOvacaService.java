/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.services;

import app.model.Aktivnost;
import app.model.Dan;
import app.model.NabavkaOvaca;
import app.model.Ovca;
import com.avaje.ebean.EbeanServer;
import java.util.List;

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
        updateNabavkeOvaca(act);
        saveDayAndActivity(act.getDan(), act);
    }
    

    
    public void createActivity(Aktivnost a){
        //a.getDan().getAktivnosti().add(a);// unesi u panel
            for (NabavkaOvaca nabavka: a.getNabavljenaGrla()){
                ovcaService.saveSheep(nabavka.getSheep());
            }
            saveDayAndActivity(a.getDan(), a);
    }
    
    
    private void updateNabavkeOvaca(Aktivnost a){

        for (NabavkaOvaca no: a.getNabavljenaGrla()){
            updateNabavkaOvaca(no);
            ovcaService.saveSheep(no.getSheep());
        }
    }
    private void updateNabavkaOvaca(NabavkaOvaca no){
        if (no.getId()!=null){
            NabavkaOvaca nabavkaO = server.find(NabavkaOvaca.class, no.getId());  
            nabavkaO.setCena(no.getCena());
            nabavkaO.setNapomena(no.getNapomena());
            System.out.println(nabavkaO.getNapomena());
            server.save(nabavkaO);
            
        }else{
            server.save(no);
        }
    }
    
    public void deleteActivity(Aktivnost a) {
        for (NabavkaOvaca no: a.getNabavljenaGrla()){
            server.delete(no.getSheep());
        }
        server.delete(a);
    }
    
}
