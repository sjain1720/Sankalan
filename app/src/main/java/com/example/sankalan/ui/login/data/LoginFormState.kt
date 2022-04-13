package com.example.sankalan.ui.login.data

data class LoginFormState(
    val emailError:Int? = null,
    val passError:Int? = null,
    val mobileError:Int? = null,
    val courseYearError:Int? = null,
    val nameError:Int? = null,
    val courseError:Int? = null,
    val instituteError:Int? = null,
    val isValid:Boolean = false
)
