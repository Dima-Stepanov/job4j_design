package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 2.2.2. Socket
 * 0. Что такое Socket?
 * 1.Бот [#7921#127268]
 * 4.Slf4j-вывод exception.[#268853#127254]
 *
 * @author Dmitry
 * @since 29.11.2021
 */
public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 Ok\r\n\r\n".getBytes());
                    String str = in.readLine();
                    System.out.println(str);
                    str = str.toLowerCase();
                    if (str.contains("msg=hello")) {
                        out.write("Hello, dear friend".getBytes());
                        continue;
                    }
                    if (str.contains("msg=exit")) {
                        out.write("Goodbye\r\n\r\n".getBytes());
                        server.close();
                        continue;
                    }
                    out.write("What the message?".getBytes());
                    out.flush();
                }
            }
        } catch (IOException e) {
            LOG.error("Exception in log example", e);
        }
    }
}
