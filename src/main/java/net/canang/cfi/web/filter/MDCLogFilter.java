package net.canang.cfi.web.filter;

import net.canang.cfi.biz.integration.springsecurity.CfUserDetails;
import org.apache.log4j.MDC;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author rafizan.baharum
 * @since 10/20/13
 */
public class MDCLogFilter implements Filter {

    private static final String X_FORWARDED_FOR = "X-Forwarded-For";
    private static final String USERNAME = "username";
    private static final String CLIENT_IP = "clientIP";

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        // Retrieves the session object from the current request.
        HttpSession session = ((HttpServletRequest) request).getSession();

        try {
            SecurityContext context = SecurityContextHolder.getContext();
            Object principal = context.getAuthentication().getPrincipal();
            if (principal instanceof CfUserDetails) {
                CfUserDetails userDetails = (CfUserDetails) principal;
                MDC.put(USERNAME, userDetails.getUser().getName());
            }
        } catch (Exception e) {
        }

        MDC.put(CLIENT_IP, request.getRemoteAddr());
        String xForwardedFor = ((HttpServletRequest) request).getHeader(X_FORWARDED_FOR);
        if (xForwardedFor != null) {
            MDC.put(X_FORWARDED_FOR, xForwardedFor);
        }

        // Continue processing the rest of the filter chain.
        chain.doFilter(request, response);

        // Remove the username from the diagnostic context.
        MDC.remove(USERNAME);
        MDC.remove(CLIENT_IP);
        MDC.remove(X_FORWARDED_FOR);
    }

}