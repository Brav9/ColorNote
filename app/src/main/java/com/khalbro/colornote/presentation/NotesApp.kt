package com.khalbro.colornote.presentation

import android.app.Application
import com.khalbro.colornote.di.dataKoinModule
import com.khalbro.colornote.di.useCaseKoinModel
import com.khalbro.colornote.di.viewModelKoinModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class NotesApp: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@NotesApp)
            androidFileProperties()
            modules(provideModels())
        }
    }

    private fun provideModels() = listOf(useCaseKoinModel, dataKoinModule, viewModelKoinModel)
}