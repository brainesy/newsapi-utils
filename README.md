NewsAPI Utilities
=================

This repo contains a Java Maven project and some Java utility classes to get up and running quickly with the NewsAPI.

* Java POJO's for the ContentAPI
* A parser to convert from JSON to the ContentAPI Java POJO's
* A Proxy servlet that routes requests to Mashery to solve the cross-origin domain problem for Javascript Web apps.

Java POJO's
------------
A Java representation of the ContentAPI object model.

Package: newsapi.content.version1

Key classes:
* Version1ContentSearchResult
* Version1NewsStory
* Version1Image
* Version1Video
* Version1ImageGallery
* Version1Collection

Usage: 
<pre>
See newsapi.content.ContentTest
</pre>

JSON Parser
-----------
A JSON parser to deserialize the ContentAPI JSON output into the JAVA object model

Package: newsapi.util.json
Class: JsonParser

Usage:
<pre>
// Parse the list of search results
Version1ContentSearchResult result = JsonParser.fromJson(jsonSearchResult, Version1ContentSearchResult.class);

// Parse a get response into a generic Version1Content object
Version1Content content = JsonParser.fromJson(jsonGetResult, Version1Content.class);

// Parse a get response into a Version1NewsStory object
Version1NewsStory content = JsonParser.fromJson(jsonGetResult, Version1NewsStory.class);
</pre>

Also see:
<pre>
See newsapi.content.ContentTest
</pre>

Proxy Servlet
-------------
A simple proxy servlet to forward requests to Mashery.  Useful to get around the cross-origin domain problem with Javascript web apps.  

Note: To utilise this fully, you will also need to serve your web app and Javascript from this container.

Usage:
<pre>
mvn jetty:run -Dapi_key=your-mashery-api-key
</pre>

This will fire up a Jetty container with the proxy running on localhost:8080/proxy.  You can then proxy requests through to Mashery with:
http://localhost:8080/proxy/content/v1/





