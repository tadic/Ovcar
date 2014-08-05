/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.database;

import app.model.Aktivnost;
import app.model.Dan;
import app.model.Linija;
import app.model.NabavkaOvaca;
import app.model.Ovca;
import app.model.VrsteAktivnosti;
import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author ivantadic
 */
public class DataBase {
    private EbeanServer server;
    
    public DataBase(boolean dropAndCreateDB){
        server = new DBServer(dropAndCreateDB).server();
    }
    
      public List<Dan> getAllDays() {
        List<Dan> listOfDays = server.find(Dan.class).order().asc("datum").findList();
        if (listOfDays==null){
            return new ArrayList<Dan>();
        }
        return listOfDays;
    }

    public List<VrsteAktivnosti> getAllTypesOfActivities() {
        List<VrsteAktivnosti> typesOfActivities = server.find(VrsteAktivnosti.class).findList();
        if (typesOfActivities==null){
            return new ArrayList<VrsteAktivnosti>();
        }
        return typesOfActivities;
    }

 /*   public void saveDay(Dan day) {
        Aktivnost akt = day.getAktivnosti().get(day.getAktivnosti().size()-1);
        Dan dDb = server.find(Dan.class).where().like("datum", day.getDatum().toString()).findUnique();
        if (dDb==null){
            server.save(day);
            return;
        }
        dDb.getAktivnosti().add(akt);
        server.save(dDb);
       
    }*/
    


    public List<Ovca> getAllSheep() {
         List<Ovca> list = server.find(Ovca.class).findList();
         System.out.println("hei ima li koga lista je ovde "+ server.find(Ovca.class).findRowCount()+" "+list.size());
         return list;
    }

    public Ovca getOvca(String oznaka) {
        return server.find(Ovca.class).where().like("oznaka", oznaka).findUnique();
    }

    public Aktivnost getActivityWithId(Integer id) {
        return server.find(Aktivnost.class).where().like("id", id.toString()).findUnique();  

    }

    public List<Aktivnost> getActivitiesFrom(Dan d) {
        List<Aktivnost> list = server.find(Aktivnost.class).where().like("dan_id", d.getId().toString()).findList();  
        if (list != null){
            return list;
        }
        return new ArrayList<Aktivnost>();
    }

    public List<NabavkaOvaca> getListOfNabavkaFor(Aktivnost aktivnost) {
        return  server.find(NabavkaOvaca.class).where().like("aktivnost_id", aktivnost.getId().toString()).findList();  
    }



    public Dan getDayWithDate(Integer date) {
        return  server.find(Dan.class).where().like("datum", date.toString()).findUnique();    
    }

    public void saveActivity(Aktivnost aktivnost) {
        if (aktivnost.getVrstaAktivnosti().getName().equals("Nabavka ovaca")){
            new DBNabavkaOvaca(server).saveActivity(aktivnost);
        } else if (aktivnost.getVrstaAktivnosti().getName().equals("Jagnjenje")) {
            new DBJagnjenja(server).saveActivity(aktivnost);
        } else if (aktivnost.getVrstaAktivnosti().getName().equals("Uginuce")) {
            new DBUginuca(server).saveActivity(aktivnost);
        } else if (aktivnost.getVrstaAktivnosti().getName().equals("Prodaja")) {
            new DBProdaja(server).saveActivity(aktivnost);
        } else if (aktivnost.getVrstaAktivnosti().getName().equals("Vakcinacija/Lečenje")) {
            new DBVakcinacija(server).saveActivity(aktivnost);
        } else {
            new DBRadovi(server).saveActivity(aktivnost);
        }
    }

    public void update(Ovca sheep) {
        setOtac(sheep);                     // povezi ovcu sa ocem i majkom iz baze
        setMother(sheep);
        if (sheep.getId()!=null){           // ako je ovca vec postojala u bazi, pronadji original i uskladi data
            Ovca o = server.find(Ovca.class, sheep.getId());  
            setSheep(o,sheep);
            System.err.println("Trenutak pre snimanja novi otac :" + o.getOtac().getOznaka());
            server.save(o);
            server.save(o.getOtac());
            System.err.println("novi otac id:" + o.getOtac().getId());
            return;
        }
        server.save(sheep); 
    }
     private void setMother(Ovca sheep){
     //   System.out.println("Majka: " + sheep.getMajka());
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
            System.out.println("Kreiran je novi otac oznake" + otac.getOznaka());
            }
            sheep.setOtac(otac);
            System.out.println("ponovo" + sheep.getOtac().getOznaka());
            proveriStarogOca(sheep);
        }
    }
    private void proveriStarogOca(Ovca sheep){
        if (sheep.getId()!=null){
         //   System.out.println("step 1");
            Ovca staraOvca = server.find(Ovca.class, sheep.getId());
            Ovca stariOtac = staraOvca.getOtac();
                    System.out.println("Stari otac: " + stariOtac);
                    if (stariOtac==null || stariOtac.getId()==sheep.getOtac().getId()){
                        return;
                    }
                //    System.out.println("step 2, status: " + stariOtac.getStatus());
                    if (stariOtac.getStatus()!=null && stariOtac.getStatus().equals("zamisljena")){
                      //  System.out.println("step 2");
                        List<Ovca> listaSinova = server.find(Ovca.class).where().like("otac_id", stariOtac.getId().toString()).findList();
                        if (listaSinova.size()==1){   // ako je stari otac zamisljen i bio je otac samo ovoj ovci - izbrisi ga
                            System.out.println("brise se stari otac");
                            server.delete(stariOtac);
                        }
                    }
        }
    }
    private void proveriStaruMajku(Ovca sheep){
        if (sheep.getId()!=null){
            Ovca staraOvca = server.find(Ovca.class, sheep.getId());
            Ovca staraMajka = staraOvca.getMajka();
             //           System.out.println("step 2, status: " + staraMajka.getStatus());
            if (staraMajka==null || staraMajka.getId()==sheep.getMajka().getId()){
                return;
            }
            if (staraMajka.getStatus()!=null && staraMajka.getStatus().equals("zamisljena")){
                List<Ovca> listaPotomaka = server.find(Ovca.class).where().like("majka_id", staraMajka.getId().toString()).findList();
                if (listaPotomaka.size()==1){   // ako je stari otac zamisljen i bio je otac samo ovoj ovci - izbrisi ga
             //       System.out.println("step 3");
                    server.delete(staraMajka);
                }
            }
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
        ovca1.setLeglo(ovca2.getLeglo());
        ovca1.setPol(ovca2.getPol());
        ovca1.setOtac(ovca2.getOtac());
        ovca1.setMajka(ovca2.getMajka());
        ovca1.setAktuelno(ovca2.getAktuelno());
                
    }
 

    public void deleteActivity(Aktivnost aktivnost) {
        if (aktivnost.getVrstaAktivnosti().getName().equals("Nabavka ovaca")){
            new DBNabavkaOvaca(server).deleteActivity(aktivnost);
        } else if (aktivnost.getVrstaAktivnosti().getName().equals("Jagnjenje")) {
            new DBJagnjenja(server).deleteActivity(aktivnost);
        } else if (aktivnost.getVrstaAktivnosti().getName().equals("Uginuce")) {
            new DBUginuca(server).deleteActivity(aktivnost);
        } else  if (aktivnost.getVrstaAktivnosti().getName().equals("Prodaja")) {
            new DBProdaja(server).deleteActivity(aktivnost);
        } else if (aktivnost.getVrstaAktivnosti().getName().equals("Vakcinacija/Lečenje")) {
            new DBVakcinacija(server).deleteActivity(aktivnost);
        } else {
            new DBRadovi(server).deleteActivity(aktivnost);
        }
    }

    public List<Linija> getLinije() {
       return  server.find(Linija.class).findList();
    }

    public Ovca getOvca(int id) {
        Ovca o =  server.find(Ovca.class, id);
        return o;
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

    public Dan getDan(Integer id) {
        return server.find(Dan.class, id);
    }






    
    
    
    
    
    
    
    
    
    
    
    
}
