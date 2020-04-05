package org.senac.convertertemperatura.conversor

import org.jetbrains.annotations.NotNull

class Conversor(val value : Int, val convertedValueType : SistemaMedicao) {

    private var registeredConverterExpression : HashMap<SistemaMedicao, (Int) -> Double>

    init {
        registeredConverterExpression = mapOf(
            SistemaMedicao.CELSIOS to {v: Int -> (v - 32) / 1.8},
            SistemaMedicao.FAHRENHEIT to {v: Int -> (v * 1.8) + 32}
        ) as HashMap<SistemaMedicao, (Int) -> Double>
    }

    fun convert() : Double? {
        var convertExpression = registeredConverterExpression.get(convertedValueType)
        return convertExpression?.invoke(value)
    }

    fun convertAsString(@NotNull pattern : String) : String {
        return pattern.format(convert()).toString() + convertedValueType.symbol
    }
}