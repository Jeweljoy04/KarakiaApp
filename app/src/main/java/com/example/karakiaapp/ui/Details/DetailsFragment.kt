package com.example.karakiaapp.ui.Details

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.karakiaapp.R
import com.example.karakiaapp.data.KarakiaItem
import com.example.karakiaapp.data.KarakiaItemAdapter
import com.example.karakiaapp.ui.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_now_playing.*
import kotlinx.android.synthetic.main.fragment_now_playing.view.*

class DetailsFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_details, container, false)

        val id = arguments?.getInt("id")
        val itemName = arguments?.getString("itemName").toString()
        val description = arguments?.getString("description").toString()
        val inMaori = arguments?.getString("inMaori").toString()
        val inEnglish = arguments?.getString("inEnglish").toString()
        //Fragment name change
        (activity as AppCompatActivity).supportActionBar?.title = itemName

        //Video player settings
        val videoLink = when (id) {
            2 -> R.raw.karakia2
            3 -> R.raw.karakia3
            4 -> R.raw.karakia4
            5 -> R.raw.karakia5
            else -> R.raw.karakia1
        }
        val videoplayer =  view.findViewById<VideoView>(R.id.video_view)
        val uri = "android.resource://" + activity?.packageName + "/" + videoLink;
        videoplayer.setVideoURI(Uri.parse(uri))
        videoplayer.start()
        val mediaController = MediaController(this.context)
        mediaController.setAnchorView(videoView)
        videoplayer.setMediaController(mediaController)

        val karakiStringList = ArrayList<KarakiaItem>()
        karakiStringList.add(
            KarakiaItem(
                1,
                itemName,
                "",
                0,
                description,
                inMaori,
                inEnglish,
                videoLink,
                "0.00"
            )
        )

        //Tab value setting
        val viewPager = view.findViewById<ViewPager2>(R.id.pagerMain)
        val viewPagerAdapter = activity?.let { ViewPagerAdapter(it,karakiStringList) }
        viewPager.adapter = viewPagerAdapter

        //Tab header setting
        var tabTile = arrayOf("Description","Māori","English")
        val tabLayout = view.findViewById<TabLayout>(R.id.tab_layout)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabTile[position]
        }.attach()
        return view
    }
}