package vn.finance.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import vn.finance.authentication.api.GetLoggedInProvider
import javax.inject.Inject

@HiltViewModel
class FinanceViewModel @Inject constructor(private val getLoggedInProvider: GetLoggedInProvider) :
    ViewModel() {

    private val _isDarkMode = MutableStateFlow(false)
    val isDarkMode = _isDarkMode.asStateFlow()

    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn = _isLoggedIn.asStateFlow()

    init {
        getLoggedIn()
    }

    fun onDarkModeChanged(value: Boolean) {
        viewModelScope.launch {
            _isDarkMode.emit(value)
        }
    }

    private fun getLoggedIn() {
        viewModelScope.launch {
            getLoggedInProvider.getLoggedIn().collect {
                _isLoggedIn.emit(it)
            }
        }
    }
}