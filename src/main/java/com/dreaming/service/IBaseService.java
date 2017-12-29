package com.dreaming.service;

import com.dreaming.base.ServerReturn;
import com.dreaming.exception.DreamingSysException;

/**
 * @author lucky
 * create on 2017/12/22
 */
public interface IBaseService {

    ServerReturn run(String id) throws DreamingSysException;

}
