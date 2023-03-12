package com.day.authority.core.filter;

import com.day.authority.common.util.StringUtil;
import com.day.authority.core.wrapper.XssHttpServletRequestWrapper1;
import org.apache.http.entity.ContentType;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author DayXhYao
 * @date 2023/3/12 12:59
 */
@Component
@WebFilter(filterName = "XssFilter", urlPatterns = "/*")
public class XssFilter implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String contentType = request.getContentType();
        //对文件上传不进行xss过滤
        if (StringUtil.isNotEmpty(contentType) && contentType.contains(ContentType.MULTIPART_FORM_DATA.getMimeType())) {
            chain.doFilter(request, response);
        } else {
            XssHttpServletRequestWrapper1 xssRequest = new XssHttpServletRequestWrapper1((HttpServletRequest) request);
            chain.doFilter(xssRequest, response);
        }
    }


}
