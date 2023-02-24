package com.example.amazon_adle

class ProductsDataArrayList {
    companion object {
        val productsList: ArrayList<Product> = arrayListOf()

        init {
            productsList.add(
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

            productsList.add(
                Product(
                    2,
                    "Laptop para juegos MSI GF63 de alta calidad, pantalla FHD de bisel " +
                            "delgado de 15.6 pulgadas, Intel Quad-Core i5-10300H, 16 GB de RAM, " +
                            "1 TB SSD, GeForce GTX 1650 4GB, teclado retroiluminado, Windows 10 ",
                    R.drawable.laptop_2,
                    91,
                    3,
                    673.23,
                    0.0,
                    "computadoras",
                    false)
            )

            productsList.add(
                Product(
                    3,
                    "Lenovo - Legion 5 - Laptop para juegos - AMD Ryzen 5 5600H - 8GB " +
                            "DDR4 RAM - 512GB NVMe TLC SSD - NVIDIA GeForce RTX 3060 Graphics " +
                            "- 15.6\" FHD 120 Hz - Windows 11 Home - Azul fantasma ",
                    R.drawable.laptop_3,
                    514,
                    4,
                    899.99,
                    0.1,
                    "computadoras",
                    true)
            )

            productsList.add(
                Product(
                    4,
                    "Lenovo - 2022 - IdeaPad Flex 5i - Computadora portátil Chromebook " +
                            "2 en 1 - Intel Core i3-1115G4 - Pantalla táctil FHD de 13.3 " +
                            "pulgadas - Memoria de 8 GB - Almacenamiento de 128 GB - Chrome OS",
                    R.drawable.laptop_4,
                    1328,
                    5,
                    299.99,
                    0.15,
                    "computadoras",
                    true)
            )

            productsList.add(
                Product(
                    5,
                    "HP 2022 - Portátiles con pantalla táctil para estudiantes " +
                            "universitarios y negocios, computadora HD de 15.6 pulgadas, " +
                            "Intel 11th Core i5-1135G7, 32 GB de RAM, 1 TB SSD, carga rápida, " +
                            "HDMI, cámara web, Wi-Fi, Windows 11, LIONEYE MP",
                    R.drawable.laptop_5,
                    102,
                    4,
                    699.49,
                    0.0,
                    "computadoras",
                    false)
            )

            productsList.add(
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

            productsList.add(
                Product(
                    6,
                    "Samsung Galaxy S21 5G, Versión EE.UU., 128GB, color Phantom Gray " +
                            "(gris fantasma) - Desbloqueado (renovado)",
                    R.drawable.phone_2,
                    214,
                    5,
                    499.25,
                    0.0,
                    "celulares",
                    false)
            )

            productsList.add(
                Product(
                    7,
                    "Samsung Galaxy A53 5G Serie A - Teléfono celular Android desbloqueado " +
                            "de fábrica, 128 GB, pantalla FHD Super AMOLED de 6.5 pulgadas, " +
                            "batería de larga duración, versión estadounidense, color negro ",
                    R.drawable.phone_3,
                    3129,
                    2,
                    399.99,
                    0.11,
                    "celulares",
                    true)
            )

            productsList.add(
                Product(
                    8,
                    "Samsung 970 EVO Plus SSD 2TB NVMe M.2 - Disco duro interno de estado " +
                            "sólido, tecnología V-NAND, almacenamiento y expansión de memoria " +
                            "para juegos, gráficos con control de calor, velocidad " +
                            "máxima, MZ-V7S2T0B/AM ",
                    R.drawable.ssd_1,
                    2045,
                    4,
                    199.99,
                    0.0,
                    "discos",
                    true)
            )

            productsList.add(
                Product(
                    9,
                    "Western Digital SSD interna de estado sólido SATA SA510 SATA de 1 TB " +
                            "WD Blue - SATA III 6 Gb/s, 2.5\"/7 mm, hasta 560 MB/s - WDS100T3B0A ",
                    R.drawable.ssd_2,
                    165,
                    4,
                    90.47,
                    0.0,
                    "discos",
                    false)
            )

            productsList.add(
                Product(
                    10,
                    "PNY CS900 - Unidad interna de estado sólido (SSD) de 250 GB 3D " +
                            "NAND 2.5 pulgadas SATA III, (SSD7CS900-250-RB) ",
                    R.drawable.ssd_3,
                    687,
                    3,
                    147.23,
                    0.01,
                    "discos",
                    false)
            )

            productsList.add(
                Product(
                    11,
                    "Apple iPhone 12 Pro Max, 256GB, grafito - Desbloqueado (renovado " +
                            "Premium) con estuche incluído, disponible en todos los colores, " +
                            "resistente al agua y al polvo",
                    R.drawable.phone_4,
                    1056,
                    4,
                    617.43,
                    0.0,
                    "celulares",
                    true)
            )

            productsList.add(
                Product(
                    12,
                    "WD color morado de vigilancia disco duro – 5400 rpm, clase, " +
                            "SATA 6 GB/s 3.5 inch (reacondicionado certificado) ",
                    R.drawable.disco_1,
                    109,
                    4,
                    260.99,
                    0.025,
                    "discos",
                    false)
            )
        }
    }
}