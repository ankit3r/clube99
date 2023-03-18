package com.gyanhub.todayclube99.fragment.menusFragment

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.gyanhub.todayclube99.R
import com.gyanhub.todayclube99.activity.DetailsActivity
import com.gyanhub.todayclube99.databinding.FragmentAddCashBinding
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import org.json.JSONException
import org.json.JSONObject
import kotlin.math.roundToInt

class AddCashFragment : Fragment() , PaymentResultListener {

private lateinit var binding : FragmentAddCashBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       binding = FragmentAddCashBinding.inflate(layoutInflater,container,false)
        (context as DetailsActivity).title = "Add Cash"

        binding.btnAdd.setOnClickListener{
            if (binding.eTxtEnterAddAmount.text.isNotEmpty()){
                val amt = binding.eTxtEnterAddAmount.text.toString()
                val amount = (amt.toFloat() * 100).roundToInt()
                startPayment(amount)
            }else{
                return@setOnClickListener
            }
        }



        return binding.root
    }


    private fun startPayment(amount: Int) {
        val checkout = Checkout()
        checkout.setKeyID("rzp_test_6W1BYpvpRvFDXQ")
        try {
            val options = JSONObject()
            options.put("name", "club-99")
            options.put("description", "Learning tutorial")

            //You can omit the image option to fetch the image from dashboard
            //options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png")

            options.put("theme.color", "#00FFEE")
            options.put("currency", "INR")

            /** Generated from backend **/
            //      options.put("order_id", "order_DBJOWzybf0sJbb");

            /** Pass in paise in INR  ( Example  Rs 5 = 500 paise ) **/
            options.put("amount", "${(amount * 100)/100}")//pass amount in currency subunits


            options.put("prefill.email", "random@gmail.com")
            options.put("prefill.contact", "+919442009211")

            checkout.open(context as Activity, options)

        } catch (e: Exception) {
            Toast.makeText(context, "Error in payment: " + e.message, Toast.LENGTH_LONG)
                .show()
            e.printStackTrace()
        }

    }


    override fun onPaymentSuccess(p0: String?) {
        Log.d("ANKIT","Payment is successful : $p0")
        Toast.makeText(context, "Payment is successful : $p0", Toast.LENGTH_SHORT).show()
    }

    override fun onPaymentError(p0: Int, p1: String?) {
        Log.d("ANKIT","Error : $p0")
        Log.d("ANKIT","Error : $p1")
        Toast.makeText(context, "Error : $p1", Toast.LENGTH_SHORT).show()
    }
}