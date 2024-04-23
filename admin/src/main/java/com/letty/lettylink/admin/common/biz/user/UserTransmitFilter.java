package com.letty.lettylink.admin.common.biz.user;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.server.HttpServerResponse;
import com.alibaba.fastjson2.JSON;
import com.google.common.collect.Lists;
import com.letty.lettylink.admin.common.convention.exception.ClientException;
import com.letty.lettylink.admin.common.convention.result.Results;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.List;
import java.util.Objects;

import static com.letty.lettylink.admin.common.enums.UserErrorCodeEnum.USER_TOKEN_FAIL;
import static java.nio.charset.StandardCharsets.UTF_8;

@RequiredArgsConstructor
public class UserTransmitFilter implements Filter {

    private final StringRedisTemplate stringRedisTemplate;

    private static final List<String> IGNORE_URI = Lists.newArrayList(
            "/api/short-link/admin/v1/user/login",
            "/api/short-link/admin/v1/user/has-username",
            "/api/short-link/admin/v1/user"
    );

    @SneakyThrows
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String requestURI = httpServletRequest.getRequestURI();
        if (!IGNORE_URI.contains(requestURI)) {
            String method = httpServletRequest.getMethod();
            // 判断请求路径和方法
            if (!(Objects.equals(requestURI, "/api/short-link/admin/v1/user") && Objects.equals(method, "POST"))) {
                String username = httpServletRequest.getHeader("username");
                String token = httpServletRequest.getHeader("token");
                if (!StrUtil.isAllNotBlank(username, token)) {
                    returnJson((HttpServletResponse)servletResponse, JSON.toJSONString(Results.failure(new ClientException(USER_TOKEN_FAIL))));
                    return;
                }
                Object userInfoJsonStr = null;
                try {
                   userInfoJsonStr = stringRedisTemplate.opsForHash().get("login_" + username, token);
                   if (userInfoJsonStr == null) {
                       throw new ClientException(USER_TOKEN_FAIL);

                   }
                } catch (Exception ex) {
                    returnJson((HttpServletResponse)servletResponse, JSON.toJSONString(Results.failure(new ClientException(USER_TOKEN_FAIL))));
                    return;
                }
                    UserInfoDTO userInfoDTO = JSON.parseObject(userInfoJsonStr.toString(), UserInfoDTO.class);
                    UserContext.setUser(userInfoDTO);
            }

        }
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            // 防止内存泄漏
            UserContext.removeUser();
        }
    }

    /**
     * 返回客户端数据 因为全局异常处理器没办法拦截到filter的异常
     */
    private void returnJson(HttpServletResponse response, String json) throws Exception {
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(json);
        } catch (IOException e) {

        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
