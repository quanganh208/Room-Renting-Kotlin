package com.itptit.roomrenting.domain.usecases.app_entry

import com.itptit.roomrenting.domain.manger.LocalUserManger

class SaveAppEntry(
    private val localUserManger: LocalUserManger
) {

    suspend operator fun invoke(){
        localUserManger.saveAppEntry()
    }

}