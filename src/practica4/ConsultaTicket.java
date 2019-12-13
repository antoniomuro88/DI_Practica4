package practica4;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Scrollbar;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;


public class ConsultaTicket extends JFrame {

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
	public ConsultaTicket() {
		setType(Type.UTILITY);
		setTitle("Consultar Tickets");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 435, 338);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(38, 43, 347, 184);
		panel.add(scrollPane);
		
		modelo = new JTable();
		scrollPane.setViewportView(modelo);
		modelo.setShowHorizontalLines(false);
		modelo.setBorder(new LineBorder(new Color(0, 0, 0)));
		modelo.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Fecha", "Descripci\u00F3n", "Cantidad", "Total"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, String.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		modelo.getColumnModel().getColumn(0).setResizable(false);
		modelo.getColumnModel().getColumn(0).setPreferredWidth(87);
		modelo.getColumnModel().getColumn(1).setResizable(false);
		modelo.getColumnModel().getColumn(1).setPreferredWidth(130);
		modelo.getColumnModel().getColumn(2).setResizable(false);
		modelo.getColumnModel().getColumn(2).setPreferredWidth(58);
		modelo.getColumnModel().getColumn(3).setResizable(false);
		modelo.getColumnModel().getColumn(3).setPreferredWidth(48);
		JLabel lbllistatickets = new JLabel("Lista de Tickets");
		lbllistatickets.setBounds(0, 12, 419, 20);
		panel.add(lbllistatickets);
		lbllistatickets.setHorizontalAlignment(SwingConstants.CENTER);
		lbllistatickets.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		conexion = Conexion.conectar();
		String sql ="select * from tickets";
		try {
			PreparedStatement pst = conexion.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			System.out.println("Mostrando Tickets...");
			if(rs.next()== true)
			{
				rs.previous();
				modelo.setModel(DbUtils.resultSetToTableModel(rs));
			}
			
			
			else
			{
				
				JOptionPane.showMessageDialog(null, "La tabla 'Tickets' está vacía. Prueba a crear uno nuevo");
				
				lbllistatickets.setText("La lista de tickets está vacía");
			}
				
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Tickets tic = new Tickets();
				tic.setVisible(true);
				setVisible(false);
			}
		});
		btnVolver.setBounds(160, 265, 89, 23);
		panel.add(btnVolver);
		
		
	}
}
