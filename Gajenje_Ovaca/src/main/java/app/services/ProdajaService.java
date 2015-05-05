
package app.services;

import app.model.Aktivnost;
import app.model.Prodaja;
import com.avaje.ebean.EbeanServer;
import java.util.ArrayList;
import java.util.List;

public class ProdajaService extends ActivityService {
    
    private OvcaService ovcaService;
    
    public ProdajaService(EbeanServer server){
        super(server);
        ovcaService = new OvcaService(server);
    }
    
    
    public void updateActivity(Aktivnost a){

        System.out.println("Usao u update \n\n\nUsao u auped");
        deleteActivity(a);
        Aktivnost nova = new Aktivnost();
        setActivity(nova, a);
        postaviNoveProdaje(nova, a);
        
        saveDayAndActivity(a.getDan(), nova);
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
        List<Prodaja> prodaje = new ArrayList<Prodaja>();
        for (Prodaja p:act.getProdaje()){       // snima stare prodaje kakve su bile pre updejtovanja
            prodaje.add(p);                     // da bih pomocu njih pronasao ovce i vratio stari status
        }
        
        server.delete(act);
        for (Prodaja p: prodaje){
            ovcaService.undoStatus(p.getOvca());
        }
        

    }
    
}
