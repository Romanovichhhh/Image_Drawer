package com.example.image_drawer

import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import android.icu.lang.UCharacter.getType
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.ImageHeaderParserUtils.getType
import com.example.api.main.requests.Attachment
import com.example.image_drawer.utils.getFileName
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.lang.Character.getType

class EnterFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

    private lateinit var viewModel: EnterFragmentVM

    private lateinit var navigationController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_enter, container, false)

        viewModel = EnterFragmentVM(
            { id -> navigateToResult(id) },
            { imageUploadedToast() }
        )

        navigationController = NavHostFragment.findNavController(this)

        recyclerView = view.findViewById(R.id.lessons_recycler)

        recyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = viewModel.adapter
        }

        viewModel.loadLessonPreviews()

        view.findViewById<FloatingActionButton>(R.id.floating_action_button)
            .setOnClickListener {
                uploadImage()
            }

        return view
    }


    private fun navigateToResult(id: String) {
        val action = EnterFragmentDirections.actionEnterFragmentToResultFragment(id)
        findNavController().navigate(action)
    }

    private fun uploadImage() {
        openImageChooser()
    }

    private fun openImageChooser() {
        Intent(Intent.ACTION_PICK).also {
            it.type = "image/*"
            val mimeTypes = arrayOf("image/jpeg", "image/png")
            it.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
            startActivityForResult(it, REQUEST_CODE_PICK_IMAGE)
        }
    }

    private fun imageUploadedToast() {
        Toast.makeText(
            this.context,
            R.string.image_uploaded,
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_CODE_PICK_IMAGE -> {
                    val uri = data?.data ?: Uri.EMPTY
                    context?.contentResolver?.let { contentResolver ->
                        val parcelFileDescriptor =
                            contentResolver.openFileDescriptor(uri, "r", null)
                        if (parcelFileDescriptor != null) {
                            val fileName = contentResolver.getFileName(uri)
                            context?.let { ctx ->
                                val file = File(ctx.filesDir, fileName)
                                val inputStream =
                                    FileInputStream(parcelFileDescriptor.fileDescriptor)
                                val outputStream = FileOutputStream(file)
                                inputStream.copyTo(outputStream)
                                viewModel.uploadImage(
                                    Attachment(
                                        title = uri,
//                                        uri_type = contentResolver.getType(uri),
                                        image = file,
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    companion object {
        const val REQUEST_CODE_PICK_IMAGE = 101
    }
}

