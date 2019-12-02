package com.enigma.datadistrbuter.modelmapper

import com.enigma.datadistrbuter.model.EntityModel
import com.enigma.usecase.model.UsecaseModel

interface DataMapper<E : EntityModel, U : UsecaseModel> {
    fun mapFromDataToUseCaseModel(entityModel: E): U
    fun mapFromUseCaseModelToData(useCaseModel:  U): E
}