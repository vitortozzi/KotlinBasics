package br.com.tozzilabs.financask.model

import br.com.tozzilabs.financask.extension.formataParaBrasileiro
import kotlinx.android.synthetic.main.resumo_card.view.*
import java.math.BigDecimal

class Resumo(private val transacoes: List<Transacao>) {

    val receita get() = somaPor(Tipo.RECEITA)

    val despesa get() = somaPor(Tipo.DESPESA)

    val total get() = receita.subtract(despesa)

    fun somaPor(tipo: Tipo) : BigDecimal {
        val somaPorTipo = transacoes
            .filter { transacao -> transacao.tipo == tipo }
            .sumByDouble { transacao -> transacao.valor.toDouble() }
        return BigDecimal(somaPorTipo)
    }

}