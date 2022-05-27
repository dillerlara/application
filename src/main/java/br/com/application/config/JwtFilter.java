package br.com.application.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.GenericFilterBean;

import br.com.application.service.impl.TokenService;
import br.com.application.utils.AuthException;
import br.com.application.utils.BlackListAuthentication;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.SignatureException;

public class JwtFilter extends GenericFilterBean {

	@Autowired
	private TokenService tokenService;

	public JwtFilter(TokenService tokenService) {
		this.tokenService = tokenService;
	}

	public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain)
			throws IOException, ServletException {

		final HttpServletRequest request = (HttpServletRequest) req;
		final HttpServletResponse response = (HttpServletResponse) res;
		final String authHeader = request.getHeader("authorization");
		final String refreshTokenHeader = request.getHeader("refreshToken");
		final String origin = request.getHeader("Origin");
		final String companyId = request.getHeader("CompanyId");
		final String userId = request.getHeader("UserId");

		if ("OPTIONS".equals(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);

			chain.doFilter(req, res);
		} else {
			try {
				if (authHeader == null || !authHeader.startsWith("Bearer ")) {
					throw new AuthException();
				}
				final String token = authHeader.substring(7);
				final Claims claims = tokenService.decodeToken(token);
				BlackListAuthentication.conteins(claims.getSubject());
				if ((!claims.isEmpty() && claims.get("type") != null) &&
						(claims.get("type").equals("FORGOT_PASSWORD") || claims.get("type").equals("CHANGE_PASSWORD"))) {
					if (!request.getRequestURI().equals("/secure/user/change/password")) {
						response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
						return;
					}
				}
				request.setAttribute("claims", claims);
			} catch (final SignatureException e) {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing or invalid Authorization header.");
			} catch (ExpiredJwtException e) {
				if (refreshTokenHeader == null || refreshTokenHeader.isEmpty()) {
					response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Session expired.");
					return;
				}
				try {
					if (origin.equals("EasyAdmin")){
						String newToken;
						if (companyId != null){
							newToken = tokenService.verifyRefresh(Integer.parseInt(userId), origin, refreshTokenHeader, Integer.parseInt(companyId));
						} else {
							newToken = tokenService.verifyRefresh(Integer.parseInt(userId), origin, refreshTokenHeader);
						}

						if (newToken != null && newToken.length() > 0) {
							String header = response.getHeader("access-control-expose-headers");
							String newAcessControl = "X-NewToken";
							if (header != null) {
								newAcessControl = newAcessControl + ", " + header;
							}
							response.setHeader("access-control-expose-headers", newAcessControl);
							response.addHeader("X-NewToken", newToken);
							response.setStatus(HttpServletResponse.SC_OK);
						} else {
							response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Session expired.");
						}
					} else {
						response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Session expired.");
					}
				} catch (JwtException e2) {
					response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing or invalid Authorization header.");
				}
				
			} catch (AuthException e) {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing or invalid Authorization header.");
			}

			chain.doFilter(req, res);
		}
	}
}
