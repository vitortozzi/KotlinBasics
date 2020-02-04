package br.com.tozzilabs.financask.model

import br.com.tozzilabs.financask.extension.formataParaBrasileiro
import kotlinx.android.synthetic.main.resumo_card.view.*
import java.math.BigDecimal

class Resumo(private val transacoes: List<Transacao>) {

    fun receita() : BigDecimal {
        var totalReceitas = BigDecimal.ZERO
        for (transacao in transacoes) {
            if (transacao.tipo == Tipo.RECEITA) {
                totalReceitas = totalReceitas.plus(transacao.valor)
            }
        }
        return totalReceitas
    }

    fun despesa() : BigDecimal {
        var totalDespesas = BigDecimal.ZERO
        for (transacao in transacoes) {
            if (transacao.tipo == Tipo.DESPESA) {
                totalDespesas = totalDespesas.plus(transacao.valor)
            }
        }
        return totalDespesas
    }

    fun total() : BigDecimal {
        return receita().subtract(despesa())
    }
}