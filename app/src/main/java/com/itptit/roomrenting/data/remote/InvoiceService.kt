package com.itptit.roomrenting.data.remote

import com.itptit.roomrenting.data.remote.dto.invoice.AllInvoiceResponse
import com.itptit.roomrenting.data.remote.dto.invoice.InvoiceRequest
import com.itptit.roomrenting.data.remote.dto.invoice.InvoiceResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface InvoiceService {
    @GET("/owner/rented-rooms/{rentedRoomId}/invoices")
    fun getAllInvoices(@Path("rentedRoomId") rentedRoomId: String): Call<AllInvoiceResponse>

    @GET("/owner/rented-rooms/{rentedRoomId}/invoices/{invoiceId}")
    fun getInvoice(
        @Path("rentedRoomId") rentedRoomId: String,
        @Path("invoiceId") invoiceId: String
    ): Call<InvoiceResponse>

    @POST("/owner/rented-rooms/{rentedRoomId}/invoices")
    fun createInvoice(
        @Path("rentedRoomId") rentedRoomId: String,
        @Body request: InvoiceRequest,
    ): Call<InvoiceResponse>

    @PUT("/owner/rented-rooms/{rentedRoomId}/invoices/{invoiceId}/payment-date")
    fun updatePaymentDate(
        @Path("rentedRoomId") rentedRoomId: String,
        @Path("invoiceId") invoiceId: String,
        @Body paymentDate: String,
    ): Call<InvoiceResponse>
}