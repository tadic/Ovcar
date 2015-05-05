package app.services;

import app.mainPanels.Merenja;
import app.model.Aktivnost;
import app.model.Dan;
import app.model.Merenje;
import app.model.Ovca;
import app.model.Parenje;
import app.model.Vakcinacija;
import app.model.VrsteAktivnosti;
import com.avaje.ebean.EbeanServer;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ParenjeServiceTest {
     private EbeanServer server;
     private OvcaService ovcaService;
     private ParenjeService parenjeService;
     
     private Aktivnost snimljena;
     private int ukupnoOvaca, naFarmi;

   
    @Before
    public void setUp() throws Exception{
        DBHelper dbHelper = new DBHelper();
        server = dbHelper.initializeDatabaseServer();
        ovcaService = new OvcaService(server);
        parenjeService = new ParenjeService(server);  
        ukupnoOvaca = ovcaService.getAllSheep().size();
        naFarmi = ovcaService.getSvaZivaGrla().size();
    }
    
    @Test
    public void createActivityTest(){
        Aktivnost novaAktivnost = novaParenja();
        parenjeService.createActivity(novaAktivnost);
        snimljena = server.find(Aktivnost.class, novaAktivnost.getId());
        List<Parenje> fromBaseParenja = snimljena.getParenja();
        Ovca ovan = ovcaService.getSveOvnove().get(0);
        
        assertTrue(ukupnoOvaca == ovcaService.getAllSheep().size());        // da li je broj ovaca ostao nepromenjen
        assertTrue(naFarmi == ovcaService.getSvaZivaGrla().size());
        
        assertEquals(Integer.valueOf(600), snimljena.getVremePocetka());
        assertEquals(Integer.valueOf(800), snimljena.getVremeZavrsetka());
        assertEquals(Float.valueOf(snimljena.getTroskovi()),Float.valueOf(0.0f));
        assertEquals("jasenica", snimljena.getLokacija());
        assertEquals(Integer.valueOf(20150101), snimljena.getDan().getDatum());
        assertEquals("parenje", snimljena.getNapomena());
        
        assertEquals(snimljena.getParenja().size(), 3);
        List<Parenje> parenja = server.find(Parenje.class).where().like("aktivnost_id", snimljena.getId().toString()).findList();
        assertEquals(parenja.size(), 3);
        
        assertEquals(fromBaseParenja.get(0).getOvca(), novaParenja().getParenja().get(0).getOvca());
        assertEquals(fromBaseParenja.get(0).getNapomena(), "napomena1");
        assertEquals(fromBaseParenja.get(0).getTip(), "Spajanje");
        assertEquals(fromBaseParenja.get(0).getOvan(), ovan);
        
        assertEquals(fromBaseParenja.get(1).getOvca(), novaParenja().getParenja().get(1).getOvca());
        assertEquals(fromBaseParenja.get(1).getNapomena(), "napomena2");
        assertEquals(fromBaseParenja.get(1).getTip(), "Odvajanje");
        
        assertEquals(fromBaseParenja.get(2).getOvca(), novaParenja().getParenja().get(2).getOvca());
        assertEquals(fromBaseParenja.get(2).getNapomena(), "napomena3");
        assertEquals(fromBaseParenja.get(2).getTip(), "Parenje");

        
        parenjeService.deleteActivity(snimljena);
        assertNull(server.find(Aktivnost.class, snimljena.getId()));
        parenja = server.find(Parenje.class).where().like("aktivnost_id", snimljena.getId().toString()).findList();
        assertEquals(parenja.size(), 0);
        
        assertTrue(ukupnoOvaca == ovcaService.getAllSheep().size());        // da li je broj ovaca ostao nepromenjen
        assertTrue(naFarmi == ovcaService.getSvaZivaGrla().size());
    }
    
    @Test
    public void updateActivityTest1(){
        Aktivnost novaAktivnost = novaParenja();
        parenjeService.createActivity(novaAktivnost);
        snimljena = server.find(Aktivnost.class, novaAktivnost.getId());
        
        Ovca promenjenaOvca = ovcaService.getSvaZivaGrla().get(0);
        Ovca ovan = ovcaService.getSveOvnove().get(0);
        int stariId = snimljena.getId();
        
        snimljena.setParenja(novaParenja().getParenja());
        snimljena.getParenja().remove(1);
        snimljena.getParenja().get(0).setNapomena("promenjenaNapomena");
        snimljena.getParenja().get(0).setTip("Parenje");
        snimljena.getParenja().get(1).setOvca(promenjenaOvca);
        snimljena.getParenja().get(1).setTip("Parenje");
        
        snimljena.setVremePocetka(500);
        snimljena.setVremeZavrsetka(750);
        snimljena.setLokacija("s.Jasenica");
        snimljena.setTroskovi(30.0f);
        snimljena.setDan(new Dan(2015, 1, 7));
        snimljena.setNapomena("novaNapomena");
        
        parenjeService.updateActivity(snimljena);  
        
        List<Aktivnost> all =  server.find(Aktivnost.class).findList();
        snimljena = all.get(all.size()-1);
        
        List<Parenje> fromBaseParenja = snimljena.getParenja();
        List<Parenje> parenja = server.find(Parenje.class).where().like("aktivnost_id", snimljena.getId().toString()).findList();
        
        assertTrue(ukupnoOvaca == ovcaService.getAllSheep().size());        // da li je broj ovaca ostao nepromenjen
        assertTrue(naFarmi == ovcaService.getSvaZivaGrla().size());
        assertEquals(2, parenja.size());           // broj snimljenih merenja
        assertEquals(2, fromBaseParenja.size());   // velicina liste snimljenih merenja
        
        // provera updejtovanih podataka

        assertEquals(Integer.valueOf(500), snimljena.getVremePocetka());
        assertEquals(Integer.valueOf(750), snimljena.getVremeZavrsetka());
        assertEquals(Float.valueOf(snimljena.getTroskovi()),Float.valueOf(30.0f));
        assertEquals("s.Jasenica", snimljena.getLokacija());
        assertEquals(Integer.valueOf(20150107), snimljena.getDan().getDatum());
        assertEquals("novaNapomena", snimljena.getNapomena());
        
        assertEquals(novaParenja().getParenja().get(0).getOvca(), fromBaseParenja.get(0).getOvca());
        assertEquals(ovan, fromBaseParenja.get(0).getOvan());
        assertEquals("promenjenaNapomena", fromBaseParenja.get(0).getNapomena());
        assertEquals("Parenje", fromBaseParenja.get(0).getTip());
        
        assertEquals(ovcaService.getSvaZivaGrla().get(0), fromBaseParenja.get(1).getOvca());
        assertEquals(ovan, fromBaseParenja.get(1).getOvan());
        assertEquals("napomena3", fromBaseParenja.get(1).getNapomena());
        assertEquals("Parenje", fromBaseParenja.get(1).getTip());
        

        // da li je ocistio staru aktivnost
        assertNull(server.find(Aktivnost.class, stariId));          
        parenja = server.find(Parenje.class).where().like("aktivnost_id", String.valueOf(stariId)).findList();
        assertEquals(parenja.size(), 0);

        //delete Test
        assertEquals(snimljena.getParenja().size(), 2);     // velicina pre brisanja
        parenjeService.deleteActivity(snimljena);
        assertNull(server.find(Aktivnost.class, snimljena.getId()));
        parenja = server.find(Parenje.class).where().like("aktivnost_id", snimljena.getId().toString()).findList();
        assertEquals(parenja.size(), 0);
        assertTrue(ukupnoOvaca == ovcaService.getAllSheep().size());        // da li je broj ovaca ostao nepromenjen
        assertTrue(naFarmi == ovcaService.getSvaZivaGrla().size());
    }
    
    @Test
    public void updateActivityTest2(){
        Aktivnost novaAktivnost = novaParenja();
        parenjeService.createActivity(novaAktivnost);
        snimljena = server.find(Aktivnost.class, novaAktivnost.getId());
        
        Ovca promenjenaOvca = ovcaService.getSvaZivaGrla().get(0);
        Ovca promenjenOvan = ovcaService.getSveOvnove().get(1);
        int stariId = snimljena.getId();
        
        snimljena.setParenja(novaParenja().getParenja());
        snimljena.getParenja().remove(1);
        snimljena.getParenja().get(0).setNapomena("promenjenaNapomena");
        snimljena.getParenja().get(0).setOvan(promenjenOvan);
        snimljena.getParenja().get(1).setOvca(promenjenaOvca);
        snimljena.getParenja().get(1).setOvan(promenjenOvan);
        
        parenjeService.updateActivity(snimljena);  
        
        List<Aktivnost> all =  server.find(Aktivnost.class).findList();
        snimljena = all.get(all.size()-1);
        
        List<Parenje> fromBaseParenja = snimljena.getParenja();
        List<Parenje> parenja = server.find(Parenje.class).where().like("aktivnost_id", snimljena.getId().toString()).findList();
        
        assertTrue(ukupnoOvaca == ovcaService.getAllSheep().size());        // da li je broj ovaca ostao nepromenjen
        assertTrue(naFarmi == ovcaService.getSvaZivaGrla().size());
        assertEquals(2, parenja.size());           // broj snimljenih merenja
        assertEquals(2, fromBaseParenja.size());   // velicina liste snimljenih merenja
        
        // provera updejtovanih podataka
        
        assertEquals(novaParenja().getParenja().get(0).getOvca(), fromBaseParenja.get(0).getOvca());
        assertEquals(promenjenOvan, fromBaseParenja.get(0).getOvan());
        assertEquals("promenjenaNapomena", fromBaseParenja.get(0).getNapomena());
        
        assertEquals(ovcaService.getSvaZivaGrla().get(0), fromBaseParenja.get(1).getOvca());
        assertEquals(promenjenOvan, fromBaseParenja.get(1).getOvan());
        
        // da li je ocistio staru aktivnost
        assertNull(server.find(Aktivnost.class, stariId));          
        parenja = server.find(Parenje.class).where().like("aktivnost_id", String.valueOf(stariId)).findList();
        assertEquals(parenja.size(), 0);

        //delete Test
        assertEquals(snimljena.getParenja().size(), 2);     // velicina pre brisanja
        parenjeService.deleteActivity(snimljena);
        assertNull(server.find(Aktivnost.class, snimljena.getId()));
        parenja = server.find(Parenje.class).where().like("aktivnost_id", snimljena.getId().toString()).findList();
        assertEquals(parenja.size(), 0);
        assertTrue(ukupnoOvaca == ovcaService.getAllSheep().size());        // da li je broj ovaca ostao nepromenjen
        assertTrue(naFarmi == ovcaService.getSvaZivaGrla().size());
    }
    
    

    private Aktivnost novaParenja(){
        Dan d = new Dan(2015, 1, 1);
        Aktivnost a = new Aktivnost();
        a.setDan(d);
        a.setVremePocetka(600);
        a.setVremeZavrsetka(800);
        a.setLokacija("jasenica");
        a.setVrstaAktivnosti(new VrsteAktivnosti("Parenje"));
        a.setNapomena("parenje");
        
        List<Ovca> list = ovcaService.getSvaZivaGrla().subList(5,8);
        Ovca ovan = ovcaService.getSveOvnove().get(0);
        List<Parenje> parenja = new ArrayList<Parenje>();
        
        Parenje  p = new Parenje();
        p.setOvca(list.get(0));
        p.setTip("Spajanje");
        p.setNapomena("napomena1");
        p.setOvan(ovan);
        parenja.add(p);
        
        p = new Parenje();
        p.setOvca(list.get(1));
        p.setTip("Odvajanje");
        p.setNapomena("napomena2");
        p.setOvan(ovan);
        parenja.add(p);
        
        p = new Parenje();
        p.setOvca(list.get(2));
        p.setTip("Parenje");
        p.setNapomena("napomena3");
        p.setOvan(ovan);
        parenja.add(p);
        
        a.setParenja(parenja);
        return a;
    }

}
