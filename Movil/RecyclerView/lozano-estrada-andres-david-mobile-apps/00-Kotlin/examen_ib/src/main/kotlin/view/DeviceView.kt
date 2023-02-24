package view

import data.dao.DAOFactory
import data.entities.Device
import java.time.LocalDate

class DeviceView {

    fun getDeviceOperationSelection(): String {
        print(
            "\nSelect the operation you want to do:\n" +
                    "1. Insert a new device (CREATE).\n" +
                    "2. Read device by code (READ).\n" +
                    "3. Update device by code (UPDATE).\n" +
                    "4. Delete device by code (DELETE).\n" +
                    "5. Get all the devices.\n" +
                    "6. Get devices by category.\n" +
                    "7. Get devices by maximum price.\n" +
                    "0. Return to the initial menu.\n\n" +
                    "Enter your option: "
        )

        return readLine()!!
    }

    fun processDeviceOperationSelection(operation: String): Boolean {
        when(operation) {
            "1" -> processDeviceInsertion()
            "2" -> processDeviceReading()
            "3" -> processDeviceUpdate()
            "4" -> processDeviceDeletion()
            "5" -> processDevicesCompleteReading()
            "6" -> processDevicesReadingByCategory()
            "7" -> processDevicesReadingByMaximumPrice()
            "0" -> return false
            else -> {
                println("The option entered does not exist.")
            }
        }

        return true
    }

    private fun processDeviceInsertion() {
        val deviceCode: Int?
        val devicePrice: Double?
        val device: Device?

        println("\n===   DEVICE INSERTION   ===\n")
        print("Enter the device's code: ")
        deviceCode = readLine()!!.toInt()

        print("Enter the device's name: ")
        val deviceName: String? = readLine()!!

        print("Enter the device's category: ")
        val deviceCategory: String? = readLine()!!

        print("Enter the device's release date: ")
        val deviceReleaseDate: LocalDate? = LocalDate.parse(readLine()!!)

        print("Enter the device's price: ")
        devicePrice = readLine()!!.toDouble()

        device = Device(deviceCode, deviceName!!, deviceCategory!!, deviceReleaseDate!!, devicePrice)

        DAOFactory.factory.getDeviceDAO().create(device)

        println("\nA new device was inserted successfully.")
    }

    private fun processDeviceReading() {
        val deviceCode: Int?

        println("\n===   DEVICE READING   ===\n")
        print("Enter the device's code you want to read: ")
        deviceCode = readLine()!!.toInt()

        println("\nCODE\tNAME\t\t\t\t\t\t   CATEGORY\t\t\t\tRELEASE DATE\tPRICE")
        println(DAOFactory.factory.getDeviceDAO().read(deviceCode))
    }

    private fun processDeviceUpdate() {
        val deviceCode: Int?
        val devicePrice: Double?
        val device: Device?

        println("\n===   DEVICE UPDATE   ===\n")
        print("Enter the device's code you want to update: ")
        deviceCode = readLine()!!.toInt()

        print("Enter the new device's price: ")
        devicePrice = readLine()!!.toDouble()

        device = DAOFactory.factory.getDeviceDAO().read(deviceCode)
        device.setPrice(devicePrice)

        DAOFactory.factory.getDeviceDAO().update(device)

        println("\nThe device was updated successfully.")
    }

    private fun processDeviceDeletion() {
        val deviceCode: Int?

        println("\n===   DEVICE DELETION   ===\n")
        print("Enter the device's code you want to delete: ")
        deviceCode = readLine()!!.toInt()

        DAOFactory.factory.getDeviceDAO().delete(deviceCode)

        println("\nThe device was deleted successfully.")
    }

    private fun processDevicesCompleteReading() {
        println("\n===   DEVICES COMPLETE READING   ===")

        printTable(DAOFactory.factory.getDeviceDAO().getDevices())
    }

    private fun processDevicesReadingByCategory() {
        println("\n===   DEVICES READING BY CATEGORY   ===\n")
        print("Enter the devices' category you want to read: ")
        val devicesCategory = readLine()!!

        printTable(DAOFactory.factory.getDeviceDAO().getDevicesFromCategory(devicesCategory))
    }

    private fun processDevicesReadingByMaximumPrice() {
        println("\n===   DEVICES READING BY MAXIMUM PRICE   ===\n")
        print("Enter the devices' maximum price you want to read: ")
        val devicesMaximumPrice = readLine()!!.toDouble()

        printTable(DAOFactory.factory.getDeviceDAO().getDevicesFromMaximumPrice(devicesMaximumPrice))
    }

    private fun printTable(devicesRows: ArrayList<out Any>) {
        println("\nCODE\tNAME\t\t\t\t\t\t   CATEGORY\t\t\t\tRELEASE DATE\tPRICE")
        devicesRows.forEach{
                current: Any -> println(current)
        }
    }
}