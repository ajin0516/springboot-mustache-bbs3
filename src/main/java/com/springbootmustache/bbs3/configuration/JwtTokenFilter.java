package com.springbootmustache.bbs3.configuration;

import com.springbootmustache.bbs3.domain.User;
import com.springbootmustache.bbs3.service.UserService;
import com.springbootmustache.bbs3.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter { // 요청 할 때마다 실행

    private final UserService userService;
    private final String secretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 아직 token 상태 아님
        final String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION); // 헤더에서 꺼내기
        log.info("authorizationHeader={}",authorizationHeader);

        // 권한 주거나 안주기(개찰구 역할)

        // 언제 막을까?
        // 1 토큰이 없거나 적절하지 않은 토큰일 떄
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            log.info("Token이 없거나 잘못되었습니다.");
            filterChain.doFilter(request, response);
            return;
        }

        String token = authorizationHeader.split(" ")[1];
        log.info("token={}",token);
        // 2 만료된 토큰일 떄
        if(JwtTokenUtil.isExpired(token, secretKey)){
            filterChain.doFilter(request,response);
            return;
        }

        // Token에서 userName 꺼내기
        String userName = JwtTokenUtil.getUserName(token, secretKey);
        log.info("userName={}",userName);

        User user = userService.getUserByUserName(userName);
        log.info("Token Filter userName:{}", userName);


        // 문 열어주기
        // 아이디, 패스워드 데이터를 파싱하여 인증 요청을 위임하는 필터 - 아이디, 패스워드를 이요한 인증을 담당
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken("", null, List.of(new SimpleGrantedAuthority("USER")));
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken); // 세션에서 계속 사용하기 위해 사용자 정보 저장, 권한 부여
        filterChain.doFilter(request,response);
    }
}
