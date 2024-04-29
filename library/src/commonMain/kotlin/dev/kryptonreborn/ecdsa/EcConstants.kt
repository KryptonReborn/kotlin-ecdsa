package dev.kryptonreborn.ecdsa

import com.ionspin.kotlin.bignum.integer.BigInteger
import com.ionspin.kotlin.bignum.integer.toBigInteger

/**
 * An object to hold static values used when doing point math.
 */
object EcConstants {
    val ZERO: BigInteger = BigInteger.ZERO
    val ONE: BigInteger = BigInteger.ONE
    val TWO: BigInteger = BigInteger.TWO
    val THREE: BigInteger = 3.toBigInteger()
}
