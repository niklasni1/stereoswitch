Overview
====

StereoSwitch exposes a json/HTTP API for discovering and connecting inputs to outputs.

The use-case is a web-controllable analogue audio switch: one out of a number of defined inputs go to one of a number of defined outputs. This requires hardware which at the moment only exists on the back of a napkin.

JSON objects
----

Two types of objects are used by the StereoSwitch API:

### Connection objects ###

Connections are pairs of source id and a destination id:

    {
      "connection": {
        "source": Int,
        "destination": Int
      }
    }

### Endpoint objects ###

These are either inputs or outputs:

    {
      "input": {
          "name": String
        }
    }

HTTP resources
----

The following resources and methods are defined:

### /endpoints ###

* GET: responds with list of endpoints as a json object of "sources":sources and "destinations":destinations where sources and destinations are json arrays of input objecst and output objects, respectively.

### /endpoints/sources ###

* GET: responds with list of inputs as a json array of input objects.

### /endpoints/sources/{id: Int} ###

* GET: responds with the single input object with a given id, or HTTP status code 404 if no object with this id exists.

### /endpoints/destinations ###

* GET: responds with a list of destinations as a json array of output objects.

### /endpoints/destinations/{id: Int} ###

* GET: responds with the single input object with the given id, or HTTP status code 404 if no object with this id exists.

### /connection ###

* GET: responds with a single connection object describing the currently connected input and output.

* PUT: accepts a connection object and attempts to make the connection described. If this fails (because either endpoint doesn't exist), HTTP status code 422 is returned.
