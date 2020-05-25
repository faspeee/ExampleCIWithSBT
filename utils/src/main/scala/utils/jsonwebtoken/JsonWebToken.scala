package utils.jsonwebtoken
import java.time.Instant

import pdi.jwt.algorithms.JwtHmacAlgorithm
import pdi.jwt.{JwtAlgorithm, JwtClaim, JwtSprayJson}
object JsonWebToken {
  val claim: JwtClaim = JwtClaim(
          expiration = Some(Instant.now.plusSeconds(157784760).getEpochSecond),
          issuedAt = Some(Instant.now.getEpochSecond)
        )
  val key = "secretKey"
  val algo: JwtHmacAlgorithm = JwtAlgorithm.HS256
  val token: String = JwtSprayJson.encode(claim, key, algo)
  val r = JwtSprayJson.decodeJson(token, key, Seq(JwtAlgorithm.HS256))
  val r2 = JwtSprayJson.decode(token, key, Seq(JwtAlgorithm.HS256))
  println(token)
  println(r)
  println(r2)
}
object t extends App{
  JsonWebToken
}