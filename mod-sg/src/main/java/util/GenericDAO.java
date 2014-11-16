package util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import org.apache.log4j.Logger;
 
public abstract class GenericDAO<T, ID extends Serializable> {
     
    @PersistenceContext
    private EntityManager em;
    
    private Logger logger = Logger.getLogger(getClass());
 
    private Class<T> entityClass;
 
    public GenericDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
        logger.info(entityClass);
    }
 

	public T save(T entity) {
		logger.info("Insertando Entity : "+entity);
        try {
        	
        	if (entity instanceof Auditable) {
				((Auditable) entity).setRegFecInsert(HelperFecha.getActual());
			}
        	
			em.persist(entity);
		} catch (Exception e) {
			logger.info(e.getMessage());
			throw new RuntimeException("Error Insert",e);
		}
        System.out.println(entity);
        return entity;
    }
 

	public boolean delete(T entity) {
    	try {
    		logger.info("Borrando Entity : "+entity);
    		T entityToBeRemoved = em.merge(entity);
            em.remove(entityToBeRemoved);
            return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return false;
    }
 

	public T update(T entity) {
    	logger.info("Actualizando Entity : "+entity);
        try {
        	
        	if (entity instanceof Auditable) {
				((Auditable) entity).setRegFecUpdate(HelperFecha.getActual());
			}
        	
			return em.merge(entity);
		} catch (Exception e) {
			throw new RuntimeException("Error Update",e);
		}
    }
 

	public T find(ID entityID) {
    	logger.info("Buscando "+entityClass.getSimpleName()+" por ID:"+entityID);
        return em.find(entityClass, entityID);
    }
 
    // Using the unchecked because JPA does not have a
    // em.getCriteriaBuilder().createQuery()<T> method

	@SuppressWarnings({ "unchecked", "rawtypes" })
    public List<T> findAll() {
    	logger.info("Buscando todos los registros de "+entityClass.getSimpleName());
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return em.createQuery(cq).getResultList();
    }
 
    // Using the unchecked because JPA does not have a
    // ery.getSingleResult()<T> method

    @SuppressWarnings("unchecked")
    public T findOneResult(String namedQuery, Map<String, Object> parameters) {
        T result = null;
        logger.info("Buscando un registro de "+entityClass.getSimpleName());
        logger.info("Query de busqueda "+namedQuery);
        try {
            //Query query = em.createNamedQuery(namedQuery);
            Query query = em.createQuery(namedQuery, entityClass);
 
            // Method that will populate parameters if they are passed not null and empty
            if (parameters != null && !parameters.isEmpty()) {
                populateQueryParameters(query, parameters);
            }
 
            result = (T) query.getSingleResult();
 
        }catch (NoResultException e) {
            logger.info("No hay registros para la query "+namedQuery);
            result = null;
        }catch (Exception e) {
            logger.info("Error buscando un resultado", e);
        }
 
        return result;
    }
    
    // Using the unchecked because JPA does not have a
    // ery.getSingleResult()<T> method

    @SuppressWarnings("unchecked")
    public List<T> findManyResult(String namedQuery, Map<String, Object> parameters) {
        List<T> results = new ArrayList<>();
        logger.info("Buscando un registro de "+entityClass.getSimpleName());
        logger.info("Query de busqueda "+namedQuery);
        try {
            //Query query = em.createNamedQuery(namedQuery);
            Query query = em.createQuery(namedQuery, entityClass);
 
            // Method that will populate parameters if they are passed not null and empty
            if (parameters != null && !parameters.isEmpty()) {
                populateQueryParameters(query, parameters);
            }
 
            results = (List<T>) query.getResultList();
 
        }catch (NoResultException e) {
            logger.info("No hay registros para la query "+namedQuery);
        }catch (Exception e) {
            logger.info("Error buscando un resultado", e);
        }
 
        return results;
    }
 
    private void populateQueryParameters(Query query, Map<String, Object> parameters) {
 
        for (Entry<String, Object> entry : parameters.entrySet()) {
        	logger.info("Aplicando parametros a la Query "+entry.getKey()+" -> "+entry.getValue());
            query.setParameter(entry.getKey(), entry.getValue());
        }
    }
}
