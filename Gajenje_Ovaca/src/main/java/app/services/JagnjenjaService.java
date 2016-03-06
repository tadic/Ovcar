
package app.services;

import app.model.Aktivnost;
import app.model.Dan;
import app.model.Jagnjenje;
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
    
    public void createActivity(Aktivnost a){
        saveDayAndActivity(a.getDan(), a);
        saveJagnjenja(a);

    }
    
    public void updateActivity(Aktivnost a){
        Aktivnost act = server.find(Aktivnost.class, a.getId());  
        izbrisiVisakPodaktivnosti(act, a); // ovoj funkciji je mesto ovde posto treba i staru i novu aktivnost nepromenjenu
       
        saveJagnjenja(a);
        setActivity(act, a);
        saveDayAndActivity(a.getDan(), act); // updejtuj dan, vreme, lokaciju, napomenu i sl.
        
    }
        
    public void saveJagnjenja(Aktivnost a){          
        String datumJagnjenja = a.getDan().toString();
        for (Jagnjenje jagnjenje: a.getListaJagnjenja()){
            jagnjenje.setAktivnost(a);
            
            saveJagnjenje(jagnjenje, datumJagnjenja);
 // mora da ide posle snimanja jagnjenja da ga jagnjenje ne bi snimilo pre vremena
            ovcaService.saveAktuelno(jagnjenje.getOvca());
        }
    }
    
    private void saveJagnjenje(Jagnjenje j, String datumJagnjenja){ 
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
            
            jagnjenje.getSheep().setDatumRodjenja(datumJagnjenja);
            jagnjenje.getSheep().setAktuelno("# " + datumJagnjenja);
            jagnjenje.getSheep().setPoreklo("farma Tadići");
            
            server.save(jagnjenje);
            //ovcaService.saveSheep(jagnjenje.getSheep());
        } else {
            j.getSheep().setDatumRodjenja(datumJagnjenja);
            j.getSheep().setAktuelno("# " + datumJagnjenja);
            j.getSheep().setPoreklo("farma Tadići");
            server.save(j);
        }
    }
    
    private void izbrisiVisakPodaktivnosti(Aktivnost staraAktivnost, Aktivnost novaAktivnost){
        List<Jagnjenje> novaJagnjenja = novaAktivnost.getListaJagnjenja();
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
