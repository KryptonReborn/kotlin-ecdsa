package dev.kryptonreborn.ecdsa

import com.ionspin.kotlin.bignum.integer.BigInteger
import com.ionspin.kotlin.bignum.integer.Sign
import kotlin.test.assertTrue

@OptIn(ExperimentalStdlibApi::class)
object TestUtil {
    fun testSigningWithTestVectors(
        loadTestJson: () -> TestJson,
        curve: EcCurve,
        hashFunction: EcHasher,
    ) {
        val testGroups = loadTestJson().testGroups
        for (group in testGroups) {
            val publishKey =
                EcPoint.fromByteArray(group.key.wx.hexToByteArray(), group.key.wy.hexToByteArray(), curve)
            for (testcase in group.tests) {
                val tcId = "testcase ${testcase.tcId} (${testcase.comment})"
                val msg = testcase.msg.hexToByteArray()
                val sig = testcase.sig.hexToByteArray()
                if (testcase.result == "valid") {
                    val signature =
                        EcSignature(
                            extractR(sig),
                            extractS(sig),
                        )
                    assertTrue(EcSign.verifySignature(publishKey, msg, hashFunction, signature), tcId)
                }
            }
        }
    }

    // https://github.com/C2SP/wycheproof/blob/cd27d6419bedd83cbd24611ec54b6d4bfdb0cdca/java/com/google/security/wycheproof/testcases/EcdsaTest.java#L123
    private fun extractR(signature: ByteArray): BigInteger {
        val startR = if ((signature[1].toInt() and 0x80) != 0) 3 else 2
        val lengthR = signature[startR + 1].toInt()
        return BigInteger.fromByteArray(signature.copyOfRange(startR + 2, startR + 2 + lengthR), Sign.POSITIVE)
    }

    private fun extractS(signature: ByteArray): BigInteger {
        val startR = if ((signature[1].toInt() and 0x80) != 0) 3 else 2
        val lengthR = signature[startR + 1].toInt()
        val startS = startR + 2 + lengthR
        val lengthS = signature[startS + 1].toInt()
        return BigInteger.fromByteArray(signature.copyOfRange(startS + 2, startS + 2 + lengthS), Sign.POSITIVE)
    }
}
