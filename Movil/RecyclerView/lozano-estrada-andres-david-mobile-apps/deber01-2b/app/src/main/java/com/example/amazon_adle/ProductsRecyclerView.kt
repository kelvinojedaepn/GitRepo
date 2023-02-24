package com.example.amazon_adle

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.HorizontalScrollView
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.SearchView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class ProductsRecyclerView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products_recycler_view)

        // Getting products data
        val productsList = ProductsDataArrayList.productsList

        // Getting views
        val recyclerView = findViewById<RecyclerView>(R.id.rv_products)
        val searchField = findViewById<SearchView>(R.id.sv_search)
        val backImageButton = findViewById<ImageButton>(R.id.img_btn_back)
        val searchImageButton = findViewById<ImageButton>(R.id.img_btn_search)
        val resultsConstraint = findViewById<ConstraintLayout>(R.id.cl_results)
        val filtersScrollView = findViewById<HorizontalScrollView>(R.id.hsv_filters)
        val filterButton = findViewById<Button>(R.id.btn_filter)
        val priceFilterButton = findViewById<Button>(R.id.btn_minus_of_500)
        val starsFilterButton = findViewById<ImageButton>(R.id.btn_4_stars)
        val homeImageButton = findViewById<ImageButton>(R.id.image_btn_home)
        val carImageButton = findViewById<ImageButton>(R.id.image_btn_car)
        val carCountTextView = findViewById<TextView>(R.id.tv_car_count)

        // Getting queries
        val query = intent.getStringExtra("query")
        searchField.setQuery(query, false)

        // Setting cart count value
        if(CarDataArrayList.carProductsList.size > 0) {
            carCountTextView.text = CarDataArrayList.carProductsList.size.toString()
        }
        else {
            carCountTextView.text = ""
        }
        carCountTextView.setTextColor(
            AppCompatResources.getColorStateList(this, R.color.black)
        )

        // Filtering products list according to the query
        if(ArrayList(productsList.filter {it.category == query}).isNotEmpty()) {
            initializeRecyclerView(
                ArrayList(productsList.filter {it.category == query}),
                recyclerView
            )
        }
        else {
            productsList.shuffle()

            initializeRecyclerView(
                productsList,
                recyclerView
            )
        }

        // Setting car and home button images
        homeImageButton.setImageDrawable(
            AppCompatResources.getDrawable(this, R.drawable.home_selected)
        )
        carImageButton.setImageDrawable(
            AppCompatResources.getDrawable(this, R.drawable.car)
        )

        // Hiding or showing views when focusing search view
        searchField.setOnQueryTextFocusChangeListener { _, _ ->
            if (recyclerView.visibility == View.VISIBLE) {
                recyclerView.visibility = View.INVISIBLE
                resultsConstraint.visibility = View.INVISIBLE
                filtersScrollView.visibility = View.INVISIBLE
                filterButton.visibility = View.INVISIBLE
                setOnSearchViewFocus(searchImageButton)
            }
            else {
                recyclerView.visibility = View.VISIBLE
                resultsConstraint.visibility = View.VISIBLE
                filtersScrollView.visibility = View.VISIBLE
                filterButton.visibility = View.VISIBLE
                setOffSearchViewFocus(searchImageButton)
            }
        }

        // Handling search view query actions
        searchField.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            // Sending a string to search a product
            override fun onQueryTextSubmit(query: String?): Boolean {
                val intent = Intent(
                    this@ProductsRecyclerView,
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
        val productsAdapter = recyclerView.adapter as ProductsRecyclerViewAdapter
        productsAdapter.setOnButtonClickListener(object: ProductsRecyclerViewAdapter.OnButtonClickListener {
            @SuppressLint("SetTextI18n")
            override fun onButtonClicked(selectedProduct: Product) {
                CarDataArrayList.carProductsList.add(selectedProduct)
                carCountTextView.text = CarDataArrayList.carProductsList.size.toString()
            }
        })

        // Setting image button back action
        backImageButton.setOnClickListener {
            if (recyclerView.visibility == View.VISIBLE) {
                openActivity(MainActivity::class.java)
            }
            else {
                searchField.clearFocus()
                recyclerView.visibility = View.VISIBLE
                resultsConstraint.visibility = View.VISIBLE
                filtersScrollView.visibility = View.VISIBLE
                filterButton.visibility = View.VISIBLE
                setOnSearchViewFocus(searchImageButton)
            }
        }

        // Setting image button home action
        homeImageButton.setOnClickListener {
            openActivity(MainActivity::class.java)
        }

        // Setting image button car action
        carImageButton.setOnClickListener {
            openActivity(CarRecycleView::class.java)
        }

        // Setting button price filter action
        priceFilterButton.setOnClickListener {
            initializeRecyclerView(
                ArrayList(productsList.filter {it.category == query && it.price <= 500.0 }),
                recyclerView
            )
        }

        // Setting button stars filter action
        starsFilterButton.setOnClickListener {
            initializeRecyclerView(
                ArrayList(productsList.filter {it.category == query && it.stars == 4}),
                recyclerView
            )
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initializeRecyclerView(list: ArrayList<Product>, recyclerView: RecyclerView) {
        val adapter = ProductsRecyclerViewAdapter(list)

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