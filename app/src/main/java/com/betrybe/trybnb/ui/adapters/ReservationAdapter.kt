package com.betrybe.trybnb.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.betrybe.trybnb.R
import com.betrybe.trybnb.data.models.DataBooking

class ReservationAdapter(private val bookingList: List<DataBooking>): Adapter<ReservationAdapter.ReservationViewHolder>() {
    class ReservationViewHolder(view: View) : RecyclerView.ViewHolder(view) { // busco os items a serem preenchidos
        val nameItemReservation : TextView  = view.findViewById(R.id.name_item_reservation)
        val checkinItemReservation: TextView = view.findViewById(R.id.checkin_item_reservation)
        val checkoutItemReservation: TextView = view.findViewById(R.id.checkout_item_reservation)
        val additionalNeedsItemReservation : TextView = view.findViewById(R.id.additional_needs_item_reservation)
        val totalPriceItemReservation : TextView = view.findViewById(R.id.total_price_item_reservation)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservationViewHolder {
        val viewReservation = LayoutInflater.from(parent.context).inflate(
            R.layout.list_item_reservation,
            parent,
            false
        )
        return ReservationViewHolder(viewReservation)
    }

    override fun getItemCount(): Int = bookingList.size

    override fun onBindViewHolder(holder: ReservationViewHolder, position: Int) { // preencho com as informaçoes retornada da api
        val bookings = bookingList[position]

            holder.nameItemReservation.text = buildString {
                append(bookings.firstmane)
                append(" ")
                append(bookings.lastName)
            }

            holder.checkinItemReservation.text = bookings.bookingdates.checkin

            holder.checkoutItemReservation.text = bookings.bookingdates.checkout

            holder.additionalNeedsItemReservation.text = bookings.additionalneeds

            holder.totalPriceItemReservation.text = bookings.totalprice.toString()

    }
}