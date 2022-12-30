package com.jiang.reggie_take_out.filter;

import com.alibaba.fastjson.JSON;
import com.jiang.reggie_take_out.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 检查用户是否完成登录
 */
@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter {
    //路径匹配器，支持通配符
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();
    
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) 
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String requestURI = request.getRequestURI();//获取本次请求uri
        
        //定义不需要处理的路径
        String[] urls = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**"
        };
        
        boolean check = check(urls,requestURI);//判断请求是否需要处理
        
        if(check){  //不需要处理，则放行
            log.info("本次请求不需要处理：{}", requestURI);
            filterChain.doFilter(request,response);
            return;
        }
        
        //判断登陆状态，如果已登录，则直接放行
        if(request.getSession().getAttribute("emp") != null){
            log.info("用户已登录,用户id为：{}", request.getSession().getAttribute("emp"));
            filterChain.doFilter(request,response);
            return;
        }
        
        //如果未登录则返回未登录结果
        log.info("用户未登录");
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        
    }
    
    public boolean check(String[] urls, String requestURI){
        for(String url: urls){
            boolean match = PATH_MATCHER.match(url, requestURI);
            if(match){
                return true;
            }
        }
        return false;
    }
}
