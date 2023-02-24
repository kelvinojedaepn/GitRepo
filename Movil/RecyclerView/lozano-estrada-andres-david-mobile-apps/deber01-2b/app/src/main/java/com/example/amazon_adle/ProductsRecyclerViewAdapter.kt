package com.example.amazon_adle

import android.annotation.SuppressLint
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StrikethroughSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductsRecyclerViewAdapter(
    private val list: ArrayList<Product>
): RecyclerView.Adapter<ProductsRecyclerViewAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val titleTextView: TextView
        val imageImageView: ImageView
        val reviewsTextView: TextView
        val starsImageView: ImageView
        val priceIntTextView: TextView
        val priceDecTextView: TextView
        val discountTextView: TextView
        val shippingTextView: TextView

        init {
            titleTextView = view.findViewById(R.id.tv_product_title)
            imageImageView = view.findViewById(R.id.img_product_image)
            reviewsTextView = view.findViewById(R.id.tv_product_reviews)
            starsImageView = view.findViewById(R.id.img_product_stars)
            priceIntTextView = view.findViewById(R.id.tv_product_int_price)
            priceDecTextView = view.findViewById(R.id.tv_product_dec_price)
            discountTextView = view.findViewById(R.id.tv_product_discount)
            shippingTextView = view.findViewById(R.id.tv_product_shipping)
        }
    }

    interface OnButtonClickListener {
        fun onButtonClicked(selectedProduct: Product)
    }

    private var buttonClickListener: OnButtonClickListener? = null

    fun setOnButtonClickListener(listener: OnButtonClickListener) {
        this.buttonClickListener = listener
    }

    // Product layout setting
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.recycler_view_product,
                parent,
                false
            )
        return MyViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentProduct = this.list[position]
        val oldPriceStr = "US$" + String.format(
            "%.2f",
            currentProduct.price + currentProduct.price * currentProduct.discount
        )
        val spannableString = SpannableString(oldPriceStr)
        spannableString.setSpan(
            StrikethroughSpan(),
            0,
            oldPriceStr.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        holder.titleTextView.text = currentProduct.title    // Title
        holder.imageImageView.setImageResource(currentProduct.imageResourceId)  // Image
        holder.reviewsTextView.text = currentProduct.reviews.toString() // Reviews
        holder.priceIntTextView.text = currentProduct.price.toInt().toString()  // Integer Price
        holder.priceDecTextView.text = (String.format(  // Decimal Price
            "%.2f", currentProduct.price - currentProduct.price.toInt()
        ).toDouble() * 100).toInt().toString()

        // Old Price
        if(currentProduct.discount != 0.0)
            holder.discountTextView.text = spannableString
        else
            holder.discountTextView.text = ""

        // Stars
        when (currentProduct.stars) {
            1 -> holder.starsImageView.setImageResource(R.drawable.amazon_stars_1)
            2 -> holder.starsImageView.setImageResource(R.drawable.amazon_stars_2)
            3 -> holder.starsImageView.setImageResource(R.drawable.amazon_stars_3)
            4 -> holder.starsImageView.setImageResource(R.drawable.amazon_stars_4)
            5 -> holder.starsImageView.setImageResource(R.drawable.amazon_stars_5)
        }

        // Shipping
        if(!currentProduct.shippingToEcuador)
            holder.shippingTextView.text = "Sin envíos a Ecuador"

        // Setting add to car action
        holder.imageImageView.setOnClickListener {
            buttonClickListener?.onButtonClicked(this.list[position])
        }
    }

    override fun getItemCount(): Int {
        return this.list.size
    }

}