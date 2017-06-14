package cz.aipsafe.ksafe.gui.components.app

import cz.aipsafe.ksafe.gui.components.base.Component
import cz.aipsafe.ksafe.gui.components.html.*
import cz.aipsafe.ksafe.gui.components.module.ModuleComponent
import cz.aipsafe.ksafe.gui.setup.AppSetup
import cz.aipsafe.ksafe.gui.setup.ModuleSetup
import kotlin.browser.document
import kotlin.dom.addClass
import kotlin.dom.clear
import kotlin.dom.removeClass


/**
 * Application component. Contains menu and body.
 */
class AppComponent(setup: AppSetup): Component {

    private val appMenu = AppMenuComponent(setup).apply {
        onSelectItemRequest = {
            body.selectedIndex = it
            selectedIndex = it
        }
        onLogoutRequest = {this@AppComponent.onLogoutRequest()}
    }

    private val body = AppBodyComponent(setup)

    /**
     * When logout is requested.
     */
    var onLogoutRequest = {}

    override val root = document.generator.div {
        el.classList.add(::AppComponent.name)

        +appMenu.root
        +body.root
    }
}

/**
 * Application menu component. Contains application menu items.
 */
class AppMenuComponent(setup: AppSetup): Component {

    private val menuItems = setup.modules.mapIndexed { index, it  ->
        val component = AppMenuItemComponent(it)
        component.onSelectRequest = {
            onSelectItemRequest(index)
        }
        component
    }

    /**
     * Selected menu item.
     */
    var selectedIndex = -1
        set(value) {
            val oldIndex = field
            field = value
            if (oldIndex != value) {
                if (oldIndex in menuItems.indices) {
                    menuItems[oldIndex].selected = false
                }
                if (value in menuItems.indices) {
                    menuItems[value].selected = true
                }
            }
        }

    /**
     * Event generated when user selects another module.
     */
    var onSelectItemRequest = {_: Int -> }

    /**
     * Event generated when wants logout.
     */
    var onLogoutRequest = {}

    override val root = document.generator.div {
        el.addClass(::AppMenuComponent.name, "pure-menu pure-menu-horizontal")

        span {
            el.addClass("pure-menu-heading")

            +setup.title
        }
        ul {
            el.addClass("pure-menu-list")

            for (menuItem in menuItems) {
                +menuItem.root
            }

            li {
                el.addClass("pure-menu-item", "pure-menu-has-children", "pure-menu-allow-hover")

                span {
                    el.addClass("pure-menu-link")
                    +"|||"
                }
                ul {
                    el.addClass("pure-menu-children")

                    li {
                        el.addClass("pure-menu-item")
                        span {
                            el.addClass("pure-menu-link")
                            el.onclick = {
                                onLogoutRequest()
                            }
                            +"Odhlásit"
                        }
                    }
                }
            }

        }
    }

    init {
        selectedIndex = setup.defaultModuleIndex
    }
}

/**
 * Application menu item component.
 */
class AppMenuItemComponent(setup: ModuleSetup, selectedSetup: Boolean = false): Component {

    //Is menu item selected?
    var selected: Boolean = selectedSetup
        set(value) {
            val oldValue = field
            field = value
            if (!oldValue && value) {
                root.addClass("pure-menu-selected")
            } else if (oldValue && !value) {
                root.removeClass("pure-menu-selected")
            }
        }

    /**
     * Event generated when user wants to select menu item
     */
    var onSelectRequest = {}

    override val root = document.generator.li {

        el.addClass(::AppMenuItemComponent.name, "pure-menu-item")
        if (selected) {
            el.addClass("pure-menu-selected")
        }

        el.onclick = {_ ->
            if (!selected) onSelectRequest()
        }

        span {
            el.addClass("pure-menu-link")
            +setup.title
        }
    }
}

/**
 * Application body component. Contains application modules.
 */
class AppBodyComponent(setup: AppSetup): Component {

    private val modules = setup.modules.map { it ->  ModuleComponent(it) }

    /**
     * Selected menu item.
     */
    var selectedIndex = -1
        set(value) {
            val oldIndex = field
            field = value
            if (oldIndex != value) {
                root.clear()
                root.generate {
                    if (value in modules.indices) {
                        +modules[value].root
                    }
                }
            }
        }

    override val root = document.generator.div {
        el.classList.add(::AppBodyComponent.name)
    }

    init {
        selectedIndex = setup.defaultModuleIndex
    }
}



