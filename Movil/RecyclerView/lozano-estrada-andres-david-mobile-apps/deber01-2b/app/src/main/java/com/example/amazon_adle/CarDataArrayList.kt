package com.example.amazon_adle

class CarDataArrayList {
    companion object {
        val carProductsList: ArrayList<Product> = arrayListOf()

        init {
            carProductsList.add(
                Product(
                    1,
                    "Lenovo Laptop Ideapad 3 2022, pantalla táctil HD de 15.6 pulgadas, " +
                            "procesador Intel Core I3-1115G4 de 11va generación, 8GB de RAM " +
                            "DDR4, SSD PCIe NVMe de 256 GB, HDMI, cámara web, Wi-Fi 5, " +
                            "Bluetooth, Windows 11 Home, almendra",
                    R.drawable.laptop_1,
                    832,
                    4,
                    385.99,
                    0.35,
                    "computadoras",
                    true)
            )

            carProductsList.add(
                Product(
                    6,
                    "Samsung Galaxy C1 desbloqueado de fábrica, teléfono celular con " +
                            "Android | Versión de EE. UU. | ID de huellas dactilares y " +
                            "reconocimiento facial | Batería de larga duración",
                    R.drawable.phone_1,
                    191,
                    5,
                    298.85,
                    0.25,
                    "celulares",
                    true)
            )

            carProductsList.add(
                Product(
                    9,
                    "Western Digital SSD interna de estado sólido SATA SA510 SATA de 1 TB " +
                            "WD Blue - SATA III 6 Gb/s, 2.5\"/7 mm, hasta 560 MB/s - WDS100T3B0A ",
                    R.drawable.ssd_1,
                    165,
                    4,
                    90.47,
                    0.0,
                    "discos",
                    false)
            )
        }

        private fun getTotalPrice(): Double {
            return carProductsList
                .map {
                    it.price
                }
                .reduce {
                    accumulation, currentIntPrice -> accumulation + currentIntPrice
                }
        }

        fun getIntTotalPrice(): Int {
            return getTotalPrice().toInt()
        }

        fun getDecimalTotalPrice(): Int {
            return (String.format(
                "%.2f", getTotalPrice() - getIntTotalPrice()
            ).toDouble() * 100).toInt()
        }
    }
}