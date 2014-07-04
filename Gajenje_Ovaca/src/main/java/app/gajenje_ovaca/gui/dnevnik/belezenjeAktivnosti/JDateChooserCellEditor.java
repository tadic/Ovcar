/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.gajenje_ovaca.gui.dnevnik.belezenjeAktivnosti;

import com.toedter.calendar.JDateChooser;
import java.awt.Component;
import java.util.Calendar;
import java.util.Date;
import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

 public class JDateChooserCellEditor extends AbstractCellEditor implements TableCellEditor {


    private JDateChooser dateChooser;

    public JDateChooserCellEditor(){
        dateChooser  = new JDateChooser();
        dateChooser.setDateFormatString("dd.MM.yyyy");
    }
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {

        Date date = null;
        if (value instanceof Date) {
            date = (Date) value;
        } else if (value instanceof String) {
            date = toDate((String) value);
        }
        
        dateChooser.setDate(date);

        return dateChooser;
    }
    private Date toDate(String s){
        if (s==null || s.equals("")){return null;}
        int d = Integer.parseInt(s.substring(0, 2));
        int m = Integer.parseInt(s.substring(3, 5));
        int y = Integer.parseInt(s.substring(6, 10));
        Calendar c = Calendar.getInstance();
        c.set(y, m, d);
        return c.getTime();
    }
    public Object getCellEditorValue() {
        dateChooser.setDateFormatString("dd.MM.yyyy");
        return dateChooser.getDate();
    }
 }