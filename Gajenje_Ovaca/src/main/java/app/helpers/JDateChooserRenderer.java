/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.helpers;

import com.toedter.calendar.JDateChooser;
import java.awt.Component;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class JDateChooserRenderer extends JDateChooser implements TableCellRenderer{

    Date inDate;

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        // TODO Auto-generated method stub

        if (value instanceof Date){
            this.setDate((Date) value);
        } else if (value instanceof String){
            this.setDate(toDate((String) value));
        }
        this.setDateFormatString("dd.MM.yyyy");
        return this;
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
}