package br.com.application.controller.user;

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

import br.com.application.model.client.Client;
import br.com.application.service.ClientOperationsService;
import br.com.application.utils.AplicationException;
import br.com.application.utils.JsonStruct;

@CrossOrigin(origins = "http://localhost", maxAge = 3600)
@RestController
@RequestMapping("/secure/client")
public class ClientController {
    
    @Autowired
    private ClientOperationsService clientOperationsService;

    // @GetMapping("/all-company")
    // public ResponseEntity<JsonStruct> findUserForCompany(
    //         @RequestHeader("EmpresaId") Integer companyId) {
    //     JsonStruct struct = new JsonStruct();
    //     try {
    //         struct.setStatusToSuccess();
    //         struct.put("clients", clientOperationsService.getAllClientsCompany(companyId));
    //         return new ResponseEntity<JsonStruct>(struct, HttpStatus.OK);
    //     } catch (AplicationException applicationException) {
    //         struct.put("exception", applicationException);
    //         return new ResponseEntity<JsonStruct>(struct, HttpStatus.NOT_FOUND);
    //     }
    // }

    @PostMapping("/")
    public ResponseEntity<JsonStruct> save(@RequestHeader("EmpresaId") Integer companyId,
            @RequestHeader("UserId") Integer userId,
            @RequestBody Client client) {
        JsonStruct struct = new JsonStruct();
        try {
            struct.setStatusToSuccess();
            struct.put("client",clientOperationsService.save(client));
            return new ResponseEntity<JsonStruct>(struct, HttpStatus.OK);
        } catch (AplicationException applicationException) {
            struct.setStatusToError();
            struct.put("exception", applicationException);
            return new ResponseEntity<JsonStruct>(struct, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<JsonStruct> delete(@RequestHeader("EmpresaId") Integer companyId,
            @RequestHeader("UserId") Integer userId,
            @PathVariable Integer id) {
        JsonStruct struct = new JsonStruct();
        try {
            clientOperationsService.delete(id);
            struct.setStatusToSuccess();
            return new ResponseEntity<JsonStruct>(struct, HttpStatus.OK);
        } catch (AplicationException applicationException) {
            struct.put("exception", applicationException);
            return new ResponseEntity<JsonStruct>(struct, HttpStatus.NOT_FOUND);
        }
        
        
      
    }

}
