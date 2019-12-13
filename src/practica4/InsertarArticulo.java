package practica4;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

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
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class InsertarArticulo extends JFrame {

	Connection conexion = null;
	PreparedStatement prepStat = null;
	ResultSet reSet = null;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textDesc;
	private JTextField textCan;
	private JTextField textPrec;

	/**
	 * Create the frame.
	 */

	public InsertarArticulo() {
		setType(Type.UTILITY);
		setTitle("Insertar Art\u00EDculo");
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

		textDesc = new JTextField();
		textDesc.setHorizontalAlignment(SwingConstants.CENTER);
		textDesc.setBounds(37, 36, 126, 20);
		panel.add(textDesc);
		textDesc.setColumns(10);

		textCan = new JTextField();
		textCan.setHorizontalAlignment(SwingConstants.CENTER);
		textCan.setBounds(37, 148, 126, 20);
		panel.add(textCan);
		textCan.setColumns(10);

		textPrec = new JTextField();
		textPrec.setHorizontalAlignment(SwingConstants.CENTER);
		textPrec.setColumns(10);
		textPrec.setBounds(37, 92, 126, 20);
		panel.add(textPrec);

		JLabel lblNewLabel = new JLabel("Descripci\u00F3n");
		lblNewLabel.setBounds(54, 11, 90, 14);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_2 = new JLabel("Cantidad");
		lblNewLabel_2.setBounds(73, 123, 64, 14);
		panel.add(lblNewLabel_2);

		JButton btnInsertar = new JButton("Insertar");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (textDesc.getText().equals("") || textPrec.getText().equals("") || textCan.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Debe rellenar el formulario primero");
				} else {
					String sql = ("Insert into Articulos (descripcionArticulo, precioArticulo, cantidadArticulo) values (?,?,?)");
					conexion = Conexion.conectar();
					try {

						prepStat = conexion.prepareStatement(sql);
						prepStat.setString(1, textDesc.getText());
						prepStat.setString(2, textPrec.getText());
						prepStat.setString(3, textCan.getText());
						int resultado = prepStat.executeUpdate();
						System.out.println(resultado);
						if (resultado > 0) {
							int resp = JOptionPane.showConfirmDialog(null,
									"El registro se ha insertado correctamente. ¿Desea seguir agregando artículos?", "¡Exito¡",
									JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
							if (resp == 0) {
								//JOptionPane.showMessageDialog(null, "Has pulsado SI");
								 textDesc.setText("");
									textPrec.setText("");
									textCan.setText("");
							} else {
								Articulos artic = new Articulos();
								artic.setVisible(true);
								setVisible(false);
							}

						}

						else {
							JOptionPane.showMessageDialog(null, "Error al intentar insertar");
						}

					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "Error al intentar insertar: " + e2);
					}
				}
			}
		});

		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(73, 67, 53, 14);
		panel.add(lblPrecio);
		btnInsertar.setBounds(54, 195, 89, 23);
		panel.add(btnInsertar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Articulos artic = new Articulos();
				artic.setVisible(true);
				setVisible(false);
			}
		});
		btnCancelar.setBounds(54, 236, 89, 23);
		panel.add(btnCancelar);
	}
}
