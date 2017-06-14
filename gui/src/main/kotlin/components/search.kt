package cz.aipsafe.ksafe.gui.search

import cz.aipsafe.ksafe.gui.components.html.*
import cz.aipsafe.ksafe.gui.components.module.ModulePageComponent
import cz.aipsafe.ksafe.gui.setup.SearchPageSetup
import kotlin.browser.document

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

