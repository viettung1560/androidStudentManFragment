package vn.edu.hust.studentman

import android.os.Bundle
import android.text.Html
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

  lateinit var students: MutableList<StudentModel>
  lateinit var listViewStudent: ListView
  lateinit var studentAdapter: StudentAdapter
  lateinit var blankFragment: BlankFragment

  var myMenu: Menu? = null
  //var addLauncher: ActivityResultLauncher<Intent>? = null
  //var editLauncher: ActivityResultLauncher<Intent>? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    students = mutableListOf(
      StudentModel("Nguyễn Văn An", "SV001"),
      StudentModel("Trần Thị Bảo", "SV002"),
      StudentModel("Lê Hoàng Cường", "SV003"),
      StudentModel("Phạm Thị Dung", "SV004"),
      StudentModel("Đỗ Minh Đức", "SV005"),
      StudentModel("Vũ Thị Hoa", "SV006"),
      StudentModel("Hoàng Văn Hải", "SV007"),
      StudentModel("Bùi Thị Hạnh", "SV008"),
      StudentModel("Đinh Văn Hùng", "SV009"),
      StudentModel("Nguyễn Thị Linh", "SV010"),
      StudentModel("Phạm Văn Long", "SV011"),
      StudentModel("Trần Thị Mai", "SV012"),
      StudentModel("Lê Thị Ngọc", "SV013"),
      StudentModel("Vũ Văn Nam", "SV014"),
      StudentModel("Hoàng Thị Phương", "SV015"),
      StudentModel("Đỗ Văn Quân", "SV016"),
      StudentModel("Nguyễn Thị Thu", "SV017"),
      StudentModel("Trần Văn Tài", "SV018"),
      StudentModel("Phạm Thị Tuyết", "SV019"),
      StudentModel("Lê Văn Vũ", "SV020")
    )
    studentAdapter = StudentAdapter(students)

    listViewStudent = findViewById<ListView>(R.id.list_view_students)
    listViewStudent.adapter = studentAdapter

    registerForContextMenu(listViewStudent)

    blankFragment = BlankFragment()

    supportFragmentManager.beginTransaction()
      .add(R.id.fragmentContainerView, blankFragment)
      .commit()

//    addLauncher =
//      registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { it: ActivityResult ->
//        if (it.resultCode == RESULT_OK) {
//          val hoten = it.data?.getStringExtra("hoten")!!
//          val mssv = it.data?.getStringExtra("mssv")!!
//          students.add(StudentModel(hoten, mssv))
//          studentAdapter.notifyDataSetChanged()
//        } else {
//          //textResult.text = "CANCELLED"
//        }
//      }
//
//    editLauncher =
//      registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { it: ActivityResult ->
//        if (it.resultCode == RESULT_OK) {
//          val hoten = it.data?.getStringExtra("hoten")!!
//          val mssv = it.data?.getStringExtra("mssv")!!
//          val pos = it.data?.getIntExtra("pos", 0)!!
//          students[pos].studentName = hoten
//          students[pos].studentId = mssv
//
//          studentAdapter.notifyDataSetChanged()
//        } else {
//          //textResult.text = "CANCELLED"
//        }
//      }
  }

  // TODO: Ham khoi tao cho context menu
  override fun onCreateContextMenu(
    menu: ContextMenu?,
    v: View?,
    menuInfo: ContextMenu.ContextMenuInfo?
  ) {
    menuInflater.inflate(R.menu.context_menu, menu)
    super.onCreateContextMenu(menu, v, menuInfo)
  }

  // TODO: Ham xu ly su kien nhan vao context menu
  override fun onContextItemSelected(item: MenuItem): Boolean {
    val pos = (item.menuInfo as AdapterView.AdapterContextMenuInfo).position
    when (item.itemId) {
      R.id.edit -> {
//        val intent = Intent(this, AddStudentActivity::class.java)
//        intent.putExtra("hoten", students[pos].studentName)
//        intent.putExtra("mssv", students[pos].studentId)
//        intent.putExtra("pos", pos)
//        editLauncher!!.launch(intent)

        val args = Bundle()
        args.putString("param1", students[pos].studentName)
        args.putString("param2", students[pos].studentId)
        args.putInt("param3", pos)
        blankFragment.findNavController().navigate(R.id.action_blankFragment_to_addStudentFragment, args)
      }

      R.id.remove -> {
        AlertDialog.Builder(this)
          .setTitle("Xóa thông tin sinh viên")
          .setMessage(
            Html.fromHtml(
              "<b>${students[pos].studentName}</b><br><i>${students[pos].studentId}</i>",
              Html.FROM_HTML_MODE_LEGACY
            )
          )
          .setPositiveButton(
            "Yes"
          ) { _, _ ->
            val student = students[pos]
            students.removeAt(pos)
            studentAdapter.notifyDataSetChanged()

            Snackbar.make(listViewStudent, "Xóa thành công", Snackbar.LENGTH_LONG)
              .setAction("UNDO") {
                students.add(pos, student)
                studentAdapter.notifyDataSetChanged()
              }
              .show()
          }
          .setNegativeButton("No", null)
          .show()
          .setCanceledOnTouchOutside(false)
      }
    }
    return super.onContextItemSelected(item)
  }

  // TODO: Ham khoi tao option menu
  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.option_menu, menu)
    myMenu = menu
    return super.onCreateOptionsMenu(menu)
  }

  // TODO: Ham xu ly su kien nhan vao option menu
  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      R.id.add_new -> {
//        val intent = Intent(this, AddStudentActivity::class.java)
//        addLauncher!!.launch(intent)

        val args = Bundle()
        args.putInt("param3", students.count())
        blankFragment.findNavController().navigate(R.id.action_blankFragment_to_addStudentFragment, args)
      }
    }
    return super.onOptionsItemSelected(item)
  }
}