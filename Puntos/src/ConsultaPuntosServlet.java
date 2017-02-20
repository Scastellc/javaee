import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * By: Salva Castell
 * 
 */

@WebServlet("/consultapuntos")
public class ConsultaPuntosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ConsultaPuntosServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String puntos = "0";
		
		Cookie[] cookies = request.getCookies();
		
		// Comprobamos que no esta vacio el array
		if(cookies != null){
			// Le indicamos que c guarde el valor del array
			for(Cookie c: cookies){
				// Si hay una cookie con ese nombre lo guardaremos en puntos
				if(c.getName().equals("cookiepuntos")){
					puntos = c.getValue();
					// Pasamos a interger
					int p = Integer.parseInt(puntos);
					
					// Sumamos uno
					p++;
					
					// Mandamos como string
					puntos = String.valueOf(p);
				}
			}
		}
		
		Cookie miCookie = new Cookie("cookiepuntos",puntos);
		miCookie.setMaxAge(604800);
		response.addCookie(miCookie);
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<head>");
		out.println("<title>Puntos</title");
		out.println("<meta charset='utf-8'>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>Puntos</h1>");
		out.println("<p>Cada vez que recarges la pagina recibiras 1 punto</p>");
		out.println("Ahora tienes: " + puntos + " puntos.");
		out.println("</body>");
		out.println("</html>");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
