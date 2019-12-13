package practica4;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import java.awt.GridBagLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Window.Type;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class EliminarArt extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2948429002419309357L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */

	Connection conexion = null;
	PreparedStatement prepStat = null;
	PreparedStatement prepStat1 = null;
	ResultSet reSet1 = null;
	ResultSet reSet = null;
	int vacio = 0;

	public EliminarArt() {
		setType(Type.UTILITY);
		setTitle("Elininar Art\u00EDculo");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 217, 318);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JComboBox combo = new JComboBox();
		combo.setBounds(10, 59, 181, 20);
		panel.add(combo);
		ArrayList<String> ArrayId = new ArrayList<String>();
		ArrayList<String> ArrayName = new ArrayList<String>();
		
		String sql = ("Select idArticulo, descripcionArticulo from articulos");

		conexion = Conexion.conectar();
		try {

			prepStat = conexion.prepareStatement(sql);

			ResultSet reSet = prepStat.executeQuery(sql);

			if (reSet.next() == true) {

				reSet.previous();
				while (reSet.next()) {

					String id = reSet.getString("idArticulo");
					ArrayId.add(id);
					String name = reSet.getString("descripcionArticulo");
					ArrayName.add(name);

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

		JLabel lblSeleccionaArtculoA = new JLabel("Selecciona art\u00EDculo a eliminar");
		lblSeleccionaArtculoA.setBounds(10, 23, 201, 14);
		panel.add(lblSeleccionaArtculoA);

		JButton btnInsertar = new JButton("Eliminar");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int resp = JOptionPane.showConfirmDialog(null,
						"Está apunto de borrar un registro de manera permanente. ¿Está seguro?", "¡Atención!",
						JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
				if (resp == 0) {
					
					int ex = combo.getSelectedIndex();
					int z = 0;
					String zid = "";
					String znom = "";
					for (int a = 0; a < ArrayName.size(); a++) {

						String ex1 = ArrayName.get(a);
						String ex2 = ArrayId.get(a);
						if (ex == a) {
							z = a;
							zid = ex2;
							znom = ex1;
						}

					}

					System.out.println("COMBO NUMERO: " + ex + " NUMERO DE REGISTRO: " + z
							+ " ID DEL REGISTRO: " + zid + " NOMBRE DEL REGISTRO: " + znom);

					String sql1 = ("delete from articulos WHERE idArticulo = ?");
					conexion = Conexion.conectar();

					try {
						prepStat1 = conexion.prepareStatement(sql1);
						prepStat1.setString(1, zid);

						int resultado = prepStat1.executeUpdate();
						if (resultado > 0) {
							int respo = JOptionPane.showConfirmDialog(null,
									"El registro se ha borrado correctamente. ¿Desea seguir borrando artículos?",
									"¡Exito¡", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
							ArrayName.remove(ex);
							ArrayId.remove(ex);
							if (respo == 0) {
								ResultSet reSet = prepStat.executeQuery(sql);

								if (reSet.next() == true) {

									reSet.previous();
									if (combo.getSelectedIndex() > -1)
										combo.removeAllItems();
									while (reSet.next()) {

										String id = reSet.getString("idArticulo");
										ArrayId.add(id);
										String name = reSet.getString("descripcionArticulo");
										ArrayName.add(name);

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

							} else {
								Articulos artic = new Articulos();
								artic.setVisible(true);
								setVisible(false);
							}
						}

					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Error al intentar borrar: " + e);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Has pulsado NO");
				}
			}
		});
		btnInsertar.setBounds(56, 211, 89, 23);
		panel.add(btnInsertar);
		
		
		
		combo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String zid = "";
				String znom = "";
				

				int ex = combo.getSelectedIndex();
				int z = 0;

				for (int a = 0; a < ArrayName.size(); a++) {

					String ex1 = ArrayName.get(a);
					String ex2 = ArrayId.get(a);
					
					if (ex == a) {
						zid = ex2;
						znom = ex1;
						

					}

				}

				System.out.println("COMBO NUMERO: " + ex + " ID: "
						+ zid + " NOMBRE: " + znom);
				
				
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
		btnNewButton.setBounds(56, 245, 89, 23);
		panel.add(btnNewButton);
		
		combo.setSelectedIndex(0);
		
	}
}
