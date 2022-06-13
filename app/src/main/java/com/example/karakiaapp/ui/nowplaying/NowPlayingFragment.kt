package com.example.karakiaapp.ui.nowplaying

import android.media.MediaPlayer
import android.media.MediaPlayer.OnPreparedListener
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.SeekBar
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.karakiaapp.R
import kotlinx.android.synthetic.main.fragment_now_playing.*
import kotlinx.android.synthetic.main.fragment_now_playing.view.*


class NowPlayingFragment: Fragment() {

    lateinit var runnable: Runnable
    private var handler = Handler(Looper.getMainLooper())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.fragment_now_playing, container, false)
        val id = arguments?.getInt("id")
        val itemName = arguments?.getString("itemName").toString()
        val shortName = arguments?.getString("shortName").toString()
        val imageSec = arguments?.getInt("imageUrl")
        val description = arguments?.getString("description").toString()
        val inMaori = arguments?.getString("inMaori").toString()
        val inEnglish = arguments?.getString("inEnglish").toString()
        val duration = arguments?.getString("duration").toString()

        var imageUrl = 0
        if (imageSec != null)
            imageUrl = imageSec

        root?.text_header?.text = itemName
        root?.text_dec?.text = shortName
        root?.text_duration?.text = duration
        root?.image_view?.setImageResource(imageUrl)

          val videoLink = when (id) {
            2 -> R.raw.karakia2
            3 -> R.raw.karakia3
            4 -> R.raw.karakia4
            5 -> R.raw.karakia5
            else -> R.raw.karakia1
        }


        root?.videoView?.setVideoPath("android.resource://" + context?.packageName + "/" + videoLink)
        val mediaController = MediaController(this.context)
        mediaController.setAnchorView(videoView)
        root?.videoView?.setMediaController(mediaController)
        root?.play_btn?.setOnClickListener()
        {
            if (!videoView.isPlaying) {
                videoView.start()
                play_btn.setImageResource(R.drawable.ic_pause);
            } else {
                videoView.pause()
                play_btn.setImageResource(R.drawable.ic_play);
            }
        }

        root?.back_btn?.setOnClickListener()
        {
            var position = videoView.currentPosition
            videoView.seekTo(position - 5000)
        }
        root?.next_btn?.setOnClickListener()
        {
            var position = videoView.currentPosition
            videoView.seekTo(position + 5000)
        }
        root?.stop_btn?.setOnClickListener()
        {
            videoView.seekTo(0)
            videoView.pause()
            root?.seekbar?.progress = 0
            play_btn.setImageResource(R.drawable.ic_play);
        }
        root?.videoView?.start()
        root?.seekbar?.progress = 0
        root?.seekbar?.max = 22000
        root?.seekbar?.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, pos: Int, changed: Boolean) {
                if(changed)
                {
                    videoView.seekTo(pos);
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })

        root?.details_btn?.setOnClickListener {
            val bundle = bundleOf(
            "id" to id,
            "itemName" to itemName,
            "description" to description,
            "inMaori" to inMaori,
            "inEnglish" to inEnglish,
            "duration" to duration)

            Navigation.findNavController(root?.details_btn)
                .navigate(R.id.action_nowPlayingFragment_to_detailsFragment2, bundle)
        }

        runnable = Runnable {
            root?.seekbar?.progress = videoView.currentPosition
            handler.postDelayed(runnable,1000)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}

