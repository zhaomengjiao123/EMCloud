package com.server.emcloud.service;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zmj
 * @Date: 2022/07/08/19:48
 * @Description:
 */
public interface AsyncService {
    /**
     * 执行异步任务
     * 可以根据需求，自己加参数拟定
     */
    void executeAsync();
}
