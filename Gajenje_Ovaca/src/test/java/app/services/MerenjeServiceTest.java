package app.services;

import app.model.Aktivnost;
import app.model.Dan;
import app.model.Merenje;
import app.model.Ovca;
import app.model.VrsteAktivnosti;
import com.avaje.ebean.EbeanServer;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MerenjeServiceTest {
     private EbeanServer server;
     private OvcaService ovcaService;
     private MerenjeService merenjeService;
     
     private Aktivnost snimljena;
     private int ukupnoOvaca, naFarmi;

   
    @Before
    public void setUp() throws Exception{
        DBHelper dbHelper = new DBHelper();
        server = dbHelper.initializeDatabaseServer();
        ovcaService = new OvcaService(server);
        merenjeService = new MerenjeService(server);
        ukupnoOvaca = ovcaService.getAllSheep().size();
        naFarmi = ovcaService.getSvaZivaGrla().size();
    }
    
    @Test
    public void createActivityTest(){
        Aktivnost novaAktivnost = noveProdaje();
        merenjeService.createActivity(novaAktivnost);
        snimljena = server.find(Aktivnost.class, novaAktivnost.getId());
        List<Merenje> fromBaseMerenja = snimljena.getMerenja();
        
        assertTrue(ukupnoOvaca == ovcaService.getAllSheep().size());        // da li je broj ovaca ostao nepromenjen
        assertTrue(naFarmi == ovcaService.getSvaZivaGrla().size());
        
        assertEquals(Integer.valueOf(600), snimljena.getVremePocetka());
        assertEquals(Integer.valueOf(800), snimljena.getVremeZavrsetka());
        assertEquals(Float.valueOf(snimljena.getTroskovi()),Float.valueOf(123.7f));
        assertEquals("jasenica", snimljena.getLokacija());
        assertEquals(Integer.valueOf(20150501), snimljena.getDan().getDatum());
        assertEquals("asdf", snimljena.getNapomena());
        
        assertEquals(snimljena.getMerenja().size(), 3);
        List<Merenje> merenja = server.find(Merenje.class).where().like("aktivnost_id", snimljena.getId().toString()).findList();
        assertEquals(merenja.size(), 3);
        
        assertEquals(fromBaseMerenja.get(0).getOvca(), noveProdaje().getMerenja().get(0).getOvca());
        assertEquals(fromBaseMerenja.get(0).getNapomena(),"napomena1");
        assertTrue(fromBaseMerenja.get(0).getTezina() == 23.3f);
        
        assertEquals(fromBaseMerenja.get(1).getOvca(), noveProdaje().getMerenja().get(1).getOvca());
        assertEquals(fromBaseMerenja.get(1).getNapomena(),"napomena2");
        assertTrue(fromBaseMerenja.get(1).getTezina() == 21.3f);
        
        assertEquals(fromBaseMerenja.get(2).getOvca(), noveProdaje().getMerenja().get(2).getOvca());
        assertEquals(fromBaseMerenja.get(2).getNapomena(),"napomena3");
        assertTrue(fromBaseMerenja.get(2).getTezina() == 20.0f);
        
        merenjeService.deleteActivity(snimljena);
        assertNull(server.find(Aktivnost.class, snimljena.getId()));
        merenja = server.find(Merenje.class).where().like("aktivnost_id", snimljena.getId().toString()).findList();
        assertEquals(merenja.size(), 0);
        assertTrue(ukupnoOvaca == ovcaService.getAllSheep().size());        // da li je broj ovaca ostao nepromenjen
        assertTrue(naFarmi == ovcaService.getSvaZivaGrla().size());
    }
    
    @Test
    public void updateActivityTest(){
        Aktivnost novaAktivnost = noveProdaje();
        merenjeService.createActivity(novaAktivnost);
        snimljena = server.find(Aktivnost.class, novaAktivnost.getId());
        
        Ovca promenjenaOvca = ovcaService.getSvaZivaGrla().get(5);
        int stariId = snimljena.getId();
        snimljena.setMerenja(noveProdaje().getMerenja());
        snimljena.getMerenja().get(0).setNapomena("promenjenaNapomena");
        snimljena.getMerenja().get(1).setTezina(15.0f);
        snimljena.getMerenja().get(2).setOvca(promenjenaOvca);
        snimljena.setVremePocetka(500);
        snimljena.setVremeZavrsetka(750);
        snimljena.setLokacija("s.Jasenica");
        snimljena.setTroskovi(130.0f);
        snimljena.setDan(new Dan(2015, 5, 4));
        snimljena.setNapomena("novaNapomena");
        
        merenjeService.updateActivity(snimljena);  
        
        List<Aktivnost> all =  server.find(Aktivnost.class).findList();
        snimljena = all.get(all.size()-1);
        
        List<Merenje> fromBaseMerenja = snimljena.getMerenja();
        List<Merenje> merenja = server.find(Merenje.class).where().like("aktivnost_id", snimljena.getId().toString()).findList();
        
        assertTrue(ukupnoOvaca == ovcaService.getAllSheep().size());        // da li je broj ovaca ostao nepromenjen
        assertTrue(naFarmi == ovcaService.getSvaZivaGrla().size());
        assertEquals(merenja.size(), 3);           // broj snimljenih merenja
        assertEquals(fromBaseMerenja.size(), 3);   // velicina liste snimljenih merenja
        
        // provera updejtovanih podataka

        assertEquals(Integer.valueOf(500), snimljena.getVremePocetka());
        assertEquals(Integer.valueOf(750), snimljena.getVremeZavrsetka());
        assertEquals(Float.valueOf(snimljena.getTroskovi()),Float.valueOf(130.0f));
        assertEquals("s.Jasenica", snimljena.getLokacija());
        assertEquals(Integer.valueOf(20150504), snimljena.getDan().getDatum());
        assertEquals("novaNapomena", snimljena.getNapomena());
        
        assertEquals(fromBaseMerenja.get(0).getOvca(), noveProdaje().getMerenja().get(0).getOvca());
        assertEquals(fromBaseMerenja.get(0).getNapomena(),"promenjenaNapomena");
        assertTrue(fromBaseMerenja.get(0).getTezina() == 23.3f);
        
        assertEquals(fromBaseMerenja.get(1).getOvca(), noveProdaje().getMerenja().get(1).getOvca());
        assertEquals(fromBaseMerenja.get(1).getNapomena(),"napomena2");
        assertTrue(fromBaseMerenja.get(1).getTezina() == 15.0f);
        
        assertEquals(fromBaseMerenja.get(2).getOvca(), promenjenaOvca);
        assertEquals(fromBaseMerenja.get(2).getNapomena(),"napomena3");
        assertTrue(fromBaseMerenja.get(2).getTezina() == 20.0f);

        // da li je ocistio staru aktivnost
        assertNull(server.find(Aktivnost.class, stariId));          
        merenja = server.find(Merenje.class).where().like("aktivnost_id", String.valueOf(stariId)).findList();
        assertEquals(merenja.size(), 0);

        //delete Test
        assertEquals(snimljena.getMerenja().size(), 3);     // velicina pre brisanja
        merenjeService.deleteActivity(snimljena);
        assertNull(server.find(Aktivnost.class, snimljena.getId()));
        merenja = server.find(Merenje.class).where().like("aktivnost_id", snimljena.getId().toString()).findList();
        assertEquals(merenja.size(), 0);
        assertTrue(ukupnoOvaca == ovcaService.getAllSheep().size());        // da li je broj ovaca ostao nepromenjen
        assertTrue(naFarmi == ovcaService.getSvaZivaGrla().size());
    }
    
    

    private Aktivnost noveProdaje(){
        Dan d = new Dan(2015, 5, 1);
        Aktivnost a = new Aktivnost();
        a.setDan(d);
        a.setVremePocetka(600);
        a.setVremeZavrsetka(800);
        a.setLokacija("jasenica");
        a.setTroskovi(123.7f);
        a.setVrstaAktivnosti(new VrsteAktivnosti("Merenje"));
        a.setNapomena("asdf");
        
        List<Ovca> list = ovcaService.getSvaZivaGrla().subList(0,3);
        List<Merenje> merenja = new ArrayList<Merenje>();
        
        Merenje  m = new Merenje();
        m.setOvca(list.get(0));
        m.setTezina(23.3f);
        m.setNapomena("napomena1");
        merenja.add(m);
        
        m = new Merenje();
        m.setOvca(list.get(1));
        m.setTezina(21.3f);
        m.setNapomena("napomena2");
        merenja.add(m);
        
        m = new Merenje();
        m.setOvca(list.get(2));
        m.setTezina(20.0f);
        m.setNapomena("napomena3");
        merenja.add(m);
        
        a.setMerenja(merenja);
        return a;
    }

}
