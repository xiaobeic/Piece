package com.jjg.repository;

import com.jjg.model.PlayerPiece;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerPieceRepository extends CrudRepository<PlayerPiece, Long> {

    List<PlayerPiece> findByjumboJackpotId(Long jumboJackpotId);
}
