package com.mykotlinapplication.niit_content.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mykotlinapplication.niit_content.R
import com.mykotlinapplication.niit_content.models.CourseData
import kotlinx.android.synthetic.main.fragment_content.*

/**
 * Created by sandeep.singh on 7/3/2017.
 */
class ContentFragment : Fragment() {

    companion object {
        val DATA = "contentFragment"
        fun newInstance(): ContentFragment {
            return ContentFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val root = inflater!!.inflate(R.layout.fragment_content, container, false)
        return root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null) {

            val courseData: CourseData = arguments.getParcelable(ContentFragment.DATA)
            textContent.text = courseData.description
        }
    }
}