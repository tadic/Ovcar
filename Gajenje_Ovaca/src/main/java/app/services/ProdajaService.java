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
        Aktivnost act = server.find(Aktivnost.class).where().like("id", a.getId().toString()).findUnique();  
        setActivity(act, a);
        updateProdaje(a);
        saveDayAndActivity(act.getDan(), act);
    }
    
    public void createActivity(Aktivnost a){
            for (Prodaja prodaja: a.getProdaje()){
                ovcaService.saveSheepStatus(prodaja.getOvca(), "prodato");
            }
            saveDayAndActivity(a.getDan(), a);
    }
    
    private void updateProdaje(Aktivnost a){
        Aktivnost staraAktivnost = server.find(Aktivnost.class, a.getId());
        for (Prodaja p:staraAktivnost.getProdaje()){
            server.delete(p);
            ovcaService.undoStatus(p.getOvca());
        }
        for (Prodaja p: a.getProdaje()){
            ovcaService.saveSheepStatus(p.getOvca(), "prodato");
        }
    }
    
    public void deleteActivity(Aktivnost a) {
        Aktivnost act = server.find(Aktivnost.class, a.getId());
        server.delete(act);
        for (Prodaja p: act.getProdaje()){
            ovcaService.undoStatus(p.getOvca());
        }
    }
    
}
