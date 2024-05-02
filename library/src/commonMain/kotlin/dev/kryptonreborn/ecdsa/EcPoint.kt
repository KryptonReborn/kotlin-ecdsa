package dev.kryptonreborn.ecdsa

import com.ionspin.kotlin.bignum.integer.BigInteger
import com.ionspin.kotlin.bignum.integer.Sign

/**
 * A point on an elliptical curve (x, y).
 *
 * @property x The x value of the point on the curve.
 * @property y The y value of the point on the curve.
 * @property curve The curve the point belongs to.
 */
data class EcPoint(
    val x: BigInteger,
    val y: BigInteger,
    val curve: EcCurve,
) {
    val xByteArray: ByteArray
        get() {
            val xarray = x.toByteArray()
            return if (xarray[0].toInt() < 0) byteArrayOf(0, *xarray) else xarray
        }

    val yByteArray: ByteArray
        get() {
            val yarray = y.toByteArray()
            return if (yarray[0].toInt() < 0) byteArrayOf(0, *yarray) else yarray
        }

    /**
     * Adds a point to this point.
     *
     * @param other The point to add to this point.
     * @return The sum of the two points.
     */
    operator fun plus(other: EcPoint): EcPoint {
        return curve.add(this, other)
    }

    /**
     * Finds the product of this point and a number. (dotting the curve multiple times)
     *
     * @param n The number to multiply the point by.
     * @return The product of the point and the number.
     */
    operator fun times(n: BigInteger): EcPoint {
        return curve.multiply(this, n)
    }

    companion object {
        fun fromByteArray(
            x: ByteArray,
            y: ByteArray,
            curve: EcCurve,
        ): EcPoint = EcPoint(
            x = BigInteger.fromByteArray(x, Sign.POSITIVE),
            y = BigInteger.fromByteArray(y, Sign.POSITIVE),
            curve = curve,
        )
    }
}
