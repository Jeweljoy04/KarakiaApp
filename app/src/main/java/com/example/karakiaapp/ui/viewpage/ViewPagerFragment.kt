package com.example.karakiaapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.karakiaapp.R
import com.example.karakiaapp.data.KarakiaItem

class ViewPagerFragment( exampleList: ArrayList<KarakiaItem>): Fragment() {

    private var position: Int = 0
    private var exampleList= ArrayList<KarakiaItem>()
    constructor(position: Int,exampleList :ArrayList<KarakiaItem> ) : this(exampleList) {
        this.position = position
        this.exampleList = exampleList
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_view_page, container, false)
        //Assign Viewpage text
        val textDescription: TextView = root.findViewById(R.id.text_description)
        val currentItem = exampleList[0]
        val description = when (position + 1) {
            2 -> currentItem.inMaori
            3 -> currentItem.inEnglish
            else -> currentItem.description
        }
        textDescription.text = description
        return root
    }
}