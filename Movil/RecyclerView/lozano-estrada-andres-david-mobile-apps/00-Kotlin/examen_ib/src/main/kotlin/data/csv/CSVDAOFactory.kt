package data.csv

import data.dao.ComponentDAO
import data.dao.DAOFactory
import data.dao.DeviceDAO

class CSVDAOFactor: DAOFactory() {

    override fun getDeviceDAO(): DeviceDAO {
        return CSVDeviceDAO()
    }

    override fun getComponentDAO(): ComponentDAO {
        return CSVComponentDAO()
    }

}