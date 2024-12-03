package vn.edu.hust.studentman

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val ARG_PARAM3 = "param3"

/**
 * A simple [Fragment] subclass.
 * Use the [BlankFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BlankFragment : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_blank, container, false)
        val mainActivity = activity as MainActivity
        if (param3 != null) {
            if (param3 == mainActivity.students.count()) {
                mainActivity.students.add(StudentModel(param1!!, param2!!))
                mainActivity.studentAdapter.notifyDataSetChanged()
            }
            else {
                mainActivity.students[param3!!].studentName = param1!!
                mainActivity.students[param3!!].studentId = param2!!
                mainActivity.studentAdapter.notifyDataSetChanged()
            }
        }
        if (mainActivity.myMenu != null)
            mainActivity.myMenu!!.findItem(R.id.add_new).setVisible(true)
        return view
    }
//
//    fun BlankToAdd(param3: Int){
//        val args = Bundle()
//        args.putInt("param3", param3)
//        findNavController().navigate(R.id.action_blankFragment_to_addStudentFragment, args)
//    }
//
//    fun BlankToEdit(param1: String, param2: String, param3: Int){
//        val args = Bundle()
//        args.putString("param1", param1)
//        args.putString("param2", param2)
//        args.putInt("param3", param3)
//        findNavController().navigate(R.id.action_blankFragment_to_addStudentFragment, args)
//    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BlankFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String, param3: Int) =
            BlankFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                    putInt(ARG_PARAM3, param3)
                }
            }
    }
}