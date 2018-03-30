package com.jjg.repository;


import com.jjg.model.JumboJackpot;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JumboJackpotDao extends PagingAndSortingRepository<JumboJackpot, Long> {

}
