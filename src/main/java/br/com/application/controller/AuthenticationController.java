package br.com.application.controller;



import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;

import br.com.application.dto.AuthenticationDTO;

import br.com.application.model.RefreshToken;

import br.com.application.model.user.User;

import br.com.application.service.UserService;
import br.com.application.service.impl.AuthenticationServiceImpl;
import br.com.application.service.impl.TokenService;
import br.com.application.utils.AplicationException;
import br.com.application.utils.JsonStatusEnum;
import br.com.application.utils.JsonStruct;


@CrossOrigin(origins = "http://localhost", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationServiceImpl authenticationService;

    @Autowired
    private TokenService tokenService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<JsonStruct> login(@RequestBody User login, HttpServletRequest httpServletRequest)
            throws ServletException {
        JsonStruct struct = new JsonStruct();
        if (login.getEmail() == null || login.getPassword() == null) {
            struct.setStatusToWarning();
            struct.setMessage("Please fill in username and password");
            return new ResponseEntity<JsonStruct>(struct, HttpStatus.UNAUTHORIZED);
        }

        String email = login.getEmail();

        User user = userService.findByEmail(email);

        if (user == null) {
            struct.setStatusToWarning();
            struct.setMessage("User not found");
            return new ResponseEntity<JsonStruct>(struct, HttpStatus.NOT_FOUND);
        }

     
        if (!authenticationService.isValidPasswordByUser(user, login.getPassword())) {
            struct.setStatusToWarning();
            struct.setMessage("Invalid login. Please check your username and password");
            return new ResponseEntity<JsonStruct>(struct, HttpStatus.UNAUTHORIZED);
        }
       
        authenticationService.generateTokens(user, null, httpServletRequest, 1);
        struct.setStatusToSuccess();
        struct.put("token", user.getToken());
        struct.put("refreshToken", user.getRefreshToken());
        struct.put("currentCompany",user.getCompany());
        return new ResponseEntity<JsonStruct>(struct, HttpStatus.OK);
    }

     @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<JsonStruct> create(@RequestBody User user) {
        JsonStruct struct = new JsonStruct();
        try {
            struct.setStatusToSuccess();
            if(user.getId() != null) {
            	struct.put("user", userService.saveUserCustomer(user,true));
            } else {
                user.setPassword(DigestUtils.sha1Hex(user.getPassword()));
                struct.put("user", userService.save(user));
            }
            return new ResponseEntity<JsonStruct>(struct, HttpStatus.OK);
        } catch (AplicationException aplicationException) {
            struct.put("exception", aplicationException);
            return new ResponseEntity<JsonStruct>(struct, HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            AplicationException aplicationException = new AplicationException(JsonStatusEnum.WARNING.getStatus(), "SUMMARY.WARNING",
                    exception.getMessage());
            struct.put("exception", aplicationException);
            return new ResponseEntity<JsonStruct>(struct, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/email/exists", method = RequestMethod.GET)
    public ResponseEntity<JsonStruct> findByEmail(@RequestHeader("email") String email) {
        JsonStruct struct = new JsonStruct();
        struct.setStatusToSuccess();
        struct.put("exists", userService.findByEmail(email) != null);
        return new ResponseEntity<JsonStruct>(struct, HttpStatus.OK);
    }

    @PutMapping(value = "/email/new-password")
    public ResponseEntity<JsonStruct> newPassword(String email) {
        JsonStruct struct = new JsonStruct();
        try {
            struct.setStatusToSuccess();
            struct.put("user", userService.newPassword(email));
            return new ResponseEntity<JsonStruct>(struct, HttpStatus.OK);
        } catch (AplicationException aplicationException) {
            struct.put("exception", aplicationException);
            return new ResponseEntity<JsonStruct>(struct, HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            AplicationException aplicationException = new AplicationException(JsonStatusEnum.WARNING.getStatus(), "SUMMARY.WARNING",
                    exception.getMessage());
            struct.put("exception", aplicationException);
            return new ResponseEntity<JsonStruct>(struct, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/refresh", method = RequestMethod.POST)
    public ResponseEntity<JsonStruct> refreshToken(@RequestBody RefreshToken refreshToken,
                                                   HttpServletRequest httpServletRequest) {
        JsonStruct struct = new JsonStruct();
        struct.put("user", authenticationService.refreshTokenLogin(refreshToken, httpServletRequest));
        return new ResponseEntity<JsonStruct>(struct, HttpStatus.OK);
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<JsonStruct> authenticate(@RequestBody AuthenticationDTO login,
                                                   HttpServletRequest httpServletRequest) throws ServletException {
        JsonStruct struct = new JsonStruct();
        if (login.getEmail() == null || login.getPassword() == null) {
            struct.setStatusToWarning();
            struct.setMessage("Please fill in email and password");
            return new ResponseEntity<JsonStruct>(struct, HttpStatus.UNAUTHORIZED);
        }

        User user = userService.findByEmailAndPassword(login);

        if (user == null) {
            struct.setStatusToWarning();
            struct.setMessage("User not found");
            return new ResponseEntity<JsonStruct>(struct, HttpStatus.UNAUTHORIZED);
        }

        authenticationService.generateTokens(user, login.getDevice(), httpServletRequest, 1);
        struct.setStatusToSuccess();
        struct.put("user", user);

        return new ResponseEntity<JsonStruct>(struct, HttpStatus.OK);
    }

    @RequestMapping(value = "/forgot-password/token", method = RequestMethod.POST)
    public ResponseEntity<JsonStruct> generateForgotPasswordToken(@RequestBody String email) {
        JsonStruct struct = new JsonStruct();

        try {
            String forgotPasswordToken = tokenService.generateForgotPasswordToken(email);
            struct.put("forgotPasswordToken", forgotPasswordToken);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(struct, HttpStatus.OK);
    }

}
