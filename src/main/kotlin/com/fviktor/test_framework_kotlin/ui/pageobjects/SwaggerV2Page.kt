package com.fviktor.test_framework_kotlin.ui.pageobjects

import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import java.io.File

class SwaggerV2Page
{
    /**
     * Clicks an operations block by the given [id], which either expands or collapses the block of operations.
     */
    fun toggleOperationsBlock(id: String): SwaggerV2Page
    {
        `$`("#$id .expand-operation").click()
        return this
    }

    /**
     * Clicks an operation block by the given [id], which either expands or collapses the block of operation, that's supposed
     * to contain the 'Parameters' and 'Response' sections.
     */
    fun toggleOperationBlock(id: String): SwaggerV2Page
    {
        `$`("#$id .opblock-summary-control").click()
        return this
    }

    /**
     * Clicks the *Try it out* / *Cancel* button within the operation block.
     *
     * **Assumes that the operation block is expanded!**
     */
    fun clickTryItOut(index: Int = 0): SwaggerV2Page
    {
        `$`(".try-out__btn", index).click()
        return this
    }

    /**
     * Sets the given [value] in the [parameterName]'s text type input field.
     */
    fun setParameterTextValue(parameterName: String, value: String, index: Int = 0): SwaggerV2Page
    {
        getParametersRowWrapper(parameterName).`$`("input", index).setValue(value)
        return this
    }

    /**
     * Selects the options by the given [values] in the [parameterName]'s select field.
     */
    fun setParameterArrayValues(parameterName: String, vararg values: String, index: Int = 0): SwaggerV2Page
    {
        getParametersRowWrapper(parameterName).`$`("select", index).selectOptionByValue(*values)
        return this
    }

    /**
     * Sets the given [file] in the [parameterName]'s file type input field.
     */
    fun setParameterFileUploadValue(parameterName: String, file: File, index: Int = 0): SwaggerV2Page
    {
        getParametersRowWrapper(parameterName).`$`("input", index).uploadFile(file)
        return this
    }

    /**
     * Sets the given [value] in the [parameterName]'s textarea field.
     */
    fun setParameterTextAreaValue(parameterName: String, value: String, index: Int = 0): SwaggerV2Page
    {
        getParametersRowWrapper(parameterName).`$`("textarea", index).setValue(value)
        return this
    }

    /**
     * Clicks the *Example value* button for the given [parameterName]
     */
    fun clickParametersExampleValue(parameterName: String, index: Int = 0): SwaggerV2Page
    {
        getParametersRowWrapper(parameterName).`$`(".parameters button[data-name='example']", index).click()
        return this
    }

    /**
     * Clicks the *Model* button for the given [parameterName]
     */
    fun clickParametersModelValue(parameterName: String, index: Int = 0): SwaggerV2Page
    {
        getParametersRowWrapper(parameterName).`$`(".parameters button[data-name='model']", index).click()
        return this
    }

    /**
     * Clicks the *Execute* button within the operation block.
     *
     * **Assumes that an operation block is expanded and *Try it out* function is on.**
     */
    fun clickExecute(index: Int = 0): SwaggerV2Page
    {
        `$`("button.execute", index).click()
        return this
    }

    /**
     * Returns the Response status code element, which is supposed to be displayed after a request has been executed.
     */
    fun getResponseStatusCode(index: Int = 0): SelenideElement
    {
        return `$`(".response .response-col_status", index)
    }

    /**
     * Returns the Response body code element, which is supposed to be displayed after a request has been executed.
     */
    fun getResponseBody(index: Int = 0): SelenideElement
    {
        return `$`(".response code", index)
    }

    /**
     * Returns the table row wrapper element for the given [parameterName], which is supposed to contain all related
     * elements to it, such as parameter name, type, description, input field, example value, model.
     */
    private fun getParametersRowWrapper(parameterName: String)
            : SelenideElement
    {
        return `$`("tr[data-param-name='$parameterName']")
    }
}