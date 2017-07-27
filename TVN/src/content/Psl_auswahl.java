package content;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ProgressMonitor;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.border.BevelBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jdesktop.swingx.JXTable;

import allgemein.JDBCAdapter;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class Psl_auswahl 
extends JDialog implements PropertyChangeListener

{
	private JPanel panel_header;
	private JTextField tf_filter;
	private JLabel lbl_filter;
	private JXTable tbl_projekt;
	private JScrollPane sp_projekt;
	private JLabel lbl_ueber;
	private JButton bt_abbrechen;
	private JButton bt_ok;
	private JPanel panel_footer;
	private JPanel panel_content;
	
	JDBCAdapter verbindung_aedb = new JDBCAdapter("jdbc:jtds:sqlserver://172.16.0.158:1433/aedb","net.sourceforge.jtds.jdbc.Driver","r_aedb","r_aedb"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	private JLabel lbl_fortschritt;
	private JProgressBar bar;
	private Task task;
	public String aufruf;
	private Vector header;

	TableModel tbl_projektModel = 
			new DefaultTableModel(
					new String[][] { { "One", "Two" }, { "Three", "Four" } },
					new String[] { "Column 1", "Column 2" });
	TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tbl_projektModel);
	
	public Psl_auswahl(Frame frame, boolean modal,String aufruf) {
		//super(frame,modal);
		this.aufruf = aufruf;
		header = new Vector();
		header.add("PA");
		this.setSize(500, 500);
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				
				
				setLocationRelativeTo(null);
				setTitle("Auswahl Projekt");
				{
					panel_header = new JPanel();
					FlowLayout panel_headerLayout = new FlowLayout();
					panel_headerLayout.setAlignment(FlowLayout.LEFT);
					panel_header.setLayout(panel_headerLayout);
					getContentPane().add(panel_header, BorderLayout.NORTH);
					panel_header.setBackground(new java.awt.Color(255,255,255));
					panel_header.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
					{
						lbl_ueber = new JLabel();
						panel_header.add(lbl_ueber);
						lbl_ueber.setText("Projektstückliste");
						lbl_ueber.setFont(new java.awt.Font("Tahoma",0,16));
					}
				}
				{
					panel_content = new JPanel();
					GridBagLayout panel_contentLayout = new GridBagLayout();
					getContentPane().add(panel_content, BorderLayout.CENTER);
					panel_content.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
					panel_contentLayout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
					panel_contentLayout.rowHeights = new int[] {7, 7, 7, 7, 7, 7, 7, 7};
					panel_contentLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
					panel_contentLayout.columnWidths = new int[] {7, 7, 7, 7, 7, 7, 7, 7};
					panel_content.setLayout(panel_contentLayout);
					{
						sp_projekt = new JScrollPane();
						panel_content.add(sp_projekt, new GridBagConstraints(1, 2, 4, 4, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						sp_projekt.setBorder(BorderFactory.createTitledBorder("Auswählbare Preisanfragen"));
						sp_projekt.setPreferredSize(new java.awt.Dimension(180, 250));
						{
							tbl_projekt = new JXTable();
							tbl_projekt.setEditable(false);
							sp_projekt.setViewportView(tbl_projekt);
							tbl_projekt.addMouseListener(new MouseAdapter() {
								public void mouseClicked(MouseEvent evt) {
									tbl_projektMouseClicked(evt);
								}
							});
							tbl_projekt.setModel(tbl_projektModel);
						}
					}
					{
						bar = new JProgressBar(0,100);
						panel_content.add(bar, new GridBagConstraints(2, 5, 4, 3, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						bar.setVisible(false);
						bar.setStringPainted(true);
						
					}
					{
						lbl_fortschritt = new JLabel();
						panel_content.add(lbl_fortschritt, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						lbl_fortschritt.setText("Fortschritt");
						lbl_fortschritt.setVisible(false);
					}
					{
						lbl_filter = new JLabel();
						panel_content.add(lbl_filter, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						lbl_filter.setText("Filter");
						lbl_filter.setIcon(new ImageIcon(getClass().getClassLoader().getResource("bilder/filter.gif")));
					}
					{
						tf_filter = new JTextField();
						panel_content.add(tf_filter, new GridBagConstraints(2, 1, 6, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						tf_filter.setPreferredSize(new java.awt.Dimension(120, 20));
						tf_filter.addKeyListener(new KeyAdapter() {
							public void keyReleased(KeyEvent evt) {
								tf_filterKeyReleased(evt);
							}
						});
					}

				}
				{
					panel_footer = new JPanel();
					FlowLayout panel_footerLayout = new FlowLayout();
					panel_footerLayout.setAlignment(FlowLayout.RIGHT);
					panel_footer.setLayout(panel_footerLayout);
					getContentPane().add(panel_footer, BorderLayout.SOUTH);
					panel_footer.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
					{
						bt_ok = new JButton();
						panel_footer.add(bt_ok);
						bt_ok.setText("OK");
						bt_ok.setIcon(new ImageIcon(getClass().getClassLoader().getResource("bilder/check.gif")));
						bt_ok.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								bt_okActionPerformed(evt);
							}
						});
					}
					{
						bt_abbrechen = new JButton();
						panel_footer.add(bt_abbrechen);
						bt_abbrechen.setText("Abbrechen");
						bt_abbrechen.setIcon(new ImageIcon(getClass().getClassLoader().getResource("bilder/button_cancel.png")));
						bt_abbrechen.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								bt_abbrechenActionPerformed(evt);
							}
						});
					}
				}
				projekt_auslesen();
				setVisible(true);
			}
		});
		
		
	}

	private void projekt_auslesen(){
		String sql_string = "SELECT SUBSTRING(panr, 0, PATINDEX('%-%', panr)) AS panr FROM TVN_Menge WHERE (panr LIKE '%') "
				+ "GROUP BY SUBSTRING(panr, 0, PATINDEX('%-%', panr)) ORDER BY SUBSTRING(panr, 0, PATINDEX('%-%', panr))";
		ResultSet rs = verbindung_aedb.rs_zurueck(sql_string);
		
		Vector v_inhalt = new Vector();
		try {
			while(rs.next()){
				Vector row = new Vector();
				row.addElement(rs.getString("panr").toString().trim());
				v_inhalt.add(row);
				//((DefaultListModel) list_projektModel).addElement(rs.getString("panr").toString().trim());
			}
			DefaultTableModel tmodel = new DefaultTableModel(v_inhalt,header);
			tbl_projekt.setModel(tmodel);
			
			sorter = new TableRowSorter<TableModel>(tmodel);
			tbl_projekt.setRowSorter(sorter);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void schliessen(){
		try {
			verbindung_aedb.finalize();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.dispose();
	}
	
	private void bt_abbrechenActionPerformed(ActionEvent evt) {
		task.cancel(true);
		schliessen();
	}
	

	public void propertyChange(PropertyChangeEvent evt) {
		
		if ("progress" == evt.getPropertyName()) {
			int progress = Integer.parseInt(evt.getNewValue().toString());
			bar.setValue(progress);
		}


	}
	
	
	private void bt_okActionPerformed(ActionEvent evt) {
		//Auslesen der gewählten Projekte
		int[] zeile = tbl_projekt.getSelectedRows();
		Vector projekte = new Vector();
		//Werte setzen
		for(int i=0;i<zeile.length;i++){
			projekte.add(tbl_projekt.getValueAt(zeile[i],0));
		}

		bar.setVisible(true);
		lbl_fortschritt.setVisible(true);
		task = new Task();
		task.aufruf_setzen(aufruf);
		task.var_fuellen(projekte);
		task.addPropertyChangeListener(this);
		task.execute();

	}
	
	private void tbl_projektMouseClicked(MouseEvent evt) {
		if (evt.getClickCount() == 2) {
			Point p = evt.getPoint();
			String pa = tbl_projekt.getValueAt(tbl_projekt.rowAtPoint(p), 0).toString();
			Vector projekte = new Vector();
			projekte.addElement(pa);
			bar.setVisible(true);
			lbl_fortschritt.setVisible(true);
			task = new Task();
			task.aufruf_setzen(aufruf);
			task.var_fuellen(projekte);
			task.addPropertyChangeListener(this);
			task.execute();
		}
	}
	
	private void tf_filterKeyReleased(KeyEvent evt) {
		String text = tf_filter.getText();
        if (text.length() == 0) {
          sorter.setRowFilter(null);
        } 
        else {
          sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
        }
	}

}
class excel_dialog extends JDialog{
	public void dialog_starten(Vector v){

		//Welche Projekt
		JFileChooser fc = new JFileChooser(".");
		fc.setFileFilter(new FileFilter() {
		    public boolean accept(File f) {
		        return f.getName().toLowerCase().endsWith(".xls") || f.isDirectory();
		    }

			@Override
			public String getDescription() {
				// TODO Auto-generated method stub
				return  "Excel-Dateien(*.xls)";
			}
		});

		int result = fc.showSaveDialog(this);
		if(result == 0){
			File f;
			HSSFWorkbook wb = new HSSFWorkbook();
			try {
				String path = fc.getSelectedFile().getPath();
				if (!path.toLowerCase().endsWith(".xls")){
					  path = path + ".xls";
				}
				f = new File(path);
				FileOutputStream fileOut = new FileOutputStream(f);
				HSSFSheet blatt = wb.createSheet("PSL");

				HSSFCellStyle style = wb.createCellStyle();
				HSSFFont font = wb.createFont();
				font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//Fettdruck für erste Zeile
				style.setFont(font);
				
				HSSFRow row = blatt.createRow((short) 0);
				HSSFCell cell;
				
				int colNum=0;
				//Anzahl Spalten auslesen
				for (int i=0;i<1;i++){
					Vector reihe = (Vector) v.elementAt(i);
					for(int j=0;j <reihe.size(); j++){
						colNum++;
					}
					
				}
				
				short[] maxWidth = new short[colNum];//max. Spaltenbreite
				//Anzeigen der Daten
				for (int i=0;i<v.size();i++){
					Vector reihe = new Vector();
					reihe = (Vector) v.elementAt(i);
					row = blatt.createRow((short) i+1);//+1 wegen 0, Zeile = NAmen
					//Einzelne Zeile als Vector
					//System.out.println(i);
					for(int j=0;j <reihe.size(); j++){
						
						cell = row.createCell((short) j );
						//System.out.println(reihe.elementAt(j).toString());
						if(i == 0){
							cell.setCellStyle(style);	
							cell.setCellType(HSSFCell.CELL_TYPE_STRING);
							String value =  reihe.elementAt(j).toString();
							if(value.length() > maxWidth[j]){
								maxWidth[j] = (short) value.length();
							}
							cell.setCellValue(value);
						}
						else if(i >= 1 && j > 3 ){
							cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
							Double value = Double.parseDouble(reihe.elementAt(j).toString());
							
							if(value.toString().length() > maxWidth[j]){
								maxWidth[j] = (short) value.toString().length();
							}
							cell.setCellValue(value);
						}
						else{
							cell.setCellType(HSSFCell.CELL_TYPE_STRING);
							String value =  reihe.elementAt(j).toString();
							if(value.length() > maxWidth[j]){
								maxWidth[j] = (short) value.length();
							}
							cell.setCellValue(value);
						}
					}
				}
				
				for(short i = 0;i < colNum;i++){
					blatt.setColumnWidth(i,(short) (maxWidth[i]*256));	
				}
				for(int i=4;i<colNum-2;i++){
    				blatt.autoSizeColumn(i);	
				}
				
				wb.write(fileOut);
			    fileOut.close();
			    Runtime.getRuntime().exec("rundll32 SHELL32.DLL,ShellExec_RunDLL " +f);
				
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(this, e,"Error",JOptionPane.ERROR_MESSAGE);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(this, e,"Error",JOptionPane.ERROR_MESSAGE);
			}

		}
	}
}




class Task extends SwingWorker<Void, Void> {
    /*
     * Main task. Executed in background thread.
     */
	private Vector projekte;
	public String aufruf;
	private String projekt_wahl = " panr LIKE ";
	JDBCAdapter verbindung_aedb = new JDBCAdapter("jdbc:jtds:sqlserver://172.16.0.158:1433/aedb","net.sourceforge.jtds.jdbc.Driver","r_aedb","r_aedb"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	public void var_fuellen(Vector projekte){
		this.projekte = projekte;
		for(int i=0;i<projekte.size();i++){
			if(i == 0){
				projekt_wahl = projekt_wahl + "'"+projekte.get(i)+"%'";
			}
			else{
				projekt_wahl = projekt_wahl + " OR panr LIKE '"+projekte.get(i)+"%'";
			}
			
		}
		//runde Klammer setzen
		projekt_wahl = "("+projekt_wahl+")";
	}
	public void aufruf_setzen(String aufruf){
		this.aufruf = aufruf;
	}
    @Override
    public Void doInBackground() {
    	final Vector v = new Vector();
    	int progress = 0;
    	double zeilen = 0.0;
    	double schrittweite = 0.0;
    	double prozent_dec;
		final HashMap hm = new HashMap();
		HashMap hm_projekt = new HashMap(); //Projekt und Menge abspeichern
		Vector v_projekt = new Vector();
		
		Double summen_zaehler;
		
		String sql_string = "SELECT TVN_Material.matnr as matnr, zkmadat.maktx AS maktx FROM TVN_Material " +
						"LEFT OUTER JOIN zkmadat ON TVN_Material.matnr = zkmadat.matnr WHERE "+projekt_wahl+ 
						" GROUP BY TVN_Material.matnr, zkmadat.maktx " +
						"ORDER BY TVN_Material.matnr";
		//System.out.println(sql_string);
		ResultSet rs = verbindung_aedb.rs_zurueck(sql_string);
		try {		
			zeilen = 0.0;
			rs.last();
			zeilen = rs.getRow();
			rs.beforeFirst();
			
	        //Initialize progress property.
	        setProgress(0);
			prozent_dec =  (100/zeilen);
			schrittweite = 0.0;
			
			
			while(rs.next()){
				//Änderung 04.07.2017 Ruisz: Stoffe ohne Teilenummerangabe (nicht in zkmadat
				if(rs.getString("maktx")==null){
					hm.put(rs.getString("matnr").trim(),"");
				}
				else{
					hm.put(rs.getString("matnr").trim(), rs.getString("maktx").trim());
				}
				
				try {
	                Thread.sleep(2);
	            } catch (InterruptedException ignore) {}
	            schrittweite = schrittweite + prozent_dec;
	            //Runden
	            progress = (int) Math.round(schrittweite);
				//progress = progress + Math.round(prozent_);
				setProgress(Math.min(progress, 100));
			}
		} 
		catch (SQLException e1) {
			}
		Vector ueberschrift = new Vector();
		Vector ueberschrift_menge = new Vector();
		Vector relevante_projekte = new Vector();
		ueberschrift.addElement("Stoffe");
		ueberschrift.addElement("Bezeichnung");
		ueberschrift.addElement("Bestellnummer");
		ueberschrift.addElement("Lieferant");
		
		//Einzelne Projekte auslesen
		//Änderung 16.09.2009: nur die neuesten Projekte, z.B. B3112-01, B3112-01/C, B3112-01/D <- richtige
		//String sql_string = "SELECT panr, mengepa FROM TVN_Menge WHERE panr LIKE '"+projekt+"%' ORDER BY panr";
		sql_string = "SELECT SUBSTRING(panr, 1,  CHARINDEX('-', panr) + 3) AS panr FROM TVN_Menge WHERE "+projekt_wahl+
		" GROUP BY LEN(SUBSTRING(panr, 1,  CHARINDEX('-', panr) + 3)), SUBSTRING(panr, 1,  CHARINDEX('-', panr) + 3) "
		+ "ORDER BY LEN(SUBSTRING(panr, 1,  CHARINDEX('-', panr) + 3))";
		rs = verbindung_aedb.rs_zurueck(sql_string);
		Double summe = 0.0;
		//System.out.println(sql_string);
		try {

			while(rs.next()){
				relevante_projekte.add(rs.getString("panr").trim());
				//ueberschrift.addElement(rs.getString("panr").trim());
				//System.out.println(rs.getString("panr").trim());
			}
			ueberschrift_menge.addElement("");
			ueberschrift_menge.addElement("");
			ueberschrift_menge.addElement("");
			ueberschrift_menge.addElement("");
			//jetzt sind sie in Form B4000-01
			String r = "Ratio";
			for(int i = 0;i<relevante_projekte.size();i++){
				sql_string = "SELECT panr,mengepa FROM TVN_Menge WHERE (panr LIKE '"+relevante_projekte.get(i)+"%') " +
						"GROUP BY panr,mengepa ORDER BY  LEN(SUBSTRING(panr, 1,  CHARINDEX('-', panr) + 3))";
				rs = verbindung_aedb.rs_zurueck(sql_string);
				//Zuerst die Überschriften bzw. Projekte
				rs.first();		
					Vector reihe = new Vector();
					//Änderung 8.2.2010: Kommt hinter _ "Ratio" vor ignorieren sonst max
					//z.B. B4000-03_Ratio1
					String panr;
					String menge;
					if(rs.getString("panr").trim().indexOf(r) == -1){
						//System.out.println(" Nicht Gefunden");
						panr = rs.getString("panr").trim();
						menge = rs.getString("mengepa").trim();
					}
					else{
						//System.out.println("gefunden");
						panr = rs.getString("panr").trim().substring(0,8);
						menge = rs.getString("mengepa").trim();
					}
					ueberschrift.addElement(panr);
					reihe.addElement(panr);
					reihe.addElement(menge);

					ueberschrift_menge.addElement(rs.getString("mengepa").trim());
					summe = summe + rs.getInt("mengepa");//
					v_projekt.add(reihe);
			}
			
			ueberschrift.addElement("Menge");
			if(aufruf.equals("ake")){
				ueberschrift.addElement("Preis/PE");
				ueberschrift.addElement("Wert/1000");	
			}
			else{
				ueberschrift.addElement("");
				ueberschrift.addElement("");
			}
			
			ueberschrift_menge.addElement(summe);
			
			v.add(ueberschrift);
			v.add(ueberschrift_menge);

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//Änderung 23.02.2015 -> Nur die Jahresmengen als Überschriften ausgeben
		if(aufruf.equals("mengenuebersicht")){
			
		}
		else{
			Double preis = 0.0;
			zeilen = 0.0;
			Iterator it = hm.keySet().iterator();
			while(it.hasNext()){
				it.next();
				zeilen++;
			}
			prozent_dec = 100/zeilen;
			setProgress(0);
			schrittweite = 0.0;
			
			it = hm.keySet().iterator();
			//Stoffenummern durchlaufen
			while(it.hasNext()){
				try {
	                Thread.sleep(2);
	            } catch (InterruptedException ignore) {}
	            schrittweite = schrittweite + prozent_dec;
	            //Runden
	            progress = (int) Math.round(schrittweite);
	            setProgress(Math.min(progress, 100));
	            
				summe = 0.0;
				String stoffenummer = it.next().toString();
				Vector reihe = new Vector();
				reihe.addElement(stoffenummer);
				reihe.addElement(hm.get(stoffenummer));
				
				//Bestellnummer/ Lieferant suchen
				sql_string = "SELECT bestprice_2.kbetr AS kbetr,klk_ebel.idnlf AS idnlf, view_lieferant.sortl as sortl FROM Bestprice_2 INNER JOIN " +
						" view_lieferant ON Bestprice_2.lifnr = view_lieferant.lifnr INNER JOIN klk_ebel ON Bestprice_2.lifnr = " +
						"klk_ebel.lifnr AND Bestprice_2.matnr = klk_ebel.matnr " +
						"WHERE (Bestprice_2.matnr = '"+stoffenummer+"') " +
						"AND (GETDATE() >= CAST(Bestprice_2.datab AS datetime)) AND (GETDATE() <= CAST(Bestprice_2.datbi " +
						"AS datetime)) AND (GETDATE() >= CAST(Bestprice_2.vdatu AS datetime)) AND (GETDATE() <= CAST(Bestprice_2.bdatu " +
						"AS datetime)) GROUP BY bestprice_2.kbetr, klk_ebel.idnlf,view_lieferant.sortl";
				//System.out.println(sql_string);
				rs = verbindung_aedb.rs_zurueck(sql_string);
				//nicht vorhanden -> leer lassen
				try {
					rs.first();
					reihe.addElement(rs.getString("idnlf").trim());
					preis = rs.getDouble("kbetr");
					reihe.addElement(rs.getString("sortl"));
				} catch (SQLException e) {
					//Kein Lieferant vorhanden -> Leer lassen
					if(e.getMessage().equals("No current row in the ResultSet.")){
						reihe.addElement("");
						preis = 0.0;
						reihe.addElement("");
					}
				}
					//Grober Aufbau beendet, jetzt kommen die Mengen in den Feldern
					sql_string = "SELECT panr, matnr, ROUND(menge,4) AS menge FROM TVN_Material WHERE "+projekt_wahl+
							"AND (matnr = '"+stoffenummer+"') ORDER BY panr";
					//System.out.println(sql_string);
					rs = verbindung_aedb.rs_zurueck(sql_string);
					HashMap hm_test = new HashMap();
					try {
						while(rs.next()){
							hm_test.put(rs.getString("panr").trim(),rs.getDouble("menge"));
							
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					 //den Vector nehmen wegen der Reihenfolge
					for(int i = 0; i< v_projekt.size();i++){
						Vector zeile = new Vector();
						zeile = (Vector) v_projekt.elementAt(i);
						String key = zeile.elementAt(0).toString();
						if(hm_test.containsKey(key)){
							reihe.addElement(hm_test.get(key));
							Double menge = Double.parseDouble(hm_test.get(key).toString());
							summe = summe + (menge * Double.parseDouble(zeile.elementAt(1).toString()));
						}
						else{
							reihe.addElement(0);
						}
					}
					reihe.addElement(summe);
					if(aufruf.equals("ake")){
						reihe.addElement(preis);
						Double summenpreis = summe*preis/1000;
						reihe.addElement(summenpreis);
					}
					else{
						reihe.addElement(0);
						reihe.addElement(0);
					}
					
					
					
					
				
				v.add(reihe);
			}
		}
		
		


		excel_dialog dia = new excel_dialog();
		dia.dialog_starten(v);
        return null;
    }

    /*
     * Executed in event dispatching thread
     */
    @Override
    public void done() {
        Toolkit.getDefaultToolkit().beep();

        
        
        //startButton.setEnabled(true);
        //setCursor(null); //turn off the wait cursor
        //taskOutput.append("Done!\n");
    }
 
}



