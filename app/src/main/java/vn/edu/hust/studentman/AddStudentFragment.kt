package vn.edu.hust.studentman

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val ARG_PARAM3 = "param3"

/**
 * A simple [Fragment] subclass.
 * Use the [AddStudentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddStudentFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var param3: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            param3 = it.getInt(ARG_PARAM3)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_student, container, false)

        val editHoten = view.findViewById<EditText>(R.id.edit_hoten)
        val editMssv = view.findViewById<EditText>(R.id.edit_mssv)

        if (param1 != null) {
            editHoten.setText(param1!!)
            editMssv.setText(param2!!)
        }
        (activity as MainActivity).myMenu!!.findItem(R.id.add_new).setVisible(false)

        view.findViewById<Button>(R.id.button_ok).setOnClickListener {
            if (editHoten.text.isNotBlank() && editMssv.text.isNotBlank()) {
//                intent.putExtra("hoten", editHoten.text.toString())
//                intent.putExtra("mssv", editMssv.text.toString())
//                setResult(Activity.RESULT_OK, intent)
//                finish()
                val args = Bundle()
                args.putString("param1", editHoten.text.toString())
                args.putString("param2", editMssv.text.toString())
                args.putInt("param3", param3!!)
                findNavController().navigate(R.id.action_addStudentFragment_to_blankFragment, args)
            }
        }

        view.findViewById<Button>(R.id.button_cancel).setOnClickListener {
//            setResult(Activity.RESULT_CANCELED)
//            finish()

            findNavController().navigate(R.id.action_addStudentFragment_to_blankFragment)
        }
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddStudentFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String, param3: Int) =
            AddStudentFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                    putInt(ARG_PARAM3, param3)
                }
            }
    }
}