package com.jjg.repository;

import com.jjg.model.JumboJackpotPieceState;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JumboJackpotPieceDao extends CrudRepository<JumboJackpotPieceState, Long> {

}
