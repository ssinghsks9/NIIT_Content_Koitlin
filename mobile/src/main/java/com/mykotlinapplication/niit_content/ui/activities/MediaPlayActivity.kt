package com.mykotlinapplication.niit_content.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.mykotlinapplication.niit_content.R
import com.mykotlinapplication.niit_content.ui.fragments.MediaFragment
import com.mykotlinapplication.niit_content.utils.ActivityUtils


/**
 * Created by sandeep.singh on 7/5/2017.
 */
class MediaPlayActivity : K_BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_media)
        val mediaURL = intent.getStringExtra(MediaPlayActivity.INTENT_MEDIA_URL) ?: throw IllegalStateException(" field ${MediaPlayActivity.INTENT_MEDIA_URL} is missing in intent ")

        var mediaFragment: MediaFragment? = supportFragmentManager.findFragmentById(R.id.mediaPanel) as? MediaFragment

        if (mediaFragment == null) {


            val bundle = Bundle()
            bundle.putString(MediaFragment.DATA, mediaURL)
            mediaFragment = MediaFragment.newInstance()
            mediaFragment.arguments = bundle


            ActivityUtils.addFragmentToActivity(supportFragmentManager, mediaFragment, R.id.mediaPanel)


        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        }
    }

    companion object {
        private val INTENT_MEDIA_URL = "mediaURL"

        fun newIntent(context: Context, mediaUrl: String): Intent {
            val intent = Intent(context, MediaPlayActivity::class.java)
            intent.putExtra(INTENT_MEDIA_URL, mediaUrl)
            return intent
        }

    }
}