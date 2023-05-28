package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ListIterator;

import javax.servlet.RequestDispatcher;
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
        String orden = request.getParameter("orden");
        
        ArrayList<Producto> productos = mp.visualizarProductos();
        
        if (orden == null || !orden.equals("desc")) {
            orden = "";
        }
        
        if (orden.equals("desc")) {
            Collections.sort(productos, Comparator.comparing(Producto::getCodigo).reversed());
        } else if(orden.equals("asc")){
            Collections.sort(productos, Comparator.comparing(Producto::getCodigo));
        }
        
        request.setAttribute("productos", productos);
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

		
		String boton = request.getParameter("boton");
		if(boton.equals("buscar")) {
		
			/*
			 * Recoger el "name" de jsp de busqueda y pasarlo a string para poder compararlo
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
		}
		else if(boton.equals("precio")) {
			
			double preciomin = 0;
			double preciomax = 9999;		
			
			if(request.getParameter("preciomin") != "") {
				preciomin = Double.parseDouble(request.getParameter("preciomin")); 
			}
			
			if(request.getParameter("preciomax") != "") {
				preciomax = Double.parseDouble(request.getParameter("preciomax"));
			}
			
			ListIterator<Producto> precioProductos = productos.listIterator();

			while(precioProductos.hasNext()) {
				
				Producto contienePrecio = precioProductos.next();
				
				if(!(contienePrecio.getPrecio() >= preciomin) || !(contienePrecio.getPrecio() <= preciomax)) {
					precioProductos.remove();
				}

			}
			
		}
		request.setAttribute("productos", productos);
		request.getRequestDispatcher("Productos.jsp").forward(request, response);
	}

}