package com.mykotlinapplication.niit_content.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created by sandeep.singh on 6/30/2017.
 */

data class CourseMedia(@SerializedName("type") val type: String, @SerializedName("name") val name: String, @SerializedName("coverImage") val coverImage: String, @SerializedName("courseName") val courseName: String) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(type)
        parcel.writeString(name)
        parcel.writeString(coverImage)
        parcel.writeString(courseName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CourseMedia> {
        override fun createFromParcel(parcel: Parcel): CourseMedia {
            return CourseMedia(parcel)
        }

        override fun newArray(size: Int): Array<CourseMedia?> {
            return arrayOfNulls(size)
        }
    }
}

data class CourseData(@SerializedName("tabTitle") val tabTitle: String, @SerializedName("title") val title: String, @SerializedName("description") val description: String) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(tabTitle)
        parcel.writeString(title)
        parcel.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CourseData> {
        override fun createFromParcel(parcel: Parcel): CourseData {
            return CourseData(parcel)
        }

        override fun newArray(size: Int): Array<CourseData?> {
            return arrayOfNulls(size)
        }
    }
}

data class CourseContent(@SerializedName("media") val media: CourseMedia, @SerializedName("data") val dataList: ArrayList<CourseData>) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readParcelable(CourseMedia::class.java.classLoader),
            parcel.createTypedArrayList(CourseData.CREATOR))

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(media, flags)
        parcel.writeTypedList(dataList)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CourseContent> {
        override fun createFromParcel(parcel: Parcel): CourseContent {
            return CourseContent(parcel)
        }

        override fun newArray(size: Int): Array<CourseContent?> {
            return arrayOfNulls(size)
        }
    }
}



