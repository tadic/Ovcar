/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.gajenje_ovaca.gui.dnevnik.belezenjeAktivnosti;

import com.toedter.calendar.JDateChooser;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

 public class JButtonCellEditor extends AbstractCellEditor implements TableCellEditor {


    private JButton buttonCell;

    public JButtonCellEditor(){
        buttonCell  = new JButton();

    }
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {

        String oznaka = null;
        if (value instanceof String) {
            oznaka = value.toString();
        }
        buttonCell.setText(oznaka);
        return buttonCell;
    }

    public Object getCellEditorValue() {
        return buttonCell.getText();
    }
    
 }