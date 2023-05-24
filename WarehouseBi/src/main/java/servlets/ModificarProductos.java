package servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.DAO.ModeloProducto;
import modelo.DAO.ModeloSeccion;
import modelo.DTO.Producto;
import modelo.DTO.Seccion;

/**
 * Servlet implementation class ModificarProductos
 */
@WebServlet("/ModificarProductos")
public class ModificarProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificarProductos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//SECCIONES
		ModeloSeccion ms = new ModeloSeccion();
		request.setAttribute("secciones", ms.visualizarSecciones());
		request.setAttribute("producto", ModeloProducto.cargarProducto(request.getParameter("id")));
		request.getRequestDispatcher("ModificarProductos.jsp").forward(request, response);
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Date fecha = null;
		try {
			fecha = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("caducidad"));
	
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Producto producto = new Producto();
		
		producto.setId(Integer.parseInt(request.getParameter("id")));
		producto.setNombre(request.getParameter("nombre"));
		producto.setCodigo(request.getParameter("codigo"));
		producto.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
		producto.setPrecio(Double.parseDouble(request.getParameter("precio")));
		
		producto.setCaducidad(fecha);
		
		int idSeccion = Integer.parseInt(request.getParameter("seccion"));
		producto.setSeccion(new ModeloSeccion().visualizarSeccion(idSeccion));
		
		ModeloProducto.modificarProducto(producto);

		response.sendRedirect("Productos");
		
	}

}
