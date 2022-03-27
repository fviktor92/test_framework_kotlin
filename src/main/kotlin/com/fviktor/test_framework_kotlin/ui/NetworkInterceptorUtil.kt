package com.fviktor.test_framework_kotlin.ui

import com.google.common.net.MediaType
import org.openqa.selenium.WebDriver
import org.openqa.selenium.devtools.NetworkInterceptor
import org.openqa.selenium.remote.http.Contents
import org.openqa.selenium.remote.http.HttpHandler
import org.openqa.selenium.remote.http.HttpMethod
import org.openqa.selenium.remote.http.HttpResponse
import org.openqa.selenium.remote.http.Route

class NetworkInterceptorUtil
{
    companion object
    {
        fun interceptPostRequestWithBody(
            driver: WebDriver,
            endpoint: String,
            requestBody: String,
            responseContentType: MediaType,
            responseStatusCode: Int,
            responseContent: String
        )
        {
            interceptRequestWithBody(
                driver,
                HttpMethod.POST,
                endpoint,
                requestBody,
                responseContentType,
                responseStatusCode,
                responseContent
            )
        }

        /**
         * FIXME: Not possible to intercept PUT methods, because [HttpMethod] does not contain a PUT value
         */
        fun interceptPutRequestWithBody(
            driver: WebDriver,
            endpoint: String,
            requestBody: String,
            responseContentType: MediaType,
            responseStatusCode: Int,
            responseContent: String
        )
        {
            interceptRequestWithBody(
                driver,
                HttpMethod.GET,
                endpoint,
                requestBody,
                responseContentType,
                responseStatusCode,
                responseContent
            )
        }

        private fun interceptRequestWithBody(
            driver: WebDriver,
            httpMethod: HttpMethod,
            endpoint: String,
            requestBody: String,
            responseContentType: MediaType,
            responseStatusCode: Int,
            responseContent: String
        )
        {
            NetworkInterceptor(
                driver,
                Route.matching { req ->
                    req.method == httpMethod && req.uri.endsWith(endpoint) && Contents.utf8String(req.content) == requestBody
                }.to {
                    HttpHandler {
                        HttpResponse().setStatus(responseStatusCode)
                            .setHeader("Content-Type", responseContentType.toString())
                            .setContent(Contents.utf8String(responseContent))
                    }
                })
        }
    }
}