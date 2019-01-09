package com.truyenho.repository;

import com.truyenho.model.Thing;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ThingRepository extends PagingAndSortingRepository<Thing, Long> {
}
