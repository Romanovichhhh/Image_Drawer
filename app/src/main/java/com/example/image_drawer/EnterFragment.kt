package com.example.image_drawer


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EnterFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

    private lateinit var viewModel : EnterFragmentVM

    private lateinit var navigationController : NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_enter, container, false)

        viewModel = EnterFragmentVM { id ->
            navigateToResult(id)
        }

        navigationController = NavHostFragment.findNavController(this)

        recyclerView = view.findViewById(R.id.lessons_recycler)

        recyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = viewModel.adapter
        }

        viewModel.loadLessonPreviews()

        return view
    }


    private fun navigateToResult(id : String)  {
        val action = EnterFragmentDirections.actionEnterFragmentToResultFragment(id)
        findNavController().navigate(action)
    }

//    private fun openImageChooser() {
//        Intent(Intent.ACTION_PICK).also {
//            it.type = "image/*"
//            val mimeTypes = arrayOf("image/jpeg", "image/png")
//            it.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
//            startActivityForResult(it, REQUEST_CODE_PICK_IMAGE)
//        }
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (resultCode == Activity.RESULT_OK) {
//            when (requestCode) {
//                REQUEST_CODE_PICK_IMAGE -> {
//                    imageUri = data?.data
//                    //var a = imageUri?.let { contentResolver(context).getFileName(it) }
//
//                    img.setImageURI(imageUri)
//                }
//            }
//        }
//    }
//
//    fun ContentResolver.getFileName(fileUri: Uri): String {
//        var name = ""
//        val returnCursor = this.query(fileUri, null, null, null, null)
//        if (returnCursor != null) {
//            val nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
//            returnCursor.moveToFirst()
//            name = returnCursor.getString(nameIndex)
//            returnCursor.close()
//        }
//        return name
//    }

//    companion object {
//        const val REQUEST_CODE_PICK_IMAGE = 101
//    }
}