package com.betrybe.trybnb.data.models

data class BookingId(
    val bookingid: Int,
)

data class ChecksBooking(
    val checkin: String,
    val checkout: String,
)

data class DataBooking(
    val firstmane: String,
    val lastName: String,
    val totalprice: Int,
    val depositpaid: Boolean,
    val bookingdates: ChecksBooking,
    val additionalneeds: String,
)
