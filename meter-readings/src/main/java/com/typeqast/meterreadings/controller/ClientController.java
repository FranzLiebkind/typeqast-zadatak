package com.typeqast.meterreadings.controller;

import com.typeqast.meterreadings.domain.dto.ClientCreateDTO;
import com.typeqast.meterreadings.domain.dto.ClientDTO;
import com.typeqast.meterreadings.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ClientController {

    private final Logger log = LoggerFactory.getLogger(ClientController.class);

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/clients")
    public ResponseEntity<ClientDTO> saveClient(@Valid @RequestBody ClientCreateDTO clientCreateDTO) {
        log.debug("REST request to save client");

        ClientDTO result = clientService.save(clientCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}
