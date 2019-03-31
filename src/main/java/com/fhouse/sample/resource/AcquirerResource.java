package com.fhouse.sample.resource;

import com.fhouse.sample.model.dto.system.ErrorDTO;
import com.fhouse.sample.model.dto.system.SystemErrorDTO;
import com.fhouse.sample.model.enums.ErrorType;
import com.fhouse.sample.service.AcquirerService;
import com.fhouse.sample.service.SecurityUtils;
import com.fhouse.sample.service.system.SystemErrorService;
import com.fhouse.sample.util.Rights;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/v3/acquirers")
public class AcquirerResource {

    @Autowired
    private AcquirerService acquirerService;

    @Autowired
    private SystemErrorService systemErrorService;

    @RolesAllowed(Rights.ACQUIRER_LIST)
    @GetMapping
    public ResponseEntity<?> getBanner(Pageable pageable) {

        try {
            return new ResponseEntity<>(acquirerService.findAll(pageable), HttpStatus.OK);
        } catch (Exception ex) {
            systemErrorService.save(new SystemErrorDTO(ErrorType.GET_ACQUIRER_LIST, SecurityUtils.getCurrentLogin()));
            return new ResponseEntity<>(new ErrorDTO("genreService.getAll", ex.getMessage()), HttpStatus.CONFLICT);
        }

    }
}
