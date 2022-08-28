package com.krugger.vacunacion.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.krugger.vacunacion.Utils.GenerateToken.*;

@Slf4j
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
        // TODO Auto-generated constructor stub
    }

    /**
     * Todas las peticiones pasan por el filtro para realizar el consumo interno.Este debe contener el Authorization y el Bearer
     *
     * @param req
     * @param res
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        String header = req.getHeader(ENCABEZADO);
        if (header == null || !header.startsWith(PREFIJO_TOKEN)) //YA que una jwt debe tener el encabezado y el token para poder consumir internamente
        {
            chain.doFilter(req, res);
            return;
        }
        //obtenemos los datos del usuario a partir del token
        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
        //la información del usuario se almacena en el contexto de seguridad
        //para que pueda ser utilizada por Spring security durante el
        //proceso de autorización
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    /**
     * Validacion de token mediante la firma y el token recibido
     *
     * @param request
     * @return
     */
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        try
        {
            //el token viene en la cabecera de la petición
            String token = request.getHeader(ENCABEZADO);
            if (token != null) {
                // Se procesa el token y se recupera el usuario y los roles.
                Claims claims = Jwts.parser()
                        .setSigningKey(CLAVE)
                        .parseClaimsJws(token.replace(PREFIJO_TOKEN, ""))
                        .getBody();
                String user = claims.getSubject();
                List<String> authorities = (List<String>) claims.get("authorities");
                if (user != null) {
                    //creamos el objeto con la información del usuario
                    return new UsernamePasswordAuthenticationToken(user, null, authorities.stream()
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList()));
                }
                return null;
            }
            return null;
        }
        catch (Exception e)
        {
            return null;
        }

    }

}
