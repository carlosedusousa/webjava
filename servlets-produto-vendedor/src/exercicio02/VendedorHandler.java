package exercicio02;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exercicio02.modelos.Vendedor;

@WebServlet("/exercicio02/vendedores")
public class VendedorHandler extends HttpServlet {
	private static final long serialVersionUID = -6325856154367987779L;

	private EntityManager em;

	public void init() throws ServletException {
		super.init();

		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("servlets-categorias");
		em = emf.createEntityManager();

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Vendedor v = new Vendedor();
		v.setNome(req.getParameter("nome"));
		v.setIdade(Integer.parseInt(req.getParameter("idade")));
		v.setEndereco(req.getParameter("endereco"));
		
		em.getTransaction().begin();
		em.persist(v);
		em.getTransaction().commit();
		
		resp.sendRedirect("/servlets-categorias/exercicio02/vendedores");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		TypedQuery<Vendedor> vendedoresQuery = em.createQuery("SELECT v FROM Vendedor v", Vendedor.class);
		List<Vendedor> vendedores = vendedoresQuery.getResultList();
		
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Listagem de Vendedores</title>");
		out.println("</head>");
		out.println("<body>");
		
		out.println("<a href=\"/servlets-categorias/vendedores/cadastro.html\">Adicionar vendedor</a>");
		
		for(Vendedor v : vendedores) {
			out.println("<h1>" + v.getNome() + "</h1>");
			out.println("<em>" + v.getIdade() + "</em> <br/>");
			out.println("<p>" + v.getEndereco() + "</p>");
			out.println("<hr/>");
		}
		
		out.println("</body>");
		out.println("</html>");
		
	}

}
