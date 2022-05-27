package br.com.application.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.application.enums.Application;
import br.com.application.enums.Platform;
import br.com.application.model.AppVersion;
import br.com.application.model.DeviceAppDto;
import br.com.application.model.RefreshToken;
import br.com.application.model.user.User;
import br.com.application.model.user.UserAccess;
import br.com.application.provider.AppVersionProvider;
import br.com.application.provider.UserAccessProvider;
import br.com.application.provider.UserProvider;
import br.com.application.service.AuthenticationService;
import br.com.application.utils.AplicationException;
import br.com.application.utils.JsonStatusEnum;

import java.time.LocalDateTime;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import eu.bitwalker.useragentutils.UserAgent;
import io.jsonwebtoken.Claims;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
	
	@Autowired
    private UserProvider userProvider;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UserAccessProvider userAccessProvider;

	@Autowired
	private AppVersionProvider appVersionProvider;
	
	@Override
	public boolean isValidPasswordByUser(User user, String password) {
		String passwordHash = DigestUtils.sha1Hex(password);
		return passwordHash.equals(user.getPassword());
	}
	
	@Override
	public User refreshTokenLogin(RefreshToken refreshToken, HttpServletRequest httpServletRequest) {
		User user = userProvider.findByRefreshToken(refreshToken.getRefreshToken());
		try {
			Claims token = tokenService.decodeToken(refreshToken.getRefreshToken());
			Date decode = token.getExpiration();
			if (decode != null && decode.before(new Date())) {
				throw new AplicationException(JsonStatusEnum.WARNING.getStatus(), "SUMMARY.WARNING",
						"Sessão expirada. Faça login novamente.");
			}
			if (decode == null && user == null) {
				user = userProvider.findByEmail((String) token.get("sub"));
			}
		//	List<Integer> marinasIdsAccess = getMarinasIdsAccess(httpServletRequest.getHeader("Client"), user);
			generateTokens(user, refreshToken.getDevice(), httpServletRequest, 1);
			return user;
		} catch (Exception e) {
			throw new AplicationException(JsonStatusEnum.WARNING.getStatus(), "SUMMARY.WARNING", "Token inválido.");
		}
	}

	@Override
	public void generateTokens(User user, DeviceAppDto device, HttpServletRequest httpServletRequest, Integer empresaId) {
		user.setRefreshToken(tokenService.generateRefreshToken(user, httpServletRequest.getHeader("Client")));
		user.setToken(tokenService.generateAccessToken(user,httpServletRequest.getHeader("Client"), empresaId));
		saveSessionInfo(user, device, httpServletRequest);
		userProvider.updateRefreshToken( user.getRefreshToken(),user.getId());
		user.setPassword(null);
	}
	
	private void saveSessionInfo(User user, DeviceAppDto device, HttpServletRequest httpServletRequest) {
		Application application = Application.valueOf(httpServletRequest.getHeader("Client"));
		
		UserAccess access = userAccessProvider.findTopByUserIdOrderByIdDesc(user.getId())
				.orElse(new UserAccess(user));
		
		access.setLoginDate(LocalDateTime.now());
		access.setApplication(application);
		getApplicationInfo(access, device, httpServletRequest, application);
		userAccessProvider.save(access);
	}

	private void getApplicationInfo(UserAccess access, DeviceAppDto device, HttpServletRequest httpServletRequest,
			Application application) {

		Platform p;
		switch (application) {
		case Postman:
		p= Platform.POSTMAN;
		default:
			p = Platform.WEB;
			break;
		}

		UserAgent userAgent = UserAgent.parseUserAgentString(httpServletRequest.getHeader("User-Agent"));
		if (device != null) {
			access.setApplicationVersion(device.getEasyVersionNumber());
			access.setPlatform(device.getPlatform().toString());
			access.setPlatformVersion(device.getVersion());
		} else {
			AppVersion last = appVersionProvider.findFirst1ByPlatformAndApplicationOrderByVersionDesc(p, application);
			if (last != null) {
				access.setApplicationVersion(last.getVersion());
			}
			access.setPlatform(userAgent.getOperatingSystem().getName());
		}
		access.setBrowser(userAgent.getBrowser().getName());
		access.setBrowserVersion(
				userAgent.getBrowserVersion() != null ? userAgent.getBrowserVersion().getVersion() : null);
		access.setDeviceType(userAgent.getOperatingSystem().getDeviceType().getName());
		access.setLocale(httpServletRequest.getLocale().toString());
		access.setManufacturer(userAgent.getOperatingSystem().getManufacturer().getName());
		access.setRemoteAddress(httpServletRequest.getRemoteAddr());
		access.setRemoteHost(httpServletRequest.getRemoteHost());
	}


}
