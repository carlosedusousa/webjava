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

import exercicio02.modelos.Produto;

@WebServlet("/exercicio02/produtos")
public class ProdutoListar extends HttpServlet {
	private static final long serialVersionUID = -2106004038931852084L;
	
	private EntityManager em;

	public void init() throws ServletException {
		super.init();

		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("servlets-categorias");
		em = emf.createEntityManager();

	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		TypedQuery<Produto> produtosQuery = em.createQuery("SELECT p FROM Produto p", Produto.class);
		List<Produto> produtos = produtosQuery.getResultList();
		
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Listagem de Produtos</title>");
		out.println("</head>");
		out.println("<body>");
		
		out.println("<a href=\"/servlets-categorias/exercicio02/produto\">Adicionar produto</a>");
		
		for(Produto p : produtos) {
			out.println("<h1>" + p.getNome() + "</h1>");
			out.println("<em>" + p.getValor() + "</em> <br/>");
			out.println("<hr/>");
		}
		
		out.println("</body>");
		out.println("</html>");
	}

}
