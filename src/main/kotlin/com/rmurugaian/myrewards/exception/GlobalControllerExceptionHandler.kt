package com.rmurugaian.myrewards.exception

import com.rmurugaian.myrewards.BalanceException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import java.time.ZonedDateTime

@RestControllerAdvice
internal class GlobalControllerExceptionHandler {

    private val logger = LoggerFactory.getLogger(GlobalControllerExceptionHandler::class.java)

    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(NOT_FOUND)
    @ResponseBody
    fun handleNotFoundExceptions(ex: Exception): HttpErrorInfo {
        return createHttpErrorInfo(NOT_FOUND, ex)
    }

    @ExceptionHandler(value = [InvalidInputException::class, HttpMessageNotReadableException::class, IllegalStateException::class])
    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    fun handleInvalidInputException(ex: Exception): HttpErrorInfo {
        return createHttpErrorInfo(BAD_REQUEST, ex)
    }

    @ExceptionHandler(BalanceException::class)
    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    fun handleBalanceException(ex: Exception): HttpErrorInfo {
        return createHttpErrorInfo(BAD_REQUEST, ex)
    }

    private fun createHttpErrorInfo(httpStatus: HttpStatus, ex: Exception): HttpErrorInfo {
        val path = (RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes).request.requestURI
        val message = ex.message
        logger.debug("Returning HTTP status: {} for path: {}, message: {}", httpStatus, path, message)
        return HttpErrorInfo(ZonedDateTime.now(), path, message, httpStatus)
    }
}
