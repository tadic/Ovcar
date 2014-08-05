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
        Aktivnost act = server.find(Aktivnost.class, a.getId());  
        setActivity(act, a);
        deleteOldJagnjenja(act, act);
        updateJagnjenja(a);
        server.save(act);
    }
    
    private void deleteOldJagnjenja(Aktivnost staraAktivnost, Aktivnost novaAktivnost){
        for (Jagnjenje j: staraAktivnost.getListaJagnjenja()){
            if (!novaAktivnost.getListaJagnjenja().contains(j)){
                deleteSheep(j.getSheep());
                server.delete(j);
            }
        }
    }
    
    private void setActivity(Aktivnost act, Aktivnost a){
        act.setDan(a.getDan());
        act.setLokacija(a.getLokacija());
        act.setNapomena(a.getNapomena());
        act.setVremePocetka(a.getVremePocetka());
        act.setVremeZavrsetka(a.getVremeZavrsetka());
        act.setVrstaAktivnosti(a.getVrstaAktivnosti());
        act.setTroskovi(a.getTroskovi());
        act.setBilans(a.getBilans());
    }
    
    private void updateJagnjenja(Aktivnost a){
        String datumJagnjenja = a.getDan().toString();
        for (Jagnjenje j: a.getListaJagnjenja()){
            //saveSheep(j.getSheep());
            j.getSheep().setDatumRodjenja(datumJagnjenja);
            updateJagnjenje(j, a);    // prvo update jagnjenje da bi definitivno dobilo id i jagnjetov id
            
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
            saveSheep(j.getSheep());
            server.save(jagnjenje);
            
        }else{
            j.setAktivnost(a);
            saveSheep(j.getSheep());
            server.save(j);
        }
    }
    private void createActivity(Aktivnost a){
        a.getDan().getAktivnosti().add(a);// unesi u panel
        String datumJagnjenja = a.getDan().toString();
            for (Jagnjenje jagnjenje: a.getListaJagnjenja()){
                jagnjenje.getSheep().setDatumRodjenja(datumJagnjenja);
                System.out.println("Ulazimo");
                saveSheep(jagnjenje.getSheep());
            }
            if (a.getDan().getId()==null){ // ako ga nema u bazi, napravi ga
             //   System.out.println("dan: "+ a.getDan().toString());
                server.save(a.getDan());
            } else{
               Dan d = server.find(Dan.class).where().like("datum", a.getDan().getDatum().toString()).findUnique();  
               d.getAktivnosti().add(a);
               server.save(d);
            }
    }
    
    
        
    public void saveSheep(Ovca sheep){
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
      System.out.println("Provera majke");
        if (sheep.getMajka().getOznaka()!= null){ //ako je oznaka uneta kroz formu
            Ovca majka = server.find(Ovca.class).where().like("oznaka", sheep.getMajka().getOznaka().toString()).findUnique();  
            if (majka==null){   
                    System.out.println("Nije pronasao majku sa tom znakom "+ sheep.getMajka().getOznaka());
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
       // System.err.println("Otac linija: " + sheep.getOtac().getLinija().getImeLinije());
        sheep.setLinija(sheep.getOtac().getLinija());
    }
    private void proveriStarogOca(Ovca sheep){
        if (sheep.getId()!=null){
           // System.out.println("step 1");
            Ovca staraOvca = server.find(Ovca.class, sheep.getId());
            Ovca stariOtac = staraOvca.getOtac();
            if (stariOtac.getId()==sheep.getOtac().getId()){
                return;
            }
            ocistiOca(stariOtac);
        }
    }
    
    private void proveriStaruMajku(Ovca sheep){
        if (sheep.getId()!=null){
            Ovca staraOvca = server.find(Ovca.class, sheep.getId());
            Ovca staraMajka = staraOvca.getMajka();
          //              System.out.println("step 2, status: " + staraMajka.getStatus());
            if (staraMajka.getId()==sheep.getMajka().getId()){
                return;
            }
            ocistiMajku(staraMajka);
        }
    }
    private void ocistiMajku(Ovca o){
            if (o.getStatus()!=null && o.getStatus().equals("zamisljena")){
                List<Ovca> listaPotomaka = server.find(Ovca.class).where().like("majka_id", o.getId().toString()).findList();
                if (listaPotomaka.size()==1){   // ako je stari otac zamisljen i bio je otac samo ovoj ovci - izbrisi ga
                    server.delete(o);
                }
            }
    }
    private void ocistiOca(Ovca o){
            if (o.getStatus()!=null && o.getStatus().equals("zamisljena")){
                List<Ovca> listaPotomaka = server.find(Ovca.class).where().like("otac_id", o.getId().toString()).findList();
                if (listaPotomaka.size()==1){   // ako je stari otac zamisljen i bio je otac samo ovoj ovci - izbrisi ga
                    server.delete(o);
                }
            }
    }
    public void deleteSheep(Ovca o){
       ocistiMajku(o.getMajka());
       ocistiOca(o.getOtac());
       server.delete(o);
    }

    public void deleteActivity(Aktivnost a) {
        for (Jagnjenje j: a.getListaJagnjenja()){
            deleteSheep(j.getSheep());
        }
        server.delete(a);
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
        ovca1.setStatus(ovca2.getStatus());
        ovca1.setTezinaNaRodjenju(ovca2.getTezinaNaRodjenju());
        ovca1.setLinija(ovca2.getLinija());
    }

}
