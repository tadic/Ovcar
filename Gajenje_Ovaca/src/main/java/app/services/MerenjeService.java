/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.services;

import app.model.Aktivnost;
import app.model.Merenje;
import app.model.Ovca;
import com.avaje.ebean.EbeanServer;
import java.util.Calendar;

/**
 *
 * @author ivantadic
 */
class MerenjeService extends ActivityService {

    public MerenjeService(EbeanServer server) {
         super(server);
    }
      
   
    public void createActivity(Aktivnost a){
        snimiKontrolneTacke(a);
        saveDayAndActivity(a.getDan(), a);
    }
    
    
    public void updateActivity(Aktivnost a){
        deleteActivity(a);
        Aktivnost nova = new Aktivnost();
        setActivity(nova, a);
        nova.setMerenja(a.getMerenja());
        createActivity(nova);
    }
       
    
    
    public void deleteActivity(Aktivnost a) {
        Aktivnost act = server.find(Aktivnost.class, a.getId());
        if (act!=null){
            server.delete(act);
        }
    }
    
    
    public void snimiKontrolneTacke(Aktivnost a){
        Calendar date = a.getDan().getDate();
        for (Merenje m: a.getMerenja()){
            Ovca o = m.getOvca();
            int starostDana = o.starostUDanima(date);
            if (starostDana>0 && starostDana<7){
                tezinaNaRodjenju(o, m, starostDana);
            } else if (starostDana>50 && starostDana<70){
                tezinaNaOdlucenju(o, m, starostDana);
            } else if (starostDana>105 && starostDana<135){
                tezinaCetiriMeseca(o, m, starostDana);
            }
                
        }
    }

    
 private void tezinaNaRodjenju(Ovca o, Merenje m, int starostDana){
          Ovca ovca = server.find(Ovca.class, o.getId());
          float prirast = 0.0f;
          if (starostDana>3){
              prirast = 0.2f * (starostDana-3);
          }
          System.out.println("rodjenje");

          ovca.setTezinaNaRodjenju(m.getTezina()-prirast);
          server.save(ovca);
 }
  private void tezinaNaOdlucenju(Ovca o, Merenje m, int starostDana){
          Ovca ovca = server.find(Ovca.class, o.getId());
          float tezinaRodjenje = 3.0f;
          if (o.getTezinaNaRodjenju()>1){
              tezinaRodjenje = o.getTezinaNaRodjenju();
          }               
          float dnevniPrirast = (m.getTezina()-tezinaRodjenje)/starostDana;
//                    System.out.println("tezini-tezRodjenje: " +  (m.getTezina()-tezinaRodjenje) + ", starost dana: " + starostDana);
          ovca.setTezinaDvaMeseca(tezinaRodjenje + 60*dnevniPrirast);
         System.out.println("dva: " + ovca.getTezinaDvaMeseca());
          server.save(ovca);
 }
   private void tezinaCetiriMeseca(Ovca o, Merenje m, int starostDana){
          Ovca ovca = server.find(Ovca.class, o.getId());
          float tezinaRodjenje = 3.0f;
          if (o.getTezinaNaRodjenju()>0){
              tezinaRodjenje = o.getTezinaNaRodjenju();
          }               
          float dnevniPrirast = (m.getTezina()-tezinaRodjenje)/starostDana;

          ovca.setTezinaCetiriMeseca(tezinaRodjenje + 120*dnevniPrirast);
          System.out.println("cetiri: " + ovca.getTezinaCetiriMeseca());
          server.save(ovca);
 }
 

    
}
