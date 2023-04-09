package com.example.dailynews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnFlingListener
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide

class NewsListAdapter(private val listener: NewsItemClicked) : RecyclerView.Adapter<NewsViewHolder>() {

    private val items: ArrayList<News> = ArrayList()
    //2.this function will be called when view holder will bw created and will return the instance of
    //view holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        //Layout Inflater is a class which convert our item view from XML format to view format
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent,false)
        val viewHolder = NewsViewHolder(view)
        view.setOnClickListener{
            listener.onItemClicked(items[viewHolder.absoluteAdapterPosition])
        }
        return viewHolder
    }
    //3.this function binds data or holder with current item
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = items[position]
        holder.titleView.text = currentItem.title
        holder.authorView.text = currentItem.author
        Glide.with(holder.itemView.context).load(currentItem.imageUrl).into(holder.imageView)
    }
    //1.this function will return the size of total items
    override fun getItemCount(): Int {
        return items.size
    }

    fun updatedNews(updatedNews: ArrayList<News>){
        items.clear()
        items.addAll(updatedNews)

        notifyDataSetChanged()//to notify the adapter that dataset has been changed or updated
        //all the above functions run again
    }
}

//It contains the item in which we have to inflate later or have to put value later
class NewsViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
    val imageView: ImageView = itemView.findViewById(R.id.image)
    val titleView: TextView = itemView.findViewById(R.id.title)
    val authorView: TextView = itemView.findViewById(R.id.author)
}

interface NewsItemClicked{
    fun onItemClicked(item: News)
}