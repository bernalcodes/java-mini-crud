package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.UUID;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import controller.GestorProductosController;
import model.ProductoVO;

public class DetallesProductoView extends JFrame implements ActionListener {
	private final GestorProductosController controller;
	private final GestorProductosView parentView;
	private ProductoVO producto;
	private JLabel lblID, lblNombre, lblPrecio, lblProveedor, lblCaracteristicas;
	private JTextField txtID, txtNombre, txtPrecio;
	private JTextArea txtCaracteristicas;
	private JButton btnModificar, btnEliminar, btnCancelar;
	private JComboBox<String> cbProveedor;
	private JScrollPane txtCaracteristicasScroll;

	// Class constructor
	public DetallesProductoView(GestorProductosController controller, UUID id, GestorProductosView parentView) {
		super("Detalles de producto");
		this.controller = controller;
		this.producto = controller.getMapaProductos().get(id);
		this.parentView = parentView;
		
		setLayout(null);
		loadFields();
		loadButtons();

		addWindowListener(getWindowAdapter());
		setSize(450, 430);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	// Creating and adding form fields
	private void loadFields() {
		// ID label
		lblID = new JLabel("ID", SwingConstants.CENTER);
		lblID.setBounds(20, 20, 80, 30);
		lblID.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		this.add(lblID);
		
		// ID input
		txtID = new JTextField();
		txtID.setBounds(120, 20, 300, 30);
		txtID.setBorder(BorderFactory.createCompoundBorder(
							BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), 
							BorderFactory.createEmptyBorder(0, 5, 0, 0)));
		txtID.setBackground(Color.WHITE);
		txtID.setText(this.producto.getId().toString());
		txtID.setEditable(false);
		this.add(txtID);

		// Nombre label
		lblNombre = new JLabel("Nombre", SwingConstants.CENTER);
		lblNombre.setBounds(20, 70, 80, 30);
		lblNombre.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		this.add(lblNombre);

		// ID input
		txtNombre = new JTextField();
		txtNombre.setBounds(120, 70, 300, 30);
		txtNombre.setBorder(BorderFactory.createCompoundBorder(
							BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), 
							BorderFactory.createEmptyBorder(0, 5, 0, 0)));
		txtNombre.setBackground(Color.WHITE);
		txtNombre.setText(this.producto.getNombre());
		this.add(txtNombre);

		// Nombre label
		lblPrecio = new JLabel("Precio", SwingConstants.CENTER);
		lblPrecio.setBounds(20, 120, 80, 30);
		lblPrecio.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		this.add(lblPrecio);

		// ID input
		txtPrecio = new JTextField();
		txtPrecio.setBounds(120, 120, 300, 30);
		txtPrecio.setBorder(BorderFactory.createCompoundBorder(
							BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), 
							BorderFactory.createEmptyBorder(0, 5, 0, 0)));
		txtPrecio.setBackground(Color.WHITE);
		txtPrecio.setText(((Float)this.producto.getPrecio()).toString());
		this.add(txtPrecio);

		// Provider label and comboBox
		loadCBox();
		
		// Caracteristicas label and textArea
		loadTextArea();
	}

	// Provider ComboBox loader
	private void loadCBox() {
		// Provider label
		lblProveedor = new JLabel("Proveedor", SwingConstants.CENTER);
		lblProveedor.setBounds(20, 170, 80, 30);
		lblProveedor.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		this.add(lblProveedor);

		// Provider comboBox
		cbProveedor = new JComboBox<>(new String[] { "PROVEEDOR", "ALCATEL", "HUAWEI", "SOLAR WINDS", "ZTE" });
		cbProveedor.setBounds(120,170,225,30);

		DefaultListCellRenderer listCellRenderer = new DefaultListCellRenderer();
		listCellRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
		cbProveedor.setRenderer(listCellRenderer);
		cbProveedor.setSelectedItem(this.producto.getProveedor());

		this.add(cbProveedor);
	}

	// Caracteristicas label and field loader
	private void loadTextArea() {
		// Caracteristicas label
		lblCaracteristicas = new JLabel("Características", SwingConstants.CENTER);
		lblCaracteristicas.setBounds(20, 220, 100, 30);
		lblCaracteristicas.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		this.add(lblCaracteristicas);

		// Caracteristicas scrollPane
		txtCaracteristicasScroll = new JScrollPane();
		txtCaracteristicasScroll.setSize(280, 100);
		txtCaracteristicasScroll.setLocation(140, 220);

		// Caracteristicas textArea
		txtCaracteristicas = new JTextArea();
		txtCaracteristicas.setLineWrap(true);
		txtCaracteristicas.setWrapStyleWord(true);
		txtCaracteristicas.setBorder(BorderFactory.createCompoundBorder(
										txtCaracteristicas.getBorder(),
										BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		txtCaracteristicas.setText(this.producto.getCaracteristicas());
		txtCaracteristicasScroll.setViewportView(txtCaracteristicas);
		this.getContentPane().add(txtCaracteristicasScroll);
	}

	// Buttons loader
	private void loadButtons() {
		// Actualizar producto button 
		btnModificar = new JButton("Modificar");
		btnModificar.setSize(110, 30);
		btnModificar.setLocation(15, 340);
		btnModificar.addActionListener(this);
		this.add(btnModificar);

		// Eliminar producto button 
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setSize(110, 30);
		btnEliminar.setLocation(160, 340);
		btnEliminar.addActionListener(this);
		this.add(btnEliminar);
		
		// Detalles producto button
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setSize(110, 30);
		btnCancelar.setLocation(310, 340);		
		btnCancelar.addActionListener(this);
		this.add(btnCancelar);
	}

	// Update productoVO on map
	private void updateProduct() {
		ProductoVO updateProducto = controller.getMapaProductos().get(this.producto.getId());
		try {
			if (cbProveedor.getSelectedItem().toString().equals("PROVEEDOR") || 
				txtNombre.getText() == "" ||
				txtPrecio.getText() == "")
				throw new Exception("Datos incompletos");
			
			updateProducto.setNombre(txtNombre.getText());
			updateProducto.setCaracteristicas(txtCaracteristicas.getText().replace(',','-'));
			updateProducto.setPrecio(Float.parseFloat(txtPrecio.getText()));
			updateProducto.setProveedor(cbProveedor.getSelectedItem().toString());

			controller.updateProducto(updateProducto);
			
			this.dispose();
			parentView.loadProductsOnTable();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(
				null, 
				"Datos incompletos", 
				"Error - Datos del Producto", 
				JOptionPane.ERROR_MESSAGE
			);
		}
	}

	// Delete productVO from map
	private void deleteProduct() {
		ProductoVO deleteProducto = controller.deleteProducto(this.producto);
		JOptionPane.showMessageDialog(
			null,
			String.format("Producto con ID [%s] eliminado", deleteProducto.getId().toString()),
			"",
			JOptionPane.INFORMATION_MESSAGE);
		this.dispose();
		parentView.loadProductsOnTable();
	}

	// Evento para evitar minimizar frame
	private WindowAdapter getWindowAdapter() {
		return new WindowAdapter() {			
			@Override
			public void windowIconified(WindowEvent we) {
				setState(JFrame.NORMAL);
				JOptionPane.showMessageDialog(
					null, 
					"Debe cerrar o cancelar",
					"Error - Minimizar",
					JOptionPane.ERROR_MESSAGE);
			}
		};
	}

	// Captura de fuentes de evento
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnModificar) { updateProduct(); }
		if (e.getSource() == btnEliminar) { deleteProduct(); }
		if (e.getSource() == btnCancelar) { this.dispose(); }
	}
}
