package peralta.sophia.atlas.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import peralta.sophia.atlas.modelos.Pais
import androidx.compose.runtime.State
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import peralta.sophia.atlas.red.RetrofitCliente

class PaisesViewModel : ViewModel() {
    private val _paises = mutableStateOf<List<Pais>>(emptyList())
    val paises: State<List<Pais>> = _paises

    private val _cargando = mutableStateOf(true)
    val cargando: State<Boolean> = _cargando

    private val _error = mutableStateOf("")
    val error: State<String> = _error

    init {
        cargarPaises()
    }

    // cargar paises y buscar pais

    fun cargarPaises() {
        _cargando.value  = true
        _error.value = ""
        viewModelScope.launch {
            try{
                val lista = RetrofitCliente.api.obtenerPaises(CAMPOS)
                _paises.value = lista.sortedBy { it.nombreEs }
            } catch(e: Exception) {
                _error.value = "No se pudo cargar la lista. Revisa tu conexion"
            }
            _cargando.value = false
        }
    }

    fun buscarPais(codigo: String): Pais? {
        return _paises.value.find { it.alpha3Code == codigo}
    }

    companion object{
        const val CAMPOS = "name,alpha3Code,translations,flags,capital,region,population,languages,currencies"
    }
}