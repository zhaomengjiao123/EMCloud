package com.server.emcloud.socket;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zmj
 * @Date: 2022/07/08/21:30
 * @Description:
 */
public class SocketPool {

    private static final ConcurrentHashMap<String, ClientSocket> ONLINE_SOCKET_MAP = new ConcurrentHashMap<>();


    public static void add(ClientSocket clientSocket){
        if (clientSocket != null && !clientSocket.getKey().isEmpty())
            ONLINE_SOCKET_MAP.put(clientSocket.getKey(), clientSocket);
    }

    public static void remove(String key){
        if (!key.isEmpty())
            ONLINE_SOCKET_MAP.remove(key);
    }

}
