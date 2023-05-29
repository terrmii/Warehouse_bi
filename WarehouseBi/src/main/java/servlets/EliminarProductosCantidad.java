package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.DAO.ModeloProducto;
import modelo.DAO.ModeloSupermercado;
import modelo.DTO.Producto;

/**
 * Servlet implementation class EliminarProductosCantidad
 */
@WebServlet("/EliminarProductosCantidad")
public class EliminarProductosCantidad extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarProductosCantidad() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		
		if(ModeloProducto.comprobarStock(id) > 0) {
			ModeloProducto.eliminarCantidadMenosUno(id);
		}
		else {
			
			Boolean existe = ModeloSupermercado.existeProductoSupermercado(id);
			if(existe == true) {
				ModeloSupermercado.eliminarProductosSupermercado(id);
			}
			else {
				ModeloProducto.eliminarProducto(id);
			}
		}
		response.sendRedirect("Productos");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.getRequestDispatcher("Productos.jsp").forward(request, response);


	}

}
