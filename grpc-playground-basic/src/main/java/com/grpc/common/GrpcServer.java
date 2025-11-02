package com.grpc.common;

import com.grpc.sec06.BankService;
import io.grpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;

public class GrpcServer {

    private static final Logger log = LoggerFactory.getLogger(GrpcServer.class);

  private final Server server;


    private GrpcServer(Server server) {
        this.server = server;
    }

    public static  GrpcServer create(BindableService... services){
        return create(6565, services);
    }

    public static  GrpcServer create(int port, BindableService... services) {
        var builder = ServerBuilder.forPort(port);
        Arrays.asList(services).forEach(builder ::addService);
        return new GrpcServer(builder.build());
    }

    public GrpcServer start() throws IOException {
        var services = server.getServices()
                .stream()
                .map(ServerServiceDefinition::getServiceDescriptor)
                .map(ServiceDescriptor::getName)
                .toList();
        try{
            server.start();
            log.info("Server started, listening on " + server.getPort());
            return this;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void awit(){
        try{
            server.awaitTermination();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void stop(){
        server.shutdown();
        log.info("Server shut down");
    }
}
