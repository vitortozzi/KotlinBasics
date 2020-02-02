package br.com.tozzilabs.financask.extension

import java.text.SimpleDateFormat
import java.util.*

fun Calendar.formatParaBrasileiro() : String{
    return SimpleDateFormat("dd/MM/yyyy").format(this.time)
}