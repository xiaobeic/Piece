package com.jjg.repository;


import com.jjg.model.JumboJackpot;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JumboJackpotDao extends PagingAndSortingRepository<JumboJackpot, Long> {
    List<JumboJackpot> findByStatus(Integer status);
}
