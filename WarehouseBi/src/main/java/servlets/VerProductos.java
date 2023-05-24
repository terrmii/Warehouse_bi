package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.DAO.ModeloProducto;
import modelo.DTO.Producto;

/**
 * Servlet implementation class Productos
 */
@WebServlet("/Productos")
public class VerProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerProductos() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		ModeloProducto mp = new ModeloProducto();
		request.setAttribute("productos", mp.visualizarProductos());
		request.getRequestDispatcher("Productos.jsp").forward(request, response);	

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ModeloProducto mp = new ModeloProducto();
		
		/*
		 * Leer los productos
		 * */
		ArrayList<Producto> productos = new ArrayList<Producto>();
		
		productos = mp.visualizarProductos();

		/*
		 * Recoger el "name" de jsp de busqueda
		 */
		String busqueda = request.getParameter("buscar");
			
		ListIterator<Producto> buscarProductos = productos.listIterator();
		
		
		while(buscarProductos.hasNext()) {
			
			Producto compararProducto = buscarProductos.next();
			
			/*
			 * Distinto del codigo Y distinto del nombre 
			 */
			if(!compararProducto.getCodigo().contains(busqueda) && !compararProducto.getNombre().contains(busqueda)) {
				buscarProductos.remove();
			}
			
		}
		
		request.setAttribute("productos", productos);
		request.getRequestDispatcher("Productos.jsp").forward(request, response);
	}

}