package com.fviktor.test_framework_kotlin.ui

import com.codeborne.selenide.Configuration
import com.fviktor.test_framework_kotlin.BaseTest
import org.junit.jupiter.api.BeforeEach

open class BaseUiTest : BaseTest()
{
    @BeforeEach
    fun beforeEachUiTest()
    {
        Configuration.browserSize = "1336x768"
    }
}