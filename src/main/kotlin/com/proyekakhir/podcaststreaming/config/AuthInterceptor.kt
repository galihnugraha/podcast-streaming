package com.proyekakhir.podcaststreaming.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.proyekakhir.podcaststreaming.domain.dto.response.ResMessageDto
import com.proyekakhir.podcaststreaming.utils.JwtGenerator
import io.jsonwebtoken.ExpiredJwtException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor

@Component
class AuthInterceptor: HandlerInterceptor {
    override fun preHandle(
            request: HttpServletRequest,
            response: HttpServletResponse,
            handler: Any
    ):Boolean {
        val token = request.getHeader("token")

        if (token == null || token == "") {
            val body:ResMessageDto<String> = ResMessageDto(
                    status = "403",
                    message = "you dont have permission",
                    data = null
            )
            internalServerError(body, response)
            return false
        }

        try {
            JwtGenerator().decodeJwt(token)["id"]
        } catch (e: ExpiredJwtException) {
            e.printStackTrace()
            val body:ResMessageDto<String> = ResMessageDto(
                    status = "401",
                    message = "invalid token",
                    data = null
            )
            internalServerError(body, response)
            return false
        }

        return super.preHandle(request, response, handler)
    }

    private fun internalServerError(
            body:ResMessageDto<String>,
            response: HttpServletResponse
    ): HttpServletResponse {
        response.status = HttpStatus.FORBIDDEN.value()
        response.contentType = "application/json"
        response.writer.write(convertObjectToJson(body))

        return response
    }

    private fun convertObjectToJson(
            dto: ResMessageDto<String>
    ):String {
        return ObjectMapper().writeValueAsString(dto)
    }
}