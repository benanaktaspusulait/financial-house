package com.fhouse.sample.service;

import com.fhouse.sample.model.Acquirer;
import com.fhouse.sample.model.dto.AcquirerDTO;
import com.fhouse.sample.repository.transaction.AcquirerRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Acquirer entity = null;
        if (dto.getId() != null) {
            entity = acquirerRepository.getOne(dto.getId());
        } else {
            entity = new Acquirer();
        }
        entity = modelMapper.map(dto, Acquirer.class);
        Acquirer result = acquirerRepository.save(entity);
        return modelMapper.map(result, AcquirerDTO.class);
    }
}