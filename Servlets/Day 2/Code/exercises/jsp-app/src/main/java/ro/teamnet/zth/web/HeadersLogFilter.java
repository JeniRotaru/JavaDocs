package ro.teamnet.zth.web;

import com.sun.jmx.snmp.Enumerated;
import ro.teamnet.zth.file.LogFileWriter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 7/13/2016.
 */
public class HeadersLogFilter implements javax.servlet.Filter{


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        Enumeration<String> enumerated = req.getHeaderNames();

        while(enumerated.hasMoreElements()) {
            String headerName = enumerated.nextElement();
            LogFileWriter.logHeader(headerName, req.getHeader(headerName));
        }
        filterChain.doFilter(req, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
