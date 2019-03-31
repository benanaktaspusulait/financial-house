package com.fhouse.sample.service;

import com.fhouse.sample.model.Acquirer;
import com.fhouse.sample.model.dto.AcquirerDTO;
import com.fhouse.sample.repository.transaction.AcquirerRepository;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class AcquirerService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AcquirerRepository acquirerRepository;

    @Transactional
    public AcquirerDTO save(AcquirerDTO dto) {

        log.debug("Request to save Acquirer : {}", dto);
        Acquirer entity = new Acquirer();
        if (dto.getId() != null) {
            entity = acquirerRepository.getOne(dto.getId());
        }
        entity = modelMapper.map(dto, Acquirer.class);
        Acquirer result = acquirerRepository.save(entity);
        return modelMapper.map(result, AcquirerDTO.class);
    }


    public Page<AcquirerDTO> findAll(Pageable pageable) {
        return acquirerRepository.findAll(pageable).map(acquire -> modelMapper.map( acquire, AcquirerDTO.class));
    }

    public List<AcquirerDTO> findAll() {
        return acquirerRepository.findAll().stream().map(acquire -> modelMapper.map( acquire, AcquirerDTO.class)).collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        log.debug ( "Request to delete Acquirer : {}" , id );
        acquirerRepository.deleteById ( id );
    }

    public AcquirerDTO findOne(Long id) throws NotFoundException {

        log.debug ( "Request to get Acquirer : {}" , id );
        Acquirer acquirer = acquirerRepository.getOne ( id );

        if (acquirer == null) {
            throw new NotFoundException ( "acquirer not found with id :" + id );
        } else {
            AcquirerDTO dto = modelMapper.map( acquirer, AcquirerDTO.class);
            return dto;
        }
    }
}
