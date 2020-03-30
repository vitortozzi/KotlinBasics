package br.com.tozzilabs.financask.ui.dialog

import android.app.DatePickerDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import br.com.tozzilabs.financask.R
import br.com.tozzilabs.financask.delegate.TransacaoDelegate
import br.com.tozzilabs.financask.extension.converteParaCalendar
import br.com.tozzilabs.financask.extension.formatParaBrasileiro
import br.com.tozzilabs.financask.model.Tipo
import br.com.tozzilabs.financask.model.Transacao
import kotlinx.android.synthetic.main.form_transacao.view.*
import java.math.BigDecimal
import java.util.*

 class AdicionaTransacaoDialog(
    private val viewGroup: ViewGroup?,
    private val context: Context
) {

    val viewCriada = criaLayout()

    fun adicionaTransacaoDialog(tipo: Tipo, transacaoDelegate: TransacaoDelegate) {

        configuraCampoCategoria(tipo)
        configuraCampoData()
        configuraFormulario(tipo, transacaoDelegate)
    }

     private val campoValor = viewCriada.form_transacao_valor
     private val campoData = viewCriada.form_transacao_data
     private val campoCategoria = viewCriada.form_transacao_categoria

     private fun configuraFormulario(tipo: Tipo, transacaoDelegate: TransacaoDelegate) {

        val titulo = tituloPor(tipo)

        AlertDialog.Builder(context)
            .setTitle(titulo)
            .setView(viewCriada)
            .setPositiveButton("Adicionar") { _, _ ->
                val valorEmTexto = campoValor.text.toString()
                val dataEmTexto = campoData.text.toString()
                val categoriaEmTexto = campoCategoria.selectedItem.toString()

                val valor = converteCampoValor(valorEmTexto)
                val data = dataEmTexto.converteParaCalendar();

                val transacao = Transacao(
                    tipo = tipo,
                    valor = valor,
                    data = data,
                    categoria = categoriaEmTexto
                )

                transacaoDelegate.delegate(transacao)

            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

     private fun tituloPor(tipo: Tipo): Int {
         if (tipo == Tipo.RECEITA) {
             return R.string.adiciona_receita
         } else {
             return R.string.adiciona_despesa
         }
     }

     private fun converteCampoValor(valorEmTexto: String): BigDecimal {
        return try {
            BigDecimal(valorEmTexto)
        } catch (exception: NumberFormatException) {
            Toast.makeText(
                context,
                "Falha na conversão do valor", Toast.LENGTH_LONG
            ).show()

            BigDecimal.ZERO
        }
    }

    private fun configuraCampoCategoria(tipo: Tipo) {

        val categorias = categoriasPo(tipo)
        val adapter = ArrayAdapter
            .createFromResource(
                context,
                categorias,
                android.R.layout.simple_spinner_dropdown_item
            )

        campoCategoria.adapter = adapter
    }

     private fun categoriasPo(tipo: Tipo): Int {
         if (tipo == Tipo.RECEITA) {
             return R.array.categorias_de_receita
         } else {
             return R.array.categorias_de_despesa
         }
     }

     private fun configuraCampoData() {
        val hoje = Calendar.getInstance()
        val ano = hoje.get(Calendar.YEAR)
        val mes = hoje.get(Calendar.MONTH)
        val dia = hoje.get(Calendar.DAY_OF_MONTH)

        campoData.setText(Calendar.getInstance().formatParaBrasileiro())
        campoData.setOnClickListener {
            DatePickerDialog(context, { _, ano, mes, dia ->
                    val dataSelecionada = Calendar.getInstance()
                    dataSelecionada.set(ano, mes, ano)
                    campoData.setText(dataSelecionada.formatParaBrasileiro())
                }
                , ano, mes, dia)
                .show()
        }
    }

    private fun criaLayout(): View {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.form_transacao, viewGroup, false)
        return view
    }

}