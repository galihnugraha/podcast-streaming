package com.proyekakhir.podcaststreaming.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class PathMatchingInterceptor(
        private val authInterceptor: AuthInterceptor,
        private val requestInterceptor: RequestInterceptor
): WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(requestInterceptor)
        registry.addInterceptor(authInterceptor).excludePathPatterns(
                "/v1/api/login",
                "/v1/api/user",
                "/v1/api/user/delete"
        )
    }
}