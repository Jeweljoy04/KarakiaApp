package com.example.karakiaapp.data

import android.graphics.ColorSpace
import android.os.Handler
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.karakiaapp.R


class KarakiViewModel : ViewModel() {
    private var karakilist: MutableLiveData<List<KarakiaItem>>? = null
    internal fun getFruitList(): MutableLiveData<List<KarakiaItem>> {
        if (karakilist == null) {
            karakilist = MutableLiveData()
            loadKarakiList()
        }
        return karakilist as MutableLiveData<List<KarakiaItem>>
    }
    private fun loadKarakiList() {
        // do async operation to fetch users
        val myHandler = Handler()
        myHandler.postDelayed({
            val karakiStringList = ArrayList<KarakiaItem>()
            karakiStringList.add(KarakiaItem(1,"Karakia Timatanga","Opening",R.drawable.image1,"","","",R.raw.karakia1,"0.22"))
            karakiStringList.add(KarakiaItem(2,"Karakia Timatanga","Opening 2",R.drawable.image2,"","","",R.raw.karakia1,"0.24"))
            karakiStringList.add(KarakiaItem(3,"Karakia ki te kai","Blessing for food",R.drawable.image3,"","","",R.raw.karakia1,"0.27"))
            karakiStringList.add(KarakiaItem(4,"Karakia Whakamutunga","Closing",R.drawable.image4,"","","",R.raw.karakia1,"0.22"))
            karakiStringList.add(KarakiaItem(5,"Karakia Whakamutunga","Closing 2",R.drawable.image5,"","","",R.raw.karakia1,"0.16"))
            val seed = System.nanoTime()
            karakilist!!.postValue(karakiStringList)//for background postValue

        }, 5000)

    }
}