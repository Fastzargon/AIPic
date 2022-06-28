package com.freshlemonadeteamltd.aipicphotofilters.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.freshlemonadeteamltd.aipicphotofilters.data.repositories.IPhotoFilterRepository
import com.freshlemonadeteamltd.aipicphotofilters.data.response.GetStylesResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoFilterViewModel @Inject constructor(private val photoRepository: IPhotoFilterRepository): ViewModel() {

    private val _getStyles = MutableStateFlow<GetStylesResponse?>(null)
    val getStyles : StateFlow<GetStylesResponse?> = _getStyles

    fun getStyles() = viewModelScope.launch {
       _getStyles.value = photoRepository.getListStyles()
    }
}