package com.jjg.repository;

import com.jjg.model.PlayerPiece;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerPieceRepository extends CrudRepository<PlayerPiece, Long> {

    List<PlayerPiece> findByjumboJackpotId(Long jumboJackpotId);

    Page<PlayerPiece> findByjumboJackpotId(Long jumboJackpotId, Pageable pageable);

    Page<PlayerPiece> findByjumboJackpotIdAndPlayerId(Long jumboJackpotId, Long playerId, Pageable pageable);
}
