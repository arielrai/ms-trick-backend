	package com.publica.microservicesanalyzer.config;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Filtro para verificar usuario e senha padrão
 * @author Ariel Rai Rodrigues(arielrairodrigues@gmail.com)
 *
 */
@Component
//@Order(Ordered.LOWEST_PRECEDENCE)
public class SecurityFilter implements Filter {

	private static final String AUTH = "authentication";
	@Value("${app.user}") private String user;
	@Value("${app.pass}") private String pass;
	
	@Override
	public void destroy() {
	}

	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		if (request.getServletPath().contains("mappings") || request.getServletPath().contains("metrics")
				|| request.getServletPath().contains("env") || request.getServletPath().contains("trace")
				|| request.getServletPath().contains("restart") || request.getServletPath().contains("shutdown")) {
			chain.doFilter(request, response);	
		}else{
			String authentication = req.getParameter(AUTH);
			
			String serverUserPass = new String(Base64.getEncoder().encode(String.format("%s:%s", user, pass).getBytes("utf-8")));
			if (StringUtils.isEmpty(authentication) || !authentication.equals(serverUserPass)) {
				response.sendError(401, "Usuário ou senha inválidos");
			}
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
	}

}
