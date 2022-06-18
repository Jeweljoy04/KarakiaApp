package com.example.karakiaapp.ui.nowplaying

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.SeekBar
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.karakiaapp.R
import kotlinx.android.synthetic.main.fragment_now_playing.*
import kotlinx.android.synthetic.main.fragment_now_playing.view.*


class NowPlayingFragment: Fragment() {

    lateinit var runnable: Runnable
    private var handler = Handler(Looper.getMainLooper())
    lateinit var audioManager: AudioManager
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
        root?.image_view?.setImageResource(imageUrl)

          val videoLink = when (id) {
            2 -> R.raw.karakia2
            3 -> R.raw.karakia3
            4 -> R.raw.karakia4
            5 -> R.raw.karakia5
            else -> R.raw.karakia1
        }
        audioManager =  getActivity()?.getSystemService(Context.AUDIO_SERVICE) as AudioManager;
        val currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
        val maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
        root?.seekbar_volume?.max = maxVolume
        root?.seekbar_volume?.progress  = currentVolume

        if(audioManager.isStreamMute(AudioManager.STREAM_MUSIC))
        {
            mute_btn?.setImageResource(R.drawable.ic_music_off);
        }
        else
        {
            mute_btn?.setImageResource(R.drawable.ic_music_on);
        }

        val uri = Uri.parse("android.resource://" + context?.packageName + "/" + videoLink)
        var durationTime: Int
        MediaPlayer.create(this.context, uri).also {
            durationTime = (it.duration).toInt()
            it.reset()
            it.release()
        }

        root?.text_duration?.text  = String.format("%02d:%02d",(durationTime / 1000) / 60,durationTime / 1000)
        root?.text_current?.text  = String.format("%02d:%02d",0,0)


        root?.videoView?.setVideoPath("android.resource://" + context?.packageName + "/" + videoLink)
        //val mediaController = MediaController(this.context)
        //mediaController.setAnchorView(videoView)
        //root?.videoView?.setMediaController(mediaController)

        root?.mute_btn?.setOnClickListener()
        {
            if (audioManager.isStreamMute(AudioManager.STREAM_MUSIC)) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_UNMUTE, 0);
                } else {
                    audioManager.setStreamMute(AudioManager.STREAM_MUSIC, false);
                }
                mute_btn.setImageResource(R.drawable.ic_music_on);
            }
            else
            {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_MUTE, 0);
                } else {
                    audioManager.setStreamMute(AudioManager.STREAM_MUSIC, true);
                }
                mute_btn.setImageResource(R.drawable.ic_music_off);
            }
        }

        root?.play_btn?.setOnClickListener()
        {
            if (!videoView.isPlaying) {
                videoView.start()
                play_btn.setImageResource(R.drawable.ic_pause);
                initialiseSeekBar(durationTime)
            } else {
                videoView.pause()
                play_btn.setImageResource(R.drawable.ic_play);
            }
        }

        root?.back_btn?.setOnClickListener()
        {
            var position = videoView.currentPosition
            videoView.seekTo(position - 5000)
            root?.seekbar?.progress = position - 5000
        }
        root?.next_btn?.setOnClickListener()
        {
            var position = videoView.currentPosition
            videoView.seekTo(position + 5000)
            root?.seekbar?.progress = position + 5000
        }
        root?.stop_btn?.setOnClickListener()
        {
            videoView.seekTo(0)
            videoView.pause()
            root?.seekbar?.progress = 0
            play_btn.setImageResource(R.drawable.ic_play);
        }

        root?.seekbar?.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, pos: Int, changed: Boolean) {
                if(changed)
                {
                    var currentPos = videoView.currentPosition
                    text_current.text  = String.format("%02d:%02d",( currentPos / 1000) / 60,currentPos / 1000)
                    videoView.seekTo(pos);
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })

        root?.seekbar_volume?.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, pos: Int, changed: Boolean) {
                if(changed)
                {
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, pos,0)
                    if(pos == 0)
                    {
                        mute_btn?.setImageResource(R.drawable.ic_music_off);
                    }
                    else
                    {
                        mute_btn?.setImageResource(R.drawable.ic_music_on);
                    }
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
            if(videoView!= null) {
                root?.seekbar?.progress = videoView.currentPosition
                var currentPos = videoView.currentPosition
                text_current.text =
                    String.format("%02d:%02d", (currentPos / 1000) / 60, currentPos / 1000)
                handler.postDelayed(runnable, 100)
            }
        }
        root?.videoView?.setOnCompletionListener {
            root?.play_btn?.setImageResource(R.drawable.ic_play);
            videoView.seekTo(0)
            root?.seekbar?.progress = 0;
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    private fun initialiseSeekBar(durationTime : Int) {
        if (videoView != null) {
            seekbar?.max = durationTime
            val handler = Handler(Looper.getMainLooper())
            handler.postDelayed(object : Runnable {
                override fun run() {
                    try {
                        var currentPos = videoView.currentPosition
                        text_current?.text =
                            String.format("%02d:%02d", (currentPos / 1000) / 60, currentPos / 1000)
                        seekbar?.progress = videoView.currentPosition
                        handler.postDelayed(runnable, 100)

                    } catch (e: Exception) {
                        seekbar?.progress = 0
                    }
                }
            }, 0)

        }
    }

}

