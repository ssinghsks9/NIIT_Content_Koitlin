package com.mykotlinapplication.niit_content.ui.activities

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.graphics.Palette
import android.view.View
import com.bumptech.glide.Glide
import com.mykotlinapplication.niit_content.R
import com.mykotlinapplication.niit_content.adapters.CourseContentAdapter
import com.mykotlinapplication.niit_content.ui.animations.DepthPageTransformer
import com.mykotlinapplication.niit_content.utils.FileUtilis
import kotlinx.android.synthetic.main.activity_course_detail.*

class CourseDetailActivity : K_BaseActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_detail)

        val courseName = intent.getStringExtra(INTENT_COURSE_NAME) ?: throw IllegalStateException(" field $INTENT_COURSE_NAME is missing in intent ")


        //val courseContent = FileUtilis.fileContent(courseName, CourseContent::class.java)
        val courseContent = FileUtilis.courseDataFromJsonFile(courseName)

        //println(" value of directory size= "+filedat?.toString())
        for (courseData in courseContent.dataList) {
            println("courseData " + courseData.description)
        }


        try {

        } catch (ex: Exception) {

        }


        val pagerAdapter: CourseContentAdapter = CourseContentAdapter(supportFragmentManager, courseContent.dataList)

        viewPagerContent.adapter = pagerAdapter

        // htab_tabs.setupWithViewPager(viewPagerContent)

        viewPagerContent.setPageTransformer(true, DepthPageTransformer())
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        //supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.title = ""

        appbar.addOnOffsetChangedListener({ appBarLayout, verticalOffset ->

            if (appBarLayout.totalScrollRange + verticalOffset >330)
                collapsing_toolbar.title = " "//carefull there should a space between double quote otherwise it wont work
            else
                collapsing_toolbar.title = resources.getString(R.string.app_name)
        })


        val bitmap = BitmapFactory.decodeResource(resources,
                R.mipmap.ic_launcher_round)

        try {


            Palette.from(bitmap).generate { palette ->
                collapsing_toolbar.setContentScrimColor(palette.getMutedColor(resources.getColor(R.color.colorPrimary, theme)))
                collapsing_toolbar.setStatusBarScrimColor(palette.getMutedColor(resources.getColor(R.color.colorPrimaryDark, theme)))

            }
        } catch (exception: Exception) {
            collapsing_toolbar.setContentScrimColor(resources.getColor(R.color.colorPrimary, theme))
            collapsing_toolbar.setStatusBarScrimColor(resources.getColor(R.color.colorPrimaryDark, theme))
        }




        mediaFileURL = FileUtilis.getFilePath(courseContent.media.name)
        if (courseContent.media.type.equals("image", true)) {
            imagePlayVideo.visibility = View.GONE
            imagePlayVideo.setOnClickListener(null)
            banner.setOnClickListener(null)
        } else {
            imagePlayVideo.visibility = View.VISIBLE
            imagePlayVideo.setOnClickListener(this)
            banner.setOnClickListener(this)
        }
        Glide.with(this)
                //.load(Uri.fromFile(File(mediaFileURL)))
                .load(mediaFileURL)
                .placeholder(R.mipmap.ic_launcher_round)
                .into(banner!!)


    }

    companion object {
        private val INTENT_COURSE_NAME = "course_Name"
        var mediaFileURL: String? = null
        fun newIntent(context: Context, courseName: String): Intent {
            val intent = Intent(context, CourseDetailActivity::class.java)
            intent.putExtra(INTENT_COURSE_NAME, courseName)
            return intent
        }

    }

    override fun onClick(p0: View?) {
        val couserDetail = MediaPlayActivity.newIntent(this, mediaFileURL!!.toString())
        startActivity(couserDetail)
    }
}
