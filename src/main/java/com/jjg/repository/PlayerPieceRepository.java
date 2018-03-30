package com.jjg.repository;

import com.jjg.model.PlayerPiece;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerPieceRepository extends CrudRepository<PlayerPiece, Long> {

}
