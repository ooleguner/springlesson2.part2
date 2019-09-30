package com.lesson6_1.Filter;

import com.lesson6_1.model.Flight;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class CityFromFilter implements javax.servlet.Filter{


    @PersistenceContext
    EntityManager entityManager;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        String city = httpRequest.getParameter("year");

         Query q = entityManager.createNativeQuery("SELECT * FROM FLIGHT WHERE CITYFROM= ?", Flight.class);
         q.setParameter(1,city);
    //     httpResponse = (HttpServletResponse) q.getResultList();

        System.out.println("in CityFromFilter ");
        filterChain.doFilter(servletRequest,servletResponse);
    }


    @Override
    public void destroy() {

    }
}
