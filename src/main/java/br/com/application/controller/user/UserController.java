package br.com.application.controller.user;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.application.model.user.User;
import br.com.application.service.UserService;
import br.com.application.utils.AplicationException;
import br.com.application.utils.JsonStatusEnum;
import br.com.application.utils.JsonStruct;

@CrossOrigin(origins = "http://localhost", maxAge = 3600)
@RestController
@RequestMapping("/secure")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/company")
    public ResponseEntity<JsonStruct> findUserForCompany(
            @RequestHeader("EmpresaId") Integer companyId) {
        JsonStruct struct = new JsonStruct();
        try {
            struct.setStatusToSuccess();
            struct.put("users", userService.findByCompany(companyId));
            return new ResponseEntity<JsonStruct>(struct, HttpStatus.OK);
        } catch (AplicationException applicationException) {
            struct.put("exception", applicationException);
            return new ResponseEntity<JsonStruct>(struct, HttpStatus.NOT_FOUND);
        }
    }


    //  @GetMapping("/user/company/not-traffic-manager")
    // public ResponseEntity<JsonStruct> findUserForCompanyNotTrafficManager(
    //         @RequestHeader("EmpresaId") Integer companyId) {
    //     JsonStruct struct = new JsonStruct();
    //     try {
    //         struct.setStatusToSuccess();
    //         struct.put("users", userService.findByCompanyNotTrafficManager(companyId));
    //         return new ResponseEntity<JsonStruct>(struct, HttpStatus.OK);
    //     } catch (AplicationException applicationException) {
    //         struct.put("exception", applicationException);
    //         return new ResponseEntity<JsonStruct>(struct, HttpStatus.NOT_FOUND);
    //     }
    // }   

    @GetMapping("/user/email/{email}")
    public ResponseEntity<JsonStruct> findUserForEmail(
            @RequestHeader("EmpresaId") Integer companyId,
            @PathVariable String email) {
        JsonStruct struct = new JsonStruct();
        try {
            struct.setStatusToSuccess();
            struct.put("users", userService.findByEmailAndCompany(email, companyId));
            return new ResponseEntity<JsonStruct>(struct, HttpStatus.OK);
        } catch (AplicationException applicationException) {
            struct.put("exception", applicationException);
            return new ResponseEntity<JsonStruct>(struct, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/user/{change}")
    public ResponseEntity<JsonStruct> create(@RequestHeader("EmpresaId") Integer companyId,
            @RequestHeader("UserId") Integer userId,
            @RequestBody User user,
            @PathVariable Boolean change) {
        JsonStruct struct = new JsonStruct();
        try {
            struct.setStatusToSuccess();
            if (user.getId() == null) {
                user.setPassword(DigestUtils.sha1Hex(user.getPassword()));
                struct.put("user", userService.save(user));
            } else {
                struct.put("user", userService.saveUserCustomer(user, change));
            }
            return new ResponseEntity<JsonStruct>(struct, HttpStatus.OK);
        } catch (AplicationException applicationException) {
            struct.put("exception", applicationException);
            return new ResponseEntity<JsonStruct>(struct, HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            AplicationException marinaException = new AplicationException(JsonStatusEnum.WARNING.getStatus(),
                    "SUMMARY.WARNING",
                    exception.getMessage());
            struct.put("exception", marinaException);
            return new ResponseEntity<JsonStruct>(struct, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<JsonStruct> delete(@RequestHeader("EmpresaId") Integer companyId,
            @RequestHeader("UserId") Integer userId,
            @PathVariable Integer id) {
        JsonStruct struct = new JsonStruct();
        try {
            userService.delete(id);
            struct.setStatusToSuccess();
            return new ResponseEntity<JsonStruct>(struct, HttpStatus.OK);
        } catch (AplicationException applicationException) {
            struct.put("exception", applicationException);
            return new ResponseEntity<JsonStruct>(struct, HttpStatus.NOT_FOUND);
        }
        
        
      
    }
}
