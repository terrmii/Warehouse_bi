package modelo.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.DTO.Producto;

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
				producto.setSeccion(ms.visualizarSeccion(rs.getInt(7)));
				
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
	
	//Para modificar
	
	/*
	 * Cargar producto para pasar a modificar
	 */
	
	public static Producto cargarProducto(String id1) {
		
		Conector con = new Conector();
		con.conectar();
		
		Producto producto = new Producto();
		
		try {
			PreparedStatement ps = con.getCon().prepareStatement("SELECT * from productos where id = ?");
			ps.setString(1, id1);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
			
			producto.setId(rs.getInt(1));
			producto.setCodigo(rs.getString(2));
			producto.setNombre(rs.getString(3));
			producto.setCantidad(rs.getInt(4));
			producto.setPrecio(rs.getDouble(5));
			producto.setCaducidad(rs.getDate(6));
			producto.setSeccion(new ModeloSeccion().visualizarSeccion(rs.getInt(7)));
			
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return producto;
		
	}
	
	
	public static void modificarProducto(Producto producto) {
		
		Conector con = new Conector();
		con.conectar();
		
		try {
			PreparedStatement ps = con.getCon().prepareStatement("UPDATE productos set codigo = ?, nombre = ?, cantidad = ?, precio = ?, caducidad = ?, id_seccion = ? WHERE id = ?");
			
			ps.setString(1, producto.getCodigo());
			ps.setString(2, producto.getNombre());
			ps.setInt(3, producto.getCantidad());
			ps.setDouble(4, producto.getPrecio());
			ps.setDate(5, new java.sql.Date(producto.getCaducidad().getTime()));
			ps.setInt(6, producto.getSeccion().getId());
			ps.setInt(7, producto.getId());
			
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
