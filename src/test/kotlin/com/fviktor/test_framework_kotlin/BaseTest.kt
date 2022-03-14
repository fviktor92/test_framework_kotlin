package com.fviktor.test_framework_kotlin

import com.codeborne.selenide.Configuration
import com.codeborne.selenide.logevents.SelenideLogger
import io.qameta.allure.selenide.AllureSelenide
import org.junit.jupiter.api.BeforeEach

open class BaseTest
{
    @BeforeEach
    fun setUpAll() {
        Configuration.browserSize = "1336x768"
        SelenideLogger.addListener("allure", AllureSelenide())
    }
}