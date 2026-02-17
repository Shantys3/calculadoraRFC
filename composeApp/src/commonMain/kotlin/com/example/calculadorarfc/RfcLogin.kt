package com.example.calculadorarfc

object RfcLogic {
    private val PALABRAS_INCONVENIENTES = setOf(
        "BUEI", "BUEY", "CACA", "CACO", "CAGA", "CAGO", "CAKA", "CAKO",
        "COGE", "COJA", "COJE", "COJI", "COJO", "CULO", "FETO", "GUEY",
        "JOTO", "KACA", "KACO", "KAGA", "KAGO", "KOGE", "KOJO", "KAKA",
        "KULO", "LOCA", "LOCO", "MAMO", "MEAR", "MEON", "MION", "MOCO",
        "MULA", "PEDA", "PEDO", "PENE", "PUTA", "PUTO", "QULO", "RATA", "RUIN"
    )

    fun calcularRFC(
        nombre: String,
        paterno: String,
        materno: String,
        fecha: String
    ): String {
        val n = limpiarTexto(nombre)
        val p = limpiarTexto(paterno)
        val m = limpiarTexto(materno)

        val letra1 = if (p.isNotEmpty()) p.first() else '_'
        val letra2 = if (p.isNotEmpty()) buscarPrimeraVocalInterna(p) else '_'

        val letra3 = if (m.isEmpty()) 'X' else m.first()

        val letra4 = if (n.isEmpty()) '_' else obtenerLetraNombre(n)

        var base = "$letra1$letra2$letra3$letra4"

        if (!base.contains('_') && PALABRAS_INCONVENIENTES.contains(base)) {
            base = base.substring(0, 3) + "X"
        }

        val fechaFinal = if (fecha.length == 6) fecha else fecha.padEnd(6, '_')

        return "$base$fechaFinal"
    }

    private fun limpiarTexto(texto: String): String {
        return texto.uppercase()
            .replace("Á", "A").replace("É", "E").replace("Í", "I")
            .replace("Ó", "O").replace("Ú", "U").replace("Ñ", "X")
            .trim()
    }

    private fun buscarPrimeraVocalInterna(texto: String): Char {
        if (texto.length < 2) return 'X'
        val vocales = listOf('A', 'E', 'I', 'O', 'U')
        for (i in 1 until texto.length) {
            if (vocales.contains(texto[i])) return texto[i]
        }
        return 'X'
    }

    private fun obtenerLetraNombre(nombreCompleto: String): Char {
        val nombres = nombreCompleto.split(" ").filter { it.isNotEmpty() }
        if (nombres.isEmpty()) return '_'

        if (nombres.size > 1) {
            val primerNombre = nombres[0]
            if (primerNombre == "JOSE" || primerNombre == "MARIA") {
                return nombres[1].first()
            }
        }
        return nombres[0].first()
    }
}