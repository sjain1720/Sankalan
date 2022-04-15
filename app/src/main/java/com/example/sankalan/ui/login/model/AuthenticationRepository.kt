package com.example.sankalan.ui.login.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.sankalan.ui.login.data.LoggedInUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase

class AuthenticationRepository {


    private val auth = FirebaseAuth.getInstance()
    val firebaseUser = MutableLiveData<FirebaseUser>()
    val isLogin: Boolean
        get() = auth.currentUser != null


    fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    //success in login.
                    firebaseUser.postValue(auth.currentUser)
                } else {
                    //failed login.
                    Log.w("Login Failed", "${it.exception}")
                }
            }
            .addOnFailureListener {
                Log.w("Login Failed", "${it}")
            }

    }

    fun signUp(email: String, password: String, data: LoggedInUser) {

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    //success in login.
                    firebaseUser.postValue(auth.currentUser)
                    uploadUserData(auth.currentUser?.uid, data)
                    Log.w("SignUp Sucess", "${it}")

                } else {
                    //failed login.
                    Log.w("SignUp Failed", "${it.exception}")
                }
            }
            .addOnFailureListener {
                Log.w("SignUp Failed", "${it}")
            }


    }

    private fun uploadUserData(uid: String?, data: LoggedInUser) {
        val database = FirebaseDatabase.getInstance().getReference("Users")
        if (uid != null) {
            database.child(uid).setValue(data)
                .addOnSuccessListener {
                    Log.w("User", "Data Saved")
                }
                .addOnFailureListener {
                    Log.w("User Data ", "Not Saved ${it}")
                }
        } else {
            Log.w("Error", "Firebase UID Error!!.")
        }
    }

    fun logout() {
        if (isLogin){
            auth.signOut()
        }
    }

}