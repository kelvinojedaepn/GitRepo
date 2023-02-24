package data.dao

import data.entities.Device

interface DeviceDAO: GenericDAO<Device, Int> {

    fun getDevices(): ArrayList<Device>
    fun getDevicesFromCategory(category: String): ArrayList<Device>
    fun getDevicesFromMaximumPrice(maximumPrice: Double): ArrayList<Device>

}