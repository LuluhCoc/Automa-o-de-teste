fun operacao(a: Int, b: Int, c: String){
    /*
    when(c){
        "Soma" -> return a + b
        "Subtração" -> {
            return a - b
        }
        else -> {
            println("Operação incorreta")
            return 0
        }

    }
    */
    when{
        a > 0 && b > 0 -> {
            println("Variaveis maiores que zero")
        }

    }
    //Range - Intervalo de valores - ..
    when {
        a in 1..99 && b >0 -> {
            println("Século")
        }
    }
}


fun main(){
    operacao(10, 10, "Soma")
    operacao(10, 10, "Subtração")

    operacao(10, 10, "Somaa")
}