import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

public class ProgrammaticWireMockTest {

    @RegisterExtension
    static WireMockExtension wm1 = WireMockExtension.newInstance()
            .options(wireMockConfig().dynamicPort().dynamicHttpsPort())
            .build();

    @RegisterExtension
    static WireMockExtension wm2 = WireMockExtension.newInstance()
            .options(wireMockConfig()
                     .dynamicPort()
                     .extensions(new ResponseTemplateTransformer()))
            .build();

    @Test
    void test_something_with_wiremock() {
        // You can get ports, base URL etc. via WireMockRuntimeInfo
        WireMockRuntimeInfo wm1RuntimeInfo = wm1.getRuntimeInfo();
        int httpsPort = wm1RuntimeInfo.getHttpsPort();

        // or directly via the extension field
        int httpPort = wm1.getPort();

        // You can use the DSL directly from the extension field
        wm1.stubFor(get("/api-1-thing").willReturn(ok()));

        wm2.stubFor(get("/api-2-stuff").willReturn(ok()));
    }
}
