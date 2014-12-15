package exercicio02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exercicio02.modelos.Produto;

@WebServlet("/exercicio02/produto")
public class ProdutoCadastro extends HttpServlet {
	private static final long serialVersionUID = -3652240481373084306L;
	
	private EntityManager em;
	
	public void init() throws ServletException {
		super.init();
		
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("servlets-categorias");
		em = emf.createEntityManager();
		
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Cadastro do Produto</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>Preencha o formulário abaixo e clique em enviar</h1>");
		out.println("<form method=\"POST\" action=\"/servlets-categorias/exercicio02/produto\">");
		out.println("<input type=\"text\" name=\"nome\" placeholder=\"Nome\" /><br/><br/>");
		out.println("<input type=\"text\" name=\"valor\" placeholder=\"Valor\" /><br/><br/>");
		out.println("<input type=\"submit\" value=\"Enviar\">");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Produto p = new Produto();
		p.setNome(req.getParameter("nome"));
		p.setValor(Double.parseDouble(req.getParameter("valor")));
		
		em.getTransaction().begin();
		em.persist(p);
		em.getTransaction().commit();
		
		resp.sendRedirect("/servlets-categorias/exercicio02/produtos");
	}

}









