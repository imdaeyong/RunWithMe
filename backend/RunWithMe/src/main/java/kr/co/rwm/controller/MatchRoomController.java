package kr.co.rwm.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.rwm.model.ChatRoom;
import kr.co.rwm.model.Response;
import kr.co.rwm.model.ResponseMessage;
import kr.co.rwm.model.StatusCode;
import kr.co.rwm.repo.MatchRoomRepository;
import kr.co.rwm.service.JwtTokenProvider;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/match")
public class MatchRoomController {

    private final MatchRoomRepository matchRoomRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("/room")
    public String rooms() {
        return "/chat/room2";
    }

    @PostMapping("/room")
    @ResponseBody 
    public ResponseEntity<Response<Object>> createAndSelectChatroom(@RequestBody Map<String, Integer> idInfo, HttpServletRequest request) {
		String token = request.getHeader("AUTH");
		int uid = 0;
		if(jwtTokenProvider.validateToken(token)) {
			uid = jwtTokenProvider.getUserIdFromJwt(token);
		}
    	ChatRoom result =  matchRoomRepository.createAndSelectChatroom(uid, idInfo);
    	
		return new ResponseEntity<>(new 
				Response<>(StatusCode.OK, ResponseMessage.CREATE_CHATROOM_SUCCESS, result), HttpStatus.OK);
    }

    @GetMapping("/user")
    @ResponseBody
    public String getUserInfo() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        return name;
    }
}
