package dev.kryptonreborn.ecdsa

import org.kotlincrypto.hash.sha2.SHA512

object EcSha512 : EcHasher {
    override fun hash(data: ByteArray): ByteArray {
        return SHA512().digest(data)
    }
}
