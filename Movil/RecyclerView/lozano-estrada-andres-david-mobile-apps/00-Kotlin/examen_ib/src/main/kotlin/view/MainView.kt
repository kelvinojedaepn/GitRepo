package view

import kotlin.system.exitProcess

class MainView {

    private val deviceView = DeviceView()
    private val componentView = ComponentView()

    fun startUserInterface() {
        printBanner()

        while (true) {
            processEntitySelection(getEntitySelection())
        }
    }

    private fun printBanner() {
        print(
            "==============================\n" +
            "===     CRUD IN KOTLIN     ===\n" +
            "==============================\n"
        )
    }

    private fun getEntitySelection(): String {
        print(
            "\nSelect the entity you want to work with:\n" +
            "1. Device.\n" +
            "2. Component.\n" +
            "0. Exit the program.\n\n" +
            "Enter your option: "
        )

        return readLine()!!
    }

    private fun processEntitySelection(selection: String) {
        when(selection) {
            "1" -> do {
                val loop = deviceView.processDeviceOperationSelection(
                    deviceView.getDeviceOperationSelection()
                )
            } while(loop)
            "2" -> do {
                val loop = componentView.processComponentOperationSelection(
                    componentView.getComponentOperationSelection()
                )
            } while(loop)
            "0" -> exitProcess(0)
            else -> {
                println("The option entered does not exist.")
            }
        }
    }

}