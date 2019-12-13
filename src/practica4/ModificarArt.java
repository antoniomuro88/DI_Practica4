package practica4;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.JSeparator;
import java.awt.Canvas;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ModificarArt extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9053920947070902007L;

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	Connection conexion = null;
	PreparedStatement prepStat = null;
	ResultSet reSet = null;
	
	
	int vacio=0;
	private JTextField txtDesc;
	private JTextField txtPrec;
	private JTextField txtCant;
	
	public ModificarArt() {
		setType(Type.UTILITY);
		setTitle("Modificar Art\u00EDculo");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 213, 385);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JComboBox<String> combo = new JComboBox<String>();
		combo.setBounds(10, 36, 181, 20);
		panel.add(combo);

		ArrayList<String> ArrayId = new ArrayList<String>();
		ArrayList<String> ArrayName = new ArrayList<String>();
		ArrayList<String> ArrayPrice = new ArrayList<String>();
		ArrayList<String> ArrayCuant = new ArrayList<String>();
		
		String sql = ("Select * from articulos");
		conexion = Conexion.conectar();
		try {

			prepStat = conexion.prepareStatement(sql);

			ResultSet reSet = prepStat.executeQuery(sql);
			if (reSet.next() == true) {
				reSet.previous();
				while (reSet.next()) {
					String name = reSet.getString("descripcionArticulo");
					ArrayName.add(name);
					String id = reSet.getString("idArticulo");
					ArrayId.add(id);
					String price = reSet.getString("precioArticulo");
					ArrayPrice.add(price);
					String cuant = reSet.getString("cantidadArticulo");
					ArrayCuant.add(cuant);
					if (name.equals("")) {
						combo.addItem("");
						combo.setVisible(false);
					} else {
						combo.addItem(reSet.getString("descripcionArticulo"));

						combo.setVisible(true);
					}
				}
			} else {
			vacio = 1;
			JOptionPane.showMessageDialog(null, "La tabla 'Artículos está vacía'. Ve a la pantalla 'Insertar Artículos'");
				
			}

		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "Error al intentar insertar: " + e2);
		}
		
		
		
		JButton btnInsertar = new JButton("Modificar");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String zid = "";
				String znom = "";
				String zpre = "";
				String zcan = "";

				int ex = combo.getSelectedIndex();
				int z = 0;

				for (int a = 0; a < ArrayName.size(); a++) {

					String ex1 = ArrayName.get(a);
					String ex2 = ArrayId.get(a);
					String ex3 = ArrayPrice.get(a);
					String ex4 = ArrayCuant.get(a);
					if (ex == a) {
						zid = ex2;
						znom = ex1;
						zpre = ex3;
						zcan = ex4;

					}

				}
				
				
				String sql = ("UPDATE Articulos SET descripcionArticulo = ?, precioArticulo= ?, cantidadArticulo = ? WHERE idArticulo = ?;");
				conexion = Conexion.conectar();
				
					try {

						prepStat = conexion.prepareStatement(sql);
						prepStat.setString(1, txtDesc.getText());
						prepStat.setString(2, txtPrec.getText());
						prepStat.setString(3, txtCant.getText());
						prepStat.setString(4, zid);
						int resultado = prepStat.executeUpdate();
						System.out.println(""+resultado+ " registro modifcado");
						
							
						if (resultado > 0) {
							JOptionPane.showMessageDialog(null, "El artículo se ha modificado correctamente.");
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Se ha producido un error al modificar");
						}
							
								Articulos artic = new Articulos();
								artic.setVisible(true);
								setVisible(false);
							



					} catch (Exception e2) {
						System.out.println("Se ha producido un error: "+e2);
					}
				}
					
					
				
				
				
			}
		);
		btnInsertar.setBounds(53, 270, 89, 23);
		panel.add(btnInsertar);

		
		combo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String zid = "";
				String znom = "";
				String zpre = "";
				String zcan = "";

				int ex = combo.getSelectedIndex();
				int z = 0;

				for (int a = 0; a < ArrayName.size(); a++) {

					String ex1 = ArrayName.get(a);
					String ex2 = ArrayId.get(a);
					String ex3 = ArrayPrice.get(a);
					String ex4 = ArrayCuant.get(a);
					if (ex == a) {
						zid = ex2;
						znom = ex1;
						zpre = ex3;
						zcan = ex4;

					}

				}

				System.out.println("COMBO NUMERO: " + ex + " ID: "
						+ zid + " NOMBRE: " + znom + " PRECIO: " + zpre + " CANTIDAD: " + zcan);
				txtDesc.setText(znom);
				txtCant.setText(zcan);	
				txtPrec.setText(zpre);
			}
		});
		
		
		
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Articulos artic = new Articulos();
				artic.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton.setBounds(54, 312, 89, 23);
		panel.add(btnNewButton);

		JLabel lblSeleccionaArtculoA = new JLabel("Selecciona art\u00EDculo a modificar");
		lblSeleccionaArtculoA.setBounds(14, 12, 201, 14);
		panel.add(lblSeleccionaArtculoA);
		
		txtDesc = new JTextField();
		txtDesc.setHorizontalAlignment(SwingConstants.CENTER);
		txtDesc.setBounds(10, 106, 181, 20);
		panel.add(txtDesc);
		txtDesc.setColumns(10);
		
		txtPrec = new JTextField();
		txtPrec.setHorizontalAlignment(SwingConstants.CENTER);
		txtPrec.setColumns(10);
		txtPrec.setBounds(52, 159, 86, 20);
		panel.add(txtPrec);
		
		txtCant = new JTextField();
		txtCant.setHorizontalAlignment(SwingConstants.CENTER);
		txtCant.setColumns(10);
		txtCant.setBounds(52, 216, 86, 20);
		panel.add(txtCant);
		
		JLabel lblDescripcin = new JLabel("Descripci\u00F3n");
		lblDescripcin.setBounds(65, 85, 89, 14);
		panel.add(lblDescripcin);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(73, 137, 135, 14);
		panel.add(lblPrecio);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(66, 191, 135, 14);
		panel.add(lblCantidad);
		combo.setSelectedIndex(0);
		
		
	}
}
