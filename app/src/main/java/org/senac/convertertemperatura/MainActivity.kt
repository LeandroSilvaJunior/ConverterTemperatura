package org.senac.convertertemperatura

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioGroup
import androidx.appcompat.app.AlertDialog
import org.senac.convertertemperatura.conversor.Conversor
import org.senac.convertertemperatura.conversor.SistemaMedicao

class MainActivity : AppCompatActivity() {

    private lateinit var temperaturaComponent : EditText
    private lateinit var sistemaMedicaoComponent : RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        temperaturaComponent = findViewById<EditText>(R.id.et_temperatura)
        sistemaMedicaoComponent = findViewById<RadioGroup>(R.id.rg_sistema_medicao)
    }

    fun converterTemperatura(view: View) {
        if (validate()) {
            var temperatura = temperaturaComponent.text.toString().toInt()
            var sistemaMedicao : SistemaMedicao

            if (sistemaMedicaoComponent.checkedRadioButtonId == R.id.rb_fahrenheit)
                sistemaMedicao = SistemaMedicao.FAHRENHEIT
            else
                sistemaMedicao = SistemaMedicao.CELSIOS

            var valorConvertido = Conversor(temperatura, sistemaMedicao).convertAsString("%.2f");

            val alertDialogBuilder = AlertDialog.Builder(this@MainActivity)
            alertDialogBuilder.setTitle("Resultado")
            alertDialogBuilder.setMessage("Temperatura convertida:${valorConvertido}")
            alertDialogBuilder.setNeutralButton("Ok") { _, _->}

            alertDialogBuilder.create().show()
        }
    }

    fun validate() : Boolean {
        var validate = true

        if (temperaturaComponent.text.trim().length == 0) {
            temperaturaComponent.setError("Você deve informar a temperatura")
            validate= false
        }

        return validate
    }
}
