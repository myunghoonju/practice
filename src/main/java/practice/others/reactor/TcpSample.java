package practice.others.reactor;

import java.util.concurrent.TimeUnit;

import io.netty.channel.ChannelOption;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;
import reactor.core.publisher.Mono;
import reactor.netty.Connection;
import reactor.netty.DisposableServer;
import reactor.netty.resources.ConnectionProvider;
import reactor.netty.tcp.TcpClient;
import reactor.netty.tcp.TcpClientConfig;
import reactor.netty.tcp.TcpServer;

public class TcpSample {

    public static void main(String[] args) {
        servers();
        clients();
    }

    private static void servers() {
        DisposableServer server = TcpServer.create()
                                           .host("localhost")
                                           .port(8080)
                                           .bind()
                                           .block();

        // receive data
        TcpServer tcpServer = TcpServer.create()
                                       .handle((nettyInbound, nettyOutbound) -> nettyInbound.receive().then());
        tcpServer.warmup().block();
        DisposableServer bind1 = tcpServer.port(8080).bindNow();
        DisposableServer bind2 = tcpServer.port(8081).bindNow();
        Mono.when(bind1.onDispose(), bind2.onDispose()).block(); // Mono.when ??

        // send data
        DisposableServer tcpServer2 = TcpServer.create().handle((nettyInbound, nettyOutbound) -> nettyOutbound.sendString(Mono.just("he"))).bindNow();
        tcpServer2.onDispose().block();

        DisposableServer nettySample = TcpServer.create()
                                                .doOnConnection(connection -> connection.addHandlerFirst(
                                                        new ReadTimeoutHandler(10, TimeUnit.SECONDS)))
                                                .doOnChannelInit(
                                                        (obs, channel, remoteAddr) -> channel.pipeline()
                                                                                             .addFirst(
                                                                                                     new LoggingHandler(
                                                                                                             "netty sample")))
                                                .bindNow();
        nettySample.onDispose().block();
    }

    private static void clients() {
        Connection connection = TcpClient.create()
                                         .host("localhost")
                                         .port(80)
                                         .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10_000)
                                         .connectNow();
        connection.onDispose().block();

        TcpClient client = TcpClient.create().host("127.0.0.1").port(90).handle((in, out) -> out.sendString(Mono.just("Hello")));
        client.warmup().block();

        Connection finalConnection = client.connectNow();
        finalConnection.onDispose().block();
    }
}
