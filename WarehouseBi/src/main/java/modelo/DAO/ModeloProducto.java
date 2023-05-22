package modelo.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

import modelo.DTO.Producto;
import modelo.DTO.Seccion;

public class ModeloProducto {
	
	public ArrayList<Producto> visualizarProductos(){
		
		Conector con = new Conector();
		
		ArrayList<Producto> productos = new ArrayList<Producto>();
		con.conectar();
		
		ModeloSeccion ms = new ModeloSeccion();
		try {
			PreparedStatement ps = con.getCon().prepareStatement("Select * from productos");
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
				Producto producto = new Producto();
				producto.setId(rs.getInt(1));
				producto.setCodigo(rs.getString(2));
				producto.setNombre(rs.getString(3));
				producto.setCantidad(rs.getInt(4));
				producto.setPrecio(rs.getDouble(5));
				producto.setCaducidad(rs.getDate(6));
				producto.setSeccion(ms.visualizarSecciones(rs.getInt(7)));
				
				productos.add(producto);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return productos;
		
	}
	
	public static void insertarProducto(Producto producto) {
		Conector con = new Conector();
		con.conectar();
		Seccion seccion;
		try {
			PreparedStatement ps = con.getCon().prepareStatement("INSERT INTO productos (codigo, nombre, cantidad, precio, caducidad, id_seccion) VALUES (?,?,?,?,?,?)");
			ps.setString(1, producto.getCodigo());
			ps.setString(2, producto.getNombre());
			ps.setInt(3, producto.getCantidad());
			ps.setDouble(4, producto.getPrecio());
			ps.setDate(5, new java.sql.Date(producto.getCaducidad().getTime()));
			ps.setInt(6, producto.getSeccion().getId());
			
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		con.cerrar();
	}
	
	public static boolean existeCodigo(String codigo) {
		
		boolean existe = false;
		
		Conector con = new Conector();
		con.conectar();
		
		try {
			PreparedStatement ps = con.getCon().prepareStatement("Select codigo from productos where codigo = ?");
			ps.setString(1, codigo);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				existe = true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return existe;
		
	}
}
