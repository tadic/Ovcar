package app.services;

import app.model.Aktivnost;
import app.model.Dan;
import app.model.Ovca;
import app.model.Radovi;
import app.model.Uginuce;
import app.model.VrsteAktivnosti;
import com.avaje.ebean.EbeanServer;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class UginucaServiceTest {
     private EbeanServer server;
     private OvcaService ovcaService;
     private UginucaService uginucaService;
     
     private Aktivnost snimljena;
     private int ukupnoOvaca, naFarmi, uginulaId;

   
    @Before
    public void setUp() throws Exception{
        DBHelper dbHelper = new DBHelper();
        server = dbHelper.initializeDatabaseServer();
        ovcaService = new OvcaService(server);
        uginucaService = new UginucaService(server);
        ukupnoOvaca = ovcaService.getAllSheep().size();
        naFarmi = ovcaService.getSvaZivaGrla().size();
        uginulaId = ovcaService.getSvaZivaGrla().get(10).getId();
    }
    
    @Test
    public void createActivityTest(){
        Aktivnost uginuceAct = novoUginuce();
        uginucaService.createActivity(uginuceAct);
        snimljena = server.find(Aktivnost.class, uginuceAct.getId());
        Uginuce u = snimljena.getUginuce();
        Ovca uginulaOvca = ovcaService.getOvca(uginulaId);
        
        assertTrue(ukupnoOvaca == ovcaService.getAllSheep().size());        // da li je broj ovaca ostao nepromenjen
       // assertTrue((naFarmi-1) == ovcaService.getSvaZivaGrla().size());
        assertEquals(Integer.valueOf(600), snimljena.getVremePocetka());
        assertEquals(Integer.valueOf(800), snimljena.getVremeZavrsetka());
        assertEquals(Float.valueOf(snimljena.getTroskovi()),Float.valueOf(123.7f));
        assertEquals("jasenica", snimljena.getLokacija());
        assertEquals(Integer.valueOf(20150501), snimljena.getDan().getDatum());
        assertEquals("asdf", snimljena.getNapomena());

        
        assertEquals(uginulaOvca, u.getO());
        assertEquals("uginulo", u.getO().getStatus());
        assertEquals("prejedanje hrane", u.getRazlog());

        uginucaService.deleteActivity(snimljena);
        uginulaOvca = ovcaService.getOvca(uginulaId);
        assertNull(server.find(Aktivnost.class, snimljena.getId()));
        u = server.find(Uginuce.class).where().like("a_id", snimljena.getId().toString()).findUnique();
        assertNull(u);
        assertTrue(ukupnoOvaca == ovcaService.getAllSheep().size());        // da li je broj ovaca ostao nepromenjen
        assertTrue(naFarmi == ovcaService.getSvaZivaGrla().size());
        assertEquals("na farmi", uginulaOvca.getStatus());
    }
    
    @Test
    public void updateActivityTest(){               // update sa promenom ovce
        Aktivnost uginuceAct = novoUginuce();
        uginucaService.createActivity(uginuceAct);
        snimljena = server.find(Aktivnost.class, uginuceAct.getId());
        int stariId = snimljena.getId();
        
        Ovca novaOvca = ovcaService.getOvca(151);
        
        Uginuce novoU = new Uginuce();
        novoU.setRazlog("noviRazlog");
        novoU.setO(novaOvca);

        snimljena.setVremePocetka(500);
        snimljena.setVremeZavrsetka(750);
        snimljena.setLokacija("s.Jasenica");
        snimljena.setTroskovi(130.0f);
        snimljena.setDan(new Dan(2015, 5, 4));
        snimljena.setNapomena("novaNapomena");
        snimljena.setUginuce(novoU);
        
        uginucaService.updateActivity(snimljena);  
        
        List<Aktivnost> all =  server.find(Aktivnost.class).findList();
        snimljena = all.get(all.size()-1);
 
        // provera updejtovanih podataka
        assertTrue(ukupnoOvaca == ovcaService.getAllSheep().size());        // da li je broj ovaca ostao nepromenjen
      //  assertTrue((naFarmi-1) == ovcaService.getSvaZivaGrla().size());
        assertEquals(Integer.valueOf(500), snimljena.getVremePocetka());
        assertEquals(Integer.valueOf(750), snimljena.getVremeZavrsetka());
        assertEquals(Float.valueOf(snimljena.getTroskovi()),Float.valueOf(130.0f));
        assertEquals("s.Jasenica", snimljena.getLokacija());
        assertEquals(Integer.valueOf(20150504), snimljena.getDan().getDatum());
        assertEquals("novaNapomena", snimljena.getNapomena());
        
        Uginuce fromBaseU = server.find(Uginuce.class).where().like("a_id", snimljena.getId().toString()).findUnique();
        Ovca staraOvca = ovcaService.getOvca(uginulaId);
        novaOvca = ovcaService.getOvca(151);
        System.out.println(snimljena.getUginuce().getO().getId());
        assertEquals("uginulo", novaOvca.getStatus());
        assertEquals("na farmi", staraOvca.getStatus());
        assertEquals("noviRazlog", fromBaseU.getRazlog());
        assertEquals(novaOvca, fromBaseU.getO());

        // da li je to ta aktivnost
        assertTrue(snimljena.getId() == stariId);          

        //delete Test
        uginucaService.deleteActivity(snimljena);
        assertNull(server.find(Aktivnost.class, snimljena.getId()));
        fromBaseU = server.find(Uginuce.class).where().like("a_id", snimljena.getId().toString()).findUnique();
        assertNull(fromBaseU);
        assertTrue(ukupnoOvaca == ovcaService.getAllSheep().size());        // da li je broj ovaca ostao nepromenjen
        assertTrue(naFarmi == ovcaService.getSvaZivaGrla().size());
    }
        
    @Test
    public void updateActivityTest2(){          // update bez promene ovce
        Aktivnost uginuceAct = novoUginuce();
        uginucaService.createActivity(uginuceAct);
        snimljena = server.find(Aktivnost.class, uginuceAct.getId());
        int stariId = snimljena.getId();
        Ovca staraOvca = ovcaService.getOvca(uginulaId);
        Uginuce novoU = new Uginuce();
        novoU.setRazlog("noviRazlog");
        novoU.setO(staraOvca);

        snimljena.setVremePocetka(500);
        snimljena.setVremeZavrsetka(750);
        snimljena.setLokacija("s.Jasenica");
        snimljena.setTroskovi(130.0f);
        snimljena.setDan(new Dan(2015, 5, 4));
        snimljena.setNapomena("novaNapomena");
        snimljena.setUginuce(novoU);
        
        uginucaService.updateActivity(snimljena);  
        
        List<Aktivnost> all =  server.find(Aktivnost.class).findList();
        snimljena = all.get(all.size()-1);
 
        // provera updejtovanih podataka
        assertTrue(ukupnoOvaca == ovcaService.getAllSheep().size());        // da li je broj ovaca ostao nepromenjen
      //  assertTrue((naFarmi-1) == ovcaService.getSvaZivaGrla().size());
        assertEquals(Integer.valueOf(500), snimljena.getVremePocetka());
        assertEquals(Integer.valueOf(750), snimljena.getVremeZavrsetka());
        assertEquals(Float.valueOf(snimljena.getTroskovi()),Float.valueOf(130.0f));
        assertEquals("s.Jasenica", snimljena.getLokacija());
        assertEquals(Integer.valueOf(20150504), snimljena.getDan().getDatum());
        assertEquals("novaNapomena", snimljena.getNapomena());
        
        Uginuce fromBaseU = server.find(Uginuce.class).where().like("a_id", snimljena.getId().toString()).findUnique();
        staraOvca = ovcaService.getOvca(uginulaId);
        assertEquals("uginulo", staraOvca.getStatus());
        assertEquals("noviRazlog", fromBaseU.getRazlog());

        // da li je to ta aktivnost
        assertTrue(snimljena.getId() == stariId);          

        //delete Test
        uginucaService.deleteActivity(snimljena);
        assertNull(server.find(Aktivnost.class, snimljena.getId()));
        fromBaseU = server.find(Uginuce.class).where().like("a_id", snimljena.getId().toString()).findUnique();
        assertNull(fromBaseU);
        assertTrue(ukupnoOvaca == ovcaService.getAllSheep().size());        // da li je broj ovaca ostao nepromenjen
        assertTrue(naFarmi == ovcaService.getSvaZivaGrla().size());
        staraOvca = ovcaService.getOvca(uginulaId);
        assertEquals("na farmi", staraOvca.getStatus());
    }


    private Aktivnost novoUginuce(){
        Dan d = new Dan(2015, 5, 1);
        Aktivnost a = new Aktivnost();
        a.setDan(d);
        a.setVremePocetka(600);
        a.setVremeZavrsetka(800);
        a.setLokacija("jasenica");
        a.setTroskovi(123.7f);
        a.setVrstaAktivnosti(new VrsteAktivnosti("Uginuce"));
        a.setNapomena("asdf");        
        Uginuce u = new Uginuce();
        u.setO(ovcaService.getOvca(uginulaId));      
        u.setRazlog("prejedanje hrane");

        a.setUginuce(u);
        return a;
    }

}
