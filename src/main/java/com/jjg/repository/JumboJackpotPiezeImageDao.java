package com.jjg.repository;


import com.jjg.model.JumboJackpotPieceImage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JumboJackpotPiezeImageDao extends CrudRepository<JumboJackpotPieceImage, Long> {

}
