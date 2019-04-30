package com.richard.webmvcfn.config.filter;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Log4j2
@Component
public class SimpleFilter extends GenericFilter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        log.info("entering SimpleFilter");
        filterChain.doFilter(req, res);
        log.info("exiting SimpleFilter");
    }
}
