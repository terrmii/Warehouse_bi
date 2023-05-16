package modelo.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.DTO.Productos;

public class ModeloProducto {
	

	
	public ArrayList<Productos> visualizarProductos(){
		
		Conector con = new Conector();
		
		ArrayList<Productos> productos = new ArrayList<Productos>();
		con.conectar();
		try {
			PreparedStatement ps = con.getCon().prepareStatement("Select * from productos");
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Productos producto = new Productos();
				producto.setId(rs.getInt(1));
				producto.setCodigo(rs.getString(2));
				producto.setNombre(rs.getString(3));
				producto.setCantidad(rs.getInt(4));
				producto.setPrecio(rs.getDouble(5));
				producto.setCaducidad(rs.getDate(6));
				producto.setId_seccion(rs.getInt(7));
				
				productos.add(producto);
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return productos;
		
	}
}
