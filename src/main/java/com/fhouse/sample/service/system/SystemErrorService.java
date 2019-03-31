package com.fhouse.sample.service.system;

import com.fhouse.sample.model.dto.system.SystemErrorDTO;
import com.fhouse.sample.model.error.SystemError;
import com.fhouse.sample.repository.system.SystemErrorRepository;
import com.fhouse.sample.service.SecurityUtils;
import com.fhouse.sample.util.AppConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class SystemErrorService {

    @Autowired
    private SystemErrorRepository systemErrorRepository;


    @Transactional
    public void save(SystemErrorDTO systemErrorDTO) {

        SystemError systemError = SystemErrorDTO.toEntity(systemErrorDTO);
        systemError.setCreatedBy ( SecurityUtils.getCurrentLogin () != null ? SecurityUtils.getCurrentLogin () : AppConstants.SYSTEM_ACCOUNT );
        systemErrorRepository.save(systemError);
    }
}

