package com.samapps.skelet.ui.adapters

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.TextView


/**
 * Created by sergey on 2/2/18.
 */
class SearchListAdapter(context: Context, private var list: ArrayList<String>?) : ArrayAdapter<String>(context, android.R.layout.simple_dropdown_item_1line, list) {


    private inner class Holder {


        var Name: TextView? = null


    }


}