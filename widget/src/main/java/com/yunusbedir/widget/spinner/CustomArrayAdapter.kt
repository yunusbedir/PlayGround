package com.yunusbedir.widget.spinner

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Filter
import com.yunusbedir.widget.R
import java.util.*

class CustomArrayAdapter(context: Context, resource: Int, list: ArrayList<String>) :
    ArrayAdapter<String>(
        context,
        R.layout.custom_item_view_spinner,
        R.id.custom_layout_textview,
        list
    ) {
    private val originalList: ArrayList<String> = ArrayList()
    private var customFilter: CustomFilter? = null

    init {
        originalList.addAll(list)
    }

    override fun getFilter(): Filter {
        if (customFilter == null) {
            customFilter = CustomFilter()
        }
        return customFilter!!
    }

    fun setList(list: List<String>) {
        clear()
        originalList.clear()
        originalList.addAll(list)
        addAll(list)
        filter.filter("")
    }

    fun getList() = originalList

    inner class CustomFilter : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val results = FilterResults()
            val filteredList = ArrayList<String>()
            if (constraint == null || constraint.isEmpty() || constraint.isBlank()) {
                filteredList.addAll(originalList)
            } else {
                val pattern = constraint.toString().toLowerCase(Locale.getDefault()).trim()
                originalList.forEach {
                    if (it.toLowerCase(Locale.getDefault()).contains(pattern)) {
                        filteredList.add(it)
                    }
                }
            }
            results.values = filteredList
            results.count = filteredList.size
            return results
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            results?.let {
                clear()
                if (it.values != null && it.values is ArrayList<*>) {
                    val temp = (it.values as ArrayList<String?>).filterNotNull()
                    addAll(temp)
                }
                notifyDataSetChanged()
            }
        }
    }
}