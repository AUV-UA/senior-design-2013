package org.auvua.catfish;

/**
 * 
 * Generates the XModem-CRC checksum.
 * 
 */
public class CrcChecksum {
	/* Represents x^16+x^12+x^5+1 */
	private final static int polynomial = 0x1021;

	/**
	 * Generate a new checksum
	 * 
	 * @return
	 */
	public static int generate(byte[] data) {
		return update(data, 0x0000);
	}

	/**
	 * Updates the current crc checksum with the given bytes.
	 * 
	 * @param args
	 */
	public static int update(byte[] args, int checksum) {

		for (byte b : args) {
			for (int i = 0; i < 8; i++) {
				boolean bit = ((b >> (7 - i) & 0x1) == 1);
				boolean c15 = ((checksum >> 15 & 0x1) == 1);
				checksum <<= 1;
				// If coefficient of bit and remainder polynomial = 1 xor crc
				// with polynomial
				if (c15 ^ bit) {
					checksum ^= polynomial;
				}
			}
		}

		checksum &= 0xffff;

		return checksum;
	}

	/**
	 * Compares the current checksum with the given checksum and returns true if
	 * the sums match.
	 * 
	 * @param checksum
	 * @return
	 */
	public static boolean verify(int checksum1, byte[] data) {
		return checksum1 == generate(data);
	}

	/**
	 * Returns a Hex String representation of the checksum.
	 */
	public static String toHexString(int checksum) {
		return Integer.toHexString(checksum);
	}
}