package com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.config;

import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.entity.Staff;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.exception.AuthenException;
import com.example.g4.FA24_SE1854_SWP391_G4_KoiPondConstructionOrderingSystem.service.TokenService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.util.List;

@Component
public class Filter extends OncePerRequestFilter {
    @Autowired
    TokenService tokenService;
    @Autowired
    @Qualifier("handlerExceptionResolver")
    HandlerExceptionResolver resolver;

    private final List<String> AUTH_PERMISSION = List.of(
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-resources/**",

            "/api/stafflogin",
            "/api/staffregister"

    );
    public boolean checkIsPublicAPI(String uri){
        //uri:/api/register

        //nếu gặp những cái api ở trên thì cho phép truy cập => true
        AntPathMatcher pathMatcher = new AntPathMatcher();
        //nếu ko thì check token => false
        return AUTH_PERMISSION.stream().anyMatch(pattern-> pathMatcher.match(pattern,uri));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // cho phép request có thể truy cập vào api(Controller)
        // filterChain.doFilter(request,response);

        //check api mà ng dùng yêu cầu có phải là public api hay không?

        boolean isPublicAPI = checkIsPublicAPI(request.getRequestURI());

        if(isPublicAPI){
            filterChain.doFilter(request,response);
        }else {
            String token = getToken(request);
            if(token == null){
                //ko cho phép truy cập
                resolver.resolveException(request,response,null, new AuthenException("Empty Token!!!"));

                return;
            }

            // => token
            //check xem token có đúng hay không => lấy thông tin account từ token
            Staff staff;
            try{
                staff=tokenService.getAccountByToken(token);
            }catch (ExpiredJwtException e){
                //response token hết hạn
                resolver.resolveException(request,response,null, new AuthenException("Expired Token!!!"));
                return;
            }catch (MalformedJwtException malformedJwtException){
                //response token sai
                resolver.resolveException(request,response,null, new AuthenException("Invalid Token!!!"));
                return;
            }

            //=> token chuẩn
            //cho phép truy cập
            //lưu lại thông tin account
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    staff,
                    token,
                    staff.getAuthorities()
            );
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            //token ok cho vào
            filterChain.doFilter(request,response);
        }
    }
    public String getToken(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        if(authHeader == null){
            return  null;
        }
        return authHeader.substring(7);
    }
}
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        filterChain.doFilter(request, response);
//    }

