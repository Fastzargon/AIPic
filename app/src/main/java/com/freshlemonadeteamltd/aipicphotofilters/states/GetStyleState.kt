package com.freshlemonadeteamltd.aipicphotofilters.states

import com.freshlemonadeteamltd.aipicphotofilters.data.response.GetStylesResponse

sealed class GetStyleState{
    object Loading : GetStyleState()
    object  Error : GetStyleState()
    data class Success(private val getStyleResponse: GetStylesResponse) : GetStyleState()
}
