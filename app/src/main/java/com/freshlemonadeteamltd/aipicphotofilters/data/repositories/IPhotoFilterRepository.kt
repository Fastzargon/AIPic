package com.freshlemonadeteamltd.aipicphotofilters.data.repositories

import com.freshlemonadeteamltd.aipicphotofilters.data.request.UploadImageRequest
import com.freshlemonadeteamltd.aipicphotofilters.data.response.GetResult
import com.freshlemonadeteamltd.aipicphotofilters.data.response.GetStylesResponse
import com.freshlemonadeteamltd.aipicphotofilters.data.response.UploadImageResponse

interface IPhotoFilterRepository {
   suspend fun getListStyles() : GetStylesResponse
   suspend fun uploadImage(uploadImageRequest: UploadImageRequest) : UploadImageResponse
   suspend fun getResult(submissionId: String) : GetResult
}