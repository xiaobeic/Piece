package com.jjg.repository;

import com.jjg.model.JumboJackpotPieceState;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JumboJackpotPieceStateDao extends CrudRepository<JumboJackpotPieceState, Long> {

    JumboJackpotPieceState findByJumboJackpotIdAndPieceName(Long jumboJackpotId, String pieceName);

    List<JumboJackpotPieceState> findByJumboJackpotId(Long jumboJackpotId);
}
