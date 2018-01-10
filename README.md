# TCP-Chat peer to peer with ServerSocket

Each user starts an own server on his own machine. 
The server waits for a client, and when another client connects to it it handles that client on other thread separately using Executor Service.

Each user start an own client app on different thread on his machine. 

With the client app, the user is able to send a message to a user by selecting the host and port of other user (This needs to be changed, to have something like a friend list).

Each call is an asynchronous using CompletableFuture.

The user is able to receive messages from one or more users at the same time, even if he is writing meanwhile a message to another user.
