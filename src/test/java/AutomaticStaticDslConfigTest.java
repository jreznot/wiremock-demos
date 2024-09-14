import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

public class AutomaticStaticDslConfigTest {

    @RegisterExtension
    static WireMockExtension wm1 = WireMockExtension.newInstance()
            .options(wireMockConfig().dynamicPort().dynamicHttpsPort())
            .configureStaticDsl(true)
            .build();

    @RegisterExtension
    static WireMockExtension wm2 = WireMockExtension.newInstance()
            .options(wireMockConfig().dynamicPort().dynamicHttpsPort())
            .build();

    @Test
    void test_something_with_wiremock() {
        // Will communicate with the instance called wm1
        stubFor(get("/static-dsl").willReturn(ok()));

        // Do test stuff...
    }
}
