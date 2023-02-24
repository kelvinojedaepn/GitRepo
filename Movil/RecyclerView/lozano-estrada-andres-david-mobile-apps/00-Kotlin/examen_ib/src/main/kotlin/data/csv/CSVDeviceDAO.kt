package data.csv

import data.dao.DeviceDAO
import data.entities.Device
import java.io.File
import java.io.FileOutputStream
import java.time.LocalDate

class CSVDeviceDAO: DeviceDAO {

    private val filePath: String = "src/main/resources/device.csv"

    private fun getDevicesFromFile(): List<Device> {
        val fileReader = File(filePath).inputStream().bufferedReader()
        fileReader.readLine()  // reading the header of the CSV file

        return ArrayList(
            fileReader.lineSequence()
                .filter {
                    it.isNotBlank() // processing only lines that are not in blank
                }
                .map {
                    // splitting values of each line
                    val (code, name, device_category, date, price) = it.split(',')
                    val releaseDate = LocalDate.parse(date)

                    // creating a new object with the split values
                    Device(code.toInt(), name, device_category, releaseDate, price.toDouble())
                }
                .toMutableList()
        )
    }

    private fun setDevicesToFile(devices: ArrayList<Device>) {
        FileOutputStream(filePath).apply {
            val fileWriter = bufferedWriter()

            fileWriter.write("code,name,category,release_date,price")   // writing the header of the CSV file
            fileWriter.newLine()

            devices.forEach {
                fileWriter.write(
                    "${it.getCode()},${it.getName()},${it.getCategory()},${it.getReleaseDate()},${it.getPrice()}"
                )
                fileWriter.newLine()
            }

            fileWriter.flush()
        }
    }

    override fun getDevices(): ArrayList<Device> {
        return ArrayList(getDevicesFromFile())
    }

    override fun getDevicesFromCategory(category: String): ArrayList<Device> {
        return ArrayList(
            getDevicesFromFile()
                .filter {
                    it.getCategory().equals(category, true)
                }
        )
    }

    override fun getDevicesFromMaximumPrice(maximumPrice: Double): ArrayList<Device> {
        return ArrayList(
            getDevicesFromFile()
                .filter {
                    maximumPrice >= it.getPrice()
                }
        )
    }

    override fun create(entity: Device) {
        setDevicesToFile(ArrayList(getDevicesFromFile() + entity))
    }

    override fun read(code: Int): Device {
        return ArrayList(
            getDevicesFromFile()
                .filter {
                    code == it.getCode()
                }
        )[0]
    }

    override fun update(entity: Device) {
        val currentDevices = ArrayList(getDevicesFromFile())
        var counter = 0

        currentDevices.forEach {
            if (it.getCode() == entity.getCode()) {
                currentDevices[counter] = entity
            }

            counter++
        }

        setDevicesToFile(currentDevices)
    }

    override fun delete(code: Int) {
        val currentDevices = ArrayList(getDevicesFromFile())
        var foundDevice: Device? = null
        var counter = 0

        currentDevices.forEach {
            if (it.getCode() == code) {
                foundDevice = currentDevices[counter]
            }

            counter++
        }

        currentDevices.remove(foundDevice)
        setDevicesToFile(currentDevices)
    }

}