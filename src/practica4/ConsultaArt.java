package practica4;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.JSlider;
import javax.swing.DropMode;
import java.awt.List;
import java.awt.Choice;
import javax.swing.JEditorPane;
import java.awt.ScrollPane;
import java.awt.TextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.xdevapi.Statement;

import net.proteanit.sql.DbUtils;

import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Scrollbar;
import java.awt.Panel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Window.Type;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ConsultaArt extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable modelo;

	Connection conexion = null;
	PreparedStatement prepStat = null;
	ResultSet reSet = null;

	/**
	 * Create the frame.
	 */
	public ConsultaArt() {
		setType(Type.UTILITY);
		setTitle("Consultar Art\u00EDculos");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 669, 334);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Articulos artic = new Articulos();
				artic.setVisible(true);
				setVisible(false);
			}
		});
		btnVolver.setBounds(285, 261, 89, 23);
		panel.add(btnVolver);
		
		JLabel lblListaDeArtculos = new JLabel("Lista de Art\u00EDculos");
		lblListaDeArtculos.setHorizontalAlignment(SwingConstants.CENTER);
		lblListaDeArtculos.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblListaDeArtculos.setBounds(54, 11, 543, 36);
		panel.add(lblListaDeArtculos);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(54, 58, 543, 173);
		panel.add(scrollPane);
		
		modelo = new JTable();
		scrollPane.setViewportView(modelo);
		
		conexion = Conexion.conectar();
	String sql ="select * from articulos";
	try {
		PreparedStatement pst = conexion.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		System.out.println("Mostrando Artículos...");
		if(rs.next()== true)
		{
			rs.previous();
			modelo.setModel(DbUtils.resultSetToTableModel(rs));
		}
		
		
		else
		{
			
			JOptionPane.showMessageDialog(null, "la tabla está vacía");
			
			lblListaDeArtculos.setText("La lista de artículos está vacía");
		}
			
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	}
}
