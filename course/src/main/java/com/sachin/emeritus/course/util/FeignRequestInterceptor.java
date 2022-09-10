package com.sachin.emeritus.course.util;

import com.sachin.emeritus.commonlib.security.JwtUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;

public class FeignRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        template.header("Authorization", JwtUtil.getAuthHeader());
    }
}
