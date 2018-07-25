package com.samapps.skelet.ui.adapters

import android.content.Context
import android.content.Intent
import android.support.constraint.ConstraintLayout
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.juliusbaer.premarket.R
import com.juliusbaer.premarket.models.serverModels.NewsModel
import com.juliusbaer.premarket.ui.activities.DetailNewsActivity
import com.juliusbaer.premarket.utils.onClick
import kotlinx.android.synthetic.main.news_card.view.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by sergey on 10/9/17.
 */

class NewsAdapter(val newsModels: List<NewsModel>, val context: Context): RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.news_card, parent, false))

    override fun getItemCount(): Int = newsModels.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(newsModels.get(position),context )

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        val txtDate: TextView = itemView!!.txtDate
        val txtNews: TextView = itemView!!.txtNews
        val newsCard:ConstraintLayout = itemView!!.newsCard
        fun bind(newsModel: NewsModel, context: Context){
            val sdf = SimpleDateFormat("MMM d",
                    Locale.ENGLISH)
            if (newsModel.isRead) {
                val typeface = ResourcesCompat.getFont(context, R.font.verlag_light)
                txtNews.typeface = typeface
            } else {
                val typeface = ResourcesCompat.getFont(context, R.font.verlag_bold)
                txtNews.typeface = typeface
            }
            txtDate.text = sdf.format(newsModel.publishDate!!*1000L)
            txtNews.text = newsModel.headLine
            newsCard.onClick { context.startActivity(Intent(context, DetailNewsActivity::class.java).putExtra("id", newsModel.id.toString())) }
        }
    }
}