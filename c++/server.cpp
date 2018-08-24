#include <iostream>
#include <string>
#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <netdb.h>
#include <sys/uio.h>
#include <sys/time.h>
#include <sys/wait.h>
#include <fcntl.h>
#include <fstream>
using namespace std;

int main(int argc, char *argv[]) {
    if (argc != 2) {
        cerr << "Usage: port" << endl;
        exit(0);
    }
    int port = atoi(argv[1]); // Get the port number
    char msg[1500]; // Message buffer
    sockaddr_in server_address; // Initialize socket
    bzero((char*)&server_address, sizeof(server_address));
    server_address.sin_family = AF_INET;
    server_address.sin_addr.s_addr = htonl(INADDR_ANY);
    server_address.sin_port = htons(port);
 
    int server_socket = socket(AF_INET, SOCK_STREAM, 0); // Open the socket
    if (server_socket < 0) {
        cerr << "Error establishing the server socket" << endl;
        exit(0);
    }
    // Bind the socket
    int bind_status = bind(
        server_socket,
        (struct sockaddr*) &server_address,
        sizeof(server_address)
    );
    if (bind_status < 0) {
        cerr << "Error binding socket to local address" << endl;
        exit(0);
    }
    cout << "Waiting for a client to connect..." << endl;
    listen(server_socket, 5); // Listen to up to five requests at a time
    sockaddr_in new_socket_address; // Create new address to connect to the client
    socklen_t new_socket_addressSize = sizeof(new_socket_address);
    int new_socket = accept( 
        server_socket,
        (sockaddr *)&new_socket_address,
        &new_socket_addressSize
    ); // Create new socket to interact with the client
    if (new_socket < 0) {
        cerr << "Error accepting request from client!" << endl;
        exit(1);
    }
    cout << "Connected with client!" << endl;
    
    struct timeval start1, end1; // Keep track of session time
    gettimeofday(&start1, NULL);
    int bytesRead, bytesWritten = 0; // Keep track of all traffic transmitted
    while (1) {
        cout << "Awaiting client response..." << endl;
        memset(&msg, 0, sizeof(msg));//clear the buffer
        bytesRead += recv(new_socket, (char*)&msg, sizeof(msg), 0);
        if (!strcmp(msg, "exit")) {
            cout << "Client has quit the session" << endl;
            break;
        }
        cout << "Client: " << msg << endl;
        cout << ">";
        string data;
        getline(cin, data);
        memset(&msg, 0, sizeof(msg)); // Clear the buffer
        strcpy(msg, data.c_str());
        if (data == "exit") {
            send(new_socket, (char*)&msg, strlen(msg), 0); // Send to the client that server has closed the connection
            break;
        }
        bytesWritten += send(new_socket, (char*)&msg, strlen(msg), 0); // Send the message to client
    }
    // Close all sockets
    gettimeofday(&end1, NULL);
    close(new_socket);
    close(server_socket);
    cout << "********Session********" << endl;
    cout << "Bytes written: " << bytesWritten << " Bytes read: " << bytesRead << endl;
    cout << "Elapsed time: " << (end1.tv_sec - start1.tv_sec) 
        << " secs" << endl;
    cout << "Connection closed..." << endl;
    return 0;   
}
