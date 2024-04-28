import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.betrybe.trybnb.R
import com.betrybe.trybnb.common.ApiIdlingResource
import com.betrybe.trybnb.data.api.RetrofitApi
import com.betrybe.trybnb.ui.adapters.ReservationAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class ReservationFragment : Fragment() {
    override fun onCreateView( // cria a visualizaçao
        inflater: LayoutInflater, // metodo para inflar o layout
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewReservation = inflater.inflate(R.layout.fragment_reservation, container, false)
        // infla o layout desejado e retona a visualizaçao resultante
        Log.d("ReservationFragment", "View inflada com sucesso")

        val reservationRecyclerView: RecyclerView by lazy {
            viewReservation.findViewById(R.id.reservation_recycler_view)
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                ApiIdlingResource.increment()

                val resultApiBooking = RetrofitApi.instanceApi.getBookings() // realiza uma chamada a api assincrona
                Log.d("ReservationFragment", "Resultado da API de reservas: $resultApiBooking")

                if (resultApiBooking.isSuccessful) {
                    var bookingIds = resultApiBooking.body() // obtem o corpo da resposta da api com todos os ids
                    Log.d("ReservationFragment", "IDs de reserva recebidos: $bookingIds")

                    if (!bookingIds.isNullOrEmpty()){
                        bookingIds = bookingIds.subList(0,6) // limita a resposa aos 5 primeiros ids
                        Log.d("ReservationFragment", "IDs de reserva após limitação: $bookingIds")

                        val resultBookingIds = bookingIds.map { //para cada id obtenho os detalhes das reservas req. api por id
                            RetrofitApi.instanceApi.getBookingId(it.bookingid.toString()).body()
                        } // retornando uma lista com esses detalhes

                        val resultInfos = resultBookingIds.filterNotNull()  // filtro os resultados tirando os nulos

                        val reservationAdapter = ReservationAdapter(resultInfos) // cria um novo objetoAdapter com as informaçoes obtidas

                        withContext(Dispatchers.Main) {
                            reservationRecyclerView.layoutManager = LinearLayoutManager( // define o layoutManager do recyclerView
                                this@ReservationFragment.requireContext() // utilizando o contexto do fragmento para configurar
                            )
                            reservationRecyclerView.adapter = reservationAdapter // conectar o adaptador ao recyclerView
                            Log.d("ReservationFragment", "Adaptador do RecyclerView configurado")
                        }

                    }
                }
                ApiIdlingResource.decrement()
            }catch (e:HttpException) {
                ApiIdlingResource.decrement()
                Log.e("ReservationFragment", "Erro de requisição: ${e.message}")
            }catch (e: IOException) {
                ApiIdlingResource.decrement()
                Log.e("ReservationFragment", "Erro de IO: ${e.message}")
            }
        }
        return viewReservation
    }
}