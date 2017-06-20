package cz.aipsafe.ksafe.gui.components

import cz.aipsafe.ksafe.gui.components.app.AppBodyComponent
import cz.aipsafe.ksafe.gui.components.app.AppComponent
import cz.aipsafe.ksafe.gui.components.app.AppMenuComponent
import cz.aipsafe.ksafe.gui.components.app.AppMenuItemComponent
import cz.aipsafe.ksafe.gui.components.base.Component
import cz.aipsafe.ksafe.gui.components.login.LoginComponent
import cz.aipsafe.ksafe.gui.components.module.*
import cz.aipsafe.ksafe.gui.components.root.RootComponent
import cz.aipsafe.ksafe.gui.search.SearchPageComponent
import kotlin.reflect.KClass


/**
 * Components structure.
 * It helps to find component.
 * It is not used by application.
 */
val structure = Structure(RootComponent::class).apply {

    m(LoginComponent::class)
    m(AppComponent::class) {
        m(AppMenuComponent::class) {
            m(AppMenuItemComponent::class)
        }
        m(AppBodyComponent::class) {
            m(ModuleComponent::class) {
                m(ModuleMenuComponent::class) {
                    m(ModuleMenuItemComponent::class)
                }
                m(ModuleBodyComponent::class) {
                    m(ModulePageComponent::class) {
                        m(SearchPageComponent::class)
                    }
                }
            }
        }
    }
}

class Structure<out T: Component>(component: KClass<T>) {

    val name = component.toString()

    var manages: List<Structure<Component>> = listOf()

    fun <R: Component> m(component: KClass<R>, apply: Structure<R>.()->Unit = {}) {
        manages += Structure(component).apply(apply)
    }
}
