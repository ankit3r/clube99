package com.gyanhub.todayclube99.fragment.menusFragment

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.ContentResolver
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.Toast
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import com.gyanhub.todayclube99.R
import com.gyanhub.todayclube99.activity.DetailsActivity
import com.gyanhub.todayclube99.databinding.FragmentKycBinding
import com.gyanhub.todayclube99.model.UserModel
import com.gyanhub.todayclube99.time.GetTime
import com.gyanhub.todayclube99.time.KeyBoardHide
import com.gyanhub.todayclube99.uitle.FirebaseUtils
import com.gyanhub.todayclube99.uitle.ToastMasssage
import java.io.IOException
import java.util.*

class KycFragment : Fragment() {
    private lateinit var binding: FragmentKycBinding
    private var frontAaUri: Uri? = null
    private var backAaUri: Uri? = null
    private var firebaseStore: FirebaseStorage? = null
    private var storageReference: StorageReference? = null

    private var FImg = ""
    private var BImg = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentKycBinding.inflate(layoutInflater, container, false)
        (context as DetailsActivity).title = "Kyc"
        firebaseStore = FirebaseStorage.getInstance()
        storageReference = FirebaseStorage.getInstance().reference
        getKycData()


        binding.btnSubmit.isEnabled = false
        binding.layoutContainer.setOnClickListener {
            KeyBoardHide(context as Activity).keyHide(it)
        }
        binding.txtUploadFront.setOnClickListener {
            launchGallery(100)
        }
        binding.txtUploadBack.setOnClickListener {
            launchGallery(99)
        }

        btnEnable()
        binding.btnSubmit.setOnClickListener {
            uploadDoc(FImg, BImg, binding.eTxtAaNo.text.toString())
        }

        return binding.root
    }

    private fun launchGallery(pickImage: Int) {
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startActivityForResult(gallery, pickImage)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == 100) {
            frontAaUri = data?.data
            binding.txtUploadFront.visibility = View.GONE
            binding.imgFront.visibility = View.VISIBLE
            binding.imgFront.setImageURI(frontAaUri)
            uploadImage(100)
            btnEnable()
        }
        if (resultCode == RESULT_OK && requestCode == 99) {
            backAaUri = data?.data
            binding.txtUploadBack.visibility = View.GONE
            binding.imgBack.visibility = View.VISIBLE
            binding.imgBack.setImageURI(backAaUri)
            uploadImage(99)
            btnEnable()
        }
    }

    private fun uploadImage(num: Int) {
        (context as DetailsActivity).progre("Image", "Image Uploading...")
        if (frontAaUri != null) {
            val ref = storageReference?.child(
                "KYC/${FirebaseUtils().userId}/" + UUID.randomUUID().toString()
            )
            val uploadTask = ref?.putFile(frontAaUri!!)
            val urlTask = uploadTask?.continueWithTask { task ->
                if (!task.isSuccessful) {
                    task.exception?.let {
                        throw it
                    }
                }
                ref.downloadUrl
            }?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val downloadUri = task.result
                    if (num == 100) {
                        FImg = downloadUri.toString()
                    } else {
                        BImg = downloadUri.toString()
                    }
                    (context as DetailsActivity).progressDialog.dismiss()
                }
            }
        }
    }

    private fun btnEnable() {
        if (binding.eTxtAaNo.text.isNotEmpty() && frontAaUri.toString()
                .isNotEmpty() && backAaUri.toString().isNotEmpty()
        ) {
            binding.btnSubmit.isEnabled = true
        }
    }

    private fun uploadDoc(fImg: String, Bimg: String, Ano: String) {
        val kyc = hashMapOf(
            "frontImg" to fImg,
            "backImg" to Bimg,
            "aadharNo" to Ano,
            "status" to "KYC Document Verification in Pending"
        )
        FirebaseUtils().fireStoreDatabase.collection("users").document(FirebaseUtils().userId)
            .collection("KYC").document("KYC${FirebaseUtils().userId}")
            .set(kyc).addOnSuccessListener {
                getKycData()
            }.addOnFailureListener {
                ToastMasssage(context as Activity, "Data Adding failed")
            }
    }

    private fun getKycData() {
        (context as DetailsActivity).progre("Please Wait", "Loading...")
        FirebaseUtils().fireStoreDatabase.collection("users").document(FirebaseUtils().userId)
            .collection("KYC").document("KYC${FirebaseUtils().userId}").get()
            .addOnSuccessListener { document ->
                if (document.data?.get("status") != null) {
                    binding.txt02.text = document.data?.get("status").toString()
                    binding.cardA.visibility = View.GONE
                    (context as DetailsActivity).progressDialog.dismiss()
                } else {
                    ToastMasssage(context as Activity, "No such document")
                    binding.txt02.text = "KYC Document Verification"
                    binding.cardA.visibility = View.VISIBLE
                    (context as DetailsActivity).progressDialog.dismiss()
                }
            }.addOnFailureListener { exception ->
                ToastMasssage(context as Activity, "get failed with $exception")
                binding.txt02.text = "KYC Document Verification"
                binding.cardA.visibility = View.VISIBLE
                (context as DetailsActivity).progressDialog.dismiss()
            }
    }
}

