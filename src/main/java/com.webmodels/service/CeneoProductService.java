package com.webmodels.service;

import com.webmodels.model.CeneoProduct;
import com.webmodels.repository.CeneoProductRepository;
import org.springframework.stereotype.Service;

@Service
public class CeneoProductService {

    private final CeneoProductRepository ceneoProductRepository;

    public CeneoProductService(CeneoProductRepository ceneoProductRepository) {
        this.ceneoProductRepository = ceneoProductRepository;
    }

    public CeneoProduct save(CeneoProduct ceneoProduct){
        return ceneoProductRepository.save(ceneoProduct);
    }

    public Iterable<CeneoProduct> list(){
        return ceneoProductRepository.findAll();
    }
}
