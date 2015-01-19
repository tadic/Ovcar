/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.services;

import app.model.Aktivnost;
import app.model.Dan;
import app.model.Ovca;
import app.model.Prodaja;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.annotation.Transactional;

/**
 *
 * @author ivantadic
 */
public class ProdajaService extends ActivityService {
    
    private OvcaService ovcaService;
    
    public ProdajaService(EbeanServer server){
        super(server);
        ovcaService = new OvcaService(server);
    }
    
    
    public void updateActivity(Aktivnost a){
//        Aktivnost act = server.find(Aktivnost.class).where().like("id", a.getId().toString()).findUnique();  
//        updateProdaje(a);
//        setActivity(act, a);
//
//        saveDayAndActivity(act.getDan(), act);
//        
//        
        Aktivnost act = server.find(Aktivnost.class, a.getId()); 
        deleteActivity(act);
        Aktivnost nova = new Aktivnost();
        setActivity(nova, a);
        postaviNoveProdaje(nova, a);
        createActivity(nova);
    }
    
    public void createActivity(Aktivnost a){
            for (Prodaja prodaja: a.getProdaje()){
                ovcaService.saveSheepStatus(prodaja.getOvca(), "prodato");
            }
            saveDayAndActivity(a.getDan(), a);
    }
    
//    @Transactional
//    private void updateProdaje(Aktivnost novaAktivnost){
//        Aktivnost staraAktivnost = server.find(Aktivnost.class, novaAktivnost.getId());
//        izbrisiStareProdaje(staraAktivnost);
//        postaviNoveProdaje(staraAktivnost, novaAktivnost);
//    }

    public void izbrisiStareProdaje(Aktivnost staraAktivnost){
        for (Prodaja p:staraAktivnost.getProdaje()){
            ovcaService.undoStatus(p.getOvca());
        }
        staraAktivnost.getProdaje().clear();  
    }
    
    public void postaviNoveProdaje(Aktivnost staraAktivnost, Aktivnost novaAktivnost){
        for (Prodaja p: novaAktivnost.getProdaje()){
            ovcaService.saveSheepStatus(p.getOvca(), "prodato");
        }
        staraAktivnost.setProdaje(novaAktivnost.getProdaje());
    }
    
    public void deleteActivity(Aktivnost a) {
        Aktivnost act = server.find(Aktivnost.class, a.getId());
        server.delete(act);
        for (Prodaja p: act.getProdaje()){
            ovcaService.undoStatus(p.getOvca());
        }
    }
    
}
