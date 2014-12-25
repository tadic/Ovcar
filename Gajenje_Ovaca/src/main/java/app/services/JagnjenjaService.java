/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.services;

import app.model.Aktivnost;
import app.model.Dan;
import app.model.Jagnjenje;
import app.model.NabavkaOvaca;
import app.model.Ovca;
import com.avaje.ebean.EbeanServer;
import java.util.List;

/**
 *
 * @author ivantadic
 */
public class JagnjenjaService extends ActivityService{
    private OvcaService ovcaService;
    
    public JagnjenjaService(EbeanServer server) {
        super(server);
        ovcaService = new OvcaService(server);
    }
    
    public void updateActivity(Aktivnost a){
        Aktivnost act = server.find(Aktivnost.class, a.getId());  
        deleteOldJagnjenja(act, a);
        setActivity(act, a);
        
        updateJagnjenja(a);
        saveDayAndActivity(act.getDan(), act);
    }
    
    private void deleteOldJagnjenja(Aktivnost staraAktivnost, Aktivnost novaAktivnost){
        for (Jagnjenje j: staraAktivnost.getListaJagnjenja()){
            if (!novaAktivnost.getListaJagnjenja().contains(j)){
                ovcaService.deleteSheep(j.getSheep());
                server.delete(j);
            }
        }
    }
    
    private void updateJagnjenja(Aktivnost a){
        String datumJagnjenja = a.getDan().toString();
        for (Jagnjenje j: a.getListaJagnjenja()){
            //saveSheep(j.getSheep());
            j.getSheep().setDatumRodjenja(datumJagnjenja);
            updateJagnjenje(j, a);    // prvo saveSheep jagnjenje da bi definitivno dobilo id i jagnjetov id
            
        }
    }
    
    private void updateJagnjenje(Jagnjenje j, Aktivnost a){
        if (j.getId()!=null){
         //   System.out.println("Update jagnjenje id :"+ j.getId());
            Jagnjenje jagnjenje = server.find(Jagnjenje.class, j.getId());
            jagnjenje.setOvca(j.getOvca());
            
            jagnjenje.setJelZivo(j.isJelZivo());
            jagnjenje.setNapomena(j.getNapomena());
            j.getSheep().setId(jagnjenje.getSheep().getId());
            ovcaService.saveSheep(j.getSheep());
            ovcaService.undoStatus(j.getSheep());
            server.save(jagnjenje);
            
            
        }else{
            j.setAktivnost(a);
            ovcaService.saveSheep(j.getSheep());
            server.save(j);
        }
    }
    
    public void createActivity(Aktivnost a){
        String datumJagnjenja = a.getDan().toString();
            for (Jagnjenje jagnjenje: a.getListaJagnjenja()){
                jagnjenje.getSheep().setDatumRodjenja(datumJagnjenja);
                System.out.println("Ulazimo");
                ovcaService.saveSheep(jagnjenje.getSheep());
            }
          saveDayAndActivity(a.getDan(), a);
    }

    public void deleteActivity(Aktivnost a) {
        for (Jagnjenje j: a.getListaJagnjenja()){
            ovcaService.deleteSheep(j.getSheep());
        }
        server.delete(a);
    }


}
