package exercicis

import javax.swing.*
import java.awt.*
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.FileReader
import java.lang.StringBuilder

class Exercici_2_2_Pantalla : JFrame() {
    val et_f = JLabel("Fitxer:")
    val fitxer = JTextField(25)
    val obrir = JButton("Obrir")
    val guardar = JButton("Guardar")
    val et_a = JLabel("Contingut:")
    val area = JTextArea(10, 50)
    val scrollPane = JScrollPane(area)

    // en iniciar posem un contenidor per als elements anteriors
    init {
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE

        setLayout(GridLayout(2, 1))
        setTitle("Editor de text")

        val panell1 = JPanel(GridLayout(0, 1))
        val panell1_1 = JPanel(FlowLayout())
        panell1.add(panell1_1)
        panell1_1.add(et_f)
        panell1_1.add(fitxer)

        val panell1_2 = JPanel(FlowLayout())
        panell1.add(panell1_2)
        panell1_2.add(obrir)
        panell1_2.add(guardar)
        val panell2 = JPanel(GridLayout(0, 1))
        panell2.add(scrollPane)
        area.setEditable(true)

        add(panell1)
        add(panell2)
        pack()

        obrir.addActionListener {

            val routeText = fitxer.text.trim()
            val file = File(routeText)

            if (file.exists() && file.isFile){
                val input = BufferedReader(FileReader(file))
                var content = ""
                var line: String

                line = input.readLine()
                while (input.readLine() != null) {
                    content += line
                    line = input.readLine()
                }
                input.close()
                area.text = content
            }
            else {
                println("No existe o no se encuentra")
            }

            // Instruccions per a bolcar el contingut del fitxer en el JTextArea

        }
        guardar.addActionListener {
            val routeText = fitxer.text.trim()
            val file = File(routeText)
            val content = area.text
            file.writeText(content)
            // Instruccions per a guardar el contingut del JTextArea al fitxer.
        }
    }
}

private fun crearMostrarFinestra() {
    val frame = Exercici_2_2_Pantalla()
    frame.isVisible = true
}

fun main(args: Array<String>) {
    EventQueue.invokeLater(::crearMostrarFinestra)
}