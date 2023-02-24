package com.example.amazon_adle

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CarRecyclerViewAdapter(
    private val list: ArrayList<Product>
): RecyclerView.Adapter<CarRecyclerViewAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val imageImageView: ImageView
        val titleTextView: TextView
        val intPriceTextView: TextView
        val decPriceTextView: TextView
        val deleteButton: Button

        init {
            imageImageView = view.findViewById(R.id.img_car_product)
            titleTextView = view.findViewById(R.id.tv_car_product_title)
            intPriceTextView = view.findViewById(R.id.tv_car_product_int_price)
            decPriceTextView = view.findViewById(R.id.tv_car_product_decimal_price)
            deleteButton = view.findViewById(R.id.btn_delete)
        }
    }

    interface OnButtonClickListener {
        fun onButtonClicked(position: Int)
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
                R.layout.recycler_view_car_product,
                parent,
                false
            )
        return MyViewHolder(itemView)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentProduct = this.list[position]

        holder.titleTextView.text = currentProduct.title    // Title
        holder.imageImageView.setImageResource(currentProduct.imageResourceId)  // Image
        holder.intPriceTextView.text = currentProduct.price.toInt().toString()  // Integer Price
        holder.decPriceTextView.text = (String.format(  // Decimal Price
            "%.2f", currentProduct.price - currentProduct.price.toInt()
        ).toDouble() * 100).toInt().toString()

        // Setting delete button action
        holder.deleteButton.setOnClickListener {
            this.list.removeAt(position)
            notifyDataSetChanged()
            buttonClickListener?.onButtonClicked(position)
        }
    }

    override fun getItemCount(): Int {
        return this.list.size
    }

}