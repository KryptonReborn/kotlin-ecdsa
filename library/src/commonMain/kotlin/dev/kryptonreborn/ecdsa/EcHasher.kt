package dev.kryptonreborn.ecdsa

interface EcHasher {
    fun hash(data: ByteArray): ByteArray
}
