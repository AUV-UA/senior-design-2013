package org.auvua.catfish.test;

import org.auvua.catfish.CrcChecksum;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(value = { ControllerTest.class,
		KeyboardControllerTest.class, CrcChecksum.class })
public class CATfishTestSuite {

}
