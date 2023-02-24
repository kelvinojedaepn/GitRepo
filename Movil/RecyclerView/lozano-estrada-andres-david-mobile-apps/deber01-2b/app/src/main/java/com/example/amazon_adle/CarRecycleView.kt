package com.example.amazon_adle

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.SearchView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class CarRecycleView : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_recycle_view)

        // Getting products data
        val carProductsList = CarDataArrayList.carProductsList

        // Getting views
        val searchField = findViewById<SearchView>(R.id.sv_search)
        val backImageButton = findViewById<ImageButton>(R.id.img_btn_back)
        val searchImageButton = findViewById<ImageButton>(R.id.img_btn_search)
        val recyclerView = findViewById<RecyclerView>(R.id.rv_car_products)
        val totalFrameLayout = findViewById<FrameLayout>(R.id.fl_total)
        val shippingFrameLayout = findViewById<FrameLayout>(R.id.fl_shipping_to_ecuador)
        val homeImageButton = findViewById<ImageButton>(R.id.image_btn_home)
        val carImageButton = findViewById<ImageButton>(R.id.image_btn_car)
        val totalIntTextView = findViewById<TextView>(R.id.tv_total_int_price)
        val totalDecTextView = findViewById<TextView>(R.id.tv_total_dec_price)
        val carCountTextView = findViewById<TextView>(R.id.tv_car_count)
        val payButton = findViewById<Button>(R.id.btn_pay)

        // Setting car recycler view
        initializeRecyclerView(carProductsList, recyclerView)

        if(CarDataArrayList.carProductsList.size > 0) {
            // Setting cart count value
            carCountTextView.text = CarDataArrayList.carProductsList.size.toString()
            payButton.text = "Proceder al pago (" + carProductsList.size + " productos)"

            // Setting total values
            totalIntTextView.text = CarDataArrayList.getIntTotalPrice().toString()
            totalDecTextView.text = CarDataArrayList.getDecimalTotalPrice().toString()
        }
        else {
            carCountTextView.text = ""
            totalIntTextView.text = "0"
            totalDecTextView.text = "00"
            payButton.text = "No tienes productos para efectuar un pago"
        }
        carCountTextView.setTextColor(
            AppCompatResources.getColorStateList(this, R.color.dark_cyan)
        )

        // Getting queries
        val query = intent.getStringExtra("query")
        searchField.setQuery(query, false)

        // Setting car and home button images
        homeImageButton.setImageDrawable(
            AppCompatResources.getDrawable(this, R.drawable.home)
        )
        carImageButton.setImageDrawable(
            AppCompatResources.getDrawable(this, R.drawable.car_selected)
        )

        // Hiding or showing views when focusing search view
        searchField.setOnQueryTextFocusChangeListener { _, _ ->
            if (recyclerView.visibility == View.VISIBLE) {
                val svLayoutParams = searchField.layoutParams as ConstraintLayout.LayoutParams
                svLayoutParams.width = 900
                searchField.layoutParams = svLayoutParams

                recyclerView.visibility = View.INVISIBLE
                totalFrameLayout.visibility = View.INVISIBLE
                shippingFrameLayout.visibility = View.INVISIBLE
                setOnSearchViewFocus(searchImageButton)
            }
            else {
                val svLayoutParams = searchField.layoutParams as ConstraintLayout.LayoutParams
                svLayoutParams.width = 1000
                searchField.layoutParams = svLayoutParams

                recyclerView.visibility = View.VISIBLE
                totalFrameLayout.visibility = View.VISIBLE
                shippingFrameLayout.visibility = View.VISIBLE
                setOffSearchViewFocus(searchImageButton)
            }
        }

        // Handling search view query actions
        searchField.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            // Sending a string to search a product
            override fun onQueryTextSubmit(query: String?): Boolean {
                val intent = Intent(
                    this@CarRecycleView,
                    ProductsRecyclerView::class.java
                )
                intent.putExtra("query", query)
                startActivity(intent)
                return false
            }

            // Handling a change in the query
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        // Setting delete car product action
        val carAdapter = recyclerView.adapter as CarRecyclerViewAdapter
        carAdapter.setOnButtonClickListener(object: CarRecyclerViewAdapter.OnButtonClickListener {
            @SuppressLint("SetTextI18n")
            override fun onButtonClicked(position: Int) {
                if(carProductsList.size > 0) {
                    carCountTextView.text = CarDataArrayList.carProductsList.size.toString()
                    totalIntTextView.text = CarDataArrayList.getIntTotalPrice().toString()
                    totalDecTextView.text = CarDataArrayList.getDecimalTotalPrice().toString()
                    payButton.text = "Proceder al pago (" + carProductsList.size + " productos)"
                }
                else {
                    carCountTextView.text = ""
                    totalIntTextView.text = "0"
                    totalDecTextView.text = "00"
                    payButton.text = "No tienes productos para efectuar un pago"
                }
            }
        })

        // Setting image button back action
        backImageButton.setOnClickListener {
            if (recyclerView.visibility == View.VISIBLE) {
                val svLayoutParams = searchField.layoutParams as ConstraintLayout.LayoutParams
                svLayoutParams.width = 900
                searchField.layoutParams = svLayoutParams

                recyclerView.visibility = View.INVISIBLE
                totalFrameLayout.visibility = View.INVISIBLE
                shippingFrameLayout.visibility = View.INVISIBLE
            }
            else {
                val svLayoutParams = searchField.layoutParams as ConstraintLayout.LayoutParams
                svLayoutParams.width = 1000
                searchField.layoutParams = svLayoutParams

                searchField.clearFocus()
                recyclerView.visibility = View.VISIBLE
                totalFrameLayout.visibility = View.VISIBLE
                shippingFrameLayout.visibility = View.VISIBLE
                setOnSearchViewFocus(searchImageButton)
            }
        }

        // Setting image button home action
        homeImageButton.setOnClickListener {
            openActivity(MainActivity::class.java)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initializeRecyclerView(list: ArrayList<Product>, recyclerView: RecyclerView) {
        val adapter = CarRecyclerViewAdapter(list)

        recyclerView.adapter = adapter
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        adapter.notifyDataSetChanged()
    }

    private fun setOnSearchViewFocus(searchImageButton: ImageButton) {
        val sImgBtnLayoutParams = searchImageButton.layoutParams as ConstraintLayout.LayoutParams

        sImgBtnLayoutParams.width = 0
        sImgBtnLayoutParams.height = 0

        searchImageButton.layoutParams = sImgBtnLayoutParams
    }

    private fun setOffSearchViewFocus(searchImageButton: ImageButton) {
        val sImgBtnLayoutParams = searchImageButton.layoutParams as ConstraintLayout.LayoutParams

        sImgBtnLayoutParams.width = 40
        sImgBtnLayoutParams.height = 40

        searchImageButton.layoutParams = sImgBtnLayoutParams
    }

    private fun openActivity (
        activityClass: Class<*>
    ) {
        val intent = Intent(this, activityClass)
        startActivity(intent)
    }
}