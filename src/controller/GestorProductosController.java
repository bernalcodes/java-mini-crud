package controller;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import model.ProductoVO;

public class GestorProductosController {
	private Map<UUID, ProductoVO> productosMap;
	
	// Constructor
	public GestorProductosController() {
		this.productosMap = new LinkedHashMap<UUID, ProductoVO>();
		readProductosFromCSV();
	}

	// Getter para mapa de productos
	public Map<UUID, ProductoVO> getMapaProductos() { return this.productosMap; }

	// Lectura de datos en DB
	private void readProductosFromCSV() {
		try (BufferedReader br = new BufferedReader(
										new FileReader(
											new File("src\\resources\\db.csv")))) {
			String line = br.readLine();
			line = br.readLine();
			
			while (line != null) {
				String[] attributes = line.split(",");
				ProductoVO producto = createProducto(attributes);
				this.productosMap.put(producto.getId(), producto);
				line = br.readLine();
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	// Creacion de ProductoVO a partir de CSV
	private static ProductoVO createProducto(String[] metadata) {
		UUID ID = UUID.fromString(metadata[0]);
		String nombre = metadata[1];
		String caracteristicas = metadata[2];
		float precio = Float.parseFloat(metadata[3]);
		String proveedor = metadata[4];
		return new ProductoVO(ID, nombre, caracteristicas, precio, proveedor);
	}

	// Guardado de cambios en DB
	private void saveDB() {
		File oldDB = new File("src\\resources\\db.csv");
		oldDB.delete();
		
		File newDB = new File("src\\resources\\db.csv");

		try {
			FileWriter dbWriter = new FileWriter(newDB, false);
			dbWriter.write("id,nombre,caracteristicas,precio,proveedor\n");

			for (UUID key : this.productosMap.keySet())
				dbWriter.write(this.productosMap.get(key).toCSV() + "\n");

			dbWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	// Creacion de reporte csv
	public void generarReporte() throws IOException {
		try {
			DateFormat dateFormatter = new SimpleDateFormat("yyy-MM-dd_HH-mm-ss");
			String currentDate = dateFormatter.format(new Date());
			File reportFile = new File(
				String.format(
					"src\\resources\\reporteInventario_%s.csv", 
					currentDate
				));
			
			reportFile.getParentFile().mkdirs();
			reportFile.createNewFile();
	
			FileWriter reportWriter = new FileWriter(reportFile);
			
			reportWriter.write("id,nombre,caracteristicas,precio,proveedor\n");

			for (UUID key : this.productosMap.keySet())
				reportWriter.write(this.productosMap.get(key).toCSV() + "\n");
	
			reportWriter.close();

			saveDB();
			
			if (Desktop.isDesktopSupported()) Desktop.getDesktop().open(reportFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
