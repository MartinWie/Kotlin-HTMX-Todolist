package de.mw.utils

import kotlinx.html.*
import kotlinx.html.stream.appendHTML


fun htmlBasePage(pageTitle: String, bodyTags: TagConsumer<StringBuilder>.() -> Unit): String {
    return "<!DOCTYPE html>" + buildHTMLString {
        head {
            title = pageTitle
            script { src = "https://unpkg.com/htmx.org@1.9.11" }
            link {
                rel = "stylesheet"
                href = "https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css"
            }
            script(type = ScriptType.textJavaScript) {
                unsafe {
                    // Using unsafe{}.raw() to insert raw HTML/JS.
                    // Be cautious with 'unsafe' as it can potentially open up for XSS vulnerabilities
                    // if untrusted user input is inserted into the HTML.
                    raw(
                        """
            document.addEventListener("DOMContentLoaded", (event) => {
                document.body.addEventListener('htmx:beforeSwap', function(evt) {
                    if (evt.detail.xhr.status === 422) {
                        console.log("setting status to paint");
                        // allow 422 responses to swap as we are using this as a signal that
                        // a form was submitted with bad data and want to rerender with the errors
                        //
                        // set isError to false to avoid error logging in console
                        evt.detail.shouldSwap = true;
                        evt.detail.isError = false;
                    }
                });
            });
        """.trimIndent()
                    )
                }
            }

        }
        body {
            classes = setOf("text-center", "bg-cyan-600")
            bodyTags()
        }
    }
}

fun buildHTMLString(builderAction: TagConsumer<StringBuilder>.() -> Unit): String {
    return buildString {
        appendHTML().builderAction()
    }
}

fun TagConsumer<*>.createTodoTag(todoId: String, todoValue: String, classes: String? = null) {
    p {
        this.id = todoId
        if (classes != null) {
            this.classes = setOf(classes)
        }
        hxDelete("/todo/$todoId")
        hxSwap(HxSwapOption.DELETE)
        +todoValue
    }
}