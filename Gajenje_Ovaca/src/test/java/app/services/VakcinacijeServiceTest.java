package app.services;

import app.mainPanels.Merenja;
import app.model.Aktivnost;
import app.model.Dan;
import app.model.Merenje;
import app.model.Ovca;
import app.model.Vakcinacija;
import app.model.VrsteAktivnosti;
import com.avaje.ebean.EbeanServer;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class VakcinacijeServiceTest {
     private EbeanServer server;
     private OvcaService ovcaService;
     private VakcinacijaService vakcinacijaService;
     
     private Aktivnost snimljena;
     private int ukupnoOvaca, naFarmi;

   
    @Before
    public void setUp() throws Exception{
        DBHelper dbHelper = new DBHelper();
        server = dbHelper.initializeDatabaseServer();
        ovcaService = new OvcaService(server);
        vakcinacijaService = new VakcinacijaService(server);  
        ukupnoOvaca = ovcaService.getAllSheep().size();
        naFarmi = ovcaService.getSvaZivaGrla().size();
    }
    
    @Test
    public void createActivityTest(){
        Aktivnost novaAktivnost = noveVakcinacije();
        vakcinacijaService.createActivity(novaAktivnost);
        snimljena = server.find(Aktivnost.class, novaAktivnost.getId());
        List<Vakcinacija> fromBaseVAkcinacije = snimljena.getVakcinacije();
        
        assertTrue(ukupnoOvaca == ovcaService.getAllSheep().size());        // da li je broj ovaca ostao nepromenjen
        assertTrue(naFarmi == ovcaService.getSvaZivaGrla().size());
        
        assertEquals(Integer.valueOf(600), snimljena.getVremePocetka());
        assertEquals(Integer.valueOf(800), snimljena.getVremeZavrsetka());
        assertEquals(Float.valueOf(snimljena.getTroskovi()),Float.valueOf(0.0f));
        assertEquals("jasenica", snimljena.getLokacija());
        assertEquals(Integer.valueOf(20150101), snimljena.getDan().getDatum());
        assertEquals("vakcin", snimljena.getNapomena());
        
        assertEquals(snimljena.getVakcinacije().size(), 3);
        List<Vakcinacija> vakcinacije = server.find(Vakcinacija.class).where().like("aktivnost_id", snimljena.getId().toString()).findList();
        assertEquals(vakcinacije.size(), 3);
        
        assertEquals(fromBaseVAkcinacije.get(0).getOvca(), noveVakcinacije().getVakcinacije().get(0).getOvca());
        assertEquals(fromBaseVAkcinacije.get(0).getNapomena(), "napomena1");
        assertEquals(fromBaseVAkcinacije.get(0).getRazlog(), "razlog");
        assertEquals(fromBaseVAkcinacije.get(0).getSredstvo(), "Ampicilin");
        
        assertEquals(fromBaseVAkcinacije.get(1).getOvca(), noveVakcinacije().getVakcinacije().get(1).getOvca());
        assertEquals(fromBaseVAkcinacije.get(0).getRazlog(), "razlog");
        assertEquals(fromBaseVAkcinacije.get(0).getSredstvo(), "Ampicilin");
        
        assertEquals(fromBaseVAkcinacije.get(2).getOvca(), noveVakcinacije().getVakcinacije().get(2).getOvca());
        assertEquals(fromBaseVAkcinacije.get(0).getRazlog(), "razlog");
        assertEquals(fromBaseVAkcinacije.get(0).getSredstvo(), "Ampicilin");
        
        vakcinacijaService.deleteActivity(snimljena);
        assertNull(server.find(Aktivnost.class, snimljena.getId()));
        vakcinacije = server.find(Vakcinacija.class).where().like("aktivnost_id", snimljena.getId().toString()).findList();
        assertEquals(vakcinacije.size(), 0);
        
        assertTrue(ukupnoOvaca == ovcaService.getAllSheep().size());        // da li je broj ovaca ostao nepromenjen
        assertTrue(naFarmi == ovcaService.getSvaZivaGrla().size());
    }
    
    @Test
    public void updateActivityTest(){
        Aktivnost novaAktivnost = noveVakcinacije();
        vakcinacijaService.createActivity(novaAktivnost);
        snimljena = server.find(Aktivnost.class, novaAktivnost.getId());
        
        Ovca promenjenaOvca = ovcaService.getSvaZivaGrla().get(0);
        int stariId = snimljena.getId();
        
        snimljena.setVakcinacije(noveVakcinacije().getVakcinacije());
        snimljena.getVakcinacije().remove(1);
        snimljena.getVakcinacije().get(0).setNapomena("promenjenaNapomena");
        snimljena.getVakcinacije().get(0).setRazlog("noviRazlog");
        snimljena.getVakcinacije().get(1).setOvca(promenjenaOvca);
        snimljena.getVakcinacije().get(1).setSredstvo("Invermektin");
        
        snimljena.setVremePocetka(500);
        snimljena.setVremeZavrsetka(750);
        snimljena.setLokacija("s.Jasenica");
        snimljena.setTroskovi(30.0f);
        snimljena.setDan(new Dan(2015, 1, 4));
        snimljena.setNapomena("novaNapomena");
        
        vakcinacijaService.updateActivity(snimljena);  
        
        List<Aktivnost> all =  server.find(Aktivnost.class).findList();
        snimljena = all.get(all.size()-1);
        
        List<Vakcinacija> fromBaseVakcinacije = snimljena.getVakcinacije();
        List<Vakcinacija> vakcinacije = server.find(Vakcinacija.class).where().like("aktivnost_id", snimljena.getId().toString()).findList();
        
        assertTrue(ukupnoOvaca == ovcaService.getAllSheep().size());        // da li je broj ovaca ostao nepromenjen
        assertTrue(naFarmi == ovcaService.getSvaZivaGrla().size());
        assertEquals(2, vakcinacije.size());           // broj snimljenih merenja
        assertEquals(2, fromBaseVakcinacije.size());   // velicina liste snimljenih merenja
        
        // provera updejtovanih podataka

        assertEquals(Integer.valueOf(500), snimljena.getVremePocetka());
        assertEquals(Integer.valueOf(750), snimljena.getVremeZavrsetka());
        assertEquals(Float.valueOf(snimljena.getTroskovi()),Float.valueOf(30.0f));
        assertEquals("s.Jasenica", snimljena.getLokacija());
        assertEquals(Integer.valueOf(20150104), snimljena.getDan().getDatum());
        assertEquals("novaNapomena", snimljena.getNapomena());
        
        assertEquals(noveVakcinacije().getVakcinacije().get(0).getOvca(), fromBaseVakcinacije.get(0).getOvca());
        assertEquals("promenjenaNapomena", fromBaseVakcinacije.get(0).getNapomena());
        assertEquals("noviRazlog", fromBaseVakcinacije.get(0).getRazlog());
        assertEquals("Ampicilin", fromBaseVakcinacije.get(0).getSredstvo());
        
        assertEquals(ovcaService.getSvaZivaGrla().get(0), fromBaseVakcinacije.get(1).getOvca());
        assertEquals("napomena3", fromBaseVakcinacije.get(1).getNapomena());
        assertEquals("razlog", fromBaseVakcinacije.get(1).getRazlog());
        assertEquals("Invermektin", fromBaseVakcinacije.get(1).getSredstvo());
        

        // da li je ocistio staru aktivnost
        assertNull(server.find(Aktivnost.class, stariId));          
        vakcinacije = server.find(Vakcinacija.class).where().like("aktivnost_id", String.valueOf(stariId)).findList();
        assertEquals(vakcinacije.size(), 0);

        //delete Test
        assertEquals(snimljena.getVakcinacije().size(), 2);     // velicina pre brisanja
        vakcinacijaService.deleteActivity(snimljena);
        assertNull(server.find(Aktivnost.class, snimljena.getId()));
        vakcinacije = server.find(Vakcinacija.class).where().like("aktivnost_id", snimljena.getId().toString()).findList();
        assertEquals(vakcinacije.size(), 0);
        assertTrue(ukupnoOvaca == ovcaService.getAllSheep().size());        // da li je broj ovaca ostao nepromenjen
        assertTrue(naFarmi == ovcaService.getSvaZivaGrla().size());
    }
    
    

    private Aktivnost noveVakcinacije(){
        Dan d = new Dan(2015, 1, 1);
        Aktivnost a = new Aktivnost();
        a.setDan(d);
        a.setVremePocetka(600);
        a.setVremeZavrsetka(800);
        a.setLokacija("jasenica");
        a.setVrstaAktivnosti(new VrsteAktivnosti("Vakcinacija/Lečenje"));
        a.setNapomena("vakcin");
        
        List<Ovca> list = ovcaService.getSvaZivaGrla().subList(5,8);
        List<Vakcinacija> vakcinacije = new ArrayList<Vakcinacija>();
        
        Vakcinacija  v = new Vakcinacija();
        v.setOvca(list.get(0));
        v.setRazlog("razlog");
        v.setSredstvo("Ampicilin");
        v.setNapomena("napomena1");
        vakcinacije.add(v);
        
        v = new Vakcinacija();
        v.setOvca(list.get(1));
        v.setRazlog("razlog");
        v.setSredstvo("Ampicilin");
        v.setNapomena("napomena2");
        vakcinacije.add(v);
        
        v = new Vakcinacija();
        v.setOvca(list.get(2));
        v.setRazlog("razlog");
        v.setSredstvo("Ampicilin");
        v.setNapomena("napomena3");
        vakcinacije.add(v);
        
        a.setVakcinacije(vakcinacije);
        return a;
    }

}
