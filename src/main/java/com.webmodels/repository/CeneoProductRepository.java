package com.webmodels.repository;

import com.webmodels.model.CeneoProduct;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CeneoProductRepository extends CrudRepository<CeneoProduct, Long> {
}
