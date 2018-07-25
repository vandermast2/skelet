package com.samapps.skelet.ui.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.samapps.skelet.R
import com.samapps.skelet.dataFlow.models.apiModels.AlertsModel
import com.samapps.skelet.utils.extentions.onClick


/**
 * Created by sergey on 10/9/17.
 */

class AlertAdapter(private val newsModels: List<AlertsModel>, val mainViewModel: MainActivityVM, private val listener: OnSettingsClickListener) : RecyclerView.Adapter<AlertAdapter.ViewHolder>() {
    val type: Int = 1
    val footer: Int = 2
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return NormalHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.setting_card, parent, false))
    }

    override fun getItemCount(): Int = newsModels.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        try {
            when (holder) {
                is NormalHolder -> holder.bind(newsModels[position], listener)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    class NormalHolder(itemView: View?) : ViewHolder(itemView) {
        val trash: ImageView = itemView!!.imgTrash
        var imgSettings = itemView!!.iClick
        val titile: TextView = itemView!!.txtTitle
        fun bind(newsModel: AlertsModel, listener: OnSettingsClickListener) {
            titile.text = newsModel.productName
            trash.onClick { titile.context.startActivity(Intent(titile.context, AlertDelActivity::class.java).putExtra("productId", newsModel.productId)) }
            trash.onClick { listener.onClick(newsModel.productId!!, "trash") }
            if (newsModel.isUnderlying!!) {
                imgSettings.onClick { titile.context.startActivity(Intent(titile.context, AlertUnderlyingActivity::class.java).putExtra("productId", newsModel.productId?.toInt())) }
            } else {
                imgSettings.onClick { titile.context.startActivity(Intent(titile.context, AlertWarrantsActivity::class.java).putExtra("productId", newsModel.productId?.toInt())) }
            }

        }

    }

    open class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        open fun bind(newsModel: AlertsModel) {

        }
    }

    interface OnSettingsClickListener {
        fun onClick(number: String, type: String)
    }
}