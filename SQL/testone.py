f = open("order-data.sql", "a")
f.seek(0)
f.truncate()

f.write("INSERT INTO ob_order (id, version, stock_id, price, order_size, side, number_matched, placed_at, usr_id, status) VALUES\n")
buy = 50
sell = 200
numberMatched = 100
id = 1
version = 0

while id < 51:
    f.write("({},{},{},{},{},\"{}\",{},\"{}\",{},\"{}\"),\n".format(id, version, 1, buy, 50, "BUY", 50, "2020-08-25 06:36:19", 1, "CANCELLED"))
    id += 1
    buy += 1

while id < 100:
    f.write("({},{},{},{},{},\"{}\",{},\"{}\",{},\"{}\"),\n".format(id, version, 1, sell, 50, "SELL", 50, "2020-08-25 06:36:19", 1, "CANCELLED"))
    id += 1
    sell -= 1
f.write("({},{},{},{},{},\"{}\",{},\"{}\",{},\"{}\")\n".format(id, version, 1, sell, 50, "SELL", 50, "2020-08-25 06:36:19", 1, "CANCELLED"))
f.close()