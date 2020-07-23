package com.example.o2o.service;

import org.springframework.stereotype.Service;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@ServerEndpoint("/webSocket/{sid}")
@Service
public class WebSocketServer {
    private static AtomicInteger onlineNum = new AtomicInteger();
    private static ConcurrentHashMap<String, Session> sessionPools = new ConcurrentHashMap<>();

    public void sendMessage(Session session, String message) throws IOException {
        if (session != null) {
            synchronized (session) {
                System.out.println("send messgae: " + message);
                session.getBasicRemote().sendText(message);
            }
        }
    }

    public void sendInfo(String userName, String message) {
        Session session = sessionPools.get(userName);
        try {
            sendMessage(session, message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnOpen
    public void onOpen(Session session, @PathParam(value = "sid") String userName) {
        sessionPools.put(userName, session);
        onlineNum.incrementAndGet();
        System.out.println(userName + " join! current people sum: " + onlineNum);
        try {
            sendMessage(session, "welcom " + userName + "!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose(@PathParam(value = "sid") String userName) {
        sessionPools.remove(userName);
        onlineNum.decrementAndGet();
        System.out.println(userName + " leave! current people sum: " + onlineNum);
    }

    @OnMessage
    public void onMessage(String message) {
        message = "client " + message + " received!";
        System.out.println(message);
        for (Session session : sessionPools.values()) {
            try {
                sendMessage(session, message);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        System.out.println("error occur!");
        throwable.printStackTrace();
    }
}
