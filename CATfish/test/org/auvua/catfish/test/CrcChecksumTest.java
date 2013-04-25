package org.auvua.catfish.test;

import static org.junit.Assert.*;

import org.auvua.catfish.CrcChecksum;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

//TODO: Fix the checksum values and add more checksums to test
public class CrcChecksumTest {
	private CrcChecksum checksum;
	private byte data1[] = { 0x00, 0x05, 0x01 };
	private byte data2[] = { 0x00, 0x09, 0x0a, 0x00, 0x00, 0x00, 0x14 };
	private int checksum1 = 61396;	//0xefd4 in decimal
	private int checksum2 = 23801;	//0x5cf9 in decimal
	private int invalid1 = 1241;
	private int invalid2 = 22;

	@Before
	public void setup() {
		checksum = new CrcChecksum();
	}

	@After
	public void cleanup() {
		checksum = null;
	}

	@Test
	public void testChecksumToString() {
		String hex1 = "0";
		String hex2 = "efd4";
		String hex3 = "5cf9";
		
		assertEquals(checksum.toString(), hex1);

		checksum.update(data1);
		assertEquals(checksum.toString(), hex2);

		checksum.reset();
		checksum.update(data2);
		assertEquals(checksum.toString(), hex3);
	}

	@Test
	public void testChecksumReset() {
		int reset = 0;
		assertEquals(checksum.getChecksum(), reset);

		checksum.update(data1);
		checksum.reset();

		assertEquals(checksum.getChecksum(), reset);
	}

	@Test
	public void testChecksums() {
		checksum.reset();
		checksum.update(data1);
		assertEquals(checksum.getChecksum(), checksum1);

		checksum.reset();
		checksum.update(data2);
		assertEquals(checksum.getChecksum(), checksum2);
	}

	@Test
	public void testChecksumVerify() {
		checksum.reset();
		checksum.update(data1);
		assertTrue(checksum.verify(checksum1));

		checksum.reset();
		checksum.update(data2);
		assertTrue(checksum.verify(checksum2));
	}
}
