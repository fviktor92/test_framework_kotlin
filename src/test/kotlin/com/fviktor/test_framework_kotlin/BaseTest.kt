package com.fviktor.test_framework_kotlin

import com.codeborne.selenide.logevents.SelenideLogger
import io.qameta.allure.selenide.AllureSelenide
import org.junit.jupiter.api.BeforeEach

open class BaseTest
{
    @BeforeEach
    fun beforeEachTest()
    {
        SelenideLogger.addListener("allure", AllureSelenide())
    }
}