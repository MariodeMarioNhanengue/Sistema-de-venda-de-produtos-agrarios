package com.ujc.students.dto;


import java.util.*;
import java.io.Serializable;
import java.lang.reflect.*;


import jakarta.persistence.*;

public abstract class AbstractDto<T, PK extends Serializable> {
	    @SuppressWarnings("unchecked")
	    private final Class<T> entityClass = (Class<T>) 
	        ( (ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	    
	    @PersistenceContext
	    private EntityManager entityManager;

	    protected EntityManager getEntityManager() {
	        return entityManager;
	    }
	    
	    public void save(T entity) {     
	        // código omitido
	    }
	    
	    public void update(T entity) {            
	        // código omitido
	    }
	    
	    public void delete(PK id) {            
	        // código omitido
	    }
	    
	    public T findById(PK id) {            
	     return entityManager.find(entityClass, id);
	    }
	    
	    public List<T> findAll() {
			return entityManager
					.createQuery("from"+ entityClass.getSimpleName(),entityClass)
					.getResultList();
	       
	    }    
	    
	    protected List<T> createQuery(String jpql, Object... params) {            
	        TypedQuery<T> query =entityManager.createQuery(jpql, entityClass);
	        for (int i = 0; i < params.length; i++) {
				query.setParameter(i+1, params[i]);
			}
			return query.getResultList();
	    }
	}