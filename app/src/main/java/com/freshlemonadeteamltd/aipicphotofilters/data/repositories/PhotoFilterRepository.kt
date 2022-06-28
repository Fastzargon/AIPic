package com.freshlemonadeteamltd.aipicphotofilters.data.repositories

import com.freshlemonadeteamltd.aipicphotofilters.data.network.ApiService
import com.freshlemonadeteamltd.aipicphotofilters.data.request.UploadImageRequest
import com.freshlemonadeteamltd.aipicphotofilters.data.response.GetResult
import com.freshlemonadeteamltd.aipicphotofilters.data.response.UploadImageResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PhotoFilterRepository @Inject constructor(private val apiService: ApiService): IPhotoFilterRepository {

    override suspend fun getListStyles() = apiService.getStyles()

    override suspend fun uploadImage(uploadImageRequest: UploadImageRequest): UploadImageResponse = apiService.uploadImage(uploadImageRequest)

    override suspend fun getResult(submissionId: String): GetResult = apiService.getResult(submissionId)

}