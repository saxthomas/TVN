/*******************************************************************************
 * Copyright (c) 2001, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package content;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import org.apache.poi.ss.usermodel.Font;
import org.jdesktop.swingx.JXTable;




import org.jdesktop.swingx.decorator.ColorHighlighter;
import org.jdesktop.swingx.decorator.HighlightPredicate;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import de.javasoft.plaf.synthetica.SyntheticaClassyLookAndFeel;

import java.io.File;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

import allgemein.BlobExtraction;
import allgemein.JDBCAdapter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
public class TVN
extends JFrame
{
	private JPanel panel_content;
	private JPanel panel_header;
	private JMenuItem item_psl;
	private JMenuItem item_drucken;
	private JMenuItem item_pflege_mengen;
	private JMenuItem item_psl_ake;
	private JLabel lbl_sax;
	private JLabel lbl_version;
	private JXTable tbl_content;
	private JButton bt_filter;
	private JTextField tf_suche;
	private JComboBox cb_spalte;
	private JMenu menu_datei;
	private JLabel lbl_spalte;
	private JLabel lbl_suchbegriff;
	private JMenuItem item_beenden;
	private JMenu menu_werkzeug;
	private JMenuBar menubar;
	private JMenu menu;

	private JPanel panel_footer;
	private JMenuItem item_mengen;

	private JScrollPane sp_content;
	TableModel tbl_contentModel = 
		new DefaultTableModel(
				new String[][] { { "", "" } },
				new String[] { "PA-Nr", "Stoffenummer","Menge","Materialnummer" });

    
   
    JDBCAdapter verbindung = new JDBCAdapter("jdbc:jtds:sqlserver://172.16.0.158:1433/aedb","net.sourceforge.jtds.jdbc.Driver","tvn","tvn"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
    //JDBCAdapter verbindung_2 = new JDBCAdapter("jdbc:jtds:sqlserver://172.16.0.158:1433/ae_bde_sap","net.sourceforge.jtds.jdbc.Driver","tvn","tvn"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
    //Mehrsprachigkeit:
    //Hier setzt man global Messages_en oder Messages
    
    public String var_version="2.1.0"; //$NON-NLS-1$
    
	public TVN() {
		
		this.setSize(700, 500);
		this.setLocation(50, 50);
		
		
		this.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("bilder/log_n.png")).getImage()); //$NON-NLS-1$
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		
        
       

		{
			panel_header = new JPanel();
			FlowLayout panel_headerLayout = new FlowLayout();
			panel_headerLayout.setAlignment(FlowLayout.LEFT);
			panel_header.setLayout(panel_headerLayout);
			getContentPane().add(panel_header, BorderLayout.NORTH);
			panel_header.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
			panel_header.setBackground(new java.awt.Color(255,255,255));
			{
				lbl_spalte = new JLabel();
				panel_header.add(lbl_spalte);
				lbl_spalte.setText("Spalte");
			}
			{
				ComboBoxModel cb_spalteModel = 
					new DefaultComboBoxModel(
							new String[] { "PA-Nr", "Stoffenummer","Materialnummer" });
				cb_spalte = new JComboBox();
				panel_header.add(cb_spalte);
				cb_spalte.setModel(cb_spalteModel);
			}
			{
				lbl_suchbegriff = new JLabel();
				panel_header.add(lbl_suchbegriff);
				lbl_suchbegriff.setText("Suchbegriff");
			}
			{
				tf_suche = new JTextField();
				panel_header.add(tf_suche);
				tf_suche.setPreferredSize(new java.awt.Dimension(120, 20));
				tf_suche.addKeyListener(new KeyAdapter() {
					public void keyPressed(KeyEvent evt) {
						tf_sucheKeyPressed(evt);
					}
				});
			}
			{
				bt_filter = new JButton();
				panel_header.add(bt_filter);
				bt_filter.setText("Filter");
				bt_filter.setIcon(new ImageIcon(getClass().getClassLoader().getResource("bilder/filter.gif")));
				bt_filter.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						bt_filterActionPerformed(evt);
					}
				});
			}

		}
        {
        	panel_content = new JPanel();
        	getContentPane().add(panel_content, BorderLayout.CENTER);
        	BorderLayout panel_contentLayout = new BorderLayout();
        	panel_content.setLayout(panel_contentLayout);
        	{
        		sp_content = new JScrollPane();
        		panel_content.add(sp_content, BorderLayout.CENTER);
        		sp_content.setSize(500, 300);
        		sp_content.setPreferredSize(new java.awt.Dimension(600, 300));
        		sp_content.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
        		{
        			
        			tbl_content = new JXTable();
        			sp_content.setViewportView(tbl_content);
        			tbl_content.setModel(tbl_contentModel);
        			tbl_content.addHighlighter(HighlighterFactory.createSimpleStriping(HighlighterFactory.LEDGER));
        			tbl_content.addHighlighter(new ColorHighlighter(HighlightPredicate.ROLLOVER_ROW,Color.BLACK, Color.WHITE));
		    		JTableHeader th = tbl_content.getTableHeader();
				    th.setFont(new java.awt.Font("Century Gothic", Font.BOLDWEIGHT_BOLD, 11));
        		}

        	}
        	
        }
		{
			panel_footer = new JPanel();
			FlowLayout panel_footerLayout = new FlowLayout();
			panel_footerLayout.setAlignment(FlowLayout.RIGHT);
			panel_footer.setLayout(panel_footerLayout);

		}
		getContentPane().add(panel_footer, BorderLayout.SOUTH);
		panel_footer.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
		{
			lbl_version = new JLabel();
			lbl_version.setText("Version "+var_version);
			panel_footer.add(lbl_version);
			
		}
		{
			lbl_sax = new JLabel();
			panel_footer.add(lbl_sax);
			lbl_sax.setText("copyright 2009, AIB Thomas Sax");
		}
		this.setTitle("Teileverwendungsnachweis");
		{
			menubar = new JMenuBar();
			setJMenuBar(menubar);
			{
				menu = new JMenu();
				menubar.add(menu);
				menu.setText("Datei");
				{
					item_drucken = new JMenuItem();
					menu.add(item_drucken);
					item_drucken.setText("Drucken");
					item_drucken.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							item_druckenActionPerformed(evt);
						}
					});
				}
				{
					item_beenden = new JMenuItem();
					menu.add(item_beenden);
					item_beenden.setText("Beenden");
					item_beenden.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							item_beendenActionPerformed(evt);
						}
					});
				}
			}
			{
				menu_werkzeug = new JMenu();
				menubar.add(menu_werkzeug);
				menu_werkzeug.setText("Werkzeug");
				{
					item_psl_ake = new JMenuItem();
					menu_werkzeug.add(item_psl_ake);
					item_psl_ake.setText("Projektstückliste AKE");
					item_psl_ake.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							item_psl_akeActionPerformed(evt);
						}
					});
				}
				{
					item_pflege_mengen = new JMenuItem();
					menu_werkzeug.add(item_pflege_mengen);
					item_pflege_mengen.setText("Pflege der Mengen/pa");
					item_pflege_mengen.setEnabled(false);
					item_pflege_mengen.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							item_pflege_mengenActionPerformed(evt);
						}
					});
				}
				{
					item_psl = new JMenuItem();
					menu_werkzeug.add(item_psl);
					item_psl.setText("Projektstückliste");
					item_psl.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							item_pslActionPerformed(evt);
						}
					});
				}
				{
					item_mengen = new JMenuItem();
					menu_werkzeug.add(item_mengen);
					item_mengen.setText("Mengenübersicht");
					item_mengen.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							item_mengenActionPerformed(evt);
						}
					});
				}
			}
		}
		this.setVisible(true);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new SyntheticaClassyLookAndFeel());
			String[] li = {"Licensee=Nexans autoelectric GmbH", "LicenseRegistrationNumber=132026", "Product=Synthetica", "LicenseType=Standard Site License", "ExpireDate=--.--.----", "MaxVersion=2.17.999"};
			UIManager.put("Synthetica.license.info", li);
			UIManager.put("Synthetica.license.key", "8D75DF9A-A29A1EA6-499013BE-54FB7691-E510B592");

		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TVN tvn = new TVN();

	}
	

    public double rint(double number, int n){
    	return(Math.rint(number * Math.pow(10, n))) / Math.pow(10, n);
    }


public void daten_auslesen(){
    //Datenauslesen
	String feld="",sql_krit="";
	if(cb_spalte.getSelectedItem().toString().trim().equals("PA-Nr")){
		sql_krit = "WHERE panr LIKE '"+tf_suche.getText().trim()+"%' AND matnr LIKE '%' AND artikelnummer LIKE '%'  ORDER BY panr";
	}
	else if(cb_spalte.getSelectedItem().toString().trim().equals("Stoffenummer")){
		sql_krit = "WHERE panr LIKE '%' AND matnr LIKE '"+tf_suche.getText().trim()+"%' AND artikelnummer LIKE '%' ORDER BY panr";
	}
	else {
		sql_krit = "WHERE panr LIKE '%' AND matnr LIKE '%' AND artikelnummer LIKE '"+tf_suche.getText().trim()+"%' ORDER BY panr";
	}
	
	
	
	
	String sql_string = "SELECT panr AS [PA-Nr],matnr AS Stoffenummer,menge AS Menge,artikelnummer AS " +
			"Materialnummer FROM TVN_Material "+sql_krit;
	//System.out.println(sql_string);
	setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    verbindung.executeQuery(sql_string);
    if(verbindung.getRowCount() < 1){
    	tbl_content.setModel(tbl_contentModel);
    }
    else{
    	tbl_content.setModel(verbindung);
    }
    setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

	
	public void schliessen(){
		try {
			verbindung.finalize();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.dispose();
		System.exit(0);
	}
	
	private void item_beendenActionPerformed(ActionEvent evt) {
		schliessen();
	}
	
	private void bt_filterActionPerformed(ActionEvent evt) {
		daten_auslesen();
	}
	
	private void tf_sucheKeyPressed(KeyEvent evt) {
		char key = evt.getKeyChar();
        if(key == KeyEvent.VK_ENTER){
           daten_auslesen();
        }
	}
	
	private void item_psl_akeActionPerformed(ActionEvent evt) {
		//Psl_auswahl psl = new Psl_auswahl(this,true);
		JTextField name = new JTextField();
		
        JPasswordField pwd = new JPasswordField();
        Object[] message = { "Eingabe Name und Passwort\n", name, pwd };
        int resp = JOptionPane.showConfirmDialog(null, message, "Login", 
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        name.requestFocus();
        if(resp == JOptionPane.OK_OPTION) {
            String id = new String(name.getText());
            String pass = new String(pwd.getPassword());
            
            LDAP_variabel loginService = new LDAP_variabel(name.getText(),pwd.getPassword(),"G-TVN"); 
    		boolean erg = loginService.authenticate(name.getText(),pwd.getPassword(), "");
    		
    		LDAP_Abfrage ldap = new LDAP_Abfrage();
    		String name_komplett = ldap.name_komplett(name.getText());//tf_username.getText().trim()
    		
    		if(erg){
    			Psl_auswahl psl = new Psl_auswahl(this,true,"ake");	
            	this.dispose();
    		}
    		else{
            	JOptionPane.showMessageDialog(this,"<html>Passwort falsch <br> oder User nicht angelegt!</html>","Error",JOptionPane.ERROR_MESSAGE);
            } 
        }
	}
	
	private void item_pflege_mengenActionPerformed(ActionEvent evt) {
		/*
		//Psl_auswahl psl = new Psl_auswahl(this,true);
		JTextField name = new JTextField();
		
        JPasswordField pwd = new JPasswordField();
        Object[] message = { "Eingabe Name und Passwort\n", name, pwd };
        int resp = JOptionPane.showConfirmDialog(null, message, "Login", 
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        name.requestFocus();
        if(resp == JOptionPane.OK_OPTION) {
            String id = new String(name.getText());
            String pass = new String(pwd.getPassword());
            
            String sql_string = "SELECT * FROM account WHERE programm = 'tvn' AND username = '"+id+"' AND pass = '"+pass+"'" ;
            verbindung_2.executeQuery(sql_string);
            if(verbindung_2.getRowCount() == 1){
            	Pflege_mengen mengen = new Pflege_mengen();
            }
            else{
            	JOptionPane.showMessageDialog(this,"<html>Passwort falsch <br> oder User nicht angelegt!</html>","Error",JOptionPane.ERROR_MESSAGE);
            }
        }*/
	}
	
	private void item_druckenActionPerformed(ActionEvent evt) {
		int spalten = tbl_content.getColumnCount();
		int row = tbl_content.getRowCount();
		ArrayList al = new ArrayList();
		for(int i=0;i<row;i++){
			for(int j=0;j<spalten;j++){
				al.add(tbl_content.getValueAt(i,j));
			}
		}
		tvn_ausdruck ausdruck = new tvn_ausdruck(tf_suche.getText(),al);
	}
	
	private void item_pslActionPerformed(ActionEvent evt) {
		Psl_auswahl psl = new Psl_auswahl(this,true,"ohne");	
	}
	
	private void item_mengenActionPerformed(ActionEvent evt) {
		Psl_auswahl psl = new Psl_auswahl(this,true,"mengenuebersicht");
	}

}
