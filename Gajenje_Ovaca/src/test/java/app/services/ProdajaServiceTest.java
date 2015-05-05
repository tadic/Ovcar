package app.services;

import app.model.Aktivnost;
import app.model.Dan;
import app.model.Merenje;
import app.model.Ovca;
import app.model.Prodaja;
import app.model.VrsteAktivnosti;
import com.avaje.ebean.EbeanServer;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ProdajaServiceTest {
     private EbeanServer server;
     private OvcaService ovcaService;
     private ProdajaService prodajaService;
     private List<Ovca> listaOvaca;
     private Aktivnost snimljena;
  
     private int ukupnoOvaca, naFarmi;
     
    @Before
    public void setUp() throws Exception{
        DBHelper dbHelper = new DBHelper();
        server = dbHelper.initializeDatabaseServer();
        ovcaService = new OvcaService(server);
        prodajaService = new ProdajaService(server);  
        listaOvaca = ovcaService.getSvaZivaGrla().subList(4,7);
        ukupnoOvaca = ovcaService.getAllSheep().size();
        naFarmi = ovcaService.getSvaZivaGrla().size();
    }
    
    @Test
    public void createActivityTest(){
        Aktivnost novaAktivnost = noveProdaje();
        prodajaService.createActivity(novaAktivnost);
        snimljena = server.find(Aktivnost.class, novaAktivnost.getId());
        List<Prodaja> fromBaseProdaje = snimljena.getProdaje();
        
        assertTrue(ukupnoOvaca == ovcaService.getAllSheep().size());        // da li je broj ovaca ostao nepromenjen
        assertTrue((naFarmi-3) == ovcaService.getSvaZivaGrla().size());     // da li je broj grla na farmi smanje za tri
        
        assertEquals(Integer.valueOf(600), snimljena.getVremePocetka());
        assertEquals(Integer.valueOf(800), snimljena.getVremeZavrsetka());
        assertEquals(Float.valueOf(snimljena.getTroskovi()),Float.valueOf(1000.9f));
        assertEquals("jasenica", snimljena.getLokacija());
        assertEquals(Integer.valueOf(20150202), snimljena.getDan().getDatum());
        assertEquals("pronap", snimljena.getNapomena());
        
        assertEquals("prodato", snimljena.getProdaje().get(0).getOvca().getStatus());
        assertEquals("prodato", snimljena.getProdaje().get(1).getOvca().getStatus());
        assertEquals("prodato", snimljena.getProdaje().get(2).getOvca().getStatus());
        
        assertEquals(snimljena.getProdaje().size(), 3);
        List<Prodaja> prodaje = server.find(Prodaja.class).where().like("aktivnost_id", snimljena.getId().toString()).findList();
        assertEquals(prodaje.size(), 3);
        
        assertEquals(fromBaseProdaje.get(0).getOvca(), noveProdaje().getProdaje().get(0).getOvca());
        assertEquals(fromBaseProdaje.get(0).getNapomena(),"napomena1");
        assertTrue(fromBaseProdaje.get(0).getTezina() == 23.3);
        assertTrue(fromBaseProdaje.get(0).getCenaGrla() == 123);
        assertTrue(fromBaseProdaje.get(0).getCenaKg() == 15.3);
        assertEquals("Milivoje",fromBaseProdaje.get(0).getKupac());
        
        assertEquals(fromBaseProdaje.get(1).getOvca(), noveProdaje().getProdaje().get(1).getOvca());
        assertEquals(fromBaseProdaje.get(1).getNapomena(),"napomena2");
        assertTrue(fromBaseProdaje.get(1).getTezina() == 21.3);
        assertTrue(fromBaseProdaje.get(1).getCenaGrla() == 100);
        assertTrue(fromBaseProdaje.get(1).getCenaKg() == 10);
        assertEquals("Rastimir",fromBaseProdaje.get(1).getKupac());
        
        assertEquals(fromBaseProdaje.get(2).getOvca(), noveProdaje().getProdaje().get(2).getOvca());
        assertEquals(fromBaseProdaje.get(2).getNapomena(),"napomena3");
        assertTrue(fromBaseProdaje.get(2).getTezina() == 20.0);
        assertTrue(fromBaseProdaje.get(2).getCenaGrla() == 250);
        assertTrue(fromBaseProdaje.get(2).getCenaKg() == 0.0);
        assertNull(fromBaseProdaje.get(2).getKupac());
        
        prodajaService.deleteActivity(snimljena);
        assertNull(server.find(Aktivnost.class, snimljena.getId()));
        prodaje = server.find(Prodaja.class).where().like("aktivnost_id", snimljena.getId().toString()).findList();
        assertEquals(prodaje.size(), 0);
        
        // proverava da li se status ovce vratio na 'na farmi' posto je prodaja obrisana
        for (Ovca o: listaOvaca){
            Ovca ovca = ovcaService.getOvca(o.getId());
            assertEquals("na farmi", ovca.getStatus());
        }
        assertTrue(ukupnoOvaca == ovcaService.getAllSheep().size());        // da li je broj ovaca ostao nepromenjen
        assertTrue(naFarmi == ovcaService.getSvaZivaGrla().size());
    }
    
    @Test
    public void updateActivityTest(){
        Aktivnost novaAktivnost = noveProdaje();
        prodajaService.createActivity(novaAktivnost);
        snimljena = server.find(Aktivnost.class, novaAktivnost.getId());
        
        Ovca promenjenaOvca = ovcaService.getSvaZivaGrla().get(5);
        int stariId = snimljena.getId();
        snimljena.setProdaje(noveProdaje().getProdaje());
        snimljena.getProdaje().get(0).setNapomena("promenjenaNapomena");
        snimljena.getProdaje().get(0).setTezina(15.1);
        snimljena.getProdaje().get(2).setOvca(promenjenaOvca);
        snimljena.getProdaje().get(2).setTezina(15.0);
        snimljena.getProdaje().remove(1);
        
        snimljena.setVremePocetka(500);
        snimljena.setVremeZavrsetka(750);
        snimljena.setLokacija("s.Jasenica");
        snimljena.setTroskovi(130.0f);
        snimljena.setDan(new Dan(2015, 2, 4));
        snimljena.setNapomena("novaNapomena");
        
        prodajaService.updateActivity(snimljena);  
        
        List<Aktivnost> all =  server.find(Aktivnost.class).findList();
        snimljena = all.get(all.size()-1);
        
        List<Prodaja> fromBaseProdaje = snimljena.getProdaje();
        List<Prodaja> prodaje = server.find(Prodaja.class).where().like("aktivnost_id", snimljena.getId().toString()).findList();
        
        assertTrue(ukupnoOvaca == ovcaService.getAllSheep().size());        // da li je broj ovaca ostao nepromenjen
        assertTrue((naFarmi-2) == ovcaService.getSvaZivaGrla().size());
        assertEquals(prodaje.size(), 2);           // broj snimljenih merenja
        assertEquals(fromBaseProdaje.size(), 2);   // velicina liste snimljenih merenja
        assertEquals("prodato", snimljena.getProdaje().get(0).getOvca().getStatus());
        assertEquals("prodato", snimljena.getProdaje().get(1).getOvca().getStatus());   // Novopostavljena ovca
        Ovca zamenjenaOvca = ovcaService.getOvca(listaOvaca.get(2).getId());            //zamenjena
      //  assertEquals("na farmi", zamenjenaOvca.getStatus());  
        Ovca obrisanaOvca = ovcaService.getOvca(listaOvaca.get(1).getId());             // obrisana prodaja
//        assertEquals("na farmi", obrisanaOvca.getStatus());
        
        
        // provera updejtovanih podataka

        assertEquals(Integer.valueOf(500), snimljena.getVremePocetka());
        assertEquals(Integer.valueOf(750), snimljena.getVremeZavrsetka());
        assertEquals(Float.valueOf(snimljena.getTroskovi()),Float.valueOf(130.0f));
        assertEquals("s.Jasenica", snimljena.getLokacija());
        assertEquals(Integer.valueOf(20150204), snimljena.getDan().getDatum());
        assertEquals("novaNapomena", snimljena.getNapomena());
        
        assertEquals(fromBaseProdaje.get(0).getOvca(), noveProdaje().getProdaje().get(0).getOvca());
        assertEquals(fromBaseProdaje.get(0).getNapomena(),"promenjenaNapomena");
        assertTrue(fromBaseProdaje.get(0).getTezina() == 15.1);
        
        assertEquals(fromBaseProdaje.get(1).getOvca(), promenjenaOvca);
        assertEquals(fromBaseProdaje.get(1).getNapomena(),"napomena3");
        assertTrue(fromBaseProdaje.get(1).getTezina() == 15.0);

        // da li je ocistio staru aktivnost
        assertNull(server.find(Aktivnost.class, stariId));          
        prodaje = server.find(Prodaja.class).where().like("aktivnost_id", String.valueOf(stariId)).findList();
        assertEquals(prodaje.size(), 0);

        //delete Test
        assertEquals(snimljena.getProdaje().size(), 2);     // velicina pre brisanja
        prodajaService.deleteActivity(snimljena);
        assertNull(server.find(Aktivnost.class, snimljena.getId()));
        prodaje = server.find(Prodaja.class).where().like("aktivnost_id", snimljena.getId().toString()).findList();
        assertEquals(prodaje.size(), 0);
        
        for (Ovca o: listaOvaca){
            Ovca ovca = ovcaService.getOvca(o.getId());
            assertEquals("na farmi", ovca.getStatus());
        }
        promenjenaOvca = ovcaService.getSvaZivaGrla().get(5);
        assertEquals("na farmi", promenjenaOvca.getStatus());
        assertTrue(ukupnoOvaca == ovcaService.getAllSheep().size());        // da li je broj ovaca ostao nepromenjen
        assertTrue(naFarmi == ovcaService.getSvaZivaGrla().size());
    }
    
    

    private Aktivnost noveProdaje(){
        Dan d = new Dan(2015, 2, 2);
        Aktivnost a = new Aktivnost();
        a.setDan(d);
        a.setVremePocetka(600);
        a.setVremeZavrsetka(800);
        a.setLokacija("jasenica");
        a.setTroskovi(1000.9f);
        a.setVrstaAktivnosti(new VrsteAktivnosti("Prodaja"));
        a.setNapomena("pronap");
        

        List<Prodaja> prodaje = new ArrayList<Prodaja>();
        System.out.println(listaOvaca.get(0));
        Prodaja  p = new Prodaja();
        p.setOvca(listaOvaca.get(0));
        p.setTezina(23.3);
        p.setNapomena("napomena1");
        p.setCenaGrla(123);
        p.setCenaKg(15.3);
        p.setKupac("Milivoje");
        prodaje.add(p);
        
        p = new Prodaja();
        p.setOvca(listaOvaca.get(1));
        p.setTezina(21.3);
        p.setNapomena("napomena2");
        p.setCenaGrla(100);
        p.setCenaKg(10);
        p.setKupac("Rastimir");
        prodaje.add(p);
        
        p = new Prodaja();
        p.setOvca(listaOvaca.get(2));
        p.setTezina(20.0);
        p.setNapomena("napomena3");
        p.setCenaGrla(250);
        prodaje.add(p);
        
        a.setProdaje(prodaje);
        return a;
    }

}
