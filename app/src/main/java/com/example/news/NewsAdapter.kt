package com.example.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.news.view.*

@Suppress("DEPRECATION")
class NewsAdapter(private val listener: NewsClicked): RecyclerView.Adapter<NewsViewHolder>() {

    private val items:ArrayList<newsData> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.news,parent,false)
        val viewHolder=NewsViewHolder(view)

        view.setOnClickListener(){
            listener.onItemClicked(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem=items[position]
        holder.titleView.text=currentItem.title
        //holder.author.text=currentItem.author
        Glide.with(holder.itemView.context).load(currentItem.urlToImage).into(holder.image)
        holder.publish.text=currentItem.publishedAt
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun update(updateNews:ArrayList<newsData>){
        items.clear()
        items.addAll(updateNews)
        notifyDataSetChanged()
    }
}

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val titleView=itemView.title
    val image=itemView.image
    //val author=itemView.author
    val publish=itemView.publishDate
}

interface NewsClicked{
    fun onItemClicked(item:newsData)
}