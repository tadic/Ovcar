
package app.services;

import app.model.Aktivnost;
import app.model.Ovca;
import app.model.Parenje;
import com.avaje.ebean.EbeanServer;


public class ParenjeService extends ActivityService{
    
    private OvcaService ovcaService;
    public ParenjeService(EbeanServer server) {
        super(server);
        ovcaService = new OvcaService(server);
    }
     

    // metod radi tako sto brise sva stara parenja i samu staru aktivnost i snima novu...
    public void updateActivity(Aktivnost a){
        resetAktuelno(a);
        deleteActivity(a);
        
        Aktivnost nova = new Aktivnost();
        setActivity(nova, a);
        nova.setParenja(a.getParenja());
        
        saveDayAndActivity(a.getDan(), nova);
        setNewAktuelno(nova);

    }
    
    private void resetAktuelno(Aktivnost staraAktivnost){
        if (staraAktivnost.getParenja().isEmpty()){
            return;
        }
        Parenje prvo = staraAktivnost.getParenja().get(0);
        if (!prvo.getTip().equals("Spajanje")){
            return;
        }
        
        for (Parenje staroParenje: staraAktivnost.getParenja()){
            ovcaService.resetAktuelno(staroParenje.getOvca());
        }
    }
    
    private void setNewAktuelno(Aktivnost nova){
        if (nova.getParenja().isEmpty()){
            return;
        }
        Parenje prvo = nova.getParenja().get(0);
        if (!prvo.getTip().equals("Spajanje")){
            return;
        }
        
        for (Parenje parenje: nova.getParenja()){
            Ovca ovca = parenje.getOvca();
            Ovca ovan = parenje.getOvan();
            ovca.setAktuelno("ö " + ovan.getNadimak() + " " + nova.getDatum());
            ovcaService.saveAktuelno(parenje.getOvca());
        }    
    }

    public void createActivity(Aktivnost a){
        setNewAktuelno(a);
        saveDayAndActivity(a.getDan(), a);
    }
    
    public void deleteActivity(Aktivnost a) {
        Aktivnost act = server.find(Aktivnost.class, a.getId());
        if (act!=null){
            server.delete(act);
        }
    }

    
}
