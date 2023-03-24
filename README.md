# agriculturalmanagesystem
Agricultural crop management system that has three entities: Crop, Store and Supplier. Crops have two types: Fruit and Vegetable.Only fruits are stored (kept in coolers and listed) in the stores or in the suppliers after
cultivation, and suppliers can buy the existing fruits available in the store or sell their own fruits and then
those sold fruits are kept in the store. There can be a list of crops, stores and suppliers. When the program
starts, the list of crops, stores and suppliers are read from relevant text files.

Relevent text files:

The list of Suppliers.txt: [Supplier name, Supplier ID, budget]
ArazMeyve, 1133, 1000
AylarTarim, 1298, 1500
HasanBey, 1322, 200
ZehraCiftci, 1429, 1250

The list of Stores.txt: [Store name, Store ID, maxCapacityArea(m2), KGperSquareMeter]
Migros, 5343, 1000, 12
File, 5967, 1200, 10

Crops.txt:
Name, type, kilogram, season, taste, price, ID of cropKeeper (for fruits)
Name, type, kilogram, CityName, ID of cropKeeper (for vegetables)

The initial content of Crops.txt:
RedApple, fruit, 45, winter, sweet, 3, 1133
Orange, fruit, 50, autumn, sour, 4, 5967
Kiwi, fruit, 10, autumn, sour, 10, 1133
Parsley , vegetable, 25, Samsun, 1429
Mint, vegetable, 15, Adana, 1429
GreenApple, fruit, 25, winter, sweet, 6, 1133
Orange, fruit, 20, autumn, sour, 4, 1322
GreenApple, fruit, 5, winter, sweet, 6, 5343
GreenBeans, vegetable, 22, Bursa, 1322
Banana, fruit, 63, summer, sweet, 12, 5343





