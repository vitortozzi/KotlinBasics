package br.com.tozzilabs.financask.delegate

import br.com.tozzilabs.financask.model.Transacao

interface TransacaoDelegate {

    fun delegate(trasacao: Transacao)
}