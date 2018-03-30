package com.jjg.repository;

import com.jjg.model.JumboJackpotPiece;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JumboJackpotPieceDao extends CrudRepository<JumboJackpotPiece, Long> {

}
