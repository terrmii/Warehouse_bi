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
@WebServlet("/EliminarProductos")
public class EliminarProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EliminarProductos() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		int id = Integer.parseInt(request.getParameter("id"));

		if (ModeloProducto.comprobarStock(id) > 0) {
			ModeloProducto.eliminarCantidadMenosUno(id);
		} else {

			Boolean existe = ModeloSupermercado.existeProductoSupermercado(id);
			if (existe == true) {
				ModeloSupermercado.eliminarProductosSupermercado(id);
			} else {
				ModeloProducto.eliminarProducto(id);
			}
		}
		response.sendRedirect("Productos");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String boton = request.getParameter("boton");

		if (boton.equals("botonEliminarCodigos")) {

			String eliminarCodigos = request.getParameter("eliminarCodigos");
			String[] codigos = eliminarCodigos.split(",");

			boolean existe = true;

			//Recorrer arraylista de codigos para eliminar
			for (String codigo : codigos) {
				if (existe == true) {
					existe = ModeloProducto.existeCodigo(codigo);
				}
			}

			for (String codigo : codigos) {
				if (existe == true) {
					ModeloProducto.eliminarProductosSegunCodigos(codigo);
				}
			}

		}

		else if (boton.equals("botonEliminarCheckbox")) {

			String[] eliminarCheckbox = request.getParameterValues("eliminarCheckbox[]");

			for (String checkbox : eliminarCheckbox) {
				ModeloProducto.eliminarProductosSegunCodigos(checkbox);
			}

		}

		response.sendRedirect("Productos");

	}

}
