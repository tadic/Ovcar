/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.report;

import app.helpers.OvcaReport;
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
public class OvcaIzvestaj {
    private List<OvcaReport> list;
    private Logic logic;
    private Map<String, Object> params = new HashMap<String, Object>();
    public OvcaIzvestaj(Logic logic, List<OvcaReport> list){
        this.list = list;
        this.logic = logic;
    }
    public void create(){
        try
        {
            String ovcaSource = "ovcaIzvestaj.jrxml";
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
            
            
            JasperDesign dis=JRXmlLoader.load(ovcaSource); 
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
}
