package com.gyanhub.todayclube99.activity


import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.gyanhub.todayclube99.R
import com.gyanhub.todayclube99.databinding.ActivityStartBinding
import com.gyanhub.todayclube99.fragment.startFragment.LoginFragment
import com.gyanhub.todayclube99.time.KeyBoardHide

@Suppress("DEPRECATION")
class StartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStartBinding
    private lateinit var progressDialog: ProgressDialog

    lateinit var mGoogleSignInClient: GoogleSignInClient
    val Req_Code: Int = 478
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)


        FirebaseApp.initializeApp(this)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        firebaseAuth = FirebaseAuth.getInstance()



        if (supportFragmentManager.findFragmentById(R.id.vPage2) != null) {
            supportFragmentManager.popBackStack()
        } else {
            val fragmentTransient = supportFragmentManager.beginTransaction()
            fragmentTransient.add(R.id.vPage2, LoginFragment())
            fragmentTransient.commit()
        }


    }

    fun signInGoogle() {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, Req_Code)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Req_Code) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleResult(task)
        }
    }

    private fun handleResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount? = completedTask.getResult(ApiException::class.java)
            if (account != null) {
                UpdateUI(account)
            }
        } catch (e: ApiException) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun UpdateUI(account: GoogleSignInAccount) {
        progre("Login", "Wait few second...")
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                progressDialog.dismiss()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }.addOnFailureListener {
            progressDialog.dismiss()
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = firebaseAuth.currentUser

        if (GoogleSignIn.getLastSignedInAccount(this) != null || currentUser != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun progre(title: String, mass: String) {
        progressDialog = ProgressDialog(this)
        progressDialog.setCancelable(false)
        progressDialog.setTitle(title)
        progressDialog.setMessage(mass)
        progressDialog.show()
    }

    fun signUpUser(email: String, Password: String) {
        progre("Register", "Wait few second...")
        firebaseAuth.createUserWithEmailAndPassword(email, Password).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "Successfully Singed Up", Toast.LENGTH_SHORT).show()
                progressDialog.dismiss()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }.addOnFailureListener {
            progressDialog.dismiss()
            Toast.makeText(
                this,
                "The email address is already in use by another account.",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    fun login(email: String, Password: String, forV: View) {
        KeyBoardHide(this).keyHide(forV)
        progre("Login", "Wait few second...")
        firebaseAuth.signInWithEmailAndPassword(email, Password).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "Successfully LoggedIn", Toast.LENGTH_SHORT).show()
                progressDialog.dismiss()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }.addOnFailureListener {
            progressDialog.dismiss()
            forV.visibility = View.VISIBLE
            Toast.makeText(this, "LogIn Failed", Toast.LENGTH_SHORT).show()
            Log.d("ANKIT", it.toString())
        }
    }
    fun forgotPass(email: String){
        progressDialog.show()
        firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener{
            Toast.makeText(this, "check your Email", Toast.LENGTH_LONG).show()
            progressDialog.dismiss()
        }.addOnFailureListener{
            progressDialog.dismiss()
            Toast.makeText(this, "Something wrong.", Toast.LENGTH_LONG).show()
        }
    }
}