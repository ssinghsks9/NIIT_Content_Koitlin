package com.mykotlinapplication.niit_content.utils

import android.content.Context
import android.os.Build
import android.os.Environment
import android.text.TextUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mykotlinapplication.niit_content.models.CourseContent
import java.io.File
import java.io.FileReader
import java.util.*
import java.util.regex.Pattern

/**
 * Created by sandeep.singh on 7/3/2017.
 */
object FileUtilis {

    val baseDir = "NIIT_Content/"
    val mediaDir="mediaDir"

    val fileExt = ".json"
    var dirFile: File? = null

    val gsonObj = Gson()

    fun <T> fileContent(fileName: String, contentType: Class<T>): T {

        val filedata = File(dirFile!!.absolutePath + File.separator + fileName + fileExt).readText(charset = Charsets.UTF_8)

        return gsonObj.fromJson(filedata, contentType)

    }
    fun courseDataFromJsonFile(fileName: String): CourseContent{

        return gsonObj.fromJson<CourseContent>(FileReader(dirFile!!.absolutePath + File.separator + fileName + fileExt),object :TypeToken<CourseContent>(){}.type)
    }

    fun getAppBasePath(): String {
        return (dirFile!!.absolutePath)
    }
    fun getAppMediaDir():String{
        return (dirFile!!.absolutePath+File.separator+ mediaDir)
    }

    fun getFilePath(mediaFileName :String):String{

        return (getAppMediaDir()+File.separator+mediaFileName)
    }

    fun listFiles(directoryName: String): ArrayList<String> {
        val directory = File(directoryName)
        val fileList = ArrayList<String>()
        //get all the files from a directory
        val fList = directory.listFiles()
        for (file in fList.filter { it.isFile }) {

            println(file.name)
            fileList.add(file.name.substring(0, file.name.indexOf(".")))

        }
        return fileList
    }

    fun listFiles(context: Context): ArrayList<String> {
        val directories = getStorageDirectories(context)

        val dir1: File? = File(directories[0] + File.separator + baseDir)
        val dir2: File? = File(directories[1] + File.separator + baseDir)


        dirFile = if (dir1?.isDirectory as Boolean) File(dir1.absolutePath) else File(dir2?.absolutePath)

        //  return listFiles(dirFile!!.absolutePath)
        return listFiles((dirFile as File).absolutePath)
    }

    private val physicalPaths = arrayListOf(
            "/storage/sdcard0", "/storage/sdcard1", // Motorola Xoom
            "/storage/extsdcard", // Samsung SGS3
            "/storage/sdcard0/external_sdcard", // User request
            "/mnt/extsdcard", "/mnt/sdcard/external_sd", // Samsung galaxy family
            "/mnt/external_sd", "/mnt/media_rw/sdcard1", // 4.4.2 on CyanogenMod S3
            "/removable/microsd", // Asus transformer prime
            "/mnt/emmc", "/storage/external_SD", // LG
            "/storage/ext_sd", // HTC One Max
            "/storage/removable/sdcard1", // Sony Xperia Z1
            "/data/sdext", "/data/sdext2", "/data/sdext3", "/data/sdext4", "/sdcard1", // Sony Xperia Z
            "/sdcard2", // HTC One M8s
            "/storage/microsd" // ASUS ZenFone 2
    )

    fun getInternalStoragePath() = Environment.getExternalStorageDirectory().toString().trimEnd('/')

    fun getStorageDirectories(context: Context): Array<String> {
        val paths = HashSet<String>()
        val rawExternalStorage = System.getenv("EXTERNAL_STORAGE")
        val rawSecondaryStoragesStr = System.getenv("SECONDARY_STORAGE")
        val rawEmulatedStorageTarget = System.getenv("EMULATED_STORAGE_TARGET")
        if (TextUtils.isEmpty(rawEmulatedStorageTarget)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                context.getExternalFilesDirs(null).filterNotNull().map { it.absolutePath }
                        .mapTo(paths) { it.substring(0, it.indexOf("Android/data")) }
            } else {
                if (TextUtils.isEmpty(rawExternalStorage)) {
                    paths.addAll(physicalPaths)
                } else {
                    paths.add(rawExternalStorage)
                }
            }
        } else {
            val path = Environment.getExternalStorageDirectory().absolutePath

            val folders = Pattern.compile("/").split(path)
            val lastFolder = folders[folders.size - 1]
            var isDigit = false
            try {
                Integer.valueOf(lastFolder)
                isDigit = true
            } catch (ignored: NumberFormatException) {
            }

            val rawUserId = if (isDigit) lastFolder else ""
            if (TextUtils.isEmpty(rawUserId)) {
                paths.add(rawEmulatedStorageTarget)
            } else {
                paths.add(rawEmulatedStorageTarget + File.separator + rawUserId)
            }
        }

        if (!TextUtils.isEmpty(rawSecondaryStoragesStr)) {
            val rawSecondaryStorages = rawSecondaryStoragesStr.split(File.pathSeparator.toRegex()).dropLastWhile(String::isEmpty).toTypedArray()
            Collections.addAll(paths, *rawSecondaryStorages)
        }
        return paths.toTypedArray()
    }
}