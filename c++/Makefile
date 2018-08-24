CC = g++
SRC = chat.cpp server.cpp client.cpp 

chat.app: $(SRC) chat.h
	$(CC) -o $@ $(SRC) 

server.app: server.cpp
	$(CC) -o $@ $^
server.app: client.cpp
	$(CC) -o $@ $^