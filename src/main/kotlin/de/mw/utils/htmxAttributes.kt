package de.mw.utils

import kotlinx.html.HTMLTag
import org.intellij.lang.annotations.Language


/**
 * The hx-get attribute will cause an element to issue a GET to the specified URL
 * and swap the HTML into the DOM using a swap strategy.
 *
 * [Details](https://htmx.org/attributes/hx-get/)
 */
fun HTMLTag.hxGet(path: String) {
    attributes += "hx-get" to path
}

/**
 * The hx-post attribute will cause an element to issue a POST to the specified URL
 * and swap the HTML into the DOM using a swap strategy (HxSwapOption).
 *
 * [Details](https://htmx.org/attributes/hx-post/)
 */
fun HTMLTag.hxPost(path: String) {
    attributes += "hx-post" to path
}

/**
 * The hx-swap attribute allows you to specify how the response will be swapped in relative to the target of an AJAX request.
 * If you do not specify the option (HxSwapOption), the default is htmx.config.defaultSwapStyle (innerHTML).
 *
 * [Details](https://htmx.org/attributes/hx-swap/)
 */
fun HTMLTag.hxSwap(swapOption: HxSwapOption) {
    attributes += "hx-swap" to swapOption.value
}

/**
 * The hx-swap-oob attribute allows you to specify that some content in a response should be swapped into the DOM
 * somewhere other than the target, that is “Out of Band”.
 * This allows you to piggy back updates to other element updates on a response.
 *
 * TLDR: If hx-swap-oob is set to a boolean value in a server response,
 * it can trigger an update of a specific element on the page that shares the same ID as the response element.
 *
 * [Details](https://htmx.org/attributes/hx-swap-oob/)
 */
fun HTMLTag.hxSwapOob(enabled: Boolean = true) {
    attributes += "hx-swap-oob" to "$enabled"
}

/**
 * The hx-swap-oob attribute allows you to specify that some content in a response should be swapped into the DOM
 * somewhere other than the target, that is “Out of Band”.
 * This allows you to piggy back updates to other element updates on a response.
 *
 * TLDR: If set, a server response containing the corresponding selector can update elements marked with hx-swap-oob,
 * allowing for simultaneous, targeted updates on the page.
 *
 * [Details](https://htmx.org/attributes/hx-swap-oob/)
 */
fun HTMLTag.hxSwapOob(selector: String) {
    attributes += "hx-swap-oob" to selector
}

/**
 * The hx-swap-oob attribute allows you to specify that some content in a response should be swapped into the DOM
 * somewhere other than the target, that is “Out of Band”.
 * This allows you to piggy back updates to other element updates on a response.
 *
 * TLDR: If set, a server response containing the corresponding selector can update elements marked with hx-swap-oob,
 * allowing for simultaneous, targeted updates on the page.
 *
 * [Details](https://htmx.org/attributes/hx-swap-oob/)
 */
fun HTMLTag.hxSwapOob(swapOption: HxSwapOption, selector: String? = null) {
    attributes += "hx-swap-oob" to swapOption.value + selector?.let { ":$it" }
}

@Suppress("unused")
enum class HxSwapOption(val value: String) {
    INNER_HTML("innerHTML"),
    OUTER_HTML("outerHTML"),
    BEFORE_BEGIN("beforebegin"),
    AFTER_BEGIN("afterbegin"),
    BEFORE_END("beforeend"),
    AFTER_END("afterend"),
    DELETE("delete"),
    NONE("none")
}

/**
 * The hx-put attribute will cause an element to issue a PUT to the specified URL
 * and swap the HTML into the DOM using a swap strategy (HxSwapOption).
 *
 * [Details](https://htmx.org/attributes/hx-put/)
 */
fun HTMLTag.hxPut(path: String) {
    attributes += "hx-put" to path
}

/**
 * The hx-delete attribute will cause an element to issue a DELETE to the specified URL
 * and swap the HTML into the DOM using a swap strategy (HxSwapOption).
 *
 * [Details](https://htmx.org/attributes/hx-delete/)
 */
fun HTMLTag.hxDelete(path: String) {
    attributes += "hx-delete" to path
}

/**
 * The hx-patch attribute will cause an element to issue a PATCH to the specified URL
 * and swap the HTML into the DOM using a swap strategy (HxSwapOption).
 *
 * [Details](https://htmx.org/attributes/hx-patch/)
 */
fun HTMLTag.hxPatch(path: String) {
    attributes += "hx-patch" to path
}

/**
 * The hx-on* attributes allow you to embed scripts inline to respond to events directly on an element;
 * Similar to the "onevent" properties found in HTML, such as onClick.
 *
 * [Details](https://htmx.org/attributes/hx-on/)
 */
fun HTMLTag.hxOn(
    event: String,
    @Language("JavaScript")
    jsCode: String
) {
    attributes += "hx-on::$event" to jsCode
}

/**
 * Own extension
 *
 * Resets a form after a successful submit.
 */
fun HTMLTag.hxResetFormAfterSuccess(
) {
    attributes += "hx-on::after-request" to "if(event.detail.successful) this.reset()"
}

/**
 * The hx-push-url attribute allows you to push a URL into the browser location history.
 * This creates a new history entry, allowing navigation with the browser’s back and forward buttons.
 * htmx snapshots the current DOM and saves it into its history cache, and restores from this cache on navigation.
 *
 * [Details](https://htmx.org/attributes/hx-push-url/)
 */
fun HTMLTag.hxPushUrl(boolean: Boolean) {
    attributes += "hx-push-url" to "$boolean"
}

/**
 * The hx-push-url attribute allows you to push a URL into the browser location history.
 * This creates a new history entry, allowing navigation with the browser’s back and forward buttons.
 * htmx snapshots the current DOM and saves it into its history cache, and restores from this cache on navigation.
 *
 * [Details](https://htmx.org/attributes/hx-push-url/)
 */
fun HTMLTag.hxPushUrl(path: String) {
    attributes += "hx-push-url" to path
}

/**
 * The hx-select attribute allows you to select the content you want swapped from a response.
 * The value of this attribute is a CSS query selector of the element or elements to select from the response.
 *
 * [Details](https://htmx.org/attributes/hx-select/)
 */
fun HTMLTag.hxSelect(selector: String) {
    attributes += "hx-select" to selector
}

/**
 * The hx-select-oob attribute allows you to select content from a response to be swapped in via an out-of-band swap.
 * The value of this attribute is comma separated list of elements to be swapped out of band.
 * This attribute is almost always paired with hx-select.
 *
 * TLDR: If hx-select-oob is used in a server response, it enables the selection and manipulation of specific elements
 * in the current page DOM, based on matching criteria, for dynamic content updates.
 *
 * [Details](https://htmx.org/attributes/hx-select-oob/)
 */
fun HTMLTag.hxSelectOob(selector: String) {
    attributes += "hx-select-oob" to selector
}


/**
 * Defines the `hx-target` attribute used to specify the target element for content swapping in AJAX responses.
 * This attribute can accept various values to determine the target element:
 *
 * - A CSS query selector specifying the exact element to target.
 * - `this` to indicate that the element with the `hx-target` attribute is the target.
 * - `closest <CSS selector>` to find the nearest ancestor (or the element itself) matching the specified selector.
 *   For example, `closest tr` targets the nearest table row.
 * - `find <CSS selector>` to locate the first child descendant that matches the specified selector.
 * - `next` which targets the element immediately following the current one in the DOM.
 * - `next <CSS selector>` to find the next element in the DOM that matches the specified selector.
 *   For example, `next .error` would target the next sibling with the `error` class.
 * - `previous` which targets the element immediately preceding the current one in the DOM.
 * - `previous <CSS selector>` to find the previous element in the DOM that matches the specified selector.
 *   For example, `previous .error` would target the previous sibling with the `error` class.
 *
 * [Details](https://htmx.org/attributes/hx-target/)
 */
fun HTMLTag.hxTarget(selector: String) {
    attributes += "hx-target" to selector
}

/**
 * The hx-vals attribute allows you to add to the parameters that will be submitted with an AJAX request.
 *
 * By default, the value of this attribute is a list of name-expression values in JSON format.
 *
 * If you wish for hx-vals to evaluate the values given, you can prefix the values with javascript: or js:
 *
 * Security Considerations
 * By default, the value of hx-vals must be valid JSON.
 * It is not dynamically computed. If you use the "javascript:" prefix, be aware that you are introducing
 * security considerations, especially when dealing with user input such as query strings or user-generated
 * content, which could introduce a Cross-Site Scripting (XSS) vulnerability.
 *
 *
 * [Details](https://htmx.org/attributes/hx-vals/)
 */
fun HTMLTag.hxVals(json: String) {
    attributes += "hx-vals" to json
}

/**
 * The hx-disable attribute will disable htmx processing for a given element and all its children.
 * This can be useful as a backup for HTML escaping, when you include user generated content in your site,
 * and you want to prevent malicious scripting attacks.
 *
 * The value of the tag is ignored, and it cannot be reversed by any content beneath it.
 *
 * [Details](https://htmx.org/attributes/hx-disable/)
 */
fun HTMLTag.hxDisable() {
    attributes += "hx-disable" to "true"
}

/**
 * The hx-disabled-elt attribute allows you to specify elements
 * that will have the disabled attribute added to them for the duration of the request.
 *
 * The value of this attribute is a CSS query selector of the element or elements to apply the class to,
 * or the keyword closest, followed by a CSS selector, which will find the closest ancestor element or itself,
 * that matches the given CSS selector (e.g. "closest tr"), or the keyword "this".
 *
 * [Details](https://htmx.org/attributes/hx-disabled-elt/)
 */
fun HTMLTag.hxDisabled(selector: String = "this") {
    attributes += "hx-disabled-elt" to selector
}

/**
 * The hx-boost attribute allows you to “boost” normal anchors and form tags to use AJAX instead.
 * This has the nice fallback that, if the user does not have javascript enabled, the site will continue to work.
 *
 * [Details](https://htmx.org/attributes/hx-boost/)
 */
fun HTMLTag.hxBoost(value: Boolean = true) {
    attributes += "hx-boost" to "$value"
}

/**
 * The hx-confirm attribute allows you to confirm an action before issuing a request.
 * This can be useful in cases where the action is destructive and you want to ensure that the user really wants to do it.
 *
 * [Details](https://htmx.org/attributes/hx-confirm/)
 */
fun HTMLTag.hxConfirm(text: String) {
    attributes += "hx-confirm" to text
}

/**
 * The hx-prompt attribute allows you to show a prompt before issuing a request.
 * The value of the prompt will be included in the request in the "HX-Prompt" header.
 *
 * [Details](https://htmx.org/attributes/hx-prompt/)
 */
fun HTMLTag.hxPrompt(text: String) {
    attributes += "hx-prompt" to text
}

/**
 * The default behavior for htmx is to “inherit” many attributes automatically: that is,
 * an attribute such as hx-target may be placed on a parent element, and all child elements will inherit that target.
 *
 * The hx-disinherit attribute allows you to control this automatic attribute inheritance.
 * An example scenario is to allow you to place an hx-boost on the body element of a page,
 * but overriding that behavior in a specific part of the page to allow for more specific behaviors.
 *
 * [Details](https://htmx.org/attributes/hx-disinherit/)
 */
fun HTMLTag.hxDisinherit(htmxTags: List<String> = listOf("*")) {
    attributes += "hx-disinherit" to htmxTags.joinToString(" ")
}

/**
 * The hx-encoding attribute allows you to switch the request encoding
 * from the usual "application/x-www-form-urlencoded" encoding to "multipart/form-data", usually
 * to support file uploads in an ajax request.
 *
 * The value of this attribute should be multipart/form-data.
 *
 * The hx-encoding tag may be placed on parent elements.
 *
 * [Details](https://htmx.org/attributes/hx-encoding/)
 */
fun HTMLTag.hxEncoding() {
    attributes += "hx-encoding" to "multipart/form-data"
}

/**
 * The hx-headers attribute allows you to add to the headers that will be submitted with an AJAX request.
 *
 * By default, the value of this attribute is a list of name-expression values in JSON format.
 *
 * If you wish for hx-headers to evaluate the values given, you can prefix the values with "javascript:" or "js:".
 *
 * [Details](https://htmx.org/attributes/hx-headers/)
 */
fun HTMLTag.hxHeaders(headers: String) {
    attributes += "hx-headers" to headers
}

/**
 * Set the hx-history attribute to false on any element in the current document,
 * or any html fragment loaded into the current document by htmx, to prevent sensitive data being saved to
 * the localStorage cache when htmx takes a snapshot of the page state.
 *
 * History navigation will work as expected, but on restoration the URL
 * will be requested from the server instead of the history cache.
 *
 * [Details](https://htmx.org/attributes/hx-history/)
 */
fun HTMLTag.hxHistory() {
    attributes += "hx-history" to "false"
}

/**
 * The hx-history-elt attribute allows you to specify the element that will be used to snapshot and restore
 * page state during navigation. By default, the body tag is used. This is typically good enough for most setups,
 * but you may want to narrow it down to a child element. Just make sure that the element is always visible
 * in your application, or htmx will not be able to restore history navigation properly.
 *
 * [Details](https://htmx.org/attributes/hx-history/)
 */
fun HTMLTag.hxHistoryElt() {
    attributes += "hx-history-elt" to "true"
}

/**
 * The hx-validate attribute will cause an element to validate itself by way of the HTML5 Validation API
 * before it submits a request.
 *
 * Only <form> elements validate data by default, but other elements do not.
 * Adding hx-validate="true" to <input>, <textarea> or <select> enables validation before sending requests.
 *
 * [Details](https://htmx.org/attributes/hx-validate/)
 */
fun HTMLTag.hxValidate() {
    attributes += "hx-validate" to "true"
}

/**
 * The hx-preserve attribute allows you to keep an element unchanged during HTML replacement.
 * Elements with hx-preserve set are preserved by id when htmx updates any ancestor element.
 * You must set an unchanging id on elements for hx-preserve to work.
 * The response requires an element with the same id, but its type and other attributes are ignored.
 *
 * [Details](https://htmx.org/attributes/hx-preserve/)
 */
fun HTMLTag.hxPreserve() {
    attributes += "hx-preserve" to "true"
}

/**
 * The hx-include attribute allows you to include additional element values in an AJAX request.
 * The value of this attribute can be:
 *
 * A CSS query selector of the elements to include.
 *
 * "this" which will include the descendants of the element.
 *
 * "closest <CSS selector>" which will find the closest ancestor element or itself, that matches the given CSS selector
 * (e.g. closest tr will target the closest table row to the element).
 *
 * "find <CSS selector>" which will find the first child descendant element that matches the given CSS selector.
 *
 * "[next <CSS selector>]" which will scan the DOM forward for the first element that matches the given CSS selector.
 * (e.g. next .error will target the closest following sibling element with error class)
 *
 * "previous <CSS selector>" which will scan the DOM backwards for the first element that matches the given CSS selector
 * (e.g previous .error will target the closest previous sibling with error class)
 *
 * [Details](https://htmx.org/attributes/hx-include/)
 */
fun HTMLTag.hxInclude(selector: String = "this") {
    attributes += "hx-include" to selector
}

/**
 * The hx-sync attribute allows you to synchronize AJAX requests between multiple elements.
 *
 * The hx-sync attribute consists of a CSS selector to indicate the element to synchronize on,
 * followed optionally by a colon and then by an optional syncing strategy. The available strategies are:
 *
 * "drop" - drop (ignore) this request if an existing request is in flight (the default)
 * "abort" - drop (ignore) this request if an existing request is in flight, and, if that is not the case, abort this request if another request occurs while it is still in flight
 * "replace" - abort the current request, if any, and replace it with this request
 * "queue" - place this request in the request queue associated with the given element
 * The "queue" modifier can take an additional argument indicating exactly how to queue:
 *
 * queue first - queue the first request to show up while a request is in flight
 * queue last - queue the last request to show up while a request is in flight
 * queue all - queue all requests that show up while a request is in flight
 *
 *
 * [Details](https://htmx.org/attributes/hx-sync/)
 */
fun HTMLTag.hxSync(selector: String, modifier: SyncModifier = SyncModifier.DROP) {
    attributes += "hx-sync" to "$selector :${modifier.value}"
}

enum class SyncModifier(val value: String) {
    DROP("drop"),
    ABORT("abort"),
    REPLACE("replace"),
    QUEUE_FIRST("queue first"),
    QUEUE_LAST("queue last"),
    QUEUE_ALL("queue all")
}

/**
 * The hx-params attribute allows you to filter the parameters that will be submitted with an AJAX request.
 *
 * The possible values of this attribute are:
 *
 * "*" - Include all parameters (default)
 * "none" - Include no parameters
 * "not <param-list>" - Include all except the comma separated list of parameter names
 * "<param-list>" - Include all the comma separated list of parameter names
 *
 * [Details](https://htmx.org/attributes/hx-params/)
 */
fun HTMLTag.hxParams(value: String = "*") {
    attributes += "hx-params" to value
}

/**
 * Exclude version of hxParams function to handle excluding certain parameters.
 * "not <param-list>" - Include all except the comma-separated list of parameter names.
 * Utilizes vararg to make it easy to pass multiple parameters to exclude.
 */
fun HTMLTag.hxParamsExclude(vararg params: String) {
    attributes += if (params.isNotEmpty()) {
        "hx-params" to params.joinToString(",")
    } else {
        // If no param is provided we can assume we do not want to exclude any param
        "hx-params" to "*"
    }
}

/**
 * Include version of hxParams function to handle including only specific parameters.
 * "<param-list>" - Include only the comma-separated list of parameter names.
 * Utilizes vararg to allow passing multiple parameters to include.
 */
fun HTMLTag.hxParamsInclude(vararg params: String) {
    attributes += if (params.isNotEmpty()) {
        "hx-params" to params.joinToString(",")
    } else {
        // If no param is provided we can assume we do not want any param.
        "hx-params" to "none"
    }
}

/**
 * The hx-request attribute allows you to configure various aspects of the request via the following attributes:
 *
 * "timeout" - the timeout for the request, in milliseconds
 * "credentials" - if the request will send credentials
 * "noHeaders" - strips all headers from the request
 *
 * It is possible to concat multiple with a ","
 *
 * You may make the values dynamically evaluated by adding the javascript: or js: prefix.
 *
 * [Details](https://htmx.org/attributes/hx-params/)
 */
fun HTMLTag.hxRequest(requestAttributes: String) {
    attributes += "hx-request" to requestAttributes
}

/**
 * The hx-indicator attribute allows you to specify the element that will have the htmx-request class
 * added to it for the duration of the request.
 * This can be used to show spinners or progress indicators while the request is in flight.
 *
 * The value of this attribute is a CSS query selector of the element or elements to apply the class to,
 * or the keyword closest, followed by a CSS selector, which will find the closest ancestor element or itself,
 * that matches the given CSS selector (e.g. closest tr);
 *
 * [Details](https://htmx.org/attributes/hx-indicator/)
 */
fun HTMLTag.hxIndicator(selector: String) {
    attributes += "hx-indicator" to selector
}

/**
 * The hx-replace-url attribute allows you to replace the current url of the browser location history.
 *
 * The possible values of this attribute are:
 *
 * "true", which replaces the fetched URL in the browser navigation bar.
 * "false", which disables replacing the fetched URL if it would otherwise be replaced due to inheritance.
 * A URL to be replaced into the location bar. This may be relative or absolute, as per history.replaceState().
 *
 * [Details](https://htmx.org/attributes/hx-replace-url/)
 */
fun HTMLTag.hxReplaceUrl(useFetchedUrl: Boolean) {
    attributes += "hx-replace-url" to useFetchedUrl.toString()
}

/**
 * The hx-replace-url attribute allows you to replace the current url of the browser location history.
 *
 * The possible values of this attribute are:
 *
 * "true", which replaces the fetched URL in the browser navigation bar.
 * "false", which disables replacing the fetched URL if it would otherwise be replaced due to inheritance.
 * A URL to be replaced into the location bar. This may be relative or absolute, as per history.replaceState().
 *
 * [Details](https://htmx.org/attributes/hx-replace-url/)
 */
fun HTMLTag.hxReplaceUrl(url: String) {
    attributes += "hx-replace-url" to url
}

/**
 * The hx-trigger attribute allows you to specify what triggers an AJAX request.
 * A trigger value can be one of the following:
 *
 * An event name (e.g. “click” or “my-custom-event”) followed by an event filter and a set of event modifiers
 * A polling definition of the form "every <timing declaration>"
 * A comma-separated list of such events
 *
 * [Details](https://htmx.org/attributes/hx-trigger/)
 */
fun HTMLTag.hxTrigger(trigger: String) {
    attributes += "hx-trigger" to trigger
}

/**
 * The hx-ext attribute enables a htmx extension for an element and all its children.
 *
 * The value can be a single extension name or a comma separated list of extensions to apply.
 *
 * The hx-ext tag may be placed on parent elements if you want a plugin to apply to an entire swath of the DOM,
 * and on the body tag for it to apply to all htmx requests.
 *
 * [Details](https://htmx.org/attributes/hx-ext/)
 */
fun HTMLTag.hxExt(extension: HtmxExtension) {
    attributes += "hx-ext" to extension.name
}
