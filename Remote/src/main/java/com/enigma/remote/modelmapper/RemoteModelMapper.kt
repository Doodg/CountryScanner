package com.enigma.remote.modelmapper

import com.enigma.datadistrbuter.model.EntityModel
import com.enigma.remote.model.RemoteModel

interface RemoteModelMapper<M : RemoteModel, E : EntityModel> {

    fun mapFromRemoteToData(remoteModel: M): E
}