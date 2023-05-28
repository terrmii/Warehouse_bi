package modelo.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.DTO.Seccion;
import modelo.DTO.Supermercado;

public class ModeloSupermercado {
	
	public Supermercado visualizarSupermercado(int id) {
		
		Conector con = new Conector();
		Supermercado supermercado = new Supermercado();
		
		try {
			PreparedStatement ps = con.getCon().prepareStatement("Select * from supermercados where id = ? ");
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				supermercado.setId(rs.getInt("id"));
				supermercado.setNombre(rs.getString("nombre"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return supermercado;
	}
	
public ArrayList<Supermercado> visualizarSupermercados(){
		
		Conector con = new Conector();
		
		ArrayList<Supermercado> supermercados = new ArrayList<Supermercado>();
		con.conectar();
		
		try {
			PreparedStatement ps = con.getCon().prepareStatement("Select * from supermercados");
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
				Supermercado supermercado = new Supermercado();
				supermercado.setId(rs.getInt(1));
				supermercado.setNombre(rs.getString(2));
				
				supermercados.add(supermercado);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return supermercados;
		
	}
	
/*
 * Metodo para leer la id segun un codigo para poder insertar los productos
 */
public static int leerIdSegunCodigo(String codigo) {
	Conector con = new Conector();
	con.conectar();
	int id = 0;
	
	try {
		PreparedStatement ps = con.getCon().prepareStatement("Select id from productos where codigo = ?");
		ps.setString(1, codigo);
		
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			id = rs.getInt("id");
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return id;
}


public static void insertarProductoSuper(Supermercado supermercado, int id) {
	Conector con = new Conector();
	con.conectar();
	
	try {
		PreparedStatement ps = con.getCon().prepareStatement("INSERT INTO productos_supermercados (id_producto, id_supermercado) VALUES (?, ?)");
		ps.setInt(1, id);
		ps.setInt(2, supermercado.getId());
		
		ps.execute();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	con.cerrar();
	}


}
