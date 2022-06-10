package net.xdclass.xdvideo.interceoter;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import net.xdclass.xdvideo.exceiption.JwtException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Slf4j
// 自定义的登录过滤器
public class JwtFilter extends BasicHttpAuthenticationFilter {

    private static  String errorMessage = "请先登录";

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        String token = WebUtils.toHttp(request).getHeader("token"); // 获取到对应的token

        try {
            if (token != null) {
                executeLogin(request, response); // 执行登录
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            errorMessage = e.getMessage();
            return false;
        }

        return true;
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        String token = WebUtils.toHttp(request).getHeader("token"); // 获取到对应的token
        JWTToken jwtToken = new JWTToken(token);
        getSubject(request, response).login(jwtToken);
        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpServletResponse.setContentType("application/json;charset=utf-8");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 401);
        jsonObject.put("msg", errorMessage);
        PrintWriter out = response.getWriter();
        out.println(jsonObject);
        out.flush();
        out.close();
        return false;
    }
}
