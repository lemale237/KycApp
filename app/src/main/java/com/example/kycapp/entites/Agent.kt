package com.example.kycapp.entites

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Agent(
    val imageURL: String,
    var agentName: String,
    var imageRecto: String,
    var imageVerso: String,
    var prenomAgent: String,
    var dateNaissance: Date,
    var lieuNaissance: String,
    var phoneNumber: String,
    var residenceAgent: String,
    var nomEntrepriseCollecteur: String,
    var numCni: String,
    var professionAgent: String,
    var activitéAgent: String,
    var capitalInitialAgent: Int,
    var sourceFinancièreAgent: String,
    var entrepriseAgent: String,
    var smobilpayIdUtilisateur: String,
    var photoAgent: String,
    var photoPointVente: String
):Parcelable {
    constructor() : this(
        "",
        "",
        "",
        "",
        "",
        Date(),
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        0,
        "",
        "",
        "",
        "",
        ""
    )

}