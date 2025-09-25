package com.arielZarate.fake_api_hexagonal.interfaces.error

import com.arielZarate.fake_api_hexagonal.interfaces.error.exception.CustomException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
class GlobalExceptionHandler {


    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(e: IllegalArgumentException): ResponseEntity<ApiErrorResponse> {
        val error = ApiErrorResponse(
            type = null,
            title = "Bad Request",
            status = HttpStatus.BAD_REQUEST.value(),
            detail = e.message
        )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error)
    }

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNoSuchElementException(e: NoSuchElementException): ResponseEntity<ApiErrorResponse> {

        val error = ApiErrorResponse(
            type = null,
            title = "Resource Not Found",
            status = HttpStatus.NOT_FOUND.value(),
            detail = e.message
        )
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error)
    }

    @ExceptionHandler(CustomException::class)
    fun handleCustomException(e: CustomException): ResponseEntity<ApiErrorResponse> {
        val error = ApiErrorResponse(
            type = null,
            title = "Generic Error",
            status = HttpStatus.CONFLICT.value(),
            detail = e.message
        )
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error)
    }

    @ExceptionHandler(Exception::class)
    fun handleGeneralException(e: Exception): ResponseEntity<ApiErrorResponse> {
        val error = ApiErrorResponse(
            type = null,
            title = "",
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            detail = e.message
        )
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error)
    }
}


