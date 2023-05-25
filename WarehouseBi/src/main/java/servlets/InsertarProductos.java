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
import modelo.DAO.ModeloSupermercado;
import modelo.DTO.Producto;
import modelo.DTO.Seccion;
import modelo.DTO.Supermercado;

/**
 * Servlet implementation class InsertarProductos
 */
@WebServlet("/InsertarProductos")
public class InsertarProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertarProductos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// LLama al jsp del Insertar
		ModeloSeccion ms = new ModeloSeccion();
		ModeloSupermercado msu = new ModeloSupermercado();
		
		request.setAttribute("secciones", ms.visualizarSecciones());
		request.setAttribute("supermercados", msu.visualizarSupermercados());
		request.getRequestDispatcher("InsertarProductos.jsp").forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Date fecha = null;
		try {
			fecha = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("fecha"));
	
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Producto producto = new Producto();
		producto.setCodigo(request.getParameter("codigo"));
		producto.setNombre(request.getParameter("nombre"));
		producto.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
		producto.setPrecio(Double.parseDouble(request.getParameter("precio")));
		producto.setCaducidad(fecha);

		Seccion seccion = new Seccion();
		
		seccion.setId(Integer.parseInt(request.getParameter("secciones")));
		producto.setSeccion(seccion);
		
		Supermercado supermercado = new Supermercado();
		
		supermercado.setId(Integer.parseInt(request.getParameter("nomnbreSuper")));
		
		
		
//		producto.setId_seccion(Integer.parseInt(request.getParameter("secciones")));
		
		boolean existe = ModeloProducto.existeCodigo(producto.getCodigo());
		
		Date hoy = new Date();
		if(existe == false && producto.getPrecio() > 0 && producto.getCantidad() > 0 && producto.getCaducidad().after(hoy)) {
			ModeloProducto.insertarProducto(producto);
			ModeloSupermercado.insertarProductoSuper(supermercado, ModeloSupermercado.leerIdSegunCodigo(producto.getCodigo()));
			response.sendRedirect("Productos");
		}
		else {
			request.setAttribute("error", "Datos erroneos");
			doGet(request, response);
		}

	}

}
