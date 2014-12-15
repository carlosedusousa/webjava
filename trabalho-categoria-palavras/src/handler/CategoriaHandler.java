package handler;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelos.Categoria;
import modelos.Palavra;

@WebServlet("/categorias")
public class CategoriaHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// O EntityManager é reponsável por alterar os estados
	private static EntityManager em;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("trabalho");
		em = emf.createEntityManager();
	}

	// Lista todos as categorias
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		TypedQuery<Categoria> categoriasQuery = em.createQuery(
				"SELECT c FROM Categoria c", Categoria.class);
		List<Categoria> categorias = categoriasQuery.getResultList();

		request.setAttribute("categorias", categorias);

		RequestDispatcher rd = request.getRequestDispatcher("jsp/listarCategorias.jsp");
		rd.forward(request, response);
	}

	// Salva categoria e suas palavras similares.
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String categoria = request.getParameter("categoria");
		String palavras = request.getParameter("texto");
		Categoria c = new Categoria ();
		c.setCategoria(categoria);
		
		for (String p : palavras.split(",")) {
			p.replaceAll("\\s+", " ");
			if (!p.equals(" ")) {

				Palavra palavra = new Palavra(p);
				em.getTransaction().begin();
				em.persist(palavra);
				em.getTransaction().commit();
				c.addPalavra(palavra);
			
			}

		}

		em.getTransaction().begin();
		em.persist(c);
		em.getTransaction().commit();
		
		TypedQuery<Categoria> categoriasQuery = em.createQuery(
				"SELECT c FROM Categoria c", Categoria.class);
		List<Categoria> categorias = categoriasQuery.getResultList();

		request.setAttribute("categorias", categorias);

		RequestDispatcher rd = request.getRequestDispatcher("jsp/listarCategorias.jsp");
		rd.forward(request, response);

	}

}
