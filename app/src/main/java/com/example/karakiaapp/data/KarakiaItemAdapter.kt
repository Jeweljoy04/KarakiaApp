package com.example.karakiaapp.data


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.karakiaapp.R
import kotlinx.android.synthetic.main.home_template.view.*

class KarakiaItemAdapter(val exampleList: ArrayList<KarakiaItem>):
    RecyclerView.Adapter<KarakiaItemAdapter.KarakiaItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KarakiaItemViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.home_template, parent, false)
        return KarakiaItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: KarakiaItemViewHolder, position: Int) {
        val currentItem = exampleList[position]
        holder.imageView.setImageResource(currentItem.imageUrl)
        holder.textView1.text = currentItem.itemName
        holder.textView2.text = currentItem.shortName
        holder.textView3.text = currentItem.duration

        holder.itemView.setOnClickListener ( object : View.OnClickListener {
            override fun onClick(v: View?) {
                val bundle = bundleOf(
                    "id" to currentItem.itemId,
                    "imageUrl" to currentItem.imageUrl,
                    "shortName" to currentItem.shortName,
                    "itemName" to currentItem.itemName,
                    "description" to currentItem.description,
                    "inMaori" to currentItem.inMaori,
                    "inEnglish" to currentItem.inEnglish,
                    "duration" to currentItem.duration)

                Navigation.findNavController(holder.itemView)
                    .navigate(R.id.action_nav_home_to_nowPlayingFragment, bundle)
            }
        })
    }

    override fun getItemCount() = exampleList.size

    inner class KarakiaItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageView: ImageView = itemView.image_view
        val textView1: TextView = itemView.text_view_1
        val textView2: TextView = itemView.text_view_2
        val textView3: TextView = itemView.text_view_3
    }

}