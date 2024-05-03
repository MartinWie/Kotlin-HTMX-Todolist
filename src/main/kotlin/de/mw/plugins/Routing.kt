package de.mw.plugins

import de.mw.utils.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.html.*
import java.util.*

fun Application.configureRouting() {

    val tmpTodoState = mutableMapOf<String, String>()

    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause", status = HttpStatusCode.InternalServerError)
        }
    }
    routing {
        get("/") {
            val htmlContent = htmlBasePage("Todo List") {
                h1 {
                    classes = setOf("text-4xl", "font-bold", "underline")
                    +"Todo test app"
                }

                form {
                    id = "todo-form"
                    hxPost("/todo")
                    hxSwap(HxSwapOption.NONE)
                    hxResetFormAfterSuccess()

                    input {
                        type = InputType.text
                        name = "todoItem"
                        classes = setOf("border-4", "border-black-800", "rounded-md", "border-solid", "m-4")
                    }

                    button {
                        type = ButtonType.submit
                        +"Add"
                    }
                }
                div {
                    id = "todo-input-error"
                }

                div {
                    id = "todos"

                    tmpTodoState.keys.reversed().forEach { todoId ->
                        createTodoTag(todoId, tmpTodoState[todoId]!!)
                    }
                }
            }

            call.respondText(htmlContent, ContentType.Text.Html)
        }

        post("/todo") {
            val userInput = call.receiveParameters()["todoItem"]

            if (userInput.isNullOrEmpty() || tmpTodoState.values.contains(userInput)) {
                call.response.header(
                    HtmxHeaders.RESPONSE_HX_RETARGET.value,
                    "#todo-input-error"
                )
                call.response.header(
                    HtmxHeaders.RESPONSE_HX_RESWAP.value,
                    HxSwapOption.INNER_HTML.name
                )
                val errorMessage = "Error: Todo is empty or already exists!"

                call.respondText(errorMessage, ContentType.Text.Html, HttpStatusCode.UnprocessableEntity)
                return@post
            }

            val newTodoId = UUID.randomUUID().toString()
            tmpTodoState[newTodoId] = userInput

            val htmlContent = buildHTMLString {
                div {
                    id = "todos"
                    hxSwapOob("afterbegin")
                    createTodoTag(newTodoId, userInput)
                }

                div {
                    id = "todo-input-error"
                    hxSwapOob()
                }
            }

            call.respondText(htmlContent, ContentType.Text.Html)
        }

        delete("/todo/{todoId}") {
            val todoId = call.parameters["todoId"]
            if (tmpTodoState.remove(todoId) != null) {
                call.respond(HttpStatusCode.OK)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }

        staticResources("/static", "static")
    }
}
