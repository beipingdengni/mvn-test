package com.tian.spring.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author tianbeiping
 * @Title: NIOSoketServerMain
 * @ProjectName mvn-test
 * @Description:
 * @date 19/3/8下午2:49
 */
public class NIOSoketServerMain {


    public static void main(String[] args) throws IOException {
        Selector selector = null;
        try {
            // 1. open a selector
            selector = Selector.open();
            // 2. listen for server socket channel
            ServerSocketChannel channel = ServerSocketChannel.open();
            // must to be nonblocking mode before register
            channel.configureBlocking(false);
            // bind server socket channel to port 7788
            channel.bind(new InetSocketAddress(7788));
            // 3. register it with selector
            channel.register(selector, SelectionKey.OP_ACCEPT);

            while (true) {
                // 4. select ready SelectionKey for I/O operation
                if (selector.select(3000) == 0) {
                    continue;
                }
                // 5. get selected keys
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                // 6. handle selected key's interest operations
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();

                    if (key.isAcceptable()) {
                        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                        // get socket channel from server socket channel
                        SocketChannel clientChannel = serverSocketChannel.accept();
                        // must to be nonblocking before register with selector
                        clientChannel.configureBlocking(false);
                        // register socket channel to selector with OP_READ
                        clientChannel.register(key.selector(), SelectionKey.OP_READ);
                    }

                    if (key.isReadable()) {
                        // read bytes from socket channel to byte buffer
                        SocketChannel clientChannel = (SocketChannel) key.channel();
                        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                        int readBytes = clientChannel.read(readBuffer);
                        if (readBytes == -1) {
                            System.out.println("closed......." + clientChannel.getRemoteAddress().toString());
                            clientChannel.close();
                        } else if (readBytes > 0) {
                            String s = new String(readBuffer.array(), "UTF-8");
                            System.out.println("Client said: " + s.trim());
//                            if (s.trim().equals("Hello")) {
                            // attachment is content used to write
                            key.interestOps(SelectionKey.OP_WRITE);
                            key.attach("Welcome=====>>>>>!!!");
//                            }
                        }
                    }

                    if (key.isValid() && key.isWritable()) {
                        SocketChannel clientChannel = (SocketChannel) key.channel();
                        // get content from attachment
                        String content = (String) key.attachment();
                        // write content to socket channel
                        clientChannel.write(ByteBuffer.wrap(content.getBytes()));
                        key.interestOps(SelectionKey.OP_READ);
                    }
                    // remove handled key from selected keys
                    iterator.remove();

                }//end iterator

            }// end while


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // close selector
            if (selector != null) {
                try {
                    selector.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

}
