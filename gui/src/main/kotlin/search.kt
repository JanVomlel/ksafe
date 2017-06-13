package cz.aipsafe.ksafe.gui.search

import cz.aipsafe.ksafe.gui.module.ModulePageComponent
import cz.aipsafe.ksafe.gui.module.PageSetup
import cz.aipsafe.ksafe.html.generator
import kotlin.browser.document

/**
 * Setup for search page.
 */
class SearchPageSetup(
        title: String = "",
        var query: String = ""
): PageSetup(title)

/*
 * Module search page.
 */
class SearchPageComponent(setup: SearchPageSetup): ModulePageComponent() {

    override val root = document.generator.div {
        el.classList.add(::SearchPageComponent.name)
        h3 {
            +setup.title
        }
        pre {
            +setup.query
        }
    }
}

