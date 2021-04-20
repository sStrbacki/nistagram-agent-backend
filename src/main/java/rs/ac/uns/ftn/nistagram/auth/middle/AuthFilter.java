package rs.ac.uns.ftn.nistagram.auth.middle;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import rs.ac.uns.ftn.nistagram.auth.exceptions.AuthorizationException;
import rs.ac.uns.ftn.nistagram.auth.model.IdentityToken;
import rs.ac.uns.ftn.nistagram.auth.service.JwtService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthFilter extends OncePerRequestFilter {

    private final JwtService<IdentityToken> jwtService;

    public AuthFilter() {
        this.jwtService = new JwtService<>(IdentityToken.class);
    }

    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain
    ) throws ServletException, IOException
    {
        final String httpHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        // Check existence and physical validity of the Auth Bearer token header
        if (httpHeader == null || httpHeader.isEmpty() || !httpHeader.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        String[] headerTokens = httpHeader.split(" ");
        if (headerTokens.length != 2) {
            chain.doFilter(request, response);
            return;
        }

        // Check JWT validity from the given Bearer token
        String jwt = headerTokens[1];
        IdentityToken identity;
        try {
            identity = jwtService.decrypt(jwt);
        }
        catch(AuthorizationException e) {
            chain.doFilter(request, response);
            return;
        }

        // We create a new Spring auth token which we add to the request before passing it on to other filters
        // This then reaches our methods which are guarded by @Secured and thus can provide protection from unauth users
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                identity,
                null,
                identity.getAuthorities()
        );

        auth.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );

        SecurityContextHolder.getContext().setAuthentication(auth);
        chain.doFilter(request, response);
    }
}
