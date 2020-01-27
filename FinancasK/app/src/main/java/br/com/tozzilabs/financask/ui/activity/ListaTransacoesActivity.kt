package br.com.tozzilabs.financask.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.tozzilabs.financask.R
import br.com.tozzilabs.financask.ui.adapter.ListaTransacoesAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*

class ListaTransacoesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        val list : List<String> = listOf("Mercado - R$ 90,00", "Contas - R$ 470,00")
        val adapter = ListaTransacoesAdapter(list, this)
        lista_transacoes_listview.adapter = adapter
    }
}