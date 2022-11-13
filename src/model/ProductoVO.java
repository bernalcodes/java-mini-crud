package model;

import java.util.UUID;

public class ProductoVO {
	private UUID id;
	private String nombre;
	private String caracteristicas;
	private float precio;
	private String proveedor;
	
	public ProductoVO(){}

	public ProductoVO(UUID id, String nombre, String caracteristicas, float precio, String proveedor) {
		this.id = id;
		this.nombre = nombre;
		this.caracteristicas = caracteristicas;
		this.precio = precio;
		this.proveedor = proveedor;
	}

	public UUID getId() { return id;}

	public void setId(UUID id) { this.id = id; }

	public String getNombre() { return nombre; }

	public void setNombre(String nombre) { this.nombre = nombre; }

	public String getCaracteristicas() { return caracteristicas; }

	public void setCaracteristicas(String caracteristicas) { this.caracteristicas = caracteristicas; }

	public float getPrecio() { return precio; }

	public void setPrecio(float precio) { this.precio = precio; }

	public String getProveedor() { return proveedor; }
	
	public void setProveedor(String proveedor) { this.proveedor = proveedor; }

	@Override
	public String toString() {
		return "ProductoVO [\n\tid = " + id + ",\n\tnombre = " + nombre + 
							",\n\tcaracteristicas = " + caracteristicas + 
							",\n\tprecio = " + precio + ",\n\tproveedor=" + 
							proveedor + "\n]";
	}

	public String toCSV() { return String.format("%s,%s,%s,%s,%s", id, nombre, caracteristicas, precio, proveedor);}
}
