package com.jjg.service;

import com.jjg.model.JumboJackpot;

import java.util.List;

public interface JumboJackpotService {

    List<JumboJackpot> getJumboJackpotAll();

    JumboJackpot getJumboJackpotById(Long jumboJackpotId);

    boolean updateJumboJackpotState(Long jumboJackpotId, Integer status);

    boolean exists(Long jumboJackpotId);
}
