package practica4;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Window.Type;
import javax.swing.ImageIcon;

public class Articulos extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	

	/**
	 * Create the frame.
	 */
	
	
	
	
	public Articulos() {
		setType(Type.UTILITY);
		setResizable(false);
		setTitle("ARTICULOS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 329, 185);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Insertar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InsertarArticulo insArt = new InsertarArticulo();
				insArt.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton.setBounds(10, 73, 89, 23);
		panel.add(btnNewButton);
		
		JLabel lblArtculos = new JLabel("ART\u00CDCULOS");
		lblArtculos.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblArtculos.setBounds(111, 22, 89, 23);
		panel.add(lblArtculos);
		
		JButton btnConsulta = new JButton("Consultar");
		btnConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultaArt consart = new ConsultaArt();
				consart.setVisible(true);
				setVisible(false);
			}
		});
		btnConsulta.setBounds(111, 112, 89, 23);
		panel.add(btnConsulta);
		
		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Tiendecita principal = new Tiendecita();
				principal.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_3.setIcon(new ImageIcon(Articulos.class.getResource("/com/sun/javafx/scene/web/skin/Undo_16x16_JFX.png")));
		btnNewButton_3.setBounds(10, 11, 49, 25);
		panel.add(btnNewButton_3);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModificarArt modiselart = new ModificarArt();
				modiselart.setVisible(true);
				setVisible(false);
				
			}
		});
		btnModificar.setBounds(111, 73, 89, 23);
		panel.add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				EliminarArt eliminart = new EliminarArt();
				eliminart.setVisible(true);
				setVisible(false);
			}
		});
		btnEliminar.setBounds(214, 73, 89, 23);
		panel.add(btnEliminar);
	}
}

