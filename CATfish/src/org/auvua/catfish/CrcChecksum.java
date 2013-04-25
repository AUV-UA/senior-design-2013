package org.auvua.catfish;

/**
 * 
 * Generates the XModem-CRC checksum.
 * 
 */
public class CrcChecksum {
	/* Represents x^16+x^12+x^5+1 */
	private final static int polynomial = 0x1021;
	private int crc;

	public CrcChecksum() {
		crc = 0x0000;
	}

	/**
	 * Fetch the integer representation of the checksum
	 * 
	 * @return
	 */
	public int getChecksum() {
		return crc;
	}

	/**
	 * Resets the current checksum back to null.
	 */
	public void reset() {
		crc = 0x0000;
	}

	/**
	 * Updates the current crc checksum with the given bytes.
	 * 
	 * @param args
	 */
	public void update(byte[] args) {
		for (byte b : args) {
			for (int i = 0; i < 8; i++) {
				boolean bit = ((b >> (7 - i) & 0x1) == 1);
				boolean c15 = ((crc >> 15 & 0x1) == 1);
				crc <<= 1;
				// If coefficient of bit and remainder polynomial = 1 xor crc
				// with polynomial
				if (c15 ^ bit) {
					crc ^= polynomial;
				}
			}
		}

		crc &= 0xffff;
	}

	/**
	 * Compares the current checksum with the given checksum and returns true if
	 * the sums match.
	 * 
	 * @param checksum
	 * @return
	 */
	public boolean verify(int checksum) {
		return crc == checksum;
	}

	/**
	 * Returns a Hex String representation of the checksum.
	 */
	@Override
	public String toString() {
		return Integer.toHexString(crc);
	}
}