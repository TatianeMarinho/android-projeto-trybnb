import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.betrybe.trybnb.R

class ReservationFragment : Fragment() {
    override fun onCreateView( // cria a visualizaçao
        inflater: LayoutInflater, // metodo para inflar o layout
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_reservation, container, false)
        // infla o layout desejado e retona a visualizaçao resultante
    }
}