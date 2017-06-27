package cz.aipsafe.ksafe.gui.search

import cz.aipsafe.ksafe.gui.components.base.Component
import cz.aipsafe.ksafe.gui.components.html.*
import cz.aipsafe.ksafe.gui.components.module.ModulePageComponent
import cz.aipsafe.ksafe.gui.model.AppModel
import cz.aipsafe.ksafe.gui.setup.SearchPageSetup
import cz.aipsafe.ksafe.gui.setup.SearchTableSetup
import cz.aipsafe.ksafe.shared.services.common.DoublePropertyValue
import cz.aipsafe.ksafe.shared.services.common.StringPropertyValue
import cz.aipsafe.ksafe.shared.services.search.SearchPostRequest
import cz.aipsafe.ksafe.shared.services.search.SearchPostResponse
import org.w3c.dom.HTMLTableSectionElement
import kotlin.browser.document
import kotlin.dom.addClass

/*
 * Module search page.
 */
class SearchPageComponent(setup: SearchPageSetup, model: AppModel): ModulePageComponent() {

    val content = SearchTableComponent(setup.content, model)

    override val root = document.generator.div {
        el.classList.add(::SearchPageComponent.name)
        h3 {
            +setup.title
        }
        +content.root
    }
}

class SearchTableComponent(val setup: SearchTableSetup, val model: AppModel): Component {

    lateinit var head: HTMLTableSectionElement
    lateinit var body: HTMLTableSectionElement

    override val root = document.generator.div {
        el.addClass(::SearchTableComponent.name)
        table {
            thead {
                head = el
            }
            tbody {
                body = el
            }
        }

    }

    init {
        load()
    }

    private fun load() {
        val searchService = model.services.searchService
        val request = SearchPostRequest(query = setup.query)
        searchService.postPromise(request).then({response->
            showResponse(response)
        }).catch {error->
            println(error.toString())
//TODO: How to show errors??????
            throw error
        }
    }

    private fun showResponse(response: SearchPostResponse) {
        body.regenerate {
              for (item in response.items) {
                tr {
                    td {
                        +item.info.display
                    }
                    for (property in item.properties) {
                        td {
                            when (property.value) {
                                is StringPropertyValue -> +("Str:"+property.value.string)
                                is DoublePropertyValue -> +("Double"+property.value.double.toString())
                            }
                            +("("+property.toString()+")")
                        }
                    }
                }
            }
        }
    }
}
