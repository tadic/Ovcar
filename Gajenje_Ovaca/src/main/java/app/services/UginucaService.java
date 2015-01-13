/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.services;

import app.model.Aktivnost;
import app.model.Dan;
import app.model.Jagnjenje;
import app.model.Ovca;
import app.model.Uginuce;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.annotation.Transactional;
import java.util.List;

/**
 *
 * @author ivantadic
 */
public class UginucaService extends ActivityService{

    private OvcaService ovcaService;
    public UginucaService(EbeanServer server) {
        super(server);
        ovcaService = new OvcaService(server);
    }
    
    public void updateActivity(Aktivnost a){
        Aktivnost act = server.find(Aktivnost.class, a.getId());  
        setActivity(act, a);
        updateUginuce(act);
        saveDayAndActivity(act.getDan(), act);
    }
    
    
    private void updateUginuce(Aktivnost a){
        Uginuce u = server.find(Uginuce.class).where().like("a_id", a.getId().toString()).findUnique(); // uginuce iz baze (staro)  
        Ovca staraOvca = u.getO();
        if (!staraOvca.getOznaka().equals(a.getUginuce().getO().getOznaka())){ // ako ovca nije ista
            ovcaService.undoStatus(staraOvca);
            server.save(staraOvca);
            ovcaService.setStatus(a.getUginuce().getO(), "uginulo");
        }
        u.setO(a.getUginuce().getO());
        u.setRazlog(a.getUginuce().getRazlog());
        server.save(u);
    }

    @Transactional
    public void createActivity(Aktivnost a){
        ovcaService.setStatus(a.getUginuce().getO(), "uginulo");       
        saveDayAndActivity(a.getDan(), a);
        a.getUginuce().setA(a);
        server.save(a.getUginuce());
        
    }
    
    @Transactional
    public void deleteActivity(Aktivnost a) {
        Uginuce u = server.find(Uginuce.class).where().like("a_id", a.getId().toString()).findUnique();
        if (u!=null){
            server.delete(a);
            ovcaService.undoStatus(u.getO());
        }
    }
    
}
