play-json-api
=============

This project served to demonstrate the [playframework](https://www.playframework.com/) JSON API in Java and in Scala.

It was used at the [Berlin User Group](http://www.meetup.com/Play-Berlin-Brandenburg/events/214930102/).


1. build JSON with Java

2. build JSON with Scala

3. consume JSON (request body) with Java

    in java:

```java
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Json.setObjectMapper(mapper);
```
(not needed anymore in trunk: https://github.com/playframework/playframework/pull/3670)

4. consume JSON (request body) with Scala

- validate id != -1 with `(.filter(_ != -1))`
- show with optional field with `Option[String]`
- defined default reads and writes
- defined reads and writes in one time (with Formats)


5. send and consume JSON with WS in Java


6. send and consume JSON with WS in Scala


7. consume and transform JSON in Scala

```scala
    val copyValue: Reads[JsObject] = (
      (__ \ "id").json.prune and
      (__ \ "name").json.copyFrom( (__ \ "group").json.pick )
    ).reduce

    val addValue: Reads[JsObject] = (
      (__ \ "id").json.prune and
      (__ \ "group").json.pickBranch and
      (__ \ "myfield").json.put(JsNumber(42))
    ).reduce
```
