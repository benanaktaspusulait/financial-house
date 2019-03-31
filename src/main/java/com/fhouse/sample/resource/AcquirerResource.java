package com.fhouse.sample.resource;

import com.fhouse.sample.model.dto.AcquirerDTO;
import com.fhouse.sample.model.dto.system.ErrorDTO;
import com.fhouse.sample.model.dto.system.SystemErrorDTO;
import com.fhouse.sample.model.enums.ErrorType;
import com.fhouse.sample.service.AcquirerService;
import com.fhouse.sample.service.SecurityUtils;
import com.fhouse.sample.service.system.SystemErrorService;
import com.fhouse.sample.util.Rights;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/v3/acquirers")
public class AcquirerResource {

    @Autowired
    private AcquirerService acquirerService;

    @Autowired
    private SystemErrorService systemErrorService;

    @ResponseBody
    @ApiOperation(value = "Search a acquirer with an ID", response = AcquirerDTO.class)
    @GetMapping(value = "/{id}")
    @RolesAllowed(value = Rights.ACQUIRER)
    public ResponseEntity<?> get(@PathVariable Long id) {
        log.debug("REST request to get Acquirer : {}", id);
        try {
            AcquirerDTO acquirerDTO = acquirerService.findOne(id);
            return Optional.ofNullable(acquirerDTO)
                    .map(result -> new ResponseEntity<>(
                            result,
                            HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception ex) {
            systemErrorService.save(new SystemErrorDTO(ErrorType.GET_ACQUIRER, SecurityUtils.getCurrentLogin()));
            return new ResponseEntity<>(new ErrorDTO("acquirerService.get", ex.getMessage()), HttpStatus.CONFLICT);
        }
    }

    @ApiOperation(value = "Add a acquirer")
    @PostMapping
    @RolesAllowed(value = {Rights.ACQUIRER, Rights.ACQUIRER})
    public ResponseEntity<?> create(@RequestBody @Valid AcquirerDTO dto) {
        log.debug("REST request to save AcquirerDTO: {}", dto);
        try {
            return new ResponseEntity<>(acquirerService.save(dto), HttpStatus.CREATED);
        } catch (DataIntegrityViolationException ex) {

            systemErrorService.save(new SystemErrorDTO(ErrorType.SAVE_ACQUIRER, SecurityUtils.getCurrentLogin()));
            return new ResponseEntity<>(new ErrorDTO("acquirerService.create", "Bu GSM numarası şu an aktif bir şoför kullanıyor!"), HttpStatus.CONFLICT);
        }
        catch (Exception ex) {
            systemErrorService.save(new SystemErrorDTO(ErrorType.SAVE_ACQUIRER, SecurityUtils.getCurrentLogin()));
            return new ResponseEntity<>(new ErrorDTO("acquirerService.create", ex.getMessage()), HttpStatus.CONFLICT);
        }
    }

    @ApiOperation(value = "Update a acquirer")
    @PatchMapping
    @RolesAllowed(value = Rights.ACQUIRER)
    public ResponseEntity<?> update(@RequestBody @Valid AcquirerDTO acquirerDTO) {
        log.debug("REST request to update basket : {}", acquirerDTO);
        try {
            if (acquirerDTO.getId() == null) {
                return create(acquirerDTO);
            }
            return new ResponseEntity<>(acquirerService.save(acquirerDTO), HttpStatus.OK);
        }
        catch (Exception ex) {
            systemErrorService.save(new SystemErrorDTO(ErrorType.SAVE_ACQUIRER, SecurityUtils.getCurrentLogin()));
            return new ResponseEntity<>(new ErrorDTO("acquirerService.update", ex.getMessage()), HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping(value = "/{id}")
    @RolesAllowed(value = Rights.ACQUIRER)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        log.debug("REST request to delete product : {}", id);
        try {
            acquirerService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            systemErrorService.save(new SystemErrorDTO(ErrorType.REMOVE_ACQUIRER, SecurityUtils.getCurrentLogin()));
            return new ResponseEntity<>(new ErrorDTO("acquirerService.delete", ex.getMessage()), HttpStatus.CONFLICT);
        }
    }
    
    @RolesAllowed(Rights.ACQUIRER)
    @GetMapping
    public ResponseEntity<?> getAcquirerList(Pageable pageable) {

        try {
            return new ResponseEntity<>(acquirerService.findAll(pageable), HttpStatus.OK);
        } catch (Exception ex) {
            systemErrorService.save(new SystemErrorDTO(ErrorType.GET_ACQUIRER_LIST, SecurityUtils.getCurrentLogin()));
            return new ResponseEntity<>(new ErrorDTO("acquirerService.getAll", ex.getMessage()), HttpStatus.CONFLICT);
        }

    }
}
