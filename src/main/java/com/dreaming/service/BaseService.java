package com.dreaming.service;

import com.dreaming.base.ServerReturn;

/**
 * @author lucky
 * create on 2017/12/22
 */
public interface BaseService {
    // static 修饰符定义默认方法
    static void precommit() {
        System.out.println("执行预提交");
    }

    // static 修饰符定义默认方法
    static void commit() {
        System.out.println("执行提交");
    }

    //执行run方法
    ServerReturn run(String id);

    // static 修饰符定义默认方法
    static void rollback() {
        System.out.println("执行回滚");
    }
}
