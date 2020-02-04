package br.com.tozzilabs.financask.ui.activity

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import br.com.tozzilabs.financask.R
import br.com.tozzilabs.financask.extension.formatParaBrasileiro
import br.com.tozzilabs.financask.model.Tipo
import br.com.tozzilabs.financask.model.Transacao
import br.com.tozzilabs.financask.ui.ResumoView
import br.com.tozzilabs.financask.ui.adapter.ListaTransacoesAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import kotlinx.android.synthetic.main.form_transacao.view.*
import java.math.BigDecimal
import java.util.*

class ListaTransacoesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        val transacoes : List<Transacao> = transacoesDeExemplo()

        configuraResumo(transacoes)
        configuraLista(transacoes)

        lista_transacoes_adiciona_receita.setOnClickListener {
            val view = LayoutInflater.from(this)
                .inflate(R.layout.form_transacao, window.decorView as ViewGroup, false)

            val adapter = ArrayAdapter
                .createFromResource(this,
                    R.array.categorias_de_receita,
                    android.R.layout.simple_spinner_dropdown_item)

            view.form_transacao_categoria.adapter = adapter

            with(view.form_transacao_data) {

                val ano = 2017
                val mes = 9
                val dia = 18

                setText(Calendar.getInstance().formatParaBrasileiro())
                setOnClickListener { DatePickerDialog(this@ListaTransacoesActivity,
                    DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                        val dataSelecionada = Calendar.getInstance()
                        dataSelecionada.set(year, month, dayOfMonth)
                        setText(dataSelecionada.formatParaBrasileiro()) }
                    , ano, mes, dia)
                    .show() }
            }



            AlertDialog.Builder(this)
                .setTitle(R.string.adiciona_receita)
                .setView(view)
                .setPositiveButton("Adicionar", null)
                .setNegativeButton("Cancelar", null)
                .show()
        }
    }

    private fun configuraResumo(transacoes: List<Transacao>) {
        val view: View = window.decorView
        ResumoView(this, view, transacoes).apply {
            atualiza()
        }
    }

    private fun configuraLista(list: List<Transacao>) {
        lista_transacoes_listview.adapter = ListaTransacoesAdapter(list, this)
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