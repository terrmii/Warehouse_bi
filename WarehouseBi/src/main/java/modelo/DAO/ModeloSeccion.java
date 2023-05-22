package modelo.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.DTO.Producto;
import modelo.DTO.Seccion;

public class ModeloSeccion {

	public Seccion visualizarSecciones(int id){
		
		Conector con = new Conector();
		Seccion seccion = new Seccion();
		
		try {
			PreparedStatement ps = con.getCon().prepareStatement("Select * from secciones where id = ?");
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				seccion.setId(rs.getInt("id"));
				seccion.setNombre(rs.getString("nombre"));
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return seccion;
	}
	
public ArrayList<Seccion> visualizarSecciones(){
		
		Conector con = new Conector();
		
		ArrayList<Seccion> secciones = new ArrayList<Seccion>();
		con.conectar();
		
		try {
			PreparedStatement ps = con.getCon().prepareStatement("Select * from secciones");
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
				Seccion seccion = new Seccion();
				seccion.setId(rs.getInt(1));
				seccion.setNombre(rs.getString(2));
				
				secciones.add(seccion);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return secciones;
		
	}
}
