package com.training.autoproject.filter;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.ThrowableAnalyzer;
import org.springframework.security.web.util.ThrowableCauseExtractor;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filter which finds ajax requests with invalid session
 *
 * @author Oleh Surkov
 * @version 1.0
 */
public class AjaxTimeoutRedirectFilter extends GenericFilterBean {


    private static final Logger log = LogManager.getLogger(AjaxTimeoutRedirectFilter.class);
    private ThrowableAnalyzer throwableAnalyzer = new DefaultThrowableAnalyzer();
    private AuthenticationTrustResolver authenticationTrustResolver = new AuthenticationTrustResolverImpl();

    private int customSessionExpiredErrorCode = 901;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {

            chain.doFilter(request, response);

            log.info("Chain processed normally");
        } catch (IOException ex) {
            throw ex;
        } catch (Exception ex) {
            Throwable[] causeChain = throwableAnalyzer.determineCauseChain(ex);
            RuntimeException ase = (AuthenticationException) throwableAnalyzer.getFirstThrowableOfType(AuthenticationException.class, causeChain);

            if (ase == null) {
                ase = (AccessDeniedException) throwableAnalyzer.getFirstThrowableOfType(AccessDeniedException.class, causeChain);
            }

            if (ase != null) {
                if (ase instanceof AuthenticationException) {
                    throw ase;
                } else if (ase instanceof AccessDeniedException) {

                    if (authenticationTrustResolver.isAnonymous(SecurityContextHolder.getContext().getAuthentication())) {
                        log.info("User session expired or not logged in yet");
                        String ajaxHeader = ((HttpServletRequest) request).getHeader("X-Requested-With");

                        if ("XMLHttpRequest".equals(ajaxHeader)) {
                            log.info("Ajax call detected, send {} error code");
                            HttpServletResponse resp = (HttpServletResponse) response;
                            resp.sendError(this.customSessionExpiredErrorCode);
                        } else {
                            log.info("Redirect to login page");
                            throw ase;
                        }
                    } else {
                        throw ase;
                    }
                }
            }

        }
    }

    private static final class DefaultThrowableAnalyzer extends ThrowableAnalyzer {
        /**
         * @see org.springframework.security.web.util.ThrowableAnalyzer#initExtractorMap()
         */
        protected void initExtractorMap() {
            super.initExtractorMap();

            registerExtractor(ServletException.class, new ThrowableCauseExtractor() {
                public Throwable extractCause(Throwable throwable) {
                    ThrowableAnalyzer.verifyThrowableHierarchy(throwable, ServletException.class);
                    return ((ServletException) throwable).getRootCause();
                }
            });
        }

    }

    public void setCustomSessionExpiredErrorCode(int customSessionExpiredErrorCode) {
        this.customSessionExpiredErrorCode = customSessionExpiredErrorCode;
    }
}