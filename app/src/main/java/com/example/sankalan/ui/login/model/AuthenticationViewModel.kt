package com.example.sankalan.ui.login.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sankalan.R
import com.example.sankalan.ui.login.data.LoggedInUser
import com.example.sankalan.ui.login.data.LoginFormState
import com.google.firebase.auth.FirebaseUser

class AuthenticationViewModel(private val repository: AuthenticationRepository):ViewModel() {
    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginForm:LiveData<LoginFormState> = _loginForm

     val user: MutableLiveData<FirebaseUser> = repository.firebaseUser


    private val _signUpForm = MutableLiveData<LoginFormState>()
    val signUpForm:LiveData<LoginFormState> = _signUpForm


    private val passwordRegex = Regex("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$")

    fun login(email: String, password: String){
        if(isValidEmail(email) && isValidPassword(password)){
            Thread(Runnable {
                repository.login(email,password)
            }).start()
        }else{
            _loginForm.value = LoginFormState(emailError = R.string.invalid_email, passError = R.string.invalid_password)
        }

    }
    fun signUp(email: String, password: String, data:LoggedInUser){
        Thread(Runnable {
            repository.signUp(email, password, data)
        }).start()
    }


    fun onLoginDataChange(email:String , password:String ){
        if (!isValidEmail(email)){
            _loginForm.value = LoginFormState(emailError = R.string.invalid_email)
        }else if(!isValidPassword(password)){
            _loginForm.value = LoginFormState(passError = R.string.invalid_password)
        }else{
            _loginForm.value = LoginFormState(isValid = true)
        }
    }
    fun onSignupDataChange(email:String , password:String ){
        if (!isValidEmail(email)){
            _signUpForm.value = LoginFormState(emailError = R.string.invalid_email)
        }else if(!isValidPassword(password)){
            _signUpForm.value = LoginFormState(passError = R.string.invalid_password)
        }else{
            _signUpForm.value = LoginFormState(isValid = true)
        }
    }


    private fun isValidEmail(email:String):Boolean{
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    private fun isValidPassword(password: String):Boolean{
        return passwordRegex.matches(password) && password.length >5
    }


}