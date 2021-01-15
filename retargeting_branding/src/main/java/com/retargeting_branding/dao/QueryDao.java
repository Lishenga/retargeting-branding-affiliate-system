package com.retargeting_branding.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class QueryDao {
	
    @Autowired
	private EntityManager em;

	// @SuppressWarnings("unchecked")
	// public List<Advert> getAdverts() {
	// 	return em.createNamedStoredProcedureQuery("getAdverts").getResultList();
	// }

	public List<?>getAdverts(String deviceType, String country) {
		return em.createNamedStoredProcedureQuery("getAdverts").setParameter("deviceType", deviceType).setParameter("country", country).getResultList();
	}
}