package com.freshlemonadeteamltd.aipicphotofilters.data.request

data class UploadImageRequest(
    val imageBase64Encoded: String,
    val imageSize: String,
    val optimizeForPrint: String,
    val partnerId: String,
    val styleId: String,
    val useOriginalColors: String
)