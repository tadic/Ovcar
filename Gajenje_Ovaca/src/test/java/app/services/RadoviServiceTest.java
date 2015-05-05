package app.services;

import app.mainPanels.Merenja;
import app.model.Aktivnost;
import app.model.Dan;
import app.model.Merenje;
import app.model.Ovca;
import app.model.Radovi;
import app.model.VrsteAktivnosti;
import com.avaje.ebean.EbeanServer;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class RadoviServiceTest {
     private EbeanServer server;
     private OvcaService ovcaService;
     private RadoviService radoviService;
     
     private Aktivnost snimljena;
     private int ukupnoOvaca, naFarmi;

   
    @Before
    public void setUp() throws Exception{
        DBHelper dbHelper = new DBHelper();
        server = dbHelper.initializeDatabaseServer();
        ovcaService = new OvcaService(server);
        radoviService = new RadoviService(server);
        ukupnoOvaca = ovcaService.getAllSheep().size();
        naFarmi = ovcaService.getSvaZivaGrla().size();
    }
    
    @Test
    public void createActivityTest(){
        Aktivnost radovi = noviRadovi();
        radoviService.createActivity(radovi);
        snimljena = server.find(Aktivnost.class, radovi.getId());
        Radovi r = snimljena.getRadovi();
        
        assertTrue(ukupnoOvaca == ovcaService.getAllSheep().size());        // da li je broj ovaca ostao nepromenjen
        assertTrue(naFarmi == ovcaService.getSvaZivaGrla().size());
        assertEquals(Integer.valueOf(600), snimljena.getVremePocetka());
        assertEquals(Integer.valueOf(800), snimljena.getVremeZavrsetka());
        assertEquals(Float.valueOf(snimljena.getTroskovi()),Float.valueOf(123.7f));
        assertEquals("jasenica", snimljena.getLokacija());
        assertEquals(Integer.valueOf(20150501), snimljena.getDan().getDatum());
        assertEquals("asdf", snimljena.getNapomena());

        
        assertEquals("100 bala", r.getKolicina());
        assertEquals("nabavka hrane", r.getRazlog());
        assertEquals("seno", r.getStaSeRadilo_Nabavilo());

        radoviService.deleteActivity(snimljena);
        assertNull(server.find(Aktivnost.class, snimljena.getId()));
        r = server.find(Radovi.class).where().like("aktivnost_id", snimljena.getId().toString()).findUnique();
        assertNull(r);
        assertTrue(ukupnoOvaca == ovcaService.getAllSheep().size());        // da li je broj ovaca ostao nepromenjen
        assertTrue(naFarmi == ovcaService.getSvaZivaGrla().size());
    }
    
    @Test
    public void updateActivityTest(){
        Aktivnost novaAktivnost = noviRadovi();
        radoviService.createActivity(novaAktivnost);
        snimljena = server.find(Aktivnost.class, novaAktivnost.getId());
        
        int stariId = snimljena.getId();
        snimljena.getRadovi().setRazlog("noviRazlog");
        snimljena.getRadovi().setStaSeRadilo_Nabavilo("noviSta");
        snimljena.getRadovi().setKolicina("100kg");
        snimljena.setVremePocetka(500);
        snimljena.setVremeZavrsetka(750);
        snimljena.setLokacija("s.Jasenica");
        snimljena.setTroskovi(130.0f);
        snimljena.setDan(new Dan(2015, 5, 4));
        snimljena.setNapomena("novaNapomena");
        
        radoviService.updateActivity(snimljena);  
        
        List<Aktivnost> all =  server.find(Aktivnost.class).findList();
        snimljena = all.get(all.size()-1);
        
        Radovi fromBaseRadovi = server.find(Radovi.class).where().like("aktivnost_id", snimljena.getId().toString()).findUnique();

        
        // provera updejtovanih podataka
        assertTrue(ukupnoOvaca == ovcaService.getAllSheep().size());        // da li je broj ovaca ostao nepromenjen
        assertTrue(naFarmi == ovcaService.getSvaZivaGrla().size());
        assertEquals(Integer.valueOf(500), snimljena.getVremePocetka());
        assertEquals(Integer.valueOf(750), snimljena.getVremeZavrsetka());
        assertEquals(Float.valueOf(snimljena.getTroskovi()),Float.valueOf(130.0f));
        assertEquals("s.Jasenica", snimljena.getLokacija());
        assertEquals(Integer.valueOf(20150504), snimljena.getDan().getDatum());
        assertEquals("novaNapomena", snimljena.getNapomena());
        
        assertEquals("noviRazlog", fromBaseRadovi.getRazlog());
        assertEquals("noviSta", fromBaseRadovi.getStaSeRadilo_Nabavilo());
        assertEquals("100kg", fromBaseRadovi.getKolicina());

        // da li je to ta aktivnost
        assertNotNull(server.find(Aktivnost.class, stariId));          

        //delete Test
        radoviService.deleteActivity(snimljena);
        assertNull(server.find(Aktivnost.class, snimljena.getId()));
        fromBaseRadovi = server.find(Radovi.class).where().like("aktivnost_id", snimljena.getId().toString()).findUnique();
        assertNull(fromBaseRadovi);
        assertTrue(ukupnoOvaca == ovcaService.getAllSheep().size());        // da li je broj ovaca ostao nepromenjen
        assertTrue(naFarmi == ovcaService.getSvaZivaGrla().size());
    }
    
    

    private Aktivnost noviRadovi(){
        Dan d = new Dan(2015, 5, 1);
        Aktivnost a = new Aktivnost();
        a.setDan(d);
        a.setVremePocetka(600);
        a.setVremeZavrsetka(800);
        a.setLokacija("jasenica");
        a.setTroskovi(123.7f);
        a.setVrstaAktivnosti(new VrsteAktivnosti("Merenje"));
        a.setNapomena("asdf");
        
        Radovi r = new Radovi();
        r.setKolicina("100 bala");
        r.setRazlog("nabavka hrane");
        r.setStaSeRadilo_Nabavilo("seno");
        
        a.setRadovi(r);
        return a;
    }

}
