package com.sail.io;

public class IoDemo {
    /**
     * API-DatagramSocket
     * DatagramSocket()不指定端口与IP
     * DatagramSocket(int port)//创建监听固定端口的实例
     * DatagramSocket(int port，InetAddress address)//创建监听的实例，此为接收who发来了信息
     *
     * receive(DatagramSocket d)接收
     *
     * send(DatagramSocket) 发送
     *
     * close() 关闭资源
     *
     * DatagramPacket 1、用于处理报文 2、将byte数组，目标地址，目标端口等数据包装成报文
     * 是发送实体也是接收实体
     *
     * DatagramPacket(byte[] buf,int offset,int length,InetAddress address,int port)
     * 前3个参数指定buf使用区间
     * 后2参数指定目标机器地址与端口，这是发送信息给who
     *
     * SocketAddress相当于 InetAddress+Port
     *
     * setData(..) 设置一个buff数组
     * setAddress(..)、setPort(..) 发送时有效
     *
     *
     * API-TCP
     *
     * socket() 创建客户端的Socket，之后进行配置
     *
     * bind() 绑定一个Socket到一个本地地址和端口
     *
     * connect() 连接到远程套接字
     *
     * accept() 服务器端Socket独有的操作
     *
     * write() 把数据写入到Socket输出流
     *
     * read() 从Socket输入流读取数据
     *
     *
     * 客户端流程
     *
     * -- 创建Socket
     *
     * -- bind本地套接字 独占
     *
     * -- connect远程套接字  --
     *
     * 服务器端流程
     *
     * -- 创建ServerSocket
     *
     * -- bind本地套接字
     *
     * -- accept客户端套接字
     *
     * IPC - 一个进程可以创建一个或多个Socket
     *
     */
}
