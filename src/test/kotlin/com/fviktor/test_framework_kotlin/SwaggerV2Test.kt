package com.fviktor.test_framework_kotlin

import com.codeborne.selenide.Selenide
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import pageobjects.SwaggerV2Page


class SwaggerV2Test : BaseTest()
{
    @BeforeEach
    fun openPage()
    {
        Selenide.open("https://petstore.swagger.io/")
    }

    @Test
    fun test()
    {
        val swagger = SwaggerV2Page()
        swagger.toggleOperationsBlock("operations-tag-pet")
    }
}