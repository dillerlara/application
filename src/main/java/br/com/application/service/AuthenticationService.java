package br.com.application.service;

import javax.servlet.http.HttpServletRequest;

import br.com.application.model.DeviceAppDto;
import br.com.application.model.RefreshToken;
import br.com.application.model.user.User;

public interface AuthenticationService {

	boolean isValidPasswordByUser(User user, String password);

	User refreshTokenLogin(RefreshToken refreshToken, HttpServletRequest httpServletRequest);

	void generateTokens(User user, DeviceAppDto device, HttpServletRequest httpServletRequest, Integer empresaId);

}
