import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@WireMockTest
public class DeclarativeWireMockTest {
    @Test
    void test_something_with_wiremock(WireMockRuntimeInfo wmRuntimeInfo) {
        // The static DSL will be automatically configured for you
        stubFor(get("/static-dsl").willReturn(ok()));

        // Instance DSL can be obtained from the runtime info parameter
        WireMock wireMock = wmRuntimeInfo.getWireMock();
        wireMock.register(get("/instance-dsl").willReturn(ok()));

        // Info such as port numbers is also available
        int port = wmRuntimeInfo.getHttpPort();
    }
}
