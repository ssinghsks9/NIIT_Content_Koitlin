package com.mykotlinapplication.niit_content.ui.activities

import android.Manifest
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.rxpermission.local.ReactivePermissions
import com.rxpermission.local.entity.Permission


/**
 * Created by sandeep.singh on 6/28/2017.
 */
abstract class K_BaseActivity : AppCompatActivity() {

    // Define a code to request the permissions
    private val REQUEST_CODE = 10
    var permissionGranted = false

    // Instantiate the library
    val reactive: ReactivePermissions = ReactivePermissions(this, REQUEST_CODE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val readStorage = Permission(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                null,
                false // If the user deny this permission, block the app
        )


        val writeStorage = Permission(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                null, // The context is clear and isn't needed explanation for this permission
                true
        )

        // Put all permissions to evaluate in a single array
        val permissions = listOf(readStorage, writeStorage)

        // Subscribe to observe results
        reactive.observeResultPermissions().subscribe { event ->
            if (event.second) {
                //Toast.makeText(this, "${event.first} GRANTED :-)", Toast.LENGTH_SHORT).show()
                permissionGranted = true
            } else {
                Toast.makeText(this, "${event.first} DENIED :-(", Toast.LENGTH_SHORT).show()
            }
        }

        // Call for evaluate the permissions
        reactive.evaluate(permissions)


    }

    // Receive the response from the user and pass to the lib
    override fun onRequestPermissionsResult(code: Int, permissions: Array<String>, results: IntArray) {
        if (code == REQUEST_CODE)
            reactive.receive(permissions, results)
    }

}