package br.com.tozzilabs.financask.ui.activity

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import br.com.tozzilabs.financask.R
import br.com.tozzilabs.financask.delegate.TransacaoDelegate
import br.com.tozzilabs.financask.model.Tipo
import br.com.tozzilabs.financask.model.Transacao
import br.com.tozzilabs.financask.ui.ResumoView
import br.com.tozzilabs.financask.ui.adapter.ListaTransacoesAdapter
import br.com.tozzilabs.financask.ui.dialog.AdicionaTransacaoDialog
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import java.math.BigDecimal
import java.util.*

class ListaTransacoesActivity : AppCompatActivity() {

    val transacoes : MutableList<Transacao> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        configuraResumo()
        configuraLista()
        configuraFab()
    }

    private fun configuraFab() {
        lista_transacoes_adiciona_receita.setOnClickListener {
            chamaDialogDeAdicao(Tipo.RECEITA)
        }

        lista_transacoes_adiciona_despesa.setOnClickListener {
            chamaDialogDeAdicao(Tipo.DESPESA)
        }
    }

    private fun chamaDialogDeAdicao(tipo: Tipo) {
        AdicionaTransacaoDialog(window.decorView as ViewGroup, this)
            .adicionaTransacaoDialog(tipo, object : TransacaoDelegate {
                override fun delegate(trasacao: Transacao) {
                    atualizaTransacoes(transacao = trasacao)
                    lista_transacoes_adiciona_menu.close(true)
                }
            })
    }


    private fun atualizaTransacoes(transacao: Transacao) {
        transacoes.add(transacao)
        configuraLista()
        configuraResumo()
    }

    private fun configuraResumo() {
        val view: View = window.decorView
        ResumoView(this, view, transacoes).apply {
            atualiza()
        }
    }

    private fun configuraLista() {
        lista_transacoes_listview.adapter = ListaTransacoesAdapter(transacoes, this)
    }

    private fun transacoesDeExemplo(): List<Transacao> {
        return listOf(

            Transacao(
                tipo = Tipo.DESPESA,
                data = Calendar.getInstance(),
                valor = BigDecimal(2000.00)
            ),

            Transacao(
                tipo = Tipo.DESPESA,
                categoria = "almoço de final de semana",
                data = Calendar.getInstance(),
                valor = BigDecimal(20.0)
            ),

            Transacao(
                valor = BigDecimal(3050),
                categoria = "Economia",
                tipo = Tipo.RECEITA
            ),

            Transacao(
                valor = BigDecimal(500.0),
                categoria = "Prêmio",
                tipo = Tipo.RECEITA
            )
        )
    }
}