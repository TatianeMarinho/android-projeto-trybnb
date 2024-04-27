import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.betrybe.trybnb.R
import com.betrybe.trybnb.common.ApiIdlingResource
import com.betrybe.trybnb.data.api.RetrofitApi
import com.betrybe.trybnb.data.models.LoginRequest
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class ProfileFragment: Fragment() {
    private val instanceApi = RetrofitApi.instanceApi // cira uma instancia da interface da Api para solicitaçoes http
    private lateinit var loginInputLayout: TextInputLayout
    private lateinit var passwordInputLayout: TextInputLayout
    private lateinit var loginEditText: TextInputEditText
    private lateinit var passwordEditText: TextInputEditText
    private lateinit var loginButton: Button
    private lateinit var loginSuccess: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewLogin = inflater.inflate(R.layout.fragment_profile, container, false)

        loginInputLayout = viewLogin.findViewById(R.id.login_input_profile)
        passwordInputLayout = viewLogin.findViewById(R.id.password_input_profile)
        loginEditText = loginInputLayout.editText as TextInputEditText
        passwordEditText = passwordInputLayout.editText as TextInputEditText
        loginButton = viewLogin.findViewById(R.id.login_button_profile)
        loginSuccess = viewLogin.findViewById(R.id.login_success)

        loginButton.setOnClickListener {
            val login = loginEditText.text.toString() //consteudo digitado
            val password = passwordEditText.text.toString() // conteudo digitado

            Log.d("ProfileFragment", "Login: $login, Password: $password")

            validateLogin(login, password)

            if (loginInputLayout.error == null && passwordInputLayout.error == null) {

                CoroutineScope(Dispatchers.IO).launch { // contexto da requisicao
                    try {
                        ApiIdlingResource.increment()

                        Log.d("ProfileFragment", "Enviando solicitação de login")
                        val responseRetrofitApi = instanceApi.login(LoginRequest(login, password)) // requisicao a api do login

                        if (responseRetrofitApi.isSuccessful) {
                            Log.d("ProfileFragment", "Requisição bem-sucedida")
                            withContext(Dispatchers.Main) { // contexto da main
                                    Log.d("ProfileFragment", "Configurando visibilidade da mensagem de sucesso")
                                    loginSuccess.visibility = View.VISIBLE //coloco o texto de sucesso de login na tela
                                }
                            }
                        ApiIdlingResource.decrement()
                    } catch (e: HttpException) {
                        ApiIdlingResource.decrement()
                        Log.e("ProfileFragment", "Erro de requisição: ${e.message}")
                    } catch (e: IOException) {
                        ApiIdlingResource.decrement()
                        Log.e("ProfileFragment", "Erro de IO: ${e.message}")
                    }
                }
            }
        }

        return viewLogin
    }

    private fun validateLogin(login: String, password: String) {
        loginInputLayout.error = null
        passwordInputLayout.error = null

        if (login.isBlank()) {
            loginInputLayout.error = "O campo Login é obrigatório"
            // se colocar return não mostra ambos os erros
        }
        if (password.isBlank()) {
            passwordInputLayout.error = "O campo Password é obrigatório"
        }
    }
}