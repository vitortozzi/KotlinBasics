package br.com.tozzilabs.financask.extension

fun String.limitaEmAte(caracteres: Int) : String{
    if(this.length > caracteres){
        val startIndex = 0
        return "${this.substring(startIndex, caracteres)}..."
    }
    return this
}