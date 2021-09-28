package com.example.kycapp.entites

import java.util.*


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
) {
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