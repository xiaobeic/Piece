package com.jjg.repository;


import com.jjg.model.JumboJackpot;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JumboJackpotDao extends PagingAndSortingRepository<JumboJackpot, Long> {
    List<JumboJackpot> findByStatus(Integer status);

    @Query("select jumboJackpotId from jumboJackpot where status in (1 , 2)")
    List<Long> getJumboJackpotsId();
}
