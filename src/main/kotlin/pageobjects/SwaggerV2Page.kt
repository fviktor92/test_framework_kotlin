package pageobjects

import com.codeborne.selenide.Selenide.`$`

class SwaggerV2Page
{
    fun toggleOperationsBlock(id: String)
    {
        `$`("#$id .expand-operation").click()
    }

    fun toggleOperationBlock(id: String)
    {
        `$`("#$id .opblock-summary-control").click()
    }

    fun clickTryItOut(index: Int)
    {
        `$`(".try-out__btn", index).click()
    }

    fun setParamValue(parameterName: String, value: String)
    {
        `$`("tr[data-param-name='$parameterName'] input").setValue(value)
    }
}