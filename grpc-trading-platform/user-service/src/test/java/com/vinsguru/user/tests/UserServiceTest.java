package com.vinsguru.user.tests;

import com.vinsguru.user.UserInformationRequest;
import com.vinsguru.user.UserServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
        "grpc.server.port=1",
        "grpc.server.in-process-name=integration-test",
        "grpc.client.user-service.address=in-process://integration-test"
})
public class UserServiceTest {

    @GrpcClient("user-service")
    private UserServiceGrpc.UserServiceBlockingStub stub;

    public void userInformationTest(){
        var request = UserInformationRequest.newBuilder()
                .setUserId(1)
                .build();
        var response = this.stub.getUserInformation(request);
        Assertions.assertEquals(10_000, response.getBalance());
        Assertions.assertEquals("Sam", response.getName());
        Assertions.assertTrue(response.getHoldingsList().isEmpty());
    }

}
