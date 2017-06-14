package cz.aipsafe.ksafe.gui.components.html

import kotlinx.html.dom.create
import kotlinx.html.js.*
import org.w3c.dom.*
import kotlin.browser.document
import kotlin.dom.appendText
import kotlin.dom.clear
import kotlin.reflect.KMutableProperty0

fun <T: HTMLElement> T.generate (block: HTMLDOMGenerator<T>.()->Unit) {
    HTMLDOMGenerator(this).apply(block)
}

fun <T: HTMLElement> T.regenerate (block: HTMLDOMGenerator<T>.()->Unit) {
    this.clear()
    HTMLDOMGenerator(this).apply(block)
}

val Document.generator: HTMLDOMGenerator<HTMLElement> get() {
    return HTMLDOMGenerator<HTMLElement>()
}

class HTMLDOMGenerator<T: HTMLElement>(val forElement: T? = null) {

    val el: T get() = forElement!!

    operator fun String.unaryPlus(): kotlin.Unit {
        el.appendText(this)
    }

    operator fun HTMLElement.unaryPlus(): kotlin.Unit {
        el.appendChild(this)
    }

    private fun <T: HTMLElement> accept(
            el: T,
            property: KMutableProperty0<T>? = null,
            block: HTMLDOMGenerator<T>.()->Unit = {}
    ): T {
        HTMLDOMGenerator(el).apply(block)
        forElement?.appendChild(el)
        property?.set(el)
        return el
    }

    fun to(property: KMutableProperty0<T>) {
        property.set(forElement!!)
    }

    fun div(property: KMutableProperty0<HTMLDivElement>? = null, block: HTMLDOMGenerator<HTMLDivElement>.()->Unit = {}): HTMLDivElement {
        return accept(document.create.div(), property, block)
    }

    fun span(property: KMutableProperty0<HTMLSpanElement>? = null, block: HTMLDOMGenerator<HTMLSpanElement>.()->Unit = {}): HTMLSpanElement {
        return accept(document.create.span(), property, block)
    }

    fun h1(property: KMutableProperty0<HTMLHeadingElement>? = null, block: HTMLDOMGenerator<HTMLHeadingElement>.()->Unit = {}): HTMLHeadingElement {
        return accept(document.create.h1(), property, block)
    }

    fun h2(property: KMutableProperty0<HTMLHeadingElement>? = null, block: HTMLDOMGenerator<HTMLHeadingElement>.()->Unit = {}): HTMLHeadingElement {
        return accept(document.create.h2(), property, block)
    }

    fun h3(property: KMutableProperty0<HTMLHeadingElement>? = null, block: HTMLDOMGenerator<HTMLHeadingElement>.()->Unit = {}): HTMLHeadingElement {
        return accept(document.create.h3(), property, block)
    }

    fun h4(property: KMutableProperty0<HTMLHeadingElement>? = null, block: HTMLDOMGenerator<HTMLHeadingElement>.()->Unit = {}): HTMLHeadingElement {
        return accept(document.create.h4(), property, block)
    }

    fun h5(property: KMutableProperty0<HTMLHeadingElement>? = null, block: HTMLDOMGenerator<HTMLHeadingElement>.()->Unit = {}): HTMLHeadingElement {
        return accept(document.create.h5(), property, block)
    }

    fun h6(property: KMutableProperty0<HTMLHeadingElement>? = null, block: HTMLDOMGenerator<HTMLHeadingElement>.()->Unit = {}): HTMLHeadingElement {
        return accept(document.create.h6(), property, block)
    }

    fun ul(property: KMutableProperty0<HTMLElement>? = null, block: HTMLDOMGenerator<HTMLElement>.()->Unit = {}): HTMLElement {
        return accept(document.create.ul(), property, block)
    }

    fun ol(property: KMutableProperty0<HTMLElement>? = null, block: HTMLDOMGenerator<HTMLElement>.()->Unit = {}): HTMLElement {
        return accept(document.create.ol(), property, block)
    }

    fun li(property: KMutableProperty0<HTMLLIElement>? = null, block: HTMLDOMGenerator<HTMLLIElement>.()->Unit = {}): HTMLLIElement {
        return accept(document.create.li(), property, block)
    }

    fun hr(property: KMutableProperty0<HTMLHRElement>? = null, block: HTMLDOMGenerator<HTMLHRElement>.()->Unit = {}): HTMLHRElement {
        return accept(document.create.hr(), property, block)
    }

    fun b(property: KMutableProperty0<HTMLElement>? = null, block: HTMLDOMGenerator<HTMLElement>.()->Unit = {}): HTMLElement {
        return accept(document.create.b(), property, block)
    }

    fun i(property: KMutableProperty0<HTMLElement>? = null, block: HTMLDOMGenerator<HTMLElement>.()->Unit = {}): HTMLElement {
        return accept(document.create.i(), property, block)
    }

    fun pre(property: KMutableProperty0<HTMLPreElement>? = null, block: HTMLDOMGenerator<HTMLPreElement>.()->Unit = {}): HTMLPreElement {
        return accept(document.create.pre(), property, block)
    }

    fun table(property: KMutableProperty0<HTMLTableElement>? = null, block: HTMLDOMGenerator<HTMLTableElement>.()->Unit = {}): HTMLTableElement {
        return accept(document.create.table(), property, block)
    }

    fun tr(property: KMutableProperty0<HTMLTableRowElement>? = null, block: HTMLDOMGenerator<HTMLTableRowElement>.()->Unit = {}): HTMLTableRowElement {
        return accept(document.create.tr(), property, block)
    }

    fun th(property: KMutableProperty0<HTMLTableColElement>? = null, block: HTMLDOMGenerator<HTMLTableColElement>.()->Unit = {}): HTMLTableColElement {
        return accept(document.create.th(), property, block)
    }

    fun td(property: KMutableProperty0<HTMLTableCellElement>? = null, block: HTMLDOMGenerator<HTMLTableCellElement>.()->Unit = {}): HTMLTableCellElement {
        return accept(document.create.td(), property, block)
    }

    fun a(property: KMutableProperty0<HTMLAnchorElement>? = null, block: HTMLDOMGenerator<HTMLAnchorElement>.()->Unit = {}): HTMLAnchorElement {
        return accept(document.create.a(), property, block)
    }

    fun form(property: KMutableProperty0<HTMLFormElement>? = null, block: HTMLDOMGenerator<HTMLFormElement>.()->Unit = {}): HTMLFormElement {
        return accept(document.create.form(), property, block)
    }

    fun fieldSet(property: KMutableProperty0<HTMLFieldSetElement>? = null, block: HTMLDOMGenerator<HTMLFieldSetElement>.()->Unit = {}): HTMLFieldSetElement {
        return accept(document.create.fieldSet(), property, block)
    }

    fun input(property: KMutableProperty0<HTMLInputElement>? = null, block: HTMLDOMGenerator<HTMLInputElement>.()->Unit = {}): HTMLInputElement {
        return accept(document.create.input(), property, block)
    }

    fun button(property: KMutableProperty0<HTMLButtonElement>? = null, block: HTMLDOMGenerator<HTMLButtonElement>.()->Unit = {}): HTMLButtonElement {
        return accept(document.create.button(), property, block)
    }

    fun legend(property: KMutableProperty0<HTMLLegendElement>? = null, block: HTMLDOMGenerator<HTMLLegendElement>.()->Unit = {}): HTMLLegendElement {
        return accept(document.create.legend(), property, block)
    }
}
