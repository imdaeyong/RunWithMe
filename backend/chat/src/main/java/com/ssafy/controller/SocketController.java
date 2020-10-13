package com.ssafy.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.ssafy.vo.SocketVO;

@Controller
public class SocketController {
    // /receive�� �޽����� ���� endpoint�� �����մϴ�.
    @MessageMapping("/receive")
    
    // /send�� �޽����� ��ȯ�մϴ�.
    @SendTo("/send")
    
 // SocketHandler�� 1) /receive���� �޽����� �ް�, /send�� �޽����� �����ݴϴ�.
    // ������ SocketVO�� 1) ���ڰ�, 2) ��ȯ������ ����մϴ�.
    public SocketVO SocketHandler(SocketVO socketVO) {
        // vo���� getter�� userName�� �����ɴϴ�.
        String userName = socketVO.getUserName();
        // vo���� setter�� content�� �����ɴϴ�.
        String content = socketVO.getContent();

        // �����ڷ� ��ȯ���� �����մϴ�.
        SocketVO result = new SocketVO(userName, content);
        // ��ȯ
        return result;
    }
}
