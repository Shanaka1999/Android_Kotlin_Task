package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar

class MainActivity : AppCompatActivity() {

    private lateinit var menuListView: ListView
    private lateinit var menuAdapter: ArrayAdapter<String>
    private lateinit var menuItems: List<MenuItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setDisplayShowCustomEnabled(true)
        val customActionBarView = layoutInflater.inflate(R.layout.custom_actionbar, null)
        val titleTextView = customActionBarView.findViewById<TextView>(R.id.action_bar_title)
        titleTextView.text = "Restaurant Menu"
        supportActionBar?.customView = customActionBarView
        supportActionBar?.elevation = 0f

        titleTextView.gravity = Gravity.CENTER

        val backButton = customActionBarView.findViewById<Button>(R.id.backButton)

        if (titleTextView.text == "Restaurant Menu") {
            backButton.visibility = View.GONE
        } else {
            backButton.setOnClickListener {
                onBackPressed()
            }
        }



        val menuItems = listOf(
            MenuItem("Pizza", "A delicious cheese and tomato pizza", 109.00),
            MenuItem("Burger", "Juicy beef burger with cheese and lettuce", 850.00),
            MenuItem("Kottu", "Spicy and flavorful Sri Lankan street food made with godhamba roti, vegetables, and your choice of meat.", 120.00),
            MenuItem("Rice", "Steamed white rice served with a variety of side dishes", 600.00),
            MenuItem("Noodles", "Stir-fried noodles with a mix of fresh vegetables and your choice of chicken, beef, or shrimp.", 925.00),
            MenuItem("Pasta", "Classic Italian pasta, served with a rich tomato sauce and a hint of basil", 110.00),
            MenuItem("Roti", "Traditional unleavened flatbread, often served with curry", 250.00),
            MenuItem("Wadei", "Deep-fried lentil dumplings, a popular Sri Lankan snack", 375.50),
            MenuItem("Parata", "Flaky, layered flatbread often enjoyed with savory or sweet fillings", 467.50),
            MenuItem("Thosai", "South Indian-style fermented crepes, typically served with chutney", 55.00),
            MenuItem("String Hoppers", "Steamed rice noodle nests, a common dish in Sri Lankan cuisine", 897.00),
            MenuItem("Hoppers", "Bowl-shaped pancakes made from fermented rice flour batter", 734.00),
            MenuItem("Macaroni", "Pasta tubes with your choice of sauce, a classic comfort food", 600.75)
            // Add more items here if needed
        )


        menuListView = findViewById(R.id.menuListView)
        menuAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, menuItems.map { it.name })
        menuListView.adapter = menuAdapter

        menuListView.setOnItemClickListener { _, _, position, _ ->
            val menuItem = menuItems[position]

            // Start the ViewItemActivity with item details
            val intent = Intent(this, ViewItemActivity::class.java).apply {
                putExtra("name", menuItem.name)
                putExtra("description", menuItem.description)
                putExtra("price", menuItem.price)
                putExtra("title", "Dish Details")
            }
            startActivity(intent)}
    }
}
