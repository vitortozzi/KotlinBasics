package br.com.tozzilabs.financask.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.tozzilabs.financask.R
import br.com.tozzilabs.financask.model.Tipo
import br.com.tozzilabs.financask.model.Transacao
import br.com.tozzilabs.financask.ui.adapter.ListaTransacoesAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import java.math.BigDecimal
import java.util.*

class ListaTransacoesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        val transacoes : List<Transacao> = transacoesDeExemplo()

        configuraLista(transacoes)
    }

    private fun configuraLista(list: List<Transacao>) {
        lista_transacoes_listview.adapter = ListaTransacoesAdapter(list, this)
    }

    private fun transacoesDeExemplo(): List<Transacao> {
        return listOf(

            Transacao(
                tipo = Tipo.DESPESA,
                data = Calendar.getInstance(),
                valor = BigDecimal(0.25)
            ),

            Transacao(
                tipo = Tipo.DESPESA,
                categoria = "almoço de final de semana",
                data = Calendar.getInstance(),
                valor = BigDecimal(20.5)
            ),

            Transacao(
                valor = BigDecimal(100),
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