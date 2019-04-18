package com.yarpg.at.yatspec;

import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringSpecRunner.class)
@SpringApplicationConfiguration(classes = { ApplicationConfigurationForEndToEndTests.class })
@IntegrationTest
@WebAppConfiguration
public abstract class EndToEndYatspecTest extends YatspecTest {

}
