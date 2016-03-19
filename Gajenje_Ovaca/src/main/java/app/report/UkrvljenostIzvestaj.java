/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.report;

import app.logic.Logic;
import app.model.Aktivnost;
import app.model.Dan;
import app.model.Jagnjenje;
import app.model.NabavkaOvaca;
import app.model.Ovca;
import app.model.Prodaja;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author ivantadic
 */
public class UkrvljenostIzvestaj {
    private Ovca ovan1, ovan2, ovan3, ovan4, ovan5;
    private List<Ukrvljenost> lista = new ArrayList<Ukrvljenost>();
    private Map<String, Object> params = new HashMap<String, Object>();
    
    public UkrvljenostIzvestaj(List<Ukrvljenost> lista, Ovca ovan1, 
            Ovca ovan2, Ovca ovan3, Ovca ovan4, Ovca ovan5){
        this.lista = lista;
        this.ovan1 = ovan1;
        this.ovan2 = ovan2;
        this.ovan3 = ovan3;
        this.ovan4 = ovan4;
        this.ovan5 = ovan5;
    }

    public void create(){
        osnovniPodatci();
     
        try
        {
            String reportSource = "ukrvljenostIzvestaj.jrxml";
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lista);
            
            JasperDesign dis=JRXmlLoader.load(reportSource); 
            JasperReport jasperReport = JasperCompileManager.compileReport(dis);

            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    jasperReport, params, dataSource);
            JasperViewer.viewReport(jasperPrint);
        }

        catch (JRException ex)
        {
            ex.printStackTrace();
        }
    }

    private void osnovniPodatci(){
        if (ovan1!=null){
            params.put("ovan1", ovan1.getNadimak()); 
        }
        if (ovan2!=null){
            params.put("ovan2", ovan2.getNadimak()); 
        }
        if (ovan3!=null){
            params.put("ovan3", ovan3.getNadimak()); 
        }
        if (ovan4!=null){
            params.put("ovan4", ovan4.getNadimak()); 
        }
        if (ovan5!=null){
            params.put("ovan5", ovan5.getNadimak()); 
        }
    }

        
}
