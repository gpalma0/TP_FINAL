import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elidacaceres.tpfinal.RetrofitInstance
import kotlinx.coroutines.launch

class HomeOptionsViewModel : ViewModel() {

    private val _logoutResult = MutableLiveData<Boolean>()
    val logoutResult: LiveData<Boolean> get() = _logoutResult

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun logout() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.logout() // Llama al endpoint /logout
                if (response.isSuccessful) {
                    _logoutResult.value = true
                } else {
                    _logoutResult.value = false
                    _errorMessage.value = "Error del servidor: ${response.code()}"
                }
            } catch (e: Exception) {
                // Captura errores de red o excepciones
                _logoutResult.value = false
                _errorMessage.value = "Error de red: ${e.message}"
            }
        }
    }
}
