package component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Mister_Brown on 10/22/2016.
 */
public class MTable extends JScrollPane{
	public JTable table;
	public DefaultTableModel model;
	
	
	public void myConfiguration(){

        table.setFont(new Font("Consolas", Font.PLAIN, 20));
        table.setRowHeight(25);
        table.getTableHeader().setFont(new Font("Consolas", Font.BOLD, 22));
        table.getTableHeader().setForeground(Color.BLUE);

        
		
	}
	public MTable(String header[]){
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		getViewport().add(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		model = new DefaultTableModel();
        table.setModel(model);
        myConfiguration();
        for(int i=0;i<header.length;i++){
        	   model.addColumn(header[i]);   
        }   
	}
	
	public void addRow(String row[]){
		
		model.addRow(row);
		
	}
	public void removeAll(){
		while (model.getRowCount() > 0) {
 	       model.removeRow(0);
 	}
	}
	
	public void addRow(ArrayList<String[]> row){
		for(int i=0;i<row.size();i++){
			addRow(row.get(i));
			model.setValueAt(" "+(i+1)+" ", i,0);
			}
	}
	
	public void setAutoResizeWidth(){
		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		 int indent=2;

        for (int column = 0; column < table.getColumnCount(); column++)
        {
            TableColumn tableColumn = table.getColumnModel().getColumn(column);
            int preferredWidth = tableColumn.getMinWidth();
            int maxWidth = tableColumn.getMaxWidth();

           
            for (int row = 0; row < table.getRowCount(); row++)
            {
                TableCellRenderer cellRenderer = table.getCellRenderer(row, column);
                Component c = table.prepareRenderer(cellRenderer, row, column);
                int width = c.getPreferredSize().width + table.getIntercellSpacing().width;
                
				preferredWidth = Math.max(preferredWidth+indent, width+indent);

                //  We've exceeded the maximum width, no need to check other rows

                
            }

            tableColumn.setPreferredWidth( preferredWidth );
        }
	}
	
	
	
	
	
	
	
}
