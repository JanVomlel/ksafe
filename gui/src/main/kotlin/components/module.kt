package cz.aipsafe.ksafe.gui.components.module

import cz.aipsafe.ksafe.gui.components.base.Component
import cz.aipsafe.ksafe.gui.components.html.*
import cz.aipsafe.ksafe.gui.model.AppModel
import cz.aipsafe.ksafe.gui.search.SearchPageComponent
import cz.aipsafe.ksafe.gui.setup.ModuleSetup
import cz.aipsafe.ksafe.gui.setup.PageSetup
import cz.aipsafe.ksafe.gui.setup.SearchPageSetup
import kotlin.browser.document
import kotlin.dom.addClass
import kotlin.dom.clear

/**
 * Module Component. Module component has menu and body.
 */
class ModuleComponent(setup: ModuleSetup, model: AppModel): Component {

    private val menu: ModuleMenuComponent = ModuleMenuComponent(setup).apply {
        onSelectItemRequest = {index->
            body.selectedPageIndex = index
            selectedIndex = index
        }
    }

    private val body: ModuleBodyComponent = ModuleBodyComponent(setup, model)

    override val root = document.generator.div {
        el.classList.add(::ModuleComponent.name)
        table {
            tr {
                td {
                    +menu.root
                }
                td {
                    +body.root
                }
            }
        }
    }
}

/**
 * Module Menu Component. Contains module menu items. One of them is selected.
 */
class ModuleMenuComponent(setup: ModuleSetup): Component {

    //Menu items
    private val menuItems = setup.pages.mapIndexed { index, it  ->
        val component = ModuleMenuItemComponent(it)
        component.onSelectRequest = {
            onSelectItemRequest(index)
        }
        component
    }

    /**
     * Selected menu item
     */
    var selectedIndex = -1
        set(value) {
            val oldIndex = field
            field = value
            if (oldIndex in menuItems.indices) {
                menuItems[oldIndex].selected = false
            }
            if (value in menuItems.indices) {
                menuItems[value].selected = true
            }
        }

    /**
     * Event generated when user wants another menu item.
     */
    var onSelectItemRequest = {_: Int -> }

    override val root = document.generator.div {
        el.addClass(::ModuleMenuComponent.name, "pure-menu")

        span {
            el.addClass("pure-menu-heading")

            +setup.title
        }
        ul {
            el.addClass("pure-menu-list")

            for (menuItem in menuItems) {
                +menuItem.root
            }
        }
    }

    init {
        selectedIndex = setup.defaultPageIndex
    }
}

/**
 * Module menu item component. Can be selected or unselected.
 */
class ModuleMenuItemComponent(setup: PageSetup, selectedSetup: Boolean = false): Component {

    /**
     * Is menu item selected?
     */
    var selected: Boolean = selectedSetup
        set(value) {
            val oldValue = field
            field = value
            if (oldValue != value) {
                if (value) {
                    root.classList.add("pure-menu-selected")
                } else {
                    root.classList.remove("pure-menu-selected")
                }
            }
        }

    /**
     * Event generated when user selects this menu item.
     */
    var onSelectRequest = {}

    override val root = document.generator.li {
        el.addClass(::ModuleMenuItemComponent.name, "pure-menu-item")
        if (selected) el.addClass("pure-menu-selected")
        el.onclick = {_->
            if (!selected) onSelectRequest()
        }
        span {
            el.addClass("pure-menu-link")
            +setup.title
        }
    }
}

/**
 * Module body component. Contains module pages. One of the pages is selected.
 */
class ModuleBodyComponent(setup: ModuleSetup, model: AppModel): Component {

    private val pages: List<ModulePageComponent> = setup.pages.map { it  ->
        val component = when(it) {
            is SearchPageSetup -> SearchPageComponent(it, model)
            else -> throw IllegalArgumentException("Unknown menu item")
        }
        component
    }

    /** Which page is selected. */
    var selectedPageIndex = -1
        set(value) {
            val oldIndex = field
            field = value
            if (oldIndex != value) {
                root.clear()
                if (value in pages.indices) {
                    root.appendChild(pages[value].root)
                }
            }
        }

    override val root = document.generator.div {
        el.classList.add(::ModuleBodyComponent.name)
    }

    init {
        selectedPageIndex = setup.defaultPageIndex
    }
}

/**
 * Module page component. It opens in the body of the module. Use descendants of this abstract class.
 */
abstract class ModulePageComponent: Component

