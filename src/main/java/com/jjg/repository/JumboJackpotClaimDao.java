package com.jjg.repository;

import com.jjg.model.JumboJackpotClaim;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JumboJackpotClaimDao extends PagingAndSortingRepository<JumboJackpotClaim, Long> {

}
