/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.Reports;

import app.logic.Logic;
import app.model.Aktivnost;
import app.model.Dan;
import app.model.Ovca;
import java.io.InputStream;
import java.util.HashMap;
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
    private Ovca ovca;
    private Logic logic;
    private Map<String, Object> params = new HashMap<String, Object>();
    public OvcaIzvestaj(Logic logic, Ovca o){
        this.ovca = o;
        this.logic = logic;
    }
    public void create(){
        osnovniPodatci();
        rodjenje();
        
        
        
        

        try
        {
            String reportSource = "listOvce.jrxml";
            String jagnjenjeSource = "jagnjenjeReport.jrxml";
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(logic.getAllSheep());
            
            
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
        params.put("procenatR", String.valueOf(Aktivnost.round(ovca.getProcenatR(),1))); 
        
        params.put("filter1", ovca.getStatus()); 
        params.put("filter2", ovca.getStatus()); 
        params.put("filter3", ovca.getStatus()); 
        params.put("filter4", ovca.getStatus()); 
        params.put("nadimak", ovca.getNadimak()); 
        params.put("oznaka", ovca.getOznaka()); 
        params.put("linija", ovca.getLinija().getImeLinije()); 
        if (ovca.getPol()=='m'){
               params.put("pol", "muško"); 
        } else {
               params.put("pol", "žensko"); 
        }
        params.put("starost", ovca.getStarost());  
        params.put("startDate", (new java.util.Date()).toString());
    }
    private void rodjenje(){
        params.put("otac", ovca.getOtac().toString());
        params.put("majka", ovca.getMajka().toString());
        params.put("datumRodjenja", ovca.getDatumRodjenja());
        params.put("opis", ovca.getOpis()); 
        
        if (ovca.getRodjenje()==null){
            params.put("mestoRodjenja", "");    
            params.put("r_napomena", "-");
            if (ovca.getNabavka()!=null){
                Dan d = logic.getDan(ovca.getNabavka().getAktivnost().getDan().getId());
                    params.put("nabavka", "Nabavljeno "+d.toString() + ", "
                            + ovca.getNabavka().getAktivnost().getLokacija() + " - " 
                            + Aktivnost.round(ovca.getNabavka().getCena(), 0)+"evra");
            }
           
        } else {
            params.put("mestoRodjenja", "na farmi");
            params.put("r_napomena", ovca.getRodjenje().getNapomena());
        }
    }
        
}
