When do you need JSON:

consume a REST API
  body of response with WS client
  body of request

  JSON -> model classes
    in java:

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Json.setObjectMapper(mapper);


  in scala
  // TODO: validate id != -1 (.filter(_ != -1))
  // TODO: with optional field
  // TODO: defined default reads and writes
  // TODO: defined reads and writes in one time


produce JSON
  send JSON to other server, with WS client
  send JSON to client (browser, other server)

  model classes -> JSON
  construct JSON directly

transform JSON
  consume a REST API to produce a REST API


