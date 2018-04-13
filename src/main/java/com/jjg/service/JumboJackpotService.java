package com.jjg.service;

import com.jjg.core.JumboJackpotPiecesPool;
import com.jjg.model.JumboJackpot;
import com.jjg.model.bo.JumboJackpotBo;

import java.util.Hashtable;
import java.util.List;
import java.util.Set;

public interface JumboJackpotService {

    List<JumboJackpot> getJumboJackpotActiveAll() throws Exception;

    JumboJackpot getJumboJackpotById(Long jumboJackpotId) throws Exception;

    boolean updateJumboJackpotState(Long jumboJackpotId, Integer status) throws Exception;

    boolean exists(Long jumboJackpotId) throws Exception;

    boolean isActive(Long jumboJackpotId) throws Exception;

    boolean saveJumboJackpot(JumboJackpotBo jumboJackpotBo) throws Exception;

    List<JumboJackpot> getJumboJackpotAll() throws Exception;

    boolean deleteJumboJack(long jumboJackpotId) throws Exception;

    JumboJackpot getJumboJackpot(long jumboJackpotId) throws Exception;

    boolean updateJumboJackpot(JumboJackpotBo jumboJackpotBo) throws Exception;

    List<Long> getJumboJackpotsId() throws Exception;
}
