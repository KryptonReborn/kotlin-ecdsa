package dev.kryptonreborn.ecdsa

import com.goncalossilva.resources.Resource
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
class TestJson(
    val algorithm: String,
    val generatorVersion: String,
    val numberOfTests: Int,
    val header: List<String>,
    val notes: Map<String, String>,
    val schema: String,
    val testGroups: List<TestGroup>,
)

@Serializable
class TestGroup(
    val key: Key,
    val keyDer: String,
    val keyPem: String,
    val sha: String,
    val type: String,
    val tests: List<TestCase>,
)

@Serializable
class TestCase(
    val tcId: Int,
    val comment: String,
    val msg: String,
    val sig: String,
    val result: String,
)

@Serializable
class Key(
    val curve: String,
    val keySize: Int,
    val type: String,
    val uncompressed: String? = null,
    val wx: String,
    val wy: String,
)

fun loadSecp256k1Sha256TestJson(): TestJson {
    val json =
        Json {
            ignoreUnknownKeys = true
        }
//  https://github.com/C2SP/wycheproof/blob/master/testvectors/ecdsa_secp256k1_sha256_test.json
    return json.decodeFromString(Resource("src/commonTest/resources/wycheproof/secp256k1_sha256_test.json").readText())
}

fun loadSecp256r1Sha256TestJson(): TestJson {
    val json =
        Json {
            ignoreUnknownKeys = true
        }
//  https://github.com/C2SP/wycheproof/blob/master/testvectors/ecdsa_secp256r1_sha256_test.json
    return json.decodeFromString(Resource("src/commonTest/resources/wycheproof/secp256r1_sha256_test.json").readText())
}
