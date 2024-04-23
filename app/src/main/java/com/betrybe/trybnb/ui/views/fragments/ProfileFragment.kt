import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.betrybe.trybnb.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class ProfileFragment: Fragment() {
    private lateinit var loginInputLayout: TextInputLayout
    private lateinit var passwordInputLayout: TextInputLayout
    private lateinit var loginEditText: TextInputEditText
    private lateinit var passwordEditText: TextInputEditText
    private lateinit var loginButton: Button

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

        loginButton.setOnClickListener {
            validateLogin()
        }

        return viewLogin
    }

    private fun validateLogin() {
        val login = loginEditText.text.toString()
        val password = passwordEditText.text.toString()

        if (login.isBlank()) {
            loginInputLayout.error = "O campo Login é obrigatório"
            // se colocar return não mostra ambos os erros
        } else {
            loginInputLayout.error = null
        }

        if (password.isBlank()) {
            passwordInputLayout.error = "O campo Password é obrigatório"
        } else {
            passwordInputLayout.error = null
        }
    }
}