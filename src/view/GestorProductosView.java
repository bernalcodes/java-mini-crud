package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.UUID;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controller.GestorProductosController;
import model.ProductoVO;

public class GestorProductosView extends JFrame implements ActionListener {
	private final GestorProductosController controller;
	private JLabel lblID, lblNombre, lblPrecio, lblProveedor, lblCaracteristicas;
	private JTextField txtID, txtNombre, txtPrecio;
	private JTextArea txtCaracteristicas;
	private JButton btnID, btnIngresar, btnCargar, btnLimpiar, btnInforme, btnDetalles;
	private JComboBox<String> cbProveedor;
	private JScrollPane txtCaracteristicasScroll, tblProductosScroll;
	private JTable tblProductos;
	private DefaultTableModel tblModel;

	// Class constructor
	public GestorProductosView() {
		super("Gestor de inventario");
		controller = new GestorProductosController();
		setLayout(null);
		loadFields();
		loadButtons();
		loadTable();
		setSize(500, 700);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		generateRandomID();
	}

	// Creating and adding form fields
	private void loadFields() {
		// ID label
		lblID = new JLabel("ID", SwingConstants.CENTER);
		lblID.setBounds(20, 20, 50, 30);
		lblID.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		this.add(lblID);
		
		// ID input
		txtID = new JTextField();
		txtID.setBounds(140, 20, 325, 30);
		txtID.setBorder(BorderFactory.createCompoundBorder(
							BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), 
							BorderFactory.createEmptyBorder(0, 5, 0, 0)));
		txtID.setBackground(Color.WHITE);
		txtID.setEditable(false);
		this.add(txtID);
		
		// Name label
		lblNombre = new JLabel("Nombre", SwingConstants.CENTER);
		lblNombre.setBounds(20, 70, 100, 30);
		lblNombre.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		this.add(lblNombre);
		
		// Name input
		txtNombre = new JTextField();
		txtNombre.setBounds(140, 70, 325, 30);
		txtNombre.setBorder(BorderFactory.createCompoundBorder(
								BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), 
								BorderFactory.createEmptyBorder(0, 5, 0, 0)));
		this.add(txtNombre);

		// Price label
		lblPrecio = new JLabel("Precio", SwingConstants.CENTER);
		lblPrecio.setBounds(20, 120, 100, 30);
		lblPrecio.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		this.add(lblPrecio);
		
		// Price input
		txtPrecio = new JTextField();
		txtPrecio.setBounds(140, 120, 325, 30);
		txtPrecio.setBorder(BorderFactory.createCompoundBorder(
								BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), 
								BorderFactory.createEmptyBorder(0, 5, 0, 0)));
		txtPrecio.setNavigationFilter(null);
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
		lblProveedor.setBounds(20, 170, 200, 30);
		lblProveedor.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		this.add(lblProveedor);
		
		// Provider comboBox
		cbProveedor = new JComboBox<>(new String[] { "PROVEEDOR", "ALCATEL", "HUAWEI", "SOLAR WINDS", "ZTE" });
		cbProveedor.setBounds(240,170,225,30);
		
		DefaultListCellRenderer listCellRenderer = new DefaultListCellRenderer();
		listCellRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
		cbProveedor.setRenderer(listCellRenderer);
		
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
		txtCaracteristicasScroll.setSize(325, 100);
		txtCaracteristicasScroll.setLocation(140, 220);
		
		// Caracteristicas textArea
		txtCaracteristicas = new JTextArea();
		txtCaracteristicas.setLineWrap(true);
		txtCaracteristicas.setWrapStyleWord(true);
		txtCaracteristicas.setBorder(BorderFactory.createCompoundBorder(
										txtCaracteristicas.getBorder(),
										BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		txtCaracteristicasScroll.setViewportView(txtCaracteristicas);
		this.getContentPane().add(txtCaracteristicasScroll);
	}

	// Buttons loader
	private void loadButtons() {
		// UUID generator button
		btnID = new JButton();
		btnID.setIcon(new ImageIcon(getClass().getResource("../resources/randomize.png")));
		btnID.setSize(40, 30);
		btnID.setLocation(80, 20);
		btnID.addActionListener(this);
		this.add(btnID);
		
		// Limpiar campos button 
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setSize(100, 30);
		btnLimpiar.setLocation(20, 270);
		btnLimpiar.addActionListener(this);
		this.add(btnLimpiar); 
		
		// Ingresar producto button 
		btnIngresar = new JButton("Registrar");
		btnIngresar.setSize(130, 30);
		btnIngresar.setLocation(20, 340);
		btnIngresar.addActionListener(this);
		this.add(btnIngresar);
		
		// Cargar tabla button 
		btnCargar = new JButton("Cargar");
		btnCargar.setSize(130, 30);
		btnCargar.setLocation(175, 340);
		btnCargar.addActionListener(this);
		this.add(btnCargar);
		
		// Detalles producto button
		btnDetalles = new JButton("Detalles");
		btnDetalles.setSize(130, 30);
		btnDetalles.setLocation(335, 340);		
		btnDetalles.addActionListener(this);
		this.add(btnDetalles);
		
		// Generar informe button
		btnInforme = new JButton("Generar reporte de inventario");
		btnInforme.setSize(445, 30);
		btnInforme.setLocation(20, 610);
		btnInforme.addActionListener(this);
		this.add(btnInforme);
	}
	
	// Generar ID aleatorio
	private void generateRandomID(){ txtID.setText(UUID.randomUUID().toString()); }

	// Clear fields
	private void clearForm() {
		generateRandomID();
		txtNombre.setText("");
		txtPrecio.setText("");
		cbProveedor.setSelectedItem("PROVEEDOR");
		txtCaracteristicas.setText("");
	}

	// Renderizar tabla
	private void loadTable() {
		// ScrollPane para tabla
		tblProductosScroll = new JScrollPane();
		tblProductosScroll.setSize(445, 200);
		tblProductosScroll.setLocation(20, 390);
		
		// Tabla y detalles
		tblProductos = new JTable(
			new DefaultTableModel(
				new String[] { "ID", "Nombre" },
				0));
		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
		cellRenderer.setHorizontalAlignment(JLabel.CENTER);
		tblProductos.setDefaultRenderer(Object.class, cellRenderer);
		tblProductos.getTableHeader().setReorderingAllowed(false);

		tblProductosScroll.setViewportView(tblProductos);
		this.getContentPane().add(tblProductosScroll);
	}

	// Mostrar productos en tabla desde db.csv
	protected void loadProductsOnTable() {
		tblModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		tblModel.addColumn("ID");
		tblModel.addColumn("Nombre");
		for (UUID key : controller.getMapaProductos().keySet()) {
			Object[] tblRow = new Object[2];
			tblRow[0] = controller.getMapaProductos().get(key).getId().toString();
			tblRow[1] = controller.getMapaProductos().get(key).getNombre();

			tblModel.addRow(tblRow);
		}

		tblProductos.setModel(tblModel);
		tblModel.fireTableDataChanged();
	}

	// Agregar producto a db.csv
	private void addProduct() {
		try {
			if (cbProveedor.getSelectedItem().toString().equals("PROVEEDOR") ||
				txtNombre.getText() == "" ||
				txtPrecio.getText() == "")
				throw new Exception("Datos incompletos");
			ProductoVO producto = new ProductoVO(
				UUID.fromString(txtID.getText()),
				txtNombre.getText(),
				txtCaracteristicas.getText().replace(',','-'),
				Float.parseFloat(txtPrecio.getText()),
				cbProveedor.getSelectedItem().toString()
			);
			controller.getMapaProductos().put(producto.getId(), producto);
			loadProductsOnTable();
			clearForm();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(
				null, 
				"Datos incompletos", 
				"Error - Datos del Producto", 
				JOptionPane.ERROR_MESSAGE
			);
		}
	}

	// Abre reporte en CSV despues de creado
	private void openReport() {
		try {
			if (tblProductos.getModel().getRowCount() == 0) {
				String[] options = {"Aceptar", "Cancelar"};
				int usrOpt = JOptionPane.showOptionDialog(
					null, 
					"Reporte generado con datos previamente almacenados",
					"Aviso - Reporte",
					JOptionPane.NO_OPTION,
					JOptionPane.WARNING_MESSAGE,
					null,
					options,
					"default");
				if (usrOpt == 0) controller.generarReporte();
			} else controller.generarReporte();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(
				null, 
				"Error al generar reporte. Intente nuevamente.", 
				"Error - Reporte", 
				JOptionPane.ERROR_MESSAGE
			);
		}
	}

	// Captura de fuentes de evento
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnID) { generateRandomID(); }
		if (e.getSource() == btnIngresar) { addProduct(); }
		if (e.getSource() == btnCargar) { loadProductsOnTable(); }
		if (e.getSource() == btnLimpiar) { clearForm(); }
		if (e.getSource() == btnDetalles) {
			try {
				UUID productoID = UUID.fromString(
					(String)tblProductos.getValueAt(
						tblProductos.getSelectedRow(), 0));
				
				new DetallesProductoView(controller, productoID, this);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(
					null,
					"No ha seleccionado un producto",
					"Error - Productos",
					JOptionPane.ERROR_MESSAGE);
			}
		}
		if (e.getSource() == btnInforme) { openReport(); }
	}
}
