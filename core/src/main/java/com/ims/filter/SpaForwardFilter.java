package com.ims.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class SpaForwardFilter implements Filter {

    @Override
    public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain)
            throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;
        final String path = request.getRequestURI();

        // If request targets API or static resources, continue filter chain
        if (path.startsWith(request.getContextPath() + "/api/") || path.startsWith(request.getContextPath() + "/dist/")
                || path.startsWith(request.getContextPath() + "/static/") || path.contains(".") ) {
            chain.doFilter(req, res);
            return;
        }

        // Forward other requests to the SPA index.html
        request.getRequestDispatcher("/index.html").forward(request, response);
    }

}

