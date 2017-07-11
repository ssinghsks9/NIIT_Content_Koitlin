package com.mykotlinapplication.niit_content.adapters

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.mykotlinapplication.niit_content.models.CourseData
import com.mykotlinapplication.niit_content.ui.fragments.ContentFragment

/**
 * Created by sandeep.singh on 7/3/2017.
 */
class CourseContentAdapter(fragmentManager: FragmentManager, var courseDataList: ArrayList<CourseData> ) : FragmentPagerAdapter(fragmentManager){


    override fun getItem(position: Int): Fragment {
       // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
           val contentFragment = ContentFragment.newInstance()
           val argument= Bundle()
            argument.putParcelable(ContentFragment.DATA,courseDataList[position])
            contentFragment.arguments=argument

        return contentFragment
    }

    override fun getCount(): Int {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return courseDataList.size
    }

    override fun getPageTitle(position: Int): CharSequence {
         super.getPageTitle(position)
        return courseDataList[position].tabTitle.toUpperCase()
    }

}