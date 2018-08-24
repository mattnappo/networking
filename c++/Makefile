BUILD = build
CC = g++
SRC = chat.cpp server.cpp client.cpp 
chat.app: $(SRC) chat.h
	$(CC) -o $(BUILD)/$@ $(SRC)
server.app: server.cpp
	$(CC) -o $(BUILD)/$@ $^
client.app: client.cpp
	$(CC) -o $(BUILD)/$@ $^
clean:
	rm build/*.app