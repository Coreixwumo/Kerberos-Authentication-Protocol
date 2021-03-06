package kerberos.server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

/**
 * Parent class for all kinds of server. This class implements the common
 * functionality between all the servers.
 */
public abstract class Server {
	// Instance variables initialized by default values
	protected int port;
	//private ServerSocket serverSocket;

	/**
	 * Initializes the port on which the server should listen for client request
	 *
	 * @param port
	 *            Port on which the server would listen for client requests
	 */
	public Server(int port) {
		this.port = port;
	}

	/**
	 * Method to parse the request sent from client. Child classes should
	 * implement this method to parse the request sent from client to generate
	 * relevant response. The client would send data as ';' separated strings.
	 *
	 * @param socket
	 *            The socket on which the client would send request on
	 * @return List of data as String.
	 * @throws IOException
	 */
	protected abstract List<String> parseRequest(Socket socket)
			throws IOException;

	/**
	 * Method to gemerate response for the request sent from client. Child
	 * classes should implement this method to generate the response according
	 * to the request sent from client. If any error occurs while servicing
	 * client's request, only error message should be sent back to the client.
	 * Each server should append a prefix "$$$" before any error messages.
	 *
	 * @param inputData
	 *            List of String that is generated by the parseRequest method.
	 * @return The generated response as ';' separated Strings
	 * @throws IOException
	 */
	protected abstract String generateResponse(List<String> inputData)
			throws IOException;

	/**
	 * Creates a ServerSocket on port given while creating this class's object
	 * and listens for client request
	 *
	 * @throws IOException
	 */
	protected abstract void createSocketAndListen() throws IOException; 

	/**
	 * Send the response generated by generateResponse method back to client
	 *
	 * @param socket
	 *            The socket that is connected to the client
	 * @param response
	 *            The response to be sent to client
	 * @throws IOException
	 */
	protected abstract void sendResponse(Socket socket, String response)
			throws IOException;
}
