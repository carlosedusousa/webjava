package model.exercicio06;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import util.JPA;

public class ClienteMonsterList extends LazyDataModel<ClienteMonster> {
	private static final long serialVersionUID = 1L;
	
	private List<ClienteMonster> people;
	
	@Override
	public List<ClienteMonster> load(int iniciaEm, int maximoPorPagina,
			String sortField, SortOrder sortOrder, Map<String, Object> filters) {
		EntityManager em = JPA.getEM();
		TypedQuery<ClienteMonster> query = em.createQuery("SELECT p from ClienteMonster p", ClienteMonster.class);
		query.setFirstResult(iniciaEm);
		query.setMaxResults(maximoPorPagina);
		this.people = query.getResultList();
		
		// total de resultados
        if(super.getRowCount() <= 0){
        	super.setRowCount(countPeople());
        }
		
        // total por página
        setPageSize(maximoPorPagina);
		return people;
	}
	
	@Override
    public Object getRowKey(ClienteMonster clienteMonster) {
        return clienteMonster.getId();
    }
	
	@Override
    public ClienteMonster getRowData(String clienteMonsterId) {
        Integer id = Integer.valueOf(clienteMonsterId);
 
        for (ClienteMonster clienteMonster: people) {
            if(id.equals(clienteMonster.getId())){
                return clienteMonster;
            }
        }
        
        return null;
    }
	
	private int countPeople() {
		EntityManager em = JPA.getEM();
        Query query = em.createQuery("select COUNT(p) from ClienteMonster p");
        Number result = (Number) query.getSingleResult();
 
        return result.intValue();
	}
}
