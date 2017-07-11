package com.mykotlinapplication.niit_content.ui.fragments

import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.mykotlinapplication.niit_content.R
import kotlinx.android.synthetic.main.fragment_media.*


/**
 * Created by sandeep.singh on 7/3/2017.
 */
class MediaFragment : Fragment() {


    companion object {

        val DATA = "mediaData"
        private var player: SimpleExoPlayer?=null
        fun newInstance(): MediaFragment {
            return MediaFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val root = inflater!!.inflate(R.layout.fragment_media, container, false)

        return root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null) {

            val mediaFileURL= arguments.getString(MediaFragment.DATA)
            println("media File URL== "+mediaFileURL)

           /* var media: CourseMedia = arguments.getParcelable(MediaFragment.DATA)

            val mediaFileURL = FileUtilis.getFilePath(media.name)

            println("media URL="+mediaFileURL)
            if (media.type.equals("image",true)) {
                imageCourse.visibility=View.VISIBLE
                Glide.with(this)
                        .load(mediaFileURL)
                        .placeholder(R.mipmap.ic_launcher)
                        .into(imageCourse!!);
            }
            else{*/

                // Measures bandwidth during playback. Can be null if not required.
                val bandwidthMeter = DefaultBandwidthMeter()
                val videoTrackSelectionFactory = AdaptiveTrackSelection.Factory(bandwidthMeter)
                val trackSelector = DefaultTrackSelector(videoTrackSelectionFactory)

// 2. Create the player
                 player = ExoPlayerFactory.newSimpleInstance(context, trackSelector)


// Produces DataSource instances through which media data is loaded.
                val dataSourceFactory = DefaultDataSourceFactory(context,
                        "NIITContent", bandwidthMeter)
// Produces Extractor instances for parsing the media data.
                val extractorsFactory = DefaultExtractorsFactory()
// This is the MediaSource representing the media to be played.
                val mp4VideoUri= Uri.parse(mediaFileURL)
                val videoSource = ExtractorMediaSource(mp4VideoUri,
                        dataSourceFactory, extractorsFactory, null, null)
// Prepare the player with the source.
                player!!.prepare(videoSource)
                player!!.playWhenReady=true

                playerView.player=player
            //}

        }
    }


    override fun onPause() {
        super.onPause()
        player!!.playWhenReady=false

    }

    override fun onDestroy() {
        super.onDestroy()
        player!!.release()
    }

    override fun onDetach() {
        super.onDetach()
        player!!.release()
    }
}