package com.sist.web.security;

import java.io.IOException;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler{

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String errMsg="";
		
		try
		{
			if(exception instanceof BadCredentialsException)
			{
				errMsg="아이디나 비밀번호가 틀립니다!!";
			}
			else if(exception instanceof DisabledException)
			{
				errMsg="휴먼 계정입니다";
			}
		}catch(Exception ex) {}
		
		request.setAttribute("message", errMsg);
		request.getRequestDispatcher("/member/login").forward(request, response);
		/*
		 *   sendRedirect : 다른 파일로 이동 => request를 초기화 
		 *       => 윈도우 => new 
		 *       => 새로운 파일을 생성 
		 *       => HttpServletResponse 
		 *   forward : 기존의 파일을 유지하기 위해서 만든 
		 *       => request를 전송하는 경우
		 *       => 기존의 파일을 덮어쓴다  
		 *       => RequestDispatcher , PageContext
		 *          | request공유 => forward , include 
		 */
		
	}

}
