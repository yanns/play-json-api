1. build JSON with Java

2. build JSON with Scala

3. consume JSON (request body) with Java

    in java:

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Json.setObjectMapper(mapper);

4. consume JSON (request body) with Scala

  // TODO: validate id != -1 (.filter(_ != -1))
  // TODO: with optional field
  // TODO: defined default reads and writes
  // TODO: defined reads and writes in one time


5. send and consume JSON with WS in Java


6. send and consume JSON with WS in Scala


7. consume and transform JSON in Scala

  val copyValue: Reads[JsObject] = (
      (__ \ "id").json.prune and
      (__ \ "name").json.copyFrom( (__ \ "group").json.pick )
    ).reduce

  val addValue: Reads[JsObject] = (
      (__ \ "id").json.prune and
        (__ \ "group").json.pickBranch and
        (__ \ "myfield").json.put(JsNumber(42))
    ).reduce


