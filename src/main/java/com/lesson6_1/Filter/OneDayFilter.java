package com.lesson6_1.Filter;

import com.lesson6_1.model.Flight;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.*;
import java.io.IOException;
import java.util.List;

public class OneDayFilter implements Filter {


    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        Query q = entityManager.createNativeQuery("SELECT * FROM FLIGHT WHERE DATEFLIGHT = ?", Flight.class);
//        q.setParameter(1, param[0]);
//        return q.getResultList();
    }

    @Override
    public void destroy() {

    }
}
