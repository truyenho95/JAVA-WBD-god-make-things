package com.truyenho.repository;

import com.truyenho.model.Thing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ThingRepository extends PagingAndSortingRepository<Thing, Long> {
  Page <Thing> findAllByNameContaining(String name, Pageable pageable);
}
