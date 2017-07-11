package com.mykotlinapplication.niit_content.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mykotlinapplication.niit_content.ui.activities.CourseDetailActivity
import com.mykotlinapplication.niit_content.R
import kotlinx.android.synthetic.main.list_course_item.view.*

/**
 * Created by sandeep.singh on 6/30/2017.
 */

class CourseAdapter(var mContext: Context, var mCourseList: ArrayList<String>) : RecyclerView.Adapter<CourseAdapter.CourseItem>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CourseAdapter.CourseItem {
       //To change body of created functions use File | Settings | File Templates.
        val v = LayoutInflater.from(mContext).inflate(R.layout.list_course_item, parent, false)
        v.setOnClickListener(mClickListener)
        return CourseItem(v)
    }

    override fun onBindViewHolder(holder:CourseAdapter.CourseItem?, position: Int) {
        //To change body of created functions use File | Settings | File Templates.
        (holder as CourseItem).bindData(mCourseList[position])
    }

    override fun getItemCount(): Int {

       return mCourseList.size
    }

    class CourseItem(itemView: View): RecyclerView.ViewHolder(itemView){


        fun bindData(_list: String){
            itemView.courseName.text=_list

        }
    }

    private val mClickListener = View.OnClickListener { view ->
        //Toast.makeText(mContext, "toast " + view.courseName.text, Toast.LENGTH_SHORT).show()
            val couserDetail= CourseDetailActivity.newIntent(mContext,view.courseName.text.toString())
            mContext.startActivity(couserDetail)
    }

}