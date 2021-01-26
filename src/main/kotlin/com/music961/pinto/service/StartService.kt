package com.music961.pinto.service

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Service
import java.io.FileInputStream

@Service
class StartService : CommandLineRunner {
    override fun run(vararg args: String?) {
        val options = FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(FileInputStream("/home/mct-dev-intern/바탕화면/firebase.json")))
                .setDatabaseUrl("https://pinto-smalto.firebaseio.com")
                .build()
        FirebaseApp.initializeApp(options)
        println("==========================")
        println("=     Server started     =")
        println("= Firebase init finished =")
        println("==========================")

    }
}