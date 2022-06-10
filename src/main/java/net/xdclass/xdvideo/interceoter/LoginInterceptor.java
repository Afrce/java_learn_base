package net.xdclass.xdvideo.interceoter;

import io.jsonwebtoken.Claims;
import net.xdclass.xdvideo.exceiption.JwtException;
import net.xdclass.xdvideo.utils.JwtUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 进入控制器之前的拦截
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");

        if (token == null) {
            token = request.getParameter("token");
        }

        if (token == null) {
            throw new JwtException("请先登录");
        }

        Claims claims = JwtUtils.parseToken(token);
        if (claims == null) {
            throw new JwtException("登录失效，请先登录");
        }
        Integer userId = (Integer)claims.get("id");
        String name = (String) claims.get("name");
        request.setAttribute("id", userId);
        request.setAttribute("name", name);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
