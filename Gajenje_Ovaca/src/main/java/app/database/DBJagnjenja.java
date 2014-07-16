/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.database;

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
public class DBJagnjenja {
    EbeanServer server;
    public DBJagnjenja(EbeanServer server) {
        this.server = server;
    }
    
    public void saveActivity(Aktivnost a) {  
        if (a.getId()==null){
            createActivity(a);
        } else{
            updateActivity(a);
        }
    } 
    
    public void updateActivity(Aktivnost a){
        Aktivnost act = server.find(Aktivnost.class).where().like("id", a.getId().toString()).findUnique();  
       // setActivity(act, a);
        //updateNabavkeOvaca(a);
        server.save(act);
    }
    
    private void createActivity(Aktivnost a){
        a.getDan().getAktivnosti().add(a);// unesi u panel
        String datumJagnjenja = a.getDan().toString();
            for (Jagnjenje jagnjenje: a.getListaJagnjenja()){
                jagnjenje.getJagnje().setDatumRodjenja(datumJagnjenja);
                saveSheep(jagnjenje.getJagnje());
            }
            if (a.getDan().getId()==null){ // ako ga nema u bazi, napravi ga
                System.out.println("dan: "+ a.getDan().toString());
                server.save(a.getDan());
            } else{
               Dan d = server.find(Dan.class).where().like("datum", a.getDan().getDatum().toString()).findUnique();  
               d.getAktivnosti().add(a);
               server.save(d);
            }
    }
    
    
        
    public void saveSheep(Ovca sheep){
             System.out.println("Update" + sheep.getId());System.out.println("Update");
        setOtac(sheep);                     // povezi ovcu sa ocem i majkom iz baze
        setMother(sheep);
        if (sheep.getId()!=null){           // ako je ovca vec postojala u bazi, pronadji original i uskladi data
            Ovca o = server.find(Ovca.class, sheep.getId());  
            setSheep(o,sheep);
            server.save(o);
            return;
        }
        server.save(sheep);                 // ako nije postojala snimi je direktno
    }
    
    private void setMother(Ovca sheep){
        System.out.println("Majka: " + sheep.getMajka());
        if (sheep.getMajka().getOznaka()!= null){ //ako je oznaka uneta kroz formu
            Ovca majka = server.find(Ovca.class).where().like("oznaka", sheep.getMajka().getOznaka().toString()).findUnique();  
            if (majka==null){   
               sheep.getMajka().setPol('ž');// ako majka nije postojala u bazi       
               server.save(sheep.getMajka());   // snimi majku kao 'nepoznatu' i uzmi njen id
               majka = server.find(Ovca.class).where().like("oznaka", sheep.getMajka().getOznaka().toString()).findUnique();  
            
            }
            sheep.setMajka(majka);
            proveriStaruMajku(sheep);
        }
    }

    private void setOtac(Ovca sheep){
        if (sheep.getOtac().getOznaka()!= null){ //ako je oznaka vec uneta kroz formular
            Ovca otac = server.find(Ovca.class).where().like("oznaka", sheep.getOtac().getOznaka().toString()).findUnique();  
            if (otac==null){       
               sheep.getOtac().setPol('m');     // ako otac nije postojala u bazi       
               server.save(sheep.getOtac());   // snimi oca kao 'nepoznatu' i uzmi njen id
               otac = server.find(Ovca.class).where().like("oznaka", sheep.getOtac().getOznaka().toString()).findUnique();  
            
            }
            sheep.setOtac(otac);
            proveriStarogOca(sheep);
        }
    }
    private void proveriStarogOca(Ovca sheep){
        if (sheep.getId()!=null){
            System.out.println("step 1");
            Ovca staraOvca = server.find(Ovca.class, sheep.getId());
            Ovca stariOtac = staraOvca.getOtac();
            if (stariOtac.getId()==sheep.getOtac().getId()){
                return;
            }
            System.out.println("step 2, status: " + stariOtac.getStatus());
            if (stariOtac.getStatus()!=null && stariOtac.getStatus().equals("zamisljena")){
                System.out.println("step 2");
                List<Ovca> listaSinova = server.find(Ovca.class).where().like("otac_id", stariOtac.getId().toString()).findList();
                if (listaSinova.size()==1){   // ako je stari otac zamisljen i bio je otac samo ovoj ovci - izbrisi ga
                    System.out.println("step 3");
                    server.delete(stariOtac);
                }
            }
        }
    }
    private void proveriStaruMajku(Ovca sheep){
        if (sheep.getId()!=null){
            Ovca staraOvca = server.find(Ovca.class, sheep.getId());
            Ovca staraMajka = staraOvca.getMajka();
                        System.out.println("step 2, status: " + staraMajka.getStatus());
            if (staraMajka.getId()==sheep.getMajka().getId()){
                return;
            }
            if (staraMajka.getStatus()!=null && staraMajka.getStatus().equals("zamisljena")){
                List<Ovca> listaPotomaka = server.find(Ovca.class).where().like("majka_id", staraMajka.getId().toString()).findList();
                if (listaPotomaka.size()==1){   // ako je stari otac zamisljen i bio je otac samo ovoj ovci - izbrisi ga
                    System.out.println("step 3");
                    server.delete(staraMajka);
                }
            }
        }
    }

    private void setSheep(Ovca ovca1, Ovca ovca2){
        ovca1.setDatumRodjenja(ovca2.getDatumRodjenja());
        ovca1.setNadimak(ovca2.getNadimak());
        ovca1.setOpis(ovca2.getOpis());
        ovca1.setOznaka(ovca2.getOznaka());
        ovca1.setProcenatR(ovca2.getProcenatR());
        ovca1.setLeglo(ovca2.getLeglo());
        ovca1.setPol(ovca2.getPol());
        ovca1.setOtac(ovca2.getOtac());
        ovca1.setMajka(ovca2.getMajka());
    }

}
