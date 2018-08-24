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
    if (argc != 3) {
        cerr << "Usage: <ip_address> <port>" << endl;
        exit(0);
    }
    char *server_ip = argv[1]; int port = atoi(argv[2]); 
    char msg[1500]; // Message buffer
    
    struct hostent* host = gethostbyname(server_ip); // Setup the socket
    sockaddr_in send_socket_address;
    bzero((char*)&send_socket_address, sizeof(send_socket_address));
    send_socket_address.sin_family = AF_INET;
    send_socket_address.sin_addr.s_addr = inet_addr(inet_ntoa(*(struct in_addr*)*host->h_addr_list));
    send_socket_address.sin_port = htons(port);
    int client_socket = socket(AF_INET, SOCK_STREAM, 0);
    
    int status = connect(
        client_socket,
        (sockaddr*) &send_socket_address,
        sizeof(send_socket_address)
    ); // Try connnecting to the server
    if (status < 0) {
        cout << "Error connecting to socket!" << endl;
    }
    cout << "Connected to the server!" << endl;
    int bytesRead, traffic = 0;
    struct timeval start1, end1;
    gettimeofday(&start1, NULL);
    while (1) {
        cout << ">";
        string data;
        getline(cin, data);
        memset(&msg, 0, sizeof(msg)); // Clear the buffer
        strcpy(msg, data.c_str());
        if (data == "exit") {
            send(client_socket, (char*)&msg, strlen(msg), 0);
            break;
        }
        traffic += send(client_socket, (char*)&msg, strlen(msg), 0);
        cout << "Awaiting server response..." << endl;
        memset(&msg, 0, sizeof(msg)); // Clear the buffer
        bytesRead += recv(client_socket, (char*)&msg, sizeof(msg), 0);
        if (!strcmp(msg, "exit")) {
            cout << "Server has quit the session" << endl;
            break;
        }
        cout << "Server: " << msg << endl;
    }
    gettimeofday(&end1, NULL);
    close(client_socket);
    cout << "********Session********" << endl;
    cout << "Bytes written: " << traffic << 
    " Bytes read: " << bytesRead << endl;
    cout << "Elapsed time: " << (end1.tv_sec- start1.tv_sec) 
      << " secs" << endl;
    cout << "Connection closed" << endl;
    return 0;    
}

