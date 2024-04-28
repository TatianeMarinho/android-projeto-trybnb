import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.betrybe.trybnb.R
import com.google.android.material.textfield.TextInputLayout

class CreateReservationFragment : Fragment() {

    private lateinit var firstNameInputLayout: TextInputLayout
    private lateinit var lastNameInputLayout: TextInputLayout
    private lateinit var checkinInputLayout: TextInputLayout
    private lateinit var checkoutInputLayout: TextInputLayout
    private lateinit var aditionalNeedsInputLayout: TextInputLayout
    private lateinit var totalPriceInputLayout: TextInputLayout
    private lateinit var firstNameEditText: String
    private lateinit var lastNameEditText: String
    private lateinit var checkinEditText: String
    private lateinit var checkoutEditText: String
    private lateinit var aditionalNeedsEditText: String
    private lateinit var totalPriceEditText: String
    private lateinit var createReservationButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewCreateReservation = inflater.inflate(R.layout.fragment_create_reservation, container, false)

        firstNameInputLayout = viewCreateReservation.findViewById(R.id.first_name_create_reservation)
        lastNameInputLayout = viewCreateReservation.findViewById(R.id.last_name_create_reservation)
        checkinInputLayout = viewCreateReservation.findViewById(R.id.checkin_create_reservation)
        checkoutInputLayout = viewCreateReservation.findViewById(R.id.checkout_create_reservation)
        aditionalNeedsInputLayout = viewCreateReservation.findViewById(R.id.additional_needs_create_reservation)
        totalPriceInputLayout = viewCreateReservation.findViewById(R.id.total_price_create_reservation)
        firstNameEditText = firstNameInputLayout.editText?.text.toString()
        lastNameEditText = lastNameInputLayout.editText?.text.toString()
        checkinEditText = checkinInputLayout.editText?.text.toString()
        checkoutEditText = checkoutInputLayout.editText?.text.toString()
        aditionalNeedsEditText = aditionalNeedsInputLayout.editText?.text.toString()
        totalPriceEditText = totalPriceInputLayout.editText?.text.toString()
        createReservationButton = viewCreateReservation.findViewById(R.id.create_reservation_button)

        createReservationButton.setOnClickListener {
            validateCreateReservation()
        }
        return viewCreateReservation
    }
    private fun validateCreateReservation() {
        firstNameInputLayout.error = null
        lastNameInputLayout.error = null
        checkinInputLayout.error = null
        checkoutInputLayout.error = null
        aditionalNeedsInputLayout.error = null
        totalPriceInputLayout.error = null

        if (firstNameEditText.isBlank()) {
            firstNameInputLayout.error = "O campo Nome é obrigatório"
        }
        if (lastNameEditText.isBlank()) {
            lastNameInputLayout.error = "O campo Sobrenome é obrigatório"
        }
        if (checkinEditText.isBlank()) {
            checkinInputLayout.error = "O campo Checkin é obrigatório"
        }
        if (checkoutEditText.isBlank()) {
            checkoutInputLayout.error = "O campo Checkout é obrigatório"
        }
        if (aditionalNeedsEditText.isBlank()) {
            aditionalNeedsInputLayout.error = "O campo Necessidades Adicionais é obrigatório"
        }
        if (totalPriceEditText.isBlank()) {
            totalPriceInputLayout.error = "O campo Preço Total é obrigatório"
        }
    }
}