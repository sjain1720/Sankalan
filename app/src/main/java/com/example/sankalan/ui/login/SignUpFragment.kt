package com.example.sankalan.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.sankalan.R
import com.example.sankalan.activities.MainActivity
import com.example.sankalan.databinding.FragmentSignUpBinding
import com.example.sankalan.ui.login.data.LoggedInUser
import com.example.sankalan.ui.login.model.AuthenticationViewModel
import com.example.sankalan.ui.login.model.AuthenticationViewModelFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SignUpFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignUpFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    //variable
    lateinit var signupBinding:FragmentSignUpBinding
    lateinit var signupViewmodel:AuthenticationViewModel
    lateinit var data:LoggedInUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        signupBinding = FragmentSignUpBinding.inflate(inflater)
        signupViewmodel = ViewModelProvider(this,AuthenticationViewModelFactory()).get(AuthenticationViewModel::class.java)
        return signupBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val name = signupBinding.FullName
        val mobile = signupBinding.mobileNum
        val institute = signupBinding.CollegeName
        val year = signupBinding.courseYear
        val course = signupBinding.CourseName
        val email = signupBinding.Email
        val password = signupBinding.createPassword
        val signupButton = signupBinding.signUp
        //viewmodel = ViewModelProvider(this,AuthenticationViewModelFactory()).get(AuthenticationViewModel::class.java)


        mobile.addTextChangedListener {
            if (it.toString().length != 10){
                mobile.error = getString(R.string.invalid_mobile)
            }
        }
        year.addTextChangedListener {
            if(it.toString().toLong() !in 1..4){
                year.error = getString(R.string.invalid_course_year)
            }
        }
        email.addTextChangedListener {
            signupViewmodel.onSignupDataChange(email = it.toString(), password = password.text.toString())
        }
        password.addTextChangedListener {
            signupViewmodel.onSignupDataChange(email = email.text.toString(), password = it.toString())
        }
        signupButton.isEnabled = true
        signupViewmodel.signUpForm.observe(viewLifecycleOwner, Observer {
            signupButton.isEnabled = it.isValid //temp

            if(it.emailError!=null){
                email.error = getString(it.emailError)
            }
            if(it.passError!=null){
                password.error = getString(it.passError)
            }

        })
        signupViewmodel.user.observe(viewLifecycleOwner, Observer {
            if(it!=null){
                startActivity(Intent(activity, MainActivity::class.java))
                activity?.finish()
            }
        })
        signupButton.setOnClickListener {
            if(name.text.isNotEmpty()&&
            mobile.text.isNotEmpty()&&
            institute.text.isNotEmpty()&&
            year.text.isNotEmpty()&&
            course.text.isNotEmpty()&&
            email.text.isNotEmpty()&&
            password.text.isNotEmpty()){
                 data = LoggedInUser(
                    name = name.text.toString(),
                    mobile = mobile.text.toString(),
                    institute = institute.text.toString(),
                    year = year.text.toString().toLong(),
                    course = course.text.toString(),
                )
                signupViewmodel.signUp(email.text.toString(),password.text.toString(),data)

            }else{
                Toast.makeText(context,"Required All Fields.",Toast.LENGTH_SHORT).show()
            }
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SignUpFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SignUpFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}