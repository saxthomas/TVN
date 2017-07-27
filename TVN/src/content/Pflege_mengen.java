package content;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

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
public class Pflege_mengen 
extends JFrame
{
	private JPanel panel_header;
	private JTextField tf_menge;
	private JLabel lbl_menge;
	private JLabel lbl_ueber;
	private JLabel lbl_panr;
	private JTextField tf_panr;
	private JLabel lbl_filter;
	private JTextField tf_filter;
	private JTable tbl_content;
	private JScrollPane sp_content;
	private JButton bt_uebernehmen;
	private JButton bt_abbrechen;
	private JPanel panel_footer;
	private JPanel panel_content;
	
	JDBCAdapter verbindung = new JDBCAdapter("jdbc:jtds:sqlserver://172.16.0.158:1433/aedb","net.sourceforge.jtds.jdbc.Driver","r_aedb","r_aedb"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	
	public Pflege_mengen() {
		this.setSize(400,400);
		this.setLocationRelativeTo(null);
		
		this.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("bilder/logo_icon.jpg")).getImage());
		this.setTitle("Pflege Mengen");
		{
			panel_header = new JPanel();
			FlowLayout panel_headerLayout = new FlowLayout();
			panel_headerLayout.setAlignment(FlowLayout.LEFT);
			panel_header.setLayout(panel_headerLayout);
			getContentPane().add(panel_header, BorderLayout.NORTH);
			panel_header.setBackground(new java.awt.Color(255,255,255));
			panel_header.setFont(new java.awt.Font("Tahoma",0,14));
			panel_header.setBorder(new LineBorder(new java.awt.Color(128,128,128), 1, false));
			{
				lbl_ueber = new JLabel();
				panel_header.add(lbl_ueber);
				lbl_ueber.setText("Pflege der Mengen/pa");
				lbl_ueber.setFont(new java.awt.Font("Tahoma",0,16));
			}
		}
		{
			panel_footer = new JPanel();
			FlowLayout panel_footerLayout = new FlowLayout();
			panel_footerLayout.setAlignment(FlowLayout.RIGHT);
			getContentPane().add(panel_footer, BorderLayout.SOUTH);
			panel_footer.setLayout(panel_footerLayout);
			panel_footer.setBorder(new LineBorder(new java.awt.Color(128,128,128), 1, false));
			{
				bt_uebernehmen = new JButton();
				panel_footer.add(bt_uebernehmen);
				bt_uebernehmen.setText("Übernehmen");
				bt_uebernehmen.setIcon(new ImageIcon(getClass().getClassLoader().getResource("bilder/database.gif")));
				bt_uebernehmen.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						bt_uebernehmenActionPerformed(evt);
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
		{
			panel_content = new JPanel();
			getContentPane().add(panel_content, BorderLayout.CENTER);
			GridBagLayout panel_contentLayout = new GridBagLayout();
			panel_contentLayout.columnWidths = new int[] {7, 7, 7, 7, 7, 7, 7, 7};
			panel_contentLayout.rowHeights = new int[] {7, 7, 7, 7, 7, 7, 7, 7, 7, 7};
			panel_contentLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
			panel_contentLayout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
			panel_content.setLayout(panel_contentLayout);
			{
				sp_content = new JScrollPane();
				panel_content.add(sp_content, new GridBagConstraints(0, 1, 5, 10, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				sp_content.setPreferredSize(new java.awt.Dimension(200, 280));
				{
					TableModel tbl_contentModel = 
						new DefaultTableModel(
								new String[][] { { "One", "Two" }, { "Three", "Four" } },
								new String[] { "Column 1", "Column 2" });
					tbl_content = new JTable();
					sp_content.setViewportView(tbl_content);
					tbl_content.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent evt) {
							tbl_contentMouseClicked(evt);
						}
					});
					tbl_content.setModel(tbl_contentModel);
				}
			}
			{
				tf_filter = new JTextField();
				panel_content.add(tf_filter, new GridBagConstraints(6, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				tf_filter.setPreferredSize(new java.awt.Dimension(100, 20));
				tf_filter.addKeyListener(new KeyAdapter() {
					public void keyPressed(KeyEvent evt) {
						tf_filterKeyPressed(evt);
					}
				});
			}
			{
				lbl_filter = new JLabel();
				panel_content.add(lbl_filter, new GridBagConstraints(5, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				lbl_filter.setText("Filter");
				lbl_filter.setIcon(new ImageIcon(getClass().getClassLoader().getResource("bilder/filter.gif")));
			}
			{
				lbl_panr = new JLabel();
				panel_content.add(lbl_panr, new GridBagConstraints(5, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				lbl_panr.setText("PA-Nr");
			}
			{
				tf_panr = new JTextField();
				panel_content.add(tf_panr, new GridBagConstraints(6, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				tf_panr.setPreferredSize(new java.awt.Dimension(100, 20));
				tf_panr.setEditable(false);
			}
			{
				lbl_menge = new JLabel();
				panel_content.add(lbl_menge, new GridBagConstraints(5, 4, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				lbl_menge.setText("Menge/pa");
			}
			{
				tf_menge = new JTextField();
				panel_content.add(tf_menge, new GridBagConstraints(6, 4, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				tf_menge.setPreferredSize(new java.awt.Dimension(100, 20));
			}
		}
		

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				thisWindowClosing(evt);
			}
		});
		
		daten_auslesen("");
		this.setVisible(true);
	}
	
	private void daten_auslesen(String sql_krit){
		
		String sql_string = "SELECT panr AS [Pa-Nr],mengepa AS [Menge/pa] FROM tvn_menge WHERE panr LIKE '" +sql_krit+
		"%' ORDER BY panr";
		verbindung.executeQuery(sql_string);
		tbl_content.setModel(verbindung);
	}
	
	
	private void schliessen(){
		try {
			verbindung.finalize();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.dispose();
	}
	
	private void thisWindowClosing(WindowEvent evt) {
		schliessen();
	}
	
	private void bt_abbrechenActionPerformed(ActionEvent evt) {
		schliessen();
	}
	
	private void bt_uebernehmenActionPerformed(ActionEvent evt) {
		try {
			double menge = Double.parseDouble(tf_menge.getText());
			String sql_string = "UPDATE tvn_menge SET mengepa = "+menge+" WHERE panr = '"+tf_panr.getText().trim()+"'";
			verbindung.sql_exec(sql_string);
			daten_auslesen(tf_filter.getText().trim());
			JOptionPane.showMessageDialog(this,"Daten aktualisiert!","Information",JOptionPane.INFORMATION_MESSAGE);
		}
		catch (NumberFormatException e){
			JOptionPane.showMessageDialog(this,"Falsches Format im Feld Menge","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void tf_filterKeyPressed(KeyEvent evt) {
	       char key = evt.getKeyChar();
	        if(key == KeyEvent.VK_ENTER){
	            daten_auslesen(tf_filter.getText().trim());
	        }
	}
	
	private void tbl_contentMouseClicked(MouseEvent evt) {
		Point p = evt.getPoint();
   	 	int rowPos = tbl_content.rowAtPoint(p);
   	 	
   	 	tf_panr.setText(tbl_content.getValueAt(rowPos, 0).toString());
   	 	tf_menge.setText(tbl_content.getValueAt(rowPos, 1).toString());
	}

}
