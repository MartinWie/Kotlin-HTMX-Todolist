package de.mw.utils

@Suppress("unused")
enum class HtmxExtension(val value: String) {
    // includes the commonly-used X-Requested-With header that identifies ajax requests in many backend frameworks
    AJAX_HEADER("ajax-header"),

    // an extension for using the Alpine.js morph plugin as the swapping mechanism in htmx
    ALPINE_MORPH("alpine-morph"),

    // an extension for manipulating timed addition and removal of classes on HTML elements
    CLASS_TOOLS("class-tools"),

    // support for client side template processing of JSON/XML responses
    CLIENT_SIDE_TEMPLATES("client-side-templates"),

    // an extension for debugging of a particular element using htmx
    DEBUG("debug"),

    // includes a JSON serialized version of the triggering event, if any
    EVENT_HEADER("event-header"),

    // support for merging the head tag from responses into the existing document's head
    HEAD_SUPPORT("head-support"),

    // allows you to include additional values in a request
    INCLUDE_VALS("include-vals"),

    // use JSON encoding in the body of requests, rather than the default x-www-form-urlencoded
    JSON_ENC("json-enc"),

    // an extension for using the idiomorph morphing algorithm as a swapping mechanism
    IDIOMORPH("idiomorph"),

    // allows you to disable inputs, add and remove CSS classes to any element while a request is in-flight
    LOADING_STATES("loading-states"),

    // use the X-HTTP-Method-Override header for non-GET and POST requests
    METHOD_OVERRIDE("method-override"),

    // an extension for using the morphdom library as the swapping mechanism in htmx
    MORPHDOM_SWAP("morphdom-swap"),

    // allows to swap multiple elements with different swap methods
    MULTI_SWAP("multi-swap"),

    // an extension for expressing path-based dependencies similar to intercooler.js
    PATH_DEPS("path-deps"),

    // preloads selected href and hx-get targets based on rules you control
    PRELOAD("preload"),

    // allows you to remove an element after a given amount of time
    REMOVE_ME("remove-me"),

    // allows to specify different target elements to be swapped when different HTTP response codes are received
    RESPONSE_TARGETS("response-targets"),

    // allows you to trigger events when the back button has been pressed
    RESTORED("restored"),

    // uni-directional server push messaging via EventSource
    SERVER_SENT_EVENTS("server-sent-events"),

    // bi-directional connection to WebSocket servers
    WEB_SOCKETS("web-sockets"),

    // allows to use parameters for path variables instead of sending them in query or body
    PATH_PARAMS("path-params");

    override fun toString(): String {
        return value
    }
}