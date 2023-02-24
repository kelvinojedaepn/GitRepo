package com.example.amazon_adle

import android.content.Intent
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.SearchView
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Getting views
        val searchField = findViewById<SearchView>(R.id.sv_search)
        val backImageButton = findViewById<ImageButton>(R.id.img_btn_back)
        val searchImageButton = findViewById<ImageButton>(R.id.img_btn_search)
        val homeImageButton = findViewById<ImageButton>(R.id.image_btn_home)
        val carImageButton = findViewById<ImageButton>(R.id.image_btn_car)
        val shippingFrameLayout = findViewById<FrameLayout>(R.id.fl_shipping_to_ecuador)
        val backgroundImage = findViewById<ImageView>(R.id.img_background)
        val relatedFrameLayout = findViewById<FrameLayout>(R.id.fl_related_products)
        val cutsRelativeLayout = findViewById<RelativeLayout>(R.id.rl_cuts)
        val saleRelativeLayout = findViewById<RelativeLayout>(R.id.rl_sale_text)
        val fashionImageView = findViewById<ImageView>(R.id.img_fashion)
        val salesImageView = findViewById<ImageView>(R.id.img_sales)
        val beautyImageView = findViewById<ImageView>(R.id.img_beauty)
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

        // Setting car and home button images
        homeImageButton.setImageDrawable(
            AppCompatResources.getDrawable(this, R.drawable.home_selected)
        )
        carImageButton.setImageDrawable(
            AppCompatResources.getDrawable(this, R.drawable.car)
        )

        // Settings rounded corners to images
        setRoundedCornerImage(fashionImageView)
        setRoundedCornerImage(salesImageView)
        setRoundedCornerImage(beautyImageView)

        // Hiding or showing views when focusing search view
        searchField.setOnQueryTextFocusChangeListener { _, _ ->
            if (backgroundImage.visibility == View.VISIBLE) {
                val svLayoutParams = searchField.layoutParams as ConstraintLayout.LayoutParams
                svLayoutParams.width = 900
                searchField.layoutParams = svLayoutParams

                backgroundImage.visibility = View.INVISIBLE
                shippingFrameLayout.visibility = View.INVISIBLE
                relatedFrameLayout.visibility = View.INVISIBLE
                cutsRelativeLayout.visibility = View.INVISIBLE
                saleRelativeLayout.visibility = View.INVISIBLE
                setOnSearchViewFocus(searchImageButton)
            }
            else {
                val svLayoutParams = searchField.layoutParams as ConstraintLayout.LayoutParams
                svLayoutParams.width = 1000
                searchField.layoutParams = svLayoutParams

                backgroundImage.visibility = View.VISIBLE
                shippingFrameLayout.visibility = View.VISIBLE
                relatedFrameLayout.visibility = View.VISIBLE
                cutsRelativeLayout.visibility = View.VISIBLE
                saleRelativeLayout.visibility = View.VISIBLE
                setOffSearchViewFocus(searchImageButton)
            }
        }

        // Handling search view query actions
        searchField.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            // Sending a string to search a product
            override fun onQueryTextSubmit(query: String?): Boolean {
                val intent = Intent(
                    this@MainActivity,
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

        // Setting image button back action
        backImageButton.setOnClickListener {
            if (backgroundImage.visibility == View.VISIBLE) {
                val svLayoutParams = searchField.layoutParams as ConstraintLayout.LayoutParams
                svLayoutParams.width = 900
                searchField.layoutParams = svLayoutParams

                backgroundImage.visibility = View.INVISIBLE
                shippingFrameLayout.visibility = View.INVISIBLE
                relatedFrameLayout.visibility = View.INVISIBLE
                cutsRelativeLayout.visibility = View.INVISIBLE
                saleRelativeLayout.visibility = View.INVISIBLE
            }
            else {
                val svLayoutParams = searchField.layoutParams as ConstraintLayout.LayoutParams
                svLayoutParams.width = 1000
                searchField.layoutParams = svLayoutParams

                searchField.clearFocus()
                backgroundImage.visibility = View.VISIBLE
                shippingFrameLayout.visibility = View.VISIBLE
                relatedFrameLayout.visibility = View.VISIBLE
                cutsRelativeLayout.visibility = View.VISIBLE
                saleRelativeLayout.visibility = View.VISIBLE
                setOnSearchViewFocus(searchImageButton)
            }
        }

        // Setting image button car action
        carImageButton.setOnClickListener {
            openActivity(CarRecycleView::class.java)
        }
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

    private fun setRoundedCornerImage(imageView: ImageView) {
        val drawable = imageView.drawable
        val bitmap = (drawable as BitmapDrawable).bitmap
        val output = Bitmap.createBitmap(
            bitmap.width,
            bitmap.height, Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(output)

        val color = Color.RED
        val paint = Paint()
        val rect = Rect(0, 0, bitmap.width, bitmap.height)
        val rectF = RectF(rect)
        val roundPx = 10f

        paint.isAntiAlias = true
        canvas.drawARGB(0, 0, 0, 0)
        paint.color = color
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint)

        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        canvas.drawBitmap(bitmap, rect, rect, paint)

        imageView.setImageBitmap(output)
    }


    private fun openActivity (
        activityClass: Class<*>
    ) {
        val intent = Intent(this, activityClass)
        startActivity(intent)
    }
}