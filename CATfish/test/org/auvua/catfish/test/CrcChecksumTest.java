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
	private byte data3[] = { 0x00 };
	private byte data4[] = { 0x00 };
	private int checksum1 = 1243;
	private int checksum2 = 23;
	private int checksum3;
	private int checksum4;
	private int invalid1 = 1241;
	private int invalid2 = 22;

	@Test
	public void testChecksumToString() {
		String hex1 = "0x0A";
		String hex2 = "0x64";

		assertEquals(CrcChecksum.toHexString(10), hex1);
		assertEquals(CrcChecksum.toHexString(100), hex2);
	}

	@Test
	public void testChecksumGenerate() {
		assertEquals(CrcChecksum.generate(data1), checksum1);
		assertEquals(CrcChecksum.generate(data2), checksum2);
	}

	@Test
	public void testChecksumVerify() {
		assertTrue(CrcChecksum.verify(checksum1, data1));
		assertTrue(CrcChecksum.verify(checksum2, data2));

		assertTrue(CrcChecksum.verify(checksum3, data1));
		assertTrue(CrcChecksum.verify(checksum4, data2));
	}

	@Test
	public void testChecksumUpdate() {
		assertEquals(CrcChecksum.update(data3, checksum1), checksum3);
		assertEquals(CrcChecksum.update(data4, checksum2), checksum4);
	}
}
