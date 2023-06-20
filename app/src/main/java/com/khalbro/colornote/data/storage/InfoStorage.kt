package com.khalbro.colornote.data.storage

import com.khalbro.colornote.data.storage.models.Info

interface InfoStorage {


    fun saveInfo(info: Info): Boolean



}