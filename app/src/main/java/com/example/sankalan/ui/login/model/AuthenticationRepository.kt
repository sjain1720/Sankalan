package com.example.sankalan.ui.login.model

import com.example.sankalan.ui.login.data.LoggedInUser

class AuthenticationRepository {

    /**
     * private val auth = FirebaseAuth.getInstance()
    val firebaseUser = MutableLiveData<FirebaseUser>()
    val isLogin:Boolean
    get() = auth.currentUser!=null
     */


    fun login(email:String, password:String){
        /** auth.signInWithEmailAndPassword(email,password)
        .addOnCompleteListener {
        if(it.isSuccessful){
        //success in login.
        firebaseUser.postValue(auth.currentUser)
        }else{
        //failed login.
        Log.w("Login Failed","${it.exception}")
        }
        }
        .addOnFailureListener {
        Log.w("Login Failed","${it}")
        }
        **/

    }
    fun signUp(email:String, password:String, data:LoggedInUser){
        /**
         * auth.createUserWithEmailAndPassword(email,password)
        .addOnCompleteListener {
        if(it.isSuccessful){
        //success in login.
        firebaseUser.postValue(auth.currentUser)
        uploadUserData(auth.currentUser?.uid,data)
        }else{
        //failed login.
        Log.w("Login Failed","${it.exception}")
        }
        }
        .addOnFailureListener {
        Log.w("Login Failed","${it}")
        }
         */

    }
    /**
     *private fun uploadUserData(uid:String?, data:LoggedInUser){
    val database = FirebaseDatabase.getInstance("https://sanklanfinalpractise-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("Users")
    if(uid!=null){
    database.child(uid).setValue(data)
    .addOnSuccessListener {
    Log.w("User","Data Saved")
    }
    .addOnFailureListener {
    Log.w("User Data ","Not Saved ${it}")
    }
    }else{
    Log.w("Error","Firebase UID Error!!.")
    }
    }
    fun logout(){
    auth.signOut()
    }
     */
}