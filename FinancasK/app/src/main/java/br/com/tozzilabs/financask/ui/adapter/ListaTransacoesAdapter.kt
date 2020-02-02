package br.com.tozzilabs.financask.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.core.content.ContextCompat
import br.com.tozzilabs.financask.R
import br.com.tozzilabs.financask.extension.formatParaBrasileiro
import br.com.tozzilabs.financask.extension.formataParaBrasileiro
import br.com.tozzilabs.financask.extension.limitaEmAte
import br.com.tozzilabs.financask.model.Tipo
import br.com.tozzilabs.financask.model.Transacao
import kotlinx.android.synthetic.main.transacao_item.view.*

class ListaTransacoesAdapter(private val transacoes: List<Transacao>,
     private val context: Context) : BaseAdapter() {

    private val limiteDaCategoria = 14

    override fun getView(posicao: Int, view: View?, parent: ViewGroup?): View {
        val viewCreated = LayoutInflater.from(context).inflate(R.layout.transacao_item, parent, false)

        val transacao = transacoes[posicao]

        adicionaValor(transacao, viewCreated)
        adicionaIcone(transacao, viewCreated)
        adicionaCategoria(viewCreated, transacao)
        adicionaData(viewCreated, transacao)



        return viewCreated
    }

    private fun adicionaData(
        viewCreated: View,
        transacao: Transacao
    ) {
        viewCreated.transacao_data.text = transacao.data.formatParaBrasileiro()
    }

    private fun adicionaCategoria(
        viewCreated: View,
        transacao: Transacao
    ) {
        viewCreated.transacao_categoria.text = transacao.categoria.limitaEmAte(limiteDaCategoria)
    }

    private fun adicionaValor(transacao: Transacao, viewCriada: View) {
        val cor: Int = corPor(transacao.tipo)
        viewCriada.transacao_valor
            .setTextColor(cor)
        viewCriada.transacao_valor.text = transacao.valor
            .formataParaBrasileiro()
    }

    private fun corPor(tipo: Tipo) : Int {
        if (tipo == Tipo.RECEITA) {
            return ContextCompat.getColor(context, R.color.receita)
        }
        return ContextCompat.getColor(context, R.color.despesa)
    }

    private fun adicionaIcone(transacao: Transacao, viewCriada: View) {
        val icone = iconePor(transacao.tipo)
        viewCriada.transacao_icone
            .setBackgroundResource(icone)
    }

    private fun iconePor(tipo: Tipo) : Int {
        return if (tipo == Tipo.RECEITA) {
            R.drawable.icone_transacao_item_receita
        } else {
            R.drawable.icone_transacao_item_despesa
        }
    }

    override fun getItem(posicao: Int): Transacao {
        return transacoes[posicao]
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return transacoes.size
    }
}