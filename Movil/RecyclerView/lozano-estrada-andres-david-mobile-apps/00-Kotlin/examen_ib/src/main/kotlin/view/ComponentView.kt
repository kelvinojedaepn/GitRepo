package view

import data.dao.DAOFactory
import data.entities.Component

class ComponentView {

    fun getComponentOperationSelection(): String {
        print(
            "\nSelect the operation you want to do:\n" +
            "1. Insert a new component (CREATE).\n" +
            "2. Read component by code (READ).\n" +
            "3. Update component by code (UPDATE).\n" +
            "4. Delete component by code (DELETE).\n" +
            "5. Get all the components.\n" +
            "6. Get components by device code.\n" +
            "0. Return to the initial menu.\n\n" +
            "Enter your option: "
        )

        return readLine()!!
    }

    fun processComponentOperationSelection(operation: String): Boolean {
        when(operation) {
            "1" -> processComponentInsertion()
            "2" -> processComponentReading()
            "3" -> processComponentUpdate()
            "4" -> processComponentDeletion()
            "5" -> processComponentCompleteReading()
            "6" -> processComponentReadingByDeviceCode()
            "0" -> return false
            else -> {
                println("The option entered does not exist.")
            }
        }

        return true
    }

    private fun processComponentInsertion() {
        val componentCode: Int?
        val componentContinuity: Boolean?
        val deviceCode: Int?
        val component: Component?

        println("\n===   COMPONENT INSERTION   ===\n")
        print("Enter the component's code: ")
        componentCode = readLine()!!.toInt()

        print("Enter the component's name: ")
        val componentName: String? = readLine()!!

        print("Enter the component's description: ")
        val componentDescription: String? = readLine()!!

        print("Enter the component's continuity: ")
        componentContinuity = readLine()!!.toBoolean()

        print("Enter the device's code of the component: ")
        deviceCode = readLine()!!.toInt()

        component = Component(componentCode, componentName!!, componentDescription!!, componentContinuity, deviceCode)

        DAOFactory.factory.getComponentDAO().create(component)

        println("\nA new component was inserted successfully.")
    }

    private fun processComponentReading() {
        val componentCode: Int?

        println("\n===   COMPONENT READING   ===\n")
        print("Enter the component's code you want to read: ")
        componentCode = readLine()!!.toInt()

        println("\nCODE\tNAME\t\t\t\t\t\t   DESCRIPTION\t\t\t\t\t\t\t\tDISCONTINUED\t\tDEVICE CODE")
        println(DAOFactory.factory.getComponentDAO().read(componentCode))
    }

    private fun processComponentUpdate() {
        val componentCode: Int?
        val componentContinuity: Boolean?
        val component: Component?

        println("\n===   COMPONENT UPDATE   ===\n")
        print("Enter the component's code you want to update: ")
        componentCode = readLine()!!.toInt()

        print("Enter the new component's continuity: ")
        componentContinuity = readLine()!!.toBoolean()

        component = DAOFactory.factory.getComponentDAO().read(componentCode)
        component.setDiscontinued(componentContinuity)

        DAOFactory.factory.getComponentDAO().update(component)

        println("\nThe component was updated successfully.")
    }

    private fun processComponentDeletion() {
        val componentCode: Int?

        println("\n===   COMPONENT DELETION   ===\n")
        print("Enter the component's code you want to delete: ")
        componentCode = readLine()!!.toInt()

        DAOFactory.factory.getComponentDAO().delete(componentCode)

        println("\nThe component was deleted successfully.")
    }

    private fun processComponentCompleteReading() {
        println("\n===   COMPONENTS COMPLETE READING   ===")

        printTable(DAOFactory.factory.getComponentDAO().getComponents())
    }

    private fun processComponentReadingByDeviceCode() {
        println("\n===   COMPONENTS READING BY DEVICE CODE   ===\n")
        print("Enter the device's code of the components you want to read: ")
        val deviceCode = readLine()!!.toInt()

        printTable(DAOFactory.factory.getComponentDAO().getComponentsFromDeviceCode(deviceCode))
    }

    private fun printTable(componentsRows: ArrayList<out Any>) {
        println("\nCODE\tNAME\t\t\t\t\t\t   DESCRIPTION\t\t\t\t\t\t\t\tDISCONTINUED\t\tDEVICE CODE")
        componentsRows.forEach{
                current: Any -> println(current)
        }
    }

}