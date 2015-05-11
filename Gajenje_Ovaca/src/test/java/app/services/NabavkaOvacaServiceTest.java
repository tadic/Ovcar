package app.services;

import app.model.Aktivnost;
import app.model.Dan;
import app.model.Merenje;
import app.model.NabavkaOvaca;
import app.model.Ovca;
import app.model.Prodaja;
import app.model.Uginuce;
import app.model.VrsteAktivnosti;
import com.avaje.ebean.EbeanServer;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class NabavkaOvacaServiceTest {
     private EbeanServer server;
     private OvcaService ovcaService;
     private NabavkaOvacaService nabavkaOvacaService;
     private UginucaService uginucaService;
     private Aktivnost snimljena;
  
     private int ukupnoOvaca, naFarmi;
     
    @Before
    public void setUp() throws Exception{
        DBHelper dbHelper = new DBHelper();
        server = dbHelper.initializeDatabaseServer();
        ovcaService = new OvcaService(server);
        nabavkaOvacaService = new NabavkaOvacaService(server);  
        uginucaService = new UginucaService(server);
        ukupnoOvaca = ovcaService.getAllSheep().size();
        naFarmi = ovcaService.getSvaZivaGrla().size();
    }
    
    @Test
    public void createActivityTest(){
        Aktivnost novaAktivnost = noveNabavke();
        
        nabavkaOvacaService.createActivity(novaAktivnost);
        
        snimljena = server.find(Aktivnost.class, novaAktivnost.getId());
        List<NabavkaOvaca> fromBaseNabavke = snimljena.getNabavljenaGrla();
        
        assertTrue(ukupnoOvaca+6 == ovcaService.getAllSheep().size());        // da li je broj uvecan za 6
        assertTrue((naFarmi+3) == ovcaService.getSvaZivaGrla().size());     // broj na farmi uvecan za 3za tri
        
        assertEquals(Integer.valueOf(600), snimljena.getVremePocetka());
        assertEquals(Integer.valueOf(800), snimljena.getVremeZavrsetka());
        assertEquals(Float.valueOf(snimljena.getTroskovi()),Float.valueOf(1000.9f));
        assertEquals("jasenica", snimljena.getLokacija());
        assertEquals(Integer.valueOf(20150323), snimljena.getDan().getDatum());
        assertEquals("nabavka ovaca", snimljena.getNapomena());
        
        assertEquals(snimljena.getNabavljenaGrla().size(), 3);
        List<NabavkaOvaca> nabavkeO = server.find(NabavkaOvaca.class).where().like("aktivnost_id", snimljena.getId().toString()).findList();
        assertEquals(nabavkeO.size(), 3);
        
        Ovca o = fromBaseNabavke.get(0).getSheep();
        assertEquals(o.getStatus(),"na farmi");
        assertEquals(o.getOznaka(),"OZ00321");
        assertEquals(o.getPol(),'m');
        assertTrue(o.getProcenatR() == 100.0f);
        assertEquals(o.getOpis(),"skroz crno 15 kg");
        assertEquals(o.getDatumRodjenja(),"20141010");
        assertTrue(o.getLeglo()== 3);
        assertTrue(o.getOtac().getId() == 1);
        assertTrue(o.getMajka().getId() == 2);
        
        o = fromBaseNabavke.get(1).getSheep();
        assertEquals(o.getStatus(),"na farmi");
        assertEquals(o.getOznaka(),"OZ00322");
        assertEquals(o.getPol(),'ž');
        assertTrue(o.getProcenatR() == 87.5f);
        assertEquals(o.getOpis(),"skroz belo 13 kg");
        assertEquals(o.getDatumRodjenja(),"20141110");
        assertTrue(o.getLeglo()== 2);
        assertTrue(o.getOtac().getId() == (o.getId()-1));
        assertTrue(o.getMajka().getId() == 2);
        
        o = fromBaseNabavke.get(2).getSheep();
        assertEquals(o.getStatus(),"na farmi");
        assertEquals(o.getOznaka(),"OZ00323");
        assertEquals(o.getPol(),'ž');
        assertTrue(o.getProcenatR() == 100.0f);
        assertEquals(o.getOpis(),"crno belo 12 kg");
        assertEquals(o.getDatumRodjenja(),"20141015");
        assertTrue(o.getLeglo()== 3);
        assertTrue(o.getOtac().getId() == (o.getId()-2));
        assertTrue(o.getMajka().getId() == (o.getId()-1));
        
        assertEquals(fromBaseNabavke.get(0).getNapomena(),"napomena nabavka1");
        assertTrue(fromBaseNabavke.get(0).getCena() == 123);
        assertEquals(fromBaseNabavke.get(1).getNapomena(),"napomena nabavka2");
        assertTrue(fromBaseNabavke.get(1).getCena() == 100);
        assertEquals(fromBaseNabavke.get(2).getNapomena(),"napomena nabavka3");
        assertTrue(fromBaseNabavke.get(2).getCena() == 250);
        
        nabavkaOvacaService.deleteActivity(snimljena);
        
        assertNull(server.find(Aktivnost.class, snimljena.getId()));
        nabavkeO = server.find(NabavkaOvaca.class).where().like("aktivnost_id", snimljena.getId().toString()).findList();
        assertEquals(nabavkeO.size(), 0);

        assertTrue(ukupnoOvaca == ovcaService.getAllSheep().size());        // da li je broj ovaca ostao nepromenjen
        assertTrue(naFarmi == ovcaService.getSvaZivaGrla().size());
    }
 
  @Test
    public void updateActivityTest(){
        Aktivnost novaAktivnost = noveNabavke();
        nabavkaOvacaService.createActivity(novaAktivnost);
        snimljena = server.find(Aktivnost.class, novaAktivnost.getId());
        
        int stariId = snimljena.getId();
        snimljena.getNabavljenaGrla().get(0).setNapomena("promenjenaNapomena");
        snimljena.getNabavljenaGrla().get(0).setCena(99.9f);
        snimljena.getNabavljenaGrla().get(2).getSheep().setOtac(new Ovca("zamisljen", "nepoznat", 'm'));
        
        snimljena.getNabavljenaGrla().get(0).getSheep().setDatumRodjenja("20140000");
        snimljena.getNabavljenaGrla().get(0).getSheep().setPol('ž');
        snimljena.getNabavljenaGrla().get(0).getSheep().setOpis("crno");
        snimljena.getNabavljenaGrla().get(0).getSheep().setProcenatR(99.9f);
        snimljena.getNabavljenaGrla().get(0).getSheep().setLeglo(4);
        snimljena.getNabavljenaGrla().get(0).getSheep().setOznaka("OZ00000");

        snimljena.setVremePocetka(500);
        snimljena.setVremeZavrsetka(750);
        snimljena.setLokacija("s.Jasenica");
        snimljena.setTroskovi(130.0f);
        snimljena.setDan(new Dan(2015, 2, 4));
        snimljena.setNapomena("novaNapomena");
        
        assertTrue(ukupnoOvaca+6 == ovcaService.getAllSheep().size());        // da li je broj uvecan za 6
        assertTrue((naFarmi+3) == ovcaService.getSvaZivaGrla().size());     // broj na farmi uvecan za 3za tri
        
        nabavkaOvacaService.updateActivity(snimljena);  
       
        snimljena =  server.find(Aktivnost.class, snimljena.getId());
        
        List<NabavkaOvaca> fromBaseNabavkeO = snimljena.getNabavljenaGrla();
        List<NabavkaOvaca> nabavkeO = server.find(NabavkaOvaca.class).where().like("aktivnost_id", snimljena.getId().toString()).findList();
        
        assertTrue((ukupnoOvaca+5) == ovcaService.getAllSheep().size());        // da li je broj ovaca smanjen zbog bisanja ocajen
        assertTrue((naFarmi+3) == ovcaService.getSvaZivaGrla().size());
        assertEquals(nabavkeO.size(), 3);           // broj snimljenih merenja
        assertEquals(fromBaseNabavkeO.size(), 3);   // velicina liste snimljenih merenja
        
        // provera updejtovanih podataka

        assertEquals(Integer.valueOf(500), snimljena.getVremePocetka());
        assertEquals(Integer.valueOf(750), snimljena.getVremeZavrsetka());
        assertEquals(Float.valueOf(snimljena.getTroskovi()),Float.valueOf(130.0f));
        assertEquals("s.Jasenica", snimljena.getLokacija());
        assertEquals(Integer.valueOf(20150204), snimljena.getDan().getDatum());
        assertEquals("novaNapomena", snimljena.getNapomena());
        
 Ovca o = fromBaseNabavkeO.get(0).getSheep();
        assertEquals(o.getStatus(),"na farmi");
        assertEquals(o.getOznaka(),"OZ00000");
        assertEquals(o.getPol(),'ž');
        assertTrue(o.getProcenatR() == 99.9f);
        assertEquals(o.getOpis(),"crno");
        assertEquals(o.getDatumRodjenja(),"20140000");
        assertTrue(o.getLeglo()== 4);
        assertTrue(o.getOtac().getId() == 1);
        assertTrue(o.getMajka().getId() == 2);
        
        o = fromBaseNabavkeO.get(1).getSheep();
        assertEquals(o.getStatus(),"na farmi");
        assertEquals(o.getOznaka(),"OZ00322");
        assertEquals(o.getPol(),'ž');
        assertTrue(o.getProcenatR() == 87.5f);
        assertEquals(o.getOpis(),"skroz belo 13 kg");
        assertEquals(o.getDatumRodjenja(),"20141110");
        assertTrue(o.getLeglo()== 2);
        assertTrue(o.getOtac().getId() == (o.getId()-1));
        assertTrue(o.getMajka().getId() == 2);
        
        o = fromBaseNabavkeO.get(2).getSheep();
        assertEquals(o.getStatus(),"na farmi");
        assertEquals(o.getOznaka(),"OZ00323");
        assertEquals(o.getPol(),'ž');
        assertTrue(o.getProcenatR() == 100.0f);
        assertEquals(o.getOpis(),"crno belo 12 kg");
        assertEquals(o.getDatumRodjenja(),"20141015");
        assertTrue(o.getLeglo()== 3);
        assertTrue(o.getOtac().getId() == 1);       // promenjen otac
        assertTrue(o.getMajka().getId() == (o.getId()-1));
        
        assertEquals(fromBaseNabavkeO.get(0).getNapomena(),"promenjenaNapomena");
        assertTrue(fromBaseNabavkeO.get(0).getCena() == 99.9f);
        assertEquals(fromBaseNabavkeO.get(1).getNapomena(),"napomena nabavka2");
        assertTrue(fromBaseNabavkeO.get(1).getCena() == 100);
        assertEquals(fromBaseNabavkeO.get(2).getNapomena(),"napomena nabavka3");
        assertTrue(fromBaseNabavkeO.get(2).getCena() == 250);
        
        // da li je ocistio staru aktivnost
        assertNotNull(server.find(Aktivnost.class, stariId));        
        assertTrue(snimljena.getId() == stariId);

        //delete Test

        nabavkaOvacaService.deleteActivity(snimljena);
        
        assertNull(server.find(Aktivnost.class, snimljena.getId()));
        nabavkeO = server.find(NabavkaOvaca.class).where().like("aktivnost_id", snimljena.getId().toString()).findList();
        assertEquals(nabavkeO.size(), 0);
        
        assertTrue(ukupnoOvaca == ovcaService.getAllSheep().size());        // da li je broj ovaca ostao nepromenjen
        assertTrue(naFarmi == ovcaService.getSvaZivaGrla().size());
    }
  
    @Test
    public void updateActivityTestStatus(){
        Aktivnost novaAktivnost = noveNabavke();
        nabavkaOvacaService.createActivity(novaAktivnost);
        snimljena = server.find(Aktivnost.class, novaAktivnost.getId());
        
        Aktivnost uginuce = new Aktivnost();
        uginuce.setVrstaAktivnosti(new VrsteAktivnosti("Uginuce"));
        uginuce.setDan(new Dan(2014, 3, 24));
        Ovca uginulaO = snimljena.getNabavljenaGrla().get(0).getSheep();
        Uginuce u = new Uginuce();
        u.setO(uginulaO);
        uginuce.setUginuce(u);
        uginucaService.saveActivity(uginuce);
        
        assertEquals("uginulo", ovcaService.getOvca(uginulaO.getId()).getStatus());
        
        snimljena.getNabavljenaGrla().get(0).getSheep().setOpis("bla");
        snimljena.getNabavljenaGrla().get(0).setNapomena("blabla");
        nabavkaOvacaService.updateActivity(snimljena);
        
        Aktivnost no = server.find(Aktivnost.class, snimljena.getId());
        
        List<NabavkaOvaca> fromBaseNabavkeO =  no.getNabavljenaGrla();
        assertEquals("bla", fromBaseNabavkeO.get(0).getSheep().getOpis());
        assertEquals("uginulo", fromBaseNabavkeO.get(0).getSheep().getStatus());
        assertEquals("blabla", fromBaseNabavkeO.get(0).getNapomena());
        
        uginucaService.deleteActivity(uginuce);
        no = server.find(Aktivnost.class, snimljena.getId());
        nabavkaOvacaService.deleteActivity(no);
    }
   

    private Aktivnost noveNabavke(){
        Dan d = new Dan(2015, 3, 23);
        Aktivnost a = new Aktivnost();
        a.setDan(d);
        a.setVremePocetka(600);
        a.setVremeZavrsetka(800);
        a.setLokacija("jasenica");
        a.setTroskovi(1000.9f);
        a.setVrstaAktivnosti(new VrsteAktivnosti("Nabavka ovaca"));
        a.setNapomena("nabavka ovaca");
        

        List<NabavkaOvaca> nabavke = new ArrayList<NabavkaOvaca>();
        NabavkaOvaca  no = new NabavkaOvaca();
        Ovca o = new Ovca("na farmi", "OZ00321", 'm');
        o.setProcenatR(100.0f);
        o.setDatumRodjenja("20141010");
        o.setOtac(new Ovca("zamisljen", "nepoznat", 'm'));
        o.setMajka(new Ovca("zamisljena", "nepoznata", 'ž'));
        o.setOpis("skroz crno 15 kg");
        o.setLeglo(3);
        no.setSheep(o);
        no.setNapomena("napomena nabavka1");
        no.setCena(123);
        nabavke.add(no);
        
        no = new NabavkaOvaca();
        o = new Ovca("na farmi", "OZ00322", 'ž');
        o.setProcenatR(87.5f);
        o.setDatumRodjenja("20141110");
        o.setOtac(new Ovca("zamisljena", "3322332", 'm'));
        o.setMajka(new Ovca("zamisljena", "nepoznata", 'ž'));
        o.setOpis("skroz belo 13 kg");
        o.setLeglo(2);
        no.setSheep(o);
        no.setNapomena("napomena nabavka2");
        no.setCena(100);
        nabavke.add(no);
        
        no = new NabavkaOvaca();
        o = new Ovca("na farmi", "OZ00323", 'ž');
        o.setProcenatR(100.0f);
        o.setDatumRodjenja("20141015");
        o.setOtac(new Ovca("zamisljena", "12345", 'm'));
        o.setMajka(new Ovca("zamisljena", "54321", 'ž'));
        o.setOpis("crno belo 12 kg");
        o.setLeglo(3);
        no.setSheep(o);
        no.setNapomena("napomena nabavka3");
        no.setCena(250);
        nabavke.add(no);
        
        a.setNabavljenaGrla(nabavke);
        return a;
    }

}
