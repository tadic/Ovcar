/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.helpers;

import app.model.Ovca;
import javax.swing.DefaultComboBoxModel;

public class MyComboBoxModel extends DefaultComboBoxModel<Ovca> {
    public MyComboBoxModel(Ovca[] items) {
       
        super.removeAllElements();
        for (Ovca o:items){
            super.addElement((Ovca)o);
        }
    }
 
    @Override
    public Ovca getSelectedItem() {
        Ovca selectedJob = (Ovca) super.getElementAt(super.getIndexOf(super.getSelectedItem()));
        return selectedJob;
    }
   

}
