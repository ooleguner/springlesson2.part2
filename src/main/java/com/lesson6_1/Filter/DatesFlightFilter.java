package com.lesson6_1.Filter;

import com.lesson6_1.model.Flight;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.*;
import java.io.IOException;
import java.util.List;

/**
 * Created by oleg on 22.09.2019.
 */
public class DatesFlightFilter implements javax.servlet.Filter {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        Query q = entityManager.createNativeQuery("SELECT * FROM FLIGHT WHERE DATEFLIGHT >= ? AND DATEFLIGHT<= ?", Flight.class);
//        q.setParameter(1, param[0]);
//        q.setParameter(2, param[1]);
//        return q.getResultList();

    }

    @Override
    public void destroy() {

    }
}
