package com.empresa.asdtests


import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.empresa.asdtests.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {


    private lateinit var binding : ActivityMainBinding

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        //setContentView(R.layout.activity_main)
        setContentView(binding.root)


        auth = Firebase.auth

        val currentUser = auth.currentUser
        if(currentUser != null){
            verActivityUsuarioLogueado();
        }else{
            //adicionar el fragment CreateAccount
            if (savedInstanceState == null) { //guardar el estado de la actividad que se está mostrando
                supportFragmentManager.beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragmentContainerMain, CreateAccount::class.java, null, "Create Account")
                    .commit()
            }

        }



    }


    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            verActivityUsuarioLogueado();
        }
    }




    private fun verActivityUsuarioLogueado() {
        val intent = Intent(this, ActivityPantallaPrincipal::class.java)
        this.startActivity(intent)
    }


}