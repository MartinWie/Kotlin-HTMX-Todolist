package de.mw.utils

@Suppress("unused")
enum class HtmxHeaders(val value: String) {
    // Indicates that the request is via an element using hx-boost
    REQUEST_HX_BOOSTED("HX-Boosted"),

    // The current URL of the browser
    REQUEST_HX_CURRENT_URL("HX-Current-URL"),

    // "true" if the request is for history restoration after a miss in the local history cache
    REQUEST_HX_HISTORY_RESTORE_REQUEST("HX-History-Restore-Request"),

    // The user response to an hx-prompt
    REQUEST_HX_PROMPT("HX-Prompt"),

    // Always "true"
    REQUEST_HX_REQUEST("HX-Request"),

    // The id of the target element if it exists
    REQUEST_HX_TARGET("HX-Target"),

    // The name of the triggered element if it exists
    REQUEST_HX_TRIGGER_NAME("HX-Trigger-Name"),

    // The id of the triggered element if it exists
    REQUEST_HX_TRIGGER("HX-Trigger"),

    /**
     * This response header can be used to trigger a client side redirection without reloading the whole page.
     * Instead of changing the page’s location it will act like following a hx-boost link, creating a new history entry,
     * issuing an ajax request to the value of the header and pushing the path into history.
     *
     * [Details](https://htmx.org/headers/hx-location/)
     */
    RESPONSE_HX_LOCATION("HX-Location"),

    /**
     * The HX-Push-Url header allows you to push a URL into the browser location history.
     * This creates a new history entry, allowing navigation with the browser’s back and forward buttons.
     * This is similar to the hx-push-url attribute.
     *
     * If present, this header overrides any behavior defined with attributes.
     *
     * [Details](https://htmx.org/headers/hx-push-url/)
     */
    RESPONSE_HX_PUSH_URL("HX-Push-Url"),

    // Can be used to do a client-side redirect to a new location
    RESPONSE_HX_REDIRECT("HX-Redirect"),

    // If set to "true" the client-side will do a full refresh of the page
    RESPONSE_HX_REFRESH("HX-Refresh"),

    /**
     * The HX-Replace-Url header allows you to replace the current URL in the browser location history.
     * This does not create a new history entry; in effect,
     * it removes the previous current URL from the browser’s history.
     *
     * This is similar to the hx-replace-url attribute.
     *
     * [Details](https://htmx.org/headers/hx-replace-url/)
     */
    RESPONSE_HX_REPLACE_URL("HX-Replace-Url"),

    // Allows you to specify how the response will be swapped. See hx-swap for possible values
    RESPONSE_HX_RESWAP("HX-Reswap"),

    // A CSS selector that updates the target of the content update to a different element on the page
    RESPONSE_HX_RETARGET("HX-Retarget"),

    // A CSS selector that allows you to choose which part of the response is used to be swapped in. Overrides an existing hx-select on the triggering element
    RESPONSE_HX_RESELECT("HX-Reselect"),

    /**
     * These response headers can be used to trigger client side actions on the target element within a response
     * to htmx.
     *
     * You can trigger a single event or as many uniquely named events as you would like.
     *
     * The headers are:
     *
     * HX-Trigger - trigger events as soon as the response is received.
     * HX-Trigger-After-Settle - trigger events after the settling step.
     * HX-Trigger-After-Swap - trigger events after the swap step.
     *
     * [Details](https://htmx.org/headers/hx-trigger/)
     */
    RESPONSE_HX_TRIGGER("HX-Trigger"),
    RESPONSE_HX_TRIGGER_AFTER_SETTLE("HX-Trigger-After-Settle"),
    RESPONSE_HX_TRIGGER_AFTER_SWAP("HX-Trigger-After-Swap");

    override fun toString(): String {
        return value
    }
}