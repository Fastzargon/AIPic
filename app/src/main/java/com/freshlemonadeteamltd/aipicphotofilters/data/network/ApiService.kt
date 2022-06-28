package com.freshlemonadeteamltd.aipicphotofilters.data.network

import com.freshlemonadeteamltd.aipicphotofilters.data.request.UploadImageRequest
import com.freshlemonadeteamltd.aipicphotofilters.data.response.GetResult
import com.freshlemonadeteamltd.aipicphotofilters.data.response.GetStylesResponse
import com.freshlemonadeteamltd.aipicphotofilters.data.response.UploadImageResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

const val GET_STYLES = "v1/noauth/styles"
const val UPLOAD_IMAGE = "v1/noauth/upload"
const val GET_RESULT = "v1/noauth/result"
interface ApiService {

    @GET(GET_STYLES)
    suspend fun getStyles() : GetStylesResponse

    @POST(UPLOAD_IMAGE)
    suspend fun  uploadImage(@Body uploadImageRequest: UploadImageRequest) : UploadImageResponse

    @GET(GET_RESULT)
    suspend fun getResult(@Query("submissionId")submissionId: String) : GetResult


}