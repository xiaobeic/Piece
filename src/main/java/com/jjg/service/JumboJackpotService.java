package com.jjg.service;

import com.jjg.model.JumboJackpot;

import java.util.List;

public interface JumboJackpotService {

    List<JumboJackpot> getJumboJackpotActiveAll() throws Exception;

    JumboJackpot getJumboJackpotById(Long jumboJackpotId) throws Exception;

    boolean updateJumboJackpotState(Long jumboJackpotId, Integer status) throws Exception;

    boolean exists(Long jumboJackpotId) throws Exception;

    boolean isActive(Long jumboJackpotId) throws Exception;
}
