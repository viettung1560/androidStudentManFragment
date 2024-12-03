package vn.edu.hust.studentman

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class AddStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        val editHoten = findViewById<EditText>(R.id.edit_hoten)
        val editMssv = findViewById<EditText>(R.id.edit_mssv)

        if (intent.getStringExtra("hoten") != null) {
            editHoten.setText(intent.getStringExtra("hoten")!!)
            editMssv.setText(intent.getStringExtra("mssv")!!)
        }

        // TODO: Su dung setResult de thiet lap ket qua tra ve

        setResult(Activity.RESULT_CANCELED)

        findViewById<Button>(R.id.button_ok).setOnClickListener {
            if (editHoten.text.isNotBlank() && editMssv.text.isNotBlank()) {
                intent.putExtra("hoten", editHoten.text.toString())
                intent.putExtra("mssv", editMssv.text.toString())
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }

        findViewById<Button>(R.id.button_cancel).setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }
}