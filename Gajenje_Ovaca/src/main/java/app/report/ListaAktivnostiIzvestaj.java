/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.report;

import app.logic.Logic;
import app.model.Aktivnost;
import app.model.Dan;
import app.model.Ovca;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
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
public class ListaAktivnostiIzvestaj {
    private List<Aktivnost> list;
    private String f2, f3,f4;
    private Map<String, Object> params = new HashMap<String, Object>();
    public ListaAktivnostiIzvestaj(List<Aktivnost> list, String f2, String f3, String f4){
        this.list = list;
        this.f2 = f2;
        this.f3 = f3;
        this.f4 = f4;
    }
    public void create(){
        osnovniPodatci();
        
        try
        {
            String reportSource = "listAktivnosti.jrxml";
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
            
            
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
 
        params.put("count", "(" + (list.size()) + ")"); 
        params.put("filter2", f2); 
        params.put("filter3", f3); 
        params.put("filter4", f4); 
 

        params.put("startDate", (new java.util.Date()).toString());
    }

        
}
