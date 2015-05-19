package app.services;

import app.model.Ovca;
import com.avaje.ebean.EbeanServer;
import java.util.ArrayList;
import java.util.List;

public class OvcaService {
    private EbeanServer server;
    
    public OvcaService(EbeanServer server){
        this.server = server;
    }
    
    public List<Ovca> getAllSheep() {
         List<Ovca> list = server.find(Ovca.class).findList();
         for(Ovca o:list){
             o.getOtac();
             o.getMajka();
         }
         return list;
    }
    
    public Ovca getOvca(int id) {
        Ovca o =  server.find(Ovca.class, id);
        o.getOtac();
        o.getMajka();
        return o;
    }
    
    public Ovca getOvca(String oznaka) {
        return server.find(Ovca.class).where().like("oznaka", oznaka).findUnique();
    }
    
    
     public void saveSheep(Ovca sheep) {
         
        sheep.getOtac();
        linkToFather(sheep);   
        sheep.getMajka();// povezi ovcu sa ocem i majkom iz baze
        linkToMother(sheep);
        if (sheep.getId()!=null){           // ako je ovca vec postojala u bazi, pronadji original i uskladi data
            Ovca o = server.find(Ovca.class, sheep.getId());  
            setSheep(o,sheep);
          //  System.out.println("Trenutak pre snimanja novi otac :" + o.getOtac().getOznaka());
            server.save(o);
            //server.save(o.getOtac());  //????????????? ne znam sto ovo mora!?!?!?!?
           // System.err.println("novi otac id:" + o.getOtac().getId());
            return;
        }
        server.save(sheep); 
    }
     private void linkToMother(Ovca sheep){
       // System.out.println("Majka: " + sheep.getMajka());
        if (sheep.getMajka().getOznaka()!= null){ //ako je oznaka uneta kroz formu
            Ovca majka = server.find(Ovca.class).where().like("oznaka", sheep.getMajka().getOznaka().toString()).findUnique();  
            if (majka==null){   
               sheep.getMajka().setPol('ž');// ako majka nije postojala u bazi   
               Ovca defaultOtac = server.find(Ovca.class).where().like("oznaka", "nepoznat").findUnique();  
               Ovca defaultMajka = server.find(Ovca.class).where().like("oznaka", "nepoznata").findUnique();  
               sheep.getMajka().setMajka(defaultMajka); 
               sheep.getMajka().setOtac(defaultOtac);
               server.save(sheep.getMajka());   // snimi majku kao 'nepoznatu' i uzmi njen id
               majka = server.find(Ovca.class).where().like("oznaka", sheep.getMajka().getOznaka().toString()).findUnique();  
            
            }
            sheep.setMajka(majka);
            proveriStaruMajku(sheep);
        }
    }

    private void linkToFather(Ovca sheep){
       // System.out.println("novi Otac: " + sheep.getOtac().getOznaka());
        if (sheep.getOtac().getOznaka()!= null){ //ako je oznaka vec uneta kroz formular
            Ovca otac = server.find(Ovca.class).where().like("oznaka", sheep.getOtac().getOznaka().toString()).findUnique();  
            if (otac==null){     // ako otac nije postojala u bazi   
               sheep.getOtac().setPol('m');  
               Ovca defaultOtac = server.find(Ovca.class).where().like("oznaka", "nepoznat").findUnique();  
               Ovca defaultMajka = server.find(Ovca.class).where().like("oznaka", "nepoznata").findUnique();  
               sheep.getOtac().setMajka(defaultMajka); 
               sheep.getOtac().setOtac(defaultOtac);
               server.save(sheep.getOtac());   // snimi oca kao 'nepoznatu' i uzmi njen id
               otac = server.find(Ovca.class).where().like("oznaka", sheep.getOtac().getOznaka().toString()).findUnique();  
              // System.out.println("Kreiran je novi otac oznake" + otac.getOznaka());
            }
            sheep.setOtac(otac);
            //System.out.println("ponovo" + sheep.getOtac().getOznaka());
            proveriStarogOca(sheep);
        }
    }
    private void proveriStarogOca(Ovca sheep){
        if (sheep.getId()!=null){
         //   System.out.println("step 1");
            Ovca staraOvca = server.find(Ovca.class, sheep.getId());
            Ovca stariOtac = staraOvca.getOtac();
            //System.out.println("Stari otac: " + stariOtac.getOznaka() + " id: " + stariOtac.getId());
            //System.out.println("Novi otac: " + sheep.getOtac().getOznaka() + " id: " + sheep.getOtac().getId());
            //System.out.println("Isti: " + stariOtac.equals(sheep.getOtac()));
            if (stariOtac==null || (stariOtac.equals(sheep.getOtac()))){
                return;
            } 
            ocistiOca(stariOtac);
        }
    }
    
    private void proveriStaruMajku(Ovca sheep){
        if (sheep.getId()!=null){
            Ovca staraOvca = server.find(Ovca.class, sheep.getId());
            Ovca staraMajka = staraOvca.getMajka();
             //           System.out.println("step 2, status: " + staraMajka.getStatus());
            if (staraMajka==null || (staraMajka.equals(sheep.getMajka()))){
                return;
            }
            ocistiMajku(staraMajka);
        }       
        
    }

    private void setSheep(Ovca ovca1, Ovca ovca2){
        ovca1.setDatumRodjenja(ovca2.getDatumRodjenja());
        ovca1.setNadimak(ovca2.getNadimak());
        ovca1.setPracenje(ovca2.getPracenje());
        ovca1.setLinija(ovca2.getLinija());
        ovca1.setOpis(ovca2.getOpis());
        ovca1.setOznaka(ovca2.getOznaka());
        ovca1.setProcenatR(ovca2.getProcenatR());
        ovca1.setPoreklo(ovca2.getPoreklo());
        ovca1.setLeglo(ovca2.getLeglo());
        ovca1.setPol(ovca2.getPol());
        ovca1.setOtac(ovca2.getOtac());
        ovca1.setMajka(ovca2.getMajka());
        ovca1.setAktuelno(ovca2.getAktuelno());
        ovca1.setTezinaNaRodjenju(ovca2.getTezinaNaRodjenju());
        ovca1.setTezinaDvaMeseca(ovca2.getTezinaDvaMeseca());
        ovca1.setTezinaCetiriMeseca(ovca2.getTezinaCetiriMeseca());
                
    }
    
    public List<Ovca> getOvceZaJagnjenje() {
         List<Ovca> list = server.find(Ovca.class).where().like("status", "na farmi").like("pol", "ž").findList();
        if (list!=null){
            return list;
        }
        return new ArrayList<Ovca>();

    }

    public List<Ovca> listaOvnova() {
        List<Ovca> list = server.find(Ovca.class).where().like("pol", "m").findList();
        if (list!=null){
            return list;
        }
        return new ArrayList<Ovca>();
    }

    public List<Ovca> getSvaZivaGrla() {
       return  server.find(Ovca.class).where().like("status", "na farmi").findList();
    }
    
    public void undoStatus(Ovca ovca){
        Ovca o = server.find(Ovca.class, ovca.getId());
        if (o!=null){
            if (o.getProdaja()!=null){
                o.setStatus("prodato");
            } else if (o.getUginuce()!=null){
                o.setStatus("uginulo");
            } else if (o.getRodjenje()==null && o.getNabavka()==null){ // ako nikad nije ni bio na farmi
                o.setStatus("zamisljena");
            } else {
                o.setStatus("na farmi");
            }
            server.save(o);
        }
    }
    
    public void setStatus(Ovca ovca, String status){
        Ovca o = server.find(Ovca.class, ovca.getId());
        if (o!=null){
            o.setStatus(status);
            server.save(o);
        }
    }
    
    public void deleteSheep(Ovca o){
            ocistiMajku(o.getMajka());
            ocistiOca(o.getOtac());
            server.delete(o);
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
    
    public void saveSheepStatus(Ovca sheep, String status){
            Ovca o = server.find(Ovca.class, sheep.getId());  
            o.setStatus(status);
            server.save(o);
    }

    public void updateOvcaFromList(Ovca o) {
        Ovca stara = server.find(Ovca.class, o.getId()); 
        stara.setAktuelno(o.getAktuelno());
        stara.setOznaka(o.getOznaka());
        stara.setNadimak(o.getNadimak());
        server.save(stara);
    }

    public List<Ovca> getSveOvnove() {
         return server.find(Ovca.class).where().like("status", "na farmi").like("pol", "m").findList();
    }
    
}
