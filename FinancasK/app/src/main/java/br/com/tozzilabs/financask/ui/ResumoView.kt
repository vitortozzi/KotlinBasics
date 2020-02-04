package br.com.tozzilabs.financask.ui

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import br.com.tozzilabs.financask.R
import br.com.tozzilabs.financask.extension.formataParaBrasileiro
import br.com.tozzilabs.financask.model.Resumo
import br.com.tozzilabs.financask.model.Transacao
import kotlinx.android.synthetic.main.resumo_card.view.*
import java.math.BigDecimal

class ResumoView(private val context: Context,
                 private val view: View,
                 transacoes: List<Transacao>) {

    private val resumo = Resumo(transacoes)

    private val corReceita = ContextCompat.getColor(context, R.color.receita)
    private val corDespesa = ContextCompat.getColor(context, R.color.despesa)

    fun atualiza() {
        adicionaReceita()
        adicionaDespesa()
        adicionaTotal()
    }

    private fun adicionaReceita() {
        val totalReceitas = resumo.receita
        with(view.resumo_card_receita){
            setTextColor(corReceita)
            text = totalReceitas.formataParaBrasileiro()
        }
    }

    private fun adicionaDespesa() {
        val totalDespesas = resumo.despesa
        with(view.resumo_card_despesa){
            setTextColor(corDespesa)
            text = totalDespesas.formataParaBrasileiro()
        }
    }

    private fun adicionaTotal() {
        val total = resumo.total
        val cor = corPor(total)
        with(view.resumo_card_total) {
            setTextColor(cor)
            text = total.formataParaBrasileiro()
        }
    }

    private fun corPor(total: BigDecimal): Int {
        if (total >= BigDecimal.ZERO) {
            return corReceita
        }
        return  corDespesa
    }
}