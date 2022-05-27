package br.com.application.controller.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.application.service.CompanyService;
import br.com.application.utils.AplicationException;
import br.com.application.utils.JsonStruct;

@CrossOrigin(origins = "http://localhost", maxAge = 3600)
@RestController
@RequestMapping("/secure")
public class CompanyController {

    @Autowired
    private CompanyService companyService;


    @GetMapping("/company/{id}")
    public ResponseEntity<JsonStruct> create(@PathVariable Integer id) {
        JsonStruct struct = new JsonStruct();
        try {
            struct.setStatusToSuccess();
            struct.put("company", companyService.findById(id));
            return new ResponseEntity<JsonStruct>(struct, HttpStatus.OK);
        } catch (AplicationException applicationException) {
            struct.put("exception", applicationException);
            return new ResponseEntity<JsonStruct>(struct, HttpStatus.NOT_FOUND);
        } 
    }
}
