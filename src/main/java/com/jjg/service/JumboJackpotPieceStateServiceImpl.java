package com.jjg.service;

import com.jjg.model.JumboJackpotPieceState;
import com.jjg.repository.JumboJackpotPieceStateDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;

@Service
public class JumboJackpotPieceStateServiceImpl implements JumboJackpotPieceStateService {

    @Autowired
    private JumboJackpotPieceStateDao jumboJackpotPieceStateDao;


    @Override
    public void saveJumboJackpotPieceState(HashMap<String, JumboJackpotPieceState> jumboJackpotPieces) {
        Iterator iterator = jumboJackpotPieces.keySet().iterator();
        while (iterator.hasNext()) {
            JumboJackpotPieceState jumboJackpotPieceState = jumboJackpotPieces.get(iterator.next());
            jumboJackpotPieceStateDao.save(jumboJackpotPieceState);
        }
    }
}
