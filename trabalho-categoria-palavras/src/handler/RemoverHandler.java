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

@WebServlet("/remover")
public class RemoverHandler extends HttpServlet {

	private static final long serialVersionUID = -869567386185670046L;

	private static EntityManager em;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("trabalho");
		em = emf.createEntityManager();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		TypedQuery<Categoria> categoriasQuery = em.createQuery(
				"SELECT c FROM Categoria c", Categoria.class);
		List<Categoria> categorias = categoriasQuery.getResultList();

		request.setAttribute("categorias", categorias);

		RequestDispatcher rd = request
				.getRequestDispatcher("jsp/listarCategorias.jsp");
		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		if (request.getParameter("categoria").equals("todos")) {

			TypedQuery<Categoria> categoriasQuery = em.createQuery(
					"SELECT c FROM Categoria c", Categoria.class);
			List<Categoria> categorias = categoriasQuery.getResultList();

			for (Categoria c : categorias) {
				em.getTransaction().begin();
				em.remove(c);
				em.getTransaction().commit();
			}

		} else {

			Integer id = Integer.parseInt(request.getParameter("categoria"));
			Categoria c = em.find(Categoria.class, id);

			c.setPalavras(null);
			em.getTransaction().begin();
			em.persist(c);
			em.getTransaction().commit();

			em.getTransaction().begin();
			em.remove(c);
			em.getTransaction().commit();

		}
		response.sendRedirect("/trabalho/categorias");

	}

}
