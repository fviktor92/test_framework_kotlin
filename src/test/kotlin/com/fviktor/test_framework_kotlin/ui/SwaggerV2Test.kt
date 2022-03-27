package com.fviktor.test_framework_kotlin.ui

import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.Selenide
import com.fviktor.test_framework_kotlin.common.ResourceFileReader
import com.fviktor.test_framework_kotlin.ui.pageobjects.SwaggerV2Page
import com.google.common.net.MediaType
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.openqa.selenium.chromium.ChromiumDriver


class SwaggerV2Test : BaseUiTest()
{
    private lateinit var driver: ChromiumDriver

    @BeforeEach
    fun openPage()
    {
        Selenide.open("https://petstore.swagger.io/")
        driver = Selenide.webdriver().`object`() as ChromiumDriver
    }

    @Test
    fun addingNewPetToStoreShouldSucceed()
    {
        val petRequestBody: String = ResourceFileReader.getResourceAsText(SwaggerV2Test::class.java, "pet_request.json")
        val petResponse: String = ResourceFileReader.getResourceAsText(SwaggerV2Test::class.java, "pet_response.json")
        NetworkInterceptorUtil.interceptPostRequestWithBody(
            driver,
            "/pet",
            petRequestBody,
            MediaType.JSON_UTF_8,
            200,
            petResponse
        )

        val swagger = SwaggerV2Page()
        swagger.toggleOperationsBlock("operations-tag-store")
            .toggleOperationBlock("operations-pet-addPet")
            .clickTryItOut()
        swagger.setParameterTextAreaValue("body", petRequestBody)
            .clickExecute()

        swagger.getResponseStatusCode().shouldHave(text("200"))
        swagger.getResponseBody().shouldHave(text(petResponse))
    }

    @Test
    fun updatingAnExistingPetShouldSucceed()
    {
        val petRequestBody: String = ResourceFileReader.getResourceAsText(SwaggerV2Test::class.java, "pet_request.json")
        val petResponse: String = ResourceFileReader.getResourceAsText(SwaggerV2Test::class.java, "pet_response.json")
        NetworkInterceptorUtil.interceptPutRequestWithBody(
            driver,
            "/pet",
            petRequestBody,
            MediaType.JSON_UTF_8,
            200,
            petResponse
        )

        val swagger = SwaggerV2Page()
        swagger.toggleOperationsBlock("operations-tag-store")
            .toggleOperationBlock("operations-pet-updatePet")
            .clickTryItOut()
        swagger.setParameterTextAreaValue("body", petRequestBody)
            .clickExecute()

        swagger.getResponseStatusCode().shouldHave(text("200"))
        swagger.getResponseBody().shouldHave(text(petResponse))
    }
}