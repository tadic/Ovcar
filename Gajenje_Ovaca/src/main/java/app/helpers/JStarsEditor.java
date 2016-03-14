/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.helpers;

import java.awt.Component;
import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author ivantadic
 */
public class JStarsEditor extends AbstractCellEditor implements TableCellEditor{
    private StarsPanel starsPanel;
    
    public JStarsEditor(){
        starsPanel = new StarsPanel();
    }
    public Object getCellEditorValue() {
        return starsPanel.getLevel();
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        return starsPanel;
    }
    
}
