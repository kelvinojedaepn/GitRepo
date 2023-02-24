package data.dao

import data.entities.Component

interface ComponentDAO: GenericDAO<Component, Int> {

    fun getComponents(): ArrayList<Component>
    fun getComponentsFromDeviceCode(deviceCode: Int): ArrayList<Component>

}