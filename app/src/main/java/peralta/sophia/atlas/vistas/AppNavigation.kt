package peralta.sophia.atlas.vistas

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import peralta.sophia.atlas.viewmodels.PaisesViewModel

@Composable
fun AppNavigation(
    viewModel: PaisesViewModel,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "paises",
        modifier = modifier
    ) {
        composable("paises") {
            PaisesScreen(
                paises = viewModel.paises.value,
                cargando = viewModel.cargando.value,
                error = viewModel.error.value,
                onReintentar = { viewModel.cargarPaises() },
                onPaisClick = { },
                onJugar = {}
            )
        }
    }
}