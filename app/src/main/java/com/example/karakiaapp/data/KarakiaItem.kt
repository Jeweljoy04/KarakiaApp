package com.example.karakiaapp.data

//data class KarakiaItem(val id : Int, val imageResource: Int, var text1: String, var text2: String,var text3: String)
data class KarakiaItem(var itemId:Int, val itemName: String,val shortName: String, val imageUrl: Int, val description:String, val inMaori:String, val inEnglish:String, val videoLink:Int, val duration:String)