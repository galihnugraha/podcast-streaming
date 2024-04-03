package com.proyekakhir.podcaststreaming.exception

import com.proyekakhir.podcaststreaming.domain.dto.response.ResMessageDto
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
class ErrorHandler {
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleArgumentNotValidException(exception: MethodArgumentNotValidException) : ResponseEntity<Any> {
        val errors = mutableListOf<String>()
        exception.bindingResult.fieldErrors.forEach {
            errors.add(it.defaultMessage!!)
        }
        val result = mapOf<String, Any>("status" to "F", "error" to "field", "message" to errors)
        return ResponseEntity.badRequest().body(result)
    }

    @ExceptionHandler(DataNotFound::class)
    fun handleDataNotFound(exception: RuntimeException) : ResponseEntity<ResMessageDto<*>> {
        exception.printStackTrace()
        return ResponseEntity.badRequest().body(ResMessageDto<Any?>(
                status = "F",
                message = exception.message.toString()
        ))
    }

    @ExceptionHandler(DataExist::class)
    fun handleDataExist(exception: RuntimeException) : ResponseEntity<ResMessageDto<*>> {
        exception.printStackTrace()
        return ResponseEntity.badRequest().body(ResMessageDto<Any?>(
                status = "F",
                message = exception.message.toString()
        ))
    }

    @ExceptionHandler(InvalidToken::class)
    fun handleInvalidToken(exception: RuntimeException) : ResponseEntity<ResMessageDto<*>> {
        exception.printStackTrace()
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ResMessageDto<Any?>(
                status = "F",
                message = exception.message.toString()
        ))
    }
}