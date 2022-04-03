package com.example.api.main.requests

import android.net.Uri
import java.io.File

data class Attachment(
    val title: Uri,
    val image: File,
)
