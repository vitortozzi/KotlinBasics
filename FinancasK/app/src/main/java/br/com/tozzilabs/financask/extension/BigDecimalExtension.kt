package br.com.tozzilabs.financask.extension

import android.text.TextUtils.replace
import java.math.BigDecimal
import java.text.DecimalFormat
import java.util.*

fun BigDecimal.formataParaBrasileiro() : String {
    val formatoBrasileiro = DecimalFormat
        .getCurrencyInstance(Locale("pt", "br"))
    return formatoBrasileiro
        .format( this)
    .replace("R$","R$ ")
}