package data.csv

import data.dao.ComponentDAO
import data.entities.Component
import java.io.File
import java.io.FileOutputStream

class CSVComponentDAO: ComponentDAO {

    private val filePath: String = "src/main/resources/component.csv"

    private fun getComponentsFromFile(): List<Component> {
        val fileReader = File(filePath).inputStream().bufferedReader()
        fileReader.readLine()  // reading the header of the CSV file

        return ArrayList(
            fileReader.lineSequence()
                .filter {
                    it.isNotBlank() // processing only lines that are not in blank
                }
                .map {
                    // splitting values of each line
                    val (code, name, description, discontinued, device) = it.split(',')

                    // creating a new object with the split values
                    Component(code.toInt(), name, description, discontinued.toBoolean(), device.toInt())
                }
                .toMutableList()
        )
    }

    private fun setComponentsToFile(components: ArrayList<Component>) {
        FileOutputStream(filePath).apply {
            val fileWriter = bufferedWriter()

            // writing the header of the CSV file
            fileWriter.write("code,name,description,discontinued,device_code_fk")
            fileWriter.newLine()

            components.forEach {
                fileWriter.write(
                    "${it.getCode()},${it.getName()},${it.getDescription()}," +
                            "${it.getDiscontinued()},${it.getDeviceCode()}"
                )
                fileWriter.newLine()
            }

            fileWriter.flush()
        }
    }

    override fun getComponents(): ArrayList<Component> {
        return ArrayList(getComponentsFromFile())
    }

    override fun getComponentsFromDeviceCode(deviceCode: Int): ArrayList<Component> {
        return ArrayList(
            getComponentsFromFile()
                .filter {
                    it.getDeviceCode() == deviceCode
                }
        )
    }

    override fun create(entity: Component) {
        setComponentsToFile(ArrayList(getComponentsFromFile() + entity))
    }

    override fun read(code: Int): Component {
        return ArrayList(
            getComponentsFromFile()
                .filter {
                    code == it.getCode()
                }
        )[0]
    }

    override fun update(entity: Component) {
        val currentComponents = ArrayList(getComponentsFromFile())
        var counter = 0

        currentComponents.forEach {
            if (it.getCode() == entity.getCode()) {
                currentComponents[counter] = entity
            }

            counter++
        }

        setComponentsToFile(currentComponents)
    }

    override fun delete(code: Int) {
        val currentComponents = ArrayList(getComponentsFromFile())
        var foundComponent: Component? = null
        var counter = 0

        currentComponents.forEach {
            if (it.getCode() == code) {
                foundComponent = currentComponents[counter]
            }

            counter++
        }

        currentComponents.remove(foundComponent)
        setComponentsToFile(currentComponents)
    }

}