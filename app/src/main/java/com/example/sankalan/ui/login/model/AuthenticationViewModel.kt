package com.example.sankalan.ui.login.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sankalan.R
import com.example.sankalan.ui.login.data.LoggedInUser
import com.example.sankalan.ui.login.data.LoginFormState

class AuthenticationViewModel(private val repository: AuthenticationRepository):ViewModel() {
    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginForm:LiveData<LoginFormState> = _loginForm

    // val user: MutableLiveData<FirebaseUser> = repository.firebaseUser


    private val _signUpForm = MutableLiveData<LoginFormState>()
    val signUpForm = _signUpForm


    private val passwordRegex = Regex("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=])(?=\\\\S+\$).{4,}\$")

    fun login(email: String, password: String){
        Thread(Runnable {
            repository.login(email,password)
        }).start()
    }
    fun signUp(email: String, password: String, data:LoggedInUser){
        Thread(Runnable {
            repository.signUp(email, password, data)
        }).start()
    }


    fun onLoginDataChange(email:String = "", password:String = ""){
        if (email.isNotEmpty() && !isValidEmail(email)){
            _loginForm.value = LoginFormState(emailError = R.string.invalid_email)
        }else if(password.isNotEmpty() && !isValidPassword(password)){
            _loginForm.value = LoginFormState(passError = R.string.invalid_password)
        }else{
            _loginForm.value = LoginFormState(isValid = true)
        }
    }

    fun onSignUpDataChange(data: LoggedInUser, email: String = "", password: String = "" ){
        if (email.isEmpty() || !isValidEmail(email)){
            _loginForm.value = LoginFormState(emailError = R.string.invalid_email)
        }else if(password.isEmpty() || !isValidPassword(password)){
            _loginForm.value = LoginFormState(passError = R.string.invalid_password)
        }else if (data.name.isEmpty()){
            _loginForm.value = LoginFormState(nameError = R.string.empty_edtit_text)
        }else if(data.course.isEmpty()){
            _loginForm.value = LoginFormState(courseError = R.string.empty_edtit_text)
        }else if(data.institute.isEmpty()){
            _loginForm.value = LoginFormState(instituteError = R.string.empty_edtit_text)
        }else if(data.mobile.isEmpty() or isValidMobile(data.mobile)){
            _loginForm.value = LoginFormState(mobileError = R.string.invalid_mobile)
        }else if(data.year !in 1..4){
            _loginForm.value = LoginFormState(courseYearError = R.string.invalid_course_year)
        }else{
            _loginForm.value = LoginFormState(isValid = true)
        }
    }

    private fun isValidMobile(mobile:String):Boolean{
        if (mobile.length==10){
            return true
        }
        return false
    }
    private fun isValidEmail(email:String):Boolean{
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    private fun isValidPassword(password: String):Boolean{
        return passwordRegex.matches(password)
    }

}