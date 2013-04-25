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
	private int checksum1 = 61396;	//0xefd4 in decimal
	private int checksum2 = 23801;	//0x5cf9 in decimal
	private int checksum3;
	private int checksum4;
	private int invalid1 = 1241;
	private int invalid2 = 22;

	@Test
	public void testChecksumToString() {
		String hex1 = "0";
		String hex2 = "efd4";
		String hex3 = "5cf9";
		
		assertEquals(checksum.toHexString(0x0000), hex1);

		assertEquals(checksum.toHexString(checksum1), hex2);

		assertEquals(checksum.toHexString(checksum2), hex3);
	}

	@Test
	public void testChecksumGenerate() {
		assertEquals(CrcChecksum.generate(data1), checksum1);
		assertEquals(CrcChecksum.generate(data2), checksum2);
	}

	@Test
	public void testChecksums() {
		assertEquals(checksum.update(data1, 0x0000), checksum1);
		assertEquals(checksum.update(data2, 0x0000), checksum2);
	}

	@Test
	public void testChecksumVerify() {
		assertTrue(checksum.verify(checksum1, data1));
		assertTrue(checksum.verify(checksum2, data2));
	}

	@Test
	public void testChecksumUpdate() {
		assertEquals(CrcChecksum.update(data3, checksum1), checksum3);
		assertEquals(CrcChecksum.update(data4, checksum2), checksum4);
	}
}
