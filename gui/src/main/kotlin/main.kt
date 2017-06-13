package cz.aipsafe.ksafe.gui

import cz.aipsafe.ksafe.gui.app.AppSetup
import cz.aipsafe.ksafe.gui.module.ModuleSetup
import cz.aipsafe.ksafe.gui.root.RootComponent
import cz.aipsafe.ksafe.gui.search.SearchPageSetup
import cz.aipsafe.ksafe.gui.services.login.HttpServices
import kotlin.browser.document

fun example() {

    val services = HttpServices("../safe-www")

    val app = AppSetup(services = services).apply {

        modules += ModuleSetup().apply {
            title = "Smlouvy"
            pages += SearchPageSetup().apply {
                title = "Moje smlouvy"
                query = "Smluva(user = \$USER)"
            }
            pages += SearchPageSetup().apply {
                title = "Najdi smlouvu"
                query = "Smluva()"
            }
        }
        modules += ModuleSetup().apply {
            title = "Faktury"
            pages += SearchPageSetup().apply {
                title = "Moje faktury"
                query = "Faktura(user = \$USER)"
            }
            pages += SearchPageSetup().apply {
                title = "Najdi fakturu"
                query = "Faktura()"
            }
        }
    }
    document.body!!.appendChild(RootComponent(app).root)

}
