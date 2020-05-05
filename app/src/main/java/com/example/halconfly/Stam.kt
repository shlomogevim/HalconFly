package com.example.halconfly

import android.content.Context
import android.util.Log
import com.google.ar.core.AugmentedImageDatabase
import com.google.ar.core.Config
import com.google.ar.core.Session
import com.google.ar.sceneform.ux.ArFragment
import java.io.IOException

class Stam() : ArFragment() {
    override fun getSessionConfiguration(session: Session?): Config {
        val config = super.getSessionConfiguration(session)
        config.focusMode = Config.FocusMode.AUTO
        config.augmentedImageDatabase = createAugmentedImageDatabase(session ?: return config)
        return config
    }

    private fun createAugmentedImageDatabase(session: Session): AugmentedImageDatabase? {
        return try {
            val inputStream = resources.openRawResource(R.raw.my_image_database)
            AugmentedImageDatabase.deserialize(session, inputStream)
        } catch (e: IOException) {
            Log.e("ImageArFragment", "IOException while loading augmented image from storage", e)
            null
        }
    }
}

