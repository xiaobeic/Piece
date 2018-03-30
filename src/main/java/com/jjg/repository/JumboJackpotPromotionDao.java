package com.jjg.repository;

import com.jjg.model.JumboJackpotPromotion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JumboJackpotPromotionDao extends CrudRepository<JumboJackpotPromotion, Long> {

}
