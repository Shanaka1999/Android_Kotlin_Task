package com.example.myapplication

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog

class ViewItemActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_item)

        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setDisplayShowCustomEnabled(true)
        val customActionBarView = layoutInflater.inflate(R.layout.custom_actionbar, null)
        val titleTextView = customActionBarView.findViewById<TextView>(R.id.action_bar_title)
        val title = intent.getStringExtra("title") ?: "Default Title"
        titleTextView.text = title
        supportActionBar?.customView = customActionBarView
        supportActionBar?.elevation = 0f

        val backButton = customActionBarView.findViewById<Button>(R.id.backButton)

        if (titleTextView.text == "Restaurant Menu") {
            backButton.visibility = View.GONE
        } else {
            backButton.setOnClickListener {
                onBackPressed()
            }
        }

        val itemName = intent.getStringExtra("name")
        val itemPrice = intent.getDoubleExtra("price", 0.0)

        val nameTextView: TextView = findViewById(R.id.itemNameTextView)
        val priceTextView: TextView = findViewById(R.id.itemPriceTextView)

        nameTextView.text = "Item: $itemName"
        priceTextView.text = "Price: LKR $itemPrice"

        val buyButton: Button = findViewById(R.id.buyButton)
        buyButton.setOnClickListener {
            // Handle button click here
            buyButton.text = "Ordered"
            buyButton.isEnabled = false
            buyButton.setBackgroundColor(getColor(R.color.buyButtonWhite))
            buyButton.setTextColor(Color.BLACK)

            showDeliveryConfirmationPopup()

        }
    }
    private fun showDeliveryConfirmationPopup() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Order Confirmation")
            .setMessage("Your order will be delivered to you within 30 minutes.")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }

        val alertDialog = builder.create()
        alertDialog.show()
    }
}

