package com.mykotlinapplication.niit_content.ui.activities

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import butterknife.ButterKnife
import com.mykotlinapplication.niit_content.R
import com.mykotlinapplication.niit_content.adapters.CourseAdapter
import com.mykotlinapplication.niit_content.utils.FileUtilis
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : K_BaseActivity() {


    var fileList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ButterKnife.bind(this)
        setContentView(R.layout.activity_main2)
        setSupportActionBar(toolbar)

    }

    override fun onResume() {
        super.onResume()
        if (permissionGranted) {
            readDataFromDirectory()
            // listFiles()
        }
    }

    fun readDataFromDirectory() {

        fileList = FileUtilis.listFiles(this)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.hasFixedSize()
        recyclerView.adapter = CourseAdapter(this, fileList)
        recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

    }

}
