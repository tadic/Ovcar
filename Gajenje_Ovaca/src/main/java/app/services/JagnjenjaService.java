/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.services;

import app.model.Aktivnost;
import app.model.Jagnjenje;
import com.avaje.ebean.EbeanServer;

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
    
    public void createActivity(Aktivnost a){
        saveDayAndActivity(a.getDan(), a);
        saveJagnjenja(a);

    }
    
    public void updateActivity(Aktivnost a){
        Aktivnost act = server.find(Aktivnost.class, a.getId());  
        izbrisiVisakPodaktivnosti(act, a); // ovoj funkciji je mesto ovde posto treba i staru i novu aktivnost nepromenjenu
        setActivity(act, a);
        saveDayAndActivity(a.getDan(), act); // updejtuj dan, vreme, lokaciju, napomenu i sl.
        saveJagnjenja(a);
    }
        
    public void saveJagnjenja(Aktivnost a){          
        String datumJagnjenja = a.getDan().toString();
        for (Jagnjenje jagnjenje: a.getListaJagnjenja()){
            jagnjenje.getSheep().setDatumRodjenja(datumJagnjenja);
            jagnjenje.getSheep().setAktuelno("# " + datumJagnjenja);
            jagnjenje.getSheep().setPoreklo("farma Tadići");
            jagnjenje.setAktivnost(a);
            
            saveJagnjenje(jagnjenje);
 // mora da ide posle snimanja jagnjenja da ga jagnjenje ne bi snimilo pre vremena
        }
    }
    
    private void saveJagnjenje(Jagnjenje j){
        
        if (j.getId()!=null){
            Jagnjenje jagnjenje = server.find(Jagnjenje.class, j.getId());
            jagnjenje.setAktivnost(j.getAktivnost());
            jagnjenje.setJelZivo(j.isJelZivo());
            jagnjenje.setNapomena(j.getNapomena());
            jagnjenje.getSheep().setStatus(j.getSheep().getStatus());
            jagnjenje.getSheep().setOznaka(j.getSheep().getOznaka());
            jagnjenje.getSheep().setPol(j.getSheep().getPol());
            jagnjenje.getSheep().setProcenatR(j.getSheep().getProcenatR());
            jagnjenje.getSheep().setOpis(j.getSheep().getOpis());
            server.save(jagnjenje);
            //ovcaService.saveSheep(jagnjenje.getSheep());
        } else {
            server.save(j);
        }
    }
    
    private void izbrisiVisakPodaktivnosti(Aktivnost staraAktivnost, Aktivnost novaAktivnost){
        for (Jagnjenje j: staraAktivnost.getListaJagnjenja()){
            if (!novaAktivnost.getListaJagnjenja().contains(j)){
                //ovcaService.deleteSheep(j.getSheep());
                server.delete(j);
            }
        }
    }
    

    public void deleteActivity(Aktivnost a) {
        server.delete(a);
    }


}
